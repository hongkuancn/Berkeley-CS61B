package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    private static final Random RANDOM = new Random(2);

    public static void drawLine(TETile[][] world, int startX, int startY, int space, int content, TETile t) {
        while (space != 0) {
            space -= 1;
            startX += 1;
        }
        while (content != 0) {
            world[startX][startY] = t;
            content -= 1;
            startX += 1;
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        for (int y = p.y, i = 0; y < p.y + s; y += 1, i += 1) {
            drawLine(world, p.x, y, s - 1 - i, s + 2 * i, t);
        }
        for (int y = p.y + s, i = s - 1; y < p.y + 2 * s; y += 1, i -= 1) {
            drawLine(world, p.x, y, s - 1 - i, s + 2 * i, t);
        }

    }

    public static void addTopRightHexagon(TETile[][] world, Position p, int small, int i) {
        TETile t = randomTile();
        addHexagon(world, new Position(p.x + i * (2 * small - 1), p.y + small * i), small, t);
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int small) {
        for (int i = 0; i < s; i += 1) {
            addTopRightHexagon(world, p, small, i);
        }
    }

    public static void drawRandomBigHexagon(TETile[][] world, Position p, int s, int small) {
        Position pnew = new Position(p.x + (s - 1) * (2 * small - 1), p.y);
        for (int i = 0; i < s; i++) {
            drawRandomVerticalHexes(world, new Position(pnew.x - i * (2 * small - 1), pnew.y + small * i), s + i, small);
        }

        for (int i = s - 1; i > 0; i--) {
            drawRandomVerticalHexes(world, new Position(p.x,p.y + small * ( s + 1) + 2 * small * (s - 1 - i)), s + i - 1, small);
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.FLOOR;
            default: return Tileset.FLOOR;
        }
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(Position p) {
            x = p.x;
            y = p.y;
        }
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Position p = new Position(3,3);
        TETile t = Tileset.FLOWER;

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        drawRandomBigHexagon(world, p, 3, 4);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}

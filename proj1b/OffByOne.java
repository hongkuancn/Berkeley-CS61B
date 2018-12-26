public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        if (x -y == 1 || y - x == 1 || Character.toLowerCase(x) - Character.toLowerCase(y) == 1 || Character.toLowerCase(y) - Character.toLowerCase(x) == 1) {
            return true;
        }
        return false;
    }


}

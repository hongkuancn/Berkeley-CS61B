public class NBody {

  /**
   * Return radius of universe
   * @param s
   * @return
   */
  public static double readRadius(String s){
    In in = new In(s);
    int number = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  /**
   * Return all the planets
   * @param s
   * @return
   */
  public static Planet[] readPlanets(String s){
    In in = new In(s);
    int number = in.readInt();
    double radius = in.readDouble();
    Planet[] all = new Planet[number];
    int i = 0;
    while(i < number){
      double xP = in.readDouble();
      double yP = in.readDouble();
      double xV = in.readDouble();
      double yV = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();
      all[i] = new Planet(xP, yP, xV, yV, m, img);
      i = i + 1;
    };
    return all;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);

    StdDraw.setScale(-radius, radius);
    StdAudio.play("audio/2001.mid");
    StdDraw.enableDoubleBuffering();

    double time = 0;
    while(time < T){
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];

      int i = 0;
      for(i = 0; i < planets.length; i += 1){
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
        planets[i].update(dt, xForces[i], yForces[i]);
      }
      for(i = 0; i < planets.length; i += 1){
        planets[i].update(dt, xForces[i], yForces[i]);
      }

      /* Draw background */
      StdDraw.clear();
      StdDraw.picture(0, 0, "images/starfield.jpg");

      /* Draw all planets */
      for(Planet p: planets){
        p.draw();
      }

      StdDraw.show();
      StdDraw.pause(10);
      time += 10;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}

public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public static final double gConstant = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /**
   * Calculate the distance between this planet and another planet
   * @param p
   * @return
   */
  public double calcDistance(Planet p){
    double distance = Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    return distance;
  }

  /**
   * Calculate the force exerted by another planet
   * @param p
   * @return
   */
  public double calcForceExertedBy(Planet p){
    double force = gConstant * mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    return force;
  }

  /**
   * Calculate the force on x axis exerted by another planet
   * @param p
   * @return
   */
  public double calcForceExertedByX(Planet p){
    double xforce = this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
    return xforce;
  }

  /**
   * Calculate the force on y axis exerted by another planet
   * @param p
   * @return
   */
  public double calcForceExertedByY(Planet p){
    double yforce = this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
    return yforce;
  }

  /**
   * Calculate the net force on x axis exerted by all other planets
   * @param all
   * @return
   */
  public double calcNetForceExertedByX(Planet[] all){
    double xNetForce = 0;
    for (Planet p: all){
      if (this.equals(p)){
        continue;
      } else {
        xNetForce = xNetForce + this.calcForceExertedByX(p);
      }
    }
    return xNetForce;
  }

  /**
   * Calculate the net force on y axis exerted by all other planets
   * @param all
   * @return
   */
  public double calcNetForceExertedByY(Planet[] all){
    double yNetForce = 0;
    for (Planet p: all){
      if (this.equals(p)){
        continue;
      } else {
        yNetForce = yNetForce + this.calcForceExertedByY(p);
      }
    }
    return yNetForce;
  }

  /**
   * Compare if this planet is the same as another planet
   * @param p
   * @return
   */
  public boolean equals(Planet p){
    return (mass == p.mass);
  }

  /**
   * Update location of one planet
   * @param dt
   * @param fX
   * @param fY
   */
  public void update(double dt, double fX, double fY){
    double xaccelaration = fX / mass;
    double yaccelaration = fY / mass;
    xxVel = xxVel + dt * xaccelaration;
    yyVel = yyVel + dt * yaccelaration;
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt * yyVel;
  }

  /**
   * Draw planet
   */
  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }

}

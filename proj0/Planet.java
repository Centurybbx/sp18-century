public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** calculate the distance between the two planets */
	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double distance = Math.hypot(dx,dy);
		return distance;
	}

	/** calculate the force exerted by object p */
	public double calcForceExertedBy(Planet p) {
		double force = G * this.mass * p.mass / 
		(calcDistance(p) * calcDistance(p));
		return force;
	}

	/** calculate the force exerted by object p on the X-axis */
	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		return calcForceExertedBy(p) * (dx / calcDistance(p));
	}

	/** calculate the force exerted by object p on the Y-axis */
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		return calcForceExertedBy(p) * (dy / calcDistance(p));
	}

	/** calculate the net force exerted by array p on the X-axis */
	public double calcNetForceExertedByX(Planet[] ps) {
		double FxNet = 0;
		for (Planet p : ps) {
			if (!this.equals(p)) {
				FxNet += calcForceExertedByX(p);
			}
		}
		return FxNet;
	}

	/** calculate the net force exerted by array p on the Y-axis */
	public double calcNetForceExertedByY(Planet[] ps) {
		double FyNet = 0;
		for (Planet p : ps) {
			if (!this.equals(p)) {
				FyNet += calcForceExertedByY(p);
			}
		}
		return FyNet;
	}

	/** draw itself */
	public void draw() {
		StdDraw.picture(xxPos,yyPos, "./images/" + imgFileName);
	}

	/** update the attribute to the new one */
	public void update(double dt, double fX, double fY) {
		double aX = fX / mass, aY = fY / mass;
		double xxNewVel = xxVel + aX * dt, yyNewVel = yyVel + aY * dt;
		double xxNewPos = xxPos + xxNewVel * dt,
		yyNewPos = yyPos + yyNewVel * dt;
		xxVel = xxNewVel;
		yyVel = yyNewVel;
		xxPos = xxNewPos;
		yyPos = yyNewPos;
	}

}
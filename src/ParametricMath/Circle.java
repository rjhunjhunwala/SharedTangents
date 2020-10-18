package ParametricMath;

public class Circle extends Curve {
    double x_center, y_center, radius;
    public Circle(double x_center, double y_center, double radius){
        this.start = 0;
        this.end = 2 * Math.PI;
        this.x_center = x_center;
        this.y_center = y_center;
        this.radius = radius;
    }
    public double x(double t){
        return radius * Math.cos(t) + x_center;
    }
    public double xp(double t){
        return -radius * Math.sin(t);
    }
    public double xpp(double t){
        return -radius * Math.cos(t);
    }
    public double y(double t){
        return radius * Math.sin(t) + y_center;
    }
    public double yp(double t){
        return radius * Math.cos(t);
    }
    public double ypp(double t){
        return -radius * Math.sin(t);
    }
}

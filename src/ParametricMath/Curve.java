package ParametricMath;

public abstract class Curve {
    double start, end;
    public abstract double x(double t);
    public abstract double xp(double t);
    public abstract double xpp(double t);
    public abstract double y(double t);
    public abstract double yp(double t);
    public abstract double ypp(double t);
}

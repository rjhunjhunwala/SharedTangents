package ParametricMath;

public class Tangent {
    double[] times;
    double[] pointB;
    double[] pointC;
    public Tangent(double[] times, Curve b, Curve c){
        this.times = times;
        pointB = new double[]{
                b.x(times[0]),
                b.y(times[0])
        };
        pointC = new double[]{
                c.x(times[1]),
                c.y(times[1])
        };
    }
    public boolean isSame(Tangent other){
        return VectorMath.dist(this.pointB, other.pointB) + VectorMath.dist(this.pointC, this.pointC) < VectorMath.EPSILON;

    }
    public String toString(){
        return java.util.Arrays.deepToString(new double[][]{times, pointB, pointC});
    }
}

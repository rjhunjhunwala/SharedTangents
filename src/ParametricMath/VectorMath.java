package ParametricMath;

import java.util.function.Function;

public class VectorMath {
    public static double[] scale2(double a, double[] vec){
        return new double[]{a * vec[0], a * vec[1]};
    }
    public static double norm(double[] vec){
        return Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1]);
    }
    public static double normsquare(double[] vec){
        return (vec[0] * vec[0] + vec[1] * vec[1]);
    }
    public static double[] add2(double[] a, double[] b){
        return new double[]{a[0] + b[0], a[1] + b[1]};
    }
    public static double[] sub2(double[] a, double[] b){
        return new double[]{a[0] - b[0], a[1] - b[1]};
    }
    public static double dist(double[] a, double[] b){
        return norm(sub2(a, b));
    }
    public static double EPSILON = .01;
    public static final int ITERATIONS = 100;
    public static double[] find_root(Function<double[], Double> objective, Function<double[], double[]> gradient, double[] guess){
        for(int i = 0; i < ITERATIONS; i++){
            double alpha = -1.0;
            double error = objective.apply(guess);
            double[] gradient_local = gradient.apply(guess);
            alpha = (error * alpha) / VectorMath.normsquare(gradient_local);
            double[] step = VectorMath.scale2(alpha, gradient_local);
            guess = VectorMath.add2(guess, step);
        }
        return guess;
    }
}

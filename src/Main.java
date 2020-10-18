import ParametricMath.*;

import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Function<double[], Double> objective = (double[] x) -> {
            return x[0] * x[0] + x[1] * x[1];
        };
        Function<double[], double[]> gradient = (double[] x) -> {
            return VectorMath.scale2(2, x);
        };
        double[] guess = new double[]{10, 10};
        double[] solved = VectorMath.find_root(objective, gradient, guess);
        System.out.println("Sanity check: this should be zero: " + java.util.Arrays.toString(solved));
        List<Tangent> tangents = SharedTangents.getSharedTangents(new Circle(1,0, 1), new Circle(-1,10, 1));
        for(int i = 0; i < tangents.size(); i++){
            System.out.println("Tangent: " + i);
            System.out.println(tangents.get(i));
        }
        for(int i = 0; i < 10000000; i++){
            VectorMath.find_root(objective, gradient, guess);
        }
    }
}

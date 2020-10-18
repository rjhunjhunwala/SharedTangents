package ParametricMath;
import java.util.*;
import java.util.function.Function;

public class SharedTangents {
    public static ArrayList<Tangent> getSharedTangents(Curve B, Curve C){
        ArrayList<Tangent> list = new ArrayList<>();
        Function<double[], Double> error = (double[] t) -> {
          double a = (B.x(t[0]) - C.x(t[1])) * C.yp(t[0]);
          double b = (B.y(t[0]) - C.y(t[1])) * C.xp(t[0]);
          double c = B.xp(t[0]) * C.yp(t[1]);
          double d = B.yp(t[0])* C.xp(t[1]);
          return (a - b) * (a - b) + (c - d) * (c - d);
        };

        Function<double[], double[]> gradient = (double[] t) -> {
            double a = (B.x(t[0]) - C.x(t[1])) * C.yp(t[0]);
            double b = (B.y(t[0]) - C.y(t[1])) * C.xp(t[0]);
            double c = B.xp(t[0]) * C.yp(t[1]);
            double d = B.yp(t[0])* C.xp(t[1]);

            double scalar1 = 2 * (a - b);
            double scalar2 = 2 * (c - d);

            double[] grad1 = new double[]{
                    (B.x(t[0]) - C.x(t[1])) * B.ypp(t[0]) + (C.y(t[1]) - B.y(t[0])) * B.xpp(t[0]),
                    C.xp(t[1]) * B.yp(t[0]) + C.yp(t[1]) * B.xp(t[0])
            };

            double[] grad2 = new double[]{
                    B.xpp(t[0]) * C.yp(t[1]) - B.ypp(t[0]) * C.xp(t[1]),
                    B.xp(t[0]) * C.ypp(t[1]) - B.yp(t[0]) * C.xpp(t[1])
            };

            double[] first = VectorMath.scale2(scalar1, grad1);
            double[] second = VectorMath.scale2(scalar2, grad2);
            return VectorMath.add2(first, second);
        };


        double MAX_SOLNS = 15;
        for(int i = 0; i < MAX_SOLNS; i++){
            double[] guess = new double[]{
                    B.start + (B.end - B.start) * Math.random(),
                    C.start + (C.end - C.start) * Math.random(),
            };
            double [] sol = VectorMath.find_root(error, gradient, guess);
            Tangent newTangent = new Tangent(sol, B, C);
            boolean alreadyFound = false;
            for(Tangent past: list){
                if(past.isSame(newTangent)){
                    alreadyFound = true;
                    break;
                }
            }
            if(!alreadyFound){
                list.add(newTangent);
            }
        }
        return list;
    }
}

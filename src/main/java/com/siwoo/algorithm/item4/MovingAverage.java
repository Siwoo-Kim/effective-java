package com.siwoo.algorithm.item4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sm123tt@gmail.com on 2019-08-14
 * Project: effective-java
 * Github : http://github.com/Siwoo-Kim
 */

public class MovingAverage {

//    /**
//     *
//     * @param weights
//     * @param m 관찰 값의 갯수.
//     * @return
//     */
//    static List<Double> movingAverage(List<Double> weights, int m) { //N^2
//        List<Double> r = new ArrayList<>();
//        for (int i=m-1; i<weights.size(); i++) {
//            //weights[i] 까지의 관찰 갯수의 평균값.
//            double sum = 0;
//            for (int j=0; j<m; j++)
//                sum += weights.get(i-j);
//            r.add(sum / m);
//        }
//        return r;
//    }

    static List<Double> movingAverage(List<Double> weights, int m) { //N
        List<Double> r = new ArrayList<>();
        double sum = 0;
        for (int i=0; i<m-1; i++)
            sum += weights.get(i);
        for (int i=m-1, n=weights.size(); i<n; i++) {
            sum += weights.get(i);
            r.add(sum / m);
            sum -= weights.get(i-m+1);
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(movingAverage(Arrays.asList(60.3, 55.5, 60.2, 57.3, 58.4), 2));
    }
}

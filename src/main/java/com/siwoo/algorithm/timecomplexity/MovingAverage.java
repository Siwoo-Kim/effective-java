package com.siwoo.algorithm.timecomplexity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N개의 측정치가 주어질 때, 매달 M달 간의 이동 평균을 구하라.
 *
 * 알고리즘 시간 복잡도 분석
 * {@link MovingAverage#movingAverage()} 은 M*(N-M+1) = NM-M^2+M 이다.
 * {@link MovingAverage#movingAverage2()} 은 중복 계산을 없애으므로 M-1+(N-M+1) 이므로 O(N) 이다.0
 *
 */
public class MovingAverage {

    private final double[] A;
    private final int N;
    private final int MOVING_UNIT;

    public MovingAverage(double[] a, int MOVING_UNIT) {
        A = a;
        N = A.length;
        this.MOVING_UNIT = MOVING_UNIT;
    }

    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(new double[]{10.0, 15.0, 13.0, 20.0}, 2);
        System.out.println(Arrays.toString(m.movingAverage()));
        System.out.println(Arrays.toString(m.movingAverage2()));
    }

    /**
     * 실수 배열 A가 주어질 때, 각 위치에서의 M 간격의 이동 평균값을 반환
     * @return
     */
    public double[] movingAverage() {
        final List<Double> r = new ArrayList<>();
        for (int i=MOVING_UNIT-1; i<N; i++) {
            //A[i-M] 부터 A[i] 까지의이동 평균 값을 구한다.
            double sum = 0;
            for (int j=0; j<MOVING_UNIT; j++)
                sum += A[i-j];
            r.add(sum / MOVING_UNIT);
        }
        return r.stream().mapToDouble(e -> e).toArray();
    }

    public double[] movingAverage2() {
        final List<Double> r = new ArrayList<>();
        double partialSum = 0;
        for (int i=0; i<MOVING_UNIT-1; i++)
            partialSum += A[i];
        for (int i=MOVING_UNIT-1; i<N; i++) {
            partialSum += A[i];
            r.add(partialSum / MOVING_UNIT);
            partialSum -= A[i-MOVING_UNIT+1];
        }
        return r.stream().mapToDouble(e -> e).toArray();
    }
}

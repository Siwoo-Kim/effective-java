package com.siwoo.algorithm.timecomplexity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 소인수분해 (N 소인수로 분해)
 */

public class Factor {

    private final int N;

    public Factor(int n) {
        N = n;
    }

    public static void main(String[] args) {
        Factor factor = new Factor(60);
        System.out.println(factor.factor());
    }

    List<Integer> factor() {
        List<Integer> r = new ArrayList<>();
        int n = N;
        if (N==1) return Arrays.asList(1);
        for (int div=2; n>1; div++) {
            while (n % div == 0) {
                n /= div;
                r.add(div);
            }
        }
        return r;
    }
}

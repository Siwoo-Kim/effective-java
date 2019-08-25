package com.siwoo.algorithm.dynamic;

/**
 * 정사각형들의 변들을 서로 완전하게 붙여 만든 도형 폴리오미노라 한다.
 * 이때 n개의 정사각형으로 구성된 폴리오미노를 만드려 하는데,
 * 이 중 세로로 단조(monotone) 인 폴리오미노의 수는?
 */
public class Poly {

    private final int N;

    public Poly(int n) {
        N = n;
    }

    public int poly() {
        int r = 0;
        for (int first=1; first<=N; first++)
            r += poly(N, first);
        return r;
    }

    /**
     * n 개의 정사각형으로 이루어졌고, 맨 위 가로줄에 first 개의
     * 정사각형을 포함하는 폴리오미노의 수를 반환한다.
     */
    private int poly(int n, int first) {
        if (n == first) return 1;
        int r = 0;
        for (int second = 1; second <= n - first; ++second) {
            int add = second + first - 1;
            add *= poly(n - first, second);
            r += add;
        }
        return r;
    }
}

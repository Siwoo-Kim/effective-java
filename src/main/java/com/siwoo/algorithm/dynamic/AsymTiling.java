package com.siwoo.algorithm.dynamic;

/**
 * 2 x n 크기의 직사각형을 2 x 1 크기의 타일로 채우려 할 때,
 * 타일은 서로 겹쳐서는 안 되고, 90도로 회전해서 쓸 수 있다.
 * 이때 타일링 방법은 좌우 대칭이어서는 안 될때, 비대칭 타일링 방법의 수를 계산하라.
 *
 */
public class AsymTiling {

    private final int N; // 직 사각형의 WIDTH

    public AsymTiling(int n) {
        N = n;
    }

    int asymmetric() {
        return asymmetric(N);
    }

    private int asymmetric(int width) {
        if (width % 2 == 1)
            return tiling(width) - tiling(width/2);
        int r = tiling(width);
        r = r - tiling(width/2);
        r = r - tiling(width/2-1);
        return r;
    }

    int tiling(int width) {
        if (width <= 1) return 1;
        return tiling(width - 1) + tiling(width - 2);
    }

    public static void main(String[] args) {
        AsymTiling asymTiling = new AsymTiling(4);
        System.out.println(asymTiling.asymmetric());
    }
}

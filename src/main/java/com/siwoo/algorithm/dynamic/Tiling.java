package com.siwoo.algorithm.dynamic;

import java.util.Objects;

/**
 * 타일링 방법의 수 세기.
 * 2 x n 크기의 사각형을 2 x 1 크기의 타일로 채우는 방법의 수를 계산하라.
 * 이때 채우는 타일 2 x 1 은 90도로 회전해서 쓸 수 있다.
 */
public class Tiling {

    private final int width; // 2 x width
    private Integer[] cached = new Integer[101];

    public Tiling(int width) {
        this.width = width;
    }

    public static void main(String[] args) {
        Tiling tiling = new Tiling(5);
        System.out.println(tiling.tiling());
    }

    public int tiling() {
        return tiling(width);
    }
    /**
     * 2 * width 크기의 사각형을 채우는 방법의 수를 반환한다.
     *
     * @param width
     * @return
     */
    private int tiling(int width) {
        if (width <= 1) return 1;
        Integer r = cached[width];
        if (Objects.nonNull(r))
            return r;
        return tiling(width - 1) + tiling(width - 2); //세로줄로 타일링 + 가로줄 타일링
    }
}

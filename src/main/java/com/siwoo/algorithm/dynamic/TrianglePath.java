package com.siwoo.algorithm.dynamic;

/**
 * 삼각형 위의 최대 경로.
 * 삼각형으로 배치된 자연수들이 있을 때, 맨 위의 숫자에서 시작해,
 * 한 번에 한 칸씩 아래로 내려가 맨 아래 줄까지 닿는 경로를 만드려 한다.
 * 이때 모든 경로 중 경로에 포함된 숫자들의 최대 합과 최대 경로는?
 */

public class TrianglePath {

    private final int[][] triangle;
    private final int HEIGHT;

    public TrianglePath(int[][] triangle) {
        this.triangle = triangle;
        this.HEIGHT = triangle.length;
    }

    /**
     * (y, x) 위치부터 맨 아래줄까지 내려가면서 얻을 수 있는
     * 최대 경로의 합을 반환한다.
     * @param y
     * @param x
     * @return
     */
    private int path(int y, int x) {
        //BASE CASE
        if (y == HEIGHT-1)  return triangle[y][x];
        return Math.max(path(y + 1, x), path(y + 1, x + 1)) + triangle[y][x];
    }

    /**
     * (y, x)에서 시작해 맨 아래줄까지 내려가는 경로 중 최대 경로의 수를 반환.
     */
    private int count(int y, int x) {
        if (y == HEIGHT - 1) return 1;
        int r = 0;
        if (path(y + 1, x + 1) >= path(y + 1, x)) r += count(y + 1, x + 1);
        if (path(y + 1, x + 1) <= path(y + 1, x)) r += count(y + 1, x);
        return r;
    }

    public int count() {
        return count(0, 0);
    }
    public static void main(String[] args) {
        int[][] triangle = {
                {9},
                {5, 7},
                {1, 3, 2},
                {3, 5, 5, 6}};

        TrianglePath trianglePath = new TrianglePath(triangle);
        System.out.println(trianglePath.count());
    }
}

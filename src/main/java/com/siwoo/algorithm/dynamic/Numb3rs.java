package com.siwoo.algorithm.dynamic;

import java.util.LinkedList;

/**
 * 두니발 박사는 감옥에서 탈출했는데,
 * 두니발 박사는 인접한 마을 하나로 도망쳐 은신한다.
 * 그는 매일 인접한 마을로 움직여 은신한다고 가정했을 때,
 * d 일 후에 주어진 연결된 n 개 마을의 각 두니발 박사가 은신할 확률을 계산하라.
 *
 */
public class Numb3rs {

    private final int N; //마을 갯수
    private final int D; //날짜 경과
    private final int Q; //목적지 마을
    private final int[] DEGREE; // 각 마을의 차수 (각 마을에 연결된 다른 마을의 수)

    private int[][] connected = new int[][]{
            {1, 2, 3},
            {0, 3},
            {0, 3},
            {0, 1, 2, 4, 5},
            {3, 6, 7},
            {3, 7},
            {4},
            {4, 5}
    };

    public Numb3rs(int n, int d, int q, int[] degree, int[][] connected) {
        N = n;
        D = d;
        Q = q;
        DEGREE = degree;
        if (connected != null)
            this.connected = connected;
    }

    double search(LinkedList<Integer> path) {
        if (path.size() == D+1) {
            if (path.peekLast() != Q) return 0.0;
            double r = 1.0;
            for (int i=0; i+1<path.size(); i++)
                r /= DEGREE[path.get(i)];
            return r;
        }
        double r = 0;
        for (int there=0; there<N; there++) {
            if (connected(path.peekLast(), there)) {
                path.push(there);
                r += search(path);
                path.pop();
            }
        }
        return r;
    }

    private boolean connected(int src, int des) {
        for (int i=0; i<connected[src].length; i++) {
            if (connected[src][i] == des)
                return true;
        }
        return false;
    }
}

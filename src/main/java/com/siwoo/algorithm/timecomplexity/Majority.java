package com.siwoo.algorithm.timecomplexity;


import scala.Int;

/**
 * req: 주어진 배열 A에서 가장 많이 등장하는 숫자를 반환한다.
 *
 * 시간 복잡도는 반복문이 지배한다.
 *  :대개는 입력의 크기에 따라 반복문의 수행 횟수가 결정되기도 한다.
 *  :Majority 은 입력 크기 {@code int[] A} 에 따라 반복문의 수행 횟수가 정해진다.
 *  {@link Majority#majority()} 은 반복문의 두 번 겹쳐지므로 시간 복잡도는 O(N^2) 이다.
 *  {@link Majority#majority(int)} 은 N+max 만큼 반복하므로, O(N) 이다.
 */
public class Majority {

    private final int[] A;

    public Majority(int[] a) {
        A = a;
    }

    public static void main(String[] args) {
        Majority m = new Majority(new int[]{1, 2, 2, 3, 4, 4, 4, 8});
        System.out.println(m.majority());
        System.out.println(m.majority(10));
    }

    /**
     * A의 각 원소가 0 부터 max 사이의 값인 경우 가장 많이 등장하는 숫자를 반환.
     * @param max
     * @return
     */
    public int majority(int max) {
        int[] count = new int[max];
        final int N = A.length;
        for (int i=0; i<N; i++) {
            final int V = A[i];
            if (V < 0) throw new IllegalArgumentException();
            count[A[i]]++;
        }
        // 지금까지 확인한 숫자 중 빈도수가 제일 큰 것을 majority 에 저장.
        int majority = 0;
        for (int i=0,CN=count.length; i<CN; i++)
            if (count[i] > count[majority])
                majority = i;
        return majority;
    }

    /**
     * 주어진 배열 A에서 가장 많이 등장하는 숫자를 반환한다.
     * @return
     */
    public int majority() {
        int majority = Integer.MIN_VALUE, majorityCount = 0, N = A.length;
        for (int i=0; i<N; i++) {
            int V = A[i], count = 1;
            for (int j=1; j<N; j++)
                if (A[j] == V)
                    count++;
            //지금까지 본 최대 빈도보다 많이 출현했다면 갱신한다.
            if (count > majorityCount) {
                majority = V;
                majorityCount = count;
            }
        }
        return majority;
    }
}

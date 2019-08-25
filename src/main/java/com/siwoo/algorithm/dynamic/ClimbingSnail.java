package com.siwoo.algorithm.dynamic;

/**
 * 깊이가 n 미터인 우물에 달팽이가 우 물의 맨 위까지 기어오르려 한다.
 * 달팽이의 움직임은 날이 맑으면 2미터, 비가 내리면 1미터밖에 오르지 못한다고 할 때,
 * 앞으로 m 일 간 각 날짜에 비가 나올 확률이 50% 라 가정할 때,
 * m 일 안에 달팽이가 우물 끝까지 올라갈 확률은?
 *
 * 경우의 수 계산하기.
 *  1. 모든 답을 직접 만들어서 세어보는 완전 탐색 알고리즘을 설계한다.
 *      이때 경우의 수를 제대로 세기 위해서는 재귀 호출의 각 단계에서 고르는 각 선택지에 다음 속성이 성립되어야 한다.
 *      1) 모든 경우는 이 선택지들에 포함됨.
 *      2) 어떤 경우도 두 개 이상의 선택지에 포함되지 않음.
 *  2. 결정한 요소들에 대한 입력을 없애거나 변형해서 줄인다.
 *  3. 메모이제이션을 사용한다.
 */
public class ClimbingSnail {

    public final int N; //우물의 높이.
    public final int M; //우물을 오를 수 있는 기간

    public ClimbingSnail(int wellHeight, int days) {
        N = wellHeight;
        M = days;
    }

    /**
     *
     * @param m //결과 일 수
     * @param climbed //지금 까지의 달팽이가 오른 높이
     * @return
     */
    public int climb(int m, int climbed) {
        if (M == m) return N <= climbed ? 1: 0;
        return climb(m+1, climbed + 1) + climb(m+1, climbed+2);
    }

}

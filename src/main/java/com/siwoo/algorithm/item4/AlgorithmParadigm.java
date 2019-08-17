package com.siwoo.algorithm.item4;

import com.siwoo.effectivejava.item7.Stack;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 패러다임이란 '어떤 시기에 그 시대의 사상과 믿음을 지배하는 이론의 틀 즉 인식 쳬계'이다.
 * 알고리즘 설계 패러다임이란 주어진 문제를 해결하기 위해 알고리즘이 채택한 전략이나 관점을 말한다.
 */
public class AlgorithmParadigm {

    /**
     * 무식하게 풀기(brute-force).
     * 가능한 방법을 전부 만들어 보는 아록리즘을 가리켜 완전 탐색(exhaustive search) 라 한다.
     * 완전 탐색은 컴퓨터의 장점을 가장 잘 이용하는 방법이다. (컴퓨터는 인간보다 빠르므로)
     *
     * 재귀 호출.
     *  :자신이 수행할 작업을 유사한 형태의 여러 조각으로 쪼갠 뒤 그 중 한 조각을 수행하고,
     *  나머지를 자기 자신을 호출해 실행하는 함수.
     *
     * 컴퓨터가 수행하는 많은 작업들은 대개 작은 조각들로 나눌 수 있으며
     * 이 조각들의 형태가 유사해지는 형태가 많다.
     *
     * 이런 작업을 구현할 때 유용하게 사용되는 개념이 바로 재귀 함수(recursive function) 이다.
     *
     * 재귀 호출의 기저 사례
     *  : 재귀 함수에서 쪼개지지 않는 가장 작은 작업들을 가리켜 재귀 호출의 '기저 사례(base case)' 라 한다.
     */
    @Test
    public void givenIntWhenSumThenGetSum() {
        System.out.println(sum(10));
        System.out.println(recursiveSum(10));
    }

    /**
     * returns sum from 1 to el
     *
     * in-variant: {@code el >= 1}
     * @param el
     * @return
     */
    public int sum(int el) {
        int r = 0;
        for (int i=0; i<=el; i++)
            r += i;
        return r;
    }

    /**
     * returns sum from 1 to el
     *
     * in-variant: {@code el >= 1}
     * @param el
     * @return
     */
    public int recursiveSum(int el) {
        if (el == 1) return el; //base case
        return el + recursiveSum(el-1);
    }

    @Test
    public void givenIntWhenPickAndPrintGetAllSelectedElements() {
        //pickAndPrint(7);
        pick(7, new LinkedList<>(), 4);
    }

    public void pickAndPrint(int el) {
        final int N = el;
        for (int i=0; i<N; i++)
            for(int j=i+1; j<N; j++)
                for(int k=j+1; k<N; k++)
                    for(int l=k+1; l<N; l++)
                        System.out.println(String.format("(%d, %d, %d, %d)", i, j, k, l));
    }

    /**
     *
     * @param el number of the elements
     * @param picked current picked elements
     * @param toPick remains the number of elements
     */
    public void pick(int el, Deque<Integer> picked, int toPick) {
        //base zse: 더 고를 원소가 없을 때 고른 원소를 출력.
        if (toPick == 0) { printPicked(picked); return; }
        final int N = el;
        //고를 수 있는 가장 작은 원소를 계산한다.
        int smallest = picked.isEmpty() ? 0: picked.peekFirst() + 1;
        //원소를 하나 고른다.
        for (int next=smallest; next<N; next++) {
            picked.add(next);
            pick(el, picked, toPick - 1);
            picked.pop();
        }
    }

    private void printPicked(Deque<Integer> picked) {
        StringBuilder sb = new StringBuilder().append("(");
        for (Integer el: picked) {
            sb.append(el).append(", ");
        }
        sb.delete(sb.length()-2, sb.length()).append(")");
        System.out.println(sb);
    }

    /**
     * Boggle Game.
     *
     * 5x5 크기의 알파벳 격자에서 상하좌우/대각선으로 인접한 칸들의 글자를 이어 단어를 찾아낸다.
     * hasWord(y, x, word) = 보글 게임판의 (x,y) 에서 시작하는 단어 word 의 존재 여부를 반환하라.
     *
     * 재귀함수에선 in-variant 을 기저 사태로 택하여 맨 처음에 처리하라.
     *
     * base case:
     * 1.위치 (x,y) 에 있는 글자가 원하는 단어의 첫 글자가 아닌 경우 바로 false 리턴.
     * 2.1의 경우가 아닌 경우 원하는 단어가 한 글자인 경우 항상 성공.
     */

    @Test
    public void givenBoggleBoardsWhenHasWordGetResult() {
        System.out.println(hasWord(1, 1, "PRETTY"));
    }

    /**
     * returns if the given word with in x,y exists in the boggle board.
     *
     * 후보 여덞칸과 단어의 길이 만큼 반복하므로 아록리즘은 O(8^N)
     * @param x
     * @param y
     * @param word
     * @return
     */
    private boolean hasWord(int x, int y, String word) {
        //base case1: 시작 위치가 범위 밖이면 false
        if (!inRange(x, y)) return false;
        //base case2: 첫 char 가 일치하지 않으면 실패.
        if (words[x][y] != word.charAt(0)) return false;
        //base case3: 단어 길이가 1이면 성공
        if (word.length() == 1) return true;
        //인접한 여덞칸을 검사한다.
        for (int d=0; d<8; d++) {
            int nextX = dx[d] + x, nextY = dy[d] + y;
            if (hasWord(nextX, nextY, word.substring(1)))
                return true;
        }
        return false;
    }

    char[][] words = {
            {'U', 'R', 'L', 'P', 'M'},
            {'X', 'P', 'R', 'E', 'T'},
            {'G', 'I', 'A', 'E', 'T'},
            {'X', 'T', 'N', 'Z', 'Y'},
            {'X', 'O', 'Q', 'R', 'S'}
    };

    final int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
    final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};


    private boolean inRange(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    /**
     * Picnic
     * 주어진 학생 명단에서 '친구인 학생들끼리만' 두 명씩 짝을 지어줄 때,
     * 학생들을 짝 지을 수 있는 경우의 수를 계산하라.
     *
     */

    private final int N = 3;
    boolean[][] areFriends = {
            {true, true, true},
            {true, true, true},
            {true, true, true}
    };

    @Test
    public void test() {
        System.out.println(countParings(new boolean[3]));
    }
    private int countParings(boolean[] taken) {
        boolean finished = true;
        for (int i=0; i<N; i++)
            if (!taken[i])
                finished = false;
        if (finished) return 1;
        int r = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                if (!taken[i] && !taken[j] && areFriends[i][j]) {
                    taken[i] = taken[j] = true;
                    r += countParings(taken);
                    taken[i] = taken[j] = false;
                }
            }
        return r;
    }
}


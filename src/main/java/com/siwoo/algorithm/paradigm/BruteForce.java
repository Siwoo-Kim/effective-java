package com.siwoo.algorithm.paradigm;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Stack;
import java.util.function.Predicate;

/**
 * Brute-Force (Exhaustive search)
 *  :컴퓨터의 빠른 계산 능력을 이용해 가능한 경우의 수를 일일히 나열하면서 답을 찾는 방법.
 *
 *  완전 탐색 레시피.
 *      :어떤 문제를 완전 탐색으로 해결하기 위한 필요 과정.
 *          1. 완전 탐색은 존재하는 모든 답을 하나씩 검사한다.
 *          2. 가능한 모든 답의 후보를 만드는 과정을 '여러 개의 선택'으로 나눈다.
 *          3. 그중 하나의 조각을 선택해 답의 일부를 만들고, 나머지 답은 '재귀 호출' 을 통해 완성한다.
 *          4. 조각이 하나밖에 남지 않은 경우(혹은 쪼개질 수 없는 경우), 이것을 기저 사례로 선택해 처리.
 *
 *  재귀 호출 (recursive function) 과 완전 탐색.
 *      :수행하는 작업의 범위가 작아질수록 형태가 유사해지는 작업의 특성을 이용해
 *      수행할 작업을 유사한 형태의 여러 조각으로 쪼갠 뒤 그 중 한 조각을 수행하고
 *      나머지를 자기 자신을 호출해 실행하는 함수.
 *
 *      1. 문제를 분할하라.
 *      2. 기저 사례를 선택하라.
 *
 *
 *
 */
public class BruteForce {

    @Test
    public void givenNumWhenSumExpectSum() {
        Sum sum = new SumItr();
        System.out.println(sum.sum(10));
        sum = new SumRecur();
        System.out.println(sum.sum(10));
    }

    interface Sum {
        /**
         * in-variant: {@code n >= 1}
         * @param n
         * @return sum from 0 to n
         */
        int sum(int n);
    }

    static class SumItr implements Sum, com.siwoo.algorithm.paradigm.SumIter {
        @Override
        public int sum(int n) {
            int r = 0;
            for (int i=0; i<=n; i++) {
                r += i;
            }
            return r;
        }
    }

    static class SumRecur implements Sum {
        @Override
        public int sum(int n) {
            //n 개의 숫자의 합을 구하는 작업을 n 개로 쪼갰을 때
            //n 이 1 일때는 더이상 작업을 쪼갤 수 없으므로,
            //이는 '더이상 쪼개지지 않는' 최소한의 작업을 뜻하며
            //이를 base case 라 하다.
            if (n == 1) return 1;
            return n + sum(n - 1);  //n개의 숫자의 합을 구하는 작업을 n개의 작업 조각으로 쪼갠다.
        }
    }


    @Test
    public void givenNumWhenPrintSectionExpectPrint() {
        PrintSection ps = new PrintSectionItr();
        ps.print(7);
        ps = new PrintSectionRecur();
        System.out.println("================================");
        ps.print(7);
    }


    interface PrintSection {
        /**
         * 0부터 차례대로 번호 매겨진 N 개의 원소 중 네 개를 고르는 모든 경우를 출력.
         * in-variant: {@code el >= 0 && el < N}
         * @param N
         */
        void print(int N);

        default void printSection(int... args) {
            StringBuilder sb = new StringBuilder()
                    .append("(");
            for (int arg: args) {
                sb.append(arg+",");
            }
            sb.replace(sb.length()-1, sb.length(), ")");
            System.out.println(sb);
        }

    }

    static class PrintSectionItr implements PrintSection {
        @Override
        public void print(int N) {
            for (int i=0; i<N; i++)
                for (int j=i+1; j<N; j++)
                    for (int k=j+1; k<N; k++)
                        for (int l=k+1; l<N; l++)
                            printSection(i, j, k, l);
        }
    }

    static class PrintSectionRecur implements PrintSection {
        @Override
        public void print(int N) {
            _print(N, new Stack<>(), 4);
        }

        /**
         * n 개의 Section 중 m(4) 개를 고르는 모든 조합의 경우를 찾는 알고리즘.
         *
         * @param N 전체 원소의 수
         * @param section 지금까지 고른 원소들.
         * @param toPick 앞으로 더 고를 원소의 수
         */
        private void _print(int N, Stack<Integer> section, int toPick) {
            //base case: 더 고를 원소가 없을 때 고른 원소를 출력한다.
            if (toPick == 0) {
                printSection(Ints.toArray(section));
                return;
            }
            // 고를 수 있는 가장 작은 번호(현재 번호)를 계산한다.
            int c = section.isEmpty() ? 0: section.peek() + 1;
            // 다음 번호를 고른다.
            for (int next=c; next<N; next++) {
                section.push(next);
                _print(N, section, toPick - 1);
                section.pop();
            }
        }
    }

    private static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point add(int x, int y) {
            return new Point(this.x + x, this.y + y);
        }

        boolean testX(Predicate<Integer> p) {
            return p.test(x);
        }

        boolean testY(Predicate<Integer> p) {
            return p.test(y);
        }
    }

    char[][] board = {
            {'U', 'R', 'L', 'P', 'M'},
            {'X', 'P', 'R', 'E', 'T'},
            {'G', 'I', 'A', 'E', 'T'},
            {'X', 'T', 'N', 'Z', 'Y'},
            {'X', 'O', 'Q', 'R', 'S'},
    };

    @Test
    public void givenBoardWhenHasWordExpectBoolean() {
        BoggleGame game = new BoggleGame(board);
        System.out.println(game.hasWord(new Point(3, 0), "PRETTY"));
    }

    static final class BoggleGame {
        private final char[][] board;

        Point[] DIRECTION = {
                new Point(-1, -1), new Point(-1, -0),
                new Point(-1, 1), new Point(1, -1),
                new Point(1, 0), new Point(1, 1),
                new Point(0, -1), new Point(0, 1)};

        public BoggleGame(char[][] board) {
            this.board = board;
        }

        private boolean inRange(Point p) {
            return p.testX(x -> x>=0 && x<board[0].length)
                    && p.testY(y -> y>=0 && y<board.length);
        }

        /**
         * 해당 위치에서 주어진 단어의 첫 문자가 주어진 좌표에서 시작하는지를 반환.
         * 시간 복잡도는 단어의 길이 N 에 대해 O(8^N)
         * @param p 주어진 좌표
         * @param word 검색 문자
         * @return
         */
        public boolean hasWord(Point p, String word) {
            //base case1. 시작 위치가 보드 범위 밖이면 실패
            if (!inRange(p)) return false;
            //base case2. 첫 글자가 일치하지 않으면 실패
            if (board[p.y][p.x] != word.charAt(0)) return false;
            //base case3. 단어 길이가 1이면 성공
            if (word.length() == 1) return true;
            // 인접한 여덞칸을 검사한다.
            for (Point d: DIRECTION) {
                Point next = p.add(d.x, d.y);
                //다음의 첫 문자가 주어진 좌표에 시작하는지 검사.
                if (hasWord(next, word.substring(1)))
                    return true;
            }
            return false;
        }
    }

    static class Pairing {
        private boolean[][] friendships;
        private int STUDENT_NUM;

        /**
         * 모든 학생을 '친구인 두 명'씩 짝지을 수 있는 모든 경우의 수를 리턴.
         * 재기 호출과 중복.
         *  짝을 지을 시 (0,1) 후에 (2,3)을 짝짓는 것은 (2,3) 이후에 (0.1) 을 짝짓는 것과 같지만
         *  아래 코드는 중복을 허용한다.
         *  재귀 호출에서는 중복으로 세는 이런 경우가 매우 흔하다.
         *  이 속성을 강제하기 위해선 각 단계에서 남아 있는 학생들 중 '가장 번호가 빠른' 학생의 짝을 찾아주도록 한다.
         *      (특정 순서를 이용하여 재귀에서 중복을 허용치 않게 하라.)
         *  이렇게 되면 (1,0) 은 허용치 않으며, 항상 빠른 학생부터 번호가 찍기에 (2,3), (0,1) 의 순서로는 짝이 지어지지 않는다.
         *
         * @param taken
         * @return
         */
//        int countPairings(boolean[] taken) {
//            boolean f = false;
//            for (int i=0; i<STUDENT_NUM; i++)
//                f = taken[i] | f;
//            if (f) return 1;
//            int r = 0;
//            for (int i=0; i<STUDENT_NUM; i++)
//                for (int j=0; j<STUDENT_NUM; j++) {
//                    if (!taken[i] && !taken[j] && friendships[i][j]) {
//                        taken[i] = taken[j] = true;
//                        r += countPairings(taken);
//                        taken[i] = taken[j] = false;
//                    }
//                }
//            return r;
//        }
        int countPairings(boolean[] taken) {
            // 남은 학생들 중 가장 번호가 빠른 학생을 찾는다(중복 방지)
            int firstFree = -1;
            for (int i=0; i<taken.length; i++)
                if (!taken[i]) {
                    firstFree = i;
                    break;
                }
            //base case: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
            if (firstFree == -1) return 1;
            int r = 0;
            for (int pair=firstFree+1; pair<STUDENT_NUM; pair++) {
                if (!taken[pair] && friendships[firstFree][pair]) {
                    taken[pair] = taken[firstFree] = true;
                    r += countPairings(taken);
                    taken[pair] = taken[firstFree] = false;
                }
            }
            return r;
        }
    }
}
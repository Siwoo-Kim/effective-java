package com.siwoo.algorithm.item4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 시간 복잡도(time complexity) 란 가장 널리 사용되는 알고리즘의 수행 시간 기준으로,
 * 알고리즘이 실행되는 동안 수행하는 "기본적인 연산의 수"를 "입력의 크기"에 대한 함수로 표현한 것이다.
 * "기본적인 연산"이란 더 작게 쪼갤 수 없는 최소 크기의 연산을 의미한다.
 *
 * 시간 복잡도가 낮다고 해서 언제나 "더 빠르게" 동작하는 것은 아니지만, 입력이 커지면 커질수록 더 효율적이다.
 * 수행 시간을 고려하기 위해 최선/최악/ 평균적인 경우가 있지만 시간 복잡도의 최악의 수행 시간을 기대치로 둔다.
 *
 * O 표기법.
 * O 표기법(Big-O Notation) 은 아록리즘의 수행 시간을 표기하며, 주어진 함수에서 가장 빨리 증가하는 항만 남긴 채
 * 나머지는 버리는 표기법이다.
 * f(N)= 5/3N^2 - Nlg*N/2 + 16N - 7 이라면 O 표기법은 N^2 이다.
 */
public class TimeComplexity {

    /**
     * N 은 입력의 크기이다.
     * 반복문이 2개 이므로 시간 복잡도는 N^2 이다.
     * @param elements
     * @return
     */
    public static int majority(int[] elements) {
        int majority = 0, count = 0;
        for (int i=0, n=elements.length; i<n; i++) {
            int v = elements[i], c = 0;
            for (int j=0; j<n; j++) {
                if (v == elements[j])
                    c++;
            }
            if (count < c) {
                majority = v;
                count = c;
            }
        }
        return majority;
    }

    /**
     * 해당 시간 복잡도는 N+max 이므로 O(N) 이다.
     * @param elements
     * @param max
     * @return
     */
    public static int majority2(int[] elements, int max) {
        int[] c = new int[max+1];
        for (int i=0, n=elements.length; i<n; i++)
            c[elements[i]]++;

        int majority = 0;
        for (int i=0; i<=max; i++) {
            if (c[majority] < c[i])
                majority = i;
        }

        return majority;
    }

    @Test
    public void givenArrayWhenMajorityThenGetMajorityNumber() {
        int[] array = {1, 2, 3, 4, 3, 3, 4, 5, 4, 2, 2, 2, 1, 2}; //2
        System.out.println(majority(array));
        System.out.println(majority2(array, 10));
        array = new int[]{10, 20, 30, 40, 30, 30, 40, 50, 40, 20, 20, 20, 10, 20}; //2
        System.out.println(majority(array));
        System.out.println(majority2(array, 100));
    }

    /**
     * 주어진 배열에서 사이즈가 m 크기만큼의 평균값의 결과를 배열 넣어 리턴한다.
     * 해당 반복문은 (N-M+1) x M 이므로 NM-M^2+M  알고리즘이다.
     * @param elements
     * @param m
     * @return
     */
    public static double[] movingAverage1(double[] elements, int m) {
        double[] result = new double[elements.length - m + 1];
        for (int i=m-1, k=0, n=elements.length; i<n; i++) {
            double sum = 0;
            for (int j=0; j<m; j++)
                sum += elements[i-j];
            result[k++] = sum / m;
        }
        return result;
    }

    public static double[] movingAverage2(double[] elements, int m) {
        double[] result = new double[elements.length - m + 1];
        double sum = 0;
        for (int i=0; i<m-1; i++)
            sum += elements[i];
        for (int i=m-1, n=elements.length, j=0; i<n; i++) {
            sum += elements[i];
            result[j++] = sum / m;
            sum -= elements[i-m+1];
        }
        return result;
    }

    @Test
    public void givenArrayWhenMovingAverageThenGetAverages() {
        double[] weights = {40.D, 45.D, 46.D, 42.D, 43.D, 55.D, 52.D, 48.D };
        System.out.println(Arrays.toString(movingAverage1(weights, 2)));
        System.out.println(Arrays.toString(movingAverage1(weights, 3)));
        System.out.println(Arrays.toString(movingAverage2(weights, 2)));
        System.out.println(Arrays.toString(movingAverage2(weights, 3)));
        System.out.println((55.D + 52.D + 48.D) / 3);
    }

    /**
     * O(log N) 은 밑이 2인 log2 을 의미한다. 각 반복마다 다음 반복 횟수는 N/2 가 된다.
     *
     * binarySearch(A[], x)
     * 이진 탐색 알고리즘이란 오름차순으로 정렬된 배열 A[] 와 찾고 싶은 값 x 가 주어질 때
     * A[i-1]<x<A[i] 인 i를 반환한다.
     *
     * 다항 시간 알고리즘.
     * 변수 N과 N^2, 그 외 N의 거듭제곱의 선형 결합으로 이루어진 식을 다항식이라 한다.
     *
     * 지수 시간 알고리즘은 O(N^2) 로 표현한다. (exponential time)
     * 지수 시간 알고리즘은 N 이 하나 증가할 때마다 걸리는 시간이 배로 증가한다.
     */

    boolean canEveryBodyEat(Stack<Integer> menu) {
        if (menu.contains(2) && menu.contains(4))
            return true;
        else
            return false;
    }

    /**
     * 한 경로를 택할때마다 canEveryBodyEat 을 수행하니
     * canEveryBodyEat 을 N*M 이라 가정하면 수행값은 N*M*2^2 이다.
     * @param menu
     * @param food
     * @param M
     * @return
     */
    private int selectMenu(Stack<Integer> menu, int food, int M) {
        //모든 음식에 대해 만들지 여부를 결정할 때
        if (food == M) {
            if (canEveryBodyEat(menu)) return menu.size();
            //아무것도 못먹는다면 아주 큰 값 반환.
            else return Integer.MAX_VALUE;
        }
        //이 음식을 만들지 않을 경로를 택할 경우의 답을 계산.
        int ret = selectMenu(menu, food + 1, M);
        menu.push(food);
        //음식을 만들까 예/아니요 중 작은 값을 취한다.
        ret = Integer.min(ret, selectMenu(menu, food+1, M));
        menu.pop();
        return ret;
    }

    @Test
    public void givenMenuWhenSelectMenuExpectLeastNumberOfMenusWhichEveryBodyCanEat() {
        Stack<Integer> integers = new Stack<>();
        System.out.println(selectMenu(integers, 0, 5));
    }

    /**
     * 자연수 n의 소인수 분해 결과를 담은 리스트를 반환한다.
     * 시간 복잡도는 N-1 이므로 O(N) 이다.
     * @param n
     * @return
     */
    private List<Integer> factor(int n) {
        if (n == 1) return Arrays.asList(1);
        List<Integer> result = new ArrayList<>();
        for (int div=2; n>1; div++)
            while (n % div == 0) {
                n /= div;
                result.add(div);
            }
        return result;
    }

    @Test
    public void givenNumberWhenFactorThenGetAllFactors() {
        System.out.println(factor(10));
        System.out.println(factor(25));
        System.out.println(factor(80));
    }

    /**
     * O 표기법은 함수의 상한을 나타낸다.
     *
     * 아주 큰 N^0 와 C(N^0, C>0) 을 적절히 선택하면 N^0<=N인 모든 N 에 대해
     * f(N)<=C*g(N) 이 참이 되도록 할 수 있다.
     *
     * 만약 함수의 시간 복잡도가 N^2+100*N+1 = O(N^2) 이라면
     * 이때 적절한 C를 선택해서 N^2에 곱해주면 항상 N^2이 더 크다고 할 수 있다. (가장 빨리 증가하는 항)
     *
     * 선택 정렬과 삽입 정렬의 O(N^2)
     * 삽입 정렬은 최악의 배열을 받았을 경우 시간 복잡도인 O(N^2) 인 선택 정렬과 같지만,
     * 정렬된 배열을 받았을 경우 O(N)의 속도까지 낼 수 있으므로 같은 시간 복잡도지만
     * 대부분 삽입 정렬이 선택 정렬보다 빠르다.
     *
     */

    @Test
    public void givenIntArrayWhenSelectionSortGetSortedArray() {
        int[] origin = {6, 7, 5, 2, 8, 13, 9, 23, 1};
        int[] elements = origin.clone();
        selectionSort(elements);
        System.out.println(Arrays.toString(elements));

        elements = origin.clone();
        insertionSort(elements);
        System.out.println(Arrays.toString(elements));
    }

    /**
     * 선택 정렬(Selection Sort)
     * 모든 i 에 대해 A[i..N-1] 에서 가장 작은 원소를 찾은 뒤,
     * 이것을 A[i] 에 넣는 것을 반복한다.
     * (N-1)*N / 2 = O(N^2)
     * @param elements
     */
    private void selectionSort(int[] elements) {
        for(int i=0, n=elements.length; i<n; i++) {
            int min = i;
            for (int j=i+1; j<n; j++)
                if (elements[min] > elements[j])
                    min = j;
            swap(i, min, elements);
        }
    }

    /**
     * 삽입 정렬(Insertion Sort)
     * 전체 배열 중 '정렬되어 있는 부분 배열'에 새 원소를 끼워넣음을 반복한다.
     *
     * @param elements
     */
    private void insertionSort(int[] elements) {
        for (int i=0,n=elements.length; i<n; i++) {
            int j=i;
            while (j>0 && elements[j-1]>elements[j]) {
                swap(j, j-1, elements);
                --j;
            }
        }
    }

    private void swap(int index1, int index2, int[] elements) {
        int t = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = t;
    }

    @Test
    public void givenArrayWhenMaxSumOfElementsThenGet() {
        int[] origin = {-7, 4, -3, 6, 3, -8};
        System.out.println(inefficientMaxSum(origin));
        System.out.println(betterMaxSum(origin));
        System.out.println(fastMaxSum(origin, 0, origin.length-1));
        System.out.println(fastestMaxSum(origin));
    }

    /**
     * Loop all sections in the array and find the max sum of the sections.
     * O(N^2)
     * @param elements
     * @return
     */
    private int betterMaxSum(int[] elements) {
        int r = Integer.MIN_VALUE;
        for (int i=0, n=elements.length; i<n; i++) {
            int sum = 0;
            for (int j=i; j<n; j++) {
                sum += elements[j];
                r = Integer.max(r, sum);
            }
        }
        return r;
    }

    /**
     * Loop all sections in the array and find the max sum of the sections.
     * O(N^3)
     * @param elements
     * @return
     */
    private int inefficientMaxSum(int[] elements) {
        int r = Integer.MIN_VALUE;
        for (int i=0, n=elements.length; i<n; i++) {
            for (int j=i; j<n; j++) {
                int sum = 0;
                for (int k=i; k<=j; k++)
                    sum += elements[k];
                r = Integer.max(r, sum);
            }
        }
        return r;
    }

    /**
     * Loop all sections in the array and find the max sum of the sections.
     * O(NlogN)
     * Divide and Conquer Algorithm.
     * Find the max sum from A[low] to A[high]
     *
     * @param elements
     * @param low
     * @param high
     * @return
     */
    int fastMaxSum(int[] elements, int low, int high) {
        //termination point, Section 의 길이가 1인 경우
        if (low == high) return elements[low];
        //배열을 논리적 두 구간 low - mid, mid+1 - high 으로 나눈다.
        int mid = (low + high) / 2;
        //두 구간에 걸쳐 있는 최대 합 구간을 찾는다.
        int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE, sum = 0;
        for (int i=mid; i>=low; i--) {
            sum += elements[i];
            left = Integer.max(left, sum);
        }
        sum = 0;
        for (int i=mid+1; i<=high; i++) {
            sum += elements[i];
            right = Integer.max(right, sum);
        }
        //쵀대 구간이 두 조각 중 하나에만 속해 있는 경우의 답을 재귀 호출로 찾는다.
        int single = Integer.max(fastMaxSum(elements, low, mid),
                fastMaxSum(elements, mid+1, high));
        return Integer.max(left + right, single);
    }

    int fastestMaxSum(int[] elements) {
        int n = elements.length, r = Integer.MIN_VALUE, sum=0;
        for(int i=0; i<n; i++) {
            sum = Integer.max(sum, 0) + elements[i];
            r = Integer.max(sum, r);
        }
        return r;
    }
}


























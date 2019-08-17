package com.siwoo.algorithm.item4;

import org.junit.Test;

import javax.naming.ldap.PagedResultsControl;
import java.util.Arrays;

public class ProofOfCorrectness {
    /**
     * 수학적 귀납법(MATHEMATICAL INDUCTION)
     * :반복적인 구조를 갖는 명제들을 증명하는 기법.
     * 한 단계에서 증명하고 싶은 내용이 성립한다면, 다음 단계에서도 성립한다.
     *
     * 사다리 게임을 예로 들면, 맨 위 선택지는 아래 선택지와 1:1 대응된다.
     * 귀납 증명에선, 가로줄을 그어 두 개의 세로줄을 연결했을 때, 두 세로 줄의 결과는 SWAP 됨으로
     * 1:1 속성이 유지된다.
     * 따라서 귀납법에 의해 사다리들은 항상 1:1로 대응됨을 증명할 수 있다.
     *
     * 반복문 불변식(loop in-variant)
     * 반복문의 내용이 한 번 시랳ㅇ될 때마다 중간 결과가 우리가 원하는 답으로 가는 길 위에 잘 있는지를
     * 명시하는 조건.
     * "불변식"이 변하지 않고 성립해야만 마지막에 제대로된 정답이 계산된다.
     *
     * 불변식을 이용한 반복문의 정당성.
     * 1. 반복문 진입시에 불변식이 성립한다.
     * 2. 반복문 내용이 불변식을 깨뜨리지 않는다.
     * 3. 반복문 종료시에 불변식이 성립하면 우리가 정답을 구했음을 보인다.
     */

    @Test
    public void givenArrayWhenBinarySearchThenGetIndex() {
        int[] origin = {1, 2, 3, 4, 6, 9, 10, 11};
        System.out.println(binarySearch(origin, 3));
        System.out.println(binarySearch(origin, 10));
    }

    /**
     * 필수 조건: elements 은 오름차순으로 정렬되어 있다.
     * 결과: A[i-1] < el <= A[i] 인 i 을 반환한다.
     * @param elements
     * @param el
     * @return
     */
    private int binarySearch(int[] elements, int el) {
        final int N = elements.length;
        int low = -1, high = N;
        //loop in-variant 1: low < high
        //loop in-variant 2: A[low] < el <= A[high]
        //불변식은 항상 성립해야 한다.
        while (low + 1 < high) {
            int mid = (high + low) / 2;
            if (elements[mid] < el)
                low = mid;
            else
                high = mid;
            //불변식은 여기서도 성립해야 한다.
        }
        //불변식이 종료되었으니 불변식인 low<high 에 의하여 low+1=high 임이 증명된다.
        return high;
    }

    @Test
    public void givenArrayWhenInsertionSortThenGetSortedArray() {
        int[] origin = {4, 2, 3, -14, 8, 20};
        int[] elements = origin.clone();
        insertionSort(elements);
        System.out.println(Arrays.toString(elements));
    }

    private void insertionSort(int[] elements) {
        final int N = elements.length;
        for (int i=0; i<N; i++) {
            //불변식 1: A[0..i-1] 은 이미 정렬되어 있다.
            //A[0..i-1] 에 A[i] 을 끼워넣는다.
            int j=i;
            while (j>0 && elements[j-1]>elements[j]) {
                //불변식 2: A[j+ 1..i] 의 모든 원소는 A[j] 보다 크다.
                //불변식 3: A[0..i] 구간은 A[j] 을 제외하면 이미 '정렬'되어 있다.
                swap(elements, j, j-1);
                --j;
            }
        }
    }

    private void swap(int[] elements, int index1, int index2) {
        int t = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = t;
    }

    /**
     * 귀류법.
     * 원하는 바와 반대되는 상황을 가정하고 논리를 전개해서 결론이 잘못됐음을 찾아내는 증명 기법을 귀류법.
     *
     * 주료 귀류법은 어떤 선택이 항상 최선임을 증명하고자 할 때, 선택한 답보다 좋은 답이 있다고 가정한 후에,
     * 사실은 그런 일이 있을 수 없음을 보이며 선택된 답이 최선의 답이였음을 증명.
     *
     */
}

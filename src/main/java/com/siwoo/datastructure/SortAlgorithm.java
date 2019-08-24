package com.siwoo.datastructure;

import javafx.embed.swt.SWTFXUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;

public class SortAlgorithm {

    interface Sort<E extends Comparable<E>> {
        void sort(E[] array);

        /**
         * Swap the element with given two indexes.
         *
         * @param array container of elements
         * @param index1 index of element to swap
         * @param index2 index of element to swap
         * @throws NullPointerException if {@code array == null}
         * @throws IndexOutOfBoundsException if given index out of bounds
         */
        default void swap(E[] array, int index1, int index2) {
            if (index1 == index2) return;
            E t = array[index1];
            array[index1] = array[index2];
            array[index2] = t;
        };
    }

    static class BubbleSort<E extends Comparable<E>> implements Sort<E> {
        /**
         * sort Array by ascending order.
         *
         * In-place algorithm.
         * O(N^2)
         * partitioning array into sorted section and unsorted section.
         * in this implementation, sorting section will grows from left to right.
         * for the little optimization, inner loop will not traverse
         * into sorted section which has been already sorted.
         *
         * @param array to sort
         */
        @Override
        public void sort(E[] array) {
//            for (int sorted= array.length-1; sorted>0; sorted--) {
//                for (int i=0; i<sorted; i++)
//                    if (array[i].compareTo(array[i+1])>0)
//                        swap(array, i, i+1);
//
//            }
            sortRecur(array, array.length-1);
        }

        private void sortRecur(E[] array, int sorted) {
            if (sorted == 0)
                return;
            for (int i=0; i<sorted; i++)
                if (array[i].compareTo(array[i+1]) > 0)
                    swap(array, i, i+1);
            sortRecur(array, sorted-1);
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            Integer[] origin = create();
            Sort<Integer> sort = new InsertionSort<>();
            sort.sort(origin);
            System.out.println(Arrays.toString(origin));
            assertThat(origin).isSorted();
        }
    }

    private static Integer[] create() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Integer[] array = new Integer[15];
        for (int i=0, n=array.length; i<n; i++) {
            array[i] = random.nextInt(20);
        }
        return array;
    }

    static class SelectionSort<E extends Comparable<E>> implements Sort<E> {

        public void sortRecur(E[] array, int sorted) {
            //base case: all elements are in sorted boundary
            if (sorted == 0) return;
            int largest = 0;
            for (int i=1; i<sorted; i++) {
                // 가장 큰 요소를 갱신한다.
                if (array[i].compareTo(array[largest]) > 0)
                    largest = i;
            }
            swap(array, largest, sorted-1);
            sortRecur(array, sorted-1);
        }

        @Override
        public void sort(E[] array) {
//            for (int unsorted=array.length-1; unsorted>0; unsorted--) {
//                int largest = 0;
//                for (int i=1; i<=unsorted; i++) {
//                    if (array[i].compareTo(array[largest]) > 0)
//                        largest=i;
//                }
//                swap(array, largest, unsorted);
//            }
            sortRecur(array, array.length);
        }
    }

    static class InsertionSort<E extends Comparable<E>> implements Sort<E> {

        @Override
        public void sort(E[] array) {
//            final int N = array.length;
//            for (int sorted=1; sorted<N; sorted++) {
//                E el = array[sorted]; int i=sorted;
//                while (i > 0 && array[i-1].compareTo(el) > 0) {
//                    array[i] = array[i-1];
//                    i--;
//                }
//                array[i] = el;
//            }
            sortRecur(array, 1);
        }

        private void sortRecur(E[] array, int sorted) {
            if (sorted == array.length) return;
            E el = array[sorted];
            int i;
            for (i=sorted; i>0 && el.compareTo(array[i-1]) < 0; i--) {
                array[i] = array[i-1];
            }
            array[i] = el;
            sortRecur(array, sorted+1);
        }
    }
}

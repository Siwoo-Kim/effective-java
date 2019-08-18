package com.siwoo.datastructure;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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
        Integer[] origin = create();
        Sort<Integer> sort = new BubbleSort<>();
        sort.sort(origin);
        System.out.println(Arrays.toString(origin));
    }

    private static Integer[] create() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Integer[] array = new Integer[15];
        for (int i=0, n=array.length; i<n; i++) {
            array[i] = random.nextInt(20);
        }
        return array;

    }
}

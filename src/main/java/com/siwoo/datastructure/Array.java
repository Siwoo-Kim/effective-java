package com.siwoo.datastructure;

/**
 * 배열 (Array)
 *
 * 특징
 *      :Contiguous block in memory
 *  배열의 모든 요소들은 메모리에 연속적으로 존재.
 *      :Every element occupies the same amount of space in memory
 *  배열에 저장되는 각 요소의 메모리 공간은 일치해야 한다. (Reference 은 4바이트)
 *  요소의 index 을 알고 있을시 배열의 요소 접근하는 시간 복잡도는 O(1)이다.
 *      :배열의 모든 요소가 연속적으로 존재하고 각 요소의 크기가 같으므로
 *      start memory + (size * index) 로 바로 계산할 수 있으므로.
 *  Arrays are not 'a dynamic data structure'. (cannot change it size)
 *
 */
public class Array {

    public static void main(String[] args) {
        int[] array = new int[7];
        array[0] = 20;  //start memory + (4 * 0) = O(1)
        array[1] = 35;  //start memory + (4 * 1) = O(1)
        array[2] = -15; //start memory + (4 * 2) = O(1)
        array[3] = 7;   //start memory + (4 * 3) = O(1)
        array[4] = 55;  //start memory + (4 * 4) = O(1)
        array[5] = 1;   //start memory + (4 * 5) = O(1)
        array[6] = -22; //start memory + (4 * 6) = O(1)

//        for (int i=0; i<array.length; i++)
//            System.out.println(array[i]);
        int index = -1;
        for (int i=0, n=array.length; i<n; i++) {   //if don't know index, O(N)
            if (array[i] == 7) {
                index = i;
                break;
            }
        }
        System.out.println(index);
    }
}

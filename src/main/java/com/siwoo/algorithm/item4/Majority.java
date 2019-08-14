package com.siwoo.algorithm.item4;

public class Majority {

//    static int majority(int[] array) {
//        int m = -1, count = 0;
//        for (int i=0, n=array.length; i<n; i++) {
//            int c = 0, v= array[i];
//            for (int j=0; j<n; j++) {   // 반복문이 두개 이므로 알고리즘은 N^2
//                if (v == array[j])
//                    c++;
//            }
//            if (c > count) {
//                m = v;
//                count = c;
//            }
//        }
//        return m;
//    }

    static int majority(int[] array) {
        final int max = 101, min = 0;
        int[] c = new int[max];
        for (int i=0,n=array.length; i<n; i++)
            c[array[i]]++;
        int r = 0;
        for (int i=min; i<max; i++)
            if (c[r]<c[i])
                r=i;
        return r;
    }

    public static void main(String[] args) {
        System.out.println(majority(new int[]{10, 20, 30, 55, 66, 78, 86, 66, 88, 99, 49, 55}));

    }
}

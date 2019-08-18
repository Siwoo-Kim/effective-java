package com.siwoo.datastructure;


/**
 * Time Complexity.
 *  :Time Complexity gives us an idea of how an algorithm
 *  will perform as the number of items
 *  that it has to deal with grows.
 *  시간 복잡성은 알고리즘이 처리해야 하는 항목의 수가
 *  증가함에 따라 알고리즘이 어떻게 작동하는지 알려줍니다.
 *
 *  :The number of items to be deal with
 *  is conventionally expressed as 'N'.
 *  처리해야 할 항목의 수는 주로 N으로 표현한다.
 *
 *  O(1)
 *  O(logN) 2진로그(N)
 *  O(N)
 *  O(NlogN) N*2진로그(N)
 *  O(N^2)
 *
 */
public class BigO {

    static class Tea {}
    static class Sugar {}

    static class TeaMaker {
        private Sugar addSugar() { return new Sugar(); };
        private Tea fetchBowl(){ return new Tea(); };
        private void pourSugar(Tea tea, Sugar sugar){ };

        /**
         * O(N) = Linear
         * @param numOfSugar number of sugar you desired
         * @return
         */
        public Tea make(int numOfSugar) {
            Tea tea = fetchBowl();
            for (int i=0; i<numOfSugar; i++) {
                Sugar sugar = addSugar();
                pourSugar(tea, sugar);
            }
            return tea;
        }
    }
}

package com.siwoo.algorithm.timecomplexity;

import java.util.ArrayList;
import java.util.List;

/**
 * Allergy
 * 집들이에 각 음식에 알러지가 있는 N 명의 친구를 초대하려 한다.
 * 이때 할 줄 아는 M 가지의 음식을 할 수 있다.
 * 각 친구가 먹을 수 있을 수 있는 음식이 최소한 하나씩은 있으려면
 * "최소" 몇 가지의 음식을 해야할 지 구하라.
 */

public class Allergy {

    private boolean[][] allergies;  //y=친구, x=알러지 여부
    private final int M; //할 줄 아는 음식의 수

    public Allergy(boolean[][] allergies, int m) {
        this.allergies = allergies.clone();
        M = m;
    }

    public static void main(String[] args) {
        Allergy allergy = new Allergy(new boolean[][]{
                {false, true, true, true, false, false},
                {false, false, false, false, true, true},
                {true, false, true, false, true, false},
                {true, true, false, false, false, true},
        }, 6);
        System.out.println(allergy.minMenu());
    }

    public int minMenu() {
        return minMenu(new ArrayList<>(), 0);
    }

    /**
     * 2^M * (F*M); F 친구의 수
     * @param menu 현재까지 선택한 요리
     * @param food 요리를 할지 선택해야할 food 의 index
     * @return
     */
    private int minMenu(List<Integer> menu, int food) {
        //BASE CASE: 모든 메뉴을 만들지 여부를 결정했을 때
        if (food == M) {
            if (canEveryBodyEat(menu))
                return menu.size();
            else
                return Integer.MAX_VALUE;
        }
        //해당 메뉴를 선택하지 않았을 경우의 최솟값.
        int r = minMenu(menu, food+1);
        //해당 메뉴를 선택했을 경우의 최소값 중 작은 것을 선택
        menu.add(food);
        r = Math.min(r, minMenu(menu, food+1));
        menu.remove(menu.size() - 1);
        return r;
    }

    private boolean canEveryBodyEat(List<Integer> menu) {
        boolean canEveryBodyEat = true;
        for (int friend=0; friend<allergies.length; friend++) {
            canEveryBodyEat = canEat(friend, menu) && canEveryBodyEat;
        }
        return canEveryBodyEat;
    }

    private boolean canEat(int friend, List<Integer> menu) {
        for (int i=0; i<menu.size(); i++) {
            if (allergies[friend][menu.get(i)])
                return true;
        }
        return false;
    }
}

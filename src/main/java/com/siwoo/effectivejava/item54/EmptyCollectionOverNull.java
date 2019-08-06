package com.siwoo.effectivejava.item54;

import java.util.ArrayList;
import java.util.List;

public class EmptyCollectionOverNull {


    public static void main(String[] args) {
        Cheese cheese = new Cheese();
        CheeseStock shop = new CheeseStock();
        List<Cheese> cheeses = shop.getCheeses();
        if (cheeses != null && cheeses.contains(cheese))
            System.out.println("좋았어, 바로 그거야.");
    }

    static class CheeseStock {
        private final List<Cheese> cheesesInStock = null;

        public List<Cheese> getCheeses() {
            return new ArrayList<>(cheesesInStock);
        }
    }

    static class Cheese {

    }
}

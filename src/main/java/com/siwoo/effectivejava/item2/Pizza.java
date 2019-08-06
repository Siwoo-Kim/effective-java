package com.siwoo.effectivejava.item2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
    public static void main(String[] args) {
        Pizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Topping.HAM)
                .build();
        System.out.println(pizza);
        pizza = new Calzone.Builder()
                .addTopping(Topping.ONION)
                .addTopping(Topping.SAUSAGE)
                .sauceInside()
                .build();
        System.out.println(pizza);
    }

    private enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings;

    static abstract class Builder<T extends Builder<T>> {
        private EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        /**
         * @implSpec
         *      하위 클래스는 이 메서드를 재정의하여 "this" 를 리턴해야 한다.
         * @return Builder which extends this
         */
        abstract T self();

        abstract Pizza build();
    }

    public Pizza(Builder<?> b) {
        this.toppings = b.toppings.clone();
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "toppings=" + toppings +
                '}';
    }
}

class NyPizza extends Pizza {
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = size;
        }

        @Override
        Builder self() {
            return this;
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }
    }

    public NyPizza(Builder b) {
        super(b);
        this.size = b.size;
    }

    @Override
    public String toString() {
        return "NyPizza{" +
                "toppings=" + toppings +
                ", size=" + size +
                '}';
    }
}


class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }

        @Override
        Builder self() {
            return this;
        }

        @Override
        Calzone build() {
            return new Calzone(this);
        }
    }

    public Calzone(Builder b) {
        super(b);
        this.sauceInside = b.sauceInside;
    }

    @Override
    public String toString() {
        return "Calzone{" +
                "toppings=" + toppings +
                ", sauceInside=" + sauceInside +
                '}';
    }
}
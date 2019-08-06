package com.siwoo.effectivejava.item2;

import java.util.OptionalInt;

public class NutritionFacts {
    private final int servingSize;       //required
    private final int servings;           //required
    private final OptionalInt calories;
    private final OptionalInt fat;
    private final OptionalInt sodium;
    private final OptionalInt carbohydrate;

    private NutritionFacts(Builder b) {
        this.servingSize = b.servingSize;
        this.servings = b.servings;
        this.calories = b.calories;
        this.fat = b.fat;
        this.sodium = b.sodium;
        this.carbohydrate = b.carbohydrate;
    }

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private OptionalInt calories = OptionalInt.empty();
        private OptionalInt fat = OptionalInt.empty();
        private OptionalInt sodium = OptionalInt.empty();
        private OptionalInt carbohydrate = OptionalInt.empty();

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) { calories = OptionalInt.of(val); return this; }
        public Builder fat(int val) { fat = OptionalInt.of(val); return this; }
        public Builder sodium(int val) { sodium = OptionalInt.of(val); return this; }
        public Builder carbohydrate(int val) { carbohydrate = OptionalInt.of(val); return this; }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

//    public NutritionFacts() { }
//
//    public void setServingSize(int servingSize) {
//        this.servingSize = servingSize;
//    }
//
//    public void setServings(int servings) {
//        this.servings = servings;
//    }
//
//    public void setCalories(int calories) {
//        this.calories = OptionalInt.of(calories);
//    }
//
//    public void setFat(int fat) {
//        this.fat = OptionalInt.of(fat);
//    }
//
//    public void setSodium(int sodium) {
//        this.sodium = OptionalInt.of(sodium);
//    }
//
//    public void setCarbohydrate(int carbohydrate) {
//        this.carbohydrate = OptionalInt.of(carbohydrate);
//    }
    //    public NutritionFacts(int servingSize, int servings) {
//        this(servingSize, servings, 0);
//    }
//
//    public NutritionFacts(int servingSize, int servings, int calories) {
//        this(servingSize, servings, calories, 0);
//    }
//
//    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
//        this(servingSize, servings, calories, fat, 0);
//    }
//
//    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
//        this(servingSize, servings, calories, fat, sodium, 0);
//    }
//
//    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
//        this.servingSize = servingSize;
//        this.servings = servings;
//        this.calories = calories == 0 ? OptionalInt.empty() : OptionalInt.of(calories);
//        this.fat = fat == 0 ? OptionalInt.empty() : OptionalInt.of(fat);
//        this.sodium =  sodium == 0 ? OptionalInt.empty() : OptionalInt.of(sodium);
//        this.carbohydrate = carbohydrate == 0 ? OptionalInt.empty() : OptionalInt.of(carbohydrate);
//    }
//

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }

    public static void main(String[] args) {
        //NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
//        NutritionFacts cocaCola = new NutritionFacts();
//        cocaCola.setServingSize(240);
//        cocaCola.setServingSize(8);
//        cocaCola.setCalories(100);
//        cocaCola.setSodium(35);
//        cocaCola.setCarbohydrate(27);
        NutritionFacts cocaCola = new Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27).build();
        System.out.println(cocaCola);
    }
}

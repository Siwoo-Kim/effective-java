package com.siwoo.effectivejava.item3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class Elvis {
    //public static final Elvis INSTANCE = new Elvis();
    private static int numOfInstance = 0;       //don't set '0'
    private static final Elvis INSTANCE = new Elvis();

    private Elvis() {
        numOfInstance++;
        if (numOfInstance > 1)
            throw new IllegalStateException();
    }

    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("See you");
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        Constructor<?>[] c = Elvis.class.getDeclaredConstructors();
////        for (Constructor e: c) {
////            e.setAccessible(true);
////            Elvis elvis = (Elvis) e.newInstance();
////            System.out.println(elvis == Elvis.INSTANCE);
////        }

        Supplier<Elvis> supplier = Elvis::getInstance;
        Elvis elvis = supplier.get();
        System.out.println(elvis);

        Constructor<?>[] c = Singleton.class.getConstructors();
        for (Constructor e: c) { //Constructor[0]
            e.setAccessible(true);
            Singleton singleton = (Singleton) e.newInstance();
            System.out.println(singleton == Singleton.INSTANCE);
        }
    }
}

enum Singleton {
    INSTANCE;

    Singleton() { }

    public void leaveTheBuilding() {
        System.out.println("Enum over Singleton class");
    }
}
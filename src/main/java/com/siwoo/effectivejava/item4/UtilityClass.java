package com.siwoo.effectivejava.item4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UtilityClass {

    // Suppresses default constructor, ensuring non-instantiability.
    private UtilityClass() {
        throw new AssertionError();
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> c = UtilityClass.class.getDeclaredConstructors()[0];
        c.setAccessible(true);
        UtilityClass instance = (UtilityClass) c.newInstance();
    }
}

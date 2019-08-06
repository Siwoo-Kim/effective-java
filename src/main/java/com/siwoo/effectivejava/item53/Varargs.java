package com.siwoo.effectivejava.item53;

/**
 * Created by sm123tt@gmail.com on 2019-08-05
 * Project: effective-java
 * Github : http://github.com/Siwoo-Kim
 */

public class Varargs {

    static int sum(int... args) {
        int sum = 0;
        for (int arg: args)
            sum += arg;
        return sum;
    }

    static int min(int firstArg, int... args) {
        int min = firstArg;
        for (int arg: args)
            if (arg < min)
                min = arg;
        return min;
    }

    public void foo() { }
    public void foo(int a) { }
    public void foo(int a, int b) { }
    public void foo(int a, int b, int c) { }
    public void foo(int a, int b, int c, int... rest) { }
}


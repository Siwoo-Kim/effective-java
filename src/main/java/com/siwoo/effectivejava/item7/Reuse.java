package com.siwoo.effectivejava.item7;

import com.google.common.base.Stopwatch;
import sun.dc.pr.PRError;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;


public class Reuse {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            String s = new String("bikini");
        }
        System.out.println(stopwatch.stop());
        stopwatch = Stopwatch.createStarted();
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            String s = "bikini";
        }
        System.out.println(stopwatch.stop());
        stopwatch = Stopwatch.createStarted();
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            Boolean b = new Boolean(true);
        }
        System.out.println(stopwatch.stop());
        stopwatch = Stopwatch.createStarted();
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            Boolean b = Boolean.valueOf(true);
        }
        System.out.println(stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        for (int i=0; i<5000000; i++) {
            RomanNumerals rn = new RomanNumerals("XL"); //6.562s
        }
        System.out.println(stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        for (int i=0; i<5000000; i++) {
            ReusedRomanNumerals rn = new ReusedRomanNumerals("XL"); //0.008 s
        }
        System.out.println(stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        for (int i=0; i<5000000; i++) {
            ReusedRomanNumerals rn = ReusedRomanNumerals.of("XL"); //0.0008 s
        }
        System.out.println(stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        Long sum = 0L;
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        long sum2 = 0L;
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            sum2 += i;
        }
        System.out.println(stopwatch.stop());
    }

}

class RomanNumerals {
    private final String roman;
    public RomanNumerals(String roman) {
        checkArgument(roman != null && isRomanNumeral(roman));
        this.roman = roman;
    }

    private boolean isRomanNumeral(String s) {
        return s.matches("(^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$)");
    }
}

class ReusedRomanNumerals {
    private final String roman;
    private static final Pattern ROMAN = Pattern.compile("(^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$)");
    private static final Map<String, ReusedRomanNumerals> cache = new ConcurrentHashMap<>();

    public ReusedRomanNumerals(String roman) {
        checkArgument(roman != null && ROMAN.matcher(roman).matches());
        this.roman = roman;
    }

    public static ReusedRomanNumerals of(String roman) {
        if (cache.containsKey(roman))
            return cache.get(roman);
        checkArgument(roman != null && ROMAN.matcher(roman).matches());
        ReusedRomanNumerals n = new ReusedRomanNumerals(roman);
        cache.computeIfAbsent(roman, k -> n);
        return n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReusedRomanNumerals that = (ReusedRomanNumerals) o;
        return Objects.equals(roman, that.roman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roman);
    }
}
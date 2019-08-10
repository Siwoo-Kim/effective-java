package com.siwoo.effectivejava.item7;

import sun.misc.GC;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.*;

public class Stack<E> {
    private E[] elements;
    private int size;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        this.elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E e = elements[--size];
        elements[size] = null; //obsolete reference 의 null 처리
        return e;
    }

    /**
     * 원소를 위한 공간을 최소 하나를 확보한다. {@code size == 0 ? 1 : 2 * size + 1}
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    private void ensureCapacity() {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, (size << 1) + 1);
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new Cache<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 1);
        map.put(4, 3);
        System.out.println(map.get(1)); //null

        WeakReference<Student> ref = new WeakReference<Student>(new Student("siwoo"));
        //Student s = ref.get();
        while (ref.get() != null) {
            System.gc();
        }

        System.out.println(ref.get() == null);
    }
}
class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }
}

class Cache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 3;

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
}
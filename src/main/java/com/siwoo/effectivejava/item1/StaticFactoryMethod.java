package com.siwoo.effectivejava.item1;

import lombok.ToString;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import sun.reflect.Reflection;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.google.common.base.Preconditions.checkArgument;

public class StaticFactoryMethod {

    public static void main(String[] args) {
        Coffee coffee = Coffee.of("BLACK");
        System.out.println(coffee);

        for (int i = 0; i < 20; i++)
            System.out.println(BigInteger.probablePrime(3, new Random()));
    }

    @ToString
    static class Coffee {
        private static final Coffee BLACK = new Coffee("BLACK");
        private final String name;

        private Coffee(String name) {
            this.name = name;
        }

        /**
         * @param name of coffee
         * @throws IllegalArgumentException if name is null
         * @throws IllegalArgumentException if name is empty string
         * @return
         */
        private static Coffee of(String name) {
            checkArgument(Objects.nonNull(name) && !name.isEmpty());
            if ("BLACK".equals(name))
                return BLACK;
            return new Coffee(name);
        }
    }

    @Test
    public void testUnmodifiableCollection() {
        Collection<Integer> collection = new SimpleCollection<Integer>();
        collection.set(0, 10);
        collection.set(1, 20);

        Collection<Integer> unmodifiableCollection = Collections.unmodifiableCollection(collection);
        unmodifiableCollection.set(0, 30);
    }

    interface Collection<E> {
        E get(int index);
        void set(int index, E e);
    }

    static class SimpleCollection<E> implements Collection<E> {
        private E[] elementData;
        private static final int DEFAULT_CAPACITY = 10;

        @SuppressWarnings("unchecked")
        public SimpleCollection() {
            elementData = (E[]) new Object[DEFAULT_CAPACITY];
        }

        @Override
        public E get(int index) { return elementData[index]; } //simplified
        @Override
        public void set(int index, E el) { elementData[index] = el; } //simplified
    }

    static class Collections {

        public static <E> Collection<E> unmodifiableCollection(Collection<? extends E> c) {
            return new UnmodifiableCollection<>(c);
        }

        private static class UnmodifiableCollection<E> implements Collection<E> {
            private final Collection<? extends E> el;

            public UnmodifiableCollection(Collection<? extends E> el) {
                checkArgument(el != null);
                this.el = el;
            }

            @Override
            public E get(int index) {
                return el.get(index);
            }

            @Override
            public void set(int index, E el) {
                throw new UnsupportedOperationException();
            }
        }
    }

    //Service interface
    static interface Connection {

    }

    //Factory class for Service interface
    static interface Driver {
        Connection getConnection();
    }

    //Class for register and service access.
    static class DriverManager {
        private static final List<Driver> registerDrivers = new CopyOnWriteArrayList<>();

        private DriverManager() { }

        public static synchronized void registerDriver(Driver driver) {
            checkArgument(Objects.nonNull(driver));
            if (!registerDrivers.contains(driver))
                registerDrivers.add(driver);
        }

        public static Connection getConnection(String url)  {
            for (Driver d: registerDrivers) {
                Connection con = Objects.requireNonNull(d.getConnection());
                return con;
            }
            throw new IllegalStateException();
        }
    }
}

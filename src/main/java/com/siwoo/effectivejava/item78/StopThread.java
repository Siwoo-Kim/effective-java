package com.siwoo.effectivejava.item78;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class StopThread {
    private static volatile boolean stopRequested;
    //private static volatile int nextSerialNumber = 0;
    private static int nextSerialNumber = 0;
    private static final AtomicLong nextSerialNum = new AtomicLong();

    public static synchronized int generateSerialNumber() {
        return nextSerialNumber++;
    }

    public static long generateSerialNumber2() {
        return nextSerialNum.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i=0;
            while (!stopRequested)
                i++;
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;

        for (int i=0; i<1000; i++)
            new Thread(() -> System.out.println(generateSerialNumber())).start(); //last el - 998
        for (int i=0; i<1000; i++)
            new Thread(() -> System.out.println(generateSerialNumber2())).start();
    }

}

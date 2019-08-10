package com.siwoo.effectivejava.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class ExeucteTiming {

    public static long time(Executor exec, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i=0; i<concurrency; i++) {
            exec.execute(() -> {
                // 타이머에게 준비를 마쳤음을 알린다.
                ready.countDown();
                try {
                    // 모든 작업자 스레드가 준비될 때까지 기다린다.
                    start.await();
                    action.run();
                }  catch (InterruptedException e) {
                    Thread.currentThread().interrupt();;
                } finally {
                    // 타이머에게 작업을 마쳤음을 알린다.
                    done.countDown();
                }
            });
        }

        ready.await(); // 모든 작업자가 준비될 때까지 기다린다.
        long startNanos = System.nanoTime();
        start.countDown();  //준비 땅!
        done.await();   //모든 작업자가 일을 끝마치기를 기다린다.
        return System.nanoTime() - startNanos;
    }

}

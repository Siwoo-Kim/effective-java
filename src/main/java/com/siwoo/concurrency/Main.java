package com.siwoo.concurrency;

import static com.siwoo.concurrency.AnsiColor.*;
import static java.lang.Thread.currentThread;

public class Main {

    static class MyRunnable implements Runnable {
        final Thread dependent;

        MyRunnable(Thread dependent) {
            this.dependent = dependent;
        }

        @Override
        public void run() {
            System.out.println(RED + currentThread().getName() +  ": Hello.");
            try {
                dependent.join(); //wait until dependent finish his work.
                System.out.println(RED + currentThread().getName() + ": "
                        + dependent.getName()
                        + " terminated, so I'm running again.");
            } catch (InterruptedException e) {
                System.out.println(RED + currentThread().getName() + ": I couldn't waits after all. I was interrupted");
            }
        }
    }

    public static void main(String[] args) {
        currentThread().setName("Main Thread");
        System.out.println(PURPLE + currentThread().getName() + ": Hello.");

        Thread anotherThread = new AnotherThread("Another Thread");
        anotherThread.start();
        Thread secondThread = new Thread(new MyRunnable(anotherThread));
        secondThread.start();
    }

}

package com.siwoo.concurrency;

import java.util.concurrent.TimeUnit;

import static com.siwoo.concurrency.AnsiColor.BLUE;

public class AnotherThread extends Thread {

    public AnotherThread(String name) {
        setName(name);
    }

    @Override
    public void run() {
        System.out.println(BLUE +  getName() + ": Hello from another thread.");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(BLUE + getName() + ": another thread woke me up.");
        }
        System.out.println(BLUE + getName() + ": Three seconds passed. terminating.");
    }
}

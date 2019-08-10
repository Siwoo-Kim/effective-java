package com.siwoo.effectivejava.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskQueue {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(() -> {
            for(int i=0; i<10000; i++)
                System.out.println(i);
        });
        exec.shutdown();
    }
}

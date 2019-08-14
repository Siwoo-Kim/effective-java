package com.siwoo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafty {
    //private static boolean done = false;
    private static volatile boolean done = false;

    public static void main(String[] args) {
        Runnable hellos = () -> {
            for (int i=0; i<1000; i++)
                System.out.println("Hello " + i);
            done = true;
        };
        Runnable goodbye = () -> {
            int i = 0;
            while (!done)   //이 쓰레드는 동기화 없이는 다른 쓰레드의 변화를 절대 보지 못한다.
                i++;
            /**
             * JVM 은 최적화 코드로 if(!done) while(true) i++; 로 변경한다.
             * volatile 을 사용하면 컴파일러는 해당 변수의 값을 접근할 시 캐시된 사본이 아닌 동기화된 캐시를 가져오도록 명령을 내린다.
             */
            System.out.println("Bye " + i);
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(hellos);
        exec.execute(goodbye);
    }
}

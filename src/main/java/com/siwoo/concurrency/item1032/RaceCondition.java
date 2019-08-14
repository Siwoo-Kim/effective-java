package com.siwoo.concurrency.item1032;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * Created by sm123tt@gmail.com on 2019-08-13
 * Project: effective-java
 * Github : http://github.com/Siwoo-Kim
 */

public class RaceCondition {
    private static int count = 0;  //volatile 은 캐시된 값이 아닌 동기화된 값을 가져오라 명령한다.
    private static RaceCondition lock = new RaceCondition();
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i=1; i<100; i++) {
            final int taskId = i;
            Runnable task = () -> {
                for (int j=0; j<1000; j++)
                    synchronized (lock) {
                        count++;
                    }
                /**
                 * ++ 연사는 '원자적' 이지 못하다.
                 * {@code count++} 은 {@code int reg = count + 1; count = reg} 와 같다.
                 * 이 두번의 연산속에서 여러 쓰레드들은 비집어 들어와 공유 변수를 업데이트 하려고 '경쟁' 한다.
                 */
                System.out.println(taskId + " : " + count);
            };
            exec.submit(task);
        }
    }

}

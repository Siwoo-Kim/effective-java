package com.siwoo.concurrency.task;

import com.google.common.collect.ImmutableSet;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

public class Executable {

    public static void main(String[] args) throws ExecutionException, InterruptedException, FileNotFoundException {
        Runnable task1 = () -> {
            for (int i=0; i<1000; i++)
                System.out.printf("hello %s - %d%n", currentThread().getName(), i);
        };
        Runnable task2 = () -> {
            for (int i=0; i<1000; i++)
                System.out.printf("bye %s - %d%n", currentThread().getName(), i);
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        //각 테스크는 하나의 쓰레드에서 실행된다.
        exec.execute(task1);
        exec.execute(task2);

        exec = Executors.newFixedThreadPool(10);
        Callable<Long> task = () -> {
            long sum = 0;
            for (int i=0; i<Integer.MAX_VALUE; i++)
                sum += i;
            return sum;
        };
        Future<Long> result = exec.submit(task);
        while (!result.isDone())
            System.out.println("Wait..");
        System.out.println(result.get());

        Set<Path> paths = ImmutableSet.of(Paths.get("C:\\Users\\HOMEPC\\IntelliJIDEAProjects\\effective-java\\target\\classes\\tmp\\file1"),
                Paths.get("C:\\Users\\HOMEPC\\IntelliJIDEAProjects\\effective-java\\target\\classes\\tmp\\file2"),
                Paths.get("C:\\Users\\HOMEPC\\IntelliJIDEAProjects\\effective-java\\target\\classes\\tmp\\file3"));
        long total = countWords(paths);
        System.out.println(total);
        System.out.println(hasWordOf("THREAD", paths));
        System.out.println(Runtime.getRuntime().availableProcessors());

        List<CompletableFuture<Long>> results = new ArrayList<>();
        int numOfThreads = 10;
        for (int i=0; i<numOfThreads; i++) {
            results.add(CompletableFuture.supplyAsync(() -> {
                long sum = 0;
                for (int j=0; j<Integer.MAX_VALUE; j++)
                    sum += j;
                return sum;
            }, exec));
        }
        for (CompletableFuture<Long> r: results)
            r.whenComplete((r2, x) -> {
                if (x == null)
                    System.out.println("Task complete " + r2);
                else
                    System.out.println("Task failed by " + x);
            });

        CompletableFuture<String> contents = readPage(paths);
    }


    private static CompletableFuture<String> readPage(Set<Path> paths) {
        return null;
    }

    static long countWords(Set<Path> paths) throws FileNotFoundException, InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        List<Callable<Long>> tasks = new ArrayList<>();
        for (Path path: paths) {
            tasks.add(() -> {
                try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path.toFile())))) {
                    long count = 0;
                    while (scanner.hasNext()) {
                        count++;
                        scanner.next();
                    }
                    return count;
                }
            });
        }
        List<Future<Long>> results = exec.invokeAll(tasks);
        long total = 0;
        for (Future<Long> future: results)
            total += future.get();
        return total;
    }

    static Path hasWordOf(final String word, Set<Path> paths) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(paths.size());
        List<Callable<Path>> tasks = new ArrayList<>();
        for (Path path: paths) {
            tasks.add(() -> {
                try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path.toFile())))) {
                    boolean result = false;
                    while (scanner.hasNext()) {
                        result |= word.equals(scanner.next());
                    }
                    //just test
                    if (result)
                        return path;
                    else
                        throw new AssertionError();
                }
            });
        }
        return exec.invokeAny(tasks);
    }
}

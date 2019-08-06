package com.siwoo.effectivejava.item55;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsageOptional {

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder());
    }


    public static void main(String[] args) {
        List<Optional<ProcessHandler>> streamOfOptionals = new ArrayList<>(
                Arrays.asList(
                    Optional.of(new ProcessHandler(1)),
                    Optional.of(new ProcessHandler(2)),
                    Optional.of(new ProcessHandler(3)))
        );

        OptionalInt optionalInt = OptionalInt.of(3);
        System.out.println(optionalInt.orElse(-1));

        List<ProcessHandler> result = streamOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(result);

        result = streamOfOptionals.stream()
                .flatMap(Stream::of)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    static class ProcessHandler {
        private int pid = 0;

        public ProcessHandler(int pid) {
            this.pid = pid;
        }

        private int pid() {
            return pid;
        }

        private Optional<ProcessHandler> parent() {
            return Optional.empty();
        }

        @Override
        public String toString() {
            return "ProcessHandler{" +
                    "pid=" + pid +
                    '}';
        }
    }

}

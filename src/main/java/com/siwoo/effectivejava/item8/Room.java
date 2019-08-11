package com.siwoo.effectivejava.item8;


public class Room implements AutoCloseable {

    @Override
    public void close() {
        state.run();
    }

    private static class State implements Runnable {
        int numJunks;

        State(int numJunks) {
            this.numJunks = numJunks;
        }

        @Override
        public void run() {
            System.out.println("방 청소");
            numJunks = 0;
        }
    }

    private final State state;

    public Room(int numJunks) {
        state = new State(numJunks);
    }
}

class Adult {
    public static void main(String[] args) {
        try (Room myRoom = new Room(7)) {
            System.out.println("안녕~");
        }
    }
}


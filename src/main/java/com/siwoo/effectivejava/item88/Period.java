package com.siwoo.effectivejava.item88;

import java.io.*;
import java.util.Date;

public class Period implements Serializable {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시각.
     * @param end 종료 시각; 시작 시간보다 뒤여야 한다. {@code end.compareTo(start) > 0}
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException start 나 end 가 null이면 발생.
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) > 0)
            throw new IllegalStateException(start + "가 " + end + " 보다 늦다.");
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        if (start.compareTo(end) > 0)
            throw new InvalidObjectException(start + "가 " + end  + "보다 늦다");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Period instance = new Period(new Date(), new Date());
        byte[] bytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(instance);
            bytes = baos.toByteArray();
        }
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            System.out.println(in.readObject());
        }
    }

    private static Period deserialize(byte[] sf) {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(sf))) {
            return (Period) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
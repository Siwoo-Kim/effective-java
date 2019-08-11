package com.siwoo.effectivejava.item87;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javassist.compiler.ast.StringL;

import java.io.*;
import java.nio.file.Paths;

public class StringList implements Serializable {
    private transient int size = 0;
    private transient Entry head = null;
    private transient Entry tail = null;
    private String testInstance;

    //no serialization for physical implementation.
    private static class Entry {
        String data;
        Entry next;
        Entry previous;

        public Entry(String data, Entry next, Entry prev) {
            this.data = data;
            this.next = next;
            this.previous = prev;
        }
    }

    public final void add(String s) {
        final Entry h = head;
        final Entry e = new Entry(s, h, null);
        head = e;
        if (h != null)
            h.previous = e;
        else
            tail = e;
        size++;
    }

    /**
     * 이 {@code StringList} 인스턴스를 직렬화한다.
     *
     * @serialData 이 리스트의 크기(포함된 문자열의 개수) 를 기록한 후
     * ({@code int}), 이어서 모든 뭔소를(각각은 {@code String}
     * 순서대로 기록한다.
     *
     * @param s
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);

        for (Entry e=tail; e!=null; e=e.previous)
            s.writeObject(e.data);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int numElements = s.readInt();

        for (int i=0; i<numElements; i++)
            add((String) s.readObject());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry c = head;
        while (c!=null) {
            sb.append(c.data).append(",");
            c = c.next;
        }

        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StringList stringList = new StringList();
        stringList.testInstance = "test";
        stringList.add("hi");
        stringList.add("serial");
        stringList.add("obj");
        System.out.println(stringList);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(Paths.get(".test-serialize2.txt").toString())))) {
            out.writeObject(stringList);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(Paths.get(".test-serialize2.txt").toString())))) {
            StringList deserialized = (StringList) in.readObject();
            System.out.println(deserialized);
            System.gc();
        }

    }
}

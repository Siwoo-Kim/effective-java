package com.siwoo.effectivejava.item8;

import com.google.common.base.MoreObjects;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Finalizer {

//    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//
//        WeakReference<Attacker> attacker;
//        {
//            attacker = new WeakReference<>(new Attacker("bad", "guy"));
//            byte[] bytes;
//            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                 ObjectOutputStream out = new ObjectOutputStream(bos)) {
//                out.writeObject(attacker.get());
//                bytes = bos.toByteArray();
//                try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//                     ObjectInputStream in = new ObjectInputStream(bis)) {
//                    Attacker resultObj = (Attacker) in.readObject();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.gc();
//        System.out.println(Attacker.instances);
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HttpContent content = new HttpContent("GET", "HI");
        byte[] bytes;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(content);
            bytes = bos.toByteArray();
        }

        Http instance;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            Http http = Http.of(new ObjectInputStream(bis))) {
            instance = http;
            System.out.println(http);
        }
        instance.close();
    }
}

class HttpContent implements Serializable {
    String method;
    String content;

    public HttpContent(String method, String content) {
        this.method = method;
        this.content = content;
    }
}

class Http implements AutoCloseable {
    private ObjectInputStream in;
    private HttpContent content;

    public Http(HttpContent content, ObjectInputStream in) {
        this.in = in;
        this.content = content;
    }

    public static Http of(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return new Http((HttpContent) Objects.requireNonNull(in.readObject()), in);
    }

    String content() {
        return content.content;
    }

    String method() {
        return content.method;
    }

    @Override
    public void close() {
        if (Objects.nonNull(in)) {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected final void finalize() throws Throwable {
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("method", content.method)
                .add("content", content.content)
                .toString();
    }
}
class Name implements Serializable {

    private final String firstName;

    private final String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected final void finalize() throws Throwable {         //prevent extension
        super.finalize();
    }
}

class Attacker extends Name {
    static final List<Attacker> instances = new ArrayList<>();

    public Attacker(String firstName, String lastName) {
        super(firstName, lastName);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        throw new IllegalArgumentException();
    }

//    @Override
//    protected void finalize() throws Throwable {
//        instances.add(this); //to prevent gc to collect this instance.
//    }
}
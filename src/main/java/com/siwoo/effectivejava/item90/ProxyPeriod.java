package com.siwoo.effectivejava.item90;

import java.io.*;
import java.util.Base64;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;

public class ProxyPeriod implements Serializable {
    private final Date start;
    private final Date end;

    public ProxyPeriod(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        checkArgument(this.start.compareTo(this.end) <= 0);
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Date start;
        private final Date end;

        public SerializationProxy(ProxyPeriod p) {
            this.start = p.start;
            this.end = p.end;
        }

        // writeReplace 으로 직렬화된 프록시를 역직렬화할때 다시 바깥 인스턴스를 생성하여 리턴화한다.
        private Object readResolve() {
            return new ProxyPeriod(start, end);
        }
    }

    // 직렬화 프록시 패턴용 writeReplace 메서드. ProxyPeriod 을 직렬화시 바깥 클래스의 인스턴스 대신 내부 프록시 인스턴스를 반환하게 한다.
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream in) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(new ProxyPeriod(new Date(), new Date()));
            byte[] bytes = bos.toByteArray();
            System.out.println(Base64.getEncoder().encodeToString(bytes));
            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                ProxyPeriod p = (ProxyPeriod) in.readObject();
                System.out.println(p);
            }
        }
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}

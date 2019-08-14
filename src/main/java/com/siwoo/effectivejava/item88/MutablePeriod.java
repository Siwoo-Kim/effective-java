package com.siwoo.effectivejava.item88;

import java.io.*;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;

public class MutablePeriod implements Serializable {
    private static final long serialVersionUID = 1l;
    private final Date start;
    private final Date end;

    public MutablePeriod(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        checkArgument(this.start.compareTo(this.end) <= 0);
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(baos)) {
            MutablePeriod p = new MutablePeriod(new Date(), new Date());
            out.writeObject(p);
            byte[] ref = { 0x71, 0, 0x7e, 0, 5 }; //#5 start 참조
            baos.write(ref);
            ref[4] = 4; //#4 end 참조
            baos.write(ref);
            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
                MutablePeriod p2 = (MutablePeriod) in.readObject();
                Date s = (Date) in.readObject();
                Date e = (Date) in.readObject();
                e.setYear(69);
                System.out.println(p2);
            }
        }
    }
}

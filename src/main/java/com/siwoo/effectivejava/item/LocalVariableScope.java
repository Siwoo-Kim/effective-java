package com.siwoo.effectivejava.item;

import com.siwoo.effectivejava.item3.Elvis;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LocalVariableScope {

    static class Element {

    }

    public static void main(String[] args) {
        List<Element> c = getElements();
//        for (Element e: elements) {
//            .. //to do something
//        }

//        for (Iterator<Element> i = c.iterator(); i.hasNext(); ) {
//            Element e = i.next();
//        };
        for (int i=0, n=c.size(); i<n; i++) {
            System.out.println(c.get(i));
        }
    }

    private static List<Element> getElements() {
        return Arrays.asList(new Element(), new Element(), new Element());
    }
}

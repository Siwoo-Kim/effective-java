package com.siwoo.effectivejava.item87;

import java.io.Serializable;

//use basic serialization.
public class Name implements Serializable {
    private static final long serialVersionUID = 537L;

    /**
     * last name, not null.
     * @serial
     */
    private final String lastName;

    /**
     * first name, not null.
     * @serial
     */
    private final String firstName;

    /**
     * middle name, not null.
     * @serial
     */
    private final String middleName;

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
}

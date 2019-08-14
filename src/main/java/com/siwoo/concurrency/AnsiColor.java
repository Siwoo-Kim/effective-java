package com.siwoo.concurrency;


public enum  AnsiColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m");

    private final String color;

    AnsiColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}

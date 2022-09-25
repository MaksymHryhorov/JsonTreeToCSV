package com;

public class Foo {
    String string;
    Foo(String string) {
        this.string = string;
    }
    @Override
    public int hashCode() {
        return string.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return string.equals(((Foo) obj).string);
    }
}

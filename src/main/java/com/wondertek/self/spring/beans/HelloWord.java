package com.wondertek.self.spring.beans;

public class HelloWord {
    private String name;

    public HelloWord() {
        System.out.println("helloWorld's constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String hello() {
        System.out.println("hello: "+ name);
        return name;
    }
}

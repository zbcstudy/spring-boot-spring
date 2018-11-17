package com.wondertek.self.spring.beans;

public class HelloWorld {

    private String name;

    public HelloWorld() {
        System.out.println("helloWorld's constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("hello: "+ name);
    }
}

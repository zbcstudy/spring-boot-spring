package com.wondertek.self.spring.beans.aop;

import org.springframework.stereotype.Component;

@Component(value = "someService")
public class SomeServiceImpl implements SomeService {

    @Override
    public void doFirst() {
        System.out.println("方法执行");
    }
}

package com.wondertek.self.spring.beans.aop;

/**
 * 用于测试aop功能的类
 * @Author zbc
 * @Date 20:33-2019/1/2
 */
public class MathCalculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int delete(int a, int b) {
        return a - b;
    }
}

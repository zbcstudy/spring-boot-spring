package com.wondertek.self.spring.beans;

import org.springframework.beans.factory.FactoryBean;

/**
 * 通过factorybean的方式创建bean
 */
public class ColorFactoryBean implements FactoryBean<Red> {

    @Override
    public Red getObject() throws Exception {
        return new Red();
    }

    @Override
    public Class<?> getObjectType() {
        return Red.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

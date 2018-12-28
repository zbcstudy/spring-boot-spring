package com.wondertek.self.spring.config;

import com.wondertek.self.spring.beans.HelloWord2;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 通过手工注册的方式将bean注册到容器中
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry beanDefinition注册类
     *                 把所有需要添加到容器中的bean，BeanDefinitionRegistry.registerBeanDefinition()
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition("helloService");
        if (definition) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(HelloWord2.class);
            registry.registerBeanDefinition("helloWord2", rootBeanDefinition);
        }
    }
}

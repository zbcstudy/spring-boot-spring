package com.wondertek.self.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是Linux系统
 */
public class LinuxCondition implements Condition {

    /**
     *
     * @param context 判断条件使用的上下文（环境）
     * @param metadata 注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取IOC容器使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取当前环境信息
        Environment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            System.out.println(profile);
        }
        //获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        //判断容器中的bean注册情况,也可以给容器中注册bean
        boolean cotains = registry.containsBeanDefinition("helloService");

        String property = environment.getProperty("os.name");
        if (property.contains("Windows")) {
            return false;
        }
        return true;
    }
}

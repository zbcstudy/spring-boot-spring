基于注解实现aop

@Import(AspectJAutoProxyRegistrar.class) 向容器中注册bean
@EnableAspectJAutoProxy 
    AnnotationAwareAspectJAutoProxyCreator
        AspectJAwareAdvisorAutoProxyCreator
            AbstractAdvisorAutoProxyCreator
               AbstractAutoProxyCreator  implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware 
               
               bean的名称：internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator

AbstractAutoProxyCreator.setBeanFactory() //自动装配beanFactory
AbstractAutoProxyCreator.postProcessBeforeInstantiation(Class<?> beanClass, String beanName) //后置处理器

AbstractAdvisorAutoProxyCreator.setBeanFactory()--> initBeanFactory()

AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()

    
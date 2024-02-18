package org.example.postprocessor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.example.quoter.impl.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {

    private List<String> beans = new ArrayList<>();

    public ProfilingAnnotationBeanPostProcessor() {
        System.out.println("ProfilingAnnotationBeanPostProcessor created by constructor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            beans.add(beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.contains(beanName)) {
            Class<?> beanClass = bean.getClass();
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                System.out.println("Profiling started");
                long before = System.nanoTime();
                Object result = method.invoke(bean, args);
                long after = System.nanoTime();
                long invokationTime = after - before;
                System.out.println("Method invoked in: " + invokationTime);
                System.out.println("Profiling ended");
                return result;
            });
        }
        return null;
    }
}

package org.spring.learn.contextlistener;

import java.lang.reflect.Method;
import org.spring.learn.quoter.impl.annotations.AfterProxy;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AfterProxyContextListener {
    private final ConfigurableListableBeanFactory beanFactory;

    public AfterProxyContextListener(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @EventListener
    public void listenRefreshEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> originalClass = Class.forName(beanClassName);
                Method[] methods = originalClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(AfterProxy.class)) {
                        Object bean = context.getBean(beanName);
                        Method beanMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        beanMethod.invoke(bean);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

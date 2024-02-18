package org.example.postprocessor;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.logging.Logger;
import org.example.quoter.impl.annotations.InjectLikeOrNot;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class InjectLikeOrNotAnnotationBeanPostProcessor implements BeanPostProcessor {
    public InjectLikeOrNotAnnotationBeanPostProcessor() {
        System.out.println("InjectLikeOrNotAnnotationBeanPostProcessor created by constructor");
    }

    private static final Logger logger = Logger.getLogger(InjectLikeOrNotAnnotationBeanPostProcessor.class.getName());

    private static final Random RANDOM = new Random();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectLikeOrNot annotation = field.getAnnotation(InjectLikeOrNot.class);
            if (annotation != null) {
                int max = annotation.max();
                int min = annotation.min();
                int petals = min + RANDOM.nextInt(max - min);
                boolean like = petals % 2 == 1;
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, like);
                logger.info("Like is set " + like);
            }
        }
        logger.info("Before initialization done on " + bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}

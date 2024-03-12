package org.spring.learn;

import org.spring.learn.quoter.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}
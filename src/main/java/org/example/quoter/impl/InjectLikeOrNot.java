package org.example.quoter.impl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLikeOrNot {
    int min();

    int max();
}

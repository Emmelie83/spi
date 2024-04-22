package com.emmeliejohansson.service;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CurrencyConverterImpl {
    String name() default "";
}

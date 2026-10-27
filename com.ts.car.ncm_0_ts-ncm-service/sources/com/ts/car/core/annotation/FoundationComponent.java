package com.ts.car.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FoundationComponent {
    RuntimeType runtimeType() default RuntimeType.Product;

    String sceneName() default "";

    int sequence() default 100;
}

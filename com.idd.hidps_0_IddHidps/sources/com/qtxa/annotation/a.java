package com.qtxa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.TYPE})
@Repeatable(b.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface a {
    String name();

    String type();
}

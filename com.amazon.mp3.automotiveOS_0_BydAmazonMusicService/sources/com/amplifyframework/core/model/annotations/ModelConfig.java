package com.amplifyframework.core.model.annotations;

import com.amplifyframework.core.model.Model;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelConfig {
    AuthRule[] authRules() default {};

    String listPluralName() default "";

    String pluralName() default "";

    String syncPluralName() default "";

    Model.Type type() default Model.Type.USER;

    int version() default 0;
}

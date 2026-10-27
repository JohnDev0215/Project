package com.amplifyframework.core.model.annotations;

import com.amplifyframework.core.model.Model;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BelongsTo {
    String targetName() default "";

    String[] targetNames() default {};

    Class<? extends Model> type();
}

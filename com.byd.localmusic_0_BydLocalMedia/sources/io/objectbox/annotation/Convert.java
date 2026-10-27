package io.objectbox.annotation;

import io.objectbox.converter.PropertyConverter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface Convert {
    Class<? extends PropertyConverter> converter();

    Class dbType();
}

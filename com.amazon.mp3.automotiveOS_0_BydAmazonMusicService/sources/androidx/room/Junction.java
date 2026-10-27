package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes2.dex */
@Target({})
@Retention(RetentionPolicy.CLASS)
public @interface Junction {
    String entityColumn() default "";

    String parentColumn() default "";

    Class<?> value();
}

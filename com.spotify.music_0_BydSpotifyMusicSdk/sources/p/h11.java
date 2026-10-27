package p;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface h11 {
    boolean hasBody() default false;

    String method();

    String path() default "";
}

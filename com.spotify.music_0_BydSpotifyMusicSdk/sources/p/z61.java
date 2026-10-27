package p;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface z61 {
    boolean ignore() default false;

    String name() default "\u0000";
}

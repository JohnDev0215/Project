package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Getter {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnyAnnotation {
    }

    boolean lazy() default false;

    AnyAnnotation[] onMethod() default {};

    AccessLevel value() default AccessLevel.PUBLIC;
}

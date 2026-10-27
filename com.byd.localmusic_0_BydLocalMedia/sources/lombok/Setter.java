package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Setter {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnyAnnotation {
    }

    AnyAnnotation[] onMethod() default {};

    AnyAnnotation[] onParam() default {};

    AccessLevel value() default AccessLevel.PUBLIC;
}

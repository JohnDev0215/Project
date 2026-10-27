package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface NoArgsConstructor {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnyAnnotation {
    }

    AccessLevel access() default AccessLevel.PUBLIC;

    boolean force() default false;

    AnyAnnotation[] onConstructor() default {};

    String staticName() default "";
}

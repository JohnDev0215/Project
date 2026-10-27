package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
public @interface Builder {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Default {
    }

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObtainVia {
        String field() default "";

        boolean isStatic() default false;

        String method() default "";
    }

    AccessLevel access() default AccessLevel.PUBLIC;

    String buildMethodName() default "build";

    String builderClassName() default "";

    String builderMethodName() default "builder";

    String setterPrefix() default "";

    boolean toBuilder() default false;
}

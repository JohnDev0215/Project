package lombok.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.AccessLevel;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface FieldNameConstants {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Exclude {
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Include {
    }

    boolean asEnum() default false;

    String innerTypeName() default "";

    AccessLevel level() default AccessLevel.PUBLIC;

    boolean onlyExplicitlyIncluded() default false;
}

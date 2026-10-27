package javax.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Method from annotation default annotation not found: defaultValue */
/* JADX INFO: loaded from: classes7.dex */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlElement {

    public static final class DEFAULT {
    }

    String name() default "##default";

    String namespace() default "##default";

    boolean nillable() default false;

    boolean required() default false;

    Class type() default DEFAULT.class;
}

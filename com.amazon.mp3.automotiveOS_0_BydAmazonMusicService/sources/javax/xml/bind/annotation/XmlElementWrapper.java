package javax.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Method from annotation default annotation not found: required */
/* JADX INFO: loaded from: classes7.dex */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlElementWrapper {
    String name() default "##default";

    String namespace() default "##default";

    boolean nillable() default false;
}

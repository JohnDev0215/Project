package javax.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Method from annotation default annotation not found: factoryClass */
/* JADX WARN: Method from annotation default annotation not found: factoryMethod */
/* JADX WARN: Method from annotation default annotation not found: namespace */
/* JADX INFO: loaded from: classes7.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlType {

    public static final class DEFAULT {
    }

    String name() default "##default";

    String[] propOrder() default {""};
}

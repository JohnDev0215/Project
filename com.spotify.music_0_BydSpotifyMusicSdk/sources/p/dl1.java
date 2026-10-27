package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class dl1 extends g00 {
    public final com.squareup.moshi.o a;

    public dl1(com.squareup.moshi.o oVar) {
        this.a = oVar;
    }

    public static Set c(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(s71.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : Collections.emptySet();
    }

    @Override // p.g00
    public final h00 a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, e52 e52Var) {
        return new gl1(this.a.e(type, c(annotationArr)));
    }

    @Override // p.g00
    public final h00 b(Type type, Annotation[] annotationArr, e52 e52Var) {
        return new hl1(this.a.e(type, c(annotationArr)));
    }
}

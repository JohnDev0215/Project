package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class yq1 extends g00 {
    public static final yq1 a = new yq1();

    @Override // p.g00
    public final h00 b(Type type, Annotation[] annotationArr, e52 e52Var) {
        if (bk0.x(type) != Optional.class) {
            return null;
        }
        return new p01(26, e52Var.e(bk0.w(0, (ParameterizedType) type), annotationArr));
    }
}

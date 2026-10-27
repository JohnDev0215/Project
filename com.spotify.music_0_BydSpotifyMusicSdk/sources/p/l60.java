package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class l60 extends kp {
    public final Executor a;

    public l60(Executor executor) {
        this.a = executor;
    }

    @Override // p.kp
    public final lp a(Type type, Annotation[] annotationArr) {
        if (bk0.x(type) != jp.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return new kk0(this, bk0.w(0, (ParameterizedType) type), bk0.F(annotationArr, sd2.class) ? null : this.a, 25);
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}

package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public final class ev extends kp {
    public static final ev a = new ev();

    @Override // p.kp
    public final lp a(Type type, Annotation[] annotationArr) {
        if (bk0.x(type) != CompletableFuture.class) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type typeW = bk0.w(0, (ParameterizedType) type);
        if (bk0.x(typeW) != u42.class) {
            return new hk0(28, typeW);
        }
        if (typeW instanceof ParameterizedType) {
            return new oh1(28, bk0.w(0, (ParameterizedType) typeW));
        }
        throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
    }
}

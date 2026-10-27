package h;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: ServiceMethod.java */
/* JADX INFO: loaded from: classes.dex */
abstract class v<T> {
    v() {
    }

    static <T> v<T> b(u uVar, Method method) {
        s sVarB = s.b(uVar, method);
        Type genericReturnType = method.getGenericReturnType();
        if (y.j(genericReturnType)) {
            throw y.m(method, "Method return type must not include a type variable or wildcard: %s", genericReturnType);
        }
        if (genericReturnType != Void.TYPE) {
            return k.f(uVar, method, sVarB);
        }
        throw y.m(method, "Service methods cannot return void.", new Object[0]);
    }

    @Nullable
    abstract T a(Object[] objArr);
}

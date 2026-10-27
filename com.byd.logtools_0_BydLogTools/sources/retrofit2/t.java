package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: ServiceMethod.java */
/* JADX INFO: loaded from: classes.dex */
abstract class t<T> {
    t() {
    }

    static <T> t<T> b(s sVar, Method method) {
        q qVarB = q.b(sVar, method);
        Type genericReturnType = method.getGenericReturnType();
        if (w.j(genericReturnType)) {
            throw w.m(method, "Method return type must not include a type variable or wildcard: %s", genericReturnType);
        }
        if (genericReturnType != Void.TYPE) {
            return j.f(sVar, method, qVarB);
        }
        throw w.m(method, "Service methods cannot return void.", new Object[0]);
    }

    @Nullable
    abstract T a(Object[] objArr);
}

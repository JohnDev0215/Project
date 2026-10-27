package p;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class qo2<T> {
    public final Class a;
    public final Type b;
    public final int c;

    public qo2() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type typeA = com.google.gson.internal.a.a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        this.b = typeA;
        this.a = com.google.gson.internal.a.f(typeA);
        this.c = typeA.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof qo2) {
            if (com.google.gson.internal.a.d(this.b, ((qo2) obj).b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return com.google.gson.internal.a.h(this.b);
    }

    public qo2(Type type) {
        type.getClass();
        Type typeA = com.google.gson.internal.a.a(type);
        this.b = typeA;
        this.a = com.google.gson.internal.a.f(typeA);
        this.c = typeA.hashCode();
    }
}

package spi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import spi.b;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vb<T> {
    public final Class<? super T> a;
    public final Type b;
    public final int c;

    public vb() {
        Type genericSuperclass = vb.class.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type typeA = b.a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        this.b = typeA;
        this.a = (Class<? super T>) b.e(typeA);
        this.c = typeA.hashCode();
    }

    public static vb<?> a(Type type, Type... typeArr) {
        return new vb<>(new b.C0018b(null, type, typeArr));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof vb) && b.c(this.b, ((vb) obj).b);
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return b.i(this.b);
    }

    public vb(Type type) {
        Objects.requireNonNull(type);
        Type typeA = b.a(type);
        this.b = typeA;
        this.a = (Class<? super T>) b.e(typeA);
        this.c = typeA.hashCode();
    }
}

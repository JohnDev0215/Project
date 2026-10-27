package com.google.gson.s;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Class<? super T> f4153a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Type f4154b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f4155c;

    protected a() {
        Type typeD = d(a.class);
        this.f4154b = typeD;
        this.f4153a = (Class<? super T>) C$Gson$Types.k(typeD);
        this.f4155c = typeD.hashCode();
    }

    public static <T> a<T> a(Class<T> cls) {
        return new a<>(cls);
    }

    public static a<?> b(Type type) {
        return new a<>(type);
    }

    static Type d(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> c() {
        return this.f4153a;
    }

    public final Type e() {
        return this.f4154b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && C$Gson$Types.f(this.f4154b, ((a) obj).f4154b);
    }

    public final int hashCode() {
        return this.f4155c;
    }

    public final String toString() {
        return C$Gson$Types.u(this.f4154b);
    }

    a(Type type) {
        Type typeB = C$Gson$Types.b((Type) com.google.gson.internal.a.b(type));
        this.f4154b = typeB;
        this.f4153a = (Class<? super T>) C$Gson$Types.k(typeB);
        this.f4155c = typeB.hashCode();
    }
}

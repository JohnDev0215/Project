package com.google.gson.v;

import com.google.gson.internal.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Class<? super T> f4602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Type f4603b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f4604c;

    protected a() {
        Type typeD = d(getClass());
        this.f4603b = typeD;
        this.f4602a = (Class<? super T>) b.k(typeD);
        this.f4604c = typeD.hashCode();
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
        return b.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> c() {
        return this.f4602a;
    }

    public final Type e() {
        return this.f4603b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b.f(this.f4603b, ((a) obj).f4603b);
    }

    public final int hashCode() {
        return this.f4604c;
    }

    public final String toString() {
        return b.u(this.f4603b);
    }

    a(Type type) {
        Type typeB = b.b((Type) com.google.gson.internal.a.b(type));
        this.f4603b = typeB;
        this.f4602a = (Class<? super T>) b.k(typeB);
        this.f4604c = typeB.hashCode();
    }
}

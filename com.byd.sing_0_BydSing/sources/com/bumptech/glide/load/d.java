package com.bumptech.glide.load;

import com.bumptech.glide.m.j;
import java.security.MessageDigest;

/* JADX INFO: compiled from: Option.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b<Object> f2734a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f2735b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b<T> f2736c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2737d;
    private volatile byte[] e;

    /* JADX INFO: compiled from: Option.java */
    class a implements b<Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.d.b
        public void a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    }

    /* JADX INFO: compiled from: Option.java */
    public interface b<T> {
        void a(byte[] bArr, T t, MessageDigest messageDigest);
    }

    private d(String str, T t, b<T> bVar) {
        this.f2737d = j.b(str);
        this.f2735b = t;
        this.f2736c = (b) j.d(bVar);
    }

    public static <T> d<T> a(String str, T t, b<T> bVar) {
        return new d<>(str, t, bVar);
    }

    private static <T> b<T> b() {
        return (b<T>) f2734a;
    }

    private byte[] d() {
        if (this.e == null) {
            this.e = this.f2737d.getBytes(c.f2733a);
        }
        return this.e;
    }

    public static <T> d<T> e(String str) {
        return new d<>(str, null, b());
    }

    public static <T> d<T> f(String str, T t) {
        return new d<>(str, t, b());
    }

    public T c() {
        return this.f2735b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            return this.f2737d.equals(((d) obj).f2737d);
        }
        return false;
    }

    public void g(T t, MessageDigest messageDigest) {
        this.f2736c.a(d(), t, messageDigest);
    }

    public int hashCode() {
        return this.f2737d.hashCode();
    }

    public String toString() {
        return "Option{key='" + this.f2737d + "'}";
    }
}

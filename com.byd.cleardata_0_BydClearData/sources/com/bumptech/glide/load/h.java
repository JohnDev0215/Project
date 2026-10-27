package com.bumptech.glide.load;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b<Object> f2012a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f2013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b<T> f2014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2015d;
    private volatile byte[] e;

    class a implements b<Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.h.b
        public void a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    }

    public interface b<T> {
        void a(byte[] bArr, T t, MessageDigest messageDigest);
    }

    private h(String str, T t, b<T> bVar) {
        this.f2015d = com.bumptech.glide.t.j.b(str);
        this.f2013b = t;
        this.f2014c = (b) com.bumptech.glide.t.j.d(bVar);
    }

    public static <T> h<T> a(String str, T t, b<T> bVar) {
        return new h<>(str, t, bVar);
    }

    private static <T> b<T> b() {
        return (b<T>) f2012a;
    }

    private byte[] d() {
        if (this.e == null) {
            this.e = this.f2015d.getBytes(g.f2011a);
        }
        return this.e;
    }

    public static <T> h<T> e(String str) {
        return new h<>(str, null, b());
    }

    public static <T> h<T> f(String str, T t) {
        return new h<>(str, t, b());
    }

    public T c() {
        return this.f2013b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof h) {
            return this.f2015d.equals(((h) obj).f2015d);
        }
        return false;
    }

    public void g(T t, MessageDigest messageDigest) {
        this.f2014c.a(d(), t, messageDigest);
    }

    public int hashCode() {
        return this.f2015d.hashCode();
    }

    public String toString() {
        return "Option{key='" + this.f2015d + "'}";
    }
}

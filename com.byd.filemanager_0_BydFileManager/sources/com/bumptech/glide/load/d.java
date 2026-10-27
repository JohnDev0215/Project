package com.bumptech.glide.load;

import com.bumptech.glide.o.j;
import java.security.MessageDigest;

/* JADX INFO: compiled from: Option.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b<Object> f2927a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f2928b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b<T> f2929c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2930d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile byte[] f2931e;

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
        this.f2930d = j.b(str);
        this.f2928b = t;
        this.f2929c = (b) j.d(bVar);
    }

    public static <T> d<T> a(String str, T t, b<T> bVar) {
        return new d<>(str, t, bVar);
    }

    private static <T> b<T> b() {
        return (b<T>) f2927a;
    }

    private byte[] d() {
        if (this.f2931e == null) {
            this.f2931e = this.f2930d.getBytes(c.f2926a);
        }
        return this.f2931e;
    }

    public static <T> d<T> e(String str) {
        return new d<>(str, null, b());
    }

    public static <T> d<T> f(String str, T t) {
        return new d<>(str, t, b());
    }

    public T c() {
        return this.f2928b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            return this.f2930d.equals(((d) obj).f2930d);
        }
        return false;
    }

    public void g(T t, MessageDigest messageDigest) {
        this.f2929c.a(d(), t, messageDigest);
    }

    public int hashCode() {
        return this.f2930d.hashCode();
    }

    public String toString() {
        return "Option{key='" + this.f2930d + "'}";
    }
}

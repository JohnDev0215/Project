package n1;

import java.security.MessageDigest;

/* JADX INFO: compiled from: Option.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final b<Object> f8506e = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T f8507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b<T> f8508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f8509c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile byte[] f8510d;

    /* JADX INFO: compiled from: Option.java */
    class a implements b<Object> {
        a() {
        }

        @Override // n1.h.b
        public void a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    }

    /* JADX INFO: compiled from: Option.java */
    public interface b<T> {
        void a(byte[] bArr, T t6, MessageDigest messageDigest);
    }

    private h(String str, T t6, b<T> bVar) {
        this.f8509c = k2.j.b(str);
        this.f8507a = t6;
        this.f8508b = (b) k2.j.d(bVar);
    }

    public static <T> h<T> a(String str, T t6, b<T> bVar) {
        return new h<>(str, t6, bVar);
    }

    private static <T> b<T> b() {
        return (b<T>) f8506e;
    }

    private byte[] d() {
        if (this.f8510d == null) {
            this.f8510d = this.f8509c.getBytes(f.f8504a);
        }
        return this.f8510d;
    }

    public static <T> h<T> e(String str) {
        return new h<>(str, null, b());
    }

    public static <T> h<T> f(String str, T t6) {
        return new h<>(str, t6, b());
    }

    public T c() {
        return this.f8507a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof h) {
            return this.f8509c.equals(((h) obj).f8509c);
        }
        return false;
    }

    public void g(T t6, MessageDigest messageDigest) {
        this.f8508b.a(d(), t6, messageDigest);
    }

    public int hashCode() {
        return this.f8509c.hashCode();
    }

    public String toString() {
        return "Option{key='" + this.f8509c + "'}";
    }
}

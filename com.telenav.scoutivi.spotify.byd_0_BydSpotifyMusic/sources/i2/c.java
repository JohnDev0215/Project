package i2;

import i2.d;

/* JADX INFO: compiled from: NoTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class c<R> implements d<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c<?> f6917a = new c<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final e<?> f6918b = new a();

    /* JADX INFO: compiled from: NoTransition.java */
    public static class a<R> implements e<R> {
        @Override // i2.e
        public d<R> a(n1.a aVar, boolean z6) {
            return c.f6917a;
        }
    }

    public static <R> d<R> b() {
        return f6917a;
    }

    public static <R> e<R> c() {
        return (e<R>) f6918b;
    }

    @Override // i2.d
    public boolean a(Object obj, d.a aVar) {
        return false;
    }
}

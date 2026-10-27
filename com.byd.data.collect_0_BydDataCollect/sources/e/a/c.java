package e.a;

/* JADX INFO: compiled from: Flowable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final int f4842a = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    public static int a() {
        return f4842a;
    }

    public final c<T> b() {
        return c(a(), false, true);
    }

    public final c<T> c(int i2, boolean z, boolean z2) {
        e.a.n.b.b.d(i2, "bufferSize");
        return e.a.p.a.k(new e.a.n.e.a.c(this, i2, z2, z, e.a.n.b.a.f4871c));
    }

    public final c<T> d() {
        return e.a.p.a.k(new e.a.n.e.a.d(this));
    }

    public final c<T> e() {
        return e.a.p.a.k(new e.a.n.e.a.f(this));
    }
}

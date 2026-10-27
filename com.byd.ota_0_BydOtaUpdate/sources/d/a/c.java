package d.a;

/* JADX INFO: compiled from: Flowable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> implements g.b.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3159a = Math.max(16, Integer.getInteger("rx2.buffer-size", 128).intValue());

    public static int a() {
        return f3159a;
    }

    public final c<T> b() {
        return c(a(), false, true);
    }

    public final c<T> c(int i2, boolean z, boolean z2) {
        d.a.p.b.b.e(i2, "bufferSize");
        return d.a.r.a.j(new d.a.p.e.a.c(this, i2, z2, z, d.a.p.b.a.f3172c));
    }

    public final c<T> d() {
        return d.a.r.a.j(new d.a.p.e.a.d(this));
    }

    public final c<T> e() {
        return d.a.r.a.j(new d.a.p.e.a.f(this));
    }
}

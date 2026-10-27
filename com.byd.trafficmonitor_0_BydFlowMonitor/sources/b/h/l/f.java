package b.h.l;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> extends e<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2579c;

    public f(int i2) {
        super(i2);
        this.f2579c = new Object();
    }

    @Override // b.h.l.e, b.h.l.d
    public boolean a(T t) {
        boolean zA;
        synchronized (this.f2579c) {
            zA = super.a(t);
        }
        return zA;
    }

    @Override // b.h.l.e, b.h.l.d
    public T b() {
        T t;
        synchronized (this.f2579c) {
            t = (T) super.b();
        }
        return t;
    }
}

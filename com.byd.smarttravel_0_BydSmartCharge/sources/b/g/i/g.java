package b.g.i;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1089c;

    public g(int i) {
        super(i);
        this.f1089c = new Object();
    }

    @Override // b.g.i.f, b.g.i.e
    public boolean a(T t) {
        boolean zA;
        synchronized (this.f1089c) {
            zA = super.a(t);
        }
        return zA;
    }

    @Override // b.g.i.f, b.g.i.e
    public T b() {
        T t;
        synchronized (this.f1089c) {
            t = (T) super.b();
        }
        return t;
    }
}

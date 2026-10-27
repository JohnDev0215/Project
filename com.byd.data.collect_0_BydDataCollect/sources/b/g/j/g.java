package b.g.j;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f2663c;

    public g(int i2) {
        super(i2);
        this.f2663c = new Object();
    }

    @Override // b.g.j.f, b.g.j.e
    public boolean a(T t) {
        boolean zA;
        synchronized (this.f2663c) {
            zA = super.a(t);
        }
        return zA;
    }

    @Override // b.g.j.f, b.g.j.e
    public T b() {
        T t;
        synchronized (this.f2663c) {
            t = (T) super.b();
        }
        return t;
    }
}

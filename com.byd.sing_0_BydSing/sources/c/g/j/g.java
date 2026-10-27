package c.g.j;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f2273c;

    public g(int i) {
        super(i);
        this.f2273c = new Object();
    }

    @Override // c.g.j.f, c.g.j.e
    public boolean a(T t) {
        boolean zA;
        synchronized (this.f2273c) {
            zA = super.a(t);
        }
        return zA;
    }

    @Override // c.g.j.f, c.g.j.e
    public T b() {
        T t;
        synchronized (this.f2273c) {
            t = (T) super.b();
        }
        return t;
    }
}

package k0;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f3583c;

    public g(int i3) {
        super(i3);
        this.f3583c = new Object();
    }

    @Override // k0.f, k0.e
    public boolean a(T t3) {
        boolean zA;
        synchronized (this.f3583c) {
            zA = super.a(t3);
        }
        return zA;
    }

    @Override // k0.f, k0.e
    public T b() {
        T t3;
        synchronized (this.f3583c) {
            t3 = (T) super.b();
        }
        return t3;
    }
}

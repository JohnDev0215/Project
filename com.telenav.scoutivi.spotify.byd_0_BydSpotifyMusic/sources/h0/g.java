package h0;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f6593c;

    public g(int i7) {
        super(i7);
        this.f6593c = new Object();
    }

    @Override // h0.f, h0.e
    public boolean a(T t6) {
        boolean zA;
        synchronized (this.f6593c) {
            zA = super.a(t6);
        }
        return zA;
    }

    @Override // h0.f, h0.e
    public T b() {
        T t6;
        synchronized (this.f6593c) {
            t6 = (T) super.b();
        }
        return t6;
    }
}

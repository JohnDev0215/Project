package n0;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6510c;

    public g(int i8) {
        super(i8);
        this.f6510c = new Object();
    }

    @Override // n0.f, n0.e
    public boolean a(T t7) {
        boolean zA;
        synchronized (this.f6510c) {
            zA = super.a(t7);
        }
        return zA;
    }

    @Override // n0.f, n0.e
    public T b() {
        T t7;
        synchronized (this.f6510c) {
            t7 = (T) super.b();
        }
        return t7;
    }
}

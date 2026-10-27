package a.g.j;

/* JADX INFO: loaded from: classes.dex */
public class g<T> extends f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f370c;

    public g(int i) {
        super(i);
        this.f370c = new Object();
    }

    @Override // a.g.j.f, a.g.j.e
    public boolean a(T t) {
        boolean zA;
        synchronized (this.f370c) {
            zA = super.a(t);
        }
        return zA;
    }

    @Override // a.g.j.f, a.g.j.e
    public T b() {
        T t;
        synchronized (this.f370c) {
            t = (T) super.b();
        }
        return t;
    }
}

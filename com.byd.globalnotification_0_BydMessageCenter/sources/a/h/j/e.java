package a.h.j;

/* JADX INFO: loaded from: classes.dex */
public class e<T> extends d<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f805c;

    public e(int i) {
        super(i);
        this.f805c = new Object();
    }

    @Override // a.h.j.d
    public T a() {
        T t;
        synchronized (this.f805c) {
            t = (T) super.a();
        }
        return t;
    }

    @Override // a.h.j.d
    public boolean b(T t) {
        boolean zB;
        synchronized (this.f805c) {
            zB = super.b(t);
        }
        return zB;
    }
}

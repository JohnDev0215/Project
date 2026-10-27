package w1;

import k2.j;
import q1.v;

/* JADX INFO: compiled from: SimpleResource.java */
/* JADX INFO: loaded from: classes.dex */
public class b<T> implements v<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final T f9707e;

    public b(T t6) {
        this.f9707e = (T) j.d(t6);
    }

    @Override // q1.v
    public void a() {
    }

    @Override // q1.v
    public final int c() {
        return 1;
    }

    @Override // q1.v
    public Class<T> e() {
        return (Class<T>) this.f9707e.getClass();
    }

    @Override // q1.v
    public final T get() {
        return this.f9707e;
    }
}

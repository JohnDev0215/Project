package e.a.k;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ReferenceDisposable.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d<T> extends AtomicReference<T> implements b {
    private static final long serialVersionUID = 6537757548749041217L;

    d(T t) {
        super(e.a.n.b.b.c(t, "value is null"));
    }

    public final boolean a() {
        return get() == null;
    }

    protected abstract void b(T t);

    @Override // e.a.k.b
    public final void d() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        b(andSet);
    }
}

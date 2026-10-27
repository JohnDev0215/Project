package t4;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ReferenceDisposable.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d<T> extends AtomicReference<T> implements b {
    private static final long serialVersionUID = 6537757548749041217L;

    d(T t6) {
        super(x4.b.c(t6, "value is null"));
    }

    protected abstract void a(T t6);

    @Override // t4.b
    public final void dispose() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        a(andSet);
    }

    @Override // t4.b
    public final boolean isDisposed() {
        return get() == null;
    }
}

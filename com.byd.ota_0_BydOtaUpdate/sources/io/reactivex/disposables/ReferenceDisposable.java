package io.reactivex.disposables;

import d.a.m.b;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ReferenceDisposable<T> extends AtomicReference<T> implements b {
    private static final long serialVersionUID = 6537757548749041217L;

    public ReferenceDisposable(T t) {
        super(d.a.p.b.b.d(t, "value is null"));
    }

    @Override // d.a.m.b
    public final void dispose() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        onDisposed(andSet);
    }

    @Override // d.a.m.b
    public final boolean isDisposed() {
        return get() == null;
    }

    public abstract void onDisposed(T t);
}

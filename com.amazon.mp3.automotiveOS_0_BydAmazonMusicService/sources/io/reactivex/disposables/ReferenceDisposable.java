package io.reactivex.disposables;

import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
abstract class ReferenceDisposable<T> extends AtomicReference<T> implements Disposable {
    protected abstract void onDisposed(T t);

    ReferenceDisposable(T t) {
        super(ObjectHelper.requireNonNull(t, "value is null"));
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        onDisposed(andSet);
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return get() == null;
    }
}

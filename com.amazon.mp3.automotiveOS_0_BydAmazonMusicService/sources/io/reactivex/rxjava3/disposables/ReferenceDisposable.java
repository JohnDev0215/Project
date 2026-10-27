package io.reactivex.rxjava3.disposables;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
abstract class ReferenceDisposable<T> extends AtomicReference<T> implements Disposable {
    protected abstract void onDisposed(T value);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ReferenceDisposable(T value) {
        super(value);
        Objects.requireNonNull(value, "value is null");
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        onDisposed(andSet);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return get() == null;
    }
}

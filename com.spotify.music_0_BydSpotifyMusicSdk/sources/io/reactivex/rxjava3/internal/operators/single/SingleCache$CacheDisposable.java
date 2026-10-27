package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class SingleCache$CacheDisposable<T> extends AtomicBoolean implements Disposable {
    private static final long serialVersionUID = 7514387411091976596L;
    final SingleObserver<? super T> downstream;
    final b parent;

    public SingleCache$CacheDisposable(SingleObserver singleObserver, b bVar) {
        this.downstream = singleObserver;
        this.parent = bVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (compareAndSet(false, true)) {
            this.parent.c(this);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get();
    }
}

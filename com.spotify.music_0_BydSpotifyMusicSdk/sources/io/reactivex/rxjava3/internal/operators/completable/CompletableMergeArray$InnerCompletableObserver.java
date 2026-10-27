package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class CompletableMergeArray$InnerCompletableObserver extends AtomicInteger implements CompletableObserver, Disposable {
    private static final long serialVersionUID = -8360547806504310570L;
    final CompletableObserver downstream;
    final AtomicBoolean once;
    final io.reactivex.rxjava3.disposables.a set;

    public CompletableMergeArray$InnerCompletableObserver(CompletableObserver completableObserver, AtomicBoolean atomicBoolean, io.reactivex.rxjava3.disposables.a aVar, int i) {
        this.downstream = completableObserver;
        this.once = atomicBoolean;
        this.set = aVar;
        lazySet(i);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.set.dispose();
        this.once.set(true);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.set.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        if (decrementAndGet() == 0) {
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        this.set.dispose();
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.b(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        this.set.c(disposable);
    }
}

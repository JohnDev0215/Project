package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.fuseable.AbstractEmptyQueueFuseable;

/* JADX INFO: loaded from: classes7.dex */
public final class ObservableFromCompletable$FromCompletableObserver<T> extends AbstractEmptyQueueFuseable<T> implements CompletableObserver {
    final Observer<? super T> downstream;
    Disposable upstream;

    public ObservableFromCompletable$FromCompletableObserver(Observer<? super T> downstream) {
        this.downstream = downstream;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.AbstractEmptyQueueFuseable, io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        this.upstream = DisposableHelper.DISPOSED;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.AbstractEmptyQueueFuseable, io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.validate(this.upstream, d)) {
            this.upstream = d;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver, io.reactivex.rxjava3.core.MaybeObserver
    public void onComplete() {
        this.upstream = DisposableHelper.DISPOSED;
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onError(Throwable e) {
        this.upstream = DisposableHelper.DISPOSED;
        this.downstream.onError(e);
    }
}

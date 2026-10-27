package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.f;

/* JADX INFO: loaded from: classes.dex */
public interface ObservableEmitter<T> extends Emitter<T> {
    boolean isDisposed();

    @Override // io.reactivex.rxjava3.core.Emitter
    /* synthetic */ void onComplete();

    @Override // io.reactivex.rxjava3.core.Emitter
    /* synthetic */ void onError(Throwable th);

    @Override // io.reactivex.rxjava3.core.Emitter
    /* synthetic */ void onNext(Object obj);

    ObservableEmitter<T> serialize();

    void setCancellable(f fVar);

    void setDisposable(Disposable disposable);

    boolean tryOnError(Throwable th);
}

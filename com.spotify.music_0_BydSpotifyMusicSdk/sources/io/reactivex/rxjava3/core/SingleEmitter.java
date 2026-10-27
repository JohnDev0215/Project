package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.f;

/* JADX INFO: loaded from: classes.dex */
public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(Throwable th);

    void onSuccess(T t);

    void setCancellable(f fVar);

    void setDisposable(Disposable disposable);

    boolean tryOnError(Throwable th);
}

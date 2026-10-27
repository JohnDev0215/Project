package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.functions.Cancellable;

/* JADX INFO: loaded from: classes7.dex */
public interface MaybeEmitter<T> {
    void onComplete();

    void onError(Throwable t);

    void onSuccess(T t);

    void setCancellable(Cancellable c);

    boolean tryOnError(Throwable t);
}

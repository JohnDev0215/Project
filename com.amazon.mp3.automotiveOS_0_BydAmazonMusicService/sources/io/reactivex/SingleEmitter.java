package io.reactivex;

/* JADX INFO: loaded from: classes7.dex */
public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(Throwable th);

    void onSuccess(T t);

    boolean tryOnError(Throwable th);
}

package com.spotify.esperanto.esperantoimpl;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class NativeSingleObserver<T> implements SingleObserver<T> {
    private final long nThis;

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public native void onError(Throwable th);

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public native void onSubscribe(Disposable disposable);

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public native void onSuccess(T t);
}

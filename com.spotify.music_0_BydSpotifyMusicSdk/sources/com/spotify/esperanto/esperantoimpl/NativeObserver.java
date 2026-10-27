package com.spotify.esperanto.esperantoimpl;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class NativeObserver<T> implements Observer<T> {
    private final long nThis;

    public final native void destroy();

    @Override // io.reactivex.rxjava3.core.Observer
    public native void onComplete();

    @Override // io.reactivex.rxjava3.core.Observer
    public native void onError(Throwable th);

    @Override // io.reactivex.rxjava3.core.Observer
    public native void onNext(T t);

    @Override // io.reactivex.rxjava3.core.Observer
    public native void onSubscribe(Disposable disposable);
}

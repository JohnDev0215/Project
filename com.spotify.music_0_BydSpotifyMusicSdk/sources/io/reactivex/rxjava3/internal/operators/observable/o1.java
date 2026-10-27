package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class o1 extends Observable implements io.reactivex.rxjava3.operators.e {
    public static final o1 c = new o1();

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return null;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        observer.onSubscribe(EmptyDisposable.c);
        observer.onComplete();
    }
}

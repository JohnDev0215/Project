package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class c4 implements Observer {
    public final ObservableSequenceEqual$EqualCoordinator c;
    public final io.reactivex.rxjava3.operators.h e;
    public final int k;
    public volatile boolean l;
    public Throwable m;

    public c4(ObservableSequenceEqual$EqualCoordinator observableSequenceEqual$EqualCoordinator, int i, int i2) {
        this.c = observableSequenceEqual$EqualCoordinator;
        this.k = i;
        this.e = new io.reactivex.rxjava3.operators.h(i2);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.l = true;
        this.c.a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.m = th;
        this.l = true;
        this.c.a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.e.offer(obj);
        this.c.a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        this.c.resources.a(this.k, disposable);
    }
}

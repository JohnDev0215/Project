package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class w3 extends a {
    public final io.reactivex.rxjava3.functions.p e;
    public final long k;

    public w3(Observable observable, long j, io.reactivex.rxjava3.functions.p pVar) {
        super(observable);
        this.e = pVar;
        this.k = j;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        new ObservableRetryPredicate$RepeatObserver(observer, this.k, this.e, sequentialDisposable, this.c).a();
    }
}

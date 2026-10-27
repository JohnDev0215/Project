package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class d4 extends Observable {
    public final ObservableSource c;
    public final ObservableSource e;
    public final io.reactivex.rxjava3.functions.d k;
    public final int l;

    public d4(ObservableSource observableSource, ObservableSource observableSource2, io.reactivex.rxjava3.functions.d dVar, int i) {
        this.c = observableSource;
        this.e = observableSource2;
        this.k = dVar;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableSequenceEqual$EqualCoordinator observableSequenceEqual$EqualCoordinator = new ObservableSequenceEqual$EqualCoordinator(observer, this.l, this.c, this.e, this.k);
        observer.onSubscribe(observableSequenceEqual$EqualCoordinator);
        observableSequenceEqual$EqualCoordinator.subscribe();
    }
}

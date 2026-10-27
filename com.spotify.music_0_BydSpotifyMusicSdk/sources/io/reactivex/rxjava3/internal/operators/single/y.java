package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class y extends Observable {
    public final SingleSource c;
    public final io.reactivex.rxjava3.functions.o e;

    public y(SingleSource singleSource, io.reactivex.rxjava3.functions.o oVar) {
        this.c = singleSource;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new SingleFlatMapIterableObservable$FlatMapIterableObserver(observer, this.e));
    }
}

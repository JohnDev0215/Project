package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class z1 extends Observable {
    public final Object c;
    public final ObservableGroupBy$State e;

    public z1(Object obj, ObservableGroupBy$State observableGroupBy$State) {
        this.c = obj;
        this.e = observableGroupBy$State;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.e.subscribe(observer);
    }
}

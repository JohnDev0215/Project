package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class v4 extends a {
    public final ObservableSource e;

    public v4(ObservableSource observableSource, ObservableSource observableSource2) {
        super(observableSource);
        this.e = observableSource2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableTakeUntil$TakeUntilMainObserver observableTakeUntil$TakeUntilMainObserver = new ObservableTakeUntil$TakeUntilMainObserver(observer);
        observer.onSubscribe(observableTakeUntil$TakeUntilMainObserver);
        this.e.subscribe(observableTakeUntil$TakeUntilMainObserver.otherObserver);
        this.c.subscribe(observableTakeUntil$TakeUntilMainObserver);
    }
}

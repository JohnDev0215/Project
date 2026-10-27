package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class a3 extends a {
    public final SingleSource e;

    public a3(Observable observable, SingleSource singleSource) {
        super(observable);
        this.e = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableMergeWithSingle$MergeWithObserver observableMergeWithSingle$MergeWithObserver = new ObservableMergeWithSingle$MergeWithObserver(observer);
        observer.onSubscribe(observableMergeWithSingle$MergeWithObserver);
        this.c.subscribe(observableMergeWithSingle$MergeWithObserver);
        this.e.subscribe(observableMergeWithSingle$MergeWithObserver.otherObserver);
    }
}

package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class z2 extends a {
    public final MaybeSource e;

    public z2(Observable observable, MaybeSource maybeSource) {
        super(observable);
        this.e = maybeSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableMergeWithMaybe$MergeWithObserver observableMergeWithMaybe$MergeWithObserver = new ObservableMergeWithMaybe$MergeWithObserver(observer);
        observer.onSubscribe(observableMergeWithMaybe$MergeWithObserver);
        this.c.subscribe(observableMergeWithMaybe$MergeWithObserver);
        this.e.subscribe(observableMergeWithMaybe$MergeWithObserver.otherObserver);
    }
}

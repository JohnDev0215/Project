package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class y2 extends a {
    public final CompletableSource e;

    public y2(Observable observable, CompletableSource completableSource) {
        super(observable);
        this.e = completableSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableMergeWithCompletable$MergeWithObserver observableMergeWithCompletable$MergeWithObserver = new ObservableMergeWithCompletable$MergeWithObserver(observer);
        observer.onSubscribe(observableMergeWithCompletable$MergeWithObserver);
        this.c.subscribe(observableMergeWithCompletable$MergeWithObserver);
        this.e.subscribe(observableMergeWithCompletable$MergeWithObserver.otherObserver);
    }
}

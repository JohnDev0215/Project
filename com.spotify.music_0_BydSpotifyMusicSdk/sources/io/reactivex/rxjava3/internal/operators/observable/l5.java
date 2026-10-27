package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class l5 extends a {
    public final ObservableSource e;
    public final int k;

    public l5(ObservableSource observableSource, ObservableSource observableSource2, int i) {
        super(observableSource);
        this.e = observableSource2;
        this.k = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableWindowBoundary$WindowBoundaryMainObserver observableWindowBoundary$WindowBoundaryMainObserver = new ObservableWindowBoundary$WindowBoundaryMainObserver(observer, this.k);
        observer.onSubscribe(observableWindowBoundary$WindowBoundaryMainObserver);
        this.e.subscribe(observableWindowBoundary$WindowBoundaryMainObserver.boundaryObserver);
        this.c.subscribe(observableWindowBoundary$WindowBoundaryMainObserver);
    }
}

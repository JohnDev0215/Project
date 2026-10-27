package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class j5 extends a {
    public final long e;
    public final long k;
    public final int l;

    public j5(ObservableSource observableSource, long j, long j2, int i) {
        super(observableSource);
        this.e = j;
        this.k = j2;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        long j = this.k;
        long j2 = this.e;
        ObservableSource observableSource = this.c;
        if (j2 == j) {
            observableSource.subscribe(new ObservableWindow$WindowExactObserver(observer, j2, this.l));
        } else {
            observableSource.subscribe(new ObservableWindow$WindowSkipObserver(observer, this.e, this.k, this.l));
        }
    }
}

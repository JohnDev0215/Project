package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public final class c3 extends a {
    public final Scheduler e;
    public final boolean k;
    public final int l;

    public c3(ObservableSource observableSource, Scheduler scheduler, boolean z, int i) {
        super(observableSource);
        this.e = scheduler;
        this.k = z;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        Scheduler scheduler = this.e;
        boolean z = scheduler instanceof io.reactivex.rxjava3.internal.schedulers.x;
        ObservableSource observableSource = this.c;
        if (z) {
            observableSource.subscribe(observer);
        } else {
            observableSource.subscribe(new ObservableObserveOn$ObserveOnObserver(observer, scheduler.b(), this.k, this.l));
        }
    }
}

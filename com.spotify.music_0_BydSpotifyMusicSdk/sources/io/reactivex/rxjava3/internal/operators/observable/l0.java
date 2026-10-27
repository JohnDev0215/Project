package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.util.ErrorMode;

/* JADX INFO: loaded from: classes.dex */
public final class l0 extends a {
    public final io.reactivex.rxjava3.functions.o e;
    public final int k;
    public final ErrorMode l;
    public final Scheduler m;

    public l0(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, int i, ErrorMode errorMode, Scheduler scheduler) {
        super(observableSource);
        this.e = oVar;
        this.l = errorMode;
        this.k = Math.max(8, i);
        this.m = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ErrorMode errorMode = ErrorMode.c;
        Scheduler scheduler = this.m;
        ObservableSource observableSource = this.c;
        ErrorMode errorMode2 = this.l;
        if (errorMode2 == errorMode) {
            observableSource.subscribe(new ObservableConcatMapScheduler$ConcatMapObserver(new io.reactivex.rxjava3.observers.d(observer), this.e, this.k, scheduler.b()));
        } else {
            observableSource.subscribe(new ObservableConcatMapScheduler$ConcatMapDelayErrorObserver(observer, this.e, this.k, errorMode2 == ErrorMode.k, scheduler.b()));
        }
    }
}

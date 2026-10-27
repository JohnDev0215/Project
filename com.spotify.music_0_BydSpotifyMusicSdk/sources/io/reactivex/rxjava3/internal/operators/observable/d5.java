package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class d5 extends a {
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final ObservableSource m;

    public d5(Observable observable, long j, TimeUnit timeUnit, Scheduler scheduler, ObservableSource observableSource) {
        super(observable);
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableSource observableSource = this.m;
        ObservableSource observableSource2 = this.c;
        Scheduler scheduler = this.l;
        if (observableSource == null) {
            ObservableTimeoutTimed$TimeoutObserver observableTimeoutTimed$TimeoutObserver = new ObservableTimeoutTimed$TimeoutObserver(observer, this.e, this.k, scheduler.b());
            observer.onSubscribe(observableTimeoutTimed$TimeoutObserver);
            SequentialDisposable sequentialDisposable = observableTimeoutTimed$TimeoutObserver.task;
            Disposable disposableB = observableTimeoutTimed$TimeoutObserver.worker.b(new io.reactivex.rxjava3.internal.operators.flowable.u0(0L, observableTimeoutTimed$TimeoutObserver), observableTimeoutTimed$TimeoutObserver.timeout, observableTimeoutTimed$TimeoutObserver.unit);
            sequentialDisposable.getClass();
            DisposableHelper.c(sequentialDisposable, disposableB);
            observableSource2.subscribe(observableTimeoutTimed$TimeoutObserver);
            return;
        }
        ObservableTimeoutTimed$TimeoutFallbackObserver observableTimeoutTimed$TimeoutFallbackObserver = new ObservableTimeoutTimed$TimeoutFallbackObserver(observer, this.e, this.k, scheduler.b(), this.m);
        observer.onSubscribe(observableTimeoutTimed$TimeoutFallbackObserver);
        SequentialDisposable sequentialDisposable2 = observableTimeoutTimed$TimeoutFallbackObserver.task;
        Disposable disposableB2 = observableTimeoutTimed$TimeoutFallbackObserver.worker.b(new io.reactivex.rxjava3.internal.operators.flowable.u0(0L, observableTimeoutTimed$TimeoutFallbackObserver), observableTimeoutTimed$TimeoutFallbackObserver.timeout, observableTimeoutTimed$TimeoutFallbackObserver.unit);
        sequentialDisposable2.getClass();
        DisposableHelper.c(sequentialDisposable2, disposableB2);
        observableSource2.subscribe(observableTimeoutTimed$TimeoutFallbackObserver);
    }
}

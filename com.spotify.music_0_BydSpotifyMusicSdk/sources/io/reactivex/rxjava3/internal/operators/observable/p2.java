package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class p2 extends Observable {
    public final Scheduler c;
    public final long e;
    public final long k;
    public final TimeUnit l;

    public p2(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.e = j;
        this.k = j2;
        this.l = timeUnit;
        this.c = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableInterval$IntervalObserver observableInterval$IntervalObserver = new ObservableInterval$IntervalObserver(observer);
        observer.onSubscribe(observableInterval$IntervalObserver);
        Scheduler scheduler = this.c;
        if (!(scheduler instanceof io.reactivex.rxjava3.internal.schedulers.x)) {
            DisposableHelper.f(observableInterval$IntervalObserver, scheduler.e(observableInterval$IntervalObserver, this.e, this.k, this.l));
            return;
        }
        Scheduler.Worker workerB = scheduler.b();
        DisposableHelper.f(observableInterval$IntervalObserver, workerB);
        workerB.c(observableInterval$IntervalObserver, this.e, this.k, this.l);
    }
}

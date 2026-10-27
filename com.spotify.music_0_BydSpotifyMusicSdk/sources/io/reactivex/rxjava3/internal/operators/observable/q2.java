package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class q2 extends Observable {
    public final Scheduler c;
    public final long e;
    public final long k;
    public final long l;
    public final long m;
    public final TimeUnit n;

    public q2(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler scheduler) {
        this.l = j3;
        this.m = j4;
        this.n = timeUnit;
        this.c = scheduler;
        this.e = j;
        this.k = j2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableIntervalRange$IntervalRangeObserver observableIntervalRange$IntervalRangeObserver = new ObservableIntervalRange$IntervalRangeObserver(observer, this.e, this.k);
        observer.onSubscribe(observableIntervalRange$IntervalRangeObserver);
        Scheduler scheduler = this.c;
        if (!(scheduler instanceof io.reactivex.rxjava3.internal.schedulers.x)) {
            DisposableHelper.f(observableIntervalRange$IntervalRangeObserver, scheduler.e(observableIntervalRange$IntervalRangeObserver, this.l, this.m, this.n));
            return;
        }
        Scheduler.Worker workerB = scheduler.b();
        DisposableHelper.f(observableIntervalRange$IntervalRangeObserver, workerB);
        workerB.c(observableIntervalRange$IntervalRangeObserver, this.l, this.m, this.n);
    }
}

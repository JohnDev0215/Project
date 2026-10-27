package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class y3 extends a {
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final boolean m;

    public y3(ObservableSource observableSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(observableSource);
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        final io.reactivex.rxjava3.observers.d dVar = new io.reactivex.rxjava3.observers.d(observer);
        boolean z = this.m;
        ObservableSource observableSource = this.c;
        if (!z) {
            observableSource.subscribe(new ObservableSampleTimed$SampleTimedNoLast(dVar, this.e, this.k, this.l));
            return;
        }
        final long j = this.e;
        final TimeUnit timeUnit = this.k;
        final Scheduler scheduler = this.l;
        observableSource.subscribe(new ObservableSampleTimed$SampleTimedObserver<T>(dVar, j, timeUnit, scheduler) { // from class: io.reactivex.rxjava3.internal.operators.observable.ObservableSampleTimed$SampleTimedEmitLast
            private static final long serialVersionUID = -7139995637533111443L;
            final AtomicInteger wip = new AtomicInteger(1);

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleTimed$SampleTimedObserver
            public final void a() {
                T andSet = getAndSet(null);
                if (andSet != null) {
                    this.downstream.onNext(andSet);
                }
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (this.wip.incrementAndGet() == 2) {
                    T andSet = getAndSet(null);
                    if (andSet != null) {
                        this.downstream.onNext(andSet);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        this.downstream.onComplete();
                    }
                }
            }
        });
    }
}

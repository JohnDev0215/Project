package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class q5 extends a {
    public final long e;
    public final long k;
    public final TimeUnit l;
    public final Scheduler m;
    public final long n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f78p;

    public q5(Observable observable, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, long j3, int i, boolean z) {
        super(observable);
        this.e = j;
        this.k = j2;
        this.l = timeUnit;
        this.m = scheduler;
        this.n = j3;
        this.o = i;
        this.f78p = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        long j = this.e;
        long j2 = this.k;
        ObservableSource observableSource = this.c;
        if (j != j2) {
            observableSource.subscribe(new ObservableWindowTimed$WindowSkipObserver(observer, this.e, this.k, this.l, this.m.b(), this.o));
            return;
        }
        if (this.n == Long.MAX_VALUE) {
            observableSource.subscribe(new ObservableWindowTimed$WindowExactUnboundedObserver(observer, this.e, this.l, this.m, this.o));
            return;
        }
        long j3 = this.e;
        TimeUnit timeUnit = this.l;
        observableSource.subscribe(new ObservableWindowTimed$WindowExactBoundedObserver(this.o, j3, this.n, observer, this.m, timeUnit, this.f78p));
    }
}

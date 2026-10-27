package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class u4 extends a {
    public final long e;
    public final long k;
    public final TimeUnit l;
    public final Scheduler m;
    public final int n;
    public final boolean o;

    public u4(ObservableSource observableSource, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, int i, boolean z) {
        super(observableSource);
        this.e = j;
        this.k = j2;
        this.l = timeUnit;
        this.m = scheduler;
        this.n = i;
        this.o = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        long j = this.e;
        long j2 = this.k;
        TimeUnit timeUnit = this.l;
        this.c.subscribe(new ObservableTakeLastTimed$TakeLastTimedObserver(this.n, j, j2, observer, this.m, timeUnit, this.o));
    }
}

package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class k4 extends a {
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final int m;
    public final boolean n;

    public k4(ObservableSource observableSource, long j, TimeUnit timeUnit, Scheduler scheduler, int i, boolean z) {
        super(observableSource);
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = i;
        this.n = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new ObservableSkipLastTimed$SkipLastTimedObserver(observer, this.e, this.k, this.l, this.m, this.n));
    }
}

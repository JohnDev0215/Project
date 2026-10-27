package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class z4 extends a {
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final boolean m;

    public z4(Observable observable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(observable);
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new ObservableThrottleLatest$ThrottleLatestObserver(observer, this.e, this.k, this.l.b(), this.m));
    }
}

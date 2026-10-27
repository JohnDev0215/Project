package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class v0 extends a {
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;

    public v0(long j, TimeUnit timeUnit, ObservableSource observableSource, Scheduler scheduler) {
        super(observableSource);
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new u0(new io.reactivex.rxjava3.observers.d(observer), this.e, this.k, this.l.b()));
    }
}

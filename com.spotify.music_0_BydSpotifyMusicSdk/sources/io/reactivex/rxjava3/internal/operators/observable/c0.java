package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends a {
    public final long e;
    public final long k;
    public final TimeUnit l;
    public final Scheduler m;
    public final io.reactivex.rxjava3.functions.q n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f75p;

    public c0(ObservableSource observableSource, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, io.reactivex.rxjava3.functions.q qVar, int i, boolean z) {
        super(observableSource);
        this.e = j;
        this.k = j2;
        this.l = timeUnit;
        this.m = scheduler;
        this.n = qVar;
        this.o = i;
        this.f75p = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        long j = this.e;
        long j2 = this.k;
        ObservableSource observableSource = this.c;
        if (j == j2 && this.o == Integer.MAX_VALUE) {
            observableSource.subscribe(new y(new io.reactivex.rxjava3.observers.d(observer), this.n, j, this.l, this.m));
            return;
        }
        Scheduler.Worker workerB = this.m.b();
        long j3 = this.e;
        long j4 = this.k;
        if (j3 == j4) {
            observableSource.subscribe(new x(new io.reactivex.rxjava3.observers.d(observer), this.n, j3, this.l, this.o, this.f75p, workerB));
        } else {
            observableSource.subscribe(new b0(new io.reactivex.rxjava3.observers.d(observer), this.n, j3, j4, this.l, workerB));
        }
    }
}

package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends Single {
    public final SingleSource c;
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final SingleSource m;

    public j0(SingleSource singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, SingleSource singleSource2) {
        this.c = singleSource;
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = singleSource2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        SingleTimeout$TimeoutMainObserver singleTimeout$TimeoutMainObserver = new SingleTimeout$TimeoutMainObserver(singleObserver, this.m, this.e, this.k);
        singleObserver.onSubscribe(singleTimeout$TimeoutMainObserver);
        DisposableHelper.c(singleTimeout$TimeoutMainObserver.task, this.l.d(singleTimeout$TimeoutMainObserver, this.e, this.k));
        this.c.subscribe(singleTimeout$TimeoutMainObserver);
    }
}

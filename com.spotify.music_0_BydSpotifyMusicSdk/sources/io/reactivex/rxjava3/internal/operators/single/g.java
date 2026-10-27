package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Single {
    public final SingleSource c;
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final boolean m;

    public g(SingleSource singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.c = singleSource;
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        singleObserver.onSubscribe(sequentialDisposable);
        this.c.subscribe(new f(this, sequentialDisposable, singleObserver));
    }
}

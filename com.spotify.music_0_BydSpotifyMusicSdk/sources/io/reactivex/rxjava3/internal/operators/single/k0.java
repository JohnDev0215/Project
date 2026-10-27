package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends Single {
    public final long c;
    public final TimeUnit e;
    public final Scheduler k;

    public k0(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.c = j;
        this.e = timeUnit;
        this.k = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        SingleTimer$TimerDisposable singleTimer$TimerDisposable = new SingleTimer$TimerDisposable(singleObserver);
        singleObserver.onSubscribe(singleTimer$TimerDisposable);
        DisposableHelper.c(singleTimer$TimerDisposable, this.k.d(singleTimer$TimerDisposable, this.c, this.e));
    }
}

package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class i0 implements SingleObserver, Disposable {
    public final SingleObserver c;
    public final TimeUnit e;
    public final Scheduler k;
    public final long l;
    public Disposable m;

    public i0(SingleObserver singleObserver, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        long jA;
        this.c = singleObserver;
        this.e = timeUnit;
        this.k = scheduler;
        if (z) {
            scheduler.getClass();
            jA = Scheduler.a(timeUnit);
        } else {
            jA = 0;
        }
        this.l = jA;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.m, disposable)) {
            this.m = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.k.getClass();
        TimeUnit timeUnit = this.e;
        this.c.onSuccess(new io.reactivex.rxjava3.schedulers.f(obj, Scheduler.a(timeUnit) - this.l, timeUnit));
    }
}

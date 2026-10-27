package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class z0 implements Observer, Disposable {
    public final Observer c;
    public final long e;
    public final TimeUnit k;
    public final Scheduler.Worker l;
    public final boolean m;
    public Disposable n;

    public z0(Observer observer, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
        this.c = observer;
        this.e = j;
        this.k = timeUnit;
        this.l = worker;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.n.dispose();
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.l.b(new x0(this), this.e, this.k);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.l.b(new y0(this, th), this.m ? this.e : 0L, this.k);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.l.b(new p4(this, 1, obj), this.e, this.k);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.n, disposable)) {
            this.n = disposable;
            this.c.onSubscribe(this);
        }
    }
}

package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class a5 implements Observer, Disposable {
    public final Observer c;
    public final TimeUnit e;
    public final Scheduler k;
    public long l;
    public Disposable m;

    public a5(Observer observer, TimeUnit timeUnit, Scheduler scheduler) {
        this.c = observer;
        this.k = scheduler;
        this.e = timeUnit;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.k.getClass();
        TimeUnit timeUnit = this.e;
        long jA = Scheduler.a(timeUnit);
        long j = this.l;
        this.l = jA;
        this.c.onNext(new io.reactivex.rxjava3.schedulers.f(obj, jA - j, timeUnit));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.m, disposable)) {
            this.m = disposable;
            this.k.getClass();
            this.l = Scheduler.a(this.e);
            this.c.onSubscribe(this);
        }
    }
}

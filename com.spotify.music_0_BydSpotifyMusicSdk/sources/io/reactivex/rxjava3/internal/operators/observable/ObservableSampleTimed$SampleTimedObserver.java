package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class ObservableSampleTimed$SampleTimedObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = -3517602651313910099L;
    final Observer<? super T> downstream;
    final long period;
    final Scheduler scheduler;
    final AtomicReference<Disposable> timer = new AtomicReference<>();
    final TimeUnit unit;
    Disposable upstream;

    public ObservableSampleTimed$SampleTimedObserver(io.reactivex.rxjava3.observers.d dVar, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.downstream = dVar;
        this.period = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public abstract void a();

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.timer);
        this.upstream.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        DisposableHelper.a(this.timer);
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.a(this.timer);
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        lazySet(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
            Scheduler scheduler = this.scheduler;
            long j = this.period;
            DisposableHelper.c(this.timer, scheduler.e(this, j, j, this.unit));
        }
    }
}

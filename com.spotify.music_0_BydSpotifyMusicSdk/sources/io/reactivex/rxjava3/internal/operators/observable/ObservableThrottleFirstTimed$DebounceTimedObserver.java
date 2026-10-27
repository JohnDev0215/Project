package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableThrottleFirstTimed$DebounceTimedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = 786994795061867455L;
    final Observer<? super T> downstream;
    volatile boolean gate;
    final long timeout;
    final TimeUnit unit;
    Disposable upstream;
    final Scheduler.Worker worker;

    public ObservableThrottleFirstTimed$DebounceTimedObserver(io.reactivex.rxjava3.observers.d dVar, long j, TimeUnit timeUnit, Scheduler.Worker worker) {
        this.downstream = dVar;
        this.timeout = j;
        this.unit = timeUnit;
        this.worker = worker;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        this.worker.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.worker.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.downstream.onComplete();
        this.worker.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.downstream.onError(th);
        this.worker.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.gate) {
            return;
        }
        this.gate = true;
        this.downstream.onNext(obj);
        Disposable disposable = get();
        if (disposable != null) {
            disposable.dispose();
        }
        DisposableHelper.c(this, this.worker.b(this, this.timeout, this.unit));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.gate = false;
    }
}

package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableTimeoutTimed$TimeoutObserver<T> extends AtomicLong implements Observer<T>, Disposable, c5 {
    private static final long serialVersionUID = 3764492702657003550L;
    final Observer<? super T> downstream;
    final long timeout;
    final TimeUnit unit;
    final Scheduler.Worker worker;
    final SequentialDisposable task = new SequentialDisposable();
    final AtomicReference<Disposable> upstream = new AtomicReference<>();

    public ObservableTimeoutTimed$TimeoutObserver(Observer observer, long j, TimeUnit timeUnit, Scheduler.Worker worker) {
        this.downstream = observer;
        this.timeout = j;
        this.unit = timeUnit;
        this.worker = worker;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.c5
    public final void b(long j) {
        if (compareAndSet(j, Long.MAX_VALUE)) {
            DisposableHelper.a(this.upstream);
            this.downstream.onError(new TimeoutException(io.reactivex.rxjava3.internal.util.c.e(this.timeout, this.unit)));
            this.worker.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.upstream);
        this.worker.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
            this.task.dispose();
            this.downstream.onComplete();
            this.worker.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (getAndSet(Long.MAX_VALUE) == Long.MAX_VALUE) {
            RxJavaPlugins.b(th);
            return;
        }
        this.task.dispose();
        this.downstream.onError(th);
        this.worker.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        long j = get();
        if (j != Long.MAX_VALUE) {
            long j2 = 1 + j;
            if (compareAndSet(j, j2)) {
                this.task.get().dispose();
                this.downstream.onNext(obj);
                SequentialDisposable sequentialDisposable = this.task;
                Disposable disposableB = this.worker.b(new io.reactivex.rxjava3.internal.operators.flowable.u0(j2, this), this.timeout, this.unit);
                sequentialDisposable.getClass();
                DisposableHelper.c(sequentialDisposable, disposableB);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.upstream, disposable);
    }
}

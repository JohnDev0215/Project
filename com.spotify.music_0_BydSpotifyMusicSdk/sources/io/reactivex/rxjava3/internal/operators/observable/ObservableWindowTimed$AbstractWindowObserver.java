package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
abstract class ObservableWindowTimed$AbstractWindowObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = 5724293814035355511L;
    final int bufferSize;
    volatile boolean done;
    final Observer<? super Observable<T>> downstream;
    long emitted;
    Throwable error;
    final long timespan;
    final TimeUnit unit;
    Disposable upstream;
    volatile boolean upstreamCancelled;
    final io.reactivex.rxjava3.operators.f queue = new io.reactivex.rxjava3.internal.queue.a();
    final AtomicBoolean downstreamCancelled = new AtomicBoolean();
    final AtomicInteger windowCount = new AtomicInteger(1);

    public ObservableWindowTimed$AbstractWindowObserver(Observer observer, long j, TimeUnit timeUnit, int i) {
        this.downstream = observer;
        this.timespan = j;
        this.unit = timeUnit;
        this.bufferSize = i;
    }

    public abstract void a();

    public abstract void b();

    public abstract void c();

    public final void d() {
        if (this.windowCount.decrementAndGet() == 0) {
            a();
            this.upstream.dispose();
            this.upstreamCancelled = true;
            c();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        if (this.downstreamCancelled.compareAndSet(false, true)) {
            d();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.downstreamCancelled.get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.error = th;
        this.done = true;
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.queue.offer(obj);
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
            b();
        }
    }

    public void run() {
        d();
    }
}

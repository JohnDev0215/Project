package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSkipLastTimed$SkipLastTimedObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5677354903406201275L;
    volatile boolean cancelled;
    final boolean delayError;
    volatile boolean done;
    final Observer<? super T> downstream;
    Throwable error;
    final io.reactivex.rxjava3.operators.h queue;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;
    Disposable upstream;

    public ObservableSkipLastTimed$SkipLastTimedObserver(Observer observer, long j, TimeUnit timeUnit, Scheduler scheduler, int i, boolean z) {
        this.downstream = observer;
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.queue = new io.reactivex.rxjava3.operators.h(i);
        this.delayError = z;
    }

    public final void a() {
        if (getAndIncrement() != 0) {
            return;
        }
        Observer<? super T> observer = this.downstream;
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        boolean z = this.delayError;
        TimeUnit timeUnit = this.unit;
        Scheduler scheduler = this.scheduler;
        long j = this.time;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            boolean z2 = this.done;
            Long l = (Long) hVar.c();
            boolean z3 = l == null;
            scheduler.getClass();
            long jA = Scheduler.a(timeUnit);
            if (!z3 && l.longValue() > jA - j) {
                z3 = true;
            }
            if (z2) {
                if (!z) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        observer.onError(th);
                        return;
                    } else if (z3) {
                        observer.onComplete();
                        return;
                    }
                } else if (z3) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        observer.onError(th2);
                        return;
                    } else {
                        observer.onComplete();
                        return;
                    }
                }
            }
            if (z3) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                hVar.poll();
                observer.onNext(hVar.poll());
            }
        }
        this.queue.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.dispose();
        if (getAndIncrement() == 0) {
            this.queue.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.error = th;
        this.done = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        Scheduler scheduler = this.scheduler;
        TimeUnit timeUnit = this.unit;
        scheduler.getClass();
        hVar.a(Long.valueOf(Scheduler.a(timeUnit)), obj);
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}

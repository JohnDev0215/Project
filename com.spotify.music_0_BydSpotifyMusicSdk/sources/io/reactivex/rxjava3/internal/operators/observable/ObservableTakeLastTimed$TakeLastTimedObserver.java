package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
final class ObservableTakeLastTimed$TakeLastTimedObserver<T> extends AtomicBoolean implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5677354903406201275L;
    volatile boolean cancelled;
    final long count;
    final boolean delayError;
    final Observer<? super T> downstream;
    Throwable error;
    final io.reactivex.rxjava3.operators.h queue;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;
    Disposable upstream;

    public ObservableTakeLastTimed$TakeLastTimedObserver(int i, long j, long j2, Observer observer, Scheduler scheduler, TimeUnit timeUnit, boolean z) {
        this.downstream = observer;
        this.count = j;
        this.time = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.queue = new io.reactivex.rxjava3.operators.h(i);
        this.delayError = z;
    }

    public final void a() {
        Throwable th;
        if (compareAndSet(false, true)) {
            Observer<? super T> observer = this.downstream;
            io.reactivex.rxjava3.operators.h hVar = this.queue;
            boolean z = this.delayError;
            Scheduler scheduler = this.scheduler;
            TimeUnit timeUnit = this.unit;
            scheduler.getClass();
            long jA = Scheduler.a(timeUnit) - this.time;
            while (!this.cancelled) {
                if (!z && (th = this.error) != null) {
                    hVar.clear();
                    observer.onError(th);
                    return;
                }
                Object objPoll = hVar.poll();
                if (objPoll == null) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        observer.onError(th2);
                        return;
                    } else {
                        observer.onComplete();
                        return;
                    }
                }
                Object objPoll2 = hVar.poll();
                if (((Long) objPoll).longValue() >= jA) {
                    observer.onNext(objPoll2);
                }
            }
            hVar.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.dispose();
        if (compareAndSet(false, true)) {
            this.queue.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.error = th;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        long j;
        long j2;
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        Scheduler scheduler = this.scheduler;
        TimeUnit timeUnit = this.unit;
        scheduler.getClass();
        long jA = Scheduler.a(timeUnit);
        long j3 = this.time;
        long j4 = this.count;
        boolean z = j4 == Long.MAX_VALUE;
        hVar.a(Long.valueOf(jA), obj);
        while (!hVar.isEmpty()) {
            if (((Long) hVar.c()).longValue() > jA - j3) {
                if (z) {
                    return;
                }
                AtomicLong atomicLong = hVar.f83p;
                long j5 = atomicLong.get();
                while (true) {
                    j = hVar.c.get();
                    j2 = atomicLong.get();
                    if (j5 == j2) {
                        break;
                    } else {
                        j5 = j2;
                    }
                }
                if ((((int) (j - j2)) >> 1) <= j4) {
                    return;
                }
            }
            hVar.poll();
            hVar.poll();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}

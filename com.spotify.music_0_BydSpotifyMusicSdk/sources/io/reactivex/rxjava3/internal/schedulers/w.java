package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class w extends Scheduler.Worker {
    public final PriorityBlockingQueue c = new PriorityBlockingQueue();
    public final AtomicInteger e = new AtomicInteger();
    public final AtomicInteger k = new AtomicInteger();
    public volatile boolean l;

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable a(Runnable runnable) {
        return d(runnable, Scheduler.a(TimeUnit.MILLISECONDS));
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(j) + Scheduler.a(TimeUnit.MILLISECONDS);
        return d(new u(runnable, this, millis), millis);
    }

    public final Disposable d(Runnable runnable, long j) {
        boolean z = this.l;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (z) {
            return emptyDisposable;
        }
        v vVar = new v(runnable, Long.valueOf(j), this.k.incrementAndGet());
        this.c.add(vVar);
        int iAddAndGet = 1;
        if (this.e.getAndIncrement() != 0) {
            return Disposable.e(new f(this, 1, vVar));
        }
        while (!this.l) {
            v vVar2 = (v) this.c.poll();
            if (vVar2 == null) {
                iAddAndGet = this.e.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return emptyDisposable;
                }
            } else if (!vVar2.l) {
                vVar2.c.run();
            }
        }
        this.c.clear();
        return emptyDisposable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l;
    }
}

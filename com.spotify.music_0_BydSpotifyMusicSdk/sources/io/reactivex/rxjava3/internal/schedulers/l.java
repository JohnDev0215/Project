package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class l extends Scheduler.Worker implements Runnable {
    public final k e;
    public final m k;
    public final AtomicBoolean l = new AtomicBoolean();
    public final io.reactivex.rxjava3.disposables.a c = new io.reactivex.rxjava3.disposables.a();

    public l(k kVar) {
        m mVar;
        m mVar2;
        this.e = kVar;
        io.reactivex.rxjava3.disposables.a aVar = kVar.k;
        if (aVar.isDisposed()) {
            mVar2 = n.h;
        } else {
            while (true) {
                ConcurrentLinkedQueue concurrentLinkedQueue = kVar.e;
                if (concurrentLinkedQueue.isEmpty()) {
                    mVar = new m(kVar.n);
                    aVar.c(mVar);
                    break;
                } else {
                    mVar = (m) concurrentLinkedQueue.poll();
                    if (mVar != null) {
                        break;
                    }
                }
            }
            mVar2 = mVar;
        }
        this.k = mVar2;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.c.isDisposed() ? EmptyDisposable.c : this.k.d(runnable, j, timeUnit, this.c);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.l.compareAndSet(false, true)) {
            this.c.dispose();
            if (n.i) {
                this.k.d(this, 0L, TimeUnit.NANOSECONDS, null);
                return;
            }
            k kVar = this.e;
            kVar.getClass();
            long jNanoTime = System.nanoTime() + kVar.c;
            m mVar = this.k;
            mVar.k = jNanoTime;
            kVar.e.offer(mVar);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.get();
    }

    @Override // java.lang.Runnable
    public final void run() {
        k kVar = this.e;
        kVar.getClass();
        long jNanoTime = System.nanoTime() + kVar.c;
        m mVar = this.k;
        mVar.k = jNanoTime;
        kVar.e.offer(mVar);
    }
}

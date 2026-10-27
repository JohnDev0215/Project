package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
abstract class FlowableObserveOn$BaseObserveOnSubscriber<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T>, Runnable {
    private static final long serialVersionUID = -8241002408341274697L;
    volatile boolean cancelled;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final int limit;
    boolean outputFused;
    final int prefetch;
    long produced;
    io.reactivex.rxjava3.operators.g queue;
    final AtomicLong requested = new AtomicLong();
    int sourceMode;
    tg2 upstream;
    final Scheduler.Worker worker;

    public FlowableObserveOn$BaseObserveOnSubscriber(Scheduler.Worker worker, boolean z, int i) {
        this.worker = worker;
        this.delayError = z;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this.requested, j);
            h();
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    public final boolean c(boolean z, boolean z2, rg2 rg2Var) {
        if (this.cancelled) {
            clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (this.delayError) {
            if (!z2) {
                return false;
            }
            this.cancelled = true;
            Throwable th = this.error;
            if (th != null) {
                rg2Var.onError(th);
            } else {
                rg2Var.onComplete();
            }
            this.worker.dispose();
            return true;
        }
        Throwable th2 = this.error;
        if (th2 != null) {
            this.cancelled = true;
            clear();
            rg2Var.onError(th2);
            this.worker.dispose();
            return true;
        }
        if (!z2) {
            return false;
        }
        this.cancelled = true;
        rg2Var.onComplete();
        this.worker.dispose();
        return true;
    }

    @Override // p.tg2
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        this.worker.dispose();
        if (this.outputFused || getAndIncrement() != 0) {
            return;
        }
        this.queue.clear();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.queue.clear();
    }

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public final void h() {
        if (getAndIncrement() != 0) {
            return;
        }
        this.worker.a(this);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        h();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.error = th;
        this.done = true;
        h();
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        if (this.sourceMode == 2) {
            h();
            return;
        }
        if (!this.queue.offer(obj)) {
            this.upstream.cancel();
            this.error = new MissingBackpressureException("Queue is full?!");
            this.done = true;
        }
        h();
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.outputFused) {
            f();
        } else if (this.sourceMode == 1) {
            g();
        } else {
            e();
        }
    }
}

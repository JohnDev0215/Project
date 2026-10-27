package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicLong;
import p.ci1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableOnBackpressureBuffer$BackpressureBufferSubscriber<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -2514538129242366402L;
    volatile boolean cancelled;
    final boolean delayError;
    volatile boolean done;
    final rg2 downstream;
    Throwable error;
    final io.reactivex.rxjava3.functions.a onOverflow;
    boolean outputFused;
    final io.reactivex.rxjava3.operators.f queue;
    final AtomicLong requested = new AtomicLong();
    tg2 upstream;

    public FlowableOnBackpressureBuffer$BackpressureBufferSubscriber(rg2 rg2Var, int i, boolean z, boolean z2, io.reactivex.rxjava3.functions.a aVar) {
        this.downstream = rg2Var;
        this.onOverflow = aVar;
        this.delayError = z2;
        this.queue = z ? new io.reactivex.rxjava3.operators.h(i) : new SpscArrayQueue(i);
    }

    @Override // p.tg2
    public final void a(long j) {
        if (this.outputFused || !SubscriptionHelper.f(j)) {
            return;
        }
        ci1.d(this.requested, j);
        e();
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
            this.queue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (this.delayError) {
            if (!z2) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                rg2Var.onError(th);
            } else {
                rg2Var.onComplete();
            }
            return true;
        }
        Throwable th2 = this.error;
        if (th2 != null) {
            this.queue.clear();
            rg2Var.onError(th2);
            return true;
        }
        if (!z2) {
            return false;
        }
        rg2Var.onComplete();
        return true;
    }

    @Override // p.tg2
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        if (this.outputFused || getAndIncrement() != 0) {
            return;
        }
        this.queue.clear();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.queue.clear();
    }

    public final void e() {
        if (getAndIncrement() == 0) {
            io.reactivex.rxjava3.operators.f fVar = this.queue;
            rg2 rg2Var = this.downstream;
            int iAddAndGet = 1;
            while (!c(this.done, fVar.isEmpty(), rg2Var)) {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    Object objPoll = fVar.poll();
                    boolean z2 = objPoll == null;
                    if (c(z, z2, rg2Var)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    rg2Var.onNext(objPoll);
                    j2++;
                }
                if (j2 == j && c(this.done, fVar.isEmpty(), rg2Var)) {
                    return;
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    this.requested.addAndGet(-j2);
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        if (this.outputFused) {
            this.downstream.onComplete();
        } else {
            e();
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.error = th;
        this.done = true;
        if (this.outputFused) {
            this.downstream.onError(th);
        } else {
            e();
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.queue.offer(obj)) {
            if (this.outputFused) {
                this.downstream.onNext(null);
                return;
            } else {
                e();
                return;
            }
        }
        this.upstream.cancel();
        MissingBackpressureException missingBackpressureException = new MissingBackpressureException("Buffer is full");
        try {
            this.onOverflow.run();
        } catch (Throwable th) {
            he1.R(th);
            missingBackpressureException.initCause(th);
        }
        onError(missingBackpressureException);
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        return this.queue.poll();
    }
}

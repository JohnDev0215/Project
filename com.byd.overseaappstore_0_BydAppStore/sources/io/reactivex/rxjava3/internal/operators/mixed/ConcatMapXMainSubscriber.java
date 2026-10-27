package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.exceptions.QueueOverflowException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicInteger;
import spi.kp;
import spi.os;
import spi.ps;
import spi.sl;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ConcatMapXMainSubscriber<T> extends AtomicInteger implements os {
    private static final long serialVersionUID = -3214213361171757852L;
    public volatile boolean cancelled;
    public volatile boolean done;
    public final ErrorMode errorMode;
    public final AtomicThrowable errors = new AtomicThrowable();
    public final int prefetch;
    public kp<T> queue;
    public boolean syncFused;
    public ps upstream;

    public ConcatMapXMainSubscriber(int i, ErrorMode errorMode) {
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    public void clearValue() {
    }

    public abstract void disposeInner();

    public abstract void drain();

    @Override // spi.os
    public final void onComplete() {
        this.done = true;
        drain();
    }

    @Override // spi.os
    public final void onError(Throwable th) {
        if (this.errors.tryAddThrowableOrReport(th)) {
            if (this.errorMode == ErrorMode.IMMEDIATE) {
                disposeInner();
            }
            this.done = true;
            drain();
        }
    }

    @Override // spi.os
    public final void onNext(T t) {
        if (t == null || this.queue.offer(t)) {
            drain();
        } else {
            this.upstream.cancel();
            onError(new QueueOverflowException());
        }
    }

    @Override // spi.os
    public final void onSubscribe(ps psVar) {
        if (SubscriptionHelper.validate(this.upstream, psVar)) {
            this.upstream = psVar;
            if (psVar instanceof sl) {
                sl slVar = (sl) psVar;
                int iRequestFusion = slVar.requestFusion(7);
                if (iRequestFusion == 1) {
                    this.queue = slVar;
                    this.syncFused = true;
                    this.done = true;
                    onSubscribeDownstream();
                    drain();
                    return;
                }
                if (iRequestFusion == 2) {
                    this.queue = slVar;
                    onSubscribeDownstream();
                    this.upstream.request(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            onSubscribeDownstream();
            this.upstream.request(this.prefetch);
        }
    }

    public abstract void onSubscribeDownstream();

    public final void stop() {
        this.cancelled = true;
        this.upstream.cancel();
        disposeInner();
        this.errors.tryTerminateAndReport();
        if (getAndIncrement() == 0) {
            this.queue.clear();
            clearValue();
        }
    }
}

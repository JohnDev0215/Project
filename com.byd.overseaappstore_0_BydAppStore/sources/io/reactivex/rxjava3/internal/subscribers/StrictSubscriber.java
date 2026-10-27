package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import spi.d;
import spi.os;
import spi.ps;

/* JADX INFO: loaded from: classes2.dex */
public class StrictSubscriber<T> extends AtomicInteger implements os, ps {
    private static final long serialVersionUID = -4945028590049415624L;
    public volatile boolean done;
    public final os<? super T> downstream;
    public final AtomicThrowable error = new AtomicThrowable();
    public final AtomicLong requested = new AtomicLong();
    public final AtomicReference<ps> upstream = new AtomicReference<>();
    public final AtomicBoolean once = new AtomicBoolean();

    public StrictSubscriber(os<? super T> osVar) {
        this.downstream = osVar;
    }

    @Override // spi.ps
    public void cancel() {
        if (this.done) {
            return;
        }
        SubscriptionHelper.cancel(this.upstream);
    }

    @Override // spi.os
    public void onComplete() {
        this.done = true;
        os<? super T> osVar = this.downstream;
        AtomicThrowable atomicThrowable = this.error;
        if (getAndIncrement() == 0) {
            atomicThrowable.tryTerminateConsumer(osVar);
        }
    }

    @Override // spi.os
    public void onError(Throwable th) {
        this.done = true;
        os<? super T> osVar = this.downstream;
        AtomicThrowable atomicThrowable = this.error;
        if (atomicThrowable.tryAddThrowableOrReport(th) && getAndIncrement() == 0) {
            atomicThrowable.tryTerminateConsumer(osVar);
        }
    }

    @Override // spi.os
    public void onNext(T t) {
        os<? super T> osVar = this.downstream;
        AtomicThrowable atomicThrowable = this.error;
        if (get() == 0 && compareAndSet(0, 1)) {
            osVar.onNext(t);
            if (decrementAndGet() == 0) {
                return;
            }
            atomicThrowable.tryTerminateConsumer(osVar);
        }
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onSubscribe(this);
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, psVar);
        } else {
            psVar.cancel();
            cancel();
            onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
        }
    }

    @Override // spi.ps
    public void request(long j) {
        if (j > 0) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        } else {
            cancel();
            onError(new IllegalArgumentException(d.d("§3.9 violated: positive request amount required but it was ", j)));
        }
    }
}

package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.rg2;
import p.tg2;
import p.u4;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
public class StrictSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = -4945028590049415624L;
    volatile boolean done;
    final rg2 downstream;
    final AtomicThrowable error = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<tg2> upstream = new AtomicReference<>();
    final AtomicBoolean once = new AtomicBoolean();

    public StrictSubscriber(rg2 rg2Var) {
        this.downstream = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (j > 0) {
            SubscriptionHelper.c(this.upstream, this.requested, j);
        } else {
            cancel();
            onError(new IllegalArgumentException(u4.g("§3.9 violated: positive request amount required but it was ", j)));
        }
    }

    @Override // p.tg2
    public final void cancel() {
        if (this.done) {
            return;
        }
        SubscriptionHelper.b(this.upstream);
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        rg2 rg2Var = this.downstream;
        AtomicThrowable atomicThrowable = this.error;
        if (getAndIncrement() == 0) {
            atomicThrowable.e(rg2Var);
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.done = true;
        rg2 rg2Var = this.downstream;
        AtomicThrowable atomicThrowable = this.error;
        if (atomicThrowable.a(th) && getAndIncrement() == 0) {
            atomicThrowable.e(rg2Var);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        ux2.w(this.downstream, obj, this, this.error);
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onSubscribe(this);
            SubscriptionHelper.d(this.upstream, this.requested, tg2Var);
        } else {
            tg2Var.cancel();
            cancel();
            onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
        }
    }
}

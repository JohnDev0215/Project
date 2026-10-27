package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import p.ci1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableOnBackpressureDrop$BackpressureDropSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = -6246093802440953054L;
    boolean done;
    final rg2 downstream;
    final io.reactivex.rxjava3.functions.g onDrop;
    tg2 upstream;

    public FlowableOnBackpressureDrop$BackpressureDropSubscriber(rg2 rg2Var, b0 b0Var) {
        this.downstream = rg2Var;
        this.onDrop = b0Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this, j);
        }
    }

    @Override // p.tg2
    public final void cancel() {
        this.upstream.cancel();
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.downstream.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
        } else {
            this.done = true;
            this.downstream.onError(th);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        if (get() != 0) {
            this.downstream.onNext(obj);
            ci1.A(this, 1L);
            return;
        }
        try {
            this.onDrop.accept(obj);
        } catch (Throwable th) {
            he1.R(th);
            cancel();
            onError(th);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}

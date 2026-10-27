package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import java.util.concurrent.atomic.AtomicInteger;
import p.az1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRepeatUntil$RepeatSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final rg2 downstream;
    long produced;
    final SubscriptionArbiter sa;
    final az1 source;
    final io.reactivex.rxjava3.functions.e stop;

    public FlowableRepeatUntil$RepeatSubscriber(rg2 rg2Var, SubscriptionArbiter subscriptionArbiter, Flowable flowable) {
        this.downstream = rg2Var;
        this.sa = subscriptionArbiter;
        this.source = flowable;
    }

    @Override // p.rg2
    public final void onComplete() {
        try {
            throw null;
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.produced++;
        this.downstream.onNext(obj);
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        this.sa.g(tg2Var);
    }
}

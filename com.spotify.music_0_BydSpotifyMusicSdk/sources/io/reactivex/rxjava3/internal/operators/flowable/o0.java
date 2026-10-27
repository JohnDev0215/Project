package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends a {
    public final io.reactivex.rxjava3.functions.p k;
    public final long l;

    public o0(Flowable flowable, long j, io.reactivex.rxjava3.functions.p pVar) {
        super(flowable);
        this.k = pVar;
        this.l = j;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
        rg2Var.onSubscribe(subscriptionArbiter);
        new FlowableRetryPredicate$RetrySubscriber(rg2Var, this.l, this.k, subscriptionArbiter, this.e).a();
    }
}

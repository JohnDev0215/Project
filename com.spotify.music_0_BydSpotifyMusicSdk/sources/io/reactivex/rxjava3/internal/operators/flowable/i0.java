package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends a {
    public final /* synthetic */ int k;
    public final long l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i0(Flowable flowable, long j, int i) {
        super(flowable);
        this.k = i;
        this.l = j;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.k;
        long j = this.l;
        switch (i) {
            case 0:
                SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
                rg2Var.onSubscribe(subscriptionArbiter);
                new FlowableRepeat$RepeatSubscriber(rg2Var, j != Long.MAX_VALUE ? j - 1 : Long.MAX_VALUE, subscriptionArbiter, this.e).a();
                break;
            default:
                this.e.subscribe((FlowableSubscriber) new t0(rg2Var, j));
                break;
        }
    }
}

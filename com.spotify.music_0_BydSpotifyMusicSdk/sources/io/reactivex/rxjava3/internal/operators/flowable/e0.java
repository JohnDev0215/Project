package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import p.rg2;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends a {
    public final /* synthetic */ int k;
    public final Object l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e0(Flowable flowable, Object obj, int i) {
        super(flowable);
        this.k = i;
        this.l = obj;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.k;
        Flowable flowable = this.e;
        Object obj = this.l;
        switch (i) {
            case 0:
                flowable.subscribe((FlowableSubscriber) new FlowableReduce$ReduceSubscriber(rg2Var, (io.reactivex.rxjava3.functions.c) obj));
                break;
            case 1:
                SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
                rg2Var.onSubscribe(subscriptionArbiter);
                u4.z(obj);
                FlowableRepeatUntil$RepeatSubscriber flowableRepeatUntil$RepeatSubscriber = new FlowableRepeatUntil$RepeatSubscriber(rg2Var, subscriptionArbiter, flowable);
                if (flowableRepeatUntil$RepeatSubscriber.getAndIncrement() == 0) {
                    int iAddAndGet = 1;
                    while (!flowableRepeatUntil$RepeatSubscriber.sa.c()) {
                        long j = flowableRepeatUntil$RepeatSubscriber.produced;
                        if (j != 0) {
                            flowableRepeatUntil$RepeatSubscriber.produced = 0L;
                            flowableRepeatUntil$RepeatSubscriber.sa.f(j);
                        }
                        flowableRepeatUntil$RepeatSubscriber.source.subscribe(flowableRepeatUntil$RepeatSubscriber);
                        iAddAndGet = flowableRepeatUntil$RepeatSubscriber.addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            break;
                        }
                    }
                }
                break;
            default:
                SubscriptionArbiter subscriptionArbiter2 = new SubscriptionArbiter();
                rg2Var.onSubscribe(subscriptionArbiter2);
                new FlowableRetryBiPredicate$RetryBiSubscriber(rg2Var, (io.reactivex.rxjava3.functions.d) obj, subscriptionArbiter2, flowable).a();
                break;
        }
    }
}

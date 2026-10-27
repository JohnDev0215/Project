package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Scheduler;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class v0 extends a {
    public final Scheduler k;
    public final boolean l;

    public v0(i0 i0Var, Scheduler scheduler) {
        super(i0Var);
        this.k = scheduler;
        this.l = true;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        Scheduler.Worker workerB = this.k.b();
        FlowableSubscribeOn$SubscribeOnSubscriber flowableSubscribeOn$SubscribeOnSubscriber = new FlowableSubscribeOn$SubscribeOnSubscriber(rg2Var, workerB, this.e, this.l);
        rg2Var.onSubscribe(flowableSubscribeOn$SubscribeOnSubscriber);
        workerB.a(flowableSubscribeOn$SubscribeOnSubscriber);
    }
}

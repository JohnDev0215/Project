package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
abstract class FlowableRepeatWhen$WhenSourceSubscriber<T, U> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -5604623027276966720L;
    protected final rg2 downstream;
    protected final io.reactivex.rxjava3.processors.a processor;
    private long produced;
    protected final tg2 receiver;

    public FlowableRepeatWhen$WhenSourceSubscriber(io.reactivex.rxjava3.subscribers.a aVar, io.reactivex.rxjava3.processors.a aVar2, tg2 tg2Var) {
        this.downstream = aVar;
        this.processor = aVar2;
        this.receiver = tg2Var;
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter, p.tg2
    public final void cancel() {
        super.cancel();
        this.receiver.cancel();
    }

    public final void h(Object obj) {
        g(EmptySubscription.c);
        long j = this.produced;
        if (j != 0) {
            this.produced = 0L;
            f(j);
        }
        this.receiver.a(1L);
        this.processor.onNext(obj);
    }

    public void onError(Throwable th) {
        h(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.produced++;
        this.downstream.onNext(obj);
    }
}

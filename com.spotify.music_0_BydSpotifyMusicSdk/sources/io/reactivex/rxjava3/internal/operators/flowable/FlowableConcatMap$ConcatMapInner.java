package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;

/* JADX INFO: loaded from: classes.dex */
final class FlowableConcatMap$ConcatMapInner<R> extends SubscriptionArbiter implements FlowableSubscriber<R> {
    private static final long serialVersionUID = 897683679971470653L;
    final c parent;
    long produced;

    public FlowableConcatMap$ConcatMapInner(c cVar) {
        this.parent = cVar;
    }

    @Override // p.rg2
    public final void onComplete() {
        long j = this.produced;
        if (j != 0) {
            this.produced = 0L;
            f(j);
        }
        FlowableConcatMap$BaseConcatMapSubscriber flowableConcatMap$BaseConcatMapSubscriber = (FlowableConcatMap$BaseConcatMapSubscriber) this.parent;
        flowableConcatMap$BaseConcatMapSubscriber.active = false;
        flowableConcatMap$BaseConcatMapSubscriber.e();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        long j = this.produced;
        if (j != 0) {
            this.produced = 0L;
            f(j);
        }
        this.parent.b(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.produced++;
        this.parent.c(obj);
    }
}

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import java.util.concurrent.atomic.AtomicInteger;
import p.az1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRepeat$RepeatSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final rg2 downstream;
    long produced;
    long remaining;
    final SubscriptionArbiter sa;
    final az1 source;

    public FlowableRepeat$RepeatSubscriber(rg2 rg2Var, long j, SubscriptionArbiter subscriptionArbiter, Flowable flowable) {
        this.downstream = rg2Var;
        this.sa = subscriptionArbiter;
        this.source = flowable;
        this.remaining = j;
    }

    public final void a() {
        if (getAndIncrement() == 0) {
            int iAddAndGet = 1;
            while (!this.sa.c()) {
                long j = this.produced;
                if (j != 0) {
                    this.produced = 0L;
                    this.sa.f(j);
                }
                this.source.subscribe(this);
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        long j = this.remaining;
        if (j != Long.MAX_VALUE) {
            this.remaining = j - 1;
        }
        if (j != 0) {
            a();
        } else {
            this.downstream.onComplete();
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

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p.az1;
import p.he1;
import p.rg2;
import p.tg2;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRetryBiPredicate$RetryBiSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final rg2 downstream;
    final io.reactivex.rxjava3.functions.d predicate;
    long produced;
    int retries;
    final SubscriptionArbiter sa;
    final az1 source;

    public FlowableRetryBiPredicate$RetryBiSubscriber(rg2 rg2Var, io.reactivex.rxjava3.functions.d dVar, SubscriptionArbiter subscriptionArbiter, Flowable flowable) {
        this.downstream = rg2Var;
        this.sa = subscriptionArbiter;
        this.source = flowable;
        this.predicate = dVar;
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
        this.downstream.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        try {
            io.reactivex.rxjava3.functions.d dVar = this.predicate;
            int i = this.retries + 1;
            this.retries = i;
            Integer numValueOf = Integer.valueOf(i);
            ((u11) dVar).getClass();
            if (Objects.equals(numValueOf, th)) {
                a();
            } else {
                this.downstream.onError(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
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

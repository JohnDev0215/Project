package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class t0 implements FlowableSubscriber, tg2 {
    public final rg2 c;
    public long e;
    public tg2 k;

    public t0(rg2 rg2Var, long j) {
        this.c = rg2Var;
        this.e = j;
    }

    @Override // p.tg2
    public final void a(long j) {
        this.k.a(j);
    }

    @Override // p.tg2
    public final void cancel() {
        this.k.cancel();
    }

    @Override // p.rg2
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        long j = this.e;
        if (j != 0) {
            this.e = j - 1;
        } else {
            this.c.onNext(obj);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.k, tg2Var)) {
            long j = this.e;
            this.k = tg2Var;
            this.c.onSubscribe(this);
            tg2Var.a(j);
        }
    }
}

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class l extends Flowable implements io.reactivex.rxjava3.operators.e {
    public static final l e = new l();

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return null;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        rg2Var.onSubscribe(EmptySubscription.c);
        rg2Var.onComplete();
    }
}

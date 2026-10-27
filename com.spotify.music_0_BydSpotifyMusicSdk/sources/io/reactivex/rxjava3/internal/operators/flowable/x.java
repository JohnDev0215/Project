package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.ScalarSubscription;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class x extends Flowable implements io.reactivex.rxjava3.operators.e {
    public final Object e;

    public x(Object obj) {
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return this.e;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        rg2Var.onSubscribe(new ScalarSubscription(this.e, rg2Var));
    }
}

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends a implements io.reactivex.rxjava3.functions.g {
    public final b0 k;

    public b0(s sVar) {
        super(sVar);
        this.k = this;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe((FlowableSubscriber) new FlowableOnBackpressureDrop$BackpressureDropSubscriber(rg2Var, this.k));
    }
}

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class q0 extends a {
    public final Object k;
    public final boolean l;

    public q0(Flowable flowable, Object obj) {
        super(flowable);
        this.k = obj;
        this.l = true;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe((FlowableSubscriber) new FlowableSingle$SingleElementSubscriber(rg2Var, this.k, this.l));
    }
}

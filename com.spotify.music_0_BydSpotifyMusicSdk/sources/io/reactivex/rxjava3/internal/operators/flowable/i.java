package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class i extends a {
    public final long k;
    public final Object l;
    public final boolean m;

    public i(Flowable flowable, long j, Object obj) {
        super(flowable);
        this.k = j;
        this.l = obj;
        this.m = true;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe((FlowableSubscriber) new FlowableElementAt$ElementAtSubscriber(rg2Var, this.k, this.l, this.m));
    }
}

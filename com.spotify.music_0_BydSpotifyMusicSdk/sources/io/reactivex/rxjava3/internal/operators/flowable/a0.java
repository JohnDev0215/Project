package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends a {
    public final int k;
    public final boolean l;
    public final boolean m;
    public final io.reactivex.rxjava3.functions.a n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(Flowable flowable, int i) {
        super(flowable);
        io.reactivex.rxjava3.internal.functions.b bVar = io.reactivex.rxjava3.internal.functions.k.c;
        this.k = i;
        this.l = true;
        this.m = false;
        this.n = bVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe((FlowableSubscriber) new FlowableOnBackpressureBuffer$BackpressureBufferSubscriber(rg2Var, this.k, this.l, this.m, this.n));
    }
}

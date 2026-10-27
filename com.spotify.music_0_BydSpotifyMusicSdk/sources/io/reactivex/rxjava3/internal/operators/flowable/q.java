package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class q extends a {
    public final io.reactivex.rxjava3.functions.o k;
    public final boolean l;
    public final int m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(Flowable flowable, boolean z, int i) {
        super(flowable);
        io.reactivex.rxjava3.internal.functions.d dVar = io.reactivex.rxjava3.internal.functions.k.a;
        this.k = dVar;
        this.l = z;
        this.m = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe((FlowableSubscriber) new FlowableFlatMapSingle$FlatMapSingleSubscriber(this.m, this.k, rg2Var, this.l));
    }
}

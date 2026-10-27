package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
public final class p extends a {
    public final io.reactivex.rxjava3.functions.o k;
    public final boolean l;
    public final int m;
    public final int n;

    public p(Flowable flowable, io.reactivex.rxjava3.functions.o oVar, int i, int i2) {
        super(flowable);
        this.k = oVar;
        this.l = false;
        this.m = i;
        this.n = i2;
    }

    public static <T, U> FlowableSubscriber<T> subscribe(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar, boolean z, int i, int i2) {
        return new FlowableFlatMap$MergeSubscriber(rg2Var, oVar, z, i, i2);
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        Flowable flowable = this.e;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        if (ux2.A(flowable, rg2Var, oVar)) {
            return;
        }
        flowable.subscribe(subscribe(rg2Var, oVar, this.l, this.m, this.n));
    }
}

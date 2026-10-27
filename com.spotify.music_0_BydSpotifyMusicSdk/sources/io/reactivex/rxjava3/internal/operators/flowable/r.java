package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import p.az1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class r extends Flowable {
    public final az1 e;
    public final io.reactivex.rxjava3.functions.o k;
    public final boolean l;
    public final int m;

    public r(az1 az1Var, boolean z) {
        io.reactivex.rxjava3.internal.functions.d dVar = io.reactivex.rxjava3.internal.functions.k.a;
        this.e = az1Var;
        this.k = dVar;
        this.l = z;
        this.m = Integer.MAX_VALUE;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(new FlowableFlatMapSingle$FlatMapSingleSubscriber(this.m, this.k, rg2Var, this.l));
    }
}

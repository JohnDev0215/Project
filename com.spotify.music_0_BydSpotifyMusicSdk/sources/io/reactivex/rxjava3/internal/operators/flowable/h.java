package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
public final class h extends a {
    public final io.reactivex.rxjava3.functions.o k;
    public final io.reactivex.rxjava3.functions.d l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Flowable flowable) {
        super(flowable);
        io.reactivex.rxjava3.internal.functions.d dVar = io.reactivex.rxjava3.internal.functions.k.a;
        u11 u11Var = io.reactivex.rxjava3.internal.functions.k.j;
        this.k = dVar;
        this.l = u11Var;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        boolean z = rg2Var instanceof io.reactivex.rxjava3.operators.a;
        io.reactivex.rxjava3.functions.d dVar = this.l;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        Flowable flowable = this.e;
        if (z) {
            flowable.subscribe((FlowableSubscriber) new f((io.reactivex.rxjava3.operators.a) rg2Var, oVar, dVar));
        } else {
            flowable.subscribe((FlowableSubscriber) new g(rg2Var, oVar, dVar));
        }
    }
}

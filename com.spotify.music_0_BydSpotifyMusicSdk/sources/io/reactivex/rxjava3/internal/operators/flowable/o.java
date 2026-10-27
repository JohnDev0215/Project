package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class o extends a {
    public final /* synthetic */ int k;
    public final io.reactivex.rxjava3.functions.p l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(Flowable flowable, io.reactivex.rxjava3.functions.p pVar, int i) {
        super(flowable);
        this.k = i;
        this.l = pVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.k;
        Flowable flowable = this.e;
        io.reactivex.rxjava3.functions.p pVar = this.l;
        switch (i) {
            case 0:
                if (!(rg2Var instanceof io.reactivex.rxjava3.operators.a)) {
                    flowable.subscribe((FlowableSubscriber) new n(rg2Var, pVar));
                } else {
                    flowable.subscribe((FlowableSubscriber) new m((io.reactivex.rxjava3.operators.a) rg2Var, pVar, 0));
                }
                break;
            default:
                flowable.subscribe((FlowableSubscriber) new w0(rg2Var, pVar));
                break;
        }
    }
}

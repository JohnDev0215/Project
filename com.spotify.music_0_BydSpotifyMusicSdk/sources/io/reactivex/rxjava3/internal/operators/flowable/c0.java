package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends a {
    public final /* synthetic */ int k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c0(s sVar, int i) {
        super(sVar);
        this.k = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.k;
        Flowable flowable = this.e;
        switch (i) {
            case 0:
                flowable.subscribe((FlowableSubscriber) new FlowableOnBackpressureError$BackpressureErrorSubscriber(rg2Var));
                break;
            default:
                flowable.subscribe((FlowableSubscriber) new FlowableOnBackpressureLatest$BackpressureLatestSubscriber(rg2Var));
                break;
        }
    }
}

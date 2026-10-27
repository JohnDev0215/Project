package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import p.az1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Flowable {
    public final az1[] e;
    public final boolean k = false;

    public b(az1[] az1VarArr) {
        this.e = az1VarArr;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        FlowableConcatArray$ConcatArraySubscriber flowableConcatArray$ConcatArraySubscriber = new FlowableConcatArray$ConcatArraySubscriber(this.e, this.k, rg2Var);
        rg2Var.onSubscribe(flowableConcatArray$ConcatArraySubscriber);
        flowableConcatArray$ConcatArraySubscriber.onComplete();
    }
}

package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleSource;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class l0 extends Flowable {
    public final SingleSource e;

    public l0(SingleSource singleSource) {
        this.e = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(new SingleToFlowable$SingleToFlowableObserver(rg2Var));
    }
}

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class e extends a {
    public final io.reactivex.rxjava3.functions.o k;
    public final int l;
    public final int m;
    public final ErrorMode n;

    public e(Flowable flowable, io.reactivex.rxjava3.functions.o oVar, int i, int i2, ErrorMode errorMode) {
        super(flowable);
        this.k = oVar;
        this.l = i;
        this.m = i2;
        this.n = errorMode;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe((FlowableSubscriber) new FlowableConcatMapEager$ConcatMapEagerDelayErrorSubscriber(rg2Var, this.k, this.l, this.m, this.n));
    }
}

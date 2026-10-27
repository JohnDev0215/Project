package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import p.az1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class c extends Flowable {
    public final /* synthetic */ int e;
    public final o k;
    public final ErrorMode l;
    public final int m;
    public final az1 n;

    public /* synthetic */ c(az1 az1Var, ErrorMode errorMode, int i, int i2) {
        io.reactivex.rxjava3.internal.functions.d dVar = k.a;
        this.e = i2;
        this.n = az1Var;
        this.k = dVar;
        this.l = errorMode;
        this.m = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.e;
        az1 az1Var = this.n;
        o oVar = this.k;
        ErrorMode errorMode = this.l;
        int i2 = this.m;
        switch (i) {
            case 0:
                ((Flowable) az1Var).subscribe((FlowableSubscriber) new FlowableConcatMapSingle$ConcatMapSingleSubscriber(rg2Var, oVar, i2, errorMode));
                break;
            default:
                az1Var.subscribe(new FlowableConcatMapSingle$ConcatMapSingleSubscriber(rg2Var, oVar, i2, errorMode));
                break;
        }
    }
}

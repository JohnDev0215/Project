package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.functions.k;
import p.az1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Flowable {
    public final az1 e;
    public final o k;
    public final boolean l;

    public d(az1 az1Var, boolean z) {
        io.reactivex.rxjava3.internal.functions.d dVar = k.a;
        this.e = az1Var;
        this.k = dVar;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(new FlowableSwitchMapSingle$SwitchMapSingleSubscriber(rg2Var, this.k, this.l));
    }
}

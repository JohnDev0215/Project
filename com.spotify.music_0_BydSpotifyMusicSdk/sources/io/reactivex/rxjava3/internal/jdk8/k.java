package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class k extends Flowable {
    public final Single e;
    public final io.reactivex.rxjava3.functions.o k;

    public k(Single single, io.reactivex.rxjava3.functions.o oVar) {
        this.e = single;
        this.k = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(new MaybeFlattenStreamAsFlowable$FlattenStreamMultiObserver(rg2Var, this.k));
    }
}

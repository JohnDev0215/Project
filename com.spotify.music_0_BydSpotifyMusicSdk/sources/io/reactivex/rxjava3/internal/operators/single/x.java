package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleSource;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class x extends Flowable {
    public final /* synthetic */ int e;
    public final SingleSource k;
    public final io.reactivex.rxjava3.functions.o l;

    public /* synthetic */ x(SingleSource singleSource, io.reactivex.rxjava3.functions.o oVar, int i) {
        this.e = i;
        this.k = singleSource;
        this.l = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.e;
        SingleSource singleSource = this.k;
        io.reactivex.rxjava3.functions.o oVar = this.l;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleFlatMapIterableFlowable$FlatMapIterableObserver(rg2Var, oVar));
                break;
            default:
                singleSource.subscribe(new SingleFlatMapPublisher$SingleFlatMapPublisherObserver(rg2Var, oVar));
                break;
        }
    }
}

package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class u extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final io.reactivex.rxjava3.functions.o k;

    public /* synthetic */ u(SingleSource singleSource, io.reactivex.rxjava3.functions.o oVar, int i) {
        this.c = i;
        this.k = oVar;
        this.e = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        SingleSource singleSource = this.e;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleFlatMap$SingleFlatMapCallback(singleObserver, oVar));
                break;
            case 1:
                singleSource.subscribe(new SingleResumeNext$ResumeMainSingleObserver(singleObserver, oVar));
                break;
            default:
                singleSource.subscribe(new c(singleObserver, oVar));
                break;
        }
    }
}

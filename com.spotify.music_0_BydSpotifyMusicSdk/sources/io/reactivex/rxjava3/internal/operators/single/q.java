package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class q extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final io.reactivex.rxjava3.functions.a k;

    public /* synthetic */ q(SingleSource singleSource, io.reactivex.rxjava3.functions.a aVar, int i) {
        this.c = i;
        this.e = singleSource;
        this.k = aVar;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        io.reactivex.rxjava3.functions.a aVar = this.k;
        SingleSource singleSource = this.e;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleDoFinally$DoFinallyObserver(singleObserver, aVar));
                break;
            case 1:
                singleSource.subscribe(new SingleDoOnDispose$DoOnDisposeObserver(singleObserver, aVar));
                break;
            case 2:
                singleSource.subscribe(new p(singleObserver, aVar));
                break;
            default:
                singleSource.subscribe(new c(this, singleObserver, 4));
                break;
        }
    }
}

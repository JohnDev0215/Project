package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class o extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final io.reactivex.rxjava3.functions.g k;

    public /* synthetic */ o(SingleSource singleSource, io.reactivex.rxjava3.functions.g gVar, int i) {
        this.c = i;
        this.e = singleSource;
        this.k = gVar;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        io.reactivex.rxjava3.functions.g gVar = this.k;
        SingleSource singleSource = this.e;
        switch (i) {
            case 0:
                singleSource.subscribe(new n(singleObserver, gVar));
                break;
            case 1:
                singleSource.subscribe(new c(this, singleObserver, 1));
                break;
            case 2:
                singleSource.subscribe(new s(singleObserver, gVar));
                break;
            default:
                singleSource.subscribe(new c(this, singleObserver, 3));
                break;
        }
    }
}

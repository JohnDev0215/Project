package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final Object k;
    public final Object l;

    public /* synthetic */ c0(SingleSource singleSource, Object obj, Object obj2, int i) {
        this.c = i;
        this.e = singleSource;
        this.k = obj;
        this.l = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        Object obj = this.l;
        Object obj2 = this.k;
        SingleSource singleSource = this.e;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleFlatMapNotification$FlatMapSingleObserver(singleObserver, (io.reactivex.rxjava3.functions.o) obj2, (io.reactivex.rxjava3.functions.o) obj));
                break;
            case 1:
                singleSource.subscribe(new c(this, singleObserver, 0));
                break;
            case 2:
                ((Single) singleSource).subscribe(new r(singleObserver, (io.reactivex.rxjava3.functions.g) obj2, (io.reactivex.rxjava3.functions.a) obj));
                break;
            case 3:
                singleSource.subscribe(new v(singleObserver, (io.reactivex.rxjava3.functions.o) obj2, (io.reactivex.rxjava3.functions.c) obj));
                break;
            default:
                singleSource.subscribe(new c(this, singleObserver, 6));
                break;
        }
    }
}

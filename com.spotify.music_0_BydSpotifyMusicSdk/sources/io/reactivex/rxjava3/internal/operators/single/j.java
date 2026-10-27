package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final SingleSource k;

    public /* synthetic */ j(SingleSource singleSource, SingleSource singleSource2, int i) {
        this.c = i;
        this.e = singleSource;
        this.k = singleSource2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        SingleSource singleSource = this.k;
        SingleSource singleSource2 = this.e;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleDelayWithSingle$OtherObserver(singleObserver, singleSource2));
                break;
            default:
                AtomicInteger atomicInteger = new AtomicInteger();
                Object[] objArr = {null, null};
                io.reactivex.rxjava3.disposables.a aVar = new io.reactivex.rxjava3.disposables.a();
                singleObserver.onSubscribe(aVar);
                singleSource2.subscribe(new t(0, aVar, objArr, singleObserver, atomicInteger));
                singleSource.subscribe(new t(1, aVar, objArr, singleObserver, atomicInteger));
                break;
        }
    }
}

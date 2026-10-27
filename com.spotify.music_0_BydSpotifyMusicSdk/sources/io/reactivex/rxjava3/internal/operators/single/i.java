package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import p.az1;

/* JADX INFO: loaded from: classes.dex */
public final class i extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final az1 k;

    public /* synthetic */ i(SingleSource singleSource, az1 az1Var, int i) {
        this.c = i;
        this.e = singleSource;
        this.k = az1Var;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        SingleSource singleSource = this.e;
        az1 az1Var = this.k;
        switch (i) {
            case 0:
                az1Var.subscribe(new SingleDelayWithPublisher$OtherSubscriber(singleObserver, singleSource));
                break;
            default:
                SingleTakeUntil$TakeUntilMainObserver singleTakeUntil$TakeUntilMainObserver = new SingleTakeUntil$TakeUntilMainObserver(singleObserver);
                singleObserver.onSubscribe(singleTakeUntil$TakeUntilMainObserver);
                az1Var.subscribe(singleTakeUntil$TakeUntilMainObserver.other);
                singleSource.subscribe(singleTakeUntil$TakeUntilMainObserver);
                break;
        }
    }
}

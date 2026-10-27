package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;
    public final Scheduler k;

    public /* synthetic */ h0(SingleSource singleSource, Scheduler scheduler, int i) {
        this.c = i;
        this.e = singleSource;
        this.k = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        SingleSource singleSource = this.e;
        Scheduler scheduler = this.k;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleObserveOn$ObserveOnSingleObserver(singleObserver, scheduler));
                break;
            case 1:
                SingleSubscribeOn$SubscribeOnObserver singleSubscribeOn$SubscribeOnObserver = new SingleSubscribeOn$SubscribeOnObserver(singleObserver, singleSource);
                singleObserver.onSubscribe(singleSubscribeOn$SubscribeOnObserver);
                Disposable disposableC = scheduler.c(singleSubscribeOn$SubscribeOnObserver);
                SequentialDisposable sequentialDisposable = singleSubscribeOn$SubscribeOnObserver.task;
                sequentialDisposable.getClass();
                DisposableHelper.c(sequentialDisposable, disposableC);
                break;
            default:
                singleSource.subscribe(new SingleUnsubscribeOn$UnsubscribeOnSingleObserver(singleObserver, scheduler));
                break;
        }
    }
}

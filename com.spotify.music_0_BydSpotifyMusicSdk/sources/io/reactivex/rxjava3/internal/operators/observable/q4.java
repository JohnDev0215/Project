package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class q4 extends a {
    public final /* synthetic */ int e;
    public final Scheduler k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q4(ObservableSource observableSource, Scheduler scheduler, int i) {
        super(observableSource);
        this.e = i;
        this.k = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        Scheduler scheduler = this.k;
        switch (i) {
            case 0:
                ObservableSubscribeOn$SubscribeOnObserver observableSubscribeOn$SubscribeOnObserver = new ObservableSubscribeOn$SubscribeOnObserver(observer);
                observer.onSubscribe(observableSubscribeOn$SubscribeOnObserver);
                DisposableHelper.f(observableSubscribeOn$SubscribeOnObserver, scheduler.c(new p4(this, 0, observableSubscribeOn$SubscribeOnObserver)));
                break;
            default:
                this.c.subscribe(new ObservableUnsubscribeOn$UnsubscribeObserver(observer, scheduler));
                break;
        }
    }
}

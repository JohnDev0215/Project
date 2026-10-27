package com.spotify.connectivity.productstateesperanto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import java.util.Map;
import p.dd1;
import p.gu;
import p.h80;
import p.wx1;

/* JADX INFO: loaded from: classes.dex */
public interface ProductStateDefaultModule {
    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ ObservableSource lambda$provideProductState$2(gu guVar, Observable observable) {
        int i = 1;
        return observable.take(1L).singleOrError().doOnSubscribe(new wx1(0)).doOnSuccess(new wx1(i)).flatMapObservable(new h80(observable, i));
    }

    static Observable<Map<String, String>> provideProductState(LoggedInProductStateClient loggedInProductStateClient, Scheduler scheduler, gu guVar) {
        io.reactivex.rxjava3.observables.a aVarReplay = loggedInProductStateClient.get().observeOn(scheduler).publish(new dd1(13, guVar)).replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }
}

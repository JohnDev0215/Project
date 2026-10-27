package com.spotify.connectivity.sessionstate;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import p.ed1;
import p.g80;
import p.h80;
import p.lj;

/* JADX INFO: loaded from: classes.dex */
public class DeferUntilConnected<T> implements ObservableTransformer<T, T> {
    private final Flowable<SessionState> mSessionState;

    public DeferUntilConnected(Flowable<SessionState> flowable) {
        this.mSessionState = flowable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ObservableSource lambda$apply$0(Observable observable, Boolean bool) {
        return bool.booleanValue() ? observable : Observable.empty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ObservableSource lambda$apply$1(Observable observable, Observable observable2) {
        return observable.switchMap(new h80(observable2, 2));
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<T> apply(Observable<T> observable) {
        return observable.compose(new g80(1, this.mSessionState.n().filter(new lj(19)).take(1L).map(new ed1(29))));
    }
}

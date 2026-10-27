package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import p.ed1;
import p.g80;
import p.h80;
import p.lj;

/* JADX INFO: loaded from: classes.dex */
public class DeferUntilConnected<T> implements ObservableTransformer<T, T> {
    private final ConnectionApis mConnectionApis;

    public DeferUntilConnected(ConnectionApis connectionApis) {
        this.mConnectionApis = connectionApis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$apply$0(ConnectionType connectionType) {
        return Boolean.valueOf(!connectionType.isOffline());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ObservableSource lambda$apply$2(Observable observable, Boolean bool) {
        return bool.booleanValue() ? observable : Observable.empty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ObservableSource lambda$apply$3(Observable observable) {
        return this.mConnectionApis.getConnectionTypeObservable().map(new ed1(25)).filter(new lj(18)).take(1L).switchMap(new h80(observable, 0));
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<T> apply(Observable<T> observable) {
        return observable.compose(new g80(0, this));
    }
}

package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionType;
import io.reactivex.rxjava3.android.schedulers.c;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import p.ux;
import p.vx;

/* JADX INFO: loaded from: classes.dex */
public class ConnectivityMonitorImpl {
    private final ConnectivityListener mConnectivityListener;

    public ConnectivityMonitorImpl(ConnectivityListener connectivityListener) {
        this.mConnectivityListener = connectivityListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getConnectionTypes$0(ObservableEmitter observableEmitter, ConnectionType connectionType, boolean z) {
        if (observableEmitter.isDisposed()) {
            return;
        }
        observableEmitter.onNext(connectionType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getConnectionTypes$1(ConnectivityObserver connectivityObserver) {
        this.mConnectivityListener.unregisterConnectivityObserver(connectivityObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getConnectionTypes$2(ObservableEmitter observableEmitter) {
        int i = 0;
        ux uxVar = new ux(observableEmitter, i);
        this.mConnectivityListener.registerConnectivityObserver(uxVar);
        observableEmitter.setCancellable(new vx(this, i, uxVar));
        observableEmitter.onNext(this.mConnectivityListener.getConnectionType());
    }

    public ConnectionType getConnectionType() {
        return this.mConnectivityListener.getConnectionType();
    }

    public Observable<ConnectionType> getConnectionTypes() {
        return Observable.create(new com.spotify.automotive.auth.a(1, this)).subscribeOn(c.a());
    }
}

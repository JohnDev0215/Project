package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionType;
import io.reactivex.rxjava3.android.schedulers.c;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import p.ed1;
import p.ux;
import p.vx;

/* JADX INFO: loaded from: classes.dex */
public final class InternetConnectivityObservableImpl {
    private final ConnectivityListener mConnectivityListener;

    public InternetConnectivityObservableImpl(ConnectivityListener connectivityListener) {
        this.mConnectivityListener = connectivityListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$create$0(ConnectionType connectionType) {
        return Boolean.valueOf(connectionType != ConnectionType.CONNECTION_TYPE_NONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createConnectionType$1(ObservableEmitter observableEmitter, ConnectionType connectionType, boolean z) {
        if (observableEmitter.isDisposed()) {
            return;
        }
        observableEmitter.onNext(connectionType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createConnectionType$2(ConnectivityObserver connectivityObserver) {
        this.mConnectivityListener.unregisterConnectivityObserver(connectivityObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createConnectionType$3(ObservableEmitter observableEmitter) {
        int i = 1;
        ux uxVar = new ux(observableEmitter, i);
        observableEmitter.setCancellable(new vx(this, i, uxVar));
        observableEmitter.onNext(this.mConnectivityListener.getConnectionType());
        this.mConnectivityListener.registerConnectivityObserver(uxVar);
    }

    public Observable<Boolean> create() {
        return createConnectionType().map(new ed1(27)).distinctUntilChanged();
    }

    public Observable<ConnectionType> createConnectionType() {
        return Observable.create(new com.spotify.automotive.auth.a(2, this)).distinctUntilChanged().subscribeOn(c.a());
    }

    public ConnectionType getNetworkConnectivity() {
        return this.mConnectivityListener.getConnectionType();
    }

    public boolean isConnected() {
        return getNetworkConnectivity() != ConnectionType.CONNECTION_TYPE_NONE;
    }
}

package com.spotify.connectivity.connectiontype;

import com.spotify.connectivity.connectiontype.ConnectionState;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.o;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RxConnectionStateImpl implements RxConnectionState {
    private final Observable<ConnectionState> connectionState;
    private final Observable<Boolean> isOnline;

    public RxConnectionStateImpl(Observable<ConnectionState> observable) {
        v03.j(observable, "connectionState");
        this.connectionState = observable;
        this.isOnline = getConnectionState().map(new o() { // from class: com.spotify.connectivity.connectiontype.RxConnectionStateImpl.isOnline.1
            @Override // io.reactivex.rxjava3.functions.o
            public final Boolean apply(ConnectionState connectionState) {
                return Boolean.valueOf(connectionState instanceof ConnectionState.Online);
            }
        }).distinctUntilChanged();
    }

    @Override // com.spotify.connectivity.connectiontype.RxConnectionState
    public Observable<ConnectionState> getConnectionState() {
        return this.connectionState;
    }

    @Override // com.spotify.connectivity.connectiontype.RxConnectionState
    public Observable<Boolean> isOnline() {
        return this.isOnline;
    }
}

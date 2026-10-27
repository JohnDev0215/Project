package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface SpotifyConnectivityManager {
    ConnectionType getConnectionType();

    Observable<ConnectionType> getConnectionTypeObservable();
}

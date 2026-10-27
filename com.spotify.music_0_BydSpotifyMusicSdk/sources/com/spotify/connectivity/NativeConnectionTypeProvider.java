package com.spotify.connectivity;

import com.spotify.connectivity.ConnectionTypeProvider;

/* JADX INFO: loaded from: classes.dex */
public final class NativeConnectionTypeProvider implements ConnectionTypeProvider {
    private long nThis;

    private NativeConnectionTypeProvider() {
    }

    @Override // com.spotify.connectivity.ConnectionTypeProvider
    public native void addObserver(ConnectionTypeProvider.Observer observer);

    public final native void destroy();

    @Override // com.spotify.connectivity.ConnectionTypeProvider
    public native ConnectionType getConnectionType();

    @Override // com.spotify.connectivity.ConnectionTypeProvider
    public native void removeObserver(ConnectionTypeProvider.Observer observer);
}

package com.spotify.connectivity;

import com.spotify.connectivity.ConnectivityPolicyProvider;

/* JADX INFO: loaded from: classes.dex */
public final class NativeConnectivityPolicyProvider implements ConnectivityPolicyProvider {
    private long nThis;

    private NativeConnectivityPolicyProvider() {
    }

    @Override // com.spotify.connectivity.ConnectivityPolicyProvider
    public native void addObserver(ConnectivityPolicyProvider.Observer observer);

    public final native void destroy();

    @Override // com.spotify.connectivity.ConnectivityPolicyProvider
    public native ConnectivityPolicy getConnectivityPolicy();

    @Override // com.spotify.connectivity.ConnectivityPolicyProvider
    public native void removeObserver(ConnectivityPolicyProvider.Observer observer);
}

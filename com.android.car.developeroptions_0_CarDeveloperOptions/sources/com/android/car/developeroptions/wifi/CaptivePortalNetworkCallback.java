package com.android.car.developeroptions.wifi;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class CaptivePortalNetworkCallback extends ConnectivityManager.NetworkCallback {
    private final ConnectedAccessPointPreference mConnectedApPreference;
    private boolean mIsCaptivePortal;
    private final Network mNetwork;

    CaptivePortalNetworkCallback(Network network, ConnectedAccessPointPreference connectedAccessPointPreference) {
        Objects.requireNonNull(network);
        this.mNetwork = network;
        Objects.requireNonNull(connectedAccessPointPreference);
        this.mConnectedApPreference = connectedAccessPointPreference;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        if (this.mNetwork.equals(network)) {
            this.mIsCaptivePortal = false;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        if (this.mNetwork.equals(network)) {
            boolean zCanSignIntoNetwork = WifiUtils.canSignIntoNetwork(networkCapabilities);
            this.mIsCaptivePortal = zCanSignIntoNetwork;
            this.mConnectedApPreference.setCaptivePortal(zCanSignIntoNetwork);
        }
    }

    public boolean isSameNetworkAndPreference(Network network, ConnectedAccessPointPreference connectedAccessPointPreference) {
        return this.mNetwork.equals(network) && this.mConnectedApPreference == connectedAccessPointPreference;
    }

    public boolean isCaptivePortal() {
        return this.mIsCaptivePortal;
    }

    public Network getNetwork() {
        return this.mNetwork;
    }
}

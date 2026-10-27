package com.spotify.connectivity.platformconnectiontype;

import android.net.Network;
import android.net.NetworkCapabilities;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkCapabilitiesChanged extends NetworkCallbackEvent {
    private final NetworkCapabilities capabilities;
    private final Network network;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super(null);
        v03.j(network, "network");
        v03.j(networkCapabilities, "capabilities");
        this.network = network;
        this.capabilities = networkCapabilities;
    }

    public static /* synthetic */ NetworkCapabilitiesChanged copy$default(NetworkCapabilitiesChanged networkCapabilitiesChanged, Network network, NetworkCapabilities networkCapabilities, int i, Object obj) {
        if ((i & 1) != 0) {
            network = networkCapabilitiesChanged.network;
        }
        if ((i & 2) != 0) {
            networkCapabilities = networkCapabilitiesChanged.capabilities;
        }
        return networkCapabilitiesChanged.copy(network, networkCapabilities);
    }

    public final Network component1() {
        return this.network;
    }

    public final NetworkCapabilities component2() {
        return this.capabilities;
    }

    public final NetworkCapabilitiesChanged copy(Network network, NetworkCapabilities networkCapabilities) {
        v03.j(network, "network");
        v03.j(networkCapabilities, "capabilities");
        return new NetworkCapabilitiesChanged(network, networkCapabilities);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkCapabilitiesChanged)) {
            return false;
        }
        NetworkCapabilitiesChanged networkCapabilitiesChanged = (NetworkCapabilitiesChanged) obj;
        return v03.b(this.network, networkCapabilitiesChanged.network) && v03.b(this.capabilities, networkCapabilitiesChanged.capabilities);
    }

    public final NetworkCapabilities getCapabilities() {
        return this.capabilities;
    }

    public final Network getNetwork() {
        return this.network;
    }

    public int hashCode() {
        return this.capabilities.hashCode() + (this.network.hashCode() * 31);
    }

    public String toString() {
        return "NetworkCapabilitiesChanged(network=" + this.network + ", capabilities=" + this.capabilities + ')';
    }
}

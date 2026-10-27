package com.spotify.connectivity.platformconnectiontype;

import android.net.Network;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkLost extends NetworkCallbackEvent {
    private final Network network;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkLost(Network network) {
        super(null);
        v03.j(network, "network");
        this.network = network;
    }

    public static /* synthetic */ NetworkLost copy$default(NetworkLost networkLost, Network network, int i, Object obj) {
        if ((i & 1) != 0) {
            network = networkLost.network;
        }
        return networkLost.copy(network);
    }

    public final Network component1() {
        return this.network;
    }

    public final NetworkLost copy(Network network) {
        v03.j(network, "network");
        return new NetworkLost(network);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof NetworkLost) && v03.b(this.network, ((NetworkLost) obj).network);
    }

    public final Network getNetwork() {
        return this.network;
    }

    public int hashCode() {
        return this.network.hashCode();
    }

    public String toString() {
        return "NetworkLost(network=" + this.network + ')';
    }
}

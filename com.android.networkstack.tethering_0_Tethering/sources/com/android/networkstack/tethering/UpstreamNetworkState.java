package com.android.networkstack.tethering;

import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;

/* JADX INFO: loaded from: classes.dex */
public class UpstreamNetworkState {
    public final LinkProperties linkProperties;
    public final Network network;
    public final NetworkCapabilities networkCapabilities;

    public UpstreamNetworkState(LinkProperties linkProperties, NetworkCapabilities networkCapabilities, Network network) {
        this.linkProperties = linkProperties;
        this.networkCapabilities = networkCapabilities;
        this.network = network;
    }

    public String toString() {
        Object[] objArr = new Object[3];
        Object obj = this.network;
        if (obj == null) {
            obj = "null";
        }
        objArr[0] = obj;
        Object obj2 = this.networkCapabilities;
        if (obj2 == null) {
            obj2 = "null";
        }
        objArr[1] = obj2;
        LinkProperties linkProperties = this.linkProperties;
        objArr[2] = linkProperties != null ? linkProperties : "null";
        return String.format("UpstreamNetworkState{%s, %s, %s}", objArr);
    }
}

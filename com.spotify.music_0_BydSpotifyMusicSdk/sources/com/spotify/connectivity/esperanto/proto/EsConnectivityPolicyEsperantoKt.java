package com.spotify.connectivity.esperanto.proto;

import com.spotify.esperanto.esperanto.Transport;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EsConnectivityPolicyEsperantoKt {
    public static final ConnectivityPolicyClient createConnectivityPolicyClient(Transport transport) {
        v03.j(transport, "transport");
        return new ConnectivityPolicyClientImpl(transport);
    }
}

package com.spotify.connectivity.connectiontypeflags;

import p.ge2;
import p.z90;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypePropertiesKt {
    private static final ge2 netCapabilitiesValidatedDisabledKey;
    private static final ge2 shouldUseSingleThreadKey;

    static {
        z90 z90Var = ge2.b;
        netCapabilitiesValidatedDisabledKey = z90Var.o("NET_CAPABILITIES_VALIDATED_DISABLED");
        shouldUseSingleThreadKey = z90Var.o("LISTENER_SHOULD_USE_SINGLE_THREAD");
    }
}

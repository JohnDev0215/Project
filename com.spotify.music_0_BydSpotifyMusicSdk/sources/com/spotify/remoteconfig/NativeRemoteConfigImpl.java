package com.spotify.remoteconfig;

import com.spotify.esperanto.esperanto.Transport;
import p.a32;
import p.gm1;
import p.v03;
import p.z22;

/* JADX INFO: loaded from: classes.dex */
public final class NativeRemoteConfigImpl implements NativeRemoteConfig {
    public static final gm1 Companion = new gm1();
    private long nThis;
    private z22 resolveClient;

    private NativeRemoteConfigImpl() {
    }

    public static final NativeRemoteConfigImpl create(Transport transport) {
        Companion.getClass();
        v03.j(transport, "service");
        NativeRemoteConfigImpl nativeRemoteConfigImplCreateInternal = createInternal(transport);
        nativeRemoteConfigImplCreateInternal.initialize();
        return nativeRemoteConfigImplCreateInternal;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final native NativeRemoteConfigImpl createInternal(Transport transport);

    private final native Transport getTransportToNative();

    /* JADX INFO: Access modifiers changed from: private */
    public final void initialize() {
        Transport transportToNative = getTransportToNative();
        v03.j(transportToNative, "transport");
        this.resolveClient = new a32(transportToNative);
    }

    @Override // com.spotify.remoteconfig.NativeRemoteConfig
    public native void destroy();

    @Override // com.spotify.remoteconfig.NativeRemoteConfig
    public long getNThis() {
        return this.nThis;
    }

    public final z22 getResolveClient() {
        z22 z22Var = this.resolveClient;
        if (z22Var != null) {
            return z22Var;
        }
        v03.Q("resolveClient");
        throw null;
    }
}

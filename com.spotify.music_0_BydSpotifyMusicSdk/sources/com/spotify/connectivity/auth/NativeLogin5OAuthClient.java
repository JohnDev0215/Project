package com.spotify.connectivity.auth;

import com.spotify.esperanto.esperanto.Transport;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLogin5OAuthClient {
    private final Transport internalTransportToNative;
    private long nThis;

    private NativeLogin5OAuthClient() {
    }

    public final native void destroy();

    public final native Transport getInternalTransportToNative();
}

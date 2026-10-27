package com.spotify.automotive.socialconnect.endpoint;

import p.e52;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class SocialConnectEndpointImpl_Factory implements ul0 {
    private final xy1 retrofitProvider;

    public SocialConnectEndpointImpl_Factory(xy1 xy1Var) {
        this.retrofitProvider = xy1Var;
    }

    public static SocialConnectEndpointImpl_Factory create(xy1 xy1Var) {
        return new SocialConnectEndpointImpl_Factory(xy1Var);
    }

    public static SocialConnectEndpointImpl newInstance(e52 e52Var) {
        return new SocialConnectEndpointImpl(e52Var);
    }

    @Override // p.xy1
    public SocialConnectEndpointImpl get() {
        return newInstance((e52) this.retrofitProvider.get());
    }
}

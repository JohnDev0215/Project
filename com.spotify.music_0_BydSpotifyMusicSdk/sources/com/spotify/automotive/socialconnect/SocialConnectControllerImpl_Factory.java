package com.spotify.automotive.socialconnect;

import com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint;
import p.st1;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class SocialConnectControllerImpl_Factory implements ul0 {
    private final xy1 playbackControllerProvider;
    private final xy1 socialConnectEndpointProvider;

    public SocialConnectControllerImpl_Factory(xy1 xy1Var, xy1 xy1Var2) {
        this.playbackControllerProvider = xy1Var;
        this.socialConnectEndpointProvider = xy1Var2;
    }

    public static SocialConnectControllerImpl_Factory create(xy1 xy1Var, xy1 xy1Var2) {
        return new SocialConnectControllerImpl_Factory(xy1Var, xy1Var2);
    }

    public static SocialConnectControllerImpl newInstance(st1 st1Var, SocialConnectEndpoint socialConnectEndpoint) {
        return new SocialConnectControllerImpl(st1Var, socialConnectEndpoint);
    }

    @Override // p.xy1
    public SocialConnectControllerImpl get() {
        return newInstance((st1) this.playbackControllerProvider.get(), (SocialConnectEndpoint) this.socialConnectEndpointProvider.get());
    }
}

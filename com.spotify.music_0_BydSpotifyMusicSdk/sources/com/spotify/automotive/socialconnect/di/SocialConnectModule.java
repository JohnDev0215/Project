package com.spotify.automotive.socialconnect.di;

import com.spotify.automotive.socialconnect.SocialConnectController;
import com.spotify.automotive.socialconnect.SocialConnectControllerImpl;
import com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint;
import com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpointImpl;

/* JADX INFO: loaded from: classes.dex */
public interface SocialConnectModule {
    SocialConnectController bindSocialConnectController(SocialConnectControllerImpl socialConnectControllerImpl);

    SocialConnectEndpoint bindSocialConnectEndpoint(SocialConnectEndpointImpl socialConnectEndpointImpl);
}

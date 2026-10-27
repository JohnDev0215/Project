package com.spotify.automotive.socialconnect.endpoint;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import p.d52;
import p.e52;
import p.u42;

/* JADX INFO: loaded from: classes.dex */
public class SocialConnectEndpointImpl implements SocialConnectEndpoint {
    private final SocialConnectEndpoint mEndpoint;

    public SocialConnectEndpointImpl(e52 e52Var) {
        e52Var.getClass();
        this.mEndpoint = (SocialConnectEndpoint) new d52(e52Var).e().b(SocialConnectEndpoint.class);
    }

    @Override // com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint
    public Completable deleteSession(String str, String str2) {
        return this.mEndpoint.deleteSession(str, str2);
    }

    @Override // com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint
    public Single<u42<SocialConnectSession>> getCurrentOrNewSession(String str, String str2) {
        return this.mEndpoint.getCurrentOrNewSession(str, str2);
    }

    @Override // com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint
    public Single<u42<SocialConnectSession>> getCurrentSession(String str) {
        return this.mEndpoint.getCurrentSession(str);
    }

    @Override // com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint
    public Completable leaveSession(String str, String str2) {
        return this.mEndpoint.leaveSession(str, str2);
    }
}

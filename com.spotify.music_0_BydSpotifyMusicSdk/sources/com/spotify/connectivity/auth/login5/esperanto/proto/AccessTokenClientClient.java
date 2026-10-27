package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface AccessTokenClientClient {
    Single<EsAccessTokenClient.AccessTokenResponse> getToken(EsAccessTokenClient.GetTokenRequest getTokenRequest);
}

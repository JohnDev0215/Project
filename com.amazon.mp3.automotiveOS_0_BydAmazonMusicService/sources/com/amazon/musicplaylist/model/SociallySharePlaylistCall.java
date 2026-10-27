package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class SociallySharePlaylistCall extends CoralCall<SociallySharePlaylistRequest, SociallySharePlaylistResponse> {
    public SociallySharePlaylistCall(URL url, SociallySharePlaylistRequest sociallySharePlaylistRequest, RequestInterceptor requestInterceptor) {
        this(url, sociallySharePlaylistRequest, requestInterceptor, false);
    }

    public SociallySharePlaylistCall(URL url, SociallySharePlaylistRequest sociallySharePlaylistRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, sociallySharePlaylistRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new SociallySharePlaylistApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<SociallySharePlaylistResponse> getResponseType() {
        return SociallySharePlaylistResponse.class;
    }
}

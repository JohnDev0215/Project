package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataCall extends CoralCall<ArtistDetailsMetadataRequest, ArtistDetailsMetadataResponse> {
    public ArtistDetailsMetadataCall(URL url, ArtistDetailsMetadataRequest artistDetailsMetadataRequest, RequestInterceptor requestInterceptor) {
        this(url, artistDetailsMetadataRequest, requestInterceptor, false);
    }

    public ArtistDetailsMetadataCall(URL url, ArtistDetailsMetadataRequest artistDetailsMetadataRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, artistDetailsMetadataRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new ArtistDetailsMetadataApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<ArtistDetailsMetadataResponse> getResponseType() {
        return ArtistDetailsMetadataResponse.class;
    }
}

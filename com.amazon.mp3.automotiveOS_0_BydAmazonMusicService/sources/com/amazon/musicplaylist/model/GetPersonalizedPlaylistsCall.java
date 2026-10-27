package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetPersonalizedPlaylistsCall extends CoralCall<GetPersonalizedPlaylistsRequest, GetPersonalizedPlaylistsResponse> {
    public GetPersonalizedPlaylistsCall(URL url, GetPersonalizedPlaylistsRequest getPersonalizedPlaylistsRequest, RequestInterceptor requestInterceptor) {
        this(url, getPersonalizedPlaylistsRequest, requestInterceptor, false);
    }

    public GetPersonalizedPlaylistsCall(URL url, GetPersonalizedPlaylistsRequest getPersonalizedPlaylistsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getPersonalizedPlaylistsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetPersonalizedPlaylistsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetPersonalizedPlaylistsResponse> getResponseType() {
        return GetPersonalizedPlaylistsResponse.class;
    }
}

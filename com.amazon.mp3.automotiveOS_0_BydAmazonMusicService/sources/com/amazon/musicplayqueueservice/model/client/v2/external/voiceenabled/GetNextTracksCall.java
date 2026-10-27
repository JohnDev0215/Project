package com.amazon.musicplayqueueservice.model.client.v2.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.v2.track.AbstractGetNextTracksRequest;
import com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetNextTracksCall extends CoralCall<AbstractGetNextTracksRequest, GetNextTracksResponse> {
    public GetNextTracksCall(URL url, AbstractGetNextTracksRequest abstractGetNextTracksRequest, RequestInterceptor requestInterceptor) {
        this(url, abstractGetNextTracksRequest, requestInterceptor, false);
    }

    public GetNextTracksCall(URL url, AbstractGetNextTracksRequest abstractGetNextTracksRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, abstractGetNextTracksRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetNextTracksApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetNextTracksResponse> getResponseType() {
        return GetNextTracksResponse.class;
    }
}

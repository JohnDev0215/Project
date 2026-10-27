package com.amazon.musicplayqueueservice.model.client.external.nonvoiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksRequest;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetNextTracksCall extends CoralCall<GetNextTracksRequest, GetNextTracksResponse> {
    public GetNextTracksCall(URL url, GetNextTracksRequest getNextTracksRequest, RequestInterceptor requestInterceptor) {
        this(url, getNextTracksRequest, requestInterceptor, false);
    }

    public GetNextTracksCall(URL url, GetNextTracksRequest getNextTracksRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getNextTracksRequest, requestInterceptor, z);
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

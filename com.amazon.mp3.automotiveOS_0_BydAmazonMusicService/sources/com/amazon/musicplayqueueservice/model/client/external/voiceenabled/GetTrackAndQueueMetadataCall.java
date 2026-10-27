package com.amazon.musicplayqueueservice.model.client.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataRequest;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetTrackAndQueueMetadataCall extends CoralCall<GetTrackAndQueueMetadataRequest, GetTrackAndQueueMetadataResponse> {
    public GetTrackAndQueueMetadataCall(URL url, GetTrackAndQueueMetadataRequest getTrackAndQueueMetadataRequest, RequestInterceptor requestInterceptor) {
        this(url, getTrackAndQueueMetadataRequest, requestInterceptor, false);
    }

    public GetTrackAndQueueMetadataCall(URL url, GetTrackAndQueueMetadataRequest getTrackAndQueueMetadataRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getTrackAndQueueMetadataRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetTrackAndQueueMetadataApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetTrackAndQueueMetadataResponse> getResponseType() {
        return GetTrackAndQueueMetadataResponse.class;
    }
}

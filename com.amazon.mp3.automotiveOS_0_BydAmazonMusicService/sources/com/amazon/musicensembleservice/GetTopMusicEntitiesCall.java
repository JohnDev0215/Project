package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetTopMusicEntitiesCall extends CoralCall<GetTopMusicEntitiesRequest, GetTopMusicEntitiesResponse> {
    public GetTopMusicEntitiesCall(URL url, GetTopMusicEntitiesRequest getTopMusicEntitiesRequest, RequestInterceptor requestInterceptor) {
        this(url, getTopMusicEntitiesRequest, requestInterceptor, false);
    }

    public GetTopMusicEntitiesCall(URL url, GetTopMusicEntitiesRequest getTopMusicEntitiesRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getTopMusicEntitiesRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetTopMusicEntitiesApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetTopMusicEntitiesResponse> getResponseType() {
        return GetTopMusicEntitiesResponse.class;
    }
}

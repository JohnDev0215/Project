package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.storeservice.model.GetStationRecommendationsRequest;
import com.amazon.music.storeservice.model.GetStationRecommendationsResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetStationRecommendationsCall extends CoralCall<GetStationRecommendationsRequest, GetStationRecommendationsResponse> {
    public GetStationRecommendationsCall(URL url, GetStationRecommendationsRequest getStationRecommendationsRequest, RequestInterceptor requestInterceptor) {
        this(url, getStationRecommendationsRequest, requestInterceptor, false);
    }

    public GetStationRecommendationsCall(URL url, GetStationRecommendationsRequest getStationRecommendationsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getStationRecommendationsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetStationRecommendationsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetStationRecommendationsResponse> getResponseType() {
        return GetStationRecommendationsResponse.class;
    }
}

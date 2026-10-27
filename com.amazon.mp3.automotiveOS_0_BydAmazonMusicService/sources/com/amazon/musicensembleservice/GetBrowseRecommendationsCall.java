package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsRequest;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetBrowseRecommendationsCall extends CoralCall<GetBrowseRecommendationsRequest, GetBrowseRecommendationsResponse> {
    public GetBrowseRecommendationsCall(URL url, GetBrowseRecommendationsRequest getBrowseRecommendationsRequest, RequestInterceptor requestInterceptor) {
        this(url, getBrowseRecommendationsRequest, requestInterceptor, false);
    }

    public GetBrowseRecommendationsCall(URL url, GetBrowseRecommendationsRequest getBrowseRecommendationsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getBrowseRecommendationsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetBrowseRecommendationsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetBrowseRecommendationsResponse> getResponseType() {
        return GetBrowseRecommendationsResponse.class;
    }
}

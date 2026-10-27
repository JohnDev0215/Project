package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequest;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetSimilarityRecommendationsCall extends CoralCall<GetSimilarityRecommendationsRequest, GetSimilarityRecommendationsResponse> {
    public GetSimilarityRecommendationsCall(URL url, GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest, RequestInterceptor requestInterceptor) {
        this(url, getSimilarityRecommendationsRequest, requestInterceptor, false);
    }

    public GetSimilarityRecommendationsCall(URL url, GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getSimilarityRecommendationsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetSimilarityRecommendationsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetSimilarityRecommendationsResponse> getResponseType() {
        return GetSimilarityRecommendationsResponse.class;
    }
}

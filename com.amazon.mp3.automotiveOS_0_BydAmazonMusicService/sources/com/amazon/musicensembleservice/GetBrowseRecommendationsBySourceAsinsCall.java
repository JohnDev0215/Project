package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsRequest;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetBrowseRecommendationsBySourceAsinsCall extends CoralCall<GetBrowseRecommendationsBySourceAsinsRequest, GetBrowseRecommendationsBySourceAsinsResponse> {
    public GetBrowseRecommendationsBySourceAsinsCall(URL url, GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest, RequestInterceptor requestInterceptor) {
        this(url, getBrowseRecommendationsBySourceAsinsRequest, requestInterceptor, false);
    }

    public GetBrowseRecommendationsBySourceAsinsCall(URL url, GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getBrowseRecommendationsBySourceAsinsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetBrowseRecommendationsBySourceAsinsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetBrowseRecommendationsBySourceAsinsResponse> getResponseType() {
        return GetBrowseRecommendationsBySourceAsinsResponse.class;
    }
}

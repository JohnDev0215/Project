package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetSongRecommendationsCall extends CoralCall<GetSongRecommendationsRequest, GetSongRecommendationsResponse> {
    public GetSongRecommendationsCall(URL url, GetSongRecommendationsRequest getSongRecommendationsRequest, RequestInterceptor requestInterceptor) {
        this(url, getSongRecommendationsRequest, requestInterceptor, false);
    }

    public GetSongRecommendationsCall(URL url, GetSongRecommendationsRequest getSongRecommendationsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getSongRecommendationsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetSongRecommendationsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetSongRecommendationsResponse> getResponseType() {
        return GetSongRecommendationsResponse.class;
    }
}

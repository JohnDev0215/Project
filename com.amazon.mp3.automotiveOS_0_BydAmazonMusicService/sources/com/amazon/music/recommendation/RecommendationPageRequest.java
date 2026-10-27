package com.amazon.music.recommendation;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class RecommendationPageRequest {
    private final int nextPageToken;
    private final RecommendationRequest request;
    private final String widgetId;

    public RecommendationPageRequest(RecommendationRequest recommendationRequest, int i, String str) {
        Validate.isTrue(recommendationRequest != null, "Request cannot be null", new Object[0]);
        this.request = recommendationRequest;
        this.nextPageToken = i;
        this.widgetId = str;
    }

    RecommendationRequest getRequest() {
        return this.request;
    }

    int getNextPageToken() {
        return this.nextPageToken;
    }

    String getWidgetId() {
        return this.widgetId;
    }
}

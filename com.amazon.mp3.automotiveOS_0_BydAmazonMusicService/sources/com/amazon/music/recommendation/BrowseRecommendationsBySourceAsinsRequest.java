package com.amazon.music.recommendation;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseRecommendationsBySourceAsinsRequest implements RecommendationRequest {
    private final String deviceId;
    private final String deviceType;
    private boolean isExplicitFilterEnabled;
    private final int maxCount;
    private String musicRequestIdentityContextToken;
    private final String requestedContent;
    private final List<String> sourceAsins;

    int getMaxCount() {
        return this.maxCount;
    }

    String getRequestedContent() {
        return this.requestedContent;
    }

    String getDeviceId() {
        return this.deviceId;
    }

    String getDeviceType() {
        return this.deviceType;
    }

    List<String> getSourceAsins() {
        return this.sourceAsins;
    }

    boolean isExplicitFilterEnabled() {
        return this.isExplicitFilterEnabled;
    }

    String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }
}

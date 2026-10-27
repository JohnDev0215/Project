package com.amazon.music.recommendation;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseRequest implements RecommendationRequest {
    private final String deviceId;
    private final String deviceType;
    private boolean isExplicitFilterEnabled;
    private final int maxCount;
    private String musicRequestIdentityContextToken;
    private final String requestedContent;

    public BrowseRequest(int i, RecommendationsContentSelector recommendationsContentSelector, String str, String str2) {
        Validate.isTrue(i > 0, "maxCount must be strictly positive", new Object[0]);
        this.deviceId = (String) Validate.notEmpty(str, "deviceId cannot be empty or null", new Object[0]);
        this.deviceType = (String) Validate.notEmpty(str2, "deviceType cannot be empty or null", new Object[0]);
        this.maxCount = i;
        this.requestedContent = recommendationsContentSelector.value;
    }

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

    boolean isExplicitFilterEnabled() {
        return this.isExplicitFilterEnabled;
    }

    String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public BrowseRequest setExplicitFilterEnabled(boolean z) {
        this.isExplicitFilterEnabled = z;
        return this;
    }

    public BrowseRequest setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
        return this;
    }
}

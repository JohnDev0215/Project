package com.amazon.music.recommendation;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class SimilarityRequest implements RecommendationRequest {
    private final String asin;
    private final String deviceId;
    private final String deviceType;
    private boolean isExplicitFilterEnabled;
    private final int itemsCount;
    private String musicRequestIdentityContextToken;
    private final String requestedContent;

    public SimilarityRequest(int i, String str, RecommendationsContentSelector recommendationsContentSelector, String str2, String str3) {
        Validate.isTrue(i > 0, "itemsCount must be strictly positive.", new Object[0]);
        Validate.notNull(str, "asin can't be null", new Object[0]);
        this.deviceId = (String) Validate.notEmpty(str2, "deviceId cannot be empty or null", new Object[0]);
        this.deviceType = (String) Validate.notEmpty(str3, "deviceType cannot be empty or null", new Object[0]);
        this.itemsCount = i;
        this.asin = str;
        this.requestedContent = recommendationsContentSelector.value;
    }

    int getItemsCount() {
        return this.itemsCount;
    }

    String getAsin() {
        return this.asin;
    }

    String getRequestedContent() {
        return this.requestedContent;
    }

    String getDeviceType() {
        return this.deviceType;
    }

    String getDeviceId() {
        return this.deviceId;
    }

    boolean isExplicitFilterEnabled() {
        return this.isExplicitFilterEnabled;
    }

    String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public SimilarityRequest setExplicitFilterEnabled(boolean z) {
        this.isExplicitFilterEnabled = z;
        return this;
    }

    public SimilarityRequest setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
        return this;
    }
}

package com.amazon.music.browse;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseItemRequest {
    private final String deviceId;
    private final String deviceType;
    private List<String> features = new ArrayList();
    private boolean isExplicitFilterEnabled;
    private final int itemsPerPage;
    private final String lang;
    private String musicRequestIdentityContextToken;
    private final RankType rankType;
    private String refinement;
    private final String requestedContent;

    @Deprecated
    public BrowseItemRequest(RankType rankType, int i, BrowseContentSelector browseContentSelector, String str, String str2, String str3) {
        Validate.notNull(rankType, "rankType can't be null", new Object[0]);
        Validate.isTrue(i > 0, "itemsPerPage must be positive", new Object[0]);
        Validate.notEmpty(str3, "Language cannot be null or empty", new Object[0]);
        this.deviceId = (String) Validate.notEmpty(str, "deviceId cannot be null or empty", new Object[0]);
        this.deviceType = (String) Validate.notEmpty(str2, "deviceType cannot be null or empty", new Object[0]);
        this.rankType = rankType;
        this.itemsPerPage = i;
        this.requestedContent = browseContentSelector.value;
        this.lang = str3;
    }

    public BrowseItemRequest setRefinement(String str) {
        this.refinement = str;
        return this;
    }

    public BrowseItemRequest setExplicitFilterEnabled(boolean z) {
        this.isExplicitFilterEnabled = z;
        return this;
    }

    public BrowseItemRequest setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
        return this;
    }

    RankType getRankType() {
        return this.rankType;
    }

    int getItemsPerPage() {
        return this.itemsPerPage;
    }

    String getRefinement() {
        return this.refinement;
    }

    List<String> getFeatures() {
        return this.features;
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

    String getLang() {
        return this.lang;
    }

    boolean isExplicitFilterEnabled() {
        return this.isExplicitFilterEnabled;
    }

    String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }
}

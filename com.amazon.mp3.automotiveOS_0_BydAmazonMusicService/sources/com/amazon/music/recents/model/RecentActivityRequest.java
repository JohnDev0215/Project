package com.amazon.music.recents.model;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentActivityRequest {
    private final List<ActivityType> activityTypes;
    private final List<ContentQuality> contentQualities;
    private final String deviceId;
    private final String deviceType;
    private final String musicRequestIdentityContextToken;
    private final String musicTerritory;

    public AllowedParentalControls getAllowedParentalControls() {
        return null;
    }

    private RecentActivityRequest(String str, String str2, String str3, List<ActivityType> list, List<ContentQuality> list2, String str4, AllowedParentalControls allowedParentalControls) {
        this.musicTerritory = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.activityTypes = list;
        this.contentQualities = list2;
        this.musicRequestIdentityContextToken = str4;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public List<ActivityType> getActivityTypes() {
        return this.activityTypes;
    }

    public List<ContentQuality> getContentQualities() {
        return this.contentQualities;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public static Builder createBuilder(String str, String str2, String str3) {
        return new Builder(str, str2, str3);
    }

    public static class Builder {
        private final List<ActivityType> activityTypes;
        private List<ContentQuality> contentQualities;
        private final String deviceId;
        private final String deviceType;
        private String musicRequestIdentityContextToken;
        private final String musicTerritory;

        private Builder(String str, String str2, String str3) {
            this.activityTypes = new ArrayList();
            this.contentQualities = new ArrayList();
            this.musicTerritory = str;
            this.deviceId = str2;
            this.deviceType = str3;
        }

        public Builder withContentQualities(List<ContentQuality> list) {
            this.contentQualities = list;
            return this;
        }

        public RecentActivityRequest build() {
            return new RecentActivityRequest(this.musicTerritory, this.deviceId, this.deviceType, this.activityTypes, this.contentQualities, this.musicRequestIdentityContextToken, null);
        }
    }
}

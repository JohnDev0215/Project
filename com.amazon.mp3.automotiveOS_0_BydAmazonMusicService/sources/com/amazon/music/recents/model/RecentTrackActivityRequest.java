package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentTrackActivityRequest {
    private final List<ActivityType> activityTypes;

    @Nullable
    private final List<ContentQuality> contentQualities;
    private final String deviceId;
    private final String deviceType;
    private final String musicRequestIdentityContextToken;
    private final String musicTerritory;

    @Nullable
    private final String pageToken;

    public AllowedParentalControls getAllowedParentalControls() {
        return null;
    }

    public RecentTrackActivityRequest(String str, String str2, String str3, List<ActivityType> list, @Nullable String str4, @Nullable List<ContentQuality> list2, String str5, AllowedParentalControls allowedParentalControls) {
        this.musicTerritory = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.activityTypes = list;
        this.pageToken = str4;
        this.contentQualities = list2;
        this.musicRequestIdentityContextToken = str5;
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

    @Nullable
    public String getPageToken() {
        return this.pageToken;
    }

    @Nullable
    public List<ContentQuality> getContentQualities() {
        return this.contentQualities;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public static final class Builder {
        private List<ActivityType> activityTypes;

        @Nullable
        private List<ContentQuality> contentQualities;
        private String deviceId;
        private String deviceType;
        private String musicRequestIdentityContextToken;
        private String musicTerritory;

        @Nullable
        private String pageToken;

        public Builder(String str, String str2, String str3, List<ActivityType> list) {
            this.musicTerritory = str;
            this.deviceId = str2;
            this.deviceType = str3;
            this.activityTypes = list;
        }

        public final Builder pageToken(@Nullable String str) {
            this.pageToken = str;
            return this;
        }

        public final Builder contentQualities(@Nullable List<ContentQuality> list) {
            this.contentQualities = list;
            return this;
        }

        public final RecentTrackActivityRequest build() {
            return new RecentTrackActivityRequest(this.musicTerritory, this.deviceId, this.deviceType, this.activityTypes, this.pageToken, this.contentQualities, this.musicRequestIdentityContextToken, null);
        }
    }
}

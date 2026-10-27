package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentUnfilteredTrackActivityRequest {
    private final String deviceId;
    private final String deviceType;

    @Nullable
    private final Date endTime;

    @Nullable
    private final Boolean filterMoreThanThirtySeconds;

    @Nullable
    private final Boolean flatten;

    @Nullable
    private final Integer maxResults;
    private final String musicTerritory;

    @Nullable
    private final String pageToken;

    @Nullable
    private final Date startTime;

    public RecentUnfilteredTrackActivityRequest(String str, String str2, String str3, @Nullable Date date, @Nullable Date date2, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Integer num, @Nullable String str4) {
        this.musicTerritory = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.startTime = date;
        this.endTime = date2;
        this.filterMoreThanThirtySeconds = bool;
        this.flatten = bool2;
        this.maxResults = num;
        this.pageToken = str4;
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

    @Nullable
    public Date getStartTime() {
        return this.startTime;
    }

    @Nullable
    public Date getEndTime() {
        return this.endTime;
    }

    @Nullable
    public Boolean getFilterMoreThanThirtySeconds() {
        return this.filterMoreThanThirtySeconds;
    }

    @Nullable
    public Boolean getFlatten() {
        return this.flatten;
    }

    @Nullable
    public Integer getMaxResults() {
        return this.maxResults;
    }

    @Nullable
    public String getPageToken() {
        return this.pageToken;
    }

    public static final class Builder {
        private String deviceId;
        private String deviceType;

        @Nullable
        private Date endTime;

        @Nullable
        private Boolean filterMoreThanThirtySeconds;

        @Nullable
        private Boolean flatten;

        @Nullable
        private Integer maxResults;
        private String musicTerritory;

        @Nullable
        private String pageToken;

        @Nullable
        private Date startTime;

        public Builder(String str, String str2, String str3) {
            this.musicTerritory = str;
            this.deviceId = str2;
            this.deviceType = str3;
        }

        public Builder withStartTime(Date date) {
            this.startTime = date;
            return this;
        }

        public Builder withEndTime(Date date) {
            this.endTime = date;
            return this;
        }

        public Builder withFilterMoreThanThirtySeconds(Boolean bool) {
            this.filterMoreThanThirtySeconds = bool;
            return this;
        }

        public Builder withFlatten(Boolean bool) {
            this.flatten = bool;
            return this;
        }

        public Builder withMaxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public RecentUnfilteredTrackActivityRequest build() {
            return new RecentUnfilteredTrackActivityRequest(this.musicTerritory, this.deviceId, this.deviceType, this.startTime, this.endTime, this.filterMoreThanThirtySeconds, this.flatten, this.maxResults, this.pageToken);
        }
    }
}

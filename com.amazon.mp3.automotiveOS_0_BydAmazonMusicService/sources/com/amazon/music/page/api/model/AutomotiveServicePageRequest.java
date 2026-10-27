package com.amazon.music.page.api.model;

import com.amazon.music.page.api.constants.FeatureFlag;
import com.amazon.music.page.api.model.BasePageRequest;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class AutomotiveServicePageRequest extends BasePageRequest {
    private final String appVersion;
    private final String deviceModel;
    private final String downloadedIds;
    private final String marketPlaceId;
    private final String osVersion;
    private final String platform;
    private final String podcastShowId;
    private final String presetIds;
    private final String recentMusicIds;
    private final String recentlyCreatedLibraryPlaylistIds;

    AutomotiveServicePageRequest(Builder builder) {
        super(builder);
        this.presetIds = builder.presetIds;
        this.downloadedIds = builder.downloadedIds;
        this.recentMusicIds = builder.recentMusicIds;
        this.recentlyCreatedLibraryPlaylistIds = builder.recentlyCreatedLibraryPlaylistIds;
        this.platform = builder.platform;
        this.osVersion = builder.osVersion;
        this.appVersion = builder.appVersion;
        this.deviceModel = builder.deviceModel;
        this.marketPlaceId = builder.marketPlaceId;
        this.podcastShowId = builder.podcastShowId;
    }

    public String getPresetIds() {
        return this.presetIds;
    }

    public String getDownloadedIds() {
        return this.downloadedIds;
    }

    public String getRecentMusicIds() {
        return this.recentMusicIds;
    }

    public String getRecentlyCreatedLibraryPlaylistIds() {
        return this.recentlyCreatedLibraryPlaylistIds;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getMarketPlaceId() {
        return this.marketPlaceId;
    }

    public String getPodcastShowId() {
        return this.podcastShowId;
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        private String appVersion;
        private String deviceModel;
        private String downloadedIds;
        private String marketPlaceId;
        private String osVersion;
        private String platform;
        private String podcastShowId;
        private String presetIds;
        private String recentMusicIds;
        private String recentlyCreatedLibraryPlaylistIds;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public Builder self() {
            return this;
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder setIsExplicitLanguageFilterEnabled(boolean z) {
            return super.setIsExplicitLanguageFilterEnabled(z);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withLocale(Locale locale) {
            return super.withLocale(locale);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withRegion(String str) {
            return super.withRegion(str);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withStage(String str) {
            return super.withStage(str);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withWidgetId(String str) {
            return super.withWidgetId(str);
        }

        public Builder(String str, String str2, String str3, String str4, String str5, String str6) {
            super(str, str2, str3, str4, str5, str6);
        }

        public AutomotiveServicePageRequest build() {
            return new AutomotiveServicePageRequest(this);
        }

        public Builder withPresetIds(String str) {
            this.presetIds = str;
            return this;
        }

        public Builder withDownloadedIds(String str) {
            this.downloadedIds = str;
            return this;
        }

        public Builder withRecentMusicIds(String str) {
            this.recentMusicIds = str;
            return this;
        }

        public Builder withRecentlyCreatedLibraryPlaylistIds(String str) {
            this.recentlyCreatedLibraryPlaylistIds = str;
            return this;
        }

        public Builder withPlatform(String str) {
            this.platform = str;
            return this;
        }

        public Builder withOSVersion(String str) {
            this.osVersion = str;
            return this;
        }

        public Builder withAppVersion(String str) {
            this.appVersion = str;
            return this;
        }

        public Builder withDeviceModel(String str) {
            this.deviceModel = str;
            return this;
        }

        public Builder withMarketPlaceId(String str) {
            this.marketPlaceId = str;
            return this;
        }

        public Builder withPodcastShowId(String str) {
            this.podcastShowId = str;
            return this;
        }

        public Builder withCarModeVisualShoveler(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.USE_CAR_MODE_VISUAL_SHOVELER;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }
    }
}

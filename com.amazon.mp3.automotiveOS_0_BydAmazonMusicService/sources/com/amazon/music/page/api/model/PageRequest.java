package com.amazon.music.page.api.model;

import com.amazon.music.page.api.constants.FeatureFlag;
import com.amazon.music.page.api.model.BasePageRequest;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class PageRequest extends BasePageRequest {
    private final boolean isCategoryPostEnabled;
    private final boolean isStub;
    private final Refinements refinements;
    private final String sessionId;

    private PageRequest(Builder builder) {
        super(builder);
        this.isStub = builder.isStub;
        this.isCategoryPostEnabled = builder.isCategoryPostEnabled;
        this.sessionId = builder.sessionId;
        this.refinements = builder.refinements;
    }

    public boolean isCategoryPostEnabled() {
        return this.isCategoryPostEnabled;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        private boolean isCategoryPostEnabled;
        private boolean isStub;
        private Refinements refinements;
        private String sessionId;

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
        public /* bridge */ /* synthetic */ BasePageRequest.Builder setMusicRequestIdentityContextToken(String str) {
            return super.setMusicRequestIdentityContextToken(str);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withBrowseId(String str) {
            return super.withBrowseId(str);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withCount(int i) {
            return super.withCount(i);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withLocale(Locale locale) {
            return super.withLocale(locale);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withNextToken(String str) {
            return super.withNextToken(str);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withRegion(String str) {
            return super.withRegion(str);
        }

        @Override // com.amazon.music.page.api.model.BasePageRequest.Builder
        public /* bridge */ /* synthetic */ BasePageRequest.Builder withStage(String str) {
            return super.withStage(str);
        }

        public Builder(String str, String str2, String str3, String str4, String str5, String str6) {
            super(str, str2, str3, str4, str5, str6);
        }

        public PageRequest build() {
            return new PageRequest(this);
        }

        public Builder withVideo(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.INCLUDE_VIDEO;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }

        public Builder withLive(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.INCLUDE_LIVE_STREAM;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }

        public Builder withPodcast(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.INCLUDE_PODCAST;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }

        public Builder withPodcastContentEnabled(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.INCLUDE_PODCAST_CONTENT;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }

        public Builder withSpatialAudio(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.INCLUDE_SPATIAL_AUDIO;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }

        public Builder withUseBrushV3CategoryPages(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.USE_BRUSH_V3_CATEGORY_PAGES;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }

        public Builder withRefinements(Refinements refinements) {
            this.refinements = refinements;
            return this;
        }

        public Builder isStub(boolean z) {
            this.isStub = z;
            return this;
        }

        public Builder isCategoryPostEnabled(boolean z) {
            this.isCategoryPostEnabled = z;
            return this;
        }

        public Builder withVideoStory(boolean z) {
            if (z) {
                List<String> list = this.featureFlags;
                FeatureFlag featureFlag = FeatureFlag.INCLUDE_VIDEO_STORY;
                if (!list.contains(featureFlag.getFeature())) {
                    this.featureFlags.add(featureFlag.getFeature());
                }
            }
            return this;
        }
    }
}

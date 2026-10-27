package com.amazon.avod.media.service.prsv2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import com.amazon.avod.util.SyeConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourceV2ParamsCreator implements ResourceParamsCreator {
    private final ResourceParamsCreator mGlobalParamsCreator;
    private final ResourceParamsCreator mLivePlaybackUrlsParamsCreator;
    private final ResourceParamsCreator mPlaybackDataParamsCreator;
    private final ResourceParamsCreator mRapidRecapPlaybackUrlsParamsCreator;
    private final ResourceParamsCreator mSyeUrlsParamsCreator;
    private final ResourceParamsCreator mXRayMetadataParamsCreator;

    private PlaybackResourceV2ParamsCreator(@Nonnull ResourceParamsCreator resourceParamsCreator, @Nullable ResourceParamsCreator resourceParamsCreator2, @Nullable ResourceParamsCreator resourceParamsCreator3, @Nullable ResourceParamsCreator resourceParamsCreator4, @Nullable ResourceParamsCreator resourceParamsCreator5, @Nullable ResourceParamsCreator resourceParamsCreator6) {
        this.mGlobalParamsCreator = (ResourceParamsCreator) Preconditions.checkNotNull(resourceParamsCreator, "globalParamsCreator");
        this.mLivePlaybackUrlsParamsCreator = resourceParamsCreator2;
        this.mRapidRecapPlaybackUrlsParamsCreator = resourceParamsCreator3;
        this.mPlaybackDataParamsCreator = resourceParamsCreator4;
        this.mSyeUrlsParamsCreator = resourceParamsCreator5;
        this.mXRayMetadataParamsCreator = resourceParamsCreator6;
    }

    @Override // com.amazon.avod.prs.ResourceParamsCreator
    public ResourceParams create() throws ContentException {
        PlaybackResourceV2Params.Builder builderGlobalParams = new PlaybackResourceV2Params.Builder().globalParams(this.mGlobalParamsCreator.create());
        ResourceParamsCreator resourceParamsCreator = this.mLivePlaybackUrlsParamsCreator;
        if (resourceParamsCreator != null) {
            builderGlobalParams.livePlaybackUrlsParams(resourceParamsCreator.create());
        }
        ResourceParamsCreator resourceParamsCreator2 = this.mRapidRecapPlaybackUrlsParamsCreator;
        if (resourceParamsCreator2 != null) {
            builderGlobalParams.rapidRecapPlaybackUrlsParams(resourceParamsCreator2.create());
        }
        ResourceParamsCreator resourceParamsCreator3 = this.mXRayMetadataParamsCreator;
        if (resourceParamsCreator3 != null) {
            builderGlobalParams.xRayMetadataParams(resourceParamsCreator3.create());
        }
        ResourceParamsCreator resourceParamsCreator4 = this.mPlaybackDataParamsCreator;
        if (resourceParamsCreator4 != null) {
            builderGlobalParams.playbackDataParams(resourceParamsCreator4.create());
        }
        if (this.mSyeUrlsParamsCreator != null && SyeConfig.getInstance().isPlayerEnabled()) {
            builderGlobalParams.syeUrlsParams(this.mSyeUrlsParamsCreator.create());
        }
        return builderGlobalParams.build();
    }

    public static class Builder {
        private ResourceParamsCreator mGlobalParamsCreator;
        private ResourceParamsCreator mLivePlaybackUrlsParamsCreator;
        private ResourceParamsCreator mPlaybackDataParamsCreator;
        private ResourceParamsCreator mRapidReplayPlaybackUrlsParamsCreator;
        private ResourceParamsCreator mSyeUrlsParamsCreator;
        private ResourceParamsCreator mXRayMetadataParams;

        public Builder globalParamsCreator(@Nonnull ResourceParamsCreator resourceParamsCreator) {
            this.mGlobalParamsCreator = (ResourceParamsCreator) Preconditions.checkNotNull(resourceParamsCreator, "globalParamsCreator");
            return this;
        }

        public Builder livePlaybackUrlsParamsCreator(@Nonnull ResourceParamsCreator resourceParamsCreator) {
            this.mLivePlaybackUrlsParamsCreator = (ResourceParamsCreator) Preconditions.checkNotNull(resourceParamsCreator, "livePlaybackUrlsParamsCreator");
            return this;
        }

        public Builder rapidReplayPlaybackUrlsParamsCreator(@Nonnull ResourceParamsCreator resourceParamsCreator) {
            this.mRapidReplayPlaybackUrlsParamsCreator = (ResourceParamsCreator) Preconditions.checkNotNull(resourceParamsCreator, "rapidReplayPlaybackUrlsParamsCreator");
            return this;
        }

        public Builder playbackDataParamsCreator(@Nonnull ResourceParamsCreator resourceParamsCreator) {
            this.mPlaybackDataParamsCreator = (ResourceParamsCreator) Preconditions.checkNotNull(resourceParamsCreator, "playbackDataParamsCreator");
            return this;
        }

        @Nonnull
        public PlaybackResourceV2ParamsCreator build() {
            return new PlaybackResourceV2ParamsCreator(this.mGlobalParamsCreator, this.mLivePlaybackUrlsParamsCreator, this.mRapidReplayPlaybackUrlsParamsCreator, this.mPlaybackDataParamsCreator, this.mSyeUrlsParamsCreator, this.mXRayMetadataParams);
        }
    }

    private static class PlaybackResourceV2Params implements ResourceParams {
        private final ResourceParams mGlobalParams;
        private final ResourceParams mLivePlaybackUrlsParams;
        private final ResourceParams mPlaybackDataParams;
        private final ResourceParams mRapidRecapPlaybackUrlsParams;
        private final ResourceParams mSyeUrlsParams;
        private final ResourceParams mXRayMetadataParams;

        private PlaybackResourceV2Params(@Nonnull ResourceParams resourceParams, @Nullable ResourceParams resourceParams2, @Nullable ResourceParams resourceParams3, @Nullable ResourceParams resourceParams4, @Nullable ResourceParams resourceParams5, @Nullable ResourceParams resourceParams6) {
            this.mGlobalParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "globalParams");
            this.mLivePlaybackUrlsParams = resourceParams2;
            this.mRapidRecapPlaybackUrlsParams = resourceParams3;
            this.mPlaybackDataParams = resourceParams4;
            this.mSyeUrlsParams = resourceParams5;
            this.mXRayMetadataParams = resourceParams6;
        }

        @JsonProperty("globalParameters")
        @Nonnull
        public ResourceParams getGlobalParams() {
            return this.mGlobalParams;
        }

        @JsonProperty("livePlaybackUrlsRequest")
        @Nullable
        public ResourceParams getLivePlaybackUrlsParams() {
            return this.mLivePlaybackUrlsParams;
        }

        @JsonProperty("rapidRecapPlaybackUrlsRequest")
        @Nullable
        public ResourceParams getRapidRecapPlaybackUrlsParams() {
            return this.mRapidRecapPlaybackUrlsParams;
        }

        @JsonProperty("playbackDataRequest")
        @Nullable
        public ResourceParams getPlaybackDataParams() {
            return this.mPlaybackDataParams;
        }

        @JsonProperty("syeUrlsRequest")
        @Nullable
        public ResourceParams getSyeUrlsParams() {
            return this.mSyeUrlsParams;
        }

        @JsonProperty("xrayMetadataRequest")
        @Nullable
        public ResourceParams getXRayMetadataParams() {
            return this.mXRayMetadataParams;
        }

        private static class Builder {
            private ResourceParams mGlobalParams;
            private ResourceParams mLivePlaybackUrlsParams;
            private ResourceParams mPlaybackDataParams;
            private ResourceParams mRapidRecapPlaybackUrlsParams;
            private ResourceParams mSyeUrlsParams;
            private ResourceParams mXRayMetadataParams;

            private Builder() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder globalParams(@Nonnull ResourceParams resourceParams) {
                this.mGlobalParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "globalParams");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder livePlaybackUrlsParams(@Nonnull ResourceParams resourceParams) {
                this.mLivePlaybackUrlsParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "livePlaybackUrlsParams");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder rapidRecapPlaybackUrlsParams(@Nonnull ResourceParams resourceParams) {
                this.mRapidRecapPlaybackUrlsParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "livePlaybackUrlsParams");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder syeUrlsParams(@Nonnull ResourceParams resourceParams) {
                this.mSyeUrlsParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "syeUrlsParams");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder playbackDataParams(@Nonnull ResourceParams resourceParams) {
                this.mPlaybackDataParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "playbackDataParams");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder xRayMetadataParams(@Nonnull ResourceParams resourceParams) {
                this.mXRayMetadataParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "xRayMetadataParams");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Nonnull
            public PlaybackResourceV2Params build() {
                return new PlaybackResourceV2Params(this.mGlobalParams, this.mLivePlaybackUrlsParams, this.mRapidRecapPlaybackUrlsParams, this.mPlaybackDataParams, this.mSyeUrlsParams, this.mXRayMetadataParams);
            }
        }
    }
}

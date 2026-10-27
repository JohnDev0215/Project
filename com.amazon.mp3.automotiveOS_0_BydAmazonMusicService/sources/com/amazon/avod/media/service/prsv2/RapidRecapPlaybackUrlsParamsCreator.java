package com.amazon.avod.media.service.prsv2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.media.service.PlayerResourcesExternalParamHolder;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RapidRecapPlaybackUrlsParamsCreator implements ResourceParamsCreator {
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final PlaybackUrlsCommonParams mPlaybackUrlsCommonParams;

    public RapidRecapPlaybackUrlsParamsCreator(VideoSpecification videoSpecification) {
        this(PlayerResourcesExternalParamHolder.getRendererSchemeController().getRendererScheme(videoSpecification.getRendererSchemeTypeString() != null ? RendererSchemeType.fromSchemeString(videoSpecification.getRendererSchemeTypeString()).get() : null, null), PlayerResourcesExternalParamHolder.getPlaybackSupportEvaluator(), videoSpecification.getContentType());
    }

    public RapidRecapPlaybackUrlsParamsCreator(@Nonnull RendererScheme rendererScheme, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull ContentType contentType) {
        this(MediaSystemSharedDependencies.getInstance().getDeviceIdentity(), MediaSystemSharedDependencies.getInstance().getHdcpLevelProvider(), PlaybackResourcesV2Config.getInstance(), playbackSupportEvaluator, rendererScheme, contentType, AVODServiceConfig.getInstance());
    }

    private RapidRecapPlaybackUrlsParamsCreator(@Nonnull DeviceIdentity deviceIdentity, @Nonnull HdcpLevelProvider hdcpLevelProvider, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull RendererScheme rendererScheme, @Nonnull ContentType contentType, @Nonnull AVODServiceConfig aVODServiceConfig) {
        this(PlaybackResourcesV2Config.getInstance(), new PlaybackUrlsCommonParams(deviceIdentity, hdcpLevelProvider, playbackResourcesV2Config, playbackSupportEvaluator, rendererScheme, contentType, aVODServiceConfig));
    }

    public RapidRecapPlaybackUrlsParamsCreator(@Nonnull PlaybackResourcesV2Config playbackResourcesV2Config, @Nonnull PlaybackUrlsCommonParams playbackUrlsCommonParams) {
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
        this.mPlaybackUrlsCommonParams = (PlaybackUrlsCommonParams) Preconditions.checkNotNull(playbackUrlsCommonParams, "prsv2CommonParams");
    }

    @Override // com.amazon.avod.prs.ResourceParamsCreator
    public ResourceParams create() throws ContentException {
        String str;
        String str2;
        String str3;
        RapidRecapPlaybackUrlsParams.Builder builder;
        List<String> list;
        ArrayList arrayList;
        RapidRecapPlaybackUrlsParams.Builder builder2 = new RapidRecapPlaybackUrlsParams.Builder();
        String firmware = this.mPlaybackUrlsCommonParams.getFirmware();
        String capVideoDefinition = this.mPlaybackUrlsCommonParams.getCapVideoDefinition();
        String drmKeyScheme = this.mPlaybackUrlsCommonParams.getDrmKeyScheme();
        String drmStrength = this.mPlaybackUrlsCommonParams.getDrmStrength(capVideoDefinition);
        String drmType = this.mPlaybackUrlsCommonParams.getDrmType();
        String hdcpLevel = this.mPlaybackUrlsCommonParams.getHdcpLevel();
        String deviceCategory = this.mPlaybackUrlsCommonParams.getDeviceCategory();
        String operatingSystem = this.mPlaybackUrlsCommonParams.getOperatingSystem();
        List<String> codecs = this.mPlaybackUrlsCommonParams.getCodecs();
        List<String> fragmentRepresentations = this.mPlaybackUrlsCommonParams.getFragmentRepresentations();
        List<String> frameRates = this.mPlaybackUrlsCommonParams.getFrameRates();
        List<PlaybackResourceServiceConstants$HdrFormat> dynamicRangeFormats = this.mPlaybackUrlsCommonParams.getDynamicRangeFormats();
        ArrayList arrayList2 = new ArrayList();
        List<String> dashBitrateAdaptations = this.mPlaybackUrlsCommonParams.getDashBitrateAdaptations();
        List<String> supportedStreamingTechnologies = this.mPlaybackResourcesV2Config.getSupportedStreamingTechnologies();
        RapidRecapPlaybackUrlsParams.Device.StreamingTechnologies streamingTechnologies = new RapidRecapPlaybackUrlsParams.Device.StreamingTechnologies();
        if (dashBitrateAdaptations.isEmpty() || !supportedStreamingTechnologies.contains("DASH")) {
            str = firmware;
            str2 = hdcpLevel;
            str3 = deviceCategory;
            builder = builder2;
            list = supportedStreamingTechnologies;
            arrayList = arrayList2;
        } else {
            str2 = hdcpLevel;
            str = firmware;
            str3 = deviceCategory;
            list = supportedStreamingTechnologies;
            builder = builder2;
            arrayList = arrayList2;
            streamingTechnologies.setDash(new RapidRecapPlaybackUrlsParams.Device.StreamingTechnologies.Dash(this.mPlaybackUrlsCommonParams.getDashBitrateAdaptations(), codecs, drmKeyScheme, drmStrength, drmType, fragmentRepresentations, frameRates, dynamicRangeFormats));
            arrayList.add("DASH");
        }
        if (list.contains("SmoothStreaming")) {
            streamingTechnologies.setSmoothStreaming(new RapidRecapPlaybackUrlsParams.Device.StreamingTechnologies.SmoothStreaming(Arrays.asList("CBR"), codecs, drmKeyScheme, drmStrength, drmType, fragmentRepresentations, frameRates, dynamicRangeFormats));
            arrayList.add("SmoothStreaming");
        }
        builder.device(str3, str, str2, operatingSystem, "Android", this.mPlaybackUrlsCommonParams.getLiveManifestTypes(), this.mPlaybackUrlsCommonParams.getMaxPlaybackResolution(), streamingTechnologies, arrayList, this.mPlaybackUrlsCommonParams.getThumbnailRepresentations());
        return builder.build();
    }

    static class RapidRecapPlaybackUrlsParams implements ResourceParams {
        private final Device mDevice;

        private RapidRecapPlaybackUrlsParams(@Nonnull Device device) {
            this.mDevice = (Device) Preconditions.checkNotNull(device, "device");
        }

        @JsonProperty("device")
        @Nonnull
        public Device getDevice() {
            return this.mDevice;
        }

        static class Device {
            private final String mCategory;
            private final String mFirmware;
            private final String mHdcpLevel;
            private final List<String> mLiveManifestTypes;
            private final String mMaxPlaybackResolution;
            private final String mOperatingSystem;
            private final String mPlatform;
            private StreamingTechnologies mStreamingTechnologies;
            private final List<String> mSupportedStreamingTechnologies;
            private final List<String> mThumbnailRepresentations;

            Device(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5, @Nonnull List<String> list, @Nonnull String str6, @Nonnull StreamingTechnologies streamingTechnologies, @Nonnull List<String> list2, @Nonnull List<String> list3) {
                this.mCategory = (String) Preconditions.checkNotNull(str, "category");
                this.mFirmware = (String) Preconditions.checkNotNull(str2, "firmware");
                this.mHdcpLevel = (String) Preconditions.checkNotNull(str3, "hdcpLevel");
                this.mOperatingSystem = (String) Preconditions.checkNotNull(str4, "operatingSystem");
                this.mPlatform = (String) Preconditions.checkNotNull(str5, "platform");
                this.mLiveManifestTypes = (List) Preconditions.checkNotNull(list, "liveManifestTypes");
                this.mMaxPlaybackResolution = (String) Preconditions.checkNotNull(str6, "maxPlaybackResolution");
                this.mStreamingTechnologies = (StreamingTechnologies) Preconditions.checkNotNull(streamingTechnologies, "streamingTechnologies");
                this.mSupportedStreamingTechnologies = (List) Preconditions.checkNotNull(list2, "supportedStreamingTechnologies");
                this.mThumbnailRepresentations = (List) Preconditions.checkNotNull(list3, "thumbnailRepresentations");
            }

            @JsonProperty("category")
            @Nonnull
            public String getCategory() {
                return this.mCategory;
            }

            @JsonProperty("firmware")
            @Nonnull
            public String getFirmware() {
                return this.mFirmware;
            }

            @JsonProperty("hdcpLevel")
            @Nonnull
            public String getHdcpLevel() {
                return this.mHdcpLevel;
            }

            @JsonProperty("operatingSystem")
            @Nonnull
            public String getOperatingSystem() {
                return this.mOperatingSystem;
            }

            @JsonProperty("platform")
            @Nonnull
            public String getPlatform() {
                return this.mPlatform;
            }

            @JsonProperty("liveManifestTypes")
            @Nonnull
            public List<String> getLiveManifestTypes() {
                return this.mLiveManifestTypes;
            }

            @JsonProperty("maxVideoResolution")
            @Nonnull
            public String getMaxPlaybackResolution() {
                return this.mMaxPlaybackResolution;
            }

            @JsonProperty("streamingTechnologies")
            @Nonnull
            public StreamingTechnologies getStreamingTechnologies() {
                return this.mStreamingTechnologies;
            }

            @JsonProperty("supportedStreamingTechnologies")
            @Nonnull
            public List<String> getSupportedStreamingTechnologies() {
                return this.mSupportedStreamingTechnologies;
            }

            static class StreamingTechnologies {
                private Dash mDash;
                private SmoothStreaming mSmoothStreaming;

                StreamingTechnologies() {
                }

                @JsonInclude(JsonInclude.Include.NON_NULL)
                @JsonProperty("DASH")
                @Nullable
                public Dash getDash() {
                    return this.mDash;
                }

                @JsonInclude(JsonInclude.Include.NON_NULL)
                @JsonProperty("SmoothStreaming")
                @Nullable
                public SmoothStreaming getSmoothStreaming() {
                    return this.mSmoothStreaming;
                }

                public void setDash(@Nonnull Dash dash) {
                    this.mDash = (Dash) Preconditions.checkNotNull(dash, "dash");
                }

                public void setSmoothStreaming(@Nonnull SmoothStreaming smoothStreaming) {
                    this.mSmoothStreaming = (SmoothStreaming) Preconditions.checkNotNull(smoothStreaming, "smoothStreaming");
                }

                static class Dash extends StreamingTechnologyBase {
                    Dash(@Nonnull List<String> list, @Nonnull List<String> list2, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull List<String> list3, @Nonnull List<String> list4, @Nonnull List<PlaybackResourceServiceConstants$HdrFormat> list5) {
                        super(list, list2, str, str2, str3, list3, list4, list5);
                    }
                }

                static class SmoothStreaming extends StreamingTechnologyBase {
                    SmoothStreaming(@Nonnull List<String> list, @Nonnull List<String> list2, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull List<String> list3, @Nonnull List<String> list4, @Nonnull List<PlaybackResourceServiceConstants$HdrFormat> list5) {
                        super(list, list2, str, str2, str3, list3, list4, list5);
                    }
                }
            }

            @JsonProperty("thumbnailRepresentations")
            @Nonnull
            public List<String> getThumbnailRepresentations() {
                return this.mThumbnailRepresentations;
            }
        }

        static class Builder {
            private Device mDevice;

            Builder() {
            }

            @Nonnull
            Builder device(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5, @Nonnull List<String> list, @Nonnull String str6, @Nonnull Device.StreamingTechnologies streamingTechnologies, @Nonnull List<String> list2, @Nonnull List<String> list3) {
                this.mDevice = new Device(str, str2, str3, str4, str5, list, str6, streamingTechnologies, list2, list3);
                return this;
            }

            @Nonnull
            RapidRecapPlaybackUrlsParams build() {
                return new RapidRecapPlaybackUrlsParams(this.mDevice);
            }
        }
    }
}

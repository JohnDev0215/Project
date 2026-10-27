package com.amazon.avod.media.service.prsv2;

import android.os.Build;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$VideoFrameRate;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$VideoQuality;
import com.amazon.avod.media.service.PlayerResourcesExternalParamHolder;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LivePlaybackUrlsParamsCreator implements ResourceParamsCreator {
    private static final Map<HdcpLevelProvider.HdcpLevel, String> HDCP_LEVEL_MAP = buildHdcpLevelMap();
    private final AVODServiceConfig mAVODServiceConfig;
    private final AdvertisingIdCollector mAdvertisingInfoCollector;
    private final ContentType mContentType;
    private final DeviceIdentity mDeviceIdentity;
    private final DrmFramework mDrmFramework;
    private final HdcpLevelProvider mHdcpLevelProvider;
    private final MediaSystem mMediaSystem;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final PlaybackSettings mPlaybackSettings;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private final RendererScheme mRendererScheme;
    private final RendererSchemeType mRendererSchemeType;
    private final boolean mSupportsEmbeddedTrickplay;
    private final String mTitleId;

    public LivePlaybackUrlsParamsCreator(@Nonnull VideoSpecification videoSpecification) {
        this(PlayerResourcesExternalParamHolder.getAdvertisingIdCollector(), PlayerResourcesExternalParamHolder.getRendererSchemeController().getRendererScheme(videoSpecification.getRendererSchemeTypeString() != null ? RendererSchemeType.fromSchemeString(videoSpecification.getRendererSchemeTypeString()).get() : null, null), PlayerResourcesExternalParamHolder.getPlaybackSupportEvaluator(), videoSpecification.getContentType(), videoSpecification.getTitleId(), videoSpecification.getPlaybackSettings());
    }

    public LivePlaybackUrlsParamsCreator(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull RendererScheme rendererScheme, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull ContentType contentType, @Nonnull String str, @Nullable PlaybackSettings playbackSettings) {
        this(advertisingIdCollector, rendererScheme, playbackSupportEvaluator, contentType, str, playbackSettings, AVODServiceConfig.getInstance(), MediaSystem.getInstance(), MediaSystemSharedDependencies.getInstance().getDeviceIdentity(), PlaybackResourcesV2Config.getInstance(), MediaSystemSharedDependencies.getInstance().getHdcpLevelProvider());
    }

    LivePlaybackUrlsParamsCreator(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull RendererScheme rendererScheme, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull ContentType contentType, @Nonnull String str, @Nullable PlaybackSettings playbackSettings, @Nonnull AVODServiceConfig aVODServiceConfig, @Nonnull MediaSystem mediaSystem, @Nonnull DeviceIdentity deviceIdentity, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config, @Nonnull HdcpLevelProvider hdcpLevelProvider) {
        this.mAdvertisingInfoCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        RendererScheme rendererScheme2 = (RendererScheme) Preconditions.checkNotNull(rendererScheme, "rendererScheme");
        this.mRendererScheme = rendererScheme2;
        this.mRendererSchemeType = rendererScheme2.getSchemeType();
        this.mDrmFramework = rendererScheme2.getDrmFramework();
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mPlaybackSettings = playbackSettings;
        this.mAVODServiceConfig = (AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig, "avodServiceConfig");
        this.mSupportsEmbeddedTrickplay = aVODServiceConfig.supportsEmbeddedTrickplay();
        this.mMediaSystem = (MediaSystem) Preconditions.checkNotNull(mediaSystem, "mediaSystem");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
        this.mHdcpLevelProvider = (HdcpLevelProvider) Preconditions.checkNotNull(hdcpLevelProvider, "hdcpLevelProvider");
    }

    @Override // com.amazon.avod.prs.ResourceParamsCreator
    public ResourceParams create() throws ContentException {
        LivePlaybackUrlsParams.Builder builder;
        String str;
        String str2;
        String str3;
        LivePlaybackUrlsParams.Device.StreamingTechnologies streamingTechnologies;
        List<String> list;
        ArrayList arrayList;
        LivePlaybackUrlsParams.Builder builder2 = new LivePlaybackUrlsParams.Builder();
        AdvertisingIdCollector.AdvertisingInfo advertisingInfo = this.mAdvertisingInfoCollector.get(this.mAVODServiceConfig.getAdvertisingIdCollectorTimeoutInMillis(), this.mAVODServiceConfig.isAdvertisingIdCollectorUseStaleData());
        builder2.ads(advertisingInfo.getAdId(), advertisingInfo.isOptOut(), "DAI_SUPPORTED");
        String firmware = getFirmware();
        String capVideoDefinition = getCapVideoDefinition();
        String drmKeyScheme = getDrmKeyScheme();
        String drmStrength = getDrmStrength(capVideoDefinition);
        String drmType = getDrmType();
        String hdcpLevel = getHdcpLevel();
        DLog.logf("PRSv2 livePlaybackUrls request key parameters: capVideoDefinition %s, drmKeyScheme %s, drmStrength %s, drmType %s, hdcpLevel %s", capVideoDefinition, drmKeyScheme, drmStrength, drmType, hdcpLevel);
        ArrayList arrayList2 = new ArrayList();
        List<String> dashBitrateAdaptations = getDashBitrateAdaptations();
        List<String> supportedStreamingTechnologies = this.mPlaybackResourcesV2Config.getSupportedStreamingTechnologies();
        LivePlaybackUrlsParams.Device.StreamingTechnologies streamingTechnologies2 = new LivePlaybackUrlsParams.Device.StreamingTechnologies();
        if (dashBitrateAdaptations.isEmpty() || !supportedStreamingTechnologies.contains("DASH")) {
            builder = builder2;
            str = firmware;
            str2 = capVideoDefinition;
            str3 = drmType;
            streamingTechnologies = streamingTechnologies2;
            list = supportedStreamingTechnologies;
            arrayList = arrayList2;
        } else {
            str2 = capVideoDefinition;
            str = firmware;
            streamingTechnologies = streamingTechnologies2;
            builder = builder2;
            list = supportedStreamingTechnologies;
            str3 = drmType;
            arrayList = arrayList2;
            streamingTechnologies.setDash(new LivePlaybackUrlsParams.Device.StreamingTechnologies.Dash(getDashBitrateAdaptations(), getCodecs(), drmKeyScheme, drmStrength, drmType, getFragmentRepresentations(), getFrameRates(), getDynamicRangeFormats()));
            arrayList.add("DASH");
        }
        if (list.contains("SmoothStreaming")) {
            streamingTechnologies.setSmoothStreaming(new LivePlaybackUrlsParams.Device.StreamingTechnologies.SmoothStreaming(Arrays.asList("CBR"), getCodecs(), drmKeyScheme, drmStrength, str3, getFragmentRepresentations(), getFrameRates(), getDynamicRangeFormats()));
            arrayList.add("SmoothStreaming");
        }
        builder.device(getDeviceCategory(), str, hdcpLevel, getOperatingSystem(), "Android", getLiveManifestTypes(), getMaxPlaybackResolution(), streamingTechnologies, arrayList, getThumbnailRepresentations()).capVideoDefinition(str2).languageFeature("MLFv2");
        if (this.mPlaybackSettings == null) {
            builder.playbackSettings("1.0.0", this.mMediaSystem.getPlayerName(), Build.MODEL, str, String.valueOf(this.mDeviceIdentity.getAppMajorVersion()), this.mTitleId);
        }
        return builder.build();
    }

    @Nonnull
    private static Map<HdcpLevelProvider.HdcpLevel, String> buildHdcpLevelMap() {
        String hdcpLevelForNoHdcpInputDevice = PlaybackResourcesV2Config.getInstance().getHdcpLevelForNoHdcpInputDevice();
        return (ImmutableMap) Preconditions2.checkFullKeyMapping(HdcpLevelProvider.HdcpLevel.class, new ImmutableMap.Builder().put(HdcpLevelProvider.HdcpLevel.NO_HDCP_SUPPORT, hdcpLevelForNoHdcpInputDevice).put(HdcpLevelProvider.HdcpLevel.LOCAL_DISPLAY, hdcpLevelForNoHdcpInputDevice).put(HdcpLevelProvider.HdcpLevel.HDCP_1_0, "1.1").put(HdcpLevelProvider.HdcpLevel.HDCP_2_0, "2.0").put(HdcpLevelProvider.HdcpLevel.HDCP_2_1, "2.1").put(HdcpLevelProvider.HdcpLevel.HDCP_2_2, "2.2").build());
    }

    static class LivePlaybackUrlsParams implements ResourceParams {
        private final Ads mAds;
        private final String mCapVideoDefinition;
        private final Device mDevice;
        private final String mLanguageFeature;
        private final PlaybackSettings mPlaybackSettings;

        /* synthetic */ LivePlaybackUrlsParams(Ads ads, Device device, String str, String str2, PlaybackSettings playbackSettings, AnonymousClass1 anonymousClass1) {
            this(ads, device, str, str2, playbackSettings);
        }

        private LivePlaybackUrlsParams(@Nonnull Ads ads, @Nonnull Device device, @Nonnull String str, @Nonnull String str2, @Nullable PlaybackSettings playbackSettings) {
            this.mAds = (Ads) Preconditions.checkNotNull(ads, "ads");
            this.mDevice = (Device) Preconditions.checkNotNull(device, "device");
            this.mCapVideoDefinition = (String) Preconditions.checkNotNull(str, "capVideoDefinition");
            this.mLanguageFeature = (String) Preconditions.checkNotNull(str2, "languageFeature");
            this.mPlaybackSettings = playbackSettings;
        }

        @JsonProperty("ads")
        @Nonnull
        public Ads getAds() {
            return this.mAds;
        }

        @JsonProperty("capVideoDefinition")
        @Nonnull
        public String getCapVideoDefinition() {
            return this.mCapVideoDefinition;
        }

        @JsonProperty("device")
        @Nonnull
        public Device getDevice() {
            return this.mDevice;
        }

        @JsonProperty("languageFeature")
        @Nonnull
        public String getLanguageFeature() {
            return this.mLanguageFeature;
        }

        @JsonProperty("playbackSettings")
        @Nullable
        public PlaybackSettings getPlaybackSettings() {
            return this.mPlaybackSettings;
        }

        static class Ads {
            private final String mAdvertisingId;
            private final boolean mOptOutAdTracking;
            private final String mSupportsDai;

            Ads(@Nullable String str, boolean z, @Nonnull String str2) {
                this.mAdvertisingId = str;
                this.mOptOutAdTracking = z;
                this.mSupportsDai = (String) Preconditions.checkNotNull(str2, "supportsDai");
            }

            @JsonProperty("advertisingId")
            @Nullable
            public String getAdvertisingId() {
                return this.mAdvertisingId;
            }

            @JsonProperty("optOutAdTracking")
            public boolean isOptOutAdTracking() {
                return this.mOptOutAdTracking;
            }

            @JsonProperty("supportsDai")
            @Nonnull
            public String getSupportsDai() {
                return this.mSupportsDai;
            }
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

        static class PlaybackSettings {
            private final String mDeviceModel;
            private final String mFirmware;
            private final String mFormatVersion;
            private final String mPlayerType;
            private final String mSoftwareVersion;
            private final String mTitleId;

            PlaybackSettings(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5, @Nonnull String str6) {
                this.mFormatVersion = (String) Preconditions.checkNotNull(str, "formatVersion");
                this.mPlayerType = (String) Preconditions.checkNotNull(str2, "playerType");
                this.mDeviceModel = (String) Preconditions.checkNotNull(str3, DeviceInformation.PARAMS_DEVICE_MODEL);
                this.mFirmware = (String) Preconditions.checkNotNull(str4, "firmware");
                this.mSoftwareVersion = (String) Preconditions.checkNotNull(str5, "softwareVersion");
                this.mTitleId = (String) Preconditions.checkNotNull(str6, "titleId");
            }

            @JsonProperty("responseFormatVersion")
            @Nonnull
            public String getFormatVersion() {
                return this.mFormatVersion;
            }

            @JsonProperty("playerType")
            @Nonnull
            public String getPlayerType() {
                return this.mPlayerType;
            }

            @JsonProperty(DeviceInformation.PARAMS_DEVICE_MODEL)
            @Nonnull
            public String getDeviceModel() {
                return this.mDeviceModel;
            }

            @JsonProperty("firmware")
            @Nonnull
            public String getFirmware() {
                return this.mFirmware;
            }

            @JsonProperty("softwareVersion")
            @Nonnull
            public String getSoftwareVersion() {
                return this.mSoftwareVersion;
            }

            @JsonProperty("titleId")
            @Nonnull
            public String getTitleId() {
                return this.mTitleId;
            }
        }

        static class Builder {
            private Ads mAds;
            private String mCapVideoDefinition;
            private Device mDevice;
            private String mLanguageFeature;
            private PlaybackSettings mPlaybackSettings;

            Builder() {
            }

            @Nonnull
            Builder ads(@Nullable String str, boolean z, @Nonnull String str2) {
                this.mAds = new Ads(str, z, str2);
                return this;
            }

            @Nonnull
            Builder device(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5, @Nonnull List<String> list, @Nonnull String str6, @Nonnull Device.StreamingTechnologies streamingTechnologies, @Nonnull List<String> list2, @Nonnull List<String> list3) {
                this.mDevice = new Device(str, str2, str3, str4, str5, list, str6, streamingTechnologies, list2, list3);
                return this;
            }

            @Nonnull
            Builder capVideoDefinition(@Nonnull String str) {
                this.mCapVideoDefinition = (String) Preconditions.checkNotNull(str, "capVideoDefinition");
                return this;
            }

            @Nonnull
            Builder languageFeature(@Nonnull String str) {
                this.mLanguageFeature = (String) Preconditions.checkNotNull(str, "languageFeature");
                return this;
            }

            @Nonnull
            Builder playbackSettings(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5, @Nonnull String str6) {
                this.mPlaybackSettings = new PlaybackSettings(str, str2, str3, str4, str5, str6);
                return this;
            }

            @Nonnull
            LivePlaybackUrlsParams build() {
                return new LivePlaybackUrlsParams(this.mAds, this.mDevice, this.mCapVideoDefinition, this.mLanguageFeature, this.mPlaybackSettings, null);
            }
        }
    }

    @Nonnull
    private String getFirmware() {
        return "fmw:" + Build.VERSION.SDK + "-app:" + this.mDeviceIdentity.getAppVersionName();
    }

    @Nonnull
    private List<String> getFragmentRepresentations() {
        return Arrays.asList("ByteOffsetRange", "SeparateFile");
    }

    @Nonnull
    private String getDeviceCategory() {
        this.mDeviceIdentity.waitOnInitialized();
        return this.mDeviceIdentity.isThirdParty() ? "Phone" : this.mDeviceIdentity.getAppVersionName().startsWith("FireTv") ? "Room" : this.mDeviceIdentity.getAppVersionName().startsWith("FireTablet") ? "Tablet" : "Other";
    }

    @Nonnull
    private String getHdcpLevel() {
        return HDCP_LEVEL_MAP.get(this.mHdcpLevelProvider.getCurrentHdcpLevel());
    }

    @Nonnull
    private String getOperatingSystem() {
        return "Android" + Build.VERSION.RELEASE;
    }

    @Nonnull
    private List<String> getLiveManifestTypes() {
        return Arrays.asList(this.mPlaybackResourcesV2Config.getLiveManifestCapabilities().split(","));
    }

    @Nonnull
    private String getMaxPlaybackResolution() {
        return this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType) ? "2160p" : this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType) ? "1080p" : "576p";
    }

    @Nonnull
    private List<String> getDashBitrateAdaptations() {
        List<String> deviceBitrateAdaptationsOverride = this.mPlaybackSupportEvaluator.getDeviceBitrateAdaptationsOverride(this.mRendererSchemeType);
        if (deviceBitrateAdaptationsOverride.isEmpty()) {
            deviceBitrateAdaptationsOverride.add(this.mPlaybackResourcesV2Config.getDefaultDashBitrateAdaptation());
        }
        return deviceBitrateAdaptationsOverride;
    }

    @Nonnull
    private List<String> getCodecs() {
        return this.mPlaybackSupportEvaluator.getDeviceVideoCodecOverride(this.mRendererSchemeType);
    }

    @Nonnull
    private String getDrmStrength(@Nonnull String str) {
        Preconditions.checkNotNull(str, "capVideoDefinition");
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[this.mDrmFramework.getDrmSecurityLevel().ordinal()];
        return (i == 4 || i == 5 || i == 6) ? PlaybackResourceServiceConstants$VideoQuality.UHD.name().equals(str) ? "L40" : "L30" : "L10";
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.service.prsv2.LivePlaybackUrlsParamsCreator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel;

        static {
            int[] iArr = new int[DrmSecurityLevel.values().length];
            $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel = iArr;
            try {
                iArr[DrmSecurityLevel.SL_150.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.LEVEL_3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.LEVEL_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.LEVEL_1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.SL_2000.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.SL_3000.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Nonnull
    private String getDrmType() {
        return this.mDrmFramework.getCurrentDrmScheme().name();
    }

    @Nonnull
    private String getDrmKeyScheme() {
        return this.mPlaybackSupportEvaluator.isMultiKeyDecryptionSupported(this.mRendererSchemeType, this.mContentType) ? "DualKey" : "SingleKey";
    }

    @Nonnull
    private List<String> getFrameRates() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PlaybackResourceServiceConstants$VideoFrameRate.Standard.toString());
        if ((this.mAVODServiceConfig.isVideoFrameRateOverrideEnabled() || this.mPlaybackResourcesV2Config.isHighFrameRateEnabled()) && (this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType) || this.mPlaybackSupportEvaluator.isHdrSupported(this.mRendererSchemeType) || this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType))) {
            arrayList.add(PlaybackResourceServiceConstants$VideoFrameRate.High.toString());
        }
        return arrayList;
    }

    @Nonnull
    private List<PlaybackResourceServiceConstants$HdrFormat> getDynamicRangeFormats() {
        if (this.mPlaybackSupportEvaluator.isHdrSupported(this.mRendererSchemeType)) {
            return this.mPlaybackSupportEvaluator.getSupportedHdrFormats(this.mRendererSchemeType);
        }
        return ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.None);
    }

    @Nonnull
    private String getCapVideoDefinition() {
        if (this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType)) {
            return PlaybackResourceServiceConstants$VideoQuality.UHD.name();
        }
        if (this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType)) {
            return PlaybackResourceServiceConstants$VideoQuality.HD.name();
        }
        return PlaybackResourceServiceConstants$VideoQuality.SD.name();
    }

    @Nonnull
    private List<String> getThumbnailRepresentations() {
        ArrayList arrayList = new ArrayList();
        if (this.mSupportsEmbeddedTrickplay) {
            arrayList.add("InManifest");
        } else {
            arrayList.add("None");
        }
        return arrayList;
    }
}

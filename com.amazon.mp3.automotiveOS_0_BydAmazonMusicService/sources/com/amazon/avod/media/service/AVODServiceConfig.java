package com.amazon.avod.media.service;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.os.Build;
import com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolder;
import com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AVODServiceConfig extends MediaConfigBase {
    private final ConfigurationValue<Long> mAdvertisingIdCollectorTimeoutInMillis;
    private final ConfigurationValue<Boolean> mAdvertisingIdCollectorUseStaleData;
    private final ConfigurationValue<Boolean> mAllowHdrDownloads;
    private final ConfigurationValue<String> mContentUrlOverrideValue;
    private final ConfigurationValue<Set<String>> mDashManufacturerDeviceModelBlackList;
    private final String mHardware;
    private final ConfigurationValue<Set<String>> mHardwareBlackList;
    private final HighFrameRateQualityHolderInterface mHighFrameRateQualityHolder;
    private final ConfigurationValue<Boolean> mIsContentUrlOverrideEnabled;
    private final ConfigurationValue<Boolean> mIsDAITrickplayWeblabEnabled;
    private final WhitelistBlacklistAvailabilityProfileConfig<Boolean> mIsDashDownloadOverrideEnabled;
    private final WhitelistBlacklistAvailabilityProfileConfig<Boolean> mIsDashStreamingOverrideEnabled;
    private final WhitelistBlacklistAvailabilityProfileConfig<Boolean> mIsDeviceWhitelistedForHFR;
    private final ConfigurationValue<Boolean> mIsMiyagiEnabled;
    private final ConfigurationValue<Boolean> mIsSmoothOverrideEnabled;
    private final ConfigurationValue<Boolean> mIsVideoFrameRateOverrideEnabled;
    private final ConfigurationValue<String> mLiveManifestCapabilities;
    private final String mManufacturerDeviceModel;
    private final ConfigurationValue<Boolean> mPlaybackResourcesCacheUseCachedAdInfo;
    private final ConfigurationValue<Boolean> mShouldPassAppIdentificationParameterInPlaybackUrlCalls;
    private final ConfigurationValue<Boolean> mShouldReportPlayheadInLive;
    private final ConfigurationValue<Boolean> mSupportsDAIEmbeddedTrickplay;
    private final ConfigurationValue<Boolean> mSupportsDAIEmbeddedTrickplayInBeta;
    private final ConfigurationValue<Boolean> mSupportsEmbeddedTrickplay;

    /* synthetic */ AVODServiceConfig(AnonymousClass1 anonymousClass1) {
        this();
    }

    public static AVODServiceConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        public static final AVODServiceConfig INSTANCE = new AVODServiceConfig(null);

        private SingletonHolder() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private AVODServiceConfig() {
        String str = String.format(Locale.US, "%s %s", Build.MANUFACTURER, Build.MODEL);
        String str2 = Build.HARDWARE;
        ThirdPartyConfigurationProfile thirdPartyConfigurationProfile = ThirdPartyConfigurationProfile.getInstance();
        Boolean bool = Boolean.FALSE;
        this(str, str2, new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.IS_DASH_STREAMING_OVERRIDE_ENABLED, thirdPartyConfigurationProfile, "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), true), new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.IS_DASH_DOWNLOAD_OVERRIDE_ENABLED, ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false), new WhitelistBlacklistAvailabilityProfileConfig("isVideoFrameRateOverrideEnabled", ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false), HighFrameRateQualityHolder.INSTANCE);
    }

    AVODServiceConfig(@Nonnull String str, @Nonnull String str2, @Nonnull WhitelistBlacklistAvailabilityProfileConfig<Boolean> whitelistBlacklistAvailabilityProfileConfig, @Nonnull WhitelistBlacklistAvailabilityProfileConfig<Boolean> whitelistBlacklistAvailabilityProfileConfig2, @Nonnull WhitelistBlacklistAvailabilityProfileConfig<Boolean> whitelistBlacklistAvailabilityProfileConfig3, @Nonnull HighFrameRateQualityHolderInterface highFrameRateQualityHolderInterface) {
        this.mManufacturerDeviceModel = (String) Preconditions.checkNotNull(str, "manufacturerDeviceModel");
        this.mHardware = (String) Preconditions.checkNotNull(str2, "hardware");
        this.mIsDashStreamingOverrideEnabled = (WhitelistBlacklistAvailabilityProfileConfig) Preconditions.checkNotNull(whitelistBlacklistAvailabilityProfileConfig, "isDashStreamingOverrideEnabled");
        this.mIsDashDownloadOverrideEnabled = (WhitelistBlacklistAvailabilityProfileConfig) Preconditions.checkNotNull(whitelistBlacklistAvailabilityProfileConfig2, "isDashDownloadOverrideEnabled");
        this.mIsSmoothOverrideEnabled = newBooleanConfigValue("isSmoothOverrideEnabled", false);
        this.mDashManufacturerDeviceModelBlackList = newSemicolonDelimitedStringSetConfigurationValue("dashManufacturerDeviceModelBlacklist", new String[0]);
        this.mHardwareBlackList = newSemicolonDelimitedStringSetConfigurationValue("dashHardwareBlacklist", new String[0]);
        this.mIsMiyagiEnabled = newBooleanConfigValue("playback_isMiyagiEnabled", true);
        this.mIsContentUrlOverrideEnabled = newBooleanConfigValue("playback_isContentUrlOverrideEnabled", false);
        this.mContentUrlOverrideValue = newStringConfigValue("playback_contentUrlOverrideValue", "https://midas.us-west-2.amazonaws.com/v1/dash/415052244193/deltaOrigin3/tnf_dash_stream_02.mpd");
        this.mAdvertisingIdCollectorTimeoutInMillis = newLongConfigValue("playback_advertisingIdCollectorTimeoutInMillis", 1000L);
        this.mAdvertisingIdCollectorUseStaleData = newBooleanConfigValue("playback_advertisingIdCollectorUseStaleData", true);
        this.mPlaybackResourcesCacheUseCachedAdInfo = newBooleanConfigValue("playback_playbackResourcesCacheUseCachedAdInfo", true);
        this.mLiveManifestCapabilities = newStringConfigValue("playback_liveManifestCapabilities", "live,accumulating");
        this.mIsDeviceWhitelistedForHFR = (WhitelistBlacklistAvailabilityProfileConfig) Preconditions.checkNotNull(whitelistBlacklistAvailabilityProfileConfig3, "isDeviceWhitelistedForHFR");
        this.mIsVideoFrameRateOverrideEnabled = newBooleanConfigValue("playback_isVideoFrameRateOverrideEnabled", false);
        this.mHighFrameRateQualityHolder = (HighFrameRateQualityHolderInterface) Preconditions.checkNotNull(highFrameRateQualityHolderInterface, "highFrameRateQualityHolder");
        ConfigType configType = ConfigType.SERVER;
        this.mAllowHdrDownloads = newBooleanConfigValue("playback_allowHdrDownloads", true, configType);
        this.mSupportsEmbeddedTrickplay = newBooleanConfigValue("playback_supportsEmbeddedTrickplay", false, configType);
        this.mSupportsDAIEmbeddedTrickplay = newBooleanConfigValue("playback_supportsDAIEmbeddedTrickplay", false, configType);
        this.mSupportsDAIEmbeddedTrickplayInBeta = newBooleanConfigValue("playback_supportsDAIEmbeddedTrickplayInBeta", false, configType);
        this.mIsDAITrickplayWeblabEnabled = newBooleanConfigValue("playback_isDAITrickplayWeblabEnabled", true, configType);
        this.mShouldPassAppIdentificationParameterInPlaybackUrlCalls = newBooleanConfigValue("playback_shouldPassAppIdentificationParameterInPlaybackUrlCalls", true, configType);
        this.mShouldReportPlayheadInLive = newBooleanConfigValue("playback_shouldReportPlayheadInLive", true, configType);
    }

    @Nonnull
    public String getLiveManifestCapabilities() {
        return this.mLiveManifestCapabilities.getValue();
    }

    public boolean isDashOverrideEnabled(ContentType contentType, ConsumptionType consumptionType) {
        if (ContentType.isLive(contentType)) {
            return true;
        }
        if (this.mIsSmoothOverrideEnabled.getValue().booleanValue() || this.mHardwareBlackList.getValue().contains(this.mHardware) || this.mDashManufacturerDeviceModelBlackList.getValue().contains(this.mManufacturerDeviceModel)) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType[consumptionType.ordinal()];
        if (i == 1) {
            return this.mIsDashStreamingOverrideEnabled.isAvailableForDevice();
        }
        if (i == 2) {
            return this.mIsDashDownloadOverrideEnabled.isAvailableForDevice();
        }
        throw new IllegalArgumentException("Unrecognized ConsumptionType " + consumptionType);
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.service.AVODServiceConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType;

        static {
            int[] iArr = new int[ConsumptionType.values().length];
            $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType = iArr;
            try {
                iArr[ConsumptionType.Streaming.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType[ConsumptionType.Download.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public boolean isMiyagiEnabled() {
        return this.mIsMiyagiEnabled.getValue().booleanValue();
    }

    public boolean isContentUrlOverrideEnabled() {
        return this.mIsContentUrlOverrideEnabled.getValue().booleanValue();
    }

    @Nonnull
    public AudioVideoUrls getOverrideContentUrl() {
        return new AudioVideoUrlsBuilder().setContentUrls(ImmutableList.of(new ContentUrl(this.mContentUrlOverrideValue.getValue(), Splash.PARAMS_SESSION_ID))).setAudioTrackMetadataList(ImmutableList.of()).setEncodeId("encodeId").setPlaybackSettingsOverride(Collections.emptyMap()).build();
    }

    public long getAdvertisingIdCollectorTimeoutInMillis() {
        return this.mAdvertisingIdCollectorTimeoutInMillis.getValue().longValue();
    }

    public boolean isAdvertisingIdCollectorUseStaleData() {
        return this.mAdvertisingIdCollectorUseStaleData.getValue().booleanValue();
    }

    public boolean isVideoFrameRateOverrideEnabled() {
        if (this.mIsDeviceWhitelistedForHFR.isAvailableForDevice()) {
            if (WeblabTreatment.T1.equals(PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_3P_AVIATOR_HFR_223619").getCurrentTreatment())) {
                DLog.logf("HFRPlayback HFRPerformanceEvaluator %s, weblab %s: T1", Boolean.valueOf(this.mHighFrameRateQualityHolder.isHFRPlaybackAllowedByPerfEvaluator()), "AIVPLAYERS_3P_AVIATOR_HFR_223619");
                return this.mHighFrameRateQualityHolder.isHFRPlaybackAllowedByPerfEvaluator();
            }
            DLog.logf("HFRPlayback HFRPerformanceEvaluator %s, config %s", Boolean.valueOf(this.mHighFrameRateQualityHolder.isHFRPlaybackAllowedByPerfEvaluator()), this.mIsVideoFrameRateOverrideEnabled.getValue());
            return this.mHighFrameRateQualityHolder.isHFRPlaybackAllowedByPerfEvaluator() && this.mIsVideoFrameRateOverrideEnabled.getValue().booleanValue();
        }
        DLog.logf("Device is not whitelisted for HFR");
        return false;
    }

    public boolean areHdrDownloadsAllowed() {
        return this.mAllowHdrDownloads.getValue().booleanValue();
    }

    public boolean supportsEmbeddedTrickplay() {
        return this.mSupportsEmbeddedTrickplay.getValue().booleanValue();
    }

    public boolean supportsDAIEmbeddedTrickplay() {
        return supportsDAIEmbeddedTrickplayInBeta() || isDAIEmbeddedTrickplayEnabledByWeblab() || this.mSupportsDAIEmbeddedTrickplay.getValue().booleanValue();
    }

    private boolean supportsDAIEmbeddedTrickplayInBeta() {
        return this.mSupportsDAIEmbeddedTrickplayInBeta.getValue().booleanValue() && BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }

    private boolean isDAIEmbeddedTrickplayEnabledByWeblab() {
        MobileWeblab mobileWeblab;
        if (!this.mIsDAITrickplayWeblabEnabled.getValue().booleanValue() || (mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("DAI_TRICKPLAY_389914")) == null) {
            return false;
        }
        mobileWeblab.trigger();
        return mobileWeblab.getCurrentTreatment() != WeblabTreatment.C;
    }

    public boolean shouldPassAppIdentificationParameterInPlaybackUrlCalls() {
        return this.mShouldPassAppIdentificationParameterInPlaybackUrlCalls.getValue().booleanValue();
    }

    public boolean shouldReportPlayheadInLive() {
        return this.mShouldReportPlayheadInLive.getValue().booleanValue();
    }
}

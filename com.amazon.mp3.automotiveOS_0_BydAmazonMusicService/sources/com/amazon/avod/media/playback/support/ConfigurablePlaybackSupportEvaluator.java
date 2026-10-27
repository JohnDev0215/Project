package com.amazon.avod.media.playback.support;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.config.DeviceCapabilityConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityConfig;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigurablePlaybackSupportEvaluator implements PlaybackSupportEvaluator {
    public static final String PRS_AV1_OVERRIDE = "AV1";
    public static final String PRS_AVC_OVERRIDE = "H264";
    public static final String PRS_HEVC_OVERRIDE = "H265";
    private final PlaybackSupportConfig mConfig;
    private final RendererSchemeController mRendererSchemeController;

    public ConfigurablePlaybackSupportEvaluator(@Nonnull RendererSchemeController rendererSchemeController) {
        this(PlaybackSupportConfig.getInstance(), rendererSchemeController);
    }

    ConfigurablePlaybackSupportEvaluator(@Nonnull PlaybackSupportConfig playbackSupportConfig, @Nonnull RendererSchemeController rendererSchemeController) {
        this.mConfig = (PlaybackSupportConfig) Preconditions.checkNotNull(playbackSupportConfig, "config");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
    }

    @Nonnull
    DeviceCapabilityDetector getCapabilityDetector(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mRendererSchemeController.getRendererScheme(rendererSchemeType, null).getCapabilityDetector();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public PlaybackSupportInfo getPlaybackSupportInfo(@Nullable RendererSchemeType rendererSchemeType) {
        return getCapabilityDetector(rendererSchemeType).getPlaybackSupportInfo();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isUhdSupported(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mConfig.isUhdEnabled() && getCapabilityDetector(rendererSchemeType).isUhdSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isHdSupported(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mConfig.isHdEnabled() && getCapabilityDetector(rendererSchemeType).isHdSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isHdrSupported(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mConfig.isHdrEnabled() && getCapabilityDetector(rendererSchemeType).isHdrSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isDolbyDigitalPlusSupported(@Nullable RendererSchemeType rendererSchemeType) {
        return getCapabilityDetector(rendererSchemeType).isDolbyDigitalPlusSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isHevcSupported(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mConfig.isHevcEnabled() && getCapabilityDetector(rendererSchemeType).isHevcSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isAv1Supported(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mConfig.isAv1Enabled() && getCapabilityDetector(rendererSchemeType).isAv1Supported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public List<String> getDeviceVideoCodecOverride(@Nullable RendererSchemeType rendererSchemeType) {
        if (!this.mConfig.isDeviceCapabilityBasedVideoCodecOverrideEnabled()) {
            return this.mConfig.getDeviceVideoCodecOverride();
        }
        ImmutableList.Builder builderAdd = new ImmutableList.Builder().add(PRS_AVC_OVERRIDE);
        if (isHevcSupported(rendererSchemeType)) {
            builderAdd.add(PRS_HEVC_OVERRIDE);
        }
        if (isAv1Supported(rendererSchemeType)) {
            builderAdd.add(PRS_AV1_OVERRIDE);
        }
        return builderAdd.build();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public List<String> getDeviceBitrateAdaptationsOverride(@Nullable RendererSchemeType rendererSchemeType) {
        return this.mConfig.getDeviceBitrateAdaptationsOverride();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isMultiKeyDecryptionSupported(@Nullable RendererSchemeType rendererSchemeType, @Nonnull ContentType contentType) {
        return getCapabilityDetector(rendererSchemeType).isMultiKeyDecryptionSupported(contentType);
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats(@Nullable RendererSchemeType rendererSchemeType) {
        return getCapabilityDetector(rendererSchemeType).getSupportedHdrFormats();
    }

    static class PlaybackSupportConfig extends MediaConfigBase {
        private final ConfigurationValue<List<String>> mDeviceBitrateAdaptationsOverride;
        private final ConfigurationValue<Boolean> mDeviceCapabilityBasedVideoCodecOverrideEnabled;
        private final DeviceCapabilityConfig mDeviceCapabilityConfig;
        private final ConfigurationValue<List<String>> mDeviceVideoCodecOverride;
        private final WhitelistBlacklistAvailabilityConfig mIsAv1Enabled;
        private final WhitelistBlacklistAvailabilityConfig mIsHdrEnabled;
        private final WhitelistBlacklistAvailabilityConfig mIsHevcEnabled;

        public static PlaybackSupportConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private static final class SingletonHolder {
            public static final PlaybackSupportConfig INSTANCE = new PlaybackSupportConfig();

            private SingletonHolder() {
            }
        }

        private PlaybackSupportConfig() {
            ThirdPartyConfigurationProfile thirdPartyConfigurationProfile = ThirdPartyConfigurationProfile.getInstance();
            Boolean bool = Boolean.FALSE;
            this.mIsHdrEnabled = new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.IS_HDR_ENABLED, thirdPartyConfigurationProfile, "HD_HEVC:false;HD:false;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false);
            ThirdPartyConfigurationProfile thirdPartyConfigurationProfile2 = ThirdPartyConfigurationProfile.getInstance();
            Locale locale = Locale.US;
            ThirdPartyProfileName thirdPartyProfileName = ThirdPartyProfileName.HD_HEVC;
            Boolean bool2 = Boolean.TRUE;
            ThirdPartyProfileName thirdPartyProfileName2 = ThirdPartyProfileName.HD;
            ThirdPartyProfileName thirdPartyProfileName3 = ThirdPartyProfileName.HDR;
            ThirdPartyProfileName thirdPartyProfileName4 = ThirdPartyProfileName.UHD_HDR;
            ThirdPartyProfileName thirdPartyProfileName5 = ThirdPartyProfileName.SD_MEDIACODEC_SOFTWARE_PLAYREADY;
            this.mIsHevcEnabled = new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.IS_HEVC_ENABLED, thirdPartyConfigurationProfile2, String.format(locale, "%s:%s;%s:%s;%s:%s;%s:%s;%s:%s", thirdPartyProfileName, bool2, thirdPartyProfileName2, bool, thirdPartyProfileName3, bool2, thirdPartyProfileName4, bool2, thirdPartyProfileName5, bool), bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false);
            this.mIsAv1Enabled = new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.IS_AV1_ENABLED, ThirdPartyConfigurationProfile.getInstance(), String.format(locale, "%s:%s;%s:%s;%s:%s;%s:%s;%s:%s", thirdPartyProfileName, bool, thirdPartyProfileName2, bool, thirdPartyProfileName3, bool, thirdPartyProfileName4, bool, thirdPartyProfileName5, bool), bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false);
            this.mDeviceCapabilityBasedVideoCodecOverrideEnabled = newBooleanConfigValue("playback_deviceCapabilityBasedVideoCodecOverrideEnabled", true);
            this.mDeviceVideoCodecOverride = newSemicolonDelimitedStringListConfigurationValue("prs_DeviceVideoCodecOverride", new String[]{ConfigurablePlaybackSupportEvaluator.PRS_AVC_OVERRIDE});
            this.mDeviceBitrateAdaptationsOverride = newSemicolonDelimitedStringListConfigurationValue("prs_DeviceBitrateAdaptationsOverride_2", new String[0]);
            this.mDeviceCapabilityConfig = (DeviceCapabilityConfig) Preconditions.checkNotNull(DeviceCapabilityConfig.getInstance(), "deviceCapabilityConfig");
        }

        public boolean isUhdEnabled() {
            return this.mDeviceCapabilityConfig.canPlayUltraHD();
        }

        public boolean isHdEnabled() {
            return this.mDeviceCapabilityConfig.canPlayHD();
        }

        public boolean isHdrEnabled() {
            return this.mIsHdrEnabled.isAvailableForDevice();
        }

        public boolean isHevcEnabled() {
            return this.mIsHevcEnabled.isAvailableForDevice();
        }

        public boolean isAv1Enabled() {
            return this.mIsAv1Enabled.isAvailableForDevice();
        }

        public boolean isDeviceCapabilityBasedVideoCodecOverrideEnabled() {
            return this.mDeviceCapabilityBasedVideoCodecOverrideEnabled.getValue().booleanValue();
        }

        @Nonnull
        public List<String> getDeviceVideoCodecOverride() {
            return this.mDeviceVideoCodecOverride.getValue();
        }

        @Nonnull
        public List<String> getDeviceBitrateAdaptationsOverride() {
            return this.mDeviceBitrateAdaptationsOverride.getValue();
        }
    }
}

package com.amazon.avod.config;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityConfig;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class DeviceCapabilityConfig extends ConfigBase {
    private final WhitelistBlacklistAvailabilityConfig mCanPlay1080pHD;
    private final WhitelistBlacklistAvailabilityConfig mCanPlayHD;
    private final WhitelistBlacklistAvailabilityConfig mCanPlayUltraHD;
    private final ConfigurationValue<Boolean> mIsDDPlusWithAtmosSupported;
    private final ConfigurationValue<Boolean> mSupportsDolby5dot1;
    private final ConfigurationValue<Boolean> mSupportsDownloading;
    private final ConfigurationValue<Boolean> mSupportsWANStreaming;

    private static class Singleton {
        private static DeviceCapabilityConfig INSTANCE = new DeviceCapabilityConfig();

        private Singleton() {
        }
    }

    public static final DeviceCapabilityConfig getInstance() {
        return Singleton.INSTANCE;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private DeviceCapabilityConfig() {
        ThirdPartyConfigurationProfile thirdPartyConfigurationProfile = ThirdPartyConfigurationProfile.getInstance();
        Boolean bool = Boolean.FALSE;
        this(new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.CAN_PLAY_HD, thirdPartyConfigurationProfile, "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false), new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.CAN_PLAY_1080P_HD, ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false), new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.CAN_PLAY_ULTRA_HD, ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:false;HD:false;HDR:false;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false));
    }

    DeviceCapabilityConfig(@Nonnull WhitelistBlacklistAvailabilityConfig whitelistBlacklistAvailabilityConfig, @Nonnull WhitelistBlacklistAvailabilityConfig whitelistBlacklistAvailabilityConfig2, @Nonnull WhitelistBlacklistAvailabilityConfig whitelistBlacklistAvailabilityConfig3) {
        super("com.amazon.avod.config.DeviceCapabilityConfig");
        this.mCanPlayHD = (WhitelistBlacklistAvailabilityConfig) Preconditions.checkNotNull(whitelistBlacklistAvailabilityConfig);
        this.mCanPlay1080pHD = (WhitelistBlacklistAvailabilityConfig) Preconditions.checkNotNull(whitelistBlacklistAvailabilityConfig2);
        this.mCanPlayUltraHD = (WhitelistBlacklistAvailabilityConfig) Preconditions.checkNotNull(whitelistBlacklistAvailabilityConfig3);
        ConfigType configType = ConfigType.SERVER;
        this.mSupportsDolby5dot1 = newBooleanConfigValue("supportsDolby5dot1", false, configType);
        this.mIsDDPlusWithAtmosSupported = newBooleanConfigValue("audio_isDolbyDigitalPlusWithAtmosSupported", false, configType);
        this.mSupportsWANStreaming = newBooleanConfigValue("isWANStreamingSupported", true, configType);
        this.mSupportsDownloading = newBooleanConfigValue("supportsDownloading", true, configType);
    }

    public boolean canPlayHD() {
        return this.mCanPlayHD.isAvailableForDevice();
    }

    public boolean canPlayUltraHD() {
        return this.mCanPlayUltraHD.isAvailableForDevice();
    }

    public boolean supportsDolby51() {
        return this.mSupportsDolby5dot1.getValue().booleanValue();
    }
}

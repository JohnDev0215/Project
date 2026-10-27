package com.amazon.avod.playbackclient.trickplay;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class TrickplayConfig extends ConfigBase {
    private final ConfigurationValue<String> mDesiredResolution;
    private final ConfigurationValue<Boolean> mIsTrickplayEnabled;
    private final ConfigurationValue<Boolean> mIsTrickplayEnabledOverWan;
    private final ConfigurationValue<Integer> mLastParseMemoryMaxBytes;
    private final ConfigurationValue<String> mLastPlayedFrameImagesRelativePath;
    private final ConfigurationValue<Integer> mMaxParseMemoryBytes;
    private final ConfigurationValue<Boolean> mShouldUseDeviceResolution;
    private final ConfigurationValue<Long> mSkipBackAfterSeekingTimeMillis;

    public enum FrameImageResolution {
        P480("480p"),
        P720("720p");

        private final String mParamValue;

        FrameImageResolution(String str) {
            this.mParamValue = str;
        }

        public String getParamValue() {
            return this.mParamValue;
        }
    }

    private static class SingletonHolder {
        private static final TrickplayConfig INSTANCE = new TrickplayConfig();

        private SingletonHolder() {
        }
    }

    public static final TrickplayConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private TrickplayConfig() {
        super("com.amazon.avod.trickplay");
        ConfigType configType = ConfigType.INTERNAL;
        this.mLastPlayedFrameImagesRelativePath = newStringConfigValue("lastPlayedFrameImages_relativePath", "lastPlayedFrameImages", configType);
        ConfigType configType2 = ConfigType.SERVER;
        this.mIsTrickplayEnabled = newBooleanConfigValue("IsTrickplayEnabled", true, configType2);
        this.mIsTrickplayEnabledOverWan = newBooleanConfigValue("IsTrickplayEnabledOverWAN", true, configType2);
        ThirdPartyConfigurationProfile thirdPartyConfigurationProfile = ThirdPartyConfigurationProfile.getInstance();
        Locale locale = Locale.US;
        FrameImageResolution frameImageResolution = FrameImageResolution.P720;
        this.mDesiredResolution = new WhitelistBlacklistAvailabilityProfileConfig("TrickplayResolution", thirdPartyConfigurationProfile, String.format(locale, "HD_HEVC:%s;HD:%s;HDR:%s;UHD_HDR:%s", frameImageResolution.getParamValue(), frameImageResolution.getParamValue(), frameImageResolution.getParamValue(), frameImageResolution.getParamValue()), FrameImageResolution.P480.getParamValue(), ProfileConfigParser.StringProfileResultParser.getResultParser(), false).asConfigValue();
        this.mShouldUseDeviceResolution = newBooleanConfigValue("playbackfeature_trickplay_useDeviceResolution", false, configType2);
        this.mSkipBackAfterSeekingTimeMillis = newLongConfigValue("TrickplaySeekToSkipBackTimeMillis", 3000L, configType2);
        this.mMaxParseMemoryBytes = newIntConfigValue("TrickplayMaxParseBytes", 100000, configType2);
        this.mLastParseMemoryMaxBytes = newIntConfigValue("TrickplayIterationMaxByteAllocation", 1024, configType);
    }

    public boolean isTrickplayEnabled() {
        return this.mIsTrickplayEnabled.getValue().booleanValue();
    }

    public boolean isTrickplayEnabledOverWAN() {
        return this.mIsTrickplayEnabledOverWan.getValue().booleanValue();
    }

    public String getDesiredTrickplayResolution() {
        return this.mDesiredResolution.getValue();
    }

    public int getMaxTrickplayParseMemoryBytes() {
        return this.mMaxParseMemoryBytes.getValue().intValue();
    }

    public boolean shouldUseDeviceTrickplayResolution() {
        return this.mShouldUseDeviceResolution.getValue().booleanValue();
    }

    public int getPreviousMaxAllocation() {
        return this.mLastParseMemoryMaxBytes.getValue().intValue();
    }

    public void updateMaxAllocation(int i) {
        this.mLastParseMemoryMaxBytes.updateValue(Integer.valueOf(i));
    }
}

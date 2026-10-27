package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.util.DataUnit;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class VideoQualityConfig extends MediaConfigBase {
    private static final int DEFAULT_MAX_HD_PLAYABLE_BITRATE = 10000000;
    private static final int DEFAULT_MAX_UHD_PLAYABLE_BITRATE = 15000000;
    private final ConfigurationValue<Integer> mHighestPlayableBitrate;
    private final ConfigurationValue<Integer> mLowestPlayableBitrate;
    private final ConfigurationValue<Integer> mMaxBandwidth;
    private final ConfigurationValue<Integer> mMaxBandwidthHistoryCount;
    private final ConfigurationValue<Integer> mMaximumPlayableHorizontalResolution;
    private final ConfigurationValue<Integer> mMaximumPlayableVerticalResolution;
    private final ConfigurationValue<Integer> mMinimumPlayableHorizontalResolution;
    private final ConfigurationValue<Integer> mMinimumPlayableVerticalResolution;

    protected VideoQualityConfig(DeviceConfiguration deviceConfiguration, String str) {
        this.mMaxBandwidth = newIntConfigValue(str + "_maxBandWidth", (int) DataUnit.MEGABYTES.toBits(2.0f));
        this.mMaxBandwidthHistoryCount = newIntConfigValue(str + "_maxBandwidthHistoryCount", 10);
        ThirdPartyConfigurationProfile thirdPartyConfigurationProfile = ThirdPartyConfigurationProfile.getInstance();
        Locale locale = Locale.US;
        Integer numValueOf = Integer.valueOf(DEFAULT_MAX_HD_PLAYABLE_BITRATE);
        this.mHighestPlayableBitrate = new WhitelistBlacklistAvailabilityProfileConfig(str + "_highestPlayableBitrate", thirdPartyConfigurationProfile, String.format(locale, "HD_HEVC:%d;HD:%d;HDR:%d;UHD_HDR:%d", numValueOf, numValueOf, numValueOf, Integer.valueOf(DEFAULT_MAX_UHD_PLAYABLE_BITRATE)), numValueOf, ProfileConfigParser.IntProfileResultParser.getResultParser(), false).asConfigValue();
        this.mLowestPlayableBitrate = newIntConfigValue(str + "_lowestPlayableBitrate", 0);
        ThirdPartyConfigurationProfile thirdPartyConfigurationProfile2 = ThirdPartyConfigurationProfile.getInstance();
        VideoResolution.ResolutionBand resolutionBand = VideoResolution.ResolutionBand.HD_1080P;
        VideoResolution.ResolutionBand resolutionBand2 = VideoResolution.ResolutionBand.ULTRA_HD;
        this.mMaximumPlayableVerticalResolution = new WhitelistBlacklistAvailabilityProfileConfig(str + "_maximumPlayableVerticalResolution", thirdPartyConfigurationProfile2, String.format(locale, "HD_HEVC:%d;HD:%d;HDR:%d;UHD_HDR:%d", Integer.valueOf(resolutionBand.getMinHeight()), Integer.valueOf(resolutionBand.getMinHeight()), Integer.valueOf(resolutionBand.getMinHeight()), Integer.valueOf(resolutionBand2.getMinHeight())), Integer.valueOf(deviceConfiguration.getScreenHeight()), ProfileConfigParser.IntProfileResultParser.getResultParser(), false).asConfigValue();
        this.mMinimumPlayableVerticalResolution = newIntConfigValue(str + "_minimumPlayableVerticalResolution", 0);
        this.mMaximumPlayableHorizontalResolution = new WhitelistBlacklistAvailabilityProfileConfig(str + "_maximumPlayableHorizontalResolution", ThirdPartyConfigurationProfile.getInstance(), String.format(locale, "HD_HEVC:%d;HD:%d;HDR:%d;UHD_HDR:%d", Integer.valueOf(resolutionBand.getMinWidth()), Integer.valueOf(resolutionBand.getMinWidth()), Integer.valueOf(resolutionBand.getMinWidth()), Integer.valueOf(resolutionBand2.getMinWidth())), Integer.valueOf(deviceConfiguration.getScreenWidth()), ProfileConfigParser.IntProfileResultParser.getResultParser(), false).asConfigValue();
        this.mMinimumPlayableHorizontalResolution = newIntConfigValue(str + "_minimumPlayableHorizontalResolution", 0);
    }

    public int getMaxBandwidth() {
        return this.mMaxBandwidth.getValue().intValue();
    }

    public int getMaxBandwidthHistoryCount() {
        return this.mMaxBandwidthHistoryCount.getValue().intValue();
    }

    public int getHighestPlayableBitrate() {
        return this.mHighestPlayableBitrate.getValue().intValue();
    }

    public int getLowestPlayableBitrate() {
        return this.mLowestPlayableBitrate.getValue().intValue();
    }

    public int getMaximumPlayableVerticalResolution() {
        return this.mMaximumPlayableVerticalResolution.getValue().intValue();
    }

    public int getMinimumPlayableVerticalResolution() {
        return this.mMinimumPlayableVerticalResolution.getValue().intValue();
    }

    public int getMaximumPlayableHorizontalResolution() {
        return this.mMaximumPlayableHorizontalResolution.getValue().intValue();
    }

    public int getMinimumPlayableHorizontalResolution() {
        return this.mMinimumPlayableHorizontalResolution.getValue().intValue();
    }
}

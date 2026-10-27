package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.playback.capability.DeviceCapability;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingDeviceCapabilityConfig extends MediaConfigBase {
    public static final SmoothStreamingDeviceCapabilityConfig INSTANCE = new SmoothStreamingDeviceCapabilityConfig();
    private final ConfigurationValue<Integer> mLowestPlaybackBitrate = newIntConfigValue("playback_lowestPlaybackBitrate", 300000);
    private final ConfigurationValue<Integer> mLowEndDeviceStartingBitrate = newIntConfigValue("playback_lowEndDeviceStartingBitrate", 150000);
    private final ConfigurationValue<Integer> mSingleCoreLowBitrate = newIntConfigValue("playback_singleCoreLowBitrate", 450000);
    private final ConfigurationValue<Integer> mSingleCoreHighBitrate = newIntConfigValue("playback_singleCoreHighBitrate", 900000);
    private final ConfigurationValue<Integer> mSingleCoreNominalFrequencyThresholdInMHz = newIntConfigValue("playback_singleCoreFrequency", 1600);
    private final ConfigurationValue<Integer> mDualCoreLowBitrate = newIntConfigValue("playback_dualCoreLowBitrate", 1350000);
    private final ConfigurationValue<Integer> mDualCoreHighBitrate = newIntConfigValue("playback_dualCoreHighBitrate", 2000000);
    private final ConfigurationValue<Integer> mDualCoreNominalFrequencyThresholdInMHz = newIntConfigValue("playback_dualCoreFrequency", DeviceCapability.DEFAULT_CORE_FREQUENCY_IN_MHZ);

    private SmoothStreamingDeviceCapabilityConfig() {
    }

    public int getLowestPlaybackBitrate() {
        return this.mLowestPlaybackBitrate.getValue().intValue();
    }

    public int getSingleCoreLowBitrate() {
        return this.mSingleCoreLowBitrate.getValue().intValue();
    }

    public int getSingleCoreHighBitrate() {
        return this.mSingleCoreHighBitrate.getValue().intValue();
    }

    public int getSingleCoreNominalFrequencyThresholdInMHz() {
        return this.mSingleCoreNominalFrequencyThresholdInMHz.getValue().intValue();
    }

    public int getDualCoreLowBitrate() {
        return this.mDualCoreLowBitrate.getValue().intValue();
    }

    public int getDualCoreHighBitrate() {
        return this.mDualCoreHighBitrate.getValue().intValue();
    }

    public int getDualCoreNominalFrequencyThresholdInMHz() {
        return this.mDualCoreNominalFrequencyThresholdInMHz.getValue().intValue();
    }

    public int getLowEndDeviceStartingBitrate() {
        return this.mLowEndDeviceStartingBitrate.getValue().intValue();
    }
}

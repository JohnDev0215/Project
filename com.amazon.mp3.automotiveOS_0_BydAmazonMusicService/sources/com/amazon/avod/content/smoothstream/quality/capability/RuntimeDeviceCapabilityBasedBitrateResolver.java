package com.amazon.avod.content.smoothstream.quality.capability;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingDeviceCapabilityConfig;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RuntimeDeviceCapabilityBasedBitrateResolver {
    private static final Range<Integer> VALID_BITRATE_RANGE = Range.closed(1000, Integer.MAX_VALUE);
    private static final Range<Integer> VALID_CORE_FREQUENCY_RANGE = Range.closed(300, 20000);
    private final SmoothStreamingDeviceCapabilityConfig mDeviceCapabilityConfig;

    public RuntimeDeviceCapabilityBasedBitrateResolver(SmoothStreamingDeviceCapabilityConfig smoothStreamingDeviceCapabilityConfig) {
        Preconditions.checkArgument(smoothStreamingDeviceCapabilityConfig != null);
        this.mDeviceCapabilityConfig = smoothStreamingDeviceCapabilityConfig;
    }

    public int getHighestSustainableBitrate(@Nonnull DeviceCapability deviceCapability) throws ContentException {
        Preconditions.checkNotNull(deviceCapability, "deviceCapability");
        int lowestPlaybackBitrate = this.mDeviceCapabilityConfig.getLowestPlaybackBitrate();
        int singleCoreNominalFrequencyThresholdInMHz = this.mDeviceCapabilityConfig.getSingleCoreNominalFrequencyThresholdInMHz();
        int dualCoreNominalFrequencyThresholdInMHz = this.mDeviceCapabilityConfig.getDualCoreNominalFrequencyThresholdInMHz();
        int singleCoreHighBitrate = this.mDeviceCapabilityConfig.getSingleCoreHighBitrate();
        int dualCoreHighBitrate = this.mDeviceCapabilityConfig.getDualCoreHighBitrate();
        int singleCoreLowBitrate = this.mDeviceCapabilityConfig.getSingleCoreLowBitrate();
        int dualCoreLowBitrate = this.mDeviceCapabilityConfig.getDualCoreLowBitrate();
        Range<Integer> range = VALID_CORE_FREQUENCY_RANGE;
        if (!range.contains(Integer.valueOf(singleCoreNominalFrequencyThresholdInMHz))) {
            ContentException contentException = new ContentException(String.format(Locale.US, "Invalid single core frequency threshold is specified in the server config %d", Integer.valueOf(singleCoreNominalFrequencyThresholdInMHz)));
            DLog.exceptionf(contentException);
            throw contentException;
        }
        if (!range.contains(Integer.valueOf(dualCoreNominalFrequencyThresholdInMHz))) {
            ContentException contentException2 = new ContentException(String.format(Locale.US, "Invalid dual core frequency threshold is specified in the server config %d", Integer.valueOf(dualCoreNominalFrequencyThresholdInMHz)));
            DLog.exceptionf(contentException2);
            throw contentException2;
        }
        DLog.devf("Device capabilities returned: %s", deviceCapability.toString());
        DLog.devf("Single core frequency threshold value: %d", Integer.valueOf(singleCoreNominalFrequencyThresholdInMHz));
        DLog.devf("Dual core frequency threshold value: %d", Integer.valueOf(dualCoreNominalFrequencyThresholdInMHz));
        DLog.devf("Single core low bitrate: %d, high bitrate: %d", Integer.valueOf(singleCoreLowBitrate), Integer.valueOf(singleCoreHighBitrate));
        DLog.devf("Dual core low bitrate: %d, high bitrate: %d", Integer.valueOf(dualCoreLowBitrate), Integer.valueOf(dualCoreHighBitrate));
        int numberOfCores = deviceCapability.getNumberOfCores();
        if (deviceCapability.getHardwareAcceleration() || numberOfCores > 2) {
            lowestPlaybackBitrate = Integer.MAX_VALUE;
        } else if (deviceCapability.getNeonSupport()) {
            int coreFrequency = deviceCapability.getCoreFrequency();
            lowestPlaybackBitrate = numberOfCores == 1 ? coreFrequency >= singleCoreNominalFrequencyThresholdInMHz ? singleCoreHighBitrate : singleCoreLowBitrate : coreFrequency >= dualCoreNominalFrequencyThresholdInMHz ? dualCoreHighBitrate : dualCoreLowBitrate;
        }
        if (!VALID_BITRATE_RANGE.contains(Integer.valueOf(lowestPlaybackBitrate))) {
            ContentException contentException3 = new ContentException(String.format(Locale.US, "Invalid bitrate is specified in the server config %d", Integer.valueOf(lowestPlaybackBitrate)));
            DLog.exceptionf(contentException3);
            throw contentException3;
        }
        DLog.logf("The calculated bitrate cap value is %d", Integer.valueOf(lowestPlaybackBitrate));
        return lowestPlaybackBitrate;
    }

    public int getLowestPlaybackBitrate() {
        return this.mDeviceCapabilityConfig.getLowestPlaybackBitrate();
    }

    public int getLowEndDeviceStartingBitrate() {
        return this.mDeviceCapabilityConfig.getLowEndDeviceStartingBitrate();
    }

    public boolean isLowEndDevice(@Nonnull DeviceCapability deviceCapability) throws ContentException {
        return getHighestSustainableBitrate(deviceCapability) <= this.mDeviceCapabilityConfig.getSingleCoreLowBitrate();
    }
}

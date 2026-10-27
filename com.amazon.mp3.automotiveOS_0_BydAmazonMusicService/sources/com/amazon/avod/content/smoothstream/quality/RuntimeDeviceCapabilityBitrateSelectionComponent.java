package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingDeviceCapabilityConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.smoothstream.quality.capability.RuntimeDeviceCapabilityBasedBitrateResolver;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class RuntimeDeviceCapabilityBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final RuntimeDeviceCapabilityBasedBitrateResolver mBitrateResolver;
    private QualityLevelClamper mClamper;
    private final QualityLevelClamperFactory mClamperFactory;
    private final DeviceCapability mDeviceCapability;

    public RuntimeDeviceCapabilityBitrateSelectionComponent(QualityLevelClamperFactory qualityLevelClamperFactory, DeviceCapability deviceCapability) {
        this(qualityLevelClamperFactory, deviceCapability, new RuntimeDeviceCapabilityBasedBitrateResolver(SmoothStreamingDeviceCapabilityConfig.INSTANCE));
    }

    RuntimeDeviceCapabilityBitrateSelectionComponent(QualityLevelClamperFactory qualityLevelClamperFactory, DeviceCapability deviceCapability, RuntimeDeviceCapabilityBasedBitrateResolver runtimeDeviceCapabilityBasedBitrateResolver) {
        this.mClamperFactory = qualityLevelClamperFactory;
        this.mBitrateResolver = runtimeDeviceCapabilityBasedBitrateResolver;
        this.mDeviceCapability = deviceCapability;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        int highestSustainableBitrate;
        try {
            highestSustainableBitrate = this.mBitrateResolver.getHighestSustainableBitrate(this.mDeviceCapability);
            int lowestPlaybackBitrate = this.mBitrateResolver.getLowestPlaybackBitrate();
            DLog.logf("Calculated highest sustainable bitrate: %d", Integer.valueOf(highestSustainableBitrate));
            DLog.logf("Minimum required bitrate: %d", Integer.valueOf(lowestPlaybackBitrate));
            DLog.logf("Device info: %s", this.mDeviceCapability.toString());
            if (highestSustainableBitrate < lowestPlaybackBitrate) {
                DLog.errorf("The device is capable of a sustained playback at %d bitrate but the minimum bitrate is specified as %d.", Integer.valueOf(highestSustainableBitrate), Integer.valueOf(lowestPlaybackBitrate));
                DLog.errorf("Using the minimum playback bitrate of %d", Integer.valueOf(lowestPlaybackBitrate));
            }
            if (!ContentSessionType.isStreamingSession(contentSessionContext.getSessionType()) && this.mBitrateResolver.isLowEndDevice(this.mDeviceCapability)) {
                highestSustainableBitrate = this.mBitrateResolver.getLowEndDeviceStartingBitrate();
                DLog.logf("The device is a low end device, its starting bitrate: %d", Integer.valueOf(highestSustainableBitrate));
            }
        } catch (ContentException e) {
            DLog.exceptionf(e, "Failed to calculate the bitrate cap. No cap will be applied.", new Object[0]);
            highestSustainableBitrate = Integer.MAX_VALUE;
        }
        this.mClamper = this.mClamperFactory.newDeviceCapabilityBasedQualityLevelClamper(contentSessionContext.getManifest(), highestSustainableBitrate);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        Preconditions.checkArgument(streamingBitrateSelectionState.getStream().isVideo(), "Only video streams are supported for now.");
        return this.mClamper.clampQualityLevel((VideoQualityLevel) qualityLevel);
    }
}

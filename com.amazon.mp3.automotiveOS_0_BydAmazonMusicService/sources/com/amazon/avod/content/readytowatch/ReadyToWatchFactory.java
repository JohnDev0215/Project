package com.amazon.avod.content.readytowatch;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ReadyToWatchFactory {
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final DefaultQualityConfiguration mQualityDefaults;

    public ReadyToWatchFactory(DefaultQualityConfiguration defaultQualityConfiguration, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mQualityDefaults = defaultQualityConfiguration;
        this.mPlaybackConfig = smoothStreamingPlaybackConfig;
    }

    @Nonnull
    public ReadyToWatch newReadyToWatch(@Nonnull ContentSessionContext contentSessionContext, @Nonnull ContentAccessor contentAccessor) {
        int initalBandwidthEstimateKbps = this.mPlaybackConfig.getInitalBandwidthEstimateKbps();
        int bitrate = this.mQualityDefaults.getVideoQuality(contentSessionContext).getBitrate();
        SmoothStreamingReadyToWatch smoothStreamingReadyToWatch = new SmoothStreamingReadyToWatch(contentSessionContext, contentAccessor);
        smoothStreamingReadyToWatch.setInitialEstimates(initalBandwidthEstimateKbps, bitrate);
        return smoothStreamingReadyToWatch;
    }
}

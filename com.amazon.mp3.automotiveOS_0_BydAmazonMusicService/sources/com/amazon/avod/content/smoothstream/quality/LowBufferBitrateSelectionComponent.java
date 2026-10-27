package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.framework.network.NetworkHistory;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class LowBufferBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final SmoothStreamingPlaybackConfig mConfig;
    private final long mControlTimeInNanoseconds;
    private QualityLevel mInitialQuality;
    private final NetworkHistoryManager mNetworkHistoryManager;
    private final float mThreshold;

    public LowBufferBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, NetworkHistoryManager networkHistoryManager) {
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mNetworkHistoryManager = networkHistoryManager;
        this.mThreshold = smoothStreamingHeuristicConfig.getLookupFactor();
        this.mControlTimeInNanoseconds = smoothStreamingHeuristicConfig.getLowBufferControlDurationInNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        int availableBandwidth;
        Manifest manifest = contentSessionContext.getManifest();
        NetworkHistory currentNetworkHistory = this.mConfig.useLastBandwidthForAccessPointOnResume() ? this.mNetworkHistoryManager.getCurrentNetworkHistory() : null;
        if (currentNetworkHistory == null) {
            availableBandwidth = this.mConfig.getMaxVideoBitrateWhenBufferEmpty();
        } else {
            availableBandwidth = getAvailableBandwidth(currentNetworkHistory.getAverageBandwidthBps());
        }
        StreamIndex videoStream = manifest.getVideoStream();
        QualityLevel qualityLevelSelectQualityLevelForBandwidth = selectQualityLevelForBandwidth(contentSessionContext.getState().getConsumptionHead(videoStream.getIndex()), videoStream, availableBandwidth);
        this.mInitialQuality = qualityLevelSelectQualityLevelForBandwidth;
        DLog.logf("Selected initial video quality level of %s", Integer.valueOf(qualityLevelSelectQualityLevelForBandwidth.getBitrate()));
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getPreviousQuality() == null && streamingBitrateSelectionState.getBufferedContentInNanos() == 0) {
            return this.mInitialQuality;
        }
        if (streamingBitrateSelectionState.getBufferedContentInNanos() > this.mControlTimeInNanoseconds) {
            return qualityLevel;
        }
        return selectQualityLevelForBandwidth(streamingBitrateSelectionState.getChunkIndex(), streamingBitrateSelectionState.getStream(), getAvailableBandwidth(streamingBitrateSelectionState.getBandwidth()));
    }

    private int getAvailableBandwidth(int i) {
        return (int) (i * this.mThreshold);
    }

    private QualityLevel selectQualityLevelForBandwidth(int i, StreamIndex streamIndex, int i2) {
        return streamIndex.getQualityLevelLessThanEqual(i, i2);
    }
}

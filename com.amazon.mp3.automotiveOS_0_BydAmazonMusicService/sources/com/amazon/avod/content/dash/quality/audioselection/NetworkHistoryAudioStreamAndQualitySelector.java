package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.framework.network.NetworkHistory;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkHistoryAudioStreamAndQualitySelector implements AudioStreamAndQualitySelector {
    private final double[][] mAudioTransferSpeedVariationToSpeedShare;
    private final Heuristics mHeuristics;
    private final int mMaxBitrate;
    private final NetworkHistoryManager mNetworkHistoryManager;

    public NetworkHistoryAudioStreamAndQualitySelector(@Nonnull NetworkHistoryManager networkHistoryManager, @Nonnull Heuristics heuristics, int i) {
        Heuristics heuristics2 = (Heuristics) Preconditions.checkNotNull(heuristics, "heuristics");
        this.mHeuristics = heuristics2;
        this.mNetworkHistoryManager = (NetworkHistoryManager) Preconditions.checkNotNull(networkHistoryManager, "networkHistoryManager");
        Preconditions.checkNotNull(heuristics2.getHeuristicsPlaybackConfig(), "heuristicPlaybackConfig");
        this.mAudioTransferSpeedVariationToSpeedShare = heuristics2.getHeuristicsPlaybackConfig().getAudioTransferSpeedVariationFactorToTransferSpeedSharePair();
        this.mMaxBitrate = i;
    }

    @Override // com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualitySelector
    @Nonnull
    public List<AudioStreamAndQualityPair> select(@Nonnull List<StreamIndex> list) {
        long bits;
        long bits2;
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(list.size() > 0, "Number of audio streams must be positive");
        long bandwidthAverageBytesPerSecond = this.mHeuristics.getBandwidthAverageBytesPerSecond();
        long bandwidthStandardDeviationBytesPerSecond = this.mHeuristics.getBandwidthStandardDeviationBytesPerSecond();
        if (bandwidthAverageBytesPerSecond != 0) {
            DataUnit dataUnit = DataUnit.BYTES;
            bits = dataUnit.toBits(bandwidthAverageBytesPerSecond);
            bits2 = dataUnit.toBits(bandwidthStandardDeviationBytesPerSecond);
        } else {
            NetworkHistory currentNetworkHistory = this.mNetworkHistoryManager.getCurrentNetworkHistory();
            long heuristicAverageBandwidthBps = currentNetworkHistory == null ? 0L : currentNetworkHistory.getHeuristicAverageBandwidthBps();
            long heuristicStdevBandwidthBps = currentNetworkHistory != null ? currentNetworkHistory.getHeuristicStdevBandwidthBps() : 0L;
            bits = heuristicAverageBandwidthBps;
            bits2 = heuristicStdevBandwidthBps;
        }
        QALog.newQALog(PlaybackQAEvent.HEURISTIC_NETWORK_INFO_LOAD).addMetric(PlaybackQAMetric.BANDWIDTH, bits).addMetric(PlaybackQAMetric.BANDWIDTH_STDEV, bits2).send();
        return ImmutableList.of(getHighestSupportedQuality(list, (int) getMaximumSupportedBitrate(bits, bits2)));
    }

    long getMaximumSupportedBitrate(long j, long j2) {
        int length = this.mAudioTransferSpeedVariationToSpeedShare.length;
        char c = 0;
        long jMin = Long.MAX_VALUE;
        int i = 0;
        while (i < length) {
            double[] dArr = this.mAudioTransferSpeedVariationToSpeedShare[i];
            i++;
            jMin = Math.min(jMin, (long) (dArr[1] * (j - (dArr[c] * j2))));
            c = 0;
        }
        return Math.min(this.mMaxBitrate, jMin);
    }

    AudioStreamAndQualityPair getHighestSupportedQuality(@Nonnull List<StreamIndex> list, int i) {
        StreamIndex streamIndex = null;
        int i2 = 0;
        for (StreamIndex streamIndex2 : list) {
            int bitrate = streamIndex2.getSortedQualityLevels(0)[0].getBitrate();
            if (bitrate > i2 && bitrate <= i) {
                streamIndex = streamIndex2;
                i2 = bitrate;
            }
        }
        if (streamIndex == null) {
            return AudioStreamSelectionUtils.getLowestBitratePair(list);
        }
        return new AudioStreamAndQualityPair(streamIndex, (AudioQualityLevel) streamIndex.getSortedQualityLevels(0)[0]);
    }
}

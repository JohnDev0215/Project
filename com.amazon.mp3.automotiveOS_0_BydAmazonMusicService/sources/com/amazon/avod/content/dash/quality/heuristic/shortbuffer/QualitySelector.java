package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsGraph;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsSharedInfo;
import com.amazon.avod.throughput.BandwidthStats;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class QualitySelector {
    private final HeuristicsGraph mBandwidthAdjustRatioWhenNotPlayingGraph;
    private final HeuristicsGraph mBandwidthBandwidthKbpsDrainedGraph;
    private final BandwidthStats mBandwidthMonitor;
    private final HeuristicsGraph mBufferDurationBandwidthDrainedGraph;
    private final HeuristicsCallbacks mCallbacks;
    private final ConfidenceEvaluator mConfidenceEvaluator;
    private final int mDefaultBandwidthBitsPerSecond;
    private final HeuristicsSharedInfo mHeuristicsSharedInfo;
    private final int mStreamIndex;

    QualitySelector(int i, BandwidthStats bandwidthStats, HeuristicsCallbacks heuristicsCallbacks, ConfidenceEvaluator confidenceEvaluator, HeuristicsSharedInfo heuristicsSharedInfo) {
        this(i, bandwidthStats, heuristicsCallbacks, confidenceEvaluator, ShortBufferHeuristicsConfig.getInstance(), heuristicsSharedInfo);
    }

    QualitySelector(int i, BandwidthStats bandwidthStats, HeuristicsCallbacks heuristicsCallbacks, ConfidenceEvaluator confidenceEvaluator, ShortBufferHeuristicsConfig shortBufferHeuristicsConfig, HeuristicsSharedInfo heuristicsSharedInfo) {
        this.mStreamIndex = i;
        this.mBandwidthMonitor = bandwidthStats;
        this.mCallbacks = heuristicsCallbacks;
        this.mConfidenceEvaluator = confidenceEvaluator;
        this.mHeuristicsSharedInfo = heuristicsSharedInfo;
        this.mBufferDurationBandwidthDrainedGraph = shortBufferHeuristicsConfig.getBufferDurationMillisToDrainedBandwidthRatioGraph();
        this.mBandwidthBandwidthKbpsDrainedGraph = shortBufferHeuristicsConfig.getBandwidthKbpsToDrainedBandwidthRatioGraph();
        this.mDefaultBandwidthBitsPerSecond = shortBufferHeuristicsConfig.getStartVideoBitrateBitsPerSecond();
        this.mBandwidthAdjustRatioWhenNotPlayingGraph = shortBufferHeuristicsConfig.getBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying();
    }

    int select(int i, int i2) {
        float bits = DataUnit.BYTES.toBits(this.mBandwidthMonitor.getAvgBandwidthBps());
        float kiloBits = DataUnit.BITS.toKiloBits(bits);
        long confidence = (long) (bits * this.mConfidenceEvaluator.getConfidence());
        double d = kiloBits;
        double dMin = Math.min(this.mBufferDurationBandwidthDrainedGraph.project(i), this.mBandwidthBandwidthKbpsDrainedGraph.project(d));
        if (this.mHeuristicsSharedInfo.getPlaybackState() != HeuristicsPlaybackState.Playing) {
            dMin = Math.max(dMin, this.mBandwidthAdjustRatioWhenNotPlayingGraph.project(d));
        }
        long j = (long) (confidence * dMin);
        if (j == 0) {
            j = this.mDefaultBandwidthBitsPerSecond;
        }
        DLog.logf("final bandwidth drained bps: %s", Long.valueOf(j));
        int iFragmentQualityCount = this.mCallbacks.fragmentQualityCount(this.mStreamIndex, i2);
        int iFindLowestBitrateIndex = findLowestBitrateIndex(i2);
        int iFragmentBitrate = this.mCallbacks.fragmentBitrate(this.mStreamIndex, i2, iFindLowestBitrateIndex);
        for (int i3 = 0; i3 < iFragmentQualityCount; i3++) {
            int iFragmentBitrate2 = this.mCallbacks.fragmentBitrate(this.mStreamIndex, i2, i3);
            if (iFragmentBitrate2 < j && iFragmentBitrate2 > iFragmentBitrate) {
                iFindLowestBitrateIndex = i3;
                iFragmentBitrate = iFragmentBitrate2;
            }
        }
        return iFindLowestBitrateIndex;
    }

    private int findLowestBitrateIndex(int i) {
        int iFragmentQualityCount = this.mCallbacks.fragmentQualityCount(this.mStreamIndex, i);
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        for (int i4 = 0; i4 < iFragmentQualityCount; i4++) {
            int iFragmentBitrate = this.mCallbacks.fragmentBitrate(this.mStreamIndex, i, i4);
            if (iFragmentBitrate < i2) {
                i3 = i4;
                i2 = iFragmentBitrate;
            }
        }
        return i3;
    }
}

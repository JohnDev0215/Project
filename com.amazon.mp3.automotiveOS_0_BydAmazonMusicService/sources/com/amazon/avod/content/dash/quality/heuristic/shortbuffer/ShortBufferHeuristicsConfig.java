package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsGraph;
import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.DLog;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class ShortBufferHeuristicsConfig extends MediaConfigBase {
    private final TimeConfigurationValue mAudioSubtitleDownloadTimeout;
    private final ConfigurationValue<String> mBandwidthKbpsToDrainedBandwidthRatioGraph;
    private final ConfigurationValue<String> mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying;
    private final ConfigurationValue<String> mBufferDurationMillisToDrainedBandwidthRatioGraph;
    private final TimeConfigurationValue mBufferThresholdToRestrictConcurrentDownloads;
    private final ConfigurationValue<Float> mConfidenceStep;
    private final ConfigurationValue<Float> mFragmentSizeOverhead;
    private final ConfigurationValue<Float> mMaxConfidence;
    private final TimeConfigurationValue mMaxVideoDownloadTimeout;
    private final ConfigurationValue<Float> mMinConfidence;
    private final TimeConfigurationValue mMinVideoDownloadTimeout;
    private final ConfigurationValue<Integer> mNumberOfLatencySamples;
    private final ConfigurationValue<ReviewDownloadProgressStrategy> mReviewDownloadProgressStrategy;
    private final ConfigurationValue<Float> mStartConfidence;
    private final ConfigurationValue<Integer> mStartVideoBitrateBitsPerSecond;
    private final ConfigurationValue<TimeoutStrategy> mTimeoutCalculationStrategy;
    private final TimeConfigurationValue mUnReviewTime;
    private final TimeConfigurationValue mVideoDownloadReservedTime;
    private final TimeConfigurationValue mVideoDownloadTimeoutWhenNotPlaying;
    private final ConfigurationValue<Integer> mAudioSubtitleConcurrentDownloadRequests = newIntConfigValue("playback_ShortBufferHeuristicsAudioSubtitleConcurrentDownloadRequests", 2);
    private final ConfigurationValue<Integer> mVideoConcurrentDownloadRequests = newIntConfigValue("playback_ShortBufferHeuristicsVideoConcurrentDownloadRequests", 1);
    private final ConfigurationValue<Integer> mNumberOfFutureFragmentsToCheck = newIntConfigValue("playback_ShortBufferHeuristicsNumberOfFutureFragmentsToCheck", 5);

    static ShortBufferHeuristicsConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static ShortBufferHeuristicsConfig INSTANCE = new ShortBufferHeuristicsConfig();

        private SingletonHolder() {
        }
    }

    ShortBufferHeuristicsConfig() {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(3000L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mAudioSubtitleDownloadTimeout = newTimeConfigurationValue("playback_ShortBufferHeuristicsAudioSubtitleTimeout", timeSpanFromMilliseconds, timeUnit);
        this.mMinVideoDownloadTimeout = newTimeConfigurationValue("playback_ShortBufferHeuristicsMinVideoDownloadTimeout_2", TimeSpan.fromMilliseconds(2500L), timeUnit);
        this.mMaxVideoDownloadTimeout = newTimeConfigurationValue("playback_ShortBufferHeuristicsMaxVideoDownloadTimeout", TimeSpan.fromMilliseconds(7000L), timeUnit);
        this.mVideoDownloadReservedTime = newTimeConfigurationValue("playback_ShortBufferHeuristicsVideoDownloadReservedTime", TimeSpan.fromMilliseconds(2000L), timeUnit);
        this.mMinConfidence = newFloatConfigValue("playback_ShortBufferHeuristicsMinConfidence", 0.2f);
        this.mMaxConfidence = newFloatConfigValue("playback_ShortBufferHeuristicsMaxConfidence", 0.8f);
        this.mStartConfidence = newFloatConfigValue("playback_ShortBufferHeuristicsStartConfidence", 0.4f);
        this.mConfidenceStep = newFloatConfigValue("playback_ShortBufferHeuristicsConfidenceStep", 0.025f);
        this.mFragmentSizeOverhead = newFloatConfigValue("playback_ShortBufferHeuristicsFragmentSizeOverhead", 1.3f);
        this.mBufferDurationMillisToDrainedBandwidthRatioGraph = newStringConfigValue("playback_ShortBufferHeuristicsBufferDurationMillisToDrainedBandwidthRatioGraph", "[ [2000, 0.1], [4000, 0.5], [5000, 1], [6000, 1], [6100, 1.3] ]");
        this.mBandwidthKbpsToDrainedBandwidthRatioGraph = newStringConfigValue("playback_ShortBufferHeuristicsBandwidthKbpsToDrainedBandwidthRatioGraph", "[ [7000, 1], [7001, 1.3] ]");
        this.mStartVideoBitrateBitsPerSecond = newIntConfigValue("playback_ShortBufferHeuristicsStartVideoBitrateBitsPerSecond", 2500000);
        this.mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying = newStringConfigValue("playback_ShortBufferHeuristicsMinBandwidthAdjustRatioWhenNotPlaying", "[ [1000, 0.3], [2000, 0.5], [3000, 0.75] ]");
        this.mUnReviewTime = newTimeConfigurationValue("playback_ShortBufferHeuristicsUnReviewTime", TimeSpan.fromMilliseconds(500L), timeUnit);
        this.mVideoDownloadTimeoutWhenNotPlaying = newTimeConfigurationValue("playback_ShortBufferHeuristicsVideoDownloadTimeoutWhenNotPlaying", TimeSpan.fromMilliseconds(8000L), timeUnit);
        this.mNumberOfLatencySamples = newIntConfigValue("playback_ShortBufferHeuristicsNumberOfLatencySamples", 10);
        this.mTimeoutCalculationStrategy = newEnumConfigValue("playback_ShortBufferHeuristicsTimeoutCalculationStrategy", TimeoutStrategy.BUFFERED_DURATION, TimeoutStrategy.class);
        this.mReviewDownloadProgressStrategy = newEnumConfigValue("playback_ShortBufferHeuristicsReviewDownloadProgressStrategy", ReviewDownloadProgressStrategy.EARLY_REVIEW_DOWNLOAD_PROGRESS, ReviewDownloadProgressStrategy.class);
        this.mBufferThresholdToRestrictConcurrentDownloads = newTimeConfigurationValue("playback_bufferThresholdToRestrictConcurrentDownloads", TimeSpan.fromMilliseconds(6000L), timeUnit);
    }

    int getAudioSubtitleConcurrentDownloadRequests() {
        return this.mAudioSubtitleConcurrentDownloadRequests.getValue().intValue();
    }

    int getVideoConcurrentDownloadRequests() {
        return this.mVideoConcurrentDownloadRequests.getValue().intValue();
    }

    int getNumberOfFutureFragmentsToCheck() {
        return this.mNumberOfFutureFragmentsToCheck.getValue().intValue();
    }

    TimeSpan getAudioSubtitleDownloadTimeout() {
        return this.mAudioSubtitleDownloadTimeout.getValue();
    }

    TimeSpan getMinVideoDownloadTimeout() {
        return this.mMinVideoDownloadTimeout.getValue();
    }

    TimeSpan getMaxVideoDownloadTimeout() {
        return this.mMaxVideoDownloadTimeout.getValue();
    }

    TimeSpan getVideoDownloadReservedTime() {
        return this.mVideoDownloadReservedTime.getValue();
    }

    float getMinConfidence() {
        return this.mMinConfidence.getValue().floatValue();
    }

    float getMaxConfidence() {
        return this.mMaxConfidence.getValue().floatValue();
    }

    float getStartConfidence() {
        return this.mStartConfidence.getValue().floatValue();
    }

    float getConfidenceStep() {
        return this.mConfidenceStep.getValue().floatValue();
    }

    float getFragmentSizeOverhead() {
        return this.mFragmentSizeOverhead.getValue().floatValue();
    }

    HeuristicsGraph getBufferDurationMillisToDrainedBandwidthRatioGraph() {
        return getHeuristicsGraph(this.mBufferDurationMillisToDrainedBandwidthRatioGraph.getValue(), this.mBufferDurationMillisToDrainedBandwidthRatioGraph.getDefaultValue());
    }

    HeuristicsGraph getBandwidthKbpsToDrainedBandwidthRatioGraph() {
        return getHeuristicsGraph(this.mBandwidthKbpsToDrainedBandwidthRatioGraph.getValue(), this.mBandwidthKbpsToDrainedBandwidthRatioGraph.getDefaultValue());
    }

    int getStartVideoBitrateBitsPerSecond() {
        return this.mStartVideoBitrateBitsPerSecond.getValue().intValue();
    }

    HeuristicsGraph getBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying() {
        return getHeuristicsGraph(this.mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying.getValue(), this.mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying.getDefaultValue());
    }

    TimeSpan getUnReviewTime() {
        return this.mUnReviewTime.getValue();
    }

    TimeSpan getVideoDownloadTimeoutWhenNotPlaying() {
        return this.mVideoDownloadTimeoutWhenNotPlaying.getValue();
    }

    int getNumberOfLatencySamples() {
        return this.mNumberOfLatencySamples.getValue().intValue();
    }

    protected HeuristicsGraph getHeuristicsGraph(String str, String str2) {
        try {
            return HeuristicsGraph.parseHeuristicsGraphFromString(str);
        } catch (Exception e) {
            DLog.warnf(e.getMessage());
            return HeuristicsGraph.parseHeuristicsGraphFromString(str2);
        }
    }

    public TimeoutStrategy getTimeoutCalculationStrategy() {
        return this.mTimeoutCalculationStrategy.getValue();
    }

    public ReviewDownloadProgressStrategy getReviewDownloadProgressStrategy() {
        return this.mReviewDownloadProgressStrategy.getValue();
    }

    public TimeSpan getBufferThresholdToRestrictConcurrentDownloads() {
        return this.mBufferThresholdToRestrictConcurrentDownloads.getValue();
    }
}

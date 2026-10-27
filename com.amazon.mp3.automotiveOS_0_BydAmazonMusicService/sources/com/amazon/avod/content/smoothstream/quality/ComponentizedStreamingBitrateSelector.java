package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.quality.timeout.FragmentTimeoutSelector;
import com.google.common.base.MoreObjects;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ComponentizedStreamingBitrateSelector implements StreamingBitrateSelector {
    private final float mBandwidthFactor;
    private long mBufferedTimeInNanoseconds;
    private int mLastAverageBandwidthBitsPerSecond;
    private final Object mMutex;
    private final DefaultQualityConfiguration mQualityDefaults;
    private long mSeekingBufferTresholdInNanoseconds;
    private QualityLevel mSelectedQualityLevel;
    private StreamingBitrateSelectionComponent mSelectionComponent;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;
    private StreamIndex mStream;
    private StreamingState mStreamState;
    private final StreamingBitrateSelectionState mStreamingBitrateSelectionState;
    private FragmentTimeoutSelector mTimeoutSelector;

    public ComponentizedStreamingBitrateSelector(DefaultQualityConfiguration defaultQualityConfiguration, SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this(defaultQualityConfiguration, smoothStreamingHeuristicConfig, smoothStreamingPlaybackConfig, new StreamingBitrateSelectionState());
    }

    ComponentizedStreamingBitrateSelector(DefaultQualityConfiguration defaultQualityConfiguration, SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, StreamingBitrateSelectionState streamingBitrateSelectionState) {
        this.mMutex = new Object();
        this.mBufferedTimeInNanoseconds = 0L;
        this.mBandwidthFactor = smoothStreamingHeuristicConfig.getLookupFactor();
        this.mQualityDefaults = defaultQualityConfiguration;
        this.mStreamingBitrateSelectionState = streamingBitrateSelectionState;
        this.mSmoothStreamingPlaybackConfig = smoothStreamingPlaybackConfig;
    }

    public void initialize(ContentSessionContext contentSessionContext, StreamIndex streamIndex, StreamingBitrateSelectionComponent streamingBitrateSelectionComponent, FragmentTimeoutSelector fragmentTimeoutSelector) {
        synchronized (this.mMutex) {
            this.mStream = streamIndex;
            this.mSelectionComponent = streamingBitrateSelectionComponent;
            this.mTimeoutSelector = fragmentTimeoutSelector;
            this.mSelectedQualityLevel = this.mQualityDefaults.getDefaultQualityLevel(contentSessionContext, streamIndex);
            this.mBufferedTimeInNanoseconds = 0L;
            StreamingState streamingState = StreamingState.STARTING;
            this.mStreamState = streamingState;
            this.mStreamingBitrateSelectionState.set(this.mStream, 0L, this.mLastAverageBandwidthBitsPerSecond, null, -1, 0, streamingState, FragmentType.DEFAULT);
            this.mSelectedQualityLevel = this.mSelectionComponent.selectQuality(this.mStreamingBitrateSelectionState, this.mSelectedQualityLevel);
            this.mLastAverageBandwidthBitsPerSecond = (int) ((r13.getBitrate() + 1) / this.mBandwidthFactor);
            this.mSeekingBufferTresholdInNanoseconds = this.mSmoothStreamingPlaybackConfig.getRequiredCacheSizeForResume(contentSessionContext.getHeuristics()).getTotalNanoSeconds();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector
    public FragmentDownloadParameters getDownloadParameters(SmoothStreamingURI smoothStreamingURI, int i) {
        FragmentDownloadParameters fragmentDownloadParameters;
        synchronized (this.mMutex) {
            QualityLevel qualityLevel = (QualityLevel) MoreObjects.firstNonNull(smoothStreamingURI.getQualityLevel(), this.mSelectedQualityLevel);
            this.mStreamingBitrateSelectionState.set(this.mStream, this.mBufferedTimeInNanoseconds, this.mLastAverageBandwidthBitsPerSecond, qualityLevel, smoothStreamingURI.getChunkIndex(), i, this.mStreamState, FragmentType.DEFAULT);
            QualityLevel qualityLevelSelectQuality = this.mSelectionComponent.selectQuality(this.mStreamingBitrateSelectionState, qualityLevel);
            this.mSelectedQualityLevel = qualityLevelSelectQuality;
            fragmentDownloadParameters = new FragmentDownloadParameters(smoothStreamingURI.getSmoothStreamingUriForQuality(qualityLevelSelectQuality), this.mTimeoutSelector.getFragmentTimeoutDurationInNanos(this.mStreamingBitrateSelectionState, this.mSelectedQualityLevel));
        }
        return fragmentDownloadParameters;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector
    public void updateBandwidth(int i, int i2) {
        synchronized (this.mMutex) {
            this.mLastAverageBandwidthBitsPerSecond = i2;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector
    public void updateBufferSizeInNanos(long j) {
        synchronized (this.mMutex) {
            this.mBufferedTimeInNanoseconds = j;
            if (j > this.mSeekingBufferTresholdInNanoseconds) {
                updateStreamState(StreamingState.NORMAL);
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector
    public void updateStreamState(StreamingState streamingState) {
        synchronized (this.mMutex) {
            this.mStreamState = streamingState;
        }
    }
}

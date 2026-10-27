package com.amazon.avod.content;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.QoeEvaluatorInterface;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.network.NetworkHistory;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.throughput.BandwidthStats;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentSessionContext {
    private boolean mAreConfigsCached = false;
    private final AudioVideoUrls mAudioVideoUrls;
    private final BandwidthStats mBandwidthStats;
    private final QualityLevelClamper mClamper;
    private final QualityLevelClamperFactory mClamperFactory;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final PlayableContent mContent;
    private final ContentStoreType mContentStoreType;
    private final ContentUrlSelector mContentUrlSelector;
    private final Heuristics mHeuristics;
    private final ByteBuffer mHeuristicsState;
    private final boolean mIsManifestRefreshEnabledForLive;
    private final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;
    private final Manifest mManifest;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final QoeEvaluatorInterface mQoeEvaluator;
    private TimeSpan mRequiredBufferDurationForPlaybackResume;
    private TimeSpan mRequiredBufferDurationForPlaybackStart;
    private TimeSpan mRequiredCacheDurationFromHeuristics;
    private final VideoResolution[] mResolutions;
    private final ContentSessionType mSessionType;
    private boolean mShouldUseHeuristicsDurationForCache;
    private final ContentSessionState mState;
    private final File mStoragePath;
    private final StreamSelections mStreamSelections;
    private final VideoSpecification mVideoSpec;

    public static ContentSessionContext create(@Nonnull PlayableContent playableContent, @Nonnull VideoSpecification videoSpecification, @Nonnull ContentSessionType contentSessionType, @Nonnull Manifest manifest, @Nonnull AudioVideoUrls audioVideoUrls, @Nonnull StreamSelections streamSelections, @Nonnull VideoResolution[] videoResolutionArr, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nullable Heuristics heuristics, @Nonnull Stopwatch stopwatch, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, @Nullable File file, @Nullable NetworkHistoryManager networkHistoryManager, @Nullable LiveWindowDuration liveWindowDuration, @Nonnull BandwidthStats bandwidthStats, @Nullable ContentUrlSelector contentUrlSelector, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull QualityLevelClamperFactory qualityLevelClamperFactory, @Nonnull QualityLevelClamper qualityLevelClamper, @Nonnull PlayerBindState playerBindState, @Nonnull QoeEvaluatorInterface qoeEvaluatorInterface, @Nonnull ContentStoreType contentStoreType) {
        long totalMilliseconds = manifest.getMinimumUpdatePeriod().getTotalMilliseconds();
        return new ContentSessionContext(playableContent, videoSpecification, contentSessionType, manifest, audioVideoUrls, streamSelections, videoResolutionArr, smoothStreamingPlaybackConfig, heuristics, stopwatch, liveStreamingPlaybackConfig, file, networkHistoryManager, liveWindowDuration, bandwidthStats, contentUrlSelector, playbackEventReporter, qualityLevelClamperFactory, qualityLevelClamper, playerBindState, qoeEvaluatorInterface, videoSpecification.isLiveStream() && totalMilliseconds > 0 && totalMilliseconds < ((long) smoothStreamingPlaybackConfig.getMinUpdatePeriodToEnableManifestRefreshThresholdMillis()), contentStoreType);
    }

    @VisibleForTesting
    public ContentSessionContext(@Nonnull PlayableContent playableContent, @Nonnull VideoSpecification videoSpecification, @Nonnull ContentSessionType contentSessionType, @Nonnull Manifest manifest, @Nonnull AudioVideoUrls audioVideoUrls, @Nonnull StreamSelections streamSelections, @Nonnull VideoResolution[] videoResolutionArr, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nullable Heuristics heuristics, @Nonnull Stopwatch stopwatch, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, @Nullable File file, @Nullable NetworkHistoryManager networkHistoryManager, @Nullable LiveWindowDuration liveWindowDuration, @Nonnull BandwidthStats bandwidthStats, @Nullable ContentUrlSelector contentUrlSelector, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull QualityLevelClamperFactory qualityLevelClamperFactory, @Nonnull QualityLevelClamper qualityLevelClamper, @Nonnull PlayerBindState playerBindState, @Nonnull QoeEvaluatorInterface qoeEvaluatorInterface, boolean z, @Nonnull ContentStoreType contentStoreType) {
        this.mBandwidthStats = (BandwidthStats) Preconditions.checkNotNull(bandwidthStats, "bandwidthStats");
        this.mContent = (PlayableContent) Preconditions.checkNotNull(playableContent, "content");
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "spec cannot be null.");
        this.mSessionType = (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "sessionType cannot be null.");
        this.mManifest = (Manifest) Preconditions.checkNotNull(manifest, "manifest cannot be null.");
        this.mAudioVideoUrls = (AudioVideoUrls) Preconditions.checkNotNull(audioVideoUrls, "audioVideoUrls cannot be null.");
        this.mStreamSelections = (StreamSelections) Preconditions.checkNotNull(streamSelections, "streamSelections cannot be null.");
        this.mResolutions = (VideoResolution[]) Preconditions.checkNotNull(videoResolutionArr, "videoResolutions cannot be null.");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig cannot be null.");
        Preconditions.checkNotNull(stopwatch, "manifestAcquiryStopwatch cannot be null.");
        this.mLiveStreamingPlaybackConfig = (LiveStreamingPlaybackConfig) Preconditions.checkNotNull(liveStreamingPlaybackConfig, "liveStreamingPlaybackConfig");
        ContentSessionState contentSessionState = new ContentSessionState(videoSpecification, manifest, stopwatch, liveStreamingPlaybackConfig, liveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(heuristics), liveWindowDuration, playerBindState);
        this.mState = contentSessionState;
        contentSessionState.setPlayPositionInNanos(videoSpecification.getStartTime().getTotalNanoSeconds());
        contentSessionState.setMediaQuality(videoSpecification.getMediaQuality());
        this.mHeuristics = heuristics;
        this.mStoragePath = file;
        if (networkHistoryManager != null) {
            NetworkHistory currentNetworkHistory = networkHistoryManager.getCurrentNetworkHistory();
            this.mHeuristicsState = currentNetworkHistory != null ? currentNetworkHistory.getHeuristicsState() : null;
        } else {
            this.mHeuristicsState = null;
        }
        this.mContentUrlSelector = contentUrlSelector;
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mClamperFactory = (QualityLevelClamperFactory) Preconditions.checkNotNull(qualityLevelClamperFactory, "clamperFactory");
        this.mClamper = (QualityLevelClamper) Preconditions.checkNotNull(qualityLevelClamper, "clamper");
        this.mQoeEvaluator = (QoeEvaluatorInterface) Preconditions.checkNotNull(qoeEvaluatorInterface, "qoeEvaluator");
        this.mIsManifestRefreshEnabledForLive = z;
        this.mContentStoreType = (ContentStoreType) Preconditions.checkNotNull(contentStoreType, "contentStoreType");
        cacheConfigs();
    }

    @Nonnull
    public SmoothStreamingPlaybackConfig getConfig() {
        return this.mConfig;
    }

    public PlayableContent getContent() {
        return this.mContent;
    }

    @Nonnull
    public Manifest getManifest() {
        return this.mManifest;
    }

    @Nonnull
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Deprecated
    public ContentUrl getContentUrl() {
        return this.mAudioVideoUrls.getContentUrls().get(0);
    }

    @Nonnull
    public ContentSessionType getSessionType() {
        return this.mSessionType;
    }

    @Nonnull
    public ContentSessionType getEffectiveSessionType() {
        ContentSessionType contentSessionType = this.mSessionType;
        if ((contentSessionType == ContentSessionType.LIVE_CACHE || contentSessionType == ContentSessionType.STREAMING_CONTINUOUS) && this.mState.isBoundToPlayer()) {
            return ContentSessionType.STREAMING;
        }
        return this.mSessionType;
    }

    public void notifyPlayerBindStateChanged(@Nonnull PlayerBindState playerBindState) {
        this.mState.notifyPlayerBindStateChanged(playerBindState);
    }

    public boolean isDownload() {
        return getSessionType().isDownload();
    }

    @Nonnull
    public StreamSelections getStreamSelections() {
        return this.mStreamSelections;
    }

    @Nullable
    public Heuristics getHeuristics() {
        return this.mHeuristics;
    }

    @Nullable
    public ByteBuffer getHeuristicsState() {
        return this.mHeuristicsState;
    }

    @Nonnull
    public ContentSessionState getState() {
        return this.mState;
    }

    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mVideoSpec;
    }

    @Nonnull
    public TimeSpan getDuration() {
        if (this.mVideoSpec.isLiveStream()) {
            return TimeSpan.MAX_VALUE;
        }
        if (this.mSessionType != ContentSessionType.CONTENT_CACHE) {
            return this.mManifest.getDuration();
        }
        return this.mShouldUseHeuristicsDurationForCache ? this.mRequiredCacheDurationFromHeuristics : this.mVideoSpec.getDuration();
    }

    private void cacheConfigs() {
        if (this.mAreConfigsCached) {
            return;
        }
        this.mRequiredBufferDurationForPlaybackResume = this.mConfig.getRequiredCacheSizeForResume(this.mHeuristics);
        this.mRequiredBufferDurationForPlaybackStart = this.mConfig.getRequiredCacheSizeForStreamingPlayback(this.mSessionType, this.mHeuristics, this.mAudioVideoUrls.getContentUrls().get(0).getAdsTrackingEndpoint() != null, this.mVideoSpec.isLiveStream());
        Heuristics heuristics = this.mHeuristics;
        if (heuristics != null && heuristics.getHeuristicsPlaybackConfig() != null) {
            this.mRequiredCacheDurationFromHeuristics = this.mConfig.getRequiredCacheSizeForWhisperCache(this.mHeuristics);
            this.mShouldUseHeuristicsDurationForCache = this.mConfig.shouldGetDurationToCacheFromHeuristics();
        }
        this.mAreConfigsCached = true;
    }

    public VideoResolution[] getAvailableResolutions() {
        return this.mResolutions;
    }

    @Nonnull
    public VideoResolution getMaxResolution() {
        Preconditions.checkArgument(this.mResolutions.length > 0, "No ABR streams in given video adaptation set");
        VideoResolution[] videoResolutionArr = this.mResolutions;
        return videoResolutionArr[videoResolutionArr.length - 1];
    }

    public String toString() {
        return String.format(Locale.US, "title: %s, sessionType: %s, sessionId: %s", getContent().getUniqueIdentifier(), getSessionType(), this.mAudioVideoUrls.getContentUrls().get(0).getSessionId());
    }

    @Nullable
    public File getStoragePath() {
        return this.mStoragePath;
    }

    @Nonnull
    public BandwidthStats getBandwidthStats() {
        return this.mBandwidthStats;
    }

    @Nullable
    public ContentUrlSelector getContentUrlSelector() {
        return this.mContentUrlSelector;
    }

    @Nonnull
    public PlaybackEventReporter getPlaybackEventReporter() {
        return this.mPlaybackEventReporter;
    }

    @Nonnull
    public QualityLevelClamperFactory getClamperFactory() {
        return this.mClamperFactory;
    }

    @Nonnull
    public QualityLevelClamper getClamper() {
        return this.mClamper;
    }

    @Nonnull
    public QoeEvaluatorInterface getQoeEvaluator() {
        return this.mQoeEvaluator;
    }

    public boolean isManifestRefreshEnabledForLive() {
        return this.mIsManifestRefreshEnabledForLive;
    }

    @Nonnull
    public TimeSpan getMaxFrontBufferSize() {
        return TimeSpan.fromSeconds(this.mConfig.getStreamingFutureBufferSizeSeconds(this.mHeuristics));
    }

    @Nonnull
    public TimeSpan getContentBufferLookAheadSize() {
        if (this.mVideoSpec.isLiveStream()) {
            return TimeSpan.fromSeconds(this.mLiveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(this.mHeuristics));
        }
        return TimeSpan.fromSeconds(this.mConfig.getStreamingFutureBufferSizeSeconds(this.mHeuristics));
    }

    @Nonnull
    public TimeSpan getMaxBackBufferSize() {
        return TimeSpan.fromSeconds(this.mConfig.getStreamingPastBufferSizeSeconds(this.mHeuristics));
    }

    @Nonnull
    public TimeSpan getRequiredDurationForStart() {
        return this.mRequiredBufferDurationForPlaybackStart;
    }

    @Nonnull
    public TimeSpan getRequiredDurationForResume() {
        return this.mRequiredBufferDurationForPlaybackResume;
    }

    public void recacheConfigs() {
        this.mAreConfigsCached = false;
        cacheConfigs();
    }

    @Nonnull
    public ContentStoreType getContentStoreType() {
        return this.mContentStoreType;
    }
}

package com.amazon.avod.qos;

import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.event.PlaybackMetadataAvailableEvent;
import com.amazon.avod.playback.event.VideoQualityChangeEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.internal.StandaloneEventReporter;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.eventbus.Subscribe;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PeakBitrateReachedEventReporter {
    private long mCurrentBitsPerSecond;
    private long mPeakBitrate;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private boolean mPlaying;
    private final QoSConfig mQoSConfig;
    private final StandaloneEventReporter mReporter;
    private final Stopwatch mStopwatch;
    private final String mStreamingSessionId;
    private long mTimeToBitrateMillis;

    public PeakBitrateReachedEventReporter(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull StandaloneEventReporter standaloneEventReporter, @Nonnull String str) {
        this(QoSConfig.INSTANCE, playbackEventTransport, standaloneEventReporter, str, Tickers.androidTicker());
    }

    PeakBitrateReachedEventReporter(@Nonnull QoSConfig qoSConfig, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull StandaloneEventReporter standaloneEventReporter, @Nonnull String str, @Nonnull Ticker ticker) {
        this.mCurrentBitsPerSecond = -1L;
        this.mPeakBitrate = -1L;
        this.mQoSConfig = (QoSConfig) Preconditions.checkNotNull(qoSConfig, "qoSconfig");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mReporter = (StandaloneEventReporter) Preconditions.checkNotNull(standaloneEventReporter, "standaloneEventReporter");
        this.mStreamingSessionId = (String) Preconditions.checkNotNull(str, "streamingSessionId");
        playbackEventTransport.registerEventBusHandler(this);
        this.mStopwatch = Stopwatch.createUnstarted(ticker);
    }

    @Subscribe
    public void playbackMetadataAvailable(PlaybackMetadataAvailableEvent playbackMetadataAvailableEvent) {
        VideoResolution[] availableResolutions = playbackMetadataAvailableEvent.getAvailableResolutions();
        VideoQualityLevel[] sortedVideoQualityLevels = playbackMetadataAvailableEvent.getSortedVideoQualityLevels();
        Preconditions.checkArgument(availableResolutions.length > 0, "There should be at least one resolution");
        Preconditions.checkArgument(sortedVideoQualityLevels.length > 0, "There should be at least one video quality level");
        VideoResolution videoResolution = availableResolutions[0];
        for (VideoResolution videoResolution2 : availableResolutions) {
            if (videoResolution.getResolutionBand().satisfiesOrExceeds(videoResolution2.getWidth(), videoResolution2.getHeight())) {
                videoResolution = videoResolution2;
            }
        }
        VideoQualityLevel videoQualityLevel = sortedVideoQualityLevels[0];
        int length = sortedVideoQualityLevels.length - 1;
        while (true) {
            if (length < 0) {
                break;
            }
            VideoQualityLevel videoQualityLevel2 = sortedVideoQualityLevels[length];
            if (videoResolution.getResolutionBand().doesNotExceed(videoQualityLevel2.getMaxWidth(), videoQualityLevel2.getMaxHeight())) {
                videoQualityLevel = videoQualityLevel2;
                break;
            }
            length--;
        }
        this.mPeakBitrate = videoQualityLevel.getBitrate();
        this.mReporter.reportMetric(QOSEventName.Information.toString(), new MetricsBuilder().eventSubtype("HighestPlayableBitrate").streamingSessionId(this.mStreamingSessionId).streamingBitRate(Long.valueOf(this.mPeakBitrate / 1000)));
        DLog.logf("Reported highest playable bitrate: %s", Long.valueOf(this.mPeakBitrate));
    }

    @Subscribe
    public void videoQualityChangeEvent(VideoQualityChangeEvent videoQualityChangeEvent) {
        if (this.mCurrentBitsPerSecond == -1) {
            if (!this.mStopwatch.isRunning()) {
                this.mStopwatch.start();
            }
            this.mTimeToBitrateMillis = 0L;
        } else {
            this.mTimeToBitrateMillis = this.mStopwatch.elapsed(TimeUnit.MILLISECONDS);
        }
        this.mCurrentBitsPerSecond = videoQualityChangeEvent.getBitrate();
    }

    @Subscribe
    public void statusEvent(StatusEvent statusEvent) {
        long j = this.mPeakBitrate;
        if (j != -1 && this.mPlaying && this.mCurrentBitsPerSecond == j && this.mStopwatch.elapsed(TimeUnit.MILLISECONDS) - this.mTimeToBitrateMillis > this.mQoSConfig.getTimeIntervalToEnsureStableBitrate().getTotalMilliseconds()) {
            DLog.logf("Reached highest bitrate %s bps in %s ms", Long.valueOf(this.mCurrentBitsPerSecond), Long.valueOf(this.mTimeToBitrateMillis));
            this.mReporter.reportMetric(QOSEventName.Information.toString(), new MetricsBuilder().eventSubtype("TimeToPeakBitrate").eventDuration(Long.valueOf(this.mTimeToBitrateMillis)).streamingBitRate(Long.valueOf(this.mCurrentBitsPerSecond)).streamingSessionId(this.mStreamingSessionId));
            this.mPlaybackEventTransport.unregisterEventBusHandler(this);
        }
    }

    @Subscribe
    public void startPlaybackEvent(PlaybackStartEvent playbackStartEvent) {
        this.mPlaying = true;
    }

    @Subscribe
    public void pauseEvent(PauseEvent pauseEvent) {
        this.mPlaying = false;
        if (this.mStopwatch.isRunning()) {
            this.mStopwatch.stop();
        }
    }

    @Subscribe
    public void resumeEvent(ResumeEvent resumeEvent) {
        this.mPlaying = true;
        if (this.mStopwatch.isRunning()) {
            return;
        }
        this.mStopwatch.start();
    }
}

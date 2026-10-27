package com.amazon.avod.qos;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.event.PlaybackMetadataAvailableEvent;
import com.amazon.avod.playback.event.VideoQualityChangeEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.internal.StandaloneEventReporter;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class ResolutionRampUpTimeReporter {
    private VideoResolution.ResolutionBand mCurrentResolutionBand;
    private PlaybackEventTransport mPlaybackEventTransport;
    private final StandaloneEventReporter mReporter;
    private final Stopwatch mStopwatch;
    private final String mStreamingSessionId;
    private final VideoResolution.ResolutionBand mTargetResolutionBand;
    private final long mTimeToEnsureQualityStability;
    private long mTimeToTargetResolutionMillis;

    public ResolutionRampUpTimeReporter(@Nonnull VideoResolution.ResolutionBand resolutionBand, @Nonnull StandaloneEventReporter standaloneEventReporter, @Nonnull String str) {
        this(Stopwatch.createUnstarted(Tickers.androidTicker()), resolutionBand, standaloneEventReporter, str, QoSConfig.INSTANCE.getTimeIntervalToEnsureStableBitrate().getTotalMilliseconds());
    }

    ResolutionRampUpTimeReporter(@Nonnull Stopwatch stopwatch, @Nonnull VideoResolution.ResolutionBand resolutionBand, @Nonnull StandaloneEventReporter standaloneEventReporter, @Nonnull String str, long j) {
        this.mCurrentResolutionBand = VideoResolution.ResolutionBand.UNKNOWN;
        this.mStopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.mTargetResolutionBand = (VideoResolution.ResolutionBand) Preconditions.checkNotNull(resolutionBand, "targetResolutionBand");
        this.mReporter = (StandaloneEventReporter) Preconditions.checkNotNull(standaloneEventReporter, "reporter");
        this.mStreamingSessionId = (String) Preconditions.checkNotNull(str, "streamingSessionId");
        this.mTimeToEnsureQualityStability = j;
    }

    @Subscribe
    public void playbackMetadataAvailable(PlaybackMetadataAvailableEvent playbackMetadataAvailableEvent) {
        if (this.mTargetResolutionBand.compareTo(playbackMetadataAvailableEvent.getAvailableResolutions()[playbackMetadataAvailableEvent.getAvailableResolutions().length - 1].getResolutionBand()) > 0) {
            release();
        }
    }

    @Subscribe
    public void statusEvent(StatusEvent statusEvent) {
        if (this.mCurrentResolutionBand.compareTo(this.mTargetResolutionBand) < 0 || this.mStopwatch.elapsed(TimeUnit.MILLISECONDS) - this.mTimeToTargetResolutionMillis <= this.mTimeToEnsureQualityStability) {
            return;
        }
        this.mReporter.reportMetric(QOSEventName.Information.toString(), new MetricsBuilder().eventSubtype(String.format(Locale.US, "%s%s", "TimeToResolution", this.mTargetResolutionBand.toString())).streamingSessionId(this.mStreamingSessionId).eventDuration(Long.valueOf(this.mTimeToTargetResolutionMillis)));
        DLog.logf("Reached resolution band %s in %s ms", this.mTargetResolutionBand.toString(), Long.valueOf(this.mTimeToTargetResolutionMillis));
        release();
    }

    @Subscribe
    public void videoQualityChangeEvent(VideoQualityChangeEvent videoQualityChangeEvent) {
        VideoResolution.ResolutionBand resolutionBand = videoQualityChangeEvent.getResolution().getResolutionBand();
        if (this.mCurrentResolutionBand.compareTo(this.mTargetResolutionBand) < 0 && resolutionBand.compareTo(this.mTargetResolutionBand) >= 0) {
            this.mTimeToTargetResolutionMillis = this.mStopwatch.elapsed(TimeUnit.MILLISECONDS);
        }
        this.mCurrentResolutionBand = resolutionBand;
    }

    @Subscribe
    public void startEvent(PlaybackStartEvent playbackStartEvent) {
        startStopwatch();
    }

    @Subscribe
    public void resumeEvent(ResumeEvent resumeEvent) {
        startStopwatch();
    }

    @Subscribe
    public void pauseEvent(PauseEvent pauseEvent) {
        stopStopwatch();
    }

    @Subscribe
    public void seekStartEvent(SeekStartEvent seekStartEvent) {
        stopStopwatch();
    }

    private void startStopwatch() {
        if (this.mStopwatch.isRunning()) {
            return;
        }
        this.mStopwatch.start();
    }

    private void stopStopwatch() {
        if (this.mStopwatch.isRunning()) {
            this.mStopwatch.stop();
        }
    }

    public void bind(PlaybackEventTransport playbackEventTransport) {
        playbackEventTransport.registerEventBusHandler(this);
        this.mPlaybackEventTransport = playbackEventTransport;
    }

    public void release() {
        PlaybackEventTransport playbackEventTransport = this.mPlaybackEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.unregisterEventBusHandler(this);
        }
    }
}

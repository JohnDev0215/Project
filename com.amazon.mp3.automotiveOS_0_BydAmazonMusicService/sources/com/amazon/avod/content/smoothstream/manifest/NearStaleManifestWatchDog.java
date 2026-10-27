package com.amazon.avod.content.smoothstream.manifest;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.RetriableServiceErrorEvent;
import com.amazon.avod.content.smoothstream.manifest.PeriodView;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifestValidator;
import com.amazon.avod.media.framework.util.GrowthCalculatorOnDurationBasedSlidingWindow;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.pmet.ContentTypePivot;
import com.amazon.avod.pmet.PlaybackPmetMetric;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NearStaleManifestWatchDog {
    private String mCdn;
    private PeriodView.Period mCurrentPeriod;
    private final ContentManagementEventBus mEventBus;
    private final GrowthCalculatorOnDurationBasedSlidingWindow mGrowthCalculatorOnDurationBasedSlidingWindow;
    private String mOrigin;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private PeriodView.Period mPreviousPeriod;
    private final boolean mShouldReportRetriableManifestErrorToAloysius;
    private final float mSlowManifestThreshold;
    private final Stopwatch mStopwatch;
    private final VideoSpecification mVideoSpecification;

    public NearStaleManifestWatchDog(PlaybackEventReporter playbackEventReporter, VideoSpecification videoSpecification, long j, @Nonnull ContentManagementEventBus contentManagementEventBus, boolean z) {
        this(new GrowthCalculatorOnDurationBasedSlidingWindow(j), Stopwatch.createUnstarted(Tickers.androidTicker()), playbackEventReporter, videoSpecification, PlaybackPmetMetricReporter.getInstance(), RefreshableManifestValidator.RefreshableManifestValidatorConfig.getInstance().getSlowManifestThresholdFactor(), contentManagementEventBus, z);
    }

    public NearStaleManifestWatchDog(@Nonnull GrowthCalculatorOnDurationBasedSlidingWindow growthCalculatorOnDurationBasedSlidingWindow, @Nonnull Stopwatch stopwatch, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, float f, @Nonnull ContentManagementEventBus contentManagementEventBus, boolean z) {
        this.mStopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.mGrowthCalculatorOnDurationBasedSlidingWindow = (GrowthCalculatorOnDurationBasedSlidingWindow) Preconditions.checkNotNull(growthCalculatorOnDurationBasedSlidingWindow, "growthCalculatorOnDurationBasedSlidingWindow");
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mSlowManifestThreshold = f;
        this.mEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mShouldReportRetriableManifestErrorToAloysius = z;
    }

    public void observeAndReport(@Nonnull PeriodView.Period period, @Nonnull PeriodView.Period period2, @Nullable String str, @Nullable String str2) {
        this.mCurrentPeriod = (PeriodView.Period) Preconditions.checkNotNull(period2, "currentLastPeriod");
        this.mPreviousPeriod = (PeriodView.Period) Preconditions.checkNotNull(period, "previousLastPeriod");
        this.mCdn = str;
        this.mOrigin = str2;
        long jElapsed = this.mStopwatch.elapsed(TimeUnit.MILLISECONDS);
        long lastFragmentTimeInPeriodInMillis = getLastFragmentTimeInPeriodInMillis(period2) - getLastFragmentTimeInPeriodInMillis(period);
        this.mGrowthCalculatorOnDurationBasedSlidingWindow.recordTimeLostAndGained(jElapsed, lastFragmentTimeInPeriodInMillis);
        report(lastFragmentTimeInPeriodInMillis);
        this.mStopwatch.reset();
        this.mStopwatch.start();
    }

    private void reportGrowth() {
        double currentGrowthFactor = this.mGrowthCalculatorOnDurationBasedSlidingWindow.getCurrentGrowthFactor();
        String reportableString = getReportableString();
        if (currentGrowthFactor <= 0.0d || currentGrowthFactor >= this.mSlowManifestThreshold) {
            DLog.logf(reportableString);
            return;
        }
        this.mPlaybackPmetMetricReporter.reportLivePlaybackSlowManifestEvent(this.mCdn, this.mOrigin, this.mVideoSpecification.getTitleId());
        PlaybackEventReporter playbackEventReporter = this.mPlaybackEventReporter;
        if (playbackEventReporter != null) {
            playbackEventReporter.reportError("SlowManifest", reportableString, null, this.mVideoSpecification.getTitleId(), false);
        }
        if (this.mShouldReportRetriableManifestErrorToAloysius) {
            this.mEventBus.postEvent(new RetriableServiceErrorEvent(new PlayableContent(this.mVideoSpecification.getTitleId(), this.mVideoSpecification.isTrailer()), ContentSessionType.STREAMING, new ContentException(ContentException.ContentError.LIVE_SLOW_MANIFEST, reportableString), null, null, null, "SlowManifest", reportableString));
        }
    }

    private void report(long j) {
        if (j > 0) {
            this.mPlaybackPmetMetricReporter.reportTimerMetric(PlaybackPmetMetric.FRAGMENT_GAINED, null, j / 2, ContentTypePivot.LIVE, this.mCdn, this.mOrigin, this.mVideoSpecification.getTitleId());
        }
        reportGrowth();
    }

    private long getLastFragmentTimeInPeriodInMillis(PeriodView.Period period) {
        return TimeUnit.NANOSECONDS.toMillis(((Long) period.getTimeRangeInNanos().upperEndpoint()).longValue());
    }

    private String getReportableString() {
        return String.format(Locale.US, "manifest-growth current_period_id : %s, previous_period_id : %s, growth_rate : %f, gained_time_millis: %d, elapsed_time_millis in the window: %d, accumulated_fragments_in_millis in the window : %d window_size : %d ", this.mCurrentPeriod.getId(), this.mPreviousPeriod.getId(), Double.valueOf(this.mGrowthCalculatorOnDurationBasedSlidingWindow.getCurrentGrowthFactor()), Long.valueOf(this.mGrowthCalculatorOnDurationBasedSlidingWindow.getNetGain()), Long.valueOf(this.mGrowthCalculatorOnDurationBasedSlidingWindow.getTotalElapsedTime()), Long.valueOf(this.mGrowthCalculatorOnDurationBasedSlidingWindow.getTotalGainedTime()), Long.valueOf(this.mGrowthCalculatorOnDurationBasedSlidingWindow.getMaxWindowDuration()));
    }
}

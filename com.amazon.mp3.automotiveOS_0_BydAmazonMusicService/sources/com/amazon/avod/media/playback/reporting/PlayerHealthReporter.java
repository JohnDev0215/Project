package com.amazon.avod.media.playback.reporting;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.event.ContentEventErrorBase;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.event.StaleManifestErrorEvent;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.Buffer;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.BufferType;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.pmet.ContentTypePivot;
import com.amazon.avod.pmet.PlaybackPmetMetric;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerHealthReporter {
    private final AloysiusDiagnosticsReporter mAloysiusDiagnosticsReporter;
    private final long mBufferFullnessReportIntervalMillis;
    private final ScheduledThreadPoolExecutor mBufferFullnessReportScheduler;
    private ScheduledFuture<?> mBufferFullnessScheduledTask;
    private ContentSessionState mContentSessionState;
    private final int mContinuousBufferFullnessReportCount;
    private final AtomicInteger mCounter;
    private final long mCriticalLowLevelOfBufferFullness;
    private final AtomicLong mCurrentFrontBufferCapacityInNanos;
    private final ContentManagementEventBus mEventBus;
    private final VideoPresentationEventReporter mEventReporter;
    private Heuristics mHeuristics;
    private final boolean mIsBufferFullnessReportEnabledForAcquisitionError;
    private final boolean mIsBufferFullnessReportEnabledForStaleManifest;
    private final AtomicBoolean mIsFrontBufferFullnessBreachedAndReported;
    private final boolean mIsPlayerHealthReportEnabled;
    private final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;
    private PlaybackEventReporter mPlaybackEventReporter;
    private PlaybackSession mPlaybackSession;
    private final PlayerHealthMetricsConfig mPlayerHealthMetricsConfig;
    private final PlaybackPmetMetricReporter mPmetMetricReporter;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;
    private VideoPlayer mVideoPlayer;
    private VideoSpecification mVideoSpec;

    public PlayerHealthReporter(@Nullable ContentManagementEventBus contentManagementEventBus, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters) {
        this(contentManagementEventBus, videoPresentationEventReporter, PlaybackPmetMetricReporter.getInstance(), playbackMediaEventReporters.getDiagnosticsReporter(), ScheduledExecutorBuilder.newBuilderFor("bufferFullnessReportScheduler", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build(), PlayerHealthMetricsConfig.getInstance(), LiveStreamingPlaybackConfig.getInstance(), SmoothStreamingPlaybackConfig.INSTANCE);
    }

    PlayerHealthReporter(@Nullable ContentManagementEventBus contentManagementEventBus, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter, @Nonnull ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, @Nonnull PlayerHealthMetricsConfig playerHealthMetricsConfig, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mCounter = new AtomicInteger();
        this.mCurrentFrontBufferCapacityInNanos = new AtomicLong();
        this.mIsFrontBufferFullnessBreachedAndReported = new AtomicBoolean();
        this.mEventBus = contentManagementEventBus;
        this.mEventReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "playbackEventReporter");
        this.mPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "pmetMetricReporter");
        this.mAloysiusDiagnosticsReporter = (AloysiusDiagnosticsReporter) Preconditions.checkNotNull(aloysiusDiagnosticsReporter, "aloysiusDiagnosticsReporter");
        this.mBufferFullnessReportScheduler = (ScheduledThreadPoolExecutor) Preconditions.checkNotNull(scheduledThreadPoolExecutor, "bufferFullnessReportScheduler");
        PlayerHealthMetricsConfig playerHealthMetricsConfig2 = (PlayerHealthMetricsConfig) Preconditions.checkNotNull(playerHealthMetricsConfig, "playerHealthMetricsConfig");
        this.mPlayerHealthMetricsConfig = playerHealthMetricsConfig2;
        this.mLiveStreamingPlaybackConfig = (LiveStreamingPlaybackConfig) Preconditions.checkNotNull(liveStreamingPlaybackConfig, "liveStreamingPlaybackConfig");
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        this.mIsPlayerHealthReportEnabled = playerHealthMetricsConfig2.isPlayerHealthReportEnabled();
        this.mIsBufferFullnessReportEnabledForAcquisitionError = playerHealthMetricsConfig2.isBufferFullnessReportEnabledForAcquisitionError();
        this.mIsBufferFullnessReportEnabledForStaleManifest = playerHealthMetricsConfig2.isBufferFullnessReportEnabledForStaleManifest();
        this.mBufferFullnessReportIntervalMillis = playerHealthMetricsConfig2.getBufferFullnessReportIntervalMillis();
        this.mContinuousBufferFullnessReportCount = playerHealthMetricsConfig2.getContinuousBufferFullnessReportCount();
        this.mCriticalLowLevelOfBufferFullness = playerHealthMetricsConfig2.getCriticalLowLevelOfBufferFullness();
    }

    public void initialize(@Nonnull VideoPlayer videoPlayer, @Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackSession playbackSession) {
        if (this.mIsPlayerHealthReportEnabled) {
            this.mVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "player");
            this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
            this.mPlaybackSession = (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession");
            this.mPlaybackEventReporter = this.mEventReporter.getPlaybackReporter();
            this.mBufferFullnessReportScheduler.setRemoveOnCancelPolicy(true);
            ContentManagementEventBus contentManagementEventBus = this.mEventBus;
            if (contentManagementEventBus != null) {
                contentManagementEventBus.registerEventBusHandler(this);
            }
        }
    }

    public void terminate() {
        if (this.mIsPlayerHealthReportEnabled) {
            ContentManagementEventBus contentManagementEventBus = this.mEventBus;
            if (contentManagementEventBus != null) {
                contentManagementEventBus.unregisterEventBusHandler(this);
            }
            ScheduledFuture<?> scheduledFuture = this.mBufferFullnessScheduledTask;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            this.mBufferFullnessReportScheduler.shutdown();
            this.mContentSessionState = null;
            this.mHeuristics = null;
            this.mCurrentFrontBufferCapacityInNanos.set(0L);
            this.mIsFrontBufferFullnessBreachedAndReported.set(false);
        }
    }

    @Subscribe
    public void handleRetriableContentEventError(@Nonnull RetriableContentEventError retriableContentEventError) {
        Preconditions.checkNotNull(retriableContentEventError, NotificationCompat.CATEGORY_EVENT);
        if (this.mIsBufferFullnessReportEnabledForAcquisitionError) {
            reportBufferFullnessStats(retriableContentEventError);
        }
    }

    @Subscribe
    public void handleStaleManifestErrorEvent(@Nonnull StaleManifestErrorEvent staleManifestErrorEvent) {
        Preconditions.checkNotNull(staleManifestErrorEvent, NotificationCompat.CATEGORY_EVENT);
        if (this.mIsBufferFullnessReportEnabledForStaleManifest) {
            reportBufferFullnessStats(staleManifestErrorEvent);
        }
    }

    private void reportBufferFullnessStats(@Nonnull final ContentEventErrorBase contentEventErrorBase) {
        if (this.mVideoPlayer == null || this.mVideoSpec == null || this.mPlaybackSession == null) {
            return;
        }
        ScheduledFuture<?> scheduledFuture = this.mBufferFullnessScheduledTask;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mBufferFullnessReportScheduler;
            Runnable runnable = new Runnable() { // from class: com.amazon.avod.media.playback.reporting.PlayerHealthReporter.1
                @Override // java.lang.Runnable
                public void run() {
                    PlayerStatistics playerStatistics;
                    if (PlayerHealthReporter.this.mCounter.incrementAndGet() > PlayerHealthReporter.this.mContinuousBufferFullnessReportCount) {
                        PlayerHealthReporter.this.notifyTaskComplete();
                    }
                    if (PlayerHealthReporter.this.mIsFrontBufferFullnessBreachedAndReported.get()) {
                        return;
                    }
                    try {
                        playerStatistics = PlayerHealthReporter.this.mVideoPlayer.getPlayerStatistics();
                    } catch (IllegalStateException | NullPointerException e) {
                        DLog.exceptionf(e, "Exception caught when fetching player statistics.", new Object[0]);
                        playerStatistics = PlayerStatistics.EMPTY_PLAYER_STATS;
                    }
                    for (Buffer buffer : playerStatistics.getFrontBuffers()) {
                        Long timeCapacity = buffer.getTimeCapacity();
                        if (timeCapacity != null && timeCapacity.longValue() > 0) {
                            long jMin = (long) Math.min(100.0d, (Math.max(0L, buffer.getTimeAvailable()) / PlayerHealthReporter.this.calculateBufferTimeCapacity(buffer)) * 100.0d);
                            if (jMin <= PlayerHealthReporter.this.mCriticalLowLevelOfBufferFullness) {
                                PlayerHealthReporter.this.reportBufferFullnessStats(PlaybackPmetMetric.BUFFER_FULLNESS_BREACH, buffer.getType().name(), contentEventErrorBase, jMin);
                                PlayerHealthReporter.this.mIsFrontBufferFullnessBreachedAndReported.set(true);
                            }
                        }
                    }
                }
            };
            long j = this.mBufferFullnessReportIntervalMillis;
            this.mBufferFullnessScheduledTask = scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, j, j, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportBufferFullnessStats(@Nonnull PlaybackPmetMetric playbackPmetMetric, @Nonnull String str, @Nonnull ContentEventErrorBase contentEventErrorBase, long j) {
        Preconditions.checkNotNull(playbackPmetMetric, "metric");
        Preconditions.checkNotNull(str, "bufferType");
        Preconditions.checkNotNull(contentEventErrorBase, NotificationCompat.CATEGORY_EVENT);
        String titleId = contentEventErrorBase.getTitleId();
        ContentUrl url = contentEventErrorBase.getUrl();
        String cdnName = url == null ? null : url.getCdnName();
        String origin = url != null ? url.getOrigin() : null;
        this.mPmetMetricReporter.reportBufferFullnessBreachThresholdEvent(ContentType.isLive(this.mVideoSpec.getContentType()) ? ContentTypePivot.LIVE : ContentTypePivot.VOD, cdnName, origin, titleId, BufferType.forValue(str));
        String str2 = String.format(Locale.US, "%s-%s: %d, cdn: %s, origin: %s", playbackPmetMetric.getMetricName(), str, Long.valueOf(j), cdnName, origin);
        PlaybackEventReporter playbackEventReporter = this.mPlaybackEventReporter;
        if (playbackEventReporter != null) {
            playbackEventReporter.reportMetric(QOSEventName.Information.name(), playbackPmetMetric.name(), null, str2, null);
        }
        this.mAloysiusDiagnosticsReporter.handleDiagnosticsEvent(new AloysiusDiagnosticEvent(playbackPmetMetric.name(), str2, AloysiusDiagnosticsState.Discrete));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long calculateBufferTimeCapacity(@Nonnull Buffer buffer) {
        ContentSessionState contentSessionState;
        updateContentSessionInfo();
        if (this.mVideoSpec.isLiveStream() && (contentSessionState = this.mContentSessionState) != null) {
            long mediaTimeWindowEndNanos = contentSessionState.getMediaTimeWindowEndNanos();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            long jMin = Math.min(timeUnit.toNanos(this.mSmoothStreamingPlaybackConfig.getStreamingFutureBufferSizeSeconds(this.mHeuristics)), ((mediaTimeWindowEndNanos + timeUnit.toNanos(this.mLiveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(this.mHeuristics))) - this.mContentSessionState.getPlayPositionInNanos()) - this.mContentSessionState.getLiveDynamicFrontMargin().getCurrentFrontMarginNanos());
            AtomicLong atomicLong = this.mCurrentFrontBufferCapacityInNanos;
            atomicLong.set(Math.max(atomicLong.get(), jMin));
            return TimeUnit.NANOSECONDS.toMillis(this.mCurrentFrontBufferCapacityInNanos.get());
        }
        if (buffer.getTimeCapacity() == null) {
            return 0L;
        }
        return buffer.getTimeCapacity().longValue();
    }

    private void updateContentSessionInfo() {
        ContentSession contentSession;
        if (this.mContentSessionState == null && (contentSession = this.mPlaybackSession.getContentSession()) != null) {
            ContentSessionContext context = contentSession.getContext();
            this.mContentSessionState = context.getState();
            this.mHeuristics = context.getHeuristics();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTaskComplete() {
        this.mCounter.set(0);
        this.mCurrentFrontBufferCapacityInNanos.set(0L);
        this.mIsFrontBufferFullnessBreachedAndReported.set(false);
        this.mBufferFullnessScheduledTask.cancel(true);
    }
}

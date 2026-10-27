package com.amazon.avod.playback.session;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.config.PlaybackZoomConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoPlayerBase;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.PlayerHealthReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.Buffer;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.media.playback.support.RendererCapabilities;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.playback.AutoEvalPlayerPerformanceEvaluator;
import com.amazon.avod.playback.HighFrameRatePlayerPerformanceEvaluator;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.OriginFailoverTestingManager;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.TimelineManager;
import com.amazon.avod.playback.config.DeviceRebootErrorConfig;
import com.amazon.avod.playback.config.EventAdapterConfig;
import com.amazon.avod.playback.config.PlayerRestartConfig;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.player.states.PlaybackState;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.amazon.avod.pmet.ContentTypePivot;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheEviction;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonVideoPlayer extends VideoPlayerBase implements PlaybackExperienceController {
    private final AutoEvalPlayerPerformanceEvaluator mAutoEvalPlayerPerformanceEvaluator;
    private volatile long mCachedAvailabilityStartTimeMillis;
    private Optional<Set<String>> mCachedAvailableAudioLanguages;
    private Optional<Set<String>> mCachedAvailableSubtitleLanguageCodes;
    private final AtomicReference<TimeSpan> mCachedContentDuration;
    private ContentSessionContext mCachedContentSessionContext;
    private Optional<String> mCachedCurrentAudioLanguage;
    private volatile long mCachedEncodeTimeMillis;
    private Optional<Boolean> mCachedIsStreamingSubtitlesSupported;
    private final AtomicReference<Optional<String>> mCachedLanguage;
    private final AtomicReference<TimeSpan> mCachedLastAvailableTime;
    private final AtomicReference<TimeSpan> mCachedLastSeekablePosition;
    private Optional<Object> mCachedLiveEventInfo;
    private volatile long mCachedLiveTimeWindowEndTimeMillis;
    private volatile long mCachedLiveTimeWindowStartTimeMillis;
    private final AtomicReference<TimeSpan> mCachedPosition;
    final EventAdapter mClientEventAdapter;
    private final DeviceConfiguration mDeviceConfiguration;
    private final DiagnosticsController mDiagnosticController;
    private final SmoothStreamingVideoPresentationEventReporter mEventReporter;
    private final HighFrameRatePlayerPerformanceEvaluator mHighFrameRatePlayerPerformanceEvaluator;
    private final AtomicBoolean mIsRestarting;
    private volatile boolean mIsSeeking;
    private volatile boolean mIsTerminated;
    private final LiveWindowDuration mLiveWindowDuration;
    private final PlaybackMediaEventReporters mMediaEventReporters;
    private final OriginFailoverTestingManager mOriginFailoverTestingManager;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final PlaybackSession mPlaybackSession;
    private final PlayerHealthReporter mPlayerHealthReporter;
    private final PlayerRestartConfig mPlayerRestartConfig;
    private final PowerManagementLockFactory mPowerLockFactory;
    private RendererCapabilities mRendererCapabilities;
    private Set<String> mRestartingContentSessionUUIDs;
    private final TimelineUtils mTimelineUtils;
    private final AVODContentUrlPolicyManager mUrlPolicyManager;
    private VideoRegion mVideoRegion;
    private final AtomicReference<VideoSpecification> mVideoSpecification;
    private final AtomicReference<PowerManagementLockFactory.PowerManagementLock> mWifiLock;
    private PlaybackZoomLevel mZoomLevel;

    private long clampClosed(long j, long j2, long j3) {
        if (j < j2) {
            j = j2;
        }
        return j > j3 ? j3 : j;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        return this;
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setTimelineManager(@Nonnull TimelineManager timelineManager) {
    }

    public AmazonVideoPlayer(@Nonnull VideoSpecification videoSpecification, @Nonnull AVODContentUrlPolicyManager aVODContentUrlPolicyManager, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull DiagnosticsController diagnosticsController, @Nonnull PlaybackSession playbackSession, @Nonnull PowerManagementLockFactory powerManagementLockFactory, @Nonnull SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter, @Nonnull TimelineUtils timelineUtils, @Nonnull DeviceRebootErrorConfig deviceRebootErrorConfig, @Nonnull LiveWindowDuration liveWindowDuration, @Nonnull PlaybackZoomConfig playbackZoomConfig, @Nonnull PlayerRestartConfig playerRestartConfig, @Nonnull DeviceConfiguration deviceConfiguration, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull HighFrameRatePlayerPerformanceEvaluator highFrameRatePlayerPerformanceEvaluator, @Nonnull AutoEvalPlayerPerformanceEvaluator autoEvalPlayerPerformanceEvaluator, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull PlayerHealthReporter playerHealthReporter) {
        this(videoSpecification, aVODContentUrlPolicyManager, baseDrmSystem, playbackEventTransport, playbackListenerProxy, diagnosticsController, playbackSession, powerManagementLockFactory, smoothStreamingVideoPresentationEventReporter, timelineUtils, deviceRebootErrorConfig, liveWindowDuration, playbackZoomConfig, playerRestartConfig, deviceConfiguration, playbackMediaEventReporters, highFrameRatePlayerPerformanceEvaluator, autoEvalPlayerPerformanceEvaluator, playbackPmetMetricReporter, new OriginFailoverTestingManager(), playerHealthReporter);
    }

    AmazonVideoPlayer(@Nonnull VideoSpecification videoSpecification, @Nonnull AVODContentUrlPolicyManager aVODContentUrlPolicyManager, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull DiagnosticsController diagnosticsController, @Nonnull PlaybackSession playbackSession, @Nonnull PowerManagementLockFactory powerManagementLockFactory, @Nonnull SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter, @Nonnull TimelineUtils timelineUtils, @Nonnull DeviceRebootErrorConfig deviceRebootErrorConfig, @Nonnull LiveWindowDuration liveWindowDuration, @Nonnull PlaybackZoomConfig playbackZoomConfig, @Nonnull PlayerRestartConfig playerRestartConfig, @Nonnull DeviceConfiguration deviceConfiguration, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull HighFrameRatePlayerPerformanceEvaluator highFrameRatePlayerPerformanceEvaluator, @Nonnull AutoEvalPlayerPerformanceEvaluator autoEvalPlayerPerformanceEvaluator, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull OriginFailoverTestingManager originFailoverTestingManager, @Nonnull PlayerHealthReporter playerHealthReporter) {
        super(playbackListenerProxy);
        this.mWifiLock = new AtomicReference<>(null);
        this.mIsSeeking = false;
        this.mIsTerminated = false;
        this.mCachedAvailabilityStartTimeMillis = 0L;
        this.mCachedLiveTimeWindowStartTimeMillis = 0L;
        this.mCachedLiveTimeWindowEndTimeMillis = 0L;
        this.mCachedEncodeTimeMillis = 0L;
        this.mRestartingContentSessionUUIDs = Sets.newConcurrentHashSet();
        this.mCachedIsStreamingSubtitlesSupported = Optional.absent();
        this.mIsRestarting = new AtomicBoolean(false);
        TimeSpan timeSpan = TimeSpan.ZERO;
        this.mCachedPosition = new AtomicReference<>(timeSpan);
        TimeSpan timeSpan2 = TimeSpan.MAX_VALUE;
        this.mCachedContentDuration = new AtomicReference<>(timeSpan2);
        this.mCachedLastAvailableTime = new AtomicReference<>(timeSpan2);
        this.mCachedLastSeekablePosition = new AtomicReference<>(timeSpan);
        this.mCachedLanguage = new AtomicReference<>(Optional.absent());
        this.mRendererCapabilities = null;
        this.mCachedAvailableSubtitleLanguageCodes = Optional.absent();
        this.mCachedAvailableAudioLanguages = Optional.absent();
        this.mCachedCurrentAudioLanguage = Optional.absent();
        this.mCachedContentSessionContext = null;
        this.mCachedLiveEventInfo = Optional.absent();
        this.mVideoSpecification = new AtomicReference<>(null);
        HighFrameRatePlayerPerformanceEvaluator highFrameRatePlayerPerformanceEvaluator2 = (HighFrameRatePlayerPerformanceEvaluator) Preconditions.checkNotNull(highFrameRatePlayerPerformanceEvaluator, "highFrameRatePlayerPerformanceEvaluator");
        this.mHighFrameRatePlayerPerformanceEvaluator = highFrameRatePlayerPerformanceEvaluator2;
        AutoEvalPlayerPerformanceEvaluator autoEvalPlayerPerformanceEvaluator2 = (AutoEvalPlayerPerformanceEvaluator) Preconditions.checkNotNull(autoEvalPlayerPerformanceEvaluator, "autoEvalPlayerPerformanceEvaluator");
        this.mAutoEvalPlayerPerformanceEvaluator = autoEvalPlayerPerformanceEvaluator2;
        this.mClientEventAdapter = new EventAdapter(baseDrmSystem, playbackListenerProxy, playbackEventTransport, this, deviceRebootErrorConfig, highFrameRatePlayerPerformanceEvaluator2, autoEvalPlayerPerformanceEvaluator2, EventAdapterConfig.getInstance());
        this.mUrlPolicyManager = (AVODContentUrlPolicyManager) Preconditions.checkNotNull(aVODContentUrlPolicyManager, "urlPolicyManager");
        this.mDiagnosticController = (DiagnosticsController) Preconditions.checkNotNull(diagnosticsController, "diagController");
        this.mPlaybackSession = (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mPowerLockFactory = (PowerManagementLockFactory) Preconditions.checkNotNull(powerManagementLockFactory, "powerLockFactory");
        this.mEventReporter = (SmoothStreamingVideoPresentationEventReporter) Preconditions.checkNotNull(smoothStreamingVideoPresentationEventReporter, "eventReporter");
        this.mTimelineUtils = (TimelineUtils) Preconditions.checkNotNull(timelineUtils, "timelineUtils");
        this.mLiveWindowDuration = (LiveWindowDuration) Preconditions.checkNotNull(liveWindowDuration, "liveWindowDuration");
        this.mZoomLevel = ((PlaybackZoomConfig) Preconditions.checkNotNull(playbackZoomConfig, "playbackZoomConfig")).getPlaybackZoomLevel();
        this.mPlayerRestartConfig = (PlayerRestartConfig) Preconditions.checkNotNull(playerRestartConfig, "playerRestartConfig");
        this.mDeviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration, "deviceConfiguration");
        this.mMediaEventReporters = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mOriginFailoverTestingManager = (OriginFailoverTestingManager) Preconditions.checkNotNull(originFailoverTestingManager, "originFailoverTestingManager");
        this.mPlayerHealthReporter = (PlayerHealthReporter) Preconditions.checkNotNull(playerHealthReporter, "playerHealthReporter");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(@Nonnull VideoSpecification videoSpecification, File file) {
        ImmutableList<String> immutableListOf;
        Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mMediaEventReporters.initialize(this, this, ContentType.isLive(videoSpecification.getContentType()));
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring PrepareAsync call after Terminate for %s", this);
            return;
        }
        DLog.logf("PrepareAsync %s", this);
        this.mHighFrameRatePlayerPerformanceEvaluator.initialize(this.mPlaybackSession.getContext());
        this.mAutoEvalPlayerPerformanceEvaluator.initialize(this.mPlaybackSession.getContext(), this.mPlaybackSession.getResources());
        if (videoSpecification.getAudioTrackIds().size() == 0) {
            immutableListOf = ImmutableList.of();
        } else {
            immutableListOf = ImmutableList.of(videoSpecification.getAudioTrackIds().get(0));
        }
        VideoSpecification videoSpecificationBuild = VideoSpecification.newBuilder(videoSpecification).setAudioTrackIds(immutableListOf).build();
        this.mVideoSpecification.set(videoSpecificationBuild);
        onVideoSpecificationChanged();
        this.mCachedPosition.set(videoSpecificationBuild.getStartTime());
        this.mPlayerHealthReporter.initialize(this, videoSpecification, this.mPlaybackSession);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring SetRenderingSettings call after Terminate for %s", this);
            return;
        }
        DLog.logf("SetRenderingSettings %s", this);
        notifyUserLaunchedPlayback();
        acquireWifiLockIfNecessary();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    public void notifyUserLaunchedPlayback() {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring NotifyUserLaunchedPlayback call after Terminate for %s", this);
        } else {
            DLog.logf("NotifyUserLaunchedPlayback %s", this);
            this.mUrlPolicyManager.notifyPlaybackLaunched();
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase
    public void terminatePlayback(boolean z, @Nullable MediaException mediaException) {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring duplicate Terminate call for %s", this);
            return;
        }
        DLog.logf("Terminate %s", this);
        this.mIsTerminated = true;
        releaseWifiLockIfNecessary();
        this.mPlaybackSession.terminate(z, mediaException);
        this.mOriginFailoverTestingManager.terminate();
        this.mPlayerHealthReporter.terminate();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    @Deprecated
    public int getBufferPercentage() {
        return (int) ((getLastAvailableTime().getTotalNanoSeconds() * 100) / Math.max(getContentDuration().getTotalNanoSeconds(), 1L));
    }

    private TimeSpan getLastAvailableTime() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (!isRestarting() && contentSession != null) {
            try {
                this.mCachedLastAvailableTime.set(contentSession.getLastAvailableTime());
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached lastAvailableTime.");
            }
        }
        return this.mCachedLastAvailableTime.get();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        long totalMilliseconds = getCurrentPositionAsTimeSpan().getTotalMilliseconds();
        if (totalMilliseconds < 0) {
            return 0L;
        }
        return totalMilliseconds;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        return convertMediaTimeToUTCMillis(getCurrentPosition());
    }

    public long convertMediaTimeToUTCMillis(long j) {
        checkUTCSupport();
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                this.mCachedAvailabilityStartTimeMillis = contentSession.getContext().getManifest().getAvailabilityStartTimeMillis();
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached availabilityStartTimeMillis.");
            }
        }
        return this.mCachedAvailabilityStartTimeMillis + j;
    }

    private long convertUTCMillisToMediaTime(long j) {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                this.mCachedAvailabilityStartTimeMillis = contentSession.getContext().getManifest().getAvailabilityStartTimeMillis();
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached availabilityStartTimeMillis.");
            }
        }
        return Math.max(j - this.mCachedAvailabilityStartTimeMillis, 0L);
    }

    @Nonnull
    private TimeSpan getCurrentPositionAsTimeSpan() {
        if (isRestarting() || this.mIsSeeking || this.mPlaybackSession.getPlaybackEngine() == null) {
            return this.mCachedPosition.get();
        }
        if (this.mPlaybackSession.getPlaybackEngine().getPlaybackState() != PlaybackState.Playing) {
            try {
                ContentSession contentSession = this.mPlaybackSession.getContentSession();
                if (contentSession != null) {
                    this.mCachedPosition.set(new TimeSpan(Math.max(0L, contentSession.getContext().getState().getPlayPositionInNanos())));
                }
            } catch (IllegalStateException e) {
                DLog.warnf("Cannot fetch play position from content session %s", e);
            }
            return this.mCachedPosition.get();
        }
        TimeSpan timeSpan = new TimeSpan(Math.max(0L, this.mPlaybackSession.getPlaybackEngine().getCurrentPositionInNanos()));
        if (timeSpan.lessThanEquals(TimeSpan.ZERO)) {
            return this.mCachedPosition.get();
        }
        this.mCachedPosition.set(timeSpan);
        return timeSpan;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        long totalMilliseconds = getContentDuration().getTotalMilliseconds();
        return totalMilliseconds < 0 ? TimeSpan.MAX_VALUE.getTotalMilliseconds() : totalMilliseconds;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nonnull
    public PlayerStatistics getPlayerStatistics() {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (isRestarting() || playbackEngine == null || this.mPlaybackSession.getContentSession() == null || !this.mPlaybackSession.getContentSession().isActive() || this.mPlaybackSession.getContentSession().getProtocol() == null) {
            return PlayerStatistics.EMPTY_PLAYER_STATS;
        }
        return new PlayerStatistics(playbackEngine.getRendererPerformanceData(), getContentBufferStats(Buffer.Position.Front), getContentBufferStats(Buffer.Position.Back));
    }

    @Nonnull
    private List<Buffer> getContentBufferStats(@Nonnull Buffer.Position position) {
        PlaybackSessionProtocol protocol = this.mPlaybackSession.getContentSession().getProtocol();
        long totalNanoSeconds = getCurrentPositionAsTimeSpan().getTotalNanoSeconds();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        try {
            StreamType[] streamTypeArrValues = StreamType.values();
            int length = streamTypeArrValues.length;
            for (int i = 0; i < length; i++) {
                StreamType streamType = streamTypeArrValues[i];
                StreamType streamType2 = StreamType.VIDEO;
                if (streamType == streamType2 || streamType == StreamType.AUDIO) {
                    Buffer.Builder builder2 = new Buffer.Builder();
                    builder2.type(streamType == streamType2 ? Buffer.Type.Video : Buffer.Type.Audio);
                    if (position == Buffer.Position.Front) {
                        builder2.timeAvailable(TimeUnit.NANOSECONDS.toMillis(Math.abs(protocol.getLastAvailablePositionInNanos(streamType) - totalNanoSeconds)));
                        builder2.timeCapacity(protocol.getMaxFrontBufferSizeInMillis());
                    } else {
                        builder2.timeAvailable(TimeUnit.NANOSECONDS.toMillis(Math.abs(totalNanoSeconds - protocol.getFirstAvailablePositionInNanos(streamType))));
                        builder2.timeCapacity(protocol.getMaxBackBufferSizeInMillis());
                    }
                    builder.add(builder2.build());
                }
            }
        } catch (IllegalStateException | IndexOutOfBoundsException e) {
            DLog.warnf("Could not read content buffer stats: %s", e);
        }
        return builder.build();
    }

    public DiagnosticsController getDiagnosticController() {
        return this.mDiagnosticController;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long j) {
        seekToManifestPosition(j, SeekAction.SeekCause.MANUAL);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToManifestPosition(long j, @Nonnull SeekAction.SeekCause seekCause) {
        TimeSpan timeSpanFromMilliseconds;
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring Seek call after Terminate for %s", this);
            return;
        }
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine == null) {
            DLog.warnf("Ignoring Seek call because video playback engine is null.");
            return;
        }
        if (seekCause == SeekAction.SeekCause.AD_SKIP || seekCause == SeekAction.SeekCause.AD_SEEK_OVER) {
            timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(j);
        } else {
            timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(clampClosed(j, 0L, getLastSeekablePosition().getTotalMilliseconds()));
        }
        this.mCachedPosition.set(timeSpanFromMilliseconds);
        this.mIsSeeking = true;
        DLog.logf("Seek %s to position: %s millis, adjusted to %s millis", this, Long.valueOf(j), Long.valueOf(timeSpanFromMilliseconds.getTotalMilliseconds()));
        playbackEngine.seekTo(timeSpanFromMilliseconds, seekCause);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long j) {
        checkUTCSupport();
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                this.mCachedContentSessionContext = contentSession.getContext();
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Trying to use cached contentSessionContext.");
            }
        }
        Preconditions.checkState(this.mCachedContentSessionContext != null, "must not call seekToUTC before player is active");
        seekTo(convertUTCMillisToMediaTime(j));
    }

    private void checkUTCSupport() {
        VideoSpecification videoSpecification = this.mVideoSpecification.get();
        Preconditions.checkState(videoSpecification != null, "UTC functions are not supported before prepareAsync");
        Preconditions.checkState(videoSpecification.isLiveStream(), "UTC functions are supported for live only");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring Pause call after Terminate for %s", this);
            return;
        }
        DLog.logf("Pause %s", this);
        releaseWifiLockIfNecessary();
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.pause();
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring Start call after Terminate for %s", this);
            return;
        }
        DLog.logf("Start %s", this);
        acquireWifiLockIfNecessary();
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.resume();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode) {
        this.mOriginFailoverTestingManager.manuallyTriggerFailover(failoverType, failoverMode, this.mPlaybackSession);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void restart(@javax.annotation.Nonnull final com.amazon.avod.playback.PlaybackRestartEvent r9) {
        /*
            r8 = this;
            java.lang.String r0 = "playbackRestartEvent"
            com.google.common.base.Preconditions.checkNotNull(r9, r0)
            int r0 = r9.getType()
            r1 = 1
            r2 = 0
            r4 = 3
            if (r0 == r1) goto L1c
            if (r0 == r4) goto L13
            goto L3e
        L13:
            long r0 = r9.getRestartTimeMillis()
            com.amazon.avod.media.TimeSpan r0 = com.amazon.avod.media.TimeSpan.fromMilliseconds(r0)
            goto L42
        L1c:
            long r0 = r9.getRestartTimeMillis()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L3e
            com.amazon.avod.playback.config.PlayerRestartConfig r0 = r8.mPlayerRestartConfig
            boolean r0 = r0.isUTCBasedRestartEnabled()
            if (r0 == 0) goto L35
            long r0 = r8.getCurrentPositionUTC()
            com.amazon.avod.media.TimeSpan r0 = com.amazon.avod.media.TimeSpan.fromMilliseconds(r0)
            goto L42
        L35:
            long r0 = r9.getRestartTimeMillis()
            com.amazon.avod.media.TimeSpan r0 = com.amazon.avod.media.TimeSpan.fromMilliseconds(r0)
            goto L42
        L3e:
            com.amazon.avod.media.TimeSpan r0 = r8.getLiveAwareCurrentPositionAsTimeSpan()
        L42:
            java.util.concurrent.atomic.AtomicReference<com.amazon.avod.media.playback.VideoSpecification> r1 = r8.mVideoSpecification
            java.lang.Object r1 = r1.get()
            com.amazon.avod.media.playback.VideoSpecification r1 = (com.amazon.avod.media.playback.VideoSpecification) r1
            com.amazon.avod.media.playback.VideoSpecification$Builder r1 = com.amazon.avod.media.playback.VideoSpecification.newBuilder(r1)
            com.amazon.avod.media.playback.VideoSpecification$Builder r0 = r1.setStartTime(r0)
            com.amazon.avod.media.playback.util.VideoRegion r1 = r8.mVideoRegion
            com.amazon.avod.media.playback.VideoSpecification$Builder r0 = r0.setVideoRegion(r1)
            com.amazon.avod.media.playback.VideoSpecification r0 = r0.build()
            int r1 = r9.getType()
            if (r1 != r4) goto L89
            long r4 = r8.getCurrentPosition()
            long r6 = r9.getRestartTimeMillis()
            long r6 = r6 - r4
            long r1 = java.lang.Math.max(r2, r6)
            com.amazon.avod.playback.config.LiveKeyRotationConfig r3 = com.amazon.avod.playback.config.LiveKeyRotationConfig.getInstance()
            long r3 = r3.getMaxLiveKeyRotationRestartDelayMillis()
            long r1 = java.lang.Math.min(r1, r3)
            java.util.Timer r3 = new java.util.Timer
            r3.<init>()
            com.amazon.avod.playback.session.AmazonVideoPlayer$1 r4 = new com.amazon.avod.playback.session.AmazonVideoPlayer$1
            r4.<init>()
            r3.schedule(r4, r1)
            goto L8c
        L89:
            r8.restart(r0, r9)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.session.AmazonVideoPlayer.restart(com.amazon.avod.playback.PlaybackRestartEvent):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restart(@Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackRestartEvent playbackRestartEvent) {
        VideoSpecification videoSpecification2 = this.mVideoSpecification.get();
        if (playbackRestartEvent.getType() == 1 && videoSpecification2 != null) {
            this.mPlaybackSession.getCacheManager().evict(videoSpecification2, WhisperCacheMetrics$CacheEviction.LIVE_ORIGIN_FAILOVER, null);
        }
        if (!this.mIsRestarting.getAndSet(true) || (playbackRestartEvent.shouldOverridePreviousRestart() && !this.mRestartingContentSessionUUIDs.contains(playbackRestartEvent.getContentSessionUUID()))) {
            this.mPlaybackSession.restart(videoSpecification, playbackRestartEvent);
            this.mVideoSpecification.set(videoSpecification);
            onVideoSpecificationChanged();
            if (playbackRestartEvent.getContentSessionUUID() != null) {
                this.mRestartingContentSessionUUIDs.add(playbackRestartEvent.getContentSessionUUID());
            }
            reportRestartPMET(playbackRestartEvent);
            reportRestartQos(playbackRestartEvent);
        }
    }

    private void reportRestartPMET(@Nonnull PlaybackRestartEvent playbackRestartEvent) {
        if (this.mPlayerRestartConfig.isPlayerRestartPmetReportingPivotsEnabled()) {
            VideoSpecification videoSpecification = this.mVideoSpecification.get();
            if (videoSpecification != null) {
                this.mPlaybackPmetMetricReporter.reportPlayerRestartEvent(playbackRestartEvent.toReportableString(), videoSpecification.isLiveStream() ? ContentTypePivot.LIVE : ContentTypePivot.VOD, getCurrentCdn(), getCurrentOrigin(), videoSpecification.getTitleId());
                return;
            }
            return;
        }
        try {
            DLog.warnf("PMET reporting: counterName %s, Type [%s, %s]", "PlayerRestart", "Counter", playbackRestartEvent.getTypeString());
            this.mEventReporter.getPMETEventReporter().reportCounterMetric(new SimpleCounterMetric("PlayerRestart", (ImmutableList<String>) ImmutableList.of("Counter", playbackRestartEvent.getTypeString())));
        } catch (Exception e) {
            DLog.exceptionf(e, "failed to report restart to PMET", new Object[0]);
        }
    }

    private void reportRestartQos(@Nonnull PlaybackRestartEvent playbackRestartEvent) {
        try {
            this.mEventReporter.getPlaybackReporter().reportMetric(QOSEventName.PlaybackSession.toString(), playbackRestartEvent.getTypeString(), null, String.format(Locale.US, "timeBeforeRestartMillis: %s restartTimeMillis: %s, note: %s", Long.valueOf(playbackRestartEvent.getTimeBeforeRestartMillis()), Long.valueOf(playbackRestartEvent.getRestartTimeMillis()), playbackRestartEvent.getMessage()), playbackRestartEvent.getLanguageCode());
        } catch (Exception e) {
            DLog.exceptionf(e, "failed to report restart to QoS", new Object[0]);
        }
    }

    @Nonnull
    private TimeSpan getLiveAwareCurrentPositionAsTimeSpan() {
        if (this.mPlayerRestartConfig.isUTCBasedRestartEnabled() && this.mVideoSpecification.get() != null && this.mVideoSpecification.get().isLiveStream()) {
            return TimeSpan.fromMilliseconds(getCurrentPositionUTC());
        }
        return getCurrentPositionAsTimeSpan();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Optional<String> getAudioTrackId() {
        ImmutableList<String> audioTrackIds = this.mVideoSpecification.get().getAudioTrackIds();
        if (audioTrackIds.size() == 0) {
            return Optional.absent();
        }
        return Optional.of(audioTrackIds.get(0));
    }

    @Nonnull
    public Optional<String> getLanguage() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (!isRestarting() && contentSession != null) {
            try {
                this.mCachedLanguage.set(Optional.fromNullable(contentSession.getContext().getStreamSelections().getAudioStream().getLanguage()));
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached language.");
            }
        }
        return this.mCachedLanguage.get();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void overrideVideoQuality(@Nullable QualityLevel qualityLevel) {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                contentSession.setVideoQualityOverride(qualityLevel);
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Skip overrideVideoQuality.");
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setRestrictPlaybackToBufferedContent(boolean z) {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                contentSession.setPlaybackRestrictedToBufferedContent(z);
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Skip setRestrictPlaybackToBufferedContent.");
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setZoomLevel(PlaybackZoomLevel playbackZoomLevel) {
        DLog.logf("Set zoom level %s %s", playbackZoomLevel, this);
        try {
            PlaybackZoomLevel playbackZoomLevel2 = PlaybackZoomLevel.INVISIBLE;
            boolean z = (playbackZoomLevel == playbackZoomLevel2 || this.mZoomLevel == playbackZoomLevel2) ? false : true;
            VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
            if (playbackEngine != null) {
                playbackEngine.setZoomLevel(playbackZoomLevel, z);
                this.mZoomLevel = playbackZoomLevel;
            }
        } catch (PlaybackException e) {
            this.mClientEventAdapter.handleErrorEvent(new FatalPlaybackErrorEvent(TimeSpan.now(), e, getConsumptionId()));
        }
    }

    void notifyStartedHelper() {
        notifyStarted(this.mPlaybackSession.getDataSource());
    }

    void notifyErrorHelper(MediaErrorCode mediaErrorCode) {
        notifyError(mediaErrorCode);
    }

    void notifyCompletedHelper() {
        notifyCompleted();
    }

    @Nonnull
    public SmoothStreamingVideoPresentationEventReporter getEventReporter() {
        return this.mEventReporter;
    }

    private TimeSpan getContentDuration() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (!isRestarting() && contentSession != null && contentSession.isActive()) {
            try {
                this.mCachedContentDuration.set(contentSession.getContext().getDuration());
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached contentDuration.");
            }
        }
        return this.mCachedContentDuration.get();
    }

    boolean isRestarting() {
        return this.mIsRestarting.get();
    }

    TimeSpan getLastSeekablePosition() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (!isRestarting() && contentSession != null && contentSession.isActive()) {
            try {
                this.mCachedLastSeekablePosition.set(this.mTimelineUtils.getLastSeekablePosition(contentSession));
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached lastSeekablePosition.");
            }
        }
        return this.mCachedLastSeekablePosition.get();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public boolean isPlaybackSpeedAdjustmentSupported() {
        return getRendererCapabilities().isPlaybackSpeedAdjustmentSupported();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setPlaybackSpeed(float f) {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.setPlaybackSpeed(f);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public boolean isVideoTrackRecreationSupported() {
        return getRendererCapabilities().isBackgroundAudioPlaybackSupported();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void changeOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        Preconditions2.checkNotMainThread();
        this.mPlaybackSession.getContext().setOutputSettings(videoRenderingSettings);
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.changeOutputDisplay(videoRenderingSettings);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void scaleVolume(float f) {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.setVolume(f);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public boolean isStreamingSubtitlesSupported() {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            this.mCachedIsStreamingSubtitlesSupported = Optional.of(Boolean.valueOf(playbackEngine.getSubtitlesEngine().isStreamingSubtitlesSupported()));
        }
        Preconditions.checkState(this.mCachedIsStreamingSubtitlesSupported.isPresent(), "must not call isStreamingSubtitlesSupported before player is active");
        return this.mCachedIsStreamingSubtitlesSupported.get().booleanValue();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public Set<String> getAvailableSubtitleLanguageCodes() {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            this.mCachedAvailableSubtitleLanguageCodes = Optional.of(playbackEngine.getSubtitlesEngine().getAvailableSubtitleLanguageCodes());
        }
        Preconditions.checkState(this.mCachedAvailableSubtitleLanguageCodes.isPresent(), "must not call getAvailableSubtitleLanguageCodes before player is active");
        return this.mCachedAvailableSubtitleLanguageCodes.get();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void startSubtitleDownload(String str) {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.getSubtitlesEngine().startDownload();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void stopSubtitleDownload() {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.getSubtitlesEngine().stopDownload();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void registerSubtitleListener(@Nonnull SubtitlesListener subtitlesListener) {
        Preconditions.checkNotNull(subtitlesListener, "subtitlesListener");
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.getSubtitlesEngine().registerListener(subtitlesListener);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void deregisterSubtitleListener(@Nonnull SubtitlesListener subtitlesListener) {
        Preconditions.checkNotNull(subtitlesListener, "subtitlesListener");
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.getSubtitlesEngine().deregisterListener(subtitlesListener);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void onSubtitlesCallbackComplete() {
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            playbackEngine.getSubtitlesEngine().onCallbackComplete();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Set<String> getAvailableAudioLanguages() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                this.mCachedAvailableAudioLanguages = Optional.of(contentSession.getAvailableAudioLanguages());
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Trying to use cached availableAudioLanguages.");
            }
        }
        Preconditions.checkState(this.mCachedAvailableAudioLanguages.isPresent(), "Cannot call getAvailableAudioLanguages before session is created");
        return this.mCachedAvailableAudioLanguages.get();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Optional<String> getCurrentAudioLanguage() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                String currentAudioLanguage = contentSession.getCurrentAudioLanguage();
                this.mCachedCurrentAudioLanguage = Strings.isNullOrEmpty(currentAudioLanguage) ? Optional.absent() : Optional.of(currentAudioLanguage);
            } catch (IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached currentAudioLanguage.");
            }
        }
        return this.mCachedCurrentAudioLanguage;
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public long getLiveTimeWindowStartMillis() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                this.mCachedLiveTimeWindowStartTimeMillis = contentSession.getTimeWindowStartMillis();
            } catch (ContentException | IllegalStateException | IndexOutOfBoundsException unused) {
                DLog.logf("The content session is under (re)construction. Using cached liveTimeWindowStartTimeMillis.");
            }
        }
        return this.mCachedLiveTimeWindowStartTimeMillis;
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public long getLiveTimeWindowEndMillis() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                this.mCachedLiveTimeWindowEndTimeMillis = contentSession.getTimeWindowEndMillis();
            } catch (ContentException | IllegalStateException unused) {
                DLog.logf("The content session is under (re)construction. Using cached liveTimeWindowEndTimeMillis.");
            }
        }
        return Math.max(getLiveTimeWindowStartMillis(), this.mCachedLiveTimeWindowEndTimeMillis);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() throws IllegalPlayerStateException {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null) {
            try {
                if (!isRestarting()) {
                    return contentSession.getAudioTrackMetadataList();
                }
            } catch (NullPointerException e) {
                throw new IllegalPlayerStateException(e.getMessage());
            }
        }
        throw new IllegalPlayerStateException("The content session is under (re)construction.");
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public PlaybackMediaEventReporters getAloysiusReporter() {
        return this.mMediaEventReporters;
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public VideoConfig getVideoConfig() throws IllegalPlayerStateException {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession != null && contentSession.isActive() && !isRestarting()) {
            return contentSession.getProtocol().getVideoConfig();
        }
        throw new IllegalPlayerStateException("The content session is under (re)construction.");
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setVideoPlayerInBackground(boolean z) {
        if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
            return;
        }
        this.mMediaEventReporters.setCyclicReportingPaused(z);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setFailoverZigZagSpeed(int i) {
        this.mOriginFailoverTestingManager.setFailoverZigZagSpeed(i, this.mPlaybackSession);
    }

    @Nullable
    public String getCurrentCdn() {
        if (this.mPlaybackSession.getContentSession() == null || this.mPlaybackSession.getContentSession().getContentUrlSelector() == null) {
            return null;
        }
        return this.mPlaybackSession.getContentSession().getContentUrlSelector().getCurrentContentUrl().getCdnName();
    }

    @Nullable
    public String getCurrentOrigin() {
        if (this.mPlaybackSession.getContentSession() == null || this.mPlaybackSession.getContentSession().getContentUrlSelector() == null) {
            return null;
        }
        return this.mPlaybackSession.getContentSession().getContentUrlSelector().getCurrentContentUrl().getOrigin();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public PlaybackZoomLevel getZoomLevel() {
        return this.mZoomLevel;
    }

    @Nonnull
    public RendererCapabilities getRendererCapabilities() {
        RendererCapabilities rendererCapabilities = this.mRendererCapabilities;
        if (rendererCapabilities != null) {
            return rendererCapabilities;
        }
        VideoPlaybackEngine playbackEngine = this.mPlaybackSession.getPlaybackEngine();
        if (playbackEngine != null) {
            RendererCapabilities rendererCapabilities2 = playbackEngine.getRendererCapabilities();
            this.mRendererCapabilities = rendererCapabilities2;
            return rendererCapabilities2;
        }
        return RendererCapabilities.NONE;
    }

    public String toString() {
        VideoSpecification videoSpecification = this.mVideoSpecification.get();
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        if (videoSpecification == null) {
            videoSpecification = "(Unspecified content)";
        }
        objArr[0] = videoSpecification;
        return String.format(locale, "[SmoothStreamingVideoPlayer for %s]", objArr);
    }

    void onPrepared() {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring OnPrepared call after Terminate for %s", this);
            return;
        }
        this.mIsSeeking = false;
        if (!this.mPlaybackSession.getContentSession().isFullyDownloadedFromNanos(0L)) {
            this.mWifiLock.set(this.mPowerLockFactory.newWifiLock("VideoPlayerWifiLock"));
        }
        getLanguage();
    }

    private void acquireWifiLockIfNecessary() {
        PowerManagementLockFactory.PowerManagementLock powerManagementLock = this.mWifiLock.get();
        if (powerManagementLock != null) {
            powerManagementLock.acquire();
        }
    }

    private void releaseWifiLockIfNecessary() {
        PowerManagementLockFactory.PowerManagementLock powerManagementLock = this.mWifiLock.get();
        if (powerManagementLock != null) {
            powerManagementLock.release();
        }
    }

    void onSeekEnd() {
        this.mIsSeeking = false;
    }

    void onRestartBegin() {
        this.mIsRestarting.set(true);
    }

    void onRestartEnd(boolean z) {
        if (z) {
            this.mIsRestarting.set(false);
            getEventProxy().onRestartSuccess();
        }
    }

    void onContentBufferingError(@Nonnull MediaException mediaException, @Nonnull MediaInternalErrorCode mediaInternalErrorCode) {
        Preconditions.checkNotNull(mediaException, "initialException");
        Preconditions.checkNotNull(mediaInternalErrorCode, "errorCode");
        PVDownloadPlayerShim download = this.mPlaybackSession.getDownload();
        if (download != null && download.getState() == UserDownloadState.DOWNLOADED) {
            this.mPlaybackEventTransport.postEvent(new FatalPlaybackErrorEvent(getCurrentPositionAsTimeSpan(), new ContentException(ContentException.ContentError.FILE_MISSING, "Download info indicated fully downloaded content, but not all content is present on disk (encountered buffering before end of content)."), mediaException.getMessage(), getConsumptionId()));
        } else if (mediaInternalErrorCode == PlaybackException.PlaybackError.CONTENT_BUFFERING_NO_DATA_CONNECTION) {
            notifyErrorHelper(StandardErrorCode.DATA_CONNECTION_UNAVAILABLE);
        } else if (download != null) {
            notifyErrorHelper(StandardErrorCode.DOWNLOAD_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT);
        } else {
            notifyErrorHelper(StandardErrorCode.STREAMING_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT);
        }
    }

    private void onVideoSpecificationChanged() {
        VideoSpecification videoSpecification = this.mVideoSpecification.get();
        if (videoSpecification != null) {
            BetaConfigProvider.getInstance().provideBetaConfig().updateBetaStatusBasedOnCurrentlyPlayingVCID(videoSpecification.getTitleId());
        }
    }

    @Nullable
    private String getConsumptionId() {
        ContentSession contentSession = this.mPlaybackSession.getContentSession();
        if (contentSession == null) {
            return null;
        }
        return contentSession.getConsumptionId();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    @Nullable
    public ContentSession getContentSession() {
        return this.mPlaybackSession.getContentSession();
    }
}

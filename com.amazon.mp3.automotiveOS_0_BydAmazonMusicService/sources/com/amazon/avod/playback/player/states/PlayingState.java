package com.amazon.avod.playback.player.states;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.PlayerBindState;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.InvalidRendererTimestampException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.config.PlayerConfigurationDiagnostics;
import com.amazon.avod.playback.event.playback.BitrateChangeEvent;
import com.amazon.avod.playback.event.playback.PlaybackCompletedEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.playback.player.LowFpsChecker;
import com.amazon.avod.playback.player.PlaybackLiveTimeWindowTracker;
import com.amazon.avod.playback.player.PlaybackPerformanceReporter;
import com.amazon.avod.playback.player.RendererActivityWatchdog;
import com.amazon.avod.playback.player.RendererSampleReceiver;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.BufferAction;
import com.amazon.avod.playback.player.actions.PauseAction;
import com.amazon.avod.playback.player.actions.PlayAction;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.pmet.PmetLifecycleProfilerReporter;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.model.internal.AudioAdaptationSetSwitchContext;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.qos.reporter.QosLifecycleProfilerReporter;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class PlayingState extends PlaybackEngineState {
    private final LowFpsChecker mFpsChecker;
    private final Map<SampleType, Integer> mLastBitrates;
    private final PlaybackLiveTimeWindowTracker mPlaybackLiveTimeWindowTracker;
    private final VideoRenderer mRenderer;
    private final RendererActivityWatchdog mRendererActivityWatchdog;
    private boolean mSentPlaybackCompletedEvent;
    private boolean mSentPlaybackStartEvent;
    private boolean mSentResumeEvent;
    private final boolean mShouldReportMetaData;
    private final int mSleepTimeMillis;
    private final long mStatusEventIntervalInNanoseconds;
    private long mTimeOfLastStatusEventInNanoseconds;
    private final long mTimeToRenderedBeforePostingResumeInNanoseconds;

    public PlayingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        this(playbackStateDependencies, playbackStateContext, new LowFpsChecker(playbackStateDependencies.getConfig(), playbackStateDependencies.getEventTransport()), new RendererActivityWatchdog(playbackStateDependencies.getConfig(), playbackStateDependencies.getRendererScheme()), playbackStateDependencies.getConfig().getTimeToRenderBeforePostingResume(), playbackStateDependencies.getConfig().getShouldReportStreamMetaData(), playbackStateDependencies.getConfig().getPlaybackStateMachineTickInterval(), playbackStateDependencies.getConfig().getPlaybackStatusEventIntervalMillis(), new PlaybackPerformanceReporter(playbackStateDependencies.getEventTransport()), Tickers.androidTicker(), new PlaybackLiveTimeWindowTracker(playbackStateDependencies.getConfig()));
    }

    PlayingState(@Nonnull PlaybackStateDependencies playbackStateDependencies, @Nonnull PlaybackStateContext playbackStateContext, @Nonnull LowFpsChecker lowFpsChecker, @Nonnull RendererActivityWatchdog rendererActivityWatchdog, @Nonnull TimeSpan timeSpan, boolean z, @Nonnull TimeSpan timeSpan2, @Nonnull TimeSpan timeSpan3, @Nonnull PlaybackPerformanceReporter playbackPerformanceReporter, @Nonnull Ticker ticker, @Nonnull PlaybackLiveTimeWindowTracker playbackLiveTimeWindowTracker) {
        super(playbackStateDependencies, playbackStateContext, ticker);
        this.mTimeOfLastStatusEventInNanoseconds = Long.MAX_VALUE;
        this.mSentPlaybackCompletedEvent = false;
        this.mSentPlaybackStartEvent = false;
        this.mSentResumeEvent = false;
        this.mFpsChecker = (LowFpsChecker) Preconditions.checkNotNull(lowFpsChecker, "checker");
        this.mRendererActivityWatchdog = (RendererActivityWatchdog) Preconditions.checkNotNull(rendererActivityWatchdog, "rendererActivityWatchdog");
        this.mTimeToRenderedBeforePostingResumeInNanoseconds = timeSpan.getTotalNanoSeconds();
        this.mStatusEventIntervalInNanoseconds = timeSpan3.getTotalNanoSeconds();
        this.mPlaybackLiveTimeWindowTracker = (PlaybackLiveTimeWindowTracker) Preconditions.checkNotNull(playbackLiveTimeWindowTracker, "playbackLiveTimeWindowTracker");
        this.mShouldReportMetaData = z;
        this.mSleepTimeMillis = (int) timeSpan2.getTotalMilliseconds();
        EnumMap enumMapNewEnumMap = Maps.newEnumMap(SampleType.class);
        this.mLastBitrates = enumMapNewEnumMap;
        enumMapNewEnumMap.put(SampleType.AUDIO_SAMPLE, -1);
        enumMapNewEnumMap.put(SampleType.VIDEO_SAMPLE, -1);
        VideoRenderer renderer = getRenderer();
        this.mRenderer = renderer;
        renderer.addListener(playbackPerformanceReporter);
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Playing;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        Locale locale = Locale.US;
        Preconditions.checkArgument(action instanceof PlayAction, String.format(locale, "Unhandled action of type %s sent to PlayingState", action.getClass().getSimpleName()));
        Heuristics heuristics = this.mPlaybackStateContext.getContentSession().getContext().getHeuristics();
        if (heuristics != null) {
            heuristics.onPlaybackStateChange(HeuristicsPlaybackState.Playing);
        }
        PlayAction playAction = (PlayAction) action;
        long playbackTimeInNanoseconds = getPlaybackTimeInNanoseconds() / PlaybackEngineState.NANOSECONDS_IN_A_MILLISECOND;
        DLog.logf("Entering playing state current time (ms): %d", Long.valueOf(playbackTimeInNanoseconds));
        if (this.mSentPlaybackStartEvent && this.mPlaybackStateContext.getSpecification().isLiveStream()) {
            ContentSession contentSession = this.mPlaybackStateContext.getContentSession();
            Preconditions.checkNotNull(contentSession, "Content session should be initialized in LoadingState");
            long availabilityStartTimeMillis = contentSession.getContext().getManifest().getAvailabilityStartTimeMillis();
            long timeWindowStartMillis = contentSession.getTimeWindowStartMillis() - availabilityStartTimeMillis;
            long timeWindowEndMillis = contentSession.getTimeWindowEndMillis() - availabilityStartTimeMillis;
            SeekAction.SeekCause seekCause = playAction.getOutgoingState() == PlaybackState.Buffering ? SeekAction.SeekCause.LIVE_OUT_OF_WINDOW_BUFFER : SeekAction.SeekCause.LIVE_OUT_OF_WINDOW;
            if (playbackTimeInNanoseconds > timeWindowEndMillis) {
                enqueueAction(new SeekAction(TimeSpan.fromMilliseconds(timeWindowEndMillis), getState(), seekCause));
                return;
            }
            if (playbackTimeInNanoseconds < timeWindowStartMillis) {
                long timeWindowStartThreshold = this.mPlaybackLiveTimeWindowTracker.getTimeWindowStartThreshold();
                enqueueAction(new SeekAction(TimeSpan.fromMilliseconds(timeWindowStartMillis + timeWindowStartThreshold), getState(), seekCause));
                DLog.logf("SeekAction enqueued with TimeWindowStartThresholdInMs: %s", Long.valueOf(timeWindowStartThreshold));
                return;
            } else {
                this.mPlaybackStateDependencies.getEventTransport().postEvent(new AloysiusDiagnosticEvent("LiveWindowStartTimeThreshold", String.format(locale, "TimeWindowStartThresholdInMs: %s ", Long.valueOf(this.mPlaybackLiveTimeWindowTracker.getCurrentLiveWindowStartThreshold())), AloysiusDiagnosticsState.Discrete));
                this.mPlaybackStateContext.getContentSession().getContext().getPlaybackEventReporter().reportMetric(QOSEventName.Information.toString(), "LiveWindowStartTimeThreshold", null, String.format(locale, "TimeWindowStartThresholdInMs: %s ", Long.valueOf(this.mPlaybackLiveTimeWindowTracker.getCurrentLiveWindowStartThreshold())), null);
                this.mPlaybackLiveTimeWindowTracker.disableCurrentSessionTimeWindowStartThreshold();
            }
        }
        this.mPlaybackStateContext.getContentSession().notifyPlayerBindStateChange(PlayerBindState.ATTACHED_LOADED);
        MediaProfiler profiler = getProfiler();
        profiler.start("ResumeRenderer");
        this.mRenderer.resume();
        profiler.stop("ResumeRenderer");
        long playbackTimeInNanoseconds2 = getPlaybackTimeInNanoseconds();
        this.mTimeOfLastStatusEventInNanoseconds = playbackTimeInNanoseconds2;
        this.mFpsChecker.reset(playbackTimeInNanoseconds2);
        this.mRendererActivityWatchdog.reset(this.mTimeOfLastStatusEventInNanoseconds);
        if (!this.mSentPlaybackStartEvent) {
            reportLifecycleProfilerMetrics();
            int rendererStatusFlags = this.mPlaybackStateContext.getRendererStatusFlags();
            ContentSessionContext context = this.mPlaybackStateContext.getContentSession().getContext();
            String settingsId = heuristics == null ? "HeuristicsSettingsIdG2S2" : heuristics.getSettingsId();
            StreamSelections streamSelections = context.getStreamSelections();
            AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext = new AudioAdaptationSetSwitchContext(streamSelections.getAudioStream().getFourCC(), streamSelections.getStartAudioQualityLevel().getBitrate(), "AudioAdaptationSetChange-Streamstart", streamSelections.getSelectionParams());
            DLog.devf("posting PlaybackStartEvent, event bus instance: %s", this.mPlaybackStateDependencies.getEventTransport());
            PlaybackStartEvent playbackStartEvent = new PlaybackStartEvent(new TimeSpan(this.mTimeOfLastStatusEventInNanoseconds), this.mPlaybackStateDependencies.getRendererScheme().getCapabilityDetector().getDeviceCapability(), null, rendererStatusFlags, this.mPlaybackStateDependencies.getRendererScheme().getSchemeType().name(), this.mPlaybackStateContext.getDecryptionContext().getDrmScheme().name(), getRenderer().getCodecInfo(), settingsId, audioAdaptationSetSwitchContext, streamSelections, context.getManifest().isHdr(), getConsumptionId(), getAudioTrackId(), getLanguageCode(), this.mPlaybackStateContext.getSpecification().isLiveStream(), context.getContentStoreType());
            postEvent(playbackStartEvent);
            getHeuristicsReporter().handlePlaybackStartEvent(playbackStartEvent);
            getPlaybackEventReporter().handlePlaybackStartEvent(playbackStartEvent);
            QALog.newQALog(VideoRenderer.RendererStatusFlags.getQAEvent(rendererStatusFlags));
            this.mSentPlaybackStartEvent = true;
            if (this.mShouldReportMetaData) {
                String str = String.format(locale, "isPatternTemplateManifest:%s, is_live:%s, is_dynamic:%s, manifest_refresh_enabled:%s, title:%s, appInstanceId:%s, DAG:%s, PVOR:%s, ROD:%s, SSAI:%s", context.getManifest().isPatternTemplateManifest(), Boolean.valueOf(this.mPlaybackStateContext.getSpecification().isLiveStream()), Boolean.valueOf(context.getManifest().isDynamic()), Boolean.valueOf(context.isManifestRefreshEnabledForLive()), this.mPlaybackStateContext.getSpecification().getTitleId(), getPlaybackEventReporter().getAppInstanceId(), PlayerConfigurationDiagnostics.getInstance().isDAGEnabled(), PlayerConfigurationDiagnostics.getInstance().isPVOREnabled(), PlayerConfigurationDiagnostics.getInstance().isRODEnabled(), PlayerConfigurationDiagnostics.getInstance().isSSAIEnabled());
                context.getPlaybackEventReporter().reportMetric(QOSEventName.PlaybackSession.toString(), "StreamMetaData", null, str, null);
                this.mPlaybackStateDependencies.getEventTransport().postEvent(new AloysiusDiagnosticEvent("StreamMetaData", str, AloysiusDiagnosticsState.Discrete));
            }
        }
        this.mSentResumeEvent = false;
        this.mPlaybackStateContext.notifyPlaybackStarted();
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() throws MediaException {
        if (this.mPendingAudioStreamSeekOverException == null || this.mPendingVideoStreamSeekOverException == null || !attemptToHandleStreamSeekOver()) {
            long playbackTimeInNanoseconds = getPlaybackTimeInNanoseconds();
            sendEventsOnExecute(playbackTimeInNanoseconds);
            this.mFpsChecker.validateRenderedTimesMatchRealTime(playbackTimeInNanoseconds);
            this.mRendererActivityWatchdog.validateRendererTimestamp(playbackTimeInNanoseconds);
            if (this.mRenderer.haveStreamsReachedEnd() && this.mRenderer.isOutOfSamples()) {
                enqueueAction(new PauseAction());
                if (this.mSentPlaybackCompletedEvent) {
                    return;
                }
                if (this.mPlaybackStateContext.getContentSession() != null) {
                    postEvent(new PlaybackCompletedEvent(new TimeSpan(playbackTimeInNanoseconds), false, this.mPlaybackStateContext.getContentSession().getContext()));
                } else {
                    postEvent(new PlaybackCompletedEvent(new TimeSpan(playbackTimeInNanoseconds)));
                }
                this.mSentPlaybackCompletedEvent = true;
                return;
            }
            RendererSampleReceiver.ForwardSampleReturnCode forwardSampleReturnCodeForwardSampleToRenderer = forwardSampleToRenderer();
            if (forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.STREAM_SEEK_OVER_EXCEPTION) {
                return;
            }
            forwardSubtitlesToSubtitleEngine(playbackTimeInNanoseconds);
            getPlaybackSessionProtocol().setPlayPositionInNanos(playbackTimeInNanoseconds);
            if (forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.RENDERER_BUFFER_FULL || forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.NO_SAMPLES_AVAILABLE) {
                sleep(this.mSleepTimeMillis);
            }
            if (this.mRenderer.needsMoreSamplesToContinuePlaying()) {
                if (forwardSampleReturnCodeForwardSampleToRenderer != RendererSampleReceiver.ForwardSampleReturnCode.CONTENT_BUFFERING) {
                    DLog.warnf("Renderer ran out of data without content management reporting buffering, return code: %s", forwardSampleReturnCodeForwardSampleToRenderer);
                    postEvent(new RetriablePlaybackErrorEvent(new TimeSpan(playbackTimeInNanoseconds), new PlaybackException("Renderer ran out of data without content management reporting buffering.")));
                }
                if (!canResumeFromTimeInNanos(playbackTimeInNanoseconds) && (this.mPlaybackStateContext.getContentSession().getContext().getState().isPlaybackRestrictedToBufferedContent() || !waitForContentViewUpdates() || hasDownloadErrors())) {
                    ensureDataConnection();
                }
                enqueueAction(new BufferAction());
                return;
            }
            if (forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.CONTENT_BUFFERING) {
                sleep(this.mSleepTimeMillis);
            }
        }
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() {
        Heuristics heuristics = this.mPlaybackStateContext.getContentSession().getContext().getHeuristics();
        if (heuristics != null) {
            heuristics.onPlaybackStateChange(HeuristicsPlaybackState.NotPlaying);
        }
    }

    private void sendEventsOnExecute(long j) {
        long nanos;
        long jAbs = Math.abs(j - this.mTimeOfLastStatusEventInNanoseconds);
        if (!this.mSentResumeEvent && jAbs > this.mTimeToRenderedBeforePostingResumeInNanoseconds) {
            ResumeEvent resumeEvent = new ResumeEvent(new TimeSpan(j));
            postEvent(resumeEvent);
            getPlaybackEventReporter().handleResumeEvent(resumeEvent);
            this.mSentResumeEvent = true;
        }
        if (jAbs > this.mStatusEventIntervalInNanoseconds) {
            try {
                nanos = this.mRenderer.getLastRenderedTimeStampInNanos();
            } catch (InvalidRendererTimestampException unused) {
                nanos = 0;
            }
            if (this.mPlaybackStateContext.getSpecification().isLiveStream()) {
                nanos += TimeUnit.MILLISECONDS.toNanos(this.mPlaybackStateContext.getContentSession().getContext().getManifest().getAvailabilityStartTimeMillis());
            }
            long j2 = nanos;
            ContentSession contentSession = this.mPlaybackStateContext.getContentSession();
            postEvent(new StatusEvent(j, j2, this.mRenderer.getLastTimeStampPassedToRendererInNanos(SampleType.VIDEO_SAMPLE), this.mRenderer.getLastTimeStampPassedToRendererInNanos(SampleType.AUDIO_SAMPLE), this.mRenderer.getRendererPerformanceData(), contentSession == null ? null : contentSession.getContext().getStreamSelections().getAudioStream().getPeriodIdWithTimestampNanos(j)));
            this.mTimeOfLastStatusEventInNanoseconds = j;
        }
        sendBitrateChangeEvent(j, SampleType.AUDIO_SAMPLE);
        sendBitrateChangeEvent(j, SampleType.VIDEO_SAMPLE);
    }

    private void reportLifecycleProfilerMetrics() {
        this.mPlaybackStateContext.getLifecycleProfiler().reportMetric(ImmutableList.of((PmetLifecycleProfilerReporter) new QosLifecycleProfilerReporter(this.mPlaybackStateContext.getContentSession().getContext().getPlaybackEventReporter()), new PmetLifecycleProfilerReporter()));
    }

    private void sendBitrateChangeEvent(long j, SampleType sampleType) {
        int iIntValue = this.mLastBitrates.get(sampleType).intValue();
        int bitrate = getRenderer().getBitrate(sampleType);
        if (bitrate <= 0 || bitrate == iIntValue) {
            return;
        }
        postEvent(new BitrateChangeEvent(new TimeSpan(j), bitrate, iIntValue, sampleType));
        this.mLastBitrates.put(sampleType, Integer.valueOf(bitrate));
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    @Nullable
    public String getConsumptionId() {
        if (this.mPlaybackStateContext.getContentSession() != null) {
            return Strings.emptyToNull(this.mPlaybackStateContext.getContentSession().getConsumptionId());
        }
        DLog.devf("ConsumptionId for the current title is not available");
        return null;
    }

    @Nullable
    public String getAudioTrackId() {
        VideoSpecification specification = this.mPlaybackStateContext.getSpecification();
        Preconditions.checkState(specification != null, "videoSpecification must be nonnull here as we are in Playing State");
        if (specification.isLiveStream()) {
            return null;
        }
        Preconditions.checkState((this.mPlaybackStateContext.getContentSession() == null || this.mPlaybackStateContext.getContentSession().getContext() == null) ? false : true, "contentSession and contentSessionContext must be nonnull here as we are in Playing State");
        return Strings.emptyToNull(this.mPlaybackStateContext.getContentSession().getContext().getStreamSelections().getAudioStream().getAudioTrackId());
    }

    @Nullable
    public String getLanguageCode() {
        VideoSpecification specification = this.mPlaybackStateContext.getSpecification();
        Preconditions.checkState(specification != null, "videoSpecification must be nonnull here as we are in Playing State");
        Preconditions.checkState((this.mPlaybackStateContext.getContentSession() == null || this.mPlaybackStateContext.getContentSession().getContext() == null) ? false : true, "contentSession and contentSessionContext must be nonnull here as we are in Playing State");
        StreamIndex audioStream = this.mPlaybackStateContext.getContentSession().getContext().getStreamSelections().getAudioStream();
        if (specification.isLiveStream()) {
            return Strings.emptyToNull(audioStream.getLanguage());
        }
        ImmutableList<AudioTrackMetadata> audioTrackMetadataList = this.mPlaybackStateContext.getContentSession().getAudioTrackMetadataList();
        if (audioTrackMetadataList.size() == 1) {
            return Strings.emptyToNull(audioTrackMetadataList.get(0).getLanguageCode());
        }
        String audioTrackId = getAudioTrackId();
        UnmodifiableIterator<AudioTrackMetadata> it = audioTrackMetadataList.iterator();
        while (it.hasNext()) {
            AudioTrackMetadata next = it.next();
            if (next.getAudioTrackId().equals(audioTrackId)) {
                return Strings.emptyToNull(next.getLanguageCode());
            }
        }
        DLog.warnf("languageCode is not available in the audioTrackMetadataList");
        return null;
    }
}

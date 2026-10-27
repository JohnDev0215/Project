package com.amazon.avod.playback.player.states;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.exceptions.StreamSeekOverException;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlayerHeuristicReporterInterface;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.player.PlaybackStateTransitionTable;
import com.amazon.avod.playback.player.RendererSampleReceiver;
import com.amazon.avod.playback.player.VideoPlaybackTimeline;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.userdownload.DownloadWanConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlaybackEngineState {
    public static final long NANOSECONDS_IN_A_MILLISECOND = TimeUnit.MILLISECONDS.toNanos(1);
    protected final PlaybackStateContext mPlaybackStateContext;
    protected final PlaybackStateDependencies mPlaybackStateDependencies;
    private final Stopwatch mWaitTimerOnContentViewUpdates;
    protected StreamSeekOverException mPendingAudioStreamSeekOverException = null;
    protected StreamSeekOverException mPendingVideoStreamSeekOverException = null;
    private Long seekTriggerProximityThresholdInNanoseconds = null;

    public static int getUpdateContentViewRetryTimeoutMilliseconds() {
        return PathInterpolatorCompat.MAX_NUM_POINTS;
    }

    public abstract PlaybackState getState();

    public abstract void onEnter(Action action) throws MediaException;

    public abstract void onExecute() throws MediaException;

    public abstract void onExit() throws MediaException;

    protected PlaybackEngineState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext, Ticker ticker) {
        this.mPlaybackStateDependencies = playbackStateDependencies;
        this.mPlaybackStateContext = playbackStateContext;
        this.mWaitTimerOnContentViewUpdates = Stopwatch.createUnstarted(ticker);
    }

    protected final void postEvent(PlaybackEvent playbackEvent) {
        this.mPlaybackStateDependencies.getEventTransport().postEvent(playbackEvent);
    }

    @Nonnull
    protected final AloysiusPlaybackReporterInterface getPlaybackEventReporter() {
        return this.mPlaybackStateDependencies.getPlaybackReporter();
    }

    @Nonnull
    protected final AloysiusInteractionReporterInterface getInteractionReporter() {
        return this.mPlaybackStateDependencies.getInteractionReporter();
    }

    @Nonnull
    protected final AloysiusPlayerHeuristicReporterInterface getHeuristicsReporter() {
        return this.mPlaybackStateDependencies.getHeuristicsReporter();
    }

    protected final void enqueueAction(Action action) {
        DLog.enterf("enqueueAction: %s", action);
        this.mPlaybackStateDependencies.getActionQueue().enqueueAction(action);
    }

    protected final void enqueueActionToHead(Action action) {
        DLog.enterf("enqueueAction to head: %s", action);
        this.mPlaybackStateDependencies.getActionQueue().enqueueActionToHead(action);
    }

    protected final void waitForNextAction() {
        this.mPlaybackStateDependencies.getActionQueue().waitForNextAction();
    }

    protected final VideoRenderer getRenderer() {
        return this.mPlaybackStateDependencies.getRenderer();
    }

    protected final SubtitlesEngine getSubtitlesEngine() {
        return this.mPlaybackStateContext.getSubtitlesEngine();
    }

    protected final PlaybackSessionProtocol getPlaybackSessionProtocol() {
        return this.mPlaybackStateContext.getProtocol();
    }

    protected final MediaProfiler getProfiler() {
        return this.mPlaybackStateDependencies.getProfiler();
    }

    protected final VideoPlaybackTimeline getTimeline() {
        return this.mPlaybackStateDependencies.getTimeline();
    }

    protected final boolean canResumeFromTimeInNanos(long j) {
        return getPlaybackSessionProtocol().canResumeFromTimeInNanos(j);
    }

    protected final boolean canStartFromTimeInNanos(long j) {
        return getPlaybackSessionProtocol().canStartWatchingFromTimeInNanos(j);
    }

    protected final boolean hasUpdatesForContentView() {
        return getPlaybackSessionProtocol().hasUpdatesForContentView();
    }

    protected final boolean hasDownloadErrors() {
        return getPlaybackSessionProtocol().hasDownloadErrors();
    }

    protected final boolean waitForContentViewUpdates() {
        if (!this.mWaitTimerOnContentViewUpdates.isRunning()) {
            this.mWaitTimerOnContentViewUpdates.start();
        }
        boolean zHasUpdatesForContentView = hasUpdatesForContentView();
        if (!zHasUpdatesForContentView && this.mWaitTimerOnContentViewUpdates.elapsed(TimeUnit.MILLISECONDS) >= 3000) {
            return false;
        }
        if (!zHasUpdatesForContentView) {
            return true;
        }
        this.mWaitTimerOnContentViewUpdates.reset();
        return true;
    }

    protected final RendererSampleReceiver.ForwardSampleReturnCode forwardSampleToRenderer() throws MediaException {
        try {
            RendererSampleReceiver.ForwardSampleReturnCode forwardSampleReturnCodeSubmitSample = this.mPlaybackStateDependencies.getDataForwarder().submitSample(getState(), this.mPendingAudioStreamSeekOverException, this.mPendingVideoStreamSeekOverException);
            if (forwardSampleReturnCodeSubmitSample == RendererSampleReceiver.ForwardSampleReturnCode.AUDIO_TRACK_IS_DEAD_ERROR) {
                enqueueActionToHead(new SeekAction(this.mPlaybackStateDependencies.getDataForwarder().getAudioTrackRecreationTime(), getState(), SeekAction.SeekCause.AUDIO_TRACK_RECREATION));
            } else if (forwardSampleReturnCodeSubmitSample == RendererSampleReceiver.ForwardSampleReturnCode.VIDEO_SURFACE_RECREATED) {
                enqueueActionToHead(new SeekAction(this.mPlaybackStateDependencies.getDataForwarder().getVideoSurfaceRecreationTime(), getState(), SeekAction.SeekCause.VIDEO_SURFACE_RECREATION));
            }
            return forwardSampleReturnCodeSubmitSample;
        } catch (StreamSeekOverException e) {
            if (e.shouldHandleImmediately()) {
                this.mPendingVideoStreamSeekOverException = e;
                this.mPendingAudioStreamSeekOverException = e;
            } else if (e.getStreamType() == StreamType.AUDIO) {
                this.mPendingAudioStreamSeekOverException = e;
            } else {
                this.mPendingVideoStreamSeekOverException = e;
            }
            return RendererSampleReceiver.ForwardSampleReturnCode.STREAM_SEEK_OVER_EXCEPTION;
        }
    }

    protected final void forwardSubtitlesToSubtitleEngine(long j) throws MediaException {
        try {
            this.mPlaybackStateContext.getSubtitlesEngine().trySubmitSample(j);
        } catch (StreamSeekOverException unused) {
            DLog.warnf("VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE in subtitles. Seek over not required for subtitles. Dropping the exception and moving to next subtitle sample");
        }
    }

    @Nonnull
    private String getContentDebugInfo() {
        return getPlaybackSessionProtocol().getContentDebugInfo();
    }

    protected final void ensureDataConnection() throws MediaException {
        boolean zIsWANDownloadingEnabledByUser;
        NetworkConnectionManager networkConnectionManager = this.mPlaybackStateDependencies.getNetworkConnectionManager();
        if (!networkConnectionManager.hasDataConnection()) {
            throw new PlaybackException(PlaybackException.PlaybackError.CONTENT_BUFFERING_NO_DATA_CONNECTION, String.format(Locale.US, "Not able to continue playback due to lack of network connection - %s", getContentDebugInfo()));
        }
        if (networkConnectionManager.hasWANConnection()) {
            ContentSessionType sessionType = this.mPlaybackStateContext.getContentSession().getContext().getSessionType();
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[sessionType.ordinal()];
            if (i == 1 || i == 2) {
                zIsWANDownloadingEnabledByUser = DownloadWanConfig.getInstance().isWANDownloadingEnabledByUser();
            } else if (i == 3 || i == 4) {
                zIsWANDownloadingEnabledByUser = PlaybackConfig.getInstance().isWANMobileVideoEnabled();
            } else {
                throw new IllegalStateException(String.format(Locale.US, "Should not be in PlaybackEngineState with content session type %s", sessionType));
            }
            if (zIsWANDownloadingEnabledByUser) {
                return;
            }
            throw new PlaybackException(ContentSessionType.isStreamingSession(sessionType) ? PlaybackException.PlaybackError.PLAYBACK_CONTENT_BUFFERING_CONNECTION_RESTRICTED : PlaybackException.PlaybackError.DOWNLOAD_CONTENT_BUFFERING_CONNECTION_RESTRICTED, String.format(Locale.US, "Not able to continue playback since we are restricted to playing buffered content and we have reached the end of the buffer - content info - %s", getContentDebugInfo()));
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.player.states.PlaybackEngineState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected final boolean attemptToHandleStreamSeekOver() {
        Preconditions.checkState((this.mPendingAudioStreamSeekOverException == null || this.mPendingVideoStreamSeekOverException == null) ? false : true, "Pending audio and video exceptions cannot be null when this method is called.");
        if (!hasPlayheadReachedSeekFromPosition()) {
            sleep(15);
            return false;
        }
        long jMax = Math.max(this.mPendingAudioStreamSeekOverException.getSeekToPositionInNanoseconds(), this.mPendingVideoStreamSeekOverException.getSeekToPositionInNanoseconds());
        postEvent(new RetriablePlaybackErrorEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), this.mPendingVideoStreamSeekOverException));
        DLog.warnf("Seeking triggered because of: %s", this.mPendingVideoStreamSeekOverException.getMessage());
        enqueueAction(new SeekAction(new TimeSpan(jMax), getState(), SeekAction.SeekCause.STREAM_SEEK_OVER));
        this.mPendingAudioStreamSeekOverException = null;
        this.mPendingVideoStreamSeekOverException = null;
        return true;
    }

    private boolean hasPlayheadReachedSeekFromPosition() {
        long jMin = Math.min(this.mPendingVideoStreamSeekOverException.getSeekFromPositionInNanoseconds(), this.mPendingAudioStreamSeekOverException.getSeekFromPositionInNanoseconds());
        long playbackTimeInNanoseconds = getPlaybackTimeInNanoseconds();
        long j = playbackTimeInNanoseconds - jMin;
        boolean z = Math.abs(j) <= getSeekTriggerProximityThresholdInNanoseconds() || playbackTimeInNanoseconds >= jMin || getRenderer().needsMoreSamplesToContinuePlaying();
        if (z) {
            DLog.warnf("Reached seek-over position: currentPlayHeadInNanoseconds: %s minSeekFromPositionInNanoseconds: %s deltaInNanoseconds: %s", Long.valueOf(playbackTimeInNanoseconds), Long.valueOf(jMin), Long.valueOf(Math.abs(j)));
        }
        return z;
    }

    private long getSeekTriggerProximityThresholdInNanoseconds() {
        if (this.seekTriggerProximityThresholdInNanoseconds == null) {
            this.seekTriggerProximityThresholdInNanoseconds = Long.valueOf(this.mPlaybackStateDependencies.getConfig().getSeekTriggerProximityThreshold().getTotalNanoSeconds());
        }
        return this.seekTriggerProximityThresholdInNanoseconds.longValue();
    }

    public final long getPlaybackTimeInNanoseconds() {
        return getTimeline().getCurrentPlayTimeInNanos();
    }

    protected final void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            DLog.exceptionf(e, "Interrupted while waiting for state: " + getClass().getSimpleName(), new Object[0]);
        }
    }

    @Nullable
    public String getConsumptionId() {
        ContentSession contentSession = this.mPlaybackStateContext.getContentSession();
        if (contentSession == null) {
            return null;
        }
        return contentSession.getConsumptionId();
    }

    public PlaybackState getNextState(Action action) throws MediaException {
        return PlaybackStateTransitionTable.getNextState(getState(), action.getActionType());
    }

    @Nonnull
    public PlaybackStateContext getPlaybackStateContext() {
        return this.mPlaybackStateContext;
    }
}

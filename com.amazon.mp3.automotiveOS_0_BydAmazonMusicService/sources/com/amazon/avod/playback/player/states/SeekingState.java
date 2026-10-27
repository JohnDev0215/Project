package com.amazon.avod.playback.player.states;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.playback.LiveManifestRefreshEvent;
import com.amazon.avod.playback.event.playback.SeekEndEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.event.playback.SeekUpdateEvent;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.PauseAction;
import com.amazon.avod.playback.player.actions.PlayAction;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.qos.internal.DurationTimer;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SeekingState extends BufferingBaseState {
    private boolean mIsInCachedRegion;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private SeekAction.SeekCause mSeekCause;
    private boolean mSendBufferStopOnExit;
    private TimeSpan mToTime;

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected void sendBufferStatusEvents(float f) {
    }

    public SeekingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        this(playbackStateDependencies, playbackStateContext, Tickers.androidTicker(), playbackStateDependencies.getConfig().getPlaybackStateMachineTickInterval(), MediaSystemSharedDependencies.getInstance());
    }

    SeekingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext, Ticker ticker, @Nonnull TimeSpan timeSpan, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies) {
        super(playbackStateDependencies, playbackStateContext, ticker, timeSpan);
        this.mToTime = null;
        this.mIsInCachedRegion = false;
        this.mSeekCause = SeekAction.SeekCause.MANUAL;
        this.mSendBufferStopOnExit = true;
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Seeking;
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState, com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        TimeSpan toTime;
        SeekAction.SeekCause seekCause;
        super.onEnter(action);
        ContentSession contentSession = this.mPlaybackStateContext.getContentSession();
        Preconditions.checkNotNull(contentSession, "Content session should be initialized in LoadingState");
        Heuristics heuristics = contentSession.getContext().getHeuristics();
        if (heuristics != null) {
            heuristics.onPlaybackStateChange(HeuristicsPlaybackState.Seeking);
        }
        getRenderer().pause();
        this.mPlaybackStateDependencies.getDataForwarder().flush();
        Preconditions.checkState(action instanceof SeekAction, "Only a SeekAction should be used to enter the SeekingState.");
        SeekAction seekAction = (SeekAction) action;
        if (contentSession.getContext().getSpecification().isLiveStream()) {
            toTime = resolveToTimeWindowBoundary(seekAction.getToTime(), false);
        } else {
            toTime = seekAction.getToTime();
        }
        this.mToTime = toTime;
        long playbackTimeInNanoseconds = getPlaybackTimeInNanoseconds() / PlaybackEngineState.NANOSECONDS_IN_A_MILLISECOND;
        DLog.logf("Entering seeking state, seekto time (ms): %d, current time (ms): %d", Long.valueOf(this.mToTime.getTotalMilliseconds()), Long.valueOf(playbackTimeInNanoseconds));
        this.mIsInCachedRegion = canResumeFromTimeInNanos(this.mToTime.getTotalNanoSeconds());
        TimeSpan timeSpan = new TimeSpan(getPlaybackTimeInNanoseconds());
        this.mSeekCause = seekAction.getSeekCause();
        SeekStartEvent seekStartEvent = new SeekStartEvent(timeSpan, this.mToTime, this.mIsInCachedRegion);
        postEvent(seekStartEvent);
        SeekAction.SeekCause seekCause2 = this.mSeekCause;
        SeekAction.SeekCause seekCause3 = SeekAction.SeekCause.MANUAL;
        if (seekCause2 == seekCause3 && !this.mMediaSystemSharedDependencies.isSDKPlayer()) {
            getInteractionReporter().reportSeekEvent(timeSpan, this.mToTime);
        }
        getPlaybackEventReporter().handleSeekStart(seekStartEvent);
        PlaybackState outgoingState = seekAction.getOutgoingState();
        if (outgoingState == PlaybackState.Playing) {
            setOutgoingAction(new PlayAction(getState()));
        } else if (outgoingState == PlaybackState.Paused) {
            setOutgoingAction(new PauseAction());
        }
        boolean z = this.mPlaybackStateContext.getSpecification().isLiveStream() && ((seekCause = this.mSeekCause) == SeekAction.SeekCause.LIVE_OUT_OF_WINDOW || seekCause == seekCause3) && !(contentSession.getContentUrlSelector().getCurrentContentUrl().getAdsTrackingEndpoint() != null);
        if (z) {
            DLog.logf("Triggering manifest refresh due to seek action, cause: " + this.mSeekCause);
            DurationTimer durationTimer = new DurationTimer();
            durationTimer.setStartTime(TimeSpan.now());
            contentSession.refreshManifest();
            TimeSpan timeSpanElapsedUntil = durationTimer.elapsedUntil(TimeSpan.now());
            this.mPlaybackStateDependencies.getDataForwarder().initialize(contentSession.getProtocol(), contentSession.getContext().getState());
            postEvent(new LiveManifestRefreshEvent(timeSpan, this.mToTime, seekAction, timeSpanElapsedUntil, this.mIsInCachedRegion));
        }
        if (!this.mIsInCachedRegion && this.mPlaybackStateContext.getContentSession().shouldSwitchAudioAdaptationSet()) {
            this.mSendBufferStopOnExit = false;
            this.mPlaybackStateDependencies.getEventTransport().postEvent(PlaybackRestartEvent.newAudioAdaptationSetSeekRestartEvent(playbackTimeInNanoseconds));
        } else {
            handleSeekActionInternal(seekAction, z);
        }
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState, com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() throws MediaException {
        super.onExit();
        if (this.mSendBufferStopOnExit) {
            postEvent(new SeekEndEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), this.mToTime, this.mIsInCachedRegion, this.mSeekCause.name()));
        }
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected boolean isContentReadyToWatch() {
        return canResumeFromTimeInNanos(this.mToTime.getTotalNanoSeconds());
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected PlaybackState handleSeekAction(SeekAction seekAction, PlaybackState playbackState) throws MediaException {
        handleSeekActionInternal(seekAction, false);
        return null;
    }

    private void handleSeekActionInternal(SeekAction seekAction, boolean z) throws MediaException {
        Preconditions.checkNotNull(this.mPlaybackStateContext.getContentSession(), "Content session should be initialized in LoadingState");
        SeekAction.SeekCause seekCause = seekAction.getSeekCause();
        SeekAction.SeekCause seekCause2 = SeekAction.SeekCause.STREAM_SEEK_OVER;
        if (seekCause != seekCause2) {
            this.mPendingAudioStreamSeekOverException = null;
            this.mPendingVideoStreamSeekOverException = null;
        }
        TimeSpan timeSpanSeekTo = getPlaybackSessionProtocol().seekTo(seekAction.getToTime(), seekAction.getSeekCause() == seekCause2 || seekAction.getSeekCause() == SeekAction.SeekCause.AD_SKIP || seekAction.getSeekCause() == SeekAction.SeekCause.AD_SEEK_OVER);
        getRenderer().flush();
        getSubtitlesEngine().flush(z);
        getPlaybackSessionProtocol().setPlayPositionInNanos(timeSpanSeekTo.getTotalNanoSeconds());
        boolean zCanResumeFromTimeInNanos = canResumeFromTimeInNanos(timeSpanSeekTo.getTotalNanoSeconds());
        postEvent(new SeekUpdateEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), this.mToTime, this.mIsInCachedRegion, timeSpanSeekTo, zCanResumeFromTimeInNanos));
        this.mToTime = timeSpanSeekTo;
        this.mIsInCachedRegion = zCanResumeFromTimeInNanos;
        getTimeline().setOverrideTimeInNanos(this.mToTime.getTotalNanoSeconds());
    }
}

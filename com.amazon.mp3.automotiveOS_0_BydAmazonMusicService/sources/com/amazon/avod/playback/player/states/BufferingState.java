package com.amazon.avod.playback.player.states;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.BufferStatusEvent;
import com.amazon.avod.playback.event.playback.BufferStopEvent;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.ActionType;
import com.amazon.avod.playback.player.actions.PlayAction;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class BufferingState extends BufferingBaseState {
    private final NetworkConnectionManager mNetworkConnectionManager;
    private boolean mOldInCachedRegion;
    private boolean mSendBufferStopOnExit;

    public BufferingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        this(playbackStateDependencies, playbackStateContext, Tickers.androidTicker(), NetworkConnectionManager.getInstance(), playbackStateDependencies.getConfig().getPlaybackStateMachineTickInterval());
    }

    BufferingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext, Ticker ticker, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull TimeSpan timeSpan) {
        super(playbackStateDependencies, playbackStateContext, ticker, timeSpan);
        this.mOldInCachedRegion = false;
        this.mSendBufferStopOnExit = true;
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "connectionManager");
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Buffering;
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState, com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        super.onEnter(action);
        Heuristics heuristics = this.mPlaybackStateContext.getContentSession().getContext().getHeuristics();
        if (heuristics != null) {
            heuristics.onPlaybackStateChange(HeuristicsPlaybackState.Buffering);
        }
        getRenderer().pause();
        DLog.logf("Entering buffering state current time (ms): %d", Long.valueOf(getPlaybackTimeInNanoseconds() / PlaybackEngineState.NANOSECONDS_IN_A_MILLISECOND));
        this.mOldInCachedRegion = isContentReadyToWatch();
        getPlaybackSessionProtocol().onBufferingStart();
        BufferingAnalysis bufferingAnalysis = getBufferingAnalysis();
        DLog.logf("Buffering-Analysis: %s", bufferingAnalysis);
        BufferStartEvent bufferStartEvent = new BufferStartEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), this.mOldInCachedRegion, bufferingAnalysis);
        postEvent(bufferStartEvent);
        getPlaybackEventReporter().handleBufferStartEvent(bufferStartEvent);
        reportNetworkStatus();
        setOutgoingAction(new PlayAction(getState()));
        if (this.mPlaybackStateContext.getContentSession().shouldSwitchAudioAdaptationSet()) {
            this.mSendBufferStopOnExit = false;
            this.mPlaybackStateDependencies.getEventTransport().postEvent(PlaybackRestartEvent.newAudioAdaptationSetBufferRestartEvent());
        }
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState, com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() throws MediaException {
        super.onExit();
        if (this.mSendBufferStopOnExit) {
            getPlaybackSessionProtocol().onBufferingStop();
            postEvent(new BufferStopEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), this.mOldInCachedRegion));
        }
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected void sendBufferStatusEvents(float f) {
        boolean zIsContentReadyToWatch = isContentReadyToWatch();
        postEvent(new BufferStatusEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), f, this.mOldInCachedRegion, zIsContentReadyToWatch));
        this.mOldInCachedRegion = zIsContentReadyToWatch;
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected boolean isContentReadyToWatch() {
        return canResumeFromTimeInNanos(getPlaybackTimeInNanoseconds());
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected PlaybackState handleSeekAction(SeekAction seekAction, PlaybackState playbackState) {
        if (getOutgoingAction().getActionType() == ActionType.Play) {
            seekAction.setOutgoingState(PlaybackState.Playing);
        } else {
            seekAction.setOutgoingState(PlaybackState.Paused);
        }
        return playbackState;
    }

    public void reportNetworkStatus() {
        PlaybackEventReporter playbackEventReporter = this.mPlaybackStateContext.getContentSession().getContext() != null ? this.mPlaybackStateContext.getContentSession().getContext().getPlaybackEventReporter() : null;
        if (playbackEventReporter != null) {
            playbackEventReporter.reportMetric(QOSEventName.Information.toString(), null, TimeSpan.ZERO, "NetworkConnectionStatus: " + this.mNetworkConnectionManager.getNetworkInfo().getNetworkState(), null);
        }
    }
}

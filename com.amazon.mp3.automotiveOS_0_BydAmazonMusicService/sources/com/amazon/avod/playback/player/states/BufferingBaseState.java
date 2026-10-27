package com.amazon.avod.playback.player.states;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.player.RendererSampleReceiver;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.ActionType;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BufferingBaseState extends PlaybackEngineState {
    private boolean mIsEndOfStreamErrorReported;
    private final Stopwatch mLoggingStopwatch;
    private Action mOutgoingAction;
    private final int mSleepTimeMillis;

    protected abstract PlaybackState handleSeekAction(SeekAction seekAction, PlaybackState playbackState) throws MediaException;

    protected abstract boolean isContentReadyToWatch();

    protected abstract void sendBufferStatusEvents(float f);

    protected BufferingBaseState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext, Ticker ticker, @Nonnull TimeSpan timeSpan) {
        super(playbackStateDependencies, playbackStateContext, ticker);
        this.mOutgoingAction = null;
        this.mLoggingStopwatch = Stopwatch.createUnstarted(ticker);
        this.mSleepTimeMillis = (int) ((TimeSpan) Preconditions.checkNotNull(timeSpan, "playbackStateMachineTickInterval")).getTotalMilliseconds();
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        this.mLoggingStopwatch.reset().start();
        this.mIsEndOfStreamErrorReported = false;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() throws MediaException {
        if (this.mPendingAudioStreamSeekOverException != null && this.mPendingVideoStreamSeekOverException != null) {
            if (attemptToHandleStreamSeekOver()) {
                return;
            }
            handleReadyToWatch();
            return;
        }
        RendererSampleReceiver.ForwardSampleReturnCode forwardSampleReturnCodeForwardSampleToRenderer = forwardSampleToRenderer();
        if (forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.STREAM_SEEK_OVER_EXCEPTION) {
            return;
        }
        if (isRendererAndContentReadyToWatch()) {
            handleReadyToWatch();
            return;
        }
        if (forwardSampleReturnCodeForwardSampleToRenderer != RendererSampleReceiver.ForwardSampleReturnCode.SUCCESS) {
            if (!this.mIsEndOfStreamErrorReported && forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.NO_SAMPLES_AVAILABLE) {
                DLog.warnf("Reached end-of-stream without content being ready to watch!  This may be indicative of an infinite spinner state");
                postEvent(new RetriablePlaybackErrorEvent(TimeSpan.now(), new PlaybackException("Reached end-of-stream without content being ready to watch!  This may be indicative of an infinite spinner state")));
                this.mIsEndOfStreamErrorReported = true;
            }
            if (forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.CONTENT_BUFFERING && !isContentReadyToWatch() && (this.mPlaybackStateContext.getContentSession().getContext().getState().isPlaybackRestrictedToBufferedContent() || !waitForContentViewUpdates() || hasDownloadErrors())) {
                ensureDataConnection();
            }
            handleWaiting();
        }
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() throws MediaException {
        this.mLoggingStopwatch.stop();
    }

    private boolean isRendererAndContentReadyToWatch() {
        boolean zNeedsMoreSamplesToStartPlaying = getRenderer().needsMoreSamplesToStartPlaying();
        boolean zIsContentReadyToWatch = isContentReadyToWatch();
        if (this.mLoggingStopwatch.elapsed(TimeUnit.MILLISECONDS) >= 10000) {
            String simpleName = getClass().getSimpleName();
            Action action = this.mOutgoingAction;
            DLog.logf("Still waiting in state %s (outgoing action %s, ); %s", simpleName, action != null ? action.getActionType() : "none", getBufferingAnalysis());
            this.mLoggingStopwatch.reset().start();
        }
        return !zNeedsMoreSamplesToStartPlaying && zIsContentReadyToWatch;
    }

    protected void handleReadyToWatch() {
        sendBufferStatusEvents(1.0f);
        Action outgoingAction = getOutgoingAction();
        if (outgoingAction != null) {
            enqueueAction(outgoingAction);
        }
    }

    protected void handleWaiting() {
        sendBufferStatusEvents(0.0f);
        sleep(this.mSleepTimeMillis);
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getNextState(Action action) throws MediaException {
        PlaybackState nextState = super.getNextState(action);
        if (action.getActionType() == ActionType.Seek) {
            Preconditions.checkState(action instanceof SeekAction, "Only SeekAction should have an ActionType of Seek.");
            Action outgoingAction = getOutgoingAction();
            DLog.logf("Seek within seek/buffer, outgoing action is %s", outgoingAction != null ? outgoingAction.getActionType() : "(null)");
            return handleSeekAction((SeekAction) action, nextState);
        }
        if (nextState == null || nextState == PlaybackState.Shutdown || isRendererAndContentReadyToWatch()) {
            return nextState;
        }
        DLog.logf("Not ready to leave %s yet - Updating outgoing action to %s", getClass().getSimpleName(), action.getActionType());
        setOutgoingAction(action);
        return null;
    }

    protected void setOutgoingAction(Action action) {
        this.mOutgoingAction = action;
    }

    protected Action getOutgoingAction() {
        return this.mOutgoingAction;
    }

    protected TimeSpan resolveToTimeWindowBoundary(TimeSpan timeSpan, boolean z) throws ContentException {
        ContentSession contentSession = this.mPlaybackStateContext.getContentSession();
        Preconditions.checkNotNull(contentSession, "mPlaybackStateContext.getContentSession()");
        ContentSessionContext context = contentSession.getContext();
        Preconditions.checkNotNull(context, "contentSession.getContext()");
        return new TimeSpan(context.getState().resolveToMediaTimeWindowBoundary(timeSpan.getTotalNanoSeconds(), z));
    }

    @Nonnull
    public BufferingAnalysis getBufferingAnalysis() {
        PlaybackSessionProtocol playbackSessionProtocol = getPlaybackSessionProtocol();
        long lastAvailablePositionInNanos = playbackSessionProtocol == null ? 0L : playbackSessionProtocol.getLastAvailablePositionInNanos(StreamType.VIDEO);
        return new BufferingAnalysis.Builder().setPlayHeadNanos(getPlaybackTimeInNanoseconds()).setRendererStall(getRenderer().needsMoreSamplesToStartPlaying()).setContentReady(isContentReadyToWatch()).setLastVideoPlayerPositionNanos(getRenderer().getLastTimeStampPassedToRendererInNanos(SampleType.VIDEO_SAMPLE)).setLastVideoContentPositionNanos(lastAvailablePositionInNanos).setLastAudioPlayerPositionNanos(getRenderer().getLastTimeStampPassedToRendererInNanos(SampleType.AUDIO_SAMPLE)).setLastAudioContentPositionNanos(playbackSessionProtocol != null ? playbackSessionProtocol.getLastAvailablePositionInNanos(StreamType.AUDIO) : 0L).build();
    }
}

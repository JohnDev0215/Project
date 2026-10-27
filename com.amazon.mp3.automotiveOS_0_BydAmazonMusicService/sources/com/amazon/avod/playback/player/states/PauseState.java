package com.amazon.avod.playback.player.states;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.player.RendererSampleReceiver;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class PauseState extends PlaybackEngineState {
    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() {
    }

    public PauseState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        super(playbackStateDependencies, playbackStateContext, Tickers.androidTicker());
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Paused;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        getRenderer().pause();
        DLog.logf("Entering pause state current time (ms): %d", Long.valueOf(getPlaybackTimeInNanoseconds() / PlaybackEngineState.NANOSECONDS_IN_A_MILLISECOND));
        PauseEvent pauseEvent = new PauseEvent(new TimeSpan(getPlaybackTimeInNanoseconds()));
        postEvent(pauseEvent);
        getPlaybackEventReporter().handlePauseEvent(pauseEvent);
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() throws MediaException {
        RendererSampleReceiver.ForwardSampleReturnCode forwardSampleReturnCodeForwardSampleToRenderer = forwardSampleToRenderer();
        if (forwardSampleReturnCodeForwardSampleToRenderer != RendererSampleReceiver.ForwardSampleReturnCode.STREAM_SEEK_OVER_EXCEPTION && forwardSampleReturnCodeForwardSampleToRenderer == RendererSampleReceiver.ForwardSampleReturnCode.RENDERER_BUFFER_FULL) {
            waitForNextAction();
        }
    }
}

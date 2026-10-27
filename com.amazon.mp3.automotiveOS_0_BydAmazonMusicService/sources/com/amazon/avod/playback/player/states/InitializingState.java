package com.amazon.avod.playback.player.states;

import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.InitializeAction;
import com.amazon.avod.threading.Tickers;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class InitializingState extends PlaybackEngineState {
    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() {
    }

    public InitializingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        super(playbackStateDependencies, playbackStateContext, Tickers.androidTicker());
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Initializing;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        String fourCC;
        Preconditions.checkArgument(action instanceof InitializeAction, "Can only accept InitializeAction in InitializingState!");
        MediaProfiler profiler = getProfiler();
        profiler.start("VideoRenderer.initialize");
        if (((InitializeAction) action).getAudioFormat() == AudioFormat.AC_3_5_1) {
            fourCC = AudioStreamType.DDP.getFourCC();
        } else {
            fourCC = AudioStreamType.AACL.getFourCC();
        }
        this.mPlaybackStateContext.setRendererStatusFlags(getRenderer().initialize("AVC1", fourCC, true));
        profiler.stop("VideoRenderer.initialize");
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() {
        waitForNextAction();
    }
}

package com.amazon.avod.playback.player.states;

import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.threading.Tickers;

/* JADX INFO: loaded from: classes2.dex */
public class UninitializedState extends PlaybackEngineState {
    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) {
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() {
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() {
    }

    public UninitializedState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        super(playbackStateDependencies, playbackStateContext, Tickers.androidTicker());
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Uninitialized;
    }
}

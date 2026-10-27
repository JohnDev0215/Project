package com.amazon.avod.content.dash.quality.heuristic;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class HeuristicsSharedInfo {
    private HeuristicsPlaybackState mPlaybackState = HeuristicsPlaybackState.NotPlaying;

    public HeuristicsPlaybackState getPlaybackState() {
        return this.mPlaybackState;
    }

    public void setPlaybackState(HeuristicsPlaybackState heuristicsPlaybackState) {
        this.mPlaybackState = heuristicsPlaybackState;
    }
}

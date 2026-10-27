package com.amazon.avod.content.dash.quality.heuristic;

/* JADX INFO: loaded from: classes2.dex */
public enum HeuristicsPlaybackState {
    Playing(0),
    NotPlaying(1),
    Seeking(2),
    Buffering(3);

    private final int mValue;

    HeuristicsPlaybackState(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}

package com.amazon.mp3.playback.view;

import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;

/* JADX INFO: loaded from: classes3.dex */
public interface OnPlayerClickListener {
    boolean canRestart();

    void onForwardClicked();

    boolean onNextClicked(InteractionType type);

    void onPlayPauseClicked();

    void onPlayerClicked();

    boolean onPrevClicked(InteractionType type);

    void onRepeatClicked();

    void onRewindClicked();

    void onShuffleClicked();

    void onThumbsDownClicked();

    void onThumbsUpClicked();
}

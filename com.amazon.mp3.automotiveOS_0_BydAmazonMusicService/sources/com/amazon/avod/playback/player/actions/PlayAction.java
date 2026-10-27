package com.amazon.avod.playback.player.actions;

import com.amazon.avod.playback.player.states.PlaybackState;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayAction extends Action {
    private final PlaybackState mOutgoingState;

    public PlayAction(@Nonnull PlaybackState playbackState) {
        this.mOutgoingState = (PlaybackState) Preconditions.checkNotNull(playbackState, "outgoingState");
    }

    @Override // com.amazon.avod.playback.player.actions.Action
    public ActionType getActionType() {
        return ActionType.Play;
    }

    @Nonnull
    public PlaybackState getOutgoingState() {
        return this.mOutgoingState;
    }
}

package com.amazon.avod.playback.player.actions;

import com.amazon.avod.media.AudioFormat;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InitializeAction extends Action {
    private final AudioFormat mAudioFormat;

    public InitializeAction(@Nonnull AudioFormat audioFormat) {
        this.mAudioFormat = (AudioFormat) Preconditions.checkNotNull(audioFormat);
    }

    @Override // com.amazon.avod.playback.player.actions.Action
    public ActionType getActionType() {
        return ActionType.Initialize;
    }

    @Nonnull
    public AudioFormat getAudioFormat() {
        return this.mAudioFormat;
    }
}

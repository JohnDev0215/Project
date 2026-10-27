package com.amazon.avod.media.playback.state.trigger;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.error.MediaErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorTrigger implements Trigger<PlayerTriggerType> {
    private final MediaErrorCode mErrorCode;

    public ErrorTrigger(MediaErrorCode mediaErrorCode) {
        this.mErrorCode = mediaErrorCode;
    }

    public MediaErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return StandardPlayerTriggerType.ERROR;
    }
}

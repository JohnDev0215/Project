package com.amazon.avod.media.playback.state.trigger;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class SeekTrigger implements Trigger<PlayerTriggerType> {
    private final TimeSpan mSeekTarget;

    public SeekTrigger(TimeSpan timeSpan) {
        this.mSeekTarget = (TimeSpan) Preconditions.checkNotNull(timeSpan);
    }

    public TimeSpan getSeekTarget() {
        return this.mSeekTarget;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return StandardPlayerTriggerType.SEEK;
    }
}

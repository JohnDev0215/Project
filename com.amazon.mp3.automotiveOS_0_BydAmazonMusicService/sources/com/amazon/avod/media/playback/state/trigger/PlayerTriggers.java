package com.amazon.avod.media.playback.state.trigger;

import com.amazon.avod.fsm.Trigger;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayerTriggers implements Trigger<PlayerTriggerType> {
    BUFFER(StandardPlayerTriggerType.BUFFER),
    INITIALIZE(StandardPlayerTriggerType.INITIALIZE),
    PAUSE(StandardPlayerTriggerType.PAUSE),
    PLAY(StandardPlayerTriggerType.PLAY),
    PREPARE(StandardPlayerTriggerType.PREPARE),
    PREPARED(StandardPlayerTriggerType.PREPARED),
    SHUTDOWN(StandardPlayerTriggerType.SHUTDOWN),
    COMPLETED(StandardPlayerTriggerType.COMPLETED);

    private final PlayerTriggerType mType;

    PlayerTriggers(PlayerTriggerType playerTriggerType) {
        this.mType = playerTriggerType;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return this.mType;
    }
}

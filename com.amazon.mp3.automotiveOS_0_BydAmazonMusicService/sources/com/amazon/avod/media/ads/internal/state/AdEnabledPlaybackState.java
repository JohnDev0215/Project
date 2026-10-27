package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.fsm.StateBase;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledPlaybackState extends StateBase<PlayerStateType, PlayerTriggerType> {
    private final AdPlaybackStateMachineContext mContext;
    private final PlayerStateType mStateType;

    @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
    }

    @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
    }

    public AdEnabledPlaybackState(PlayerStateType playerStateType, AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(adPlaybackStateMachineContext.getStateMachine());
        this.mStateType = (PlayerStateType) Preconditions.checkNotNull(playerStateType);
        this.mContext = (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext);
    }

    @Override // com.amazon.avod.fsm.State
    public PlayerStateType getType() {
        return this.mStateType;
    }

    @Nonnull
    public AdPlaybackStateMachineContext getContext() {
        return this.mContext;
    }

    @Nullable
    protected String getConsumptionId() {
        ContentSession contentSession = getContext().getPlaybackSessionContext().getContentSession();
        if (contentSession != null) {
            return contentSession.getConsumptionId();
        }
        return null;
    }
}

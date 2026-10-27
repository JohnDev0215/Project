package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState;
import com.amazon.avod.playback.smoothstream.fsm.Triggers;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class IdleState extends PlayerLifecycleState {
    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState
    protected void doEnter(@Nonnull Trigger<Triggers.Type> trigger) {
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public /* bridge */ /* synthetic */ void enter(Trigger trigger) {
        super.enter(trigger);
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState, com.amazon.avod.fsm.State
    /* JADX INFO: renamed from: getType */
    public /* bridge */ /* synthetic */ PlayerLifecycleState.Type get_type() {
        return super.get_type();
    }

    public IdleState(PlayerLifecycleStateMachine playerLifecycleStateMachine, PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext) {
        super(playerLifecycleStateMachine, PlayerLifecycleState.Type.IDLE, playbackSessionResources, playbackSessionContext);
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(@Nonnull Trigger<Triggers.Type> trigger) {
        Preconditions.checkNotNull(trigger, "exit() must be called with a valid trigger.");
    }
}

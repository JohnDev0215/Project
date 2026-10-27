package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class CompletedState extends AdEnabledPlaybackState {
    public CompletedState(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(StandardPlayerStateType.COMPLETED, adPlaybackStateMachineContext);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        getContext().getPresentationEventListener().onCompletion();
    }
}

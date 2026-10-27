package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.ErrorTrigger;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.util.CastUtils;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class ErrorState extends AdEnabledPlaybackState {
    public ErrorState(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(StandardPlayerStateType.ERROR, adPlaybackStateMachineContext);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        ErrorTrigger errorTrigger = (ErrorTrigger) CastUtils.castTo(trigger, ErrorTrigger.class);
        Preconditions.checkArgument(errorTrigger != null, "only ErrorTrigger triggers are handled.");
        getContext().getPresentationEventListener().onError(errorTrigger.getErrorCode());
    }
}

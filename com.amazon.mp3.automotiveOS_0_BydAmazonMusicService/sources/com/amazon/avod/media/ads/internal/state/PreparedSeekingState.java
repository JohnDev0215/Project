package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.util.CastUtils;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class PreparedSeekingState extends AdEnabledPlaybackState {
    public PreparedSeekingState(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(AdEnabledPlayerStateType.PREPARED_SEEKING, adPlaybackStateMachineContext);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        SeekTrigger seekTrigger = (SeekTrigger) CastUtils.castTo(trigger, SeekTrigger.class);
        Preconditions.checkArgument(seekTrigger != null, "Only SeekTrigger triggers are handled.");
        getContext().getPrimaryPlayer().seekTo(seekTrigger.getSeekTarget().getTotalMilliseconds());
    }
}

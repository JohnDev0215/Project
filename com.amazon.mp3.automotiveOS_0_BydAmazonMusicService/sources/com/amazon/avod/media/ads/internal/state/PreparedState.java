package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class PreparedState extends AdEnabledPlaybackState {
    private final boolean mPreloadingEnabled;

    public PreparedState(PlayerStateType playerStateType, AdPlaybackStateMachineContext adPlaybackStateMachineContext, boolean z) {
        super(playerStateType, adPlaybackStateMachineContext);
        this.mPreloadingEnabled = z;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        VideoPlayerLifecyleEventHandler presentationEventListener = getContext().getPresentationEventListener();
        preloadPreRollBreaks(getContext().getVideoSpecification().getStartTime());
        presentationEventListener.onPrepared(getContext().getPrimaryPresentationPlaybackStatus());
    }

    private void preloadPreRollBreaks(TimeSpan timeSpan) {
        if (this.mPreloadingEnabled && timeSpan.isZero()) {
            for (AdBreak adBreak : getContext().getPlan().getBreaks()) {
                if (adBreak.getStartTime().isZero()) {
                    adBreak.preload(timeSpan, getContext().shouldStoreInPersistentCache(adBreak.getAdPositionType()), getConsumptionId());
                    return;
                }
            }
        }
    }
}

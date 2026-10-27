package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.state.AdBreakTrigger;
import com.amazon.avod.media.ads.internal.state.PrerollStatus;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusContentReporter;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.playback.event.PlaybackContentEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedAdCheckPrerollState extends CheckPrerollState {
    private final AloysiusContentReporter mAloysiusContentReporter;

    public ServerInsertedAdCheckPrerollState(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdBreakSelector adBreakSelector, @Nonnull AloysiusContentReporter aloysiusContentReporter) {
        super(adPlaybackStateMachineContext, adBreakSelector);
        this.mAloysiusContentReporter = (AloysiusContentReporter) Preconditions.checkNotNull(aloysiusContentReporter, "aloysiusContentReporter");
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.CheckPrerollState, com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        TimeSpan startTime = getContext().getVideoSpecification().getStartTime();
        AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), startTime);
        PrerollStatus prerollStatus = PrerollStatus.getPrerollStatus(startTime, adBreakSelectNextBreak, getContext());
        doTrigger(PlayerTriggers.PLAY);
        if (prerollStatus.isAvailableForPlayback()) {
            doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
        } else {
            this.mAloysiusContentReporter.handlePlaybackContentEvent(new PlaybackContentEvent(getContext().getVideoSpecification().getContentId(), getContext().getPrimaryPlayer(), "Feature"));
        }
        reportPrerollStatus(prerollStatus);
    }
}

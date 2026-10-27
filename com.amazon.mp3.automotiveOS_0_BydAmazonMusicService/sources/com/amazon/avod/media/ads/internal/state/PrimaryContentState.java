package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusContentReporter;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.media.playback.state.trigger.StandardPlayerTriggerType;
import com.amazon.avod.playback.event.PlaybackContentEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class PrimaryContentState extends AdEnabledPlaybackState {
    private final AloysiusContentReporter mAloysiusContentReporter;

    public PrimaryContentState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AloysiusContentReporter aloysiusContentReporter) {
        super(StandardPlayerStateType.PLAYING, adPlaybackStateMachineContext);
        this.mAloysiusContentReporter = (AloysiusContentReporter) Preconditions.checkNotNull(aloysiusContentReporter, "aloysiusContentReporter");
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        if (getContext().isPrimaryContentComplete()) {
            doTrigger(PlayerTriggers.COMPLETED);
            return;
        }
        AdPlaybackStateMachineContext context = getContext();
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(context.getPrimaryPlayer().getCurrentPosition());
        context.showPrimaryContent();
        if (!context.isPrimaryContentStarted()) {
            context.getStateMachine().setRenderingSettingsOnPrimaryPlayer(context.getRenderingSettings());
            context.setPrimaryContentStarted(true);
        }
        context.getPrimaryPlayer().start();
        doTrigger(new MonitorTrigger(timeSpanFromMilliseconds));
        if (trigger.getType() == AdEnabledPlayerTriggerType.NO_MORE_ADS || trigger.getType() == AdEnabledPlayerTriggerType.SKIP_CURRENT_AD_BREAK || trigger.getType() == AdEnabledPlayerTriggerType.NO_MORE_ADS_SKIP_TRANSITION || trigger.getType() == AdEnabledPlayerTriggerType.AD_CLIP_ERROR) {
            this.mAloysiusContentReporter.handlePlaybackContentEvent(new PlaybackContentEvent(getContext().getVideoSpecification().getUniqueIdentifier(), getContext().getPrimaryPlayer(), "Feature"));
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (trigger.getType() == StandardPlayerTriggerType.COMPLETED) {
            getContext().setPrimaryContentComplete(true);
        }
    }
}

package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedSeekState extends AdEnabledPlaybackState {
    private final AdBreakSelector mBreakSelector;
    private TimeSpan mSeekTarget;
    private final ServerInsertedManifestTimelineManager mServerInsertedManifestTimelineManager;

    public ServerInsertedSeekState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AdBreakSelector adBreakSelector, @Nullable ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        super(StandardPlayerStateType.SEEKING, adPlaybackStateMachineContext);
        this.mBreakSelector = adBreakSelector;
        this.mServerInsertedManifestTimelineManager = serverInsertedManifestTimelineManager;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        SeekTrigger seekTrigger = (SeekTrigger) CastUtils.castTo(trigger, SeekTrigger.class);
        Preconditions.checkArgument(seekTrigger != null, "Only SeekTrigger triggers are handled.");
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getCurrentPosition());
        TimeSpan timeSpanFromMilliseconds2 = TimeSpan.fromMilliseconds(getContext().getVideoPlayerProxy().getCurrentPosition());
        this.mSeekTarget = seekTrigger.getSeekTarget();
        AdBreak adBreakSelectPriorBreakIfUnPlayed = this.mBreakSelector.selectPriorBreakIfUnPlayed(getContext().getPlan(), this.mSeekTarget, timeSpanFromMilliseconds2);
        if (adBreakSelectPriorBreakIfUnPlayed != null && this.mSeekTarget.greaterThanEquals(adBreakSelectPriorBreakIfUnPlayed.getRelativeStartTime()) && shouldChapter(this.mSeekTarget, timeSpanFromMilliseconds2)) {
            DLog.logf("ServerInsertedPlaybackSession: SeekState currentAbsolutePlayheadPosition: %s, ConvertedRelativePlayHeadPosition: %s, SeekTarget: %s", timeSpanFromMilliseconds, timeSpanFromMilliseconds2, this.mSeekTarget);
            DLog.logf("Seeking past %s will get ad break %s - will force to ad state.", this.mSeekTarget, adBreakSelectPriorBreakIfUnPlayed.getStartTime());
            DLog.devf("adBreak triggered at Seeking state position = %d", Long.valueOf(timeSpanFromMilliseconds2.getTotalMilliseconds()));
            TimeSpan playbackAbsolutePositionFromRelativePositionForMain = this.mServerInsertedManifestTimelineManager.getPlaybackAbsolutePositionFromRelativePositionForMain(this.mSeekTarget.getTotalNanoSeconds());
            adBreakSelectPriorBreakIfUnPlayed.setAdTriggeredTimeStamp(timeSpanFromMilliseconds2);
            doTrigger(new AdBreakTrigger(adBreakSelectPriorBreakIfUnPlayed, playbackAbsolutePositionFromRelativePositionForMain, timeSpanFromMilliseconds2));
            return;
        }
        DLog.logf("ServerInsertedPlaybackSession: SISS.enter seekTo: %s", seekTrigger.getSeekTarget());
        doTrigger(new MonitorTrigger(seekTrigger.getSeekTarget()));
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (trigger.getType() != AdEnabledPlayerTriggerType.BEGIN_AD_BREAK) {
            TimeSpan playbackAbsolutePositionFromRelativePositionForMain = this.mServerInsertedManifestTimelineManager.getPlaybackAbsolutePositionFromRelativePositionForMain(this.mSeekTarget.getTotalNanoSeconds());
            DLog.logf("ServerInsertedPlaybackSession: SISS.exit seekTo: %s => manifest position: %s", this.mSeekTarget, playbackAbsolutePositionFromRelativePositionForMain);
            getContext().getPrimaryPlayer().seekTo(playbackAbsolutePositionFromRelativePositionForMain.getTotalMilliseconds());
        }
    }

    private boolean shouldChapter(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        return !this.mServerInsertedManifestTimelineManager.areInSameChapter(timeSpan.getTotalNanoSeconds(), timeSpan2.getTotalNanoSeconds()) || timeSpan.isZero();
    }
}

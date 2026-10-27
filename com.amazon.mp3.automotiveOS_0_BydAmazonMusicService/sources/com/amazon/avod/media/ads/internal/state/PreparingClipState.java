package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.TimerTask;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class PreparingClipState extends AdEnabledPlaybackState {
    private final AdEnabledPlaybackTimer mTimer;

    public PreparingClipState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, TimeSpan timeSpan) {
        this(adPlaybackStateMachineContext, new AdEnabledPlaybackTimer(timeSpan));
    }

    PreparingClipState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AdEnabledPlaybackTimer adEnabledPlaybackTimer) {
        super(AdEnabledPlayerStateType.PREPARING_AD, adPlaybackStateMachineContext);
        this.mTimer = adEnabledPlaybackTimer;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        PrepareClipTrigger prepareClipTrigger = (PrepareClipTrigger) CastUtils.castTo(trigger, PrepareClipTrigger.class);
        Preconditions.checkArgument(prepareClipTrigger != null, "Only PrepareClipTrigger triggers are handled.");
        final AdManagerBasedAdClip clip = prepareClipTrigger.getClip();
        VideoPresentation presentation = clip.getPresentation();
        DLog.logf("Beginning to prepare clip: %s", presentation.getSpecification());
        try {
            VideoRenderingSettings adEnabledRenderingSettings = getContext().getAdEnabledRenderingSettings();
            if (adEnabledRenderingSettings != null) {
                presentation.setRenderingSettings(adEnabledRenderingSettings);
            } else {
                presentation.setRenderingSettings(getContext().getRenderingSettings());
            }
            presentation.setListener(getContext().getStateMachine());
            presentation.prepareAsync();
            if (clip.getAdId() != null) {
                presentation.getReporter().appendReportingTag("AdId", clip.getAdId(), UUID.randomUUID().toString());
            }
            this.mTimer.start(new TimerTask() { // from class: com.amazon.avod.media.ads.internal.state.PreparingClipState.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    PreparingClipState.this.doTrigger(new ClipErrorTrigger(clip, AdError.FILE_NOT_AVAILABLE, String.format(Locale.US, "Ad video player preparing timed out after %s", PreparingClipState.this.mTimer.getTimeout())));
                }
            });
        } catch (MediaException e) {
            String str = String.format(Locale.US, "Ad presentation prepareAsync threw exception %s", e);
            DLog.exceptionf(e);
            doTrigger(new ClipErrorTrigger(clip, AdError.FILE_NOT_AVAILABLE, str));
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        this.mTimer.stop();
    }
}

package com.amazon.avod.media.ads.internal;

import com.amazon.avod.fsm.BlockingStateMachine;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.playback.PlaybackEventReporter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdPlaybackStateMachine extends BlockingStateMachine<PlayerStateType, PlayerTriggerType> implements VideoPresentationEventListener, AdEnabledPlaybackManager {
    @Nonnull
    @Deprecated
    public abstract PlaybackEventReporter getPrimaryPlayerEventReporter();

    public abstract void setCyclicReportingPaused(boolean z);

    @Deprecated
    public abstract void setRenderingSettingsOnPrimaryPlayer(@Nonnull VideoRenderingSettings videoRenderingSettings);

    public AdPlaybackStateMachine(@Nonnull String str) {
        super(str);
    }
}

package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.StandardPlayerTriggerType;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareTrigger implements Trigger<PlayerTriggerType> {
    private final VideoPresentation mPresentation;

    public PrepareTrigger(VideoPresentation videoPresentation) {
        this.mPresentation = (VideoPresentation) Preconditions.checkNotNull(videoPresentation);
    }

    @Nonnull
    public VideoPresentation getPresentation() {
        return this.mPresentation;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return StandardPlayerTriggerType.PREPARE;
    }

    public String toString() {
        return String.format(Locale.US, "PrepareTrigger:%s", this.mPresentation.getSpecification());
    }
}

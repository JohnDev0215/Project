package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdBreakTrigger implements Trigger<PlayerTriggerType> {
    private final AdBreak mAdBreak;
    private final TimeSpan mSeekStartPosition;
    private final TimeSpan mSeekTarget;

    public AdBreakTrigger(AdBreak adBreak) {
        this(adBreak, null, null);
    }

    public AdBreakTrigger(AdBreak adBreak, @Nullable TimeSpan timeSpan, @Nullable TimeSpan timeSpan2) {
        this.mAdBreak = (AdBreak) Preconditions.checkNotNull(adBreak);
        this.mSeekTarget = timeSpan;
        this.mSeekStartPosition = timeSpan2;
    }

    @Nonnull
    public AdBreak getBreak() {
        return this.mAdBreak;
    }

    @Nullable
    public TimeSpan getSeekTarget() {
        return this.mSeekTarget;
    }

    @Nullable
    public TimeSpan getSeekStartPosition() {
        return this.mSeekStartPosition;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return AdEnabledPlayerTriggerType.BEGIN_AD_BREAK;
    }
}

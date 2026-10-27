package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class PrepareClipTrigger implements Trigger<PlayerTriggerType> {
    private final AdManagerBasedAdClip mClip;

    public PrepareClipTrigger(AdManagerBasedAdClip adManagerBasedAdClip) {
        this.mClip = (AdManagerBasedAdClip) Preconditions.checkNotNull(adManagerBasedAdClip);
    }

    @Nonnull
    public AdManagerBasedAdClip getClip() {
        return this.mClip;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return AdEnabledPlayerTriggerType.PREPARE_AD;
    }

    public String toString() {
        return String.format(Locale.US, "PrepareClipTrigger:%s", getClip().getPresentation().getSpecification());
    }
}

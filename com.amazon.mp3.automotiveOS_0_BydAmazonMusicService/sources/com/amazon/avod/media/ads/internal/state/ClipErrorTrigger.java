package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class ClipErrorTrigger implements Trigger<PlayerTriggerType> {
    private final AdManagerBasedAdClip mClip;
    private final AdError mError;
    private final String mMessage;

    public ClipErrorTrigger(AdManagerBasedAdClip adManagerBasedAdClip, AdError adError, String str) {
        this.mClip = (AdManagerBasedAdClip) Preconditions.checkNotNull(adManagerBasedAdClip);
        this.mError = (AdError) Preconditions.checkNotNull(adError);
        this.mMessage = (String) Preconditions.checkNotNull(str);
    }

    @Nonnull
    public AdManagerBasedAdClip getClip() {
        return this.mClip;
    }

    @Nonnull
    public AdError getError() {
        return this.mError;
    }

    @Nonnull
    public String getMessage() {
        return this.mMessage;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return AdEnabledPlayerTriggerType.AD_CLIP_ERROR;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("error", getError()).add("clip", getClip()).add("message", getMessage()).toString();
    }
}

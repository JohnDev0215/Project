package com.amazon.avod.playback.sye.events;

import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeFallbackMidStreamEvent {
    private final SyePlayerError mOriginalSyePlayerError;

    public SyeFallbackMidStreamEvent(@Nonnull SyePlayerError syePlayerError) {
        this.mOriginalSyePlayerError = (SyePlayerError) Preconditions.checkNotNull(syePlayerError, "syePlayerError");
    }

    @Nonnull
    public SyePlayerError getOriginalSyePlayerError() {
        return this.mOriginalSyePlayerError;
    }
}

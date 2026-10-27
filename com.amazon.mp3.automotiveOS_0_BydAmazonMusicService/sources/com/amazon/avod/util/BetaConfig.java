package com.amazon.avod.util;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface BetaConfig {
    boolean isBeta();

    boolean isInternalBeta();

    void updateBetaStatusBasedOnCurrentlyPlayingVCID(@Nullable String str);
}

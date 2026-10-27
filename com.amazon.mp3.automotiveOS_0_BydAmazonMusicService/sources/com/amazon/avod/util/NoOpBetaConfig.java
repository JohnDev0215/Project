package com.amazon.avod.util;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpBetaConfig implements BetaConfig {
    @Override // com.amazon.avod.util.BetaConfig
    public boolean isBeta() {
        return false;
    }

    @Override // com.amazon.avod.util.BetaConfig
    public boolean isInternalBeta() {
        return false;
    }

    @Override // com.amazon.avod.util.BetaConfig
    public void updateBetaStatusBasedOnCurrentlyPlayingVCID(@Nullable String str) {
    }

    private static class SingletonHolder {
        private static final NoOpBetaConfig INSTANCE = new NoOpBetaConfig();

        private SingletonHolder() {
        }
    }

    public static NoOpBetaConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private NoOpBetaConfig() {
    }
}

package com.amazon.avod.util;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BetaConfigProvider {
    private BetaConfig mBetaConfig;

    private static class SingletonHolder {
        private static final BetaConfigProvider INSTANCE = new BetaConfigProvider();

        private SingletonHolder() {
        }
    }

    public static BetaConfigProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private BetaConfigProvider() {
    }

    @Nonnull
    public BetaConfig provideBetaConfig() {
        BetaConfig betaConfig = this.mBetaConfig;
        return betaConfig != null ? betaConfig : NoOpBetaConfig.getInstance();
    }
}

package com.amazon.avod.playback.config;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerConfigurationDiagnostics {
    private Boolean mDAGEnabled = null;
    private Boolean mPVOREnabled = null;
    private Boolean mRODEnabled = null;
    private Boolean mSSAIEnabled = null;

    private static final class SingletonHolder {
        private static final PlayerConfigurationDiagnostics INSTANCE = new PlayerConfigurationDiagnostics();

        private SingletonHolder() {
        }
    }

    public static PlayerConfigurationDiagnostics getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Boolean isDAGEnabled() {
        return this.mDAGEnabled;
    }

    public Boolean isPVOREnabled() {
        return this.mPVOREnabled;
    }

    public Boolean isRODEnabled() {
        return this.mRODEnabled;
    }

    public Boolean isSSAIEnabled() {
        return this.mSSAIEnabled;
    }
}

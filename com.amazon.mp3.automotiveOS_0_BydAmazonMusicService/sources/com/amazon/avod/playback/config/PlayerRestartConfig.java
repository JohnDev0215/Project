package com.amazon.avod.playback.config;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerRestartConfig extends ServerConfigBase {
    private final ConfigurationValue<Boolean> mIsPlayerRestartPmetReportingPivotsEnabled;
    private final ConfigurationValue<Boolean> mIsUTCBasedLiveRestartEnabled;

    private static final class SingletonHolder {
        private static final PlayerRestartConfig INSTANCE = new PlayerRestartConfig();

        private SingletonHolder() {
        }
    }

    public static PlayerRestartConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlayerRestartConfig() {
        this.mIsUTCBasedLiveRestartEnabled = newBooleanConfigValue("player_utcBasedRestart", true);
        this.mIsPlayerRestartPmetReportingPivotsEnabled = newBooleanConfigValue("playback_playerRestartPmetReportingPivotsEnabled", true);
    }

    public boolean isUTCBasedRestartEnabled() {
        return this.mIsUTCBasedLiveRestartEnabled.getValue().booleanValue();
    }

    public boolean isPlayerRestartPmetReportingPivotsEnabled() {
        return this.mIsPlayerRestartPmetReportingPivotsEnabled.getValue().booleanValue();
    }
}

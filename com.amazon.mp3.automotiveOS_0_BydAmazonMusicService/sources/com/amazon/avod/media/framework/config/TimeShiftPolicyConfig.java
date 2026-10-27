package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: loaded from: classes2.dex */
public class TimeShiftPolicyConfig extends ServerConfigBase {
    private final ConfigurationValue<Boolean> mIsPlayerControlDefaultKeyEnabled = newBooleanConfigValue("playback_isPlayerControlEnabled", true);
    private final ConfigurationValue<Boolean> mIsPlayPauseDefaultKeyEnabled = newBooleanConfigValue("playback_isPlayPauseEnabled", true);
    private final ConfigurationValue<Boolean> mIsSeekForwardDefaultKeyEnabled = newBooleanConfigValue("playback_isSeekForwardEnabled", true);
    private final ConfigurationValue<Boolean> mIsSeekBackwardDefaultKeyEnabled = newBooleanConfigValue("playback_isSeekBackwardEnabled", true);
    private final ConfigurationValue<Boolean> mIsPlayerControlOverrideKeyEnabled = newBooleanConfigValue("playback_isPlayerControlOverrideEnabled", true);
    private final ConfigurationValue<Boolean> mIsPlayPauseOverrideKeyEnabled = newBooleanConfigValue("playback_isPlayPauseOverrideEnabled", true);
    private final ConfigurationValue<Boolean> mIsSeekForwardOverrideKeyEnabled = newBooleanConfigValue("playback_isSeekForwardOverrideEnabled", true);
    private final ConfigurationValue<Boolean> mIsSeekBackwardOverrideKeyEnabled = newBooleanConfigValue("playback_isSeekBackwardOverrideEnabled", true);

    private static final class SingletonHolder {
        private static final TimeShiftPolicyConfig INSTANCE = new TimeShiftPolicyConfig();

        private SingletonHolder() {
        }
    }

    public static TimeShiftPolicyConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @VisibleForTesting
    TimeShiftPolicyConfig() {
    }

    public boolean isPlayerControlEnabled() {
        return this.mIsPlayerControlDefaultKeyEnabled.getValue().booleanValue();
    }

    public boolean isPlayPauseEnabled() {
        return this.mIsPlayPauseDefaultKeyEnabled.getValue().booleanValue();
    }

    public boolean isSeekForwardEnabled() {
        return this.mIsSeekForwardDefaultKeyEnabled.getValue().booleanValue();
    }

    public boolean isSeekBackwardEnabled() {
        return this.mIsSeekBackwardDefaultKeyEnabled.getValue().booleanValue();
    }

    public boolean isPlayerControlOverrideKeyEnabled() {
        return this.mIsPlayerControlOverrideKeyEnabled.getValue().booleanValue();
    }

    public boolean isPlayPauseOverrideKeyEnabled() {
        return this.mIsPlayPauseOverrideKeyEnabled.getValue().booleanValue();
    }

    public boolean isSeekForwardOverrideKeyEnabled() {
        return this.mIsSeekForwardOverrideKeyEnabled.getValue().booleanValue();
    }

    public boolean isSeekBackwardOverrideKeyEnabled() {
        return this.mIsSeekBackwardOverrideKeyEnabled.getValue().booleanValue();
    }
}

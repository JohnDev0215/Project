package com.amazon.avod.media.playback.reporting;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerHealthMetricsConfig extends MediaConfigBase {
    private final ConfigurationValue<Long> mBufferFullnessReportIntervalMillis;
    private final ConfigurationValue<Integer> mConsecutiveBufferFullnessReportCount;
    private final ConfigurationValue<Long> mCriticalLowLevelOfBufferFullness;
    private final ConfigurationValue<Boolean> mIsBufferFullnessReportEnabledForAcquisitionError;
    private final ConfigurationValue<Boolean> mIsBufferFullnessReportEnabledForStaleManifest;
    private final ConfigurationValue<Boolean> mIsPlayerHealthReportEnabled;

    private static class SingletonHolder {
        private static final PlayerHealthMetricsConfig PLAYER_HEALTH_METRICS_CONFIG = new PlayerHealthMetricsConfig();

        private SingletonHolder() {
        }
    }

    public static PlayerHealthMetricsConfig getInstance() {
        return SingletonHolder.PLAYER_HEALTH_METRICS_CONFIG;
    }

    private PlayerHealthMetricsConfig() {
        ConfigType configType = ConfigType.SERVER;
        this.mIsPlayerHealthReportEnabled = newBooleanConfigValue("playback_health_isPlayerHealthReportEnabled", true, configType);
        this.mIsBufferFullnessReportEnabledForStaleManifest = newBooleanConfigValue("playback_health_isBufferFullnessReportEnabledForStaleManifest", true, configType);
        this.mIsBufferFullnessReportEnabledForAcquisitionError = newBooleanConfigValue("playback_health_isBufferFullnessReportEnabledForAcquisitionError", false, configType);
        this.mBufferFullnessReportIntervalMillis = newLongConfigValue("playback_health_bufferFullnessReportIntervalMills", 3000L, configType);
        this.mConsecutiveBufferFullnessReportCount = newIntConfigValue("playback_health_continuousBufferFullnessReportCount", 10, configType);
        this.mCriticalLowLevelOfBufferFullness = newLongConfigValue("playback_health_criticalLowLevelOfBufferFullness", 25L, configType);
    }

    public boolean isPlayerHealthReportEnabled() {
        return this.mIsPlayerHealthReportEnabled.getValue().booleanValue();
    }

    public boolean isBufferFullnessReportEnabledForStaleManifest() {
        return this.mIsBufferFullnessReportEnabledForStaleManifest.getValue().booleanValue();
    }

    public boolean isBufferFullnessReportEnabledForAcquisitionError() {
        return this.mIsBufferFullnessReportEnabledForAcquisitionError.getValue().booleanValue();
    }

    public long getBufferFullnessReportIntervalMillis() {
        return this.mBufferFullnessReportIntervalMillis.getValue().longValue();
    }

    public int getContinuousBufferFullnessReportCount() {
        return this.mConsecutiveBufferFullnessReportCount.getValue().intValue();
    }

    public long getCriticalLowLevelOfBufferFullness() {
        return this.mCriticalLowLevelOfBufferFullness.getValue().longValue();
    }
}

package com.amazon.avod.drm.autorecovery;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.util.SlidingWindowConfig;
import com.amazon.avod.util.DLog;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class DrmAutoRecoveryPolicy {
    private final AutoResetConfig mAutoResetConfig;
    private final DrmAutoRecoveryExceptionTracker mExceptionTracker;
    private final Object mExceptionTrackerMutex;

    private static class SingletonHolder {
        private static final DrmAutoRecoveryPolicy INSTANCE = new DrmAutoRecoveryPolicy(AutoResetConfig.getInstance());

        private SingletonHolder() {
        }
    }

    public static DrmAutoRecoveryPolicy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    DrmAutoRecoveryPolicy(AutoResetConfig autoResetConfig) {
        this(autoResetConfig, new DrmAutoRecoveryExceptionTracker());
    }

    DrmAutoRecoveryPolicy(AutoResetConfig autoResetConfig, DrmAutoRecoveryExceptionTracker drmAutoRecoveryExceptionTracker) {
        this.mExceptionTrackerMutex = new Object();
        this.mAutoResetConfig = autoResetConfig;
        this.mExceptionTracker = drmAutoRecoveryExceptionTracker;
    }

    public boolean shouldReset() {
        boolean zIsThresholdBreached;
        synchronized (this.mExceptionTrackerMutex) {
            zIsThresholdBreached = this.mExceptionTracker.isThresholdBreached();
            DLog.logf("Attempting to reset DRM subsystem, Should Reset = %b", Boolean.valueOf(zIsThresholdBreached));
        }
        return zIsThresholdBreached;
    }

    public void notifyException(MediaException mediaException) {
        synchronized (this.mExceptionTrackerMutex) {
            this.mExceptionTracker.reportException(mediaException);
        }
    }

    public boolean shouldHandle(MediaException mediaException) {
        boolean zIsTracked = this.mExceptionTracker.isTracked(mediaException);
        boolean z = this.mAutoResetConfig.getAutoResetCount() < this.mAutoResetConfig.getDrmAutoRecoveryMaxResets();
        boolean z2 = zIsTracked && z;
        DLog.logf("Exception from DRM subsystem, ExceptionErrorCode: %d, shouldHandle = %b. istracked: %b isResetCountLessThanMax: %b", Integer.valueOf(mediaException.getIntegerErrorCode()), Boolean.valueOf(z2), Boolean.valueOf(zIsTracked), Boolean.valueOf(z));
        return z2;
    }

    public void incrementAutoResetCount() {
        this.mAutoResetConfig.incrementAutoResetCount();
    }

    public boolean getDrmAutoRecoveryEnabled(boolean z) {
        boolean drmAutoRecoveryEnabled = this.mAutoResetConfig.getDrmAutoRecoveryEnabled();
        boolean z2 = drmAutoRecoveryEnabled && z;
        DLog.logf("getDrmAutoRecoveryEnabled = %b hasCocnnection: %b AutoRecoveryEnabled: %b", Boolean.valueOf(z2), Boolean.valueOf(z), Boolean.valueOf(drmAutoRecoveryEnabled));
        return z2;
    }

    static class AutoResetConfig extends MediaConfigBase implements SlidingWindowConfig {
        private static final AutoResetConfig INSTANCE = new AutoResetConfig();
        private ConfigurationValue<Integer> mAutoResetCount;
        private TimeConfigurationValue mAutoResetLastTimestamp;
        private final ConfigurationValue<Boolean> mDrmAutoRecoveryEnabled;
        private final ConfigurationValue<Set<String>> mDrmAutoRecoveryErrorSet = newSemicolonDelimitedStringSetConfigurationValue("drm_autoRecoveryErrorSet", new String[0]);
        private final TimeConfigurationValue mDrmAutoRecoveryMaxResetWindowLength;
        private final ConfigurationValue<Integer> mDrmAutoRecoveryMaxResets;
        private final ConfigurationValue<Integer> mDrmAutoRecoveryResetThreshold;
        private final TimeConfigurationValue mDrmAutoRecoveryWindowLength;

        public static AutoResetConfig getInstance() {
            return INSTANCE;
        }

        private AutoResetConfig() {
            ConfigType configType = ConfigType.INTERNAL;
            this.mAutoResetCount = newIntConfigValue("drm_autoRecoveryResetCount", 0, configType);
            TimeSpan timeSpan = TimeSpan.ZERO;
            TimeUnit timeUnit = TimeUnit.HOURS;
            this.mAutoResetLastTimestamp = newTimeConfigurationValue("drm_autoRecoveryResetLastTimestamp", timeSpan, timeUnit, configType);
            this.mDrmAutoRecoveryWindowLength = newTimeConfigurationValue("drm_autoRecoveryWindowLengthMinutes", TimeSpan.fromMinutes(5L), TimeUnit.MINUTES);
            this.mDrmAutoRecoveryMaxResets = newIntConfigValue("drm_autoRecoveryMaxResets", 3);
            this.mDrmAutoRecoveryMaxResetWindowLength = newTimeConfigurationValue("drm_autoRecoveryMaxResetWindowLengthHours", TimeSpan.fromHours(24L), timeUnit);
            this.mDrmAutoRecoveryResetThreshold = newIntConfigValue("drm_autoRecoveryResetThreshold", 1);
            this.mDrmAutoRecoveryEnabled = newBooleanConfigValue("drm_autoRecoveryEnabled", false);
        }

        public int getAutoResetCount() {
            if (TimeSpan.difference(TimeSpan.now(), getAutoResetLastTimestamp()).greaterThan(getDrmAutoRecoveryMaxResetWindowLength())) {
                reInitializeAutoResetConfigs();
                return 0;
            }
            return this.mAutoResetCount.getValue().intValue();
        }

        private void reInitializeAutoResetConfigs() {
            reInitializeAutoResetCount();
            setAutoResetLastTimestamp(TimeSpan.ZERO);
        }

        public void incrementAutoResetCount() {
            this.mAutoResetCount.updateValue(Integer.valueOf(getAutoResetCount() + 1));
            setAutoResetLastTimestamp(TimeSpan.now());
        }

        private void reInitializeAutoResetCount() {
            this.mAutoResetCount.updateValue(0);
        }

        public TimeSpan getAutoResetLastTimestamp() {
            return this.mAutoResetLastTimestamp.getValue();
        }

        private void setAutoResetLastTimestamp(TimeSpan timeSpan) {
            this.mAutoResetLastTimestamp.updateValue(timeSpan);
        }

        public Set<Integer> getDrmAutoRecoveryErrorConfig() {
            return createIntegerSetFromConfigurationValueStringSet(this.mDrmAutoRecoveryErrorSet);
        }

        public int getDrmAutoRecoveryMaxResets() {
            return this.mDrmAutoRecoveryMaxResets.getValue().intValue();
        }

        public TimeSpan getDrmAutoRecoveryMaxResetWindowLength() {
            return this.mDrmAutoRecoveryMaxResetWindowLength.getValue();
        }

        public boolean getDrmAutoRecoveryEnabled() {
            return this.mDrmAutoRecoveryEnabled.getValue().booleanValue();
        }

        @Override // com.amazon.avod.media.playback.util.SlidingWindowConfig
        public TimeSpan getSlidingWindowLength() {
            return this.mDrmAutoRecoveryWindowLength.getValue();
        }

        @Override // com.amazon.avod.media.playback.util.SlidingWindowConfig
        public int getSlidingWindowThreshold() {
            return this.mDrmAutoRecoveryResetThreshold.getValue().intValue();
        }
    }
}

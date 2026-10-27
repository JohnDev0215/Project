package com.amazon.avod.playback.config;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class LiveKeyRotationConfig extends ServerConfigBase {
    private final ConfigurationValue<Long> mMaxLiveKeyRotationRestartDelayMillis;

    private static final class SingletonHolder {
        private static final LiveKeyRotationConfig INSTANCE = new LiveKeyRotationConfig();

        private SingletonHolder() {
        }
    }

    public static final LiveKeyRotationConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private LiveKeyRotationConfig() {
        this.mMaxLiveKeyRotationRestartDelayMillis = newLongConfigValue("liveKeyRotation_maxLiveKeyRotationRestartDelayMillis", 7000L);
    }

    public long getMaxLiveKeyRotationRestartDelayMillis() {
        return this.mMaxLiveKeyRotationRestartDelayMillis.getValue().longValue();
    }
}

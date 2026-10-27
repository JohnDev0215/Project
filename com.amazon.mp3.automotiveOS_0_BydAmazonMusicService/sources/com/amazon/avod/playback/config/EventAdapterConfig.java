package com.amazon.avod.playback.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class EventAdapterConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mShouldJitterClosePlayerSignal;
    private final ConfigurationValue<Boolean> mShouldJitterStaticManifestEnd;

    private static final class SingletonHolder {
        private static final EventAdapterConfig INSTANCE = new EventAdapterConfig();

        private SingletonHolder() {
        }
    }

    public static final EventAdapterConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private EventAdapterConfig() {
        this.mShouldJitterClosePlayerSignal = newBooleanConfigValue("live_shouldJitterClosePlayerSignal", false);
        this.mShouldJitterStaticManifestEnd = newBooleanConfigValue("live_shouldJitterStaticManifestEnd", false);
    }

    public boolean shouldJitterClosePlayerSignal() {
        return this.mShouldJitterClosePlayerSignal.getValue().booleanValue();
    }

    public boolean shouldJitterStaticManifestEnd() {
        return this.mShouldJitterStaticManifestEnd.getValue().booleanValue();
    }
}

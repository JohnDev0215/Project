package com.amazon.avod.session;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SessionConfig extends ServerConfigBase {
    private ConfigurationValue<String> mCachedSessionId;
    private ConfigurationValue<Long> mLastUpdatedTimeMs;
    private ConfigurationValue<Long> mSessionIdSyncIntervalMs;

    private static class SingletonHolder {
        public static final SessionConfig INSTANCE = new SessionConfig();

        private SingletonHolder() {
        }
    }

    @Nonnull
    public static SessionConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    SessionConfig() {
        ConfigType configType = ConfigType.INTERNAL;
        this.mCachedSessionId = newStringConfigValue("CachedSessionId", null, configType);
        this.mSessionIdSyncIntervalMs = newLongConfigValue("SessionIdSyncIntervalMs", TimeUnit.DAYS.toMillis(1L), ConfigType.SERVER);
        this.mLastUpdatedTimeMs = newLongConfigValue("SessionIdLastUpdatedTimeMs", -1L, configType);
    }

    @Nullable
    public String getCachedSessionId() {
        return this.mCachedSessionId.getValue();
    }

    public void setCachedSessionId(@Nullable String str) {
        this.mCachedSessionId.updateValue(str);
    }
}

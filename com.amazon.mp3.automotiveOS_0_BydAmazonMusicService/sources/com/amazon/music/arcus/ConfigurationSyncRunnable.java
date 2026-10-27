package com.amazon.music.arcus;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigurationSyncRunnable implements Runnable {
    private final ConfigurationSync configurationSync;

    public ConfigurationSyncRunnable(ConfigurationSync configurationSync) {
        this.configurationSync = (ConfigurationSync) Validate.notNull(configurationSync);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.configurationSync.refreshConfig();
    }
}

package com.amazon.music.config;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigManager {
    private ConfigManager() {
    }

    public static File getCachedRemoteConfigFile(Context context) {
        return new File(context.getCacheDir(), "remote_config.json");
    }

    static synchronized void reload() {
    }
}

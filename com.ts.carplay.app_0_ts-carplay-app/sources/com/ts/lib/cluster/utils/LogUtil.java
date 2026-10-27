package com.ts.lib.cluster.utils;

import android.os.SystemProperties;
import com.ts.lib.common.Logger;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    private static final String PROPERTY_LOGGING_ENABLED = "persist.interact.log.enable";
    private static final String TAG = "INTERACT.MANAGER";

    public static void logI(String tag, String msg) {
        if (isLoggable()) {
            Logger.info(TAG, tag + ":" + msg);
        }
    }

    public static void logD(String tag, String msg) {
        if (isLoggable()) {
            Logger.debug(TAG, tag + ":" + msg);
        }
    }

    public static void logW(String tag, String msg) {
        Logger.warning(TAG, tag + ":" + msg);
    }

    public static void logE(String tag, String msg) {
        Logger.error(TAG, tag + ":" + msg);
    }

    private static boolean isLoggable() {
        return SystemProperties.getBoolean(PROPERTY_LOGGING_ENABLED, true);
    }
}

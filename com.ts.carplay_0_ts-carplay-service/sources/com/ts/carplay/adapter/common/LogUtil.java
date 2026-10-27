package com.ts.carplay.adapter.common;

import android.os.SystemProperties;
import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class LogUtil {
    private static final String APP_TAG = "CarPlay";
    private static boolean mAggregateLog = true;
    private static final Object ENABLE = new Object();
    private static final Object DISABLE = new Object();
    private static ConcurrentHashMap<String, Object> mEnableCache = new ConcurrentHashMap<>();

    public static void init() {
    }

    public static void verbose(String tag, String msg) {
        if (mAggregateLog) {
            Log.v("CarPlay", tag + " " + msg);
            return;
        }
        Log.v(tag, msg);
    }

    public static void verbose(String tag, Exception exc) {
        verbose(tag, prepareStackTrace(exc));
    }

    public static void debug(String tag, String msg) {
        if (mAggregateLog) {
            Log.d("CarPlay", tag + " " + msg);
            return;
        }
        Log.d(tag, msg);
    }

    public static void debug(String tag, Exception exc) {
        debug(tag, prepareStackTrace(exc));
    }

    public static void info(String tag, String msg, Throwable throwable) {
        if (mAggregateLog) {
            Log.i("CarPlay", tag + " " + msg, throwable);
            return;
        }
        Log.i(tag, msg, throwable);
    }

    public static void info(String tag, String msg) {
        if (mAggregateLog) {
            Log.i("CarPlay", tag + " " + msg);
            return;
        }
        Log.i(tag, msg);
    }

    public static void info(String tag, Exception exc) {
        info(tag, prepareStackTrace(exc));
    }

    public static void warning(String tag, String msg) {
        if (mAggregateLog) {
            Log.w("CarPlay", tag + " " + msg);
            return;
        }
        Log.w(tag, msg);
    }

    public static void warning(String tag, String msg, Throwable throwable) {
        if (mAggregateLog) {
            Log.w("CarPlay", tag + " " + msg, throwable);
            return;
        }
        Log.w(tag, msg, throwable);
    }

    public static void warning(String tag, Exception exc) {
        warning(tag, prepareStackTrace(exc));
    }

    public static void error(String tag, String msg, Throwable throwable) {
        if (mAggregateLog) {
            Log.e("CarPlay", tag + " " + msg, throwable);
            return;
        }
        Log.e(tag, msg, throwable);
    }

    public static void error(String tag, String msg) {
        if (mAggregateLog) {
            Log.e("CarPlay", tag + " " + msg);
            return;
        }
        Log.e(tag, msg);
    }

    public static void error(String tag, Exception exc) {
        error(tag, prepareStackTrace(exc));
    }

    public static String prepareStackTrace(Exception exc) {
        if (exc == null) {
            return null;
        }
        return Log.getStackTraceString(exc);
    }

    public static void verboseInDebugMode(String featureKey, String tag, String msg) {
        if (isFeatureEnabled(featureKey)) {
            verbose(tag, msg);
        }
    }

    public static void debugInDebugMode(String featureKey, String tag, String msg) {
        if (isFeatureEnabled(featureKey)) {
            debug(tag, msg);
        }
    }

    public static void infoInDebugMode(String featureKey, String tag, String msg) {
        if (isFeatureEnabled(featureKey)) {
            info(tag, msg);
        }
    }

    public static void warningInDebugMode(String featureKey, String tag, String msg) {
        if (isFeatureEnabled(featureKey)) {
            warning(tag, msg);
        }
    }

    public static void errorInDebugMode(String featureKey, String tag, String msg) {
        if (isFeatureEnabled(featureKey)) {
            error(tag, msg);
        }
    }

    private static boolean isFeatureEnabled(String featureKey) {
        Object result = mEnableCache.get(featureKey);
        if (result == null) {
            if (SystemProperties.getBoolean(featureKey, false)) {
                result = ENABLE;
            } else {
                result = DISABLE;
            }
            mEnableCache.put(featureKey, result);
            error("CarPlay", "getSystemProperties, featureKey=" + featureKey + ",result=" + result);
        }
        return result == ENABLE;
    }
}

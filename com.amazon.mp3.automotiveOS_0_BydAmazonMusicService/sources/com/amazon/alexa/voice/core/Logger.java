package com.amazon.alexa.voice.core;

import android.util.Log;
import com.google.android.gms.wearable.WearableStatusCodes;

/* JADX INFO: loaded from: classes2.dex */
public final class Logger {
    private static Level level = Level.NONE;

    public enum Level {
        NONE,
        BASIC,
        VERBOSE,
        DEBUG
    }

    private Logger() {
    }

    public static void info(String str) {
        log(Level.BASIC, str);
    }

    public static void info(String str, Object... objArr) {
        info(String.format(str, objArr));
    }

    public static void error(String str, Throwable th) {
        log(Level.DEBUG, str, th);
    }

    public static void verbose(String str) {
        log(Level.VERBOSE, str);
    }

    public static void verbose(String str, Object... objArr) {
        verbose(String.format(str, objArr));
    }

    public static void debug(String str) {
        log(Level.DEBUG, str);
    }

    public static void debug(String str, Object... objArr) {
        debug(String.format(str, objArr));
    }

    public static void setLevel(Level level2) {
        level = level2;
    }

    public static void log(Level level2, String str) {
        log(level2, str, null);
    }

    private static boolean shouldLog(Level level2) {
        return level != Level.NONE && level.compareTo(level2) >= 0;
    }

    public static void log(Level level2, String str, Throwable th) {
        while (str.length() > 4000) {
            logLine(level2, str.substring(0, WearableStatusCodes.TARGET_NODE_NOT_CONNECTED), th);
            str = str.substring(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED);
        }
        if (str.length() > 0) {
            logLine(level2, str, th);
        }
    }

    private static void logLine(Level level2, String str, Throwable th) {
        if (shouldLog(level2)) {
            if (level2 == Level.BASIC) {
                Log.i("AlexaVoice", str);
                return;
            }
            if (level2 == Level.VERBOSE) {
                Log.v("AlexaVoice", str);
            } else if (level2 == Level.DEBUG) {
                if (th == null) {
                    Log.d("AlexaVoice", str);
                } else {
                    Log.e("AlexaVoice", str, th);
                }
            }
        }
    }
}

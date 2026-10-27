package com.amazon.mls.config.internal.core.logcat;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class LogcatProxy {
    public static void log(String str) {
        Log.d("MobileLoggingSuite", str);
    }

    public static void log(String str, Throwable th) {
        Log.d("MobileLoggingSuite", str, th);
    }

    /* JADX INFO: renamed from: com.amazon.mls.config.internal.core.logcat.LogcatProxy$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel;

        static {
            int[] iArr = new int[LogLevel.values().length];
            $SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel = iArr;
            try {
                iArr[LogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel[LogLevel.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel[LogLevel.DEBUG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel[LogLevel.INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void log(LogLevel logLevel, String str) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel[logLevel.ordinal()];
        if (i == 1) {
            Log.e("MobileLoggingSuite", str);
            return;
        }
        if (i == 2) {
            Log.w("MobileLoggingSuite", str);
        } else if (i == 3) {
            Log.d("MobileLoggingSuite", str);
        } else {
            Log.i("MobileLoggingSuite", str);
        }
    }

    public static void log(LogLevel logLevel, String str, Throwable th) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mls$config$internal$core$logcat$LogLevel[logLevel.ordinal()];
        if (i == 1) {
            Log.e("MobileLoggingSuite", str, th);
            return;
        }
        if (i == 2) {
            Log.w("MobileLoggingSuite", str, th);
        } else if (i == 3) {
            Log.d("MobileLoggingSuite", str, th);
        } else {
            Log.i("MobileLoggingSuite", str, th);
        }
    }

    public static void logForAutomation(String str) {
        log(str);
    }
}

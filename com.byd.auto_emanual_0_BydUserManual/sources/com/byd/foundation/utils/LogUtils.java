package com.byd.foundation.utils;

import android.text.TextUtils;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    private static final String BASE_TAG = "CAR.LOG";
    private static boolean DEBUG = true;
    private static boolean ERROR = true;
    private static boolean INFO = true;
    private static boolean VERBOSE = true;
    private static boolean WARN = true;

    public static void setDebugEnable(boolean z) {
        DEBUG = z;
        VERBOSE = z;
        WARN = z;
        INFO = z;
        ERROR = z;
    }

    public static void setDebugEnable(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        DEBUG = z;
        VERBOSE = z2;
        WARN = z3;
        INFO = z4;
        ERROR = z5;
    }

    public static void i(String str, String str2) {
        if (INFO) {
            log(str, 4, str2, null);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (INFO) {
            log(str, 4, str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (DEBUG) {
            log(str, 3, str2, null);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (DEBUG) {
            log(str, 3, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (WARN) {
            log(str, 5, str2, null);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (WARN) {
            log(str, 5, str2, th);
        }
    }

    public static void v(String str, String str2) {
        if (VERBOSE) {
            log(str, 2, str2, null);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (VERBOSE) {
            log(str, 2, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (ERROR) {
            log(str, 6, str2, null);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (ERROR) {
            log(str, 6, str2, th);
        }
    }

    private static String generateTag(String str) {
        return TextUtils.isEmpty(str) ? BASE_TAG : str;
    }

    private static void log(String str, int i, String str2, Throwable th) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            Log.println(i, generateTag(str), stringWriter.toString());
            return;
        }
        Log.println(i, generateTag(str), str2);
    }
}

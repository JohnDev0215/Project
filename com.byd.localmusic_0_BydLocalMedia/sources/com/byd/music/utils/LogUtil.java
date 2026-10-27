package com.byd.music.utils;

import android.util.Log;
import com.blankj.utilcode.util.AppUtils;

/* JADX INFO: loaded from: classes5.dex */
public class LogUtil {
    public static final String COMMON_TAG = "BM_" + AppUtils.getAppVersionName();
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static final int INFO = 3;
    public static final int NOTHING = 6;
    public static final int VERBOSE = 1;
    public static final int WARN = 4;
    public static boolean isDebug = true;
    public static int level = 1;

    public static void setEnable(boolean z) {
        isDebug = z;
    }

    public static void v(String str, String str2) {
        if (level > 1 || !isDebug) {
            return;
        }
        Log.v(COMMON_TAG, str + "==" + str2);
    }

    public static void v(String str) {
        if (level > 1 || !isDebug) {
            return;
        }
        Log.v(COMMON_TAG, str);
    }

    public static void d(String str, String str2) {
        if (level > 2 || !isDebug) {
            return;
        }
        Log.d(COMMON_TAG, str + "===" + str2);
    }

    public static void d(String str) {
        if (level > 2 || !isDebug) {
            return;
        }
        Log.d(COMMON_TAG, str);
    }

    public static void i(String str, String str2) {
        if (level > 3 || !isDebug) {
            return;
        }
        Log.i(COMMON_TAG, str + "===" + str2);
    }

    public static void i(String str) {
        if (level > 3 || !isDebug) {
            return;
        }
        Log.i(COMMON_TAG, str);
    }

    public static void w(String str, String str2) {
        if (level > 4 || !isDebug) {
            return;
        }
        Log.w(COMMON_TAG, str + "===" + str2);
    }

    public static void w(String str) {
        if (level > 4 || !isDebug) {
            return;
        }
        Log.w(COMMON_TAG, str);
    }

    public static void e(String str, String str2) {
        if (level <= 5) {
            Log.e(COMMON_TAG, str + "===" + str2);
        }
    }

    public static void e(String str) {
        if (level <= 5) {
            Log.e(COMMON_TAG, str);
        }
    }
}

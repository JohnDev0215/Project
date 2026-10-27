package com.android.car.apps.common.log;

import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class L {
    private static final List<String> TYPE_LIST = Arrays.asList("eng", "userdebug");

    public static void v(String tag, String msg, Object... args) {
        if (Log.isLoggable(tag, 2) || TYPE_LIST.contains(Build.TYPE)) {
            Log.v(tag, String.format(msg, args));
        }
    }

    public static void d(String tag, String msg, Object... args) {
        if (Log.isLoggable(tag, 3) || TYPE_LIST.contains(Build.TYPE)) {
            Log.d(tag, String.format(msg, args));
        }
    }

    public static void i(String tag, String msg, Object... args) {
        if (Log.isLoggable(tag, 4) || TYPE_LIST.contains(Build.TYPE)) {
            Log.i(tag, String.format(msg, args));
        }
    }

    public static void w(String tag, String msg, Object... args) {
        if (Log.isLoggable(tag, 5) || TYPE_LIST.contains(Build.TYPE)) {
            Log.w(tag, String.format(msg, args));
        }
    }

    public static void e(String tag, String msg, Object... args) {
        if (Log.isLoggable(tag, 6) || TYPE_LIST.contains(Build.TYPE)) {
            Log.e(tag, String.format(msg, args));
        }
    }

    public static void e(String tag, Exception e, String msg, Object... args) {
        if (Log.isLoggable(tag, 6) || TYPE_LIST.contains(Build.TYPE)) {
            Log.e(tag, String.format(msg, args), e);
        }
    }
}

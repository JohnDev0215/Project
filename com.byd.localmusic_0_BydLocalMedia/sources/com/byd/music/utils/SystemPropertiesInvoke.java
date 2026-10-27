package com.byd.music.utils;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public class SystemPropertiesInvoke {
    private static final String TAG = "SystemPropertiesInvoke";
    private static Method getBooleanMethod;
    private static Method getIntMethod;
    private static Method getLongMethod;
    private static Method getStringMethod;

    public static long getLong(String str, long j) {
        try {
            if (getLongMethod == null) {
                getLongMethod = Class.forName("android.os.SystemProperties").getMethod("getLong", String.class, Long.TYPE);
            }
            return ((Long) getLongMethod.invoke(null, str, Long.valueOf(j))).longValue();
        } catch (Exception e) {
            LogUtil.e(TAG, "Platform error: " + e.toString());
            return j;
        }
    }

    public static int getInt(String str, int i) {
        try {
            if (getIntMethod == null) {
                getIntMethod = Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE);
            }
            return ((Integer) getIntMethod.invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Exception e) {
            LogUtil.e(TAG, "Platform error: " + e.toString());
            return i;
        }
    }

    public static String getString(String str, String str2) {
        try {
            if (getStringMethod == null) {
                getStringMethod = Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class);
            }
            return (String) getStringMethod.invoke(null, str, str2);
        } catch (Exception e) {
            LogUtil.e(TAG, "Platform error: " + e.toString());
            return str2;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        try {
            if (getBooleanMethod == null) {
                getBooleanMethod = Class.forName("android.os.SystemProperties").getMethod("getBoolean", String.class, Boolean.TYPE);
            }
            return ((Boolean) getBooleanMethod.invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception e) {
            LogUtil.e(TAG, "Platform error: " + e.toString());
            return z;
        }
    }
}

package com.byd.common.countrycode;

/* JADX INFO: loaded from: classes.dex */
public class SystemPropertiesUtils {
    public static final String KEY_VEHICLE_TYPE = "ro.vehicle.type";
    private static final String TAG = "NEU_SystemProperties";
    public static final String pano_start = "sys.byd.pano_start";

    public static String get(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
            return null;
        }
    }

    public static String get(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
            return null;
        }
    }

    public static int getInt(String str, int i) {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
            return 0;
        }
    }

    public static long getLong(String str, long j) {
        try {
            return ((Long) Class.forName("android.os.SystemProperties").getMethod("getLong", String.class, Long.TYPE).invoke(null, str, Long.valueOf(j))).longValue();
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
            return 0L;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        try {
            return ((Boolean) Class.forName("android.os.SystemProperties").getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
            return false;
        }
    }

    public static void set(String str, String str2) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
        }
    }

    public static void addChangeCallback(Runnable runnable) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("addChangeCallback", Runnable.class).invoke(null, runnable);
        } catch (Exception unused) {
            LogUtils.e(TAG, "read SystemProperties error");
        }
    }

    public static String getVehicleType() {
        return get(KEY_VEHICLE_TYPE);
    }
}

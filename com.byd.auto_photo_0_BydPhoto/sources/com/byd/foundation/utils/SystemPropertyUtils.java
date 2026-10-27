package com.byd.foundation.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class SystemPropertyUtils {
    private static final String TAG = "SystemPropertyUtil";

    public static String get(Context context, String str) {
        String message;
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, str);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            LogUtils.e(TAG, message);
            return "";
        } catch (Exception e2) {
            message = e2.getMessage();
            LogUtils.e(TAG, message);
            return "";
        }
    }

    public static String get(Context context, String str, String str2) {
        String message;
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) clsLoadClass.getMethod("get", String.class, String.class).invoke(clsLoadClass, str, str2);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            LogUtils.e(TAG, message);
            return str2;
        } catch (Exception e2) {
            message = e2.getMessage();
            LogUtils.e(TAG, message);
            return str2;
        }
    }

    public static Boolean getBoolean(Context context, String str, boolean z) {
        String message;
        Boolean boolValueOf = Boolean.valueOf(z);
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Boolean) clsLoadClass.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(clsLoadClass, str, new Boolean(z));
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            LogUtils.e(TAG, message);
            return boolValueOf;
        } catch (Exception e2) {
            boolValueOf = Boolean.valueOf(z);
            message = e2.getMessage();
            LogUtils.e(TAG, message);
            return boolValueOf;
        }
    }

    public static Integer getInt(Context context, String str, int i) {
        String message;
        Integer numValueOf = Integer.valueOf(i);
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, str, new Integer(i));
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            LogUtils.e(TAG, message);
            return numValueOf;
        } catch (Exception e2) {
            numValueOf = Integer.valueOf(i);
            message = e2.getMessage();
            LogUtils.e(TAG, message);
            return numValueOf;
        }
    }

    public static Long getLong(Context context, String str, long j) {
        String message;
        Long lValueOf = Long.valueOf(j);
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Long) clsLoadClass.getMethod("getLong", String.class, Long.TYPE).invoke(clsLoadClass, str, new Long(j));
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            LogUtils.e(TAG, message);
            return lValueOf;
        } catch (Exception e2) {
            lValueOf = Long.valueOf(j);
            message = e2.getMessage();
            LogUtils.e(TAG, message);
            return lValueOf;
        }
    }

    public static void set(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getMethod("set", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        }
    }
}

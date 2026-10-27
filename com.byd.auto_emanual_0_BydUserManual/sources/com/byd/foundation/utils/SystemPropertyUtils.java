package com.byd.foundation.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class SystemPropertyUtils {
    private static final String TAG = "SystemPropertyUtil";

    public static String get(Context context, String str) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, str);
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, e.getMessage());
            return "";
        } catch (Exception e2) {
            LogUtils.e(TAG, e2.getMessage());
            return "";
        }
    }

    public static String get(Context context, String str, String str2) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) clsLoadClass.getMethod("get", String.class, String.class).invoke(clsLoadClass, str, str2);
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, e.getMessage());
            return str2;
        } catch (Exception e2) {
            LogUtils.e(TAG, e2.getMessage());
            return str2;
        }
    }

    public static Integer getInt(Context context, String str, int i) {
        Integer numValueOf = Integer.valueOf(i);
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, str, new Integer(i));
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, e.getMessage());
            return numValueOf;
        } catch (Exception e2) {
            Integer numValueOf2 = Integer.valueOf(i);
            LogUtils.e(TAG, e2.getMessage());
            return numValueOf2;
        }
    }

    public static Long getLong(Context context, String str, long j) {
        Long lValueOf = Long.valueOf(j);
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Long) clsLoadClass.getMethod("getLong", String.class, Long.TYPE).invoke(clsLoadClass, str, new Long(j));
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, e.getMessage());
            return lValueOf;
        } catch (Exception e2) {
            Long lValueOf2 = Long.valueOf(j);
            LogUtils.e(TAG, e2.getMessage());
            return lValueOf2;
        }
    }

    public static Boolean getBoolean(Context context, String str, boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Boolean) clsLoadClass.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(clsLoadClass, str, new Boolean(z));
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, e.getMessage());
            return boolValueOf;
        } catch (Exception e2) {
            Boolean boolValueOf2 = Boolean.valueOf(z);
            LogUtils.e(TAG, e2.getMessage());
            return boolValueOf2;
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

package com.byd.systemsettings.system;

/* JADX INFO: loaded from: classes2.dex */
public class BydSystemProperties {
    public static final String VERSION_TYPE = "ro.build.type";
    public static final String VERSION_USER = "user";
    public static final String VERSION_USER_DEBUG = "userdebug";

    public static String get(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
            } catch (Exception e8) {
                e8.printStackTrace();
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static void set(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getMethod("set", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }
}

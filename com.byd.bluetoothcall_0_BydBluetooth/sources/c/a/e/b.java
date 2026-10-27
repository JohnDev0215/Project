package c.a.e;

import android.os.SystemProperties;

/* JADX INFO: compiled from: SystemUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static String a(String str, String str2) {
        try {
            return SystemProperties.get(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static String b(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "unknown");
            } catch (Exception e2) {
                e2.printStackTrace();
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static void c(String str, String str2) {
        SystemProperties.set(str, str2);
    }
}

package android.car.utils;

/* JADX INFO: loaded from: classes.dex */
public final class SystemPropertiesUtils {
    private static final String CLASS_NAME = "android.os.SystemProperties";

    public static String getProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName(CLASS_NAME);
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            Logs.e("error ", "getProperty");
            return str2;
        }
    }

    public static void setProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName(CLASS_NAME);
            cls.getMethod("set", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            Logs.e("error ", "setProperty");
        }
    }
}

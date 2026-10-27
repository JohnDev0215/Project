package android.os;

/* JADX INFO: loaded from: classes.dex */
public class SystemProperties {
    public static String get(String str, String str2) {
        return System.getProperty(str, str2);
    }
}

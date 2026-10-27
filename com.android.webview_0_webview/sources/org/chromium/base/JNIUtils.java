package org.chromium.base;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JNIUtils {
    public static Boolean a;
    public static ClassLoader b;

    public static Object getClassLoader() {
        ClassLoader classLoader = b;
        return classLoader == null ? JNIUtils.class.getClassLoader() : classLoader;
    }

    public static boolean isSelectiveJniRegistrationEnabled() {
        if (a == null) {
            a = Boolean.FALSE;
        }
        return a.booleanValue();
    }
}

package org.chromium.base;

import dalvik.system.BaseDexClassLoader;
import defpackage.AbstractC0279Pk;
import defpackage.C0683dt;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BundleUtils {
    public static Boolean a;

    public static String getNativeLibraryPath(String str) {
        C0683dt c0683dtX = C0683dt.x();
        try {
            String strFindLibrary = ((BaseDexClassLoader) Is.a.getClassLoader()).findLibrary(str);
            c0683dtX.close();
            return strFindLibrary;
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public static boolean isBundleForNative() {
        return false;
    }
}

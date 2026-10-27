package org.chromium.base;

import defpackage.fa1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class JNIUtils {
    public static Boolean a;
    public static ClassLoader b;

    @CalledByNative
    public static ClassLoader getSplitClassLoader(String str) {
        if (!str.isEmpty()) {
            boolean zD = BundleUtils.d(str);
            fa1.h("JNIUtils", "Init JNI Classloader for %s. isInstalled=%b", str, Boolean.valueOf(zD));
            if (zD) {
                return BundleUtils.b(str);
            }
        }
        ClassLoader classLoader = b;
        return classLoader != null ? classLoader : JNIUtils.class.getClassLoader();
    }

    @CalledByNative
    public static boolean isSelectiveJniRegistrationEnabled() {
        if (a == null) {
            a = Boolean.FALSE;
        }
        return a.booleanValue();
    }
}

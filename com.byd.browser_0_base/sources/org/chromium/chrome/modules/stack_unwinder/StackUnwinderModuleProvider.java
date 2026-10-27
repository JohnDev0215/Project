package org.chromium.chrome.modules.stack_unwinder;

import defpackage.gr2;
import defpackage.hi1;
import defpackage.oj2;
import defpackage.va3;
import org.chromium.base.BundleUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class StackUnwinderModuleProvider {
    @CalledByNative
    public static void ensureNativeLoaded() {
        hi1 hi1Var = oj2.a;
        if (hi1Var.f) {
            return;
        }
        Object obj = BundleUtils.a;
        hi1Var.f = true;
    }

    @CalledByNative
    public static long getCreateLibunwindstackUnwinderFunction() {
        return ((gr2) oj2.a.a()).b();
    }

    @CalledByNative
    public static long getCreateMemoryRegionsMapFunction() {
        return ((gr2) oj2.a.a()).c();
    }

    @CalledByNative
    public static long getCreateNativeUnwinderFunction() {
        return ((gr2) oj2.a.a()).a();
    }

    @CalledByNative
    public static void installModule() {
        hi1 hi1Var = oj2.a;
        va3 va3Var = new va3();
        try {
            hi1Var.b().c("stack_unwinder");
            va3Var.close();
        } catch (Throwable th) {
            try {
                va3Var.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public static boolean isModuleInstalled() {
        return oj2.a.d();
    }
}

package org.chromium.chrome.modules.dev_ui;

import defpackage.hi1;
import defpackage.z7;
import org.chromium.base.BundleUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class DevUiModuleProvider {
    @CalledByNative
    public static void ensureNativeLoaded() {
        hi1 hi1Var = z7.i;
        if (hi1Var.f) {
            return;
        }
        Object obj = BundleUtils.a;
        hi1Var.f = true;
    }

    @CalledByNative
    public static void installModule(DevUiInstallListener devUiInstallListener) {
        z7.i.c(devUiInstallListener);
    }

    @CalledByNative
    public static boolean isModuleInstalled() {
        return z7.i.d();
    }
}

package org.chromium.chrome.browser;

import defpackage.j6;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppHooks {
    public static j6 a;

    @CalledByNative
    public static AppHooks get() {
        if (a == null) {
            a = new j6();
        }
        return a;
    }
}

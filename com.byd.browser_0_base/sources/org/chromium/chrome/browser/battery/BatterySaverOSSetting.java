package org.chromium.chrome.browser.battery;

import defpackage.i32;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverOSSetting {
    @CalledByNative
    public static boolean isBatterySaverEnabled() {
        if (i32.e == null) {
            i32.e = new i32();
        }
        return i32.e.d;
    }
}

package org.chromium.chrome.browser.sharing;

import android.telephony.TelephonyManager;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class SharingJNIBridge {
    @CalledByNative
    public static boolean isTelephonySupported() {
        return ((TelephonyManager) xz.a.getSystemService("phone")).getPhoneType() != 0;
    }
}

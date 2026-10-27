package org.chromium.content.browser;

import org.chromium.net.ProxyChangeListener;

/* JADX INFO: loaded from: classes3.dex */
public class ContentViewStatics {
    private static native String nativeFindAddress(String str);

    private static native void nativeSetWebKitSharedTimersSuspended(boolean z);

    public static String findAddress(String str) {
        if (str == null) {
            throw new NullPointerException("addr is null");
        }
        String strNativeFindAddress = nativeFindAddress(str);
        if (strNativeFindAddress == null || strNativeFindAddress.isEmpty()) {
            return null;
        }
        return strNativeFindAddress;
    }

    public static void setWebKitSharedTimersSuspended(boolean z) {
        nativeSetWebKitSharedTimersSuspended(z);
    }

    public static void enablePlatformNotifications() {
        ProxyChangeListener.setEnabled(true);
    }

    public static void disablePlatformNotifications() {
        ProxyChangeListener.setEnabled(false);
    }
}

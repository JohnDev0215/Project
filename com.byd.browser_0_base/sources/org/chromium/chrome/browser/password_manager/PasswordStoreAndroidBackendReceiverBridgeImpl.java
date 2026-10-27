package org.chromium.chrome.browser.password_manager;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordStoreAndroidBackendReceiverBridgeImpl {
    public long a;

    public PasswordStoreAndroidBackendReceiverBridgeImpl(long j) {
        this.a = j;
    }

    @CalledByNative
    public static PasswordStoreAndroidBackendReceiverBridgeImpl create(long j) {
        return new PasswordStoreAndroidBackendReceiverBridgeImpl(j);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }
}

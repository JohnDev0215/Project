package org.chromium.chrome.browser.password_manager;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordSettingsUpdaterReceiverBridge {
    public long a;

    public PasswordSettingsUpdaterReceiverBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static PasswordSettingsUpdaterReceiverBridge create(long j) {
        Object obj = ThreadUtils.a;
        return new PasswordSettingsUpdaterReceiverBridge(j);
    }

    @CalledByNative
    public final void destroy() {
        Object obj = ThreadUtils.a;
        this.a = 0L;
    }
}

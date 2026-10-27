package org.chromium.chrome.browser.password_manager;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordManagerLifecycleHelper {
    public static PasswordManagerLifecycleHelper b;
    public final b<Long> a = new b<>();

    @CalledByNative
    public static PasswordManagerLifecycleHelper getInstance() {
        if (b == null) {
            b = new PasswordManagerLifecycleHelper();
        }
        return b;
    }

    @CalledByNative
    public void registerObserver(long j) {
        this.a.a(Long.valueOf(j));
    }

    @CalledByNative
    public void unregisterObserver(long j) {
        this.a.c(Long.valueOf(j));
    }
}

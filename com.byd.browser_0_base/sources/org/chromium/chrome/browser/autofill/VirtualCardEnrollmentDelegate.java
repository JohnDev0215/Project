package org.chromium.chrome.browser.autofill;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class VirtualCardEnrollmentDelegate {
    public long a;

    public VirtualCardEnrollmentDelegate(long j) {
        this.a = j;
    }

    @CalledByNative
    public static VirtualCardEnrollmentDelegate create(long j) {
        return new VirtualCardEnrollmentDelegate(j);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.a = 0L;
    }
}

package org.chromium.chrome.modules.dev_ui;

import J.N;
import defpackage.p01;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class DevUiInstallListener implements p01 {
    public long e;

    @CalledByNative
    public DevUiInstallListener(long j) {
        this.e = j;
    }

    @Override // defpackage.p01
    public final void b() {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.MaWzS2R6(j, false);
    }

    @CalledByNative
    public final void onNativeDestroy() {
        this.e = 0L;
    }
}

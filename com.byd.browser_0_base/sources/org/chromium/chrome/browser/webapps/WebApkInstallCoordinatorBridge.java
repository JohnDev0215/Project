package org.chromium.chrome.browser.webapps;

import J.N;
import android.os.RemoteException;
import defpackage.fw0;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class WebApkInstallCoordinatorBridge {
    public long a = N.MpLwMZHi(this);
    public fw0 b;

    @CalledByNative
    public void onFinishedInstall(int i) {
        try {
            this.b.c(i);
        } catch (RemoteException unused) {
        }
        long j = this.a;
        if (j != 0) {
            N.MIO1A7Cv(j);
            this.a = 0L;
        }
    }
}

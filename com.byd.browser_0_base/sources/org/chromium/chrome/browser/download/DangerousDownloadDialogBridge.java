package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class DangerousDownloadDialogBridge {
    public long a;

    public DangerousDownloadDialogBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static DangerousDownloadDialogBridge create(long j) {
        return new DangerousDownloadDialogBridge(j);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public void showDialog(WindowAndroid windowAndroid, String str, String str2, long j, int i) {
        if (((Activity) windowAndroid.k().get()) == null) {
            N.MQlyjW3H(this.a, str);
        }
    }
}

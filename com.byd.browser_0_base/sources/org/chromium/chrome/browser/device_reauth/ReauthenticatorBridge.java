package org.chromium.chrome.browser.device_reauth;

import J.N;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class ReauthenticatorBridge {
    public final long a;
    public Callback<Boolean> b;

    public ReauthenticatorBridge(int i) {
        this.a = N.MZO11iFf(this, i);
    }

    @CalledByNative
    public void onReauthenticationCompleted(boolean z) {
        Callback<Boolean> callback = this.b;
        if (callback == null) {
            return;
        }
        callback.onResult(Boolean.valueOf(z));
        this.b = null;
    }
}

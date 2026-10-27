package org.chromium.content.browser.sms;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class SmsProviderGms {
    @CalledByNative
    public static SmsProviderGms create(long j, int i) {
        return null;
    }

    @CalledByNative
    public final void setClientAndWindow(Wrappers$SmsRetrieverClientWrapper wrappers$SmsRetrieverClientWrapper, WindowAndroid windowAndroid) {
        wrappers$SmsRetrieverClientWrapper.getClass();
    }

    @CalledByNative
    public final void destroy() {
    }

    @CalledByNative
    public void listen(WindowAndroid windowAndroid, boolean z) {
    }
}

package org.chromium.chrome.browser.push_messaging;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PushMessagingServiceObserver {
    @CalledByNative
    public static void onMessageHandled() {
        Object obj = ThreadUtils.a;
    }
}

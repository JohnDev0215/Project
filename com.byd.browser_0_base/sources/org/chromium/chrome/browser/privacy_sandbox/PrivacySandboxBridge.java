package org.chromium.chrome.browser.privacy_sandbox;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacySandboxBridge {
    @CalledByNative
    public static Topic createTopic(int i, int i2, String str) {
        return new Topic(i, i2, str);
    }
}

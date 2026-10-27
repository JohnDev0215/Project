package org.chromium.chrome.browser.touch_to_fill.data;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class WebAuthnCredential {
    public final String a;
    public final String b;

    public WebAuthnCredential(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @CalledByNative
    public String getId() {
        return this.b;
    }

    @CalledByNative
    public String getUsername() {
        return this.a;
    }
}

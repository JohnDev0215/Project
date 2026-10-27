package org.chromium.chrome.browser.autofill;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public final class SaveUpdateAddressProfilePromptController {
    public long a;

    public SaveUpdateAddressProfilePromptController(long j) {
        this.a = j;
    }

    @CalledByNative
    public static SaveUpdateAddressProfilePromptController create(long j) {
        return new SaveUpdateAddressProfilePromptController(j);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.a = 0L;
    }
}

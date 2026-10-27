package org.chromium.chrome.browser.browsing_data;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class UrlFilterBridge {
    public long a;

    public UrlFilterBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static UrlFilterBridge create(long j) {
        return new UrlFilterBridge(j);
    }
}

package org.chromium.chrome.browser.ui.android.webid.data;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class ClientIdMetadata {
    public final GURL a;
    public final GURL b;

    @CalledByNative
    public ClientIdMetadata(GURL gurl, GURL gurl2) {
        this.a = gurl;
        this.b = gurl2;
    }
}

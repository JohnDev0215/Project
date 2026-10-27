package org.chromium.chrome.browser.ui.android.webid.data;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class IdentityProviderMetadata {
    public final Integer a;
    public final Integer b;
    public final String c;
    public final GURL d;

    @CalledByNative
    public IdentityProviderMetadata(long j, long j2, String str, GURL gurl) {
        this.a = j == 2147483648L ? null : Integer.valueOf((int) j);
        this.b = j2 != 2147483648L ? Integer.valueOf((int) j2) : null;
        this.c = str;
        this.d = gurl;
    }
}

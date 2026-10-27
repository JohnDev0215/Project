package org.chromium.chrome.browser.feed.webfeed;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class WebFeedBridge$WebFeedMetadata {
    public final byte[] a;
    public final String b;
    public final GURL c;
    public final int d;
    public final int e;
    public final boolean f;
    public final GURL g;

    @CalledByNative
    public WebFeedBridge$WebFeedMetadata(byte[] bArr, String str, GURL gurl, int i, int i2, boolean z, GURL gurl2) {
        this.a = bArr;
        this.b = str;
        this.c = gurl;
        this.d = i;
        this.e = i2;
        this.f = z;
        this.g = gurl2;
    }
}

package org.chromium.chrome.browser.ui.android.webid.data;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class Account {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final GURL e;
    public final boolean f;

    @CalledByNative
    public Account(String str, String str2, String str3, String str4, GURL gurl, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = gurl;
        this.f = z;
    }
}

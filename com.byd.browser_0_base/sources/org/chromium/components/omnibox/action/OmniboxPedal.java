package org.chromium.components.omnibox.action;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class OmniboxPedal {
    public final int a;
    public final String b;

    public OmniboxPedal(int i, String str) {
        this.a = i;
        this.b = str;
    }

    @CalledByNative
    public static OmniboxPedal build(int i, String str, String str2, String str3, String str4, GURL gurl) {
        return new OmniboxPedal(i, str);
    }
}

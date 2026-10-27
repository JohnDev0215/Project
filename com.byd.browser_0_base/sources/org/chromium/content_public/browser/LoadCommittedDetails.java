package org.chromium.content_public.browser;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class LoadCommittedDetails {
    public final boolean a;
    public final boolean b;

    @CalledByNative
    public LoadCommittedDetails(int i, GURL gurl, boolean z, boolean z2, boolean z3, int i2) {
        this.a = z2;
        this.b = z3;
    }
}

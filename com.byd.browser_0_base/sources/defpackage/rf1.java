package defpackage;

import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MerchantTrustMessageContext.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rf1 {
    public final WebContents a;
    public final NavigationHandle b;

    public rf1(NavigationHandle navigationHandle, WebContents webContents) {
        this.b = navigationHandle;
        this.a = webContents;
    }

    public final String a() {
        GURL gurl;
        NavigationHandle navigationHandle = this.b;
        if (navigationHandle == null || (gurl = navigationHandle.e) == null) {
            return null;
        }
        return gurl.e();
    }
}

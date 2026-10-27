package org.chromium.chrome.browser.autofill;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.autofill.f;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class CreditCardScannerBridge implements f.a {
    public final long a;
    public final f b;

    public CreditCardScannerBridge(long j, WebContents webContents) {
        this.a = j;
        this.b = new f(webContents, this);
    }

    @CalledByNative
    public static CreditCardScannerBridge create(long j, WebContents webContents) {
        return new CreditCardScannerBridge(j, webContents);
    }

    @CalledByNative
    public final boolean canScan() {
        this.b.getClass();
        return false;
    }

    @CalledByNative
    public final void scan() {
        CreditCardScannerBridge creditCardScannerBridge = (CreditCardScannerBridge) this.b.a;
        N.MzlSwhwH(creditCardScannerBridge.a, creditCardScannerBridge);
    }
}

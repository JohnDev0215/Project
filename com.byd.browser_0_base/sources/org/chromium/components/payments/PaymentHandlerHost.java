package org.chromium.components.payments;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes3.dex */
public class PaymentHandlerHost {
    public long a;

    public PaymentHandlerHost(WebContents webContents, e eVar) {
        this.a = N.MNMwlyDN(webContents, eVar);
    }

    @CalledByNative
    public long getNativeBridge() {
        return this.a;
    }
}

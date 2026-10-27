package org.chromium.chrome.browser.payments.ui;

import org.chromium.chrome.browser.payments.ui.e;

/* JADX INFO: compiled from: PaymentRequestUI.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements Runnable {
    public final /* synthetic */ e e;

    public f(e eVar) {
        this.e = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        e eVar = this.e;
        eVar.R = null;
        e.c cVar = eVar.e;
        if (cVar.c) {
            return;
        }
        cVar.c = true;
        cVar.a.post(cVar.b);
    }
}

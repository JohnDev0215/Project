package org.chromium.chrome.browser.payments.ui;

import org.chromium.chrome.browser.payments.ui.e;

/* JADX INFO: compiled from: PaymentRequestUI.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements Runnable {
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ e.c f;

    public g(e.c cVar, b bVar) {
        this.f = cVar;
        this.e = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.run();
        this.f.c = false;
    }
}

package org.chromium.chrome.browser.payments;

import defpackage.bb;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;

/* JADX INFO: compiled from: ServiceWorkerPaymentAppBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements Runnable {
    public final /* synthetic */ ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback e;

    public a(bb bbVar) {
        this.e = bbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.a(false);
    }
}

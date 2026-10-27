package org.chromium.chrome.browser.payments;

import defpackage.g5;
import java.util.HashMap;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;

/* JADX INFO: compiled from: ServiceWorkerPaymentAppBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements Runnable {
    public final /* synthetic */ ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback e;

    public b(g5 g5Var) {
        this.e = g5Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.a(new HashMap());
    }
}

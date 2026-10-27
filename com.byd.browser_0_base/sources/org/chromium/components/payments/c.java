package org.chromium.components.payments;

import org.chromium.components.payments.PaymentApp;

/* JADX INFO: compiled from: PaymentApp.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c implements Runnable {
    public final /* synthetic */ PaymentApp.a e;
    public final /* synthetic */ PaymentApp f;

    public c(PaymentApp paymentApp, e eVar) {
        this.f = paymentApp;
        this.e = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((e) this.e).n(false);
    }
}

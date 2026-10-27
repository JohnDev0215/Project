package org.chromium.components.payments;

import defpackage.iy1;
import defpackage.n3;
import defpackage.nz1;
import defpackage.vz1;
import org.chromium.components.payments.g;

/* JADX INFO: compiled from: PaymentResponseHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f implements g {
    public final vz1 e;
    public final nz1 f;
    public final boolean g;
    public final PaymentApp h;

    public f(PaymentApp paymentApp, nz1 nz1Var) {
        this.h = paymentApp;
        this.g = paymentApp.p();
        this.f = nz1Var;
        vz1 vz1Var = new vz1(0);
        this.e = vz1Var;
        vz1Var.f = new iy1(0);
    }

    @Override // org.chromium.components.payments.g
    public final void a(String str, String str2, PayerData payerData, g.a aVar) {
        vz1 vz1Var = this.e;
        vz1Var.b = str;
        vz1Var.c = str2;
        nz1 nz1Var = this.f;
        if (nz1Var.e && this.g) {
            vz1Var.d = n3.y(payerData.d);
            vz1Var.e = payerData.e;
        }
        boolean z = nz1Var.b;
        PaymentApp paymentApp = this.h;
        if (z && paymentApp.n()) {
            vz1Var.f.c = payerData.a;
        }
        if (nz1Var.d && paymentApp.o()) {
            vz1Var.f.d = payerData.b;
        }
        if (nz1Var.c && paymentApp.m()) {
            vz1Var.f.b = payerData.c;
        }
        ((e) aVar).q(vz1Var);
    }
}

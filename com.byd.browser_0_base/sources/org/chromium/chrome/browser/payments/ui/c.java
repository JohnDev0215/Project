package org.chromium.chrome.browser.payments.ui;

import defpackage.dh2;
import defpackage.jz1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.payments.ui.e;

/* JADX INFO: compiled from: PaymentRequestUI.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements Callback<jz1> {
    public final /* synthetic */ e e;

    public c(e eVar) {
        this.e = eVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(jz1 jz1Var) {
        jz1 jz1Var2 = jz1Var;
        e eVar = this.e;
        eVar.I = false;
        eVar.k(jz1Var2.a);
        e.b bVar = eVar.g;
        if (((j) bVar).w()) {
            eVar.n(1, jz1Var2.b);
            eVar.n(2, jz1Var2.c);
        }
        if (((j) bVar).v()) {
            eVar.n(3, jz1Var2.d);
        }
        eVar.n(4, jz1Var2.e);
        dh2 dh2Var = eVar.N;
        if (dh2Var == null || dh2Var.b() != null) {
            eVar.c(null);
        } else {
            eVar.c(eVar.z);
        }
        eVar.m();
    }
}

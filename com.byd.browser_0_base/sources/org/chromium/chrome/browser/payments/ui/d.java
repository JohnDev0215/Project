package org.chromium.chrome.browser.payments.ui;

import defpackage.dh2;
import defpackage.jz1;
import defpackage.tl2;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.payments.ui.PaymentRequestSection;
import org.chromium.chrome.browser.payments.ui.e;
import org.chromium.chrome.browser.payments.ui.e.ViewOnLayoutChangeListenerC0107e;

/* JADX INFO: compiled from: PaymentRequestUI.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements Callback<jz1> {
    public final /* synthetic */ e e;

    public d(e eVar) {
        this.e = eVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(jz1 jz1Var) {
        jz1 jz1Var2 = jz1Var;
        tl2 tl2Var = jz1Var2.a;
        e eVar = this.e;
        eVar.k(tl2Var);
        e.b bVar = eVar.g;
        if (((j) bVar).w()) {
            eVar.n(1, jz1Var2.b);
            eVar.n(2, jz1Var2.c);
        }
        if (((j) bVar).v()) {
            eVar.n(3, jz1Var2.d);
        }
        PaymentRequestSection.OptionSection optionSection = eVar.C;
        dh2 dh2Var = jz1Var2.e;
        optionSection.setDisplaySummaryInSingleLineInNormalMode(dh2Var.d);
        eVar.n(4, dh2Var);
        eVar.m();
        eVar.a(false);
        eVar.m.addOnLayoutChangeListener(eVar.new ViewOnLayoutChangeListenerC0107e(false));
    }
}

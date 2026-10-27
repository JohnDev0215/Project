package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.payments.ui.e;

/* JADX INFO: compiled from: PaymentRequestUI.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tz1 implements Callback<dh2> {
    public final /* synthetic */ int e;
    public final /* synthetic */ e f;

    public tz1(e eVar, int i) {
        this.f = eVar;
        this.e = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(dh2 dh2Var) {
        e eVar = this.f;
        eVar.n(this.e, dh2Var);
        eVar.p();
    }
}

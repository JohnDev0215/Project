package defpackage;

import J.N;
import org.chromium.chrome.browser.ui.fast_checkout.FastCheckoutBridge;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FastCheckoutMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ck0 implements ov0 {
    public final /* synthetic */ ek0 a;

    public ck0(ek0 ek0Var) {
        this.a = ek0Var;
    }

    @Override // defpackage.ov0
    public final void a() {
        this.a.b(2);
        C0173a.a(5);
    }

    @Override // defpackage.ov0
    public final void b() {
        this.a.b(1);
        C0173a.a(4);
    }

    @Override // defpackage.ov0
    public final void c() {
        ek0 ek0Var = this.a;
        PropertyModel propertyModel = ek0Var.a;
        PropertyModel.n nVar = fk0.a;
        if (propertyModel.k(nVar)) {
            C0173a.a(2);
            FastCheckoutAutofillProfile fastCheckoutAutofillProfile = (FastCheckoutAutofillProfile) ek0Var.a.j(fk0.c);
            FastCheckoutCreditCard fastCheckoutCreditCard = (FastCheckoutCreditCard) ek0Var.a.j(fk0.e);
            ek0Var.a.l(nVar, false);
            long j = ((FastCheckoutBridge) ek0Var.b).a;
            if (j != 0) {
                N.M0K3$Nuc(j, fastCheckoutAutofillProfile, fastCheckoutCreditCard);
            }
        }
    }
}

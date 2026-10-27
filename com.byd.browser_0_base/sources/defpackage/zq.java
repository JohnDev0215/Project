package defpackage;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.payments.PayerData;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.e;
import org.chromium.components.payments.g;

/* JADX INFO: compiled from: ChromePaymentResponseHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zq implements PersonalDataManager.NormalizedAddressRequestDelegate, g {
    public final ra e;
    public final PaymentApp f;
    public final nz1 g;
    public final vz1 h;
    public final qa i;
    public g.a j;
    public boolean k;
    public boolean l = true;
    public PayerData m;

    public zq(lf0 lf0Var, lf0 lf0Var2, ra raVar, PaymentApp paymentApp, nz1 nz1Var) {
        vz1 vz1Var = new vz1(0);
        this.h = vz1Var;
        vz1Var.f = new iy1(0);
        this.f = paymentApp;
        this.g = nz1Var;
        this.e = raVar;
        if (nz1Var.e && !paymentApp.p()) {
            vz1Var.e = lf0Var2.g;
        }
        if (!nz1Var.e || paymentApp.p()) {
            return;
        }
        qa qaVar = (qa) lf0Var;
        this.i = qaVar;
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        String guid = qaVar.l.getGUID();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        N.MT65YYp8(personalDataManagerC.a, personalDataManagerC, guid);
        vz1Var.d = qaVar.j();
        this.k = true;
        PersonalDataManager personalDataManagerC2 = PersonalDataManager.c();
        PersonalDataManager.AutofillProfile autofillProfile = qaVar.l;
        personalDataManagerC2.getClass();
        N.M7ysHTTC(personalDataManagerC2.a, personalDataManagerC2, autofillProfile, 5, this);
    }

    @Override // org.chromium.components.payments.g
    public final void a(String str, String str2, PayerData payerData, g.a aVar) {
        this.j = aVar;
        vz1 vz1Var = this.h;
        vz1Var.b = str;
        vz1Var.c = str2;
        this.m = payerData;
        this.l = false;
        if (this.k) {
            return;
        }
        b();
    }

    public final void b() {
        nz1 nz1Var = this.g;
        boolean z = nz1Var.e;
        PaymentApp paymentApp = this.f;
        vz1 vz1Var = this.h;
        if (z && paymentApp.p()) {
            vz1Var.d = n3.y(this.m.d);
            vz1Var.e = this.m.e;
        }
        boolean z2 = nz1Var.b;
        ra raVar = this.e;
        if (z2) {
            if (paymentApp.n()) {
                vz1Var.f.c = this.m.a;
            } else {
                vz1Var.f.c = raVar.q;
            }
        }
        if (nz1Var.d) {
            if (paymentApp.o()) {
                vz1Var.f.d = this.m.b;
            } else {
                vz1Var.f.d = raVar.r;
            }
        }
        if (nz1Var.c) {
            if (paymentApp.m()) {
                vz1Var.f.b = this.m.c;
            } else {
                vz1Var.f.b = raVar.s;
            }
        }
        iy1 iy1Var = vz1Var.f;
        String str = iy1Var.d;
        if (str != null) {
            iy1Var.d = N.MntwGN0J(str);
        }
        ((e) this.j).q(vz1Var);
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public final void onAddressNormalized(PersonalDataManager.AutofillProfile autofillProfile) {
        if (this.k) {
            this.k = false;
            if (autofillProfile != null) {
                qa qaVar = this.i;
                qaVar.k(autofillProfile);
                this.h.d = qaVar.j();
            }
            if (this.l) {
                return;
            }
            b();
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public final void onCouldNotNormalize(PersonalDataManager.AutofillProfile autofillProfile) {
        onAddressNormalized(autofillProfile);
    }
}

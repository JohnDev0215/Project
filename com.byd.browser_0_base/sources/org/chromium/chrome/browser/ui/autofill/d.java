package org.chromium.chrome.browser.ui.autofill;

import J.N;
import defpackage.bi1;
import defpackage.di1;
import java.util.Optional;
import org.chromium.chrome.browser.ui.autofill.c;
import org.chromium.chrome.browser.ui.autofill.e;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: OtpVerificationDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements di1.a, e.a {
    public final bi1 e;
    public final PropertyModel f;
    public final c.a g;
    public PropertyModel h;

    public d(bi1 bi1Var, PropertyModel.b bVar, c.a aVar) {
        this.e = bi1Var;
        bVar.e(di1.a, this);
        this.f = bVar.a();
        this.g = aVar;
    }

    @Override // org.chromium.chrome.browser.ui.autofill.e.a
    public final void a(CharSequence charSequence) {
        this.f.l(di1.l, charSequence.length() != this.h.h(e.a));
        this.h.p(e.e, Optional.empty());
        this.h.p(e.d, Optional.of(charSequence));
    }

    @Override // di1.a
    public final void b(int i) {
        N.MKRdS4uy(((OtpVerificationDialogBridge) this.g).a);
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i != 0) {
            if (i != 1) {
                return;
            }
            this.e.b(2, propertyModel);
            return;
        }
        Optional optional = (Optional) this.h.j(e.d);
        if (optional.isPresent()) {
            this.h.l(e.g, true);
            this.f.l(di1.l, true);
            N.MTvxl6AI(((OtpVerificationDialogBridge) this.g).a, ((CharSequence) optional.get()).toString());
        }
    }

    @Override // org.chromium.chrome.browser.ui.autofill.e.a
    public final void d() {
        this.h.p(e.d, Optional.empty());
        N.MwUcrcWa(((OtpVerificationDialogBridge) this.g).a);
    }
}

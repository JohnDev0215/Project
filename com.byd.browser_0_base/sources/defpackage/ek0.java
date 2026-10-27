package defpackage;

import J.N;
import androidx.appcompat.widget.Toolbar;
import org.chromium.chrome.browser.ui.fast_checkout.FastCheckoutBridge;
import org.chromium.chrome.browser.ui.fast_checkout.R$string;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FastCheckoutMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ek0 implements hk0 {
    public PropertyModel a;
    public xj0 b;
    public ji c;
    public bk0 d;

    public final void a() {
        PropertyModel propertyModel = this.a;
        PropertyModel.n nVar = fk0.a;
        if (propertyModel.k(nVar)) {
            C0173a.a(14);
            this.a.l(nVar, false);
            long j = ((FastCheckoutBridge) this.b).a;
            if (j != 0) {
                N.M4U6AmKC(j);
            }
        }
    }

    public final void b(int i) {
        PropertyModel.r<ya1> rVar = fk0.m;
        PropertyModel.r<Toolbar.OnMenuItemClickListener> rVar2 = fk0.l;
        PropertyModel.p pVar = fk0.j;
        PropertyModel.p pVar2 = fk0.i;
        PropertyModel.p pVar3 = fk0.h;
        if (i == 1) {
            this.a.n(pVar3, R$string.fast_checkout_autofill_profile_sheet_title);
            this.a.n(pVar2, R$string.fast_checkout_autofill_profile_sheet_title_description);
            this.a.n(pVar, R$string.fast_checkout_autofill_profile_settings_button_description);
            this.a.p(rVar2, new dk0(new f52(20, this)));
            PropertyModel propertyModel = this.a;
            propertyModel.p(rVar, (ya1) propertyModel.j(fk0.d));
        } else if (i == 2) {
            this.a.n(pVar3, R$string.fast_checkout_credit_card_sheet_title);
            this.a.n(pVar2, R$string.fast_checkout_credit_card_sheet_title_description);
            this.a.n(pVar, R$string.fast_checkout_credit_card_settings_button_description);
            this.a.p(rVar2, new dk0(new ak0(this, 0)));
            PropertyModel propertyModel2 = this.a;
            propertyModel2.p(rVar, (ya1) propertyModel2.j(fk0.f));
        }
        this.a.n(fk0.b, i);
        this.c.l();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c(FastCheckoutAutofillProfile fastCheckoutAutofillProfile) {
        PropertyModel propertyModel = this.a;
        PropertyModel.r<FastCheckoutAutofillProfile> rVar = fk0.c;
        boolean z = propertyModel.j(rVar) == null;
        this.a.p(rVar, fastCheckoutAutofillProfile);
        for (xa1 xa1Var : (ya1) this.a.j(fk0.d)) {
            if (xa1Var.a == 1) {
                PropertyModel.l<FastCheckoutAutofillProfile> lVar = gb.a;
                PropertyModel propertyModel2 = xa1Var.b;
                boolean zEquals = fastCheckoutAutofillProfile.equals(propertyModel2.j(lVar));
                PropertyModel.n nVar = gb.b;
                boolean zK = propertyModel2.k(nVar);
                propertyModel2.l(nVar, zEquals);
                if (zEquals && !z) {
                    if (zK) {
                        C0173a.a(12);
                    } else {
                        C0173a.a(10);
                    }
                }
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void d(FastCheckoutCreditCard fastCheckoutCreditCard) {
        PropertyModel propertyModel = this.a;
        PropertyModel.r<FastCheckoutCreditCard> rVar = fk0.e;
        boolean z = propertyModel.j(rVar) == null;
        this.a.p(rVar, fastCheckoutCreditCard);
        for (xa1 xa1Var : (ya1) this.a.j(fk0.f)) {
            if (xa1Var.a == 2) {
                PropertyModel.l<FastCheckoutCreditCard> lVar = v10.a;
                PropertyModel propertyModel2 = xa1Var.b;
                boolean zEquals = fastCheckoutCreditCard.equals(propertyModel2.j(lVar));
                PropertyModel.n nVar = v10.b;
                boolean zK = propertyModel2.k(nVar);
                propertyModel2.l(nVar, zEquals);
                if (zEquals && !z) {
                    if (zK) {
                        C0173a.a(13);
                    } else {
                        C0173a.a(11);
                    }
                }
            }
        }
    }
}

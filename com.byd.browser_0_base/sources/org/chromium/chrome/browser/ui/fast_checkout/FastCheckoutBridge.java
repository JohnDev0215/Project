package org.chromium.chrome.browser.ui.fast_checkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import defpackage.C0173a;
import defpackage.C0175b;
import defpackage.ak0;
import defpackage.bk0;
import defpackage.ck0;
import defpackage.ee3;
import defpackage.ek0;
import defpackage.fk0;
import defpackage.gb;
import defpackage.gk0;
import defpackage.ji;
import defpackage.pv0;
import defpackage.v10;
import defpackage.ve3;
import defpackage.xa1;
import defpackage.xj0;
import defpackage.y70;
import defpackage.ya1;
import defpackage.z8;
import defpackage.z80;
import defpackage.zj;
import defpackage.zj0;
import defpackage.zo0;
import defpackage.zx1;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: loaded from: classes2.dex */
public class FastCheckoutBridge implements xj0 {
    public long a;
    public final zj0 b;

    public FastCheckoutBridge(long j, WindowAndroid windowAndroid, ji jiVar) {
        this.a = j;
        final zj0 zj0Var = new zj0();
        this.b = zj0Var;
        Context context = windowAndroid.i.get();
        zj0Var.d = jiVar;
        PropertyModel propertyModel = zj0Var.b;
        ek0 ek0Var = zj0Var.a;
        ek0Var.a = propertyModel;
        ek0Var.b = this;
        ek0Var.c = jiVar;
        ek0Var.d = new bk0(ek0Var);
        propertyModel.p(fk0.g, new ck0(ek0Var));
        ek0Var.a.p(fk0.k, new zx1(25, ek0Var));
        C0173a.a(1);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.fast_checkout_bottom_sheet, (ViewGroup) null);
        zj0Var.c = new gk0(ek0Var, linearLayout);
        j.b(propertyModel, new pv0.a(context, linearLayout.findViewById(R$id.fast_checkout_home_screen_sheet)), new ee3(6));
        new z80(context, linearLayout.findViewById(R$id.fast_checkout_detail_screen_sheet), propertyModel, zj0Var.d);
        final ViewFlipper viewFlipper = (ViewFlipper) linearLayout.findViewById(R$id.fast_checkout_bottom_sheet_view_flipper);
        propertyModel.a(new k.a() { // from class: yj0
            @Override // org.chromium.ui.modelutil.k.a
            public final void a(k kVar, Object obj) {
                v62 v62Var = (v62) obj;
                PropertyModel.p pVar = fk0.b;
                zj0 zj0Var2 = zj0Var;
                PropertyModel propertyModel2 = zj0Var2.b;
                int i = 1;
                if (pVar == v62Var) {
                    int iH = propertyModel2.h(pVar);
                    if (iH == 0 || (iH != 1 && iH != 2)) {
                        i = 0;
                    }
                    viewFlipper.setDisplayedChild(i);
                    return;
                }
                PropertyModel.n nVar = fk0.a;
                if (nVar == v62Var) {
                    boolean zK = propertyModel2.k(nVar);
                    gk0 gk0Var = zj0Var2.c;
                    ek0 ek0Var2 = zj0Var2.a;
                    if (zK) {
                        ek0Var2.c.g(ek0Var2.d);
                        if (!ek0Var2.c.d(gk0Var, true)) {
                            ek0Var2.c.e(ek0Var2.d);
                            i = 0;
                        }
                    } else {
                        ek0Var2.c.c(gk0Var, true);
                    }
                    if (i == 0 && propertyModel2.k(nVar)) {
                        ek0Var2.a();
                    }
                }
            }
        });
    }

    @CalledByNative
    public static FastCheckoutBridge create(long j, WindowAndroid windowAndroid) {
        ji jiVarA = h.a(windowAndroid);
        if (jiVarA == null) {
            return null;
        }
        return new FastCheckoutBridge(j, windowAndroid, jiVarA);
    }

    @CalledByNative
    public static FastCheckoutAutofillProfile[] createAutofillProfilesArray(int i) {
        return new FastCheckoutAutofillProfile[i];
    }

    @CalledByNative
    public static FastCheckoutCreditCard[] createCreditCardsArray(int i) {
        return new FastCheckoutCreditCard[i];
    }

    @CalledByNative
    public static void setAutofillProfile(FastCheckoutAutofillProfile[] fastCheckoutAutofillProfileArr, int i, FastCheckoutAutofillProfile fastCheckoutAutofillProfile) {
        fastCheckoutAutofillProfileArr[i] = fastCheckoutAutofillProfile;
    }

    @CalledByNative
    public static void setCreditCard(FastCheckoutCreditCard[] fastCheckoutCreditCardArr, int i, FastCheckoutCreditCard fastCheckoutCreditCard) {
        fastCheckoutCreditCardArr[i] = fastCheckoutCreditCard;
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
        ek0 ek0Var = this.b.a;
        ek0Var.getClass();
        C0173a.a(15);
        ek0Var.a.l(fk0.a, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r13v2, types: [ek0, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, java.lang.Object, org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile] */
    /* JADX WARN: Type inference failed for: r8v0, types: [T, java.lang.Object, org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, z8] */
    /* JADX WARN: Type inference failed for: r9v0, types: [T, y70] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @CalledByNative
    public final void showBottomSheet(FastCheckoutAutofillProfile[] fastCheckoutAutofillProfileArr, FastCheckoutCreditCard[] fastCheckoutCreditCardArr) {
        ?? r13 = this.b.a;
        FastCheckoutAutofillProfile fastCheckoutAutofillProfile = (FastCheckoutAutofillProfile) r13.a.j(fk0.c);
        ?? r2 = fastCheckoutAutofillProfileArr[0];
        ya1 ya1Var = (ya1) r13.a.j(fk0.d);
        ya1Var.clear();
        int length = fastCheckoutAutofillProfileArr.length;
        int i = 0;
        ?? r22 = r2;
        while (i < length) {
            ?? r7 = fastCheckoutAutofillProfileArr[i];
            if (fastCheckoutAutofillProfile != null && r7.getGUID().equals(fastCheckoutAutofillProfile.getGUID())) {
                r22 = r7;
            }
            ?? z8Var = new z8(r13, 26, r7);
            HashMap mapE = PropertyModel.e(gb.d);
            PropertyModel.l<FastCheckoutAutofillProfile> lVar = gb.a;
            int i2 = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = r7;
            mapE.put(lVar, gVar);
            PropertyModel.n nVar = gb.b;
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = false;
            mapE.put(nVar, aVar);
            PropertyModel.l<Runnable> lVar2 = gb.c;
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = z8Var;
            ya1Var.t(new xa1(1, C0175b.p(mapE, lVar2, gVar2, mapE)));
            i++;
            r22 = r22;
        }
        ya1Var.t(new xa1(3, zo0.a(R$string.fast_checkout_detail_screen_add_autofill_profile_text, new ak0(r13, 1))));
        r13.c(r22);
        FastCheckoutCreditCard fastCheckoutCreditCard = (FastCheckoutCreditCard) r13.a.j(fk0.e);
        ?? r0 = fastCheckoutCreditCardArr[0];
        ya1 ya1Var2 = (ya1) r13.a.j(fk0.f);
        ya1Var2.clear();
        int length2 = fastCheckoutCreditCardArr.length;
        int i3 = 0;
        ?? r02 = r0;
        while (true) {
            int i4 = 2;
            if (i3 >= length2) {
                ya1Var2.t(new xa1(3, zo0.a(R$string.fast_checkout_detail_screen_add_credit_card_text, new ve3(i4, r13))));
                r13.d(r02);
                r13.b(r13.a.h(fk0.b));
                r13.a.l(fk0.a, true);
                return;
            }
            ?? r8 = fastCheckoutCreditCardArr[i3];
            if (fastCheckoutCreditCard != null && r8.getGUID().equals(fastCheckoutCreditCard.getGUID())) {
                r02 = r8;
            }
            ?? y70Var = new y70(r13, 16, r8);
            HashMap mapE2 = PropertyModel.e(v10.d);
            PropertyModel.l<FastCheckoutCreditCard> lVar3 = v10.a;
            int i5 = zj.a;
            PropertyModel.g gVar3 = new PropertyModel.g();
            gVar3.a = r8;
            mapE2.put(lVar3, gVar3);
            PropertyModel.n nVar2 = v10.b;
            PropertyModel.a aVar2 = new PropertyModel.a();
            aVar2.a = false;
            mapE2.put(nVar2, aVar2);
            PropertyModel.l<Runnable> lVar4 = v10.c;
            PropertyModel.g gVar4 = new PropertyModel.g();
            gVar4.a = y70Var;
            ya1Var2.t(new xa1(2, C0175b.p(mapE2, lVar4, gVar4, mapE2)));
            i3++;
            r02 = r02;
        }
    }
}

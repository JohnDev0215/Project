package org.chromium.chrome.browser.touch_to_fill.payments;

import android.content.Context;
import defpackage.C0175b;
import defpackage.a9;
import defpackage.bg;
import defpackage.dr;
import defpackage.eu1;
import defpackage.fe3;
import defpackage.ge3;
import defpackage.he3;
import defpackage.ie3;
import defpackage.ji;
import defpackage.oi;
import defpackage.oj2;
import defpackage.xa1;
import defpackage.ya1;
import defpackage.zj;
import defpackage.zx1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes2.dex */
public class TouchToFillCreditCardViewBridge {
    public final fe3 a;

    /* JADX WARN: Type inference failed for: r2v2, types: [T, ya1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, dr] */
    public TouchToFillCreditCardViewBridge(TouchToFillCreditCardComponent$Delegate touchToFillCreditCardComponent$Delegate, Context context, ji jiVar, WindowAndroid windowAndroid) {
        fe3 fe3Var = new fe3();
        this.a = fe3Var;
        oi oiVar = new oi(jiVar, windowAndroid);
        HashMap mapE = PropertyModel.e(he3.d);
        PropertyModel.n nVar = he3.a;
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = false;
        mapE.put(nVar, aVar);
        PropertyModel.l<ya1> lVar = he3.b;
        ?? ya1Var = new ya1();
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = ya1Var;
        mapE.put(lVar, gVar);
        PropertyModel.l<Callback<Integer>> lVar2 = he3.c;
        ge3 ge3Var = fe3Var.a;
        Objects.requireNonNull(ge3Var);
        ?? drVar = new dr(22, ge3Var);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = drVar;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar2, gVar2, mapE);
        ge3Var.a = context;
        ge3Var.b = touchToFillCreditCardComponent$Delegate;
        ge3Var.c = propertyModelP;
        ge3Var.e = oiVar;
        j.b(propertyModelP, new ie3(context, jiVar), new bg(27));
    }

    @CalledByNative
    public static TouchToFillCreditCardViewBridge create(TouchToFillCreditCardComponent$Delegate touchToFillCreditCardComponent$Delegate, WindowAndroid windowAndroid) {
        Context context;
        ji jiVarA;
        if (windowAndroid == null || (context = windowAndroid.i.get()) == null || (jiVarA = h.a(windowAndroid)) == null) {
            return null;
        }
        return new TouchToFillCreditCardViewBridge(touchToFillCreditCardComponent$Delegate, context, jiVarA, windowAndroid);
    }

    @CalledByNative
    public static PersonalDataManager.CreditCard[] createCreditCardsArray(int i) {
        return new PersonalDataManager.CreditCard[i];
    }

    @CalledByNative
    public static void setCreditCard(PersonalDataManager.CreditCard[] creditCardArr, int i, PersonalDataManager.CreditCard creditCard) {
        creditCardArr[i] = creditCard;
    }

    @CalledByNative
    public final void hideSheet() {
        this.a.a.a(0);
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [T, zx1] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, eu1] */
    @CalledByNative
    public final void showSheet(PersonalDataManager.CreditCard[] creditCardArr, boolean z) {
        boolean z2;
        ge3 ge3Var = this.a.a;
        ge3Var.getClass();
        ge3Var.d = Arrays.asList(creditCardArr);
        ya1 ya1Var = (ya1) ge3Var.c.j(he3.b);
        ya1Var.clear();
        for (PersonalDataManager.CreditCard creditCard : creditCardArr) {
            PropertyModel.b bVar = new PropertyModel.b(he3.a.g);
            bVar.c(he3.a.a, creditCard.k);
            bVar.e(he3.a.b, creditCard.t);
            bVar.e(he3.a.c, creditCard.u);
            bVar.e(he3.a.f, new a9(ge3Var, 23, creditCard));
            if (creditCard.getIsVirtual()) {
                bVar.e(he3.a.e, ge3Var.a.getString(R$string.autofill_virtual_card_number_switch_label));
            } else {
                bVar.e(he3.a.d, ge3Var.a.getString(R$string.autofill_credit_card_two_line_label_from_card_number).replace("$1", creditCard.a(ge3Var.a)));
            }
            ya1Var.t(new xa1(1, bVar.a()));
        }
        if (creditCardArr.length == 1) {
            ya1Var.t(new xa1(2, ya1Var.get(0).b));
        }
        int length = creditCardArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z2 = true;
                break;
            } else {
                if (!creditCardArr[i].getIsLocal()) {
                    z2 = false;
                    break;
                }
                i++;
            }
        }
        HashMap mapE = PropertyModel.e(he3.c.b);
        PropertyModel.j jVar = he3.c.a;
        int i2 = z2 ? R$drawable.fre_product_logo : R$drawable.google_pay;
        int i3 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i2;
        mapE.put(jVar, dVar);
        ya1Var.u(0, new xa1(0, new PropertyModel(mapE)));
        HashMap mapE2 = PropertyModel.e(he3.b.d);
        PropertyModel.n nVar = he3.b.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE2.put(nVar, aVar);
        PropertyModel.l<Runnable> lVar = he3.b.b;
        ?? zx1Var = new zx1(23, ge3Var);
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = zx1Var;
        mapE2.put(lVar, gVar);
        PropertyModel.l<Runnable> lVar2 = he3.b.c;
        ?? eu1Var = new eu1(28, ge3Var);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = eu1Var;
        ya1Var.t(new xa1(3, C0175b.p(mapE2, lVar2, gVar2, mapE2)));
        oi oiVar = ge3Var.e;
        oiVar.f.g(oiVar);
        ge3Var.c.l(he3.a, true);
        oj2.Q(creditCardArr.length, "Autofill.TouchToFill.CreditCard.NumberOfCardsShown");
    }
}

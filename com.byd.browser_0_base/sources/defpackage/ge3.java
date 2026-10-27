package defpackage;

import android.content.Context;
import java.util.List;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.touch_to_fill.payments.TouchToFillCreditCardComponent$Delegate;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TouchToFillCreditCardMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ge3 {
    public Context a;
    public TouchToFillCreditCardComponent$Delegate b;
    public PropertyModel c;
    public List<PersonalDataManager.CreditCard> d;
    public oi e;

    public final void a(int i) {
        PropertyModel propertyModel = this.c;
        PropertyModel.n nVar = he3.a;
        if (propertyModel.k(nVar)) {
            this.c.l(nVar, false);
            boolean z = i == 1 || i == 2;
            this.b.a(z);
            if (z) {
                oj2.U(4, 5, "Autofill.TouchToFill.CreditCard.Outcome");
            }
        }
    }
}

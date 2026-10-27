package defpackage;

import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CreditCardItemProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v10 {
    public static final PropertyModel.l<FastCheckoutCreditCard> a;
    public static final PropertyModel.n b;
    public static final PropertyModel.l<Runnable> c;
    public static final v62[] d;

    static {
        PropertyModel.l<FastCheckoutCreditCard> lVar = new PropertyModel.l<>("credit_card");
        a = lVar;
        PropertyModel.n nVar = new PropertyModel.n("is_selected");
        b = nVar;
        PropertyModel.l<Runnable> lVar2 = new PropertyModel.l<>("on_click_listener");
        c = lVar2;
        d = new v62[]{lVar, nVar, lVar2};
    }
}

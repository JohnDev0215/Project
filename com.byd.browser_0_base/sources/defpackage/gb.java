package defpackage;

import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AutofillProfileItemProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gb {
    public static final PropertyModel.l<FastCheckoutAutofillProfile> a;
    public static final PropertyModel.n b;
    public static final PropertyModel.l<Runnable> c;
    public static final v62[] d;

    static {
        PropertyModel.l<FastCheckoutAutofillProfile> lVar = new PropertyModel.l<>("autofill_profile");
        a = lVar;
        PropertyModel.n nVar = new PropertyModel.n("is_selected");
        b = nVar;
        PropertyModel.l<Runnable> lVar2 = new PropertyModel.l<>("on_click_listener");
        c = lVar2;
        d = new v62[]{lVar, nVar, lVar2};
    }
}

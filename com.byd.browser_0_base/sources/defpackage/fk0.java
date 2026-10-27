package defpackage;

import androidx.appcompat.widget.Toolbar;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FastCheckoutProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fk0 {
    public static final PropertyModel.n a;
    public static final PropertyModel.p b;
    public static final PropertyModel.r<FastCheckoutAutofillProfile> c;
    public static final PropertyModel.l<ya1> d;
    public static final PropertyModel.r<FastCheckoutCreditCard> e;
    public static final PropertyModel.l<ya1> f;
    public static final PropertyModel.r<ov0> g;
    public static final PropertyModel.p h;
    public static final PropertyModel.p i;
    public static final PropertyModel.p j;
    public static final PropertyModel.r<Runnable> k;
    public static final PropertyModel.r<Toolbar.OnMenuItemClickListener> l;
    public static final PropertyModel.r<ya1> m;
    public static final v62[] n;

    static {
        PropertyModel.n nVar = new PropertyModel.n("visible");
        a = nVar;
        PropertyModel.p pVar = new PropertyModel.p("current_screen");
        b = pVar;
        PropertyModel.r<FastCheckoutAutofillProfile> rVar = new PropertyModel.r<>("selected_profile", false);
        c = rVar;
        PropertyModel.l<ya1> lVar = new PropertyModel.l<>("profile_model_list");
        d = lVar;
        PropertyModel.r<FastCheckoutCreditCard> rVar2 = new PropertyModel.r<>("selected_credit_card", false);
        e = rVar2;
        PropertyModel.l<ya1> lVar2 = new PropertyModel.l<>("credit_card_model_list");
        f = lVar2;
        PropertyModel.r<ov0> rVar3 = new PropertyModel.r<>("home_screen_delegate", false);
        g = rVar3;
        PropertyModel.p pVar2 = new PropertyModel.p("detail_screen_title");
        h = pVar2;
        PropertyModel.p pVar3 = new PropertyModel.p("detail_screen_title");
        i = pVar3;
        PropertyModel.p pVar4 = new PropertyModel.p("detail_screen_settings_menu_title");
        j = pVar4;
        PropertyModel.r<Runnable> rVar4 = new PropertyModel.r<>("detail_screen_back_click_handler", false);
        k = rVar4;
        PropertyModel.r<Toolbar.OnMenuItemClickListener> rVar5 = new PropertyModel.r<>("detail_screen_settings_click_handler", false);
        l = rVar5;
        PropertyModel.r<ya1> rVar6 = new PropertyModel.r<>("detail_screen_model_list", false);
        m = rVar6;
        n = new v62[]{nVar, pVar, rVar, lVar, rVar2, lVar2, rVar3, pVar2, pVar3, pVar4, rVar4, rVar5, rVar6};
    }
}

package defpackage;

import J.N;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.et2;
import defpackage.q6;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.ApplicationLifetime;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.language.settings.a;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.modelutil.l;
import org.chromium.ui.modelutil.o;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qh1 implements Preference.c, a.f, o.a, et2.a, a.InterfaceC0091a, r02, q6.d, LocationBarModel.d, g.d, ToolbarTablet.b, l.b, WebContentsImpl.d {
    public final /* synthetic */ int e;

    public /* synthetic */ qh1(int i) {
        this.e = i;
    }

    @Override // defpackage.r02
    public final q02 a() {
        if (m02.j == null) {
            m02.j = new rh0();
        }
        return m02.j;
    }

    public final Object b(Bundle bundle) {
        String[] strArr = rs0.a;
        for (int i = 0; i < 5; i++) {
            String str = strArr[i];
            if (!bundle.containsKey(str) || !(bundle.get(str) instanceof String)) {
                Log.w("cr_DigitalGoods", "Item does not contain field String " + str + ".");
                return null;
            }
        }
        ry1 ry1Var = new ry1(0);
        ry1Var.b = bundle.getString("itemDetails.currency");
        ry1Var.c = bundle.getString("itemDetails.value");
        a31 a31Var = new a31();
        a31Var.b = bundle.getString("itemDetails.id");
        a31Var.c = bundle.getString("itemDetails.title");
        a31Var.d = bundle.getString("itemDetails.description");
        a31Var.e = ry1Var;
        String string = bundle.getString("itemDetails.type");
        a31Var.k = "subs".equals(string) ? 2 : "inapp".equals(string) ? 1 : 0;
        String string2 = bundle.getString("itemDetails.url");
        if (string2 != null) {
            qj3 qj3Var = new qj3(0);
            qj3Var.b = string2;
            a31Var.l = new qj3[]{qj3Var};
        } else {
            a31Var.l = new qj3[0];
        }
        a31Var.f = bundle.getString("itemDetails.subsPeriod");
        a31Var.g = bundle.getString("itemDetails.freeTrialPeriod");
        a31Var.i = bundle.getString("itemDetails.introPricePeriod");
        a31Var.j = bundle.getInt("itemDetails.introPriceCycles", 0);
        String string3 = bundle.getString("itemDetails.introPriceCurrency");
        String string4 = bundle.getString("itemDetails.introPriceValue");
        if (string3 != null && string4 != null) {
            ry1 ry1Var2 = new ry1(0);
            ry1Var2.b = string3;
            ry1Var2.c = string4;
            a31Var.h = ry1Var2;
        }
        return a31Var;
    }

    @Override // org.chromium.chrome.browser.omnibox.g.d
    public final void c(String str, int i, boolean z) {
        if ((i & 255) == 5) {
            um1.a(0);
        } else if (N.Mj1SQ9S8(str)) {
            um1.a(1);
        } else {
            um1.a(2);
        }
        if (z) {
            n3.Z0(1, 1);
        }
    }

    public final long d() {
        switch (this.e) {
            case 8:
                break;
            case 17:
                break;
        }
        return System.currentTimeMillis();
    }

    @Override // org.chromium.ui.modelutil.o.a
    public final int f(Object obj) {
        return ((xa1) obj).a;
    }

    @Override // org.chromium.chrome.browser.language.settings.a.InterfaceC0091a
    public final void h() {
        switch (this.e) {
            case 10:
                SettingsActivity settingsActivity = SettingsActivity.P;
                ApplicationLifetime.terminate(true);
                break;
            default:
                ApplicationLifetime.terminate(true);
                break;
        }
    }

    @Override // org.chromium.content.browser.webcontents.WebContentsImpl.d
    public final Object i(WebContentsImpl webContentsImpl) {
        switch (this.e) {
            case 19:
                return new cs0(webContentsImpl);
            case 20:
                return new SelectPopup(webContentsImpl);
            default:
                return new SelectionPopupControllerImpl(webContentsImpl);
        }
    }

    @Override // org.chromium.ui.modelutil.l.b
    public final Object j(int i, RecyclerView recyclerView) {
        if (i == 1) {
            return new oe3(recyclerView, qx1.c() ? R$layout.touch_to_fill_header_item_modern : R$layout.touch_to_fill_header_item, new bg(25));
        }
        if (i == 2) {
            return new oe3(recyclerView, qx1.c() ? R$layout.touch_to_fill_credential_item_modern : R$layout.touch_to_fill_credential_item, new cg(18));
        }
        if (i == 3) {
            return new oe3(recyclerView, qx1.c() ? R$layout.touch_to_fill_webauthn_credential_item_modern : R$layout.touch_to_fill_webauthn_credential_item, new zf(28));
        }
        if (i == 4) {
            return new oe3(recyclerView, qx1.c() ? R$layout.touch_to_fill_fill_button_modern : R$layout.touch_to_fill_fill_button, new ag(15));
        }
        if (i != 5) {
            return null;
        }
        return new oe3(recyclerView, R$layout.touch_to_fill_footer_item, new bg(26));
    }

    @Override // et2.a
    public final void m(Tab tab, wq wqVar) {
        Object obj = SearchActivity.q0;
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        int i = AutofillPaymentMethodsFragment.h0;
        PersonalDataManager.d().e("autofill.credit_card_enabled", ((Boolean) obj).booleanValue());
        return true;
    }

    @Override // org.chromium.chrome.browser.customtabs.a.f
    public final Object o(a.e eVar) {
        return Boolean.FALSE;
    }
}

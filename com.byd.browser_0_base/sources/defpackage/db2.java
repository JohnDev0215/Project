package defpackage;

import J.N;
import android.content.Intent;
import defpackage.ij0;
import defpackage.xz;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class db2 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ db2(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.h;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                xe3 xe3Var = (xe3) obj;
                N.MM1KTgoi((Profile) obj3, 73, true);
                int i2 = SingleCategorySettings.w0;
                oj2.U(1, 2, "Android.RequestDesktopSite.Changed");
                xz.a.a.edit().putBoolean("Chrome.RequestDesktopSiteGlobalSetting.UserEnabled", true).apply();
                Tab tab = (Tab) ((zp1) obj2).get();
                if (tab != null && !tab.isDestroyed()) {
                    tab.C(6);
                }
                xe3Var.notifyEvent("desktop_site_opt_in_primary_action");
                break;
            default:
                ij0 ij0Var = (ij0) obj;
                ((hj0) obj3).x((Intent) obj2);
                lb2<ij0.a> lb2Var = ij0Var.s;
                if (lb2Var != null) {
                    lb2Var.onResult(new ij0.a(ij0Var, true));
                }
                break;
        }
        return 1;
    }
}

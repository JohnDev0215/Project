package org.chromium.components.browser_ui.site_settings;

import android.view.View;
import defpackage.kr;
import defpackage.oj2;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.page_info.PageInfoController;

/* JADX INFO: compiled from: SingleWebsiteSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements View.OnClickListener {
    public final /* synthetic */ SingleWebsiteSettings e;

    public c(SingleWebsiteSettings singleWebsiteSettings) {
        this.e = singleWebsiteSettings;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SingleWebsiteSettings singleWebsiteSettings = this.e;
        if (singleWebsiteSettings.D() != null) {
            for (int i = 0; i < 87; i++) {
                String strR0 = SingleWebsiteSettings.R0(i);
                if (strR0 != null) {
                    singleWebsiteSettings.V0(strR0);
                }
            }
            boolean z = singleWebsiteSettings.m0.j() == 0 && singleWebsiteSettings.o0 == 0;
            d.c(((kr) singleWebsiteSettings.h0).b, singleWebsiteSettings.m0);
            d.b(((kr) singleWebsiteSettings.h0).b, singleWebsiteSettings.m0, singleWebsiteSettings.t0);
            oj2.U(singleWebsiteSettings.j.getInt("org.chromium.chrome.preferences.navigation_source", 0), 3, "SingleWebsitePreferences.NavigatedFromToReset");
            if (z) {
                singleWebsiteSettings.D().finish();
            }
        }
        SingleWebsiteSettings.a aVar = singleWebsiteSettings.l0;
        if (aVar != null) {
            org.chromium.components.page_info.e eVar = (org.chromium.components.page_info.e) aVar;
            PageInfoController pageInfoController = (PageInfoController) eVar.g;
            pageInfoController.f(15);
            eVar.l = true;
            pageInfoController.d();
        }
    }
}

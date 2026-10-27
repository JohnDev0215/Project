package org.chromium.chrome.browser.search_engines.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import defpackage.e81;
import defpackage.gg2;
import defpackage.i62;
import defpackage.oj2;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: loaded from: classes2.dex */
public class SearchEngineSettings extends e81 implements i62 {
    public gg2 i0;
    public Profile j0;

    public final void K0() {
        if (this.i0 != null) {
            return;
        }
        this.i0 = new gg2(D(), this.j0);
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        D().setTitle(R$string.search_engine_settings);
        K0();
        I0(this.i0);
    }

    @Override // defpackage.i62
    public final void m(Profile profile) {
        this.j0 = profile;
    }

    @Override // androidx.fragment.app.Fragment
    public final void o0() {
        this.H = true;
        gg2 gg2Var = this.i0;
        gg2Var.d();
        oj2.q(gg2Var.f).a(gg2Var);
    }

    @Override // androidx.fragment.app.Fragment
    public final void p0() {
        this.H = true;
        gg2 gg2Var = this.i0;
        boolean z = gg2Var.l;
        Profile profile = gg2Var.f;
        if (z) {
            TemplateUrlService templateUrlServiceQ = oj2.q(profile);
            templateUrlServiceQ.getClass();
            Object obj = ThreadUtils.a;
            templateUrlServiceQ.a.c(gg2Var);
            gg2Var.l = false;
        }
        oj2.q(profile).i(gg2Var);
    }

    @Override // defpackage.e81, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        H0();
        H0();
        ListView listView = this.d0;
        listView.setDivider(null);
        listView.setItemsCanFocus(true);
    }
}

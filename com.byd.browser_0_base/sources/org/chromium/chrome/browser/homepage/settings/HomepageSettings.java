package org.chromium.chrome.browser.homepage.settings;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.bk3;
import defpackage.gq;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pq2;
import defpackage.qj2;
import defpackage.qv0;
import defpackage.rv0;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.homepage.settings.RadioButtonGroupHomepagePreference;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class HomepageSettings extends d {
    public static final /* synthetic */ int j0 = 0;
    public qv0 h0;
    public RadioButtonGroupHomepagePreference i0;

    public static class a implements gq {
        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            return rv0.b();
        }
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        this.h0 = qv0.d();
        D().setTitle(R$string.options_homepage_title);
        qj2.a(this, R$xml.homepage_preferences);
        a aVar = new a();
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("homepage_switch");
        chromeSwitchPreference.b0(aVar);
        this.i0 = (RadioButtonGroupHomepagePreference) f("homepage_radio_group");
        chromeSwitchPreference.Y(qv0.e());
        chromeSwitchPreference.setOnPreferenceChangeListener(new pq2(16, this));
        this.i0.Y(M0());
        n3.X0("Settings.Homepage.Opened");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RadioButtonGroupHomepagePreference.a M0() {
        String strJ;
        boolean zB = rv0.b();
        boolean zL = zB ? bk3.l(rv0.a().f) : (this.h0.a.readBoolean("Chrome.Homepage.UseNTP", false) || (this.h0.a.readBoolean("homepage_partner_enabled", true) && bk3.k(qv0.b()))) ? 1 : 0;
        int i = !zL;
        boolean z = !zB && qv0.e();
        boolean z2 = (zB && zL == 0) ? false : true;
        boolean z3 = !zB || zL == 0;
        if (rv0.b()) {
            strJ = rv0.a().f.j();
        } else {
            String strB = qv0.b();
            String strG = this.h0.a.g("homepage_custom_uri", "");
            if (this.h0.a.readBoolean("homepage_partner_enabled", true)) {
                if (bk3.k(strB)) {
                    strB = "";
                }
            } else if (!TextUtils.isEmpty(strG) || bk3.k(strB)) {
                strJ = strG;
            }
            strJ = strB;
        }
        return new RadioButtonGroupHomepagePreference.a(i, strJ, z, z2, z3);
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        RadioButtonGroupHomepagePreference radioButtonGroupHomepagePreference = this.i0;
        if (radioButtonGroupHomepagePreference != null) {
            radioButtonGroupHomepagePreference.Y(M0());
        }
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void p0() {
        super.p0();
        RadioButtonGroupHomepagePreference.a aVar = this.i0.X;
        if (rv0.b()) {
            return;
        }
        boolean z = aVar.a == 0;
        GURL gurlH = oj2.h(aVar.b);
        String str = gurlH.b ? gurlH.a : "";
        boolean zEquals = qv0.b().equals(str);
        qv0 qv0Var = this.h0;
        boolean z2 = qv0Var.a.readBoolean("Chrome.Homepage.UseNTP", false);
        SharedPreferencesManager sharedPreferencesManager = qv0Var.a;
        boolean z3 = sharedPreferencesManager.readBoolean("homepage_partner_enabled", true);
        String strG = sharedPreferencesManager.g("homepage_custom_uri", "");
        if (z == z2 && zEquals == z3 && strG.equals(str)) {
            return;
        }
        if (z != z2) {
            sharedPreferencesManager.l("Chrome.Homepage.UseNTP", z);
        }
        if (z3 != zEquals) {
            sharedPreferencesManager.l("homepage_partner_enabled", zEquals);
        }
        if (!strG.equals(str)) {
            sharedPreferencesManager.o("homepage_custom_uri", str);
        }
        n3.X0("Settings.Homepage.LocationChanged_V2");
        qv0Var.f();
    }
}

package org.chromium.components.page_info;

import android.os.Bundle;
import defpackage.aq3;
import defpackage.f52;
import defpackage.qj2;
import defpackage.ve3;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment;

/* JADX INFO: loaded from: classes3.dex */
public class PageInfoCookiesPreference extends SiteSettingsPreferenceFragment {
    public static final /* synthetic */ int p0 = 0;
    public ChromeSwitchPreference i0;
    public ChromeImageViewPreference j0;
    public ChromeImageViewPreference k0;
    public Runnable l0;
    public boolean m0;
    public boolean n0;
    public CharSequence o0;

    public static class a {
        public boolean a;
        public aq3 b;
        public ve3 c;
        public f52 d;
        public boolean e;
        public String f;
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        if (!(this.h0 != null)) {
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(M());
            aVar.k(this);
            aVar.g();
        } else {
            qj2.a(this, R$xml.page_info_cookie_preference);
            this.i0 = (ChromeSwitchPreference) f("cookie_switch");
            this.j0 = (ChromeImageViewPreference) f("cookie_in_use");
            ChromeImageViewPreference chromeImageViewPreference = (ChromeImageViewPreference) f("fps_in_use");
            this.k0 = chromeImageViewPreference;
            chromeImageViewPreference.U(false);
        }
    }

    public final void M0(int i, boolean z) {
        boolean z2 = i != 2;
        boolean z3 = i == 1;
        this.i0.U(z2);
        if (z2) {
            this.i0.L(qj2.b(K(), R$drawable.ic_eye_crossed));
            this.i0.Y(z3);
            this.i0.J(!z);
        }
    }

    public final void N0(int i, int i2) {
        this.i0.R(i2 > 0 ? K().getResources().getQuantityString(R$plurals.cookie_controls_blocked_cookies, i2, Integer.valueOf(i2)) : null);
        this.j0.T(K().getResources().getQuantityString(R$plurals.page_info_cookies_in_use, i, Integer.valueOf(i)));
        this.n0 |= i != 0;
        O0();
    }

    public final void O0() {
        ChromeImageViewPreference chromeImageViewPreference = this.j0;
        int i = (this.m0 || !this.n0) ? R$color.default_icon_color_disabled : R$color.default_icon_color_accent1_tint_list;
        if (chromeImageViewPreference.V == i) {
            return;
        }
        chromeImageViewPreference.V = i;
        chromeImageViewPreference.Y();
    }

    @Override // org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment, androidx.preference.d, androidx.fragment.app.Fragment
    public final void g0() {
        super.g0();
    }
}

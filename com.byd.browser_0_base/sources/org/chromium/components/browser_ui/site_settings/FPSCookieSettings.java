package org.chromium.components.browser_ui.site_settings;

import J.N;
import androidx.preference.Preference;
import defpackage.kr;
import defpackage.mb1;
import defpackage.ph1;
import defpackage.zn2;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* JADX INFO: loaded from: classes2.dex */
public class FPSCookieSettings extends SiteSettingsPreferenceFragment implements Preference.c {
    public ChromeSwitchPreference i0;
    public TextMessagePreference j0;
    public TextMessagePreference k0;

    public class a extends ph1 {
        public a(mb1 mb1Var) {
            super(mb1Var);
        }

        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            if (!"allow_fps".equals(preference.p)) {
                return false;
            }
            ((kr) FPSCookieSettings.this.h0).getClass();
            return N.MRTP8BOe();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008d  */
    @Override // androidx.preference.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void J0(android.os.Bundle r5, java.lang.String r6) {
        /*
            r4 = this;
            int r5 = org.chromium.components.browser_ui.site_settings.R$xml.fps_cookie_settings
            defpackage.qj2.a(r4, r5)
            androidx.fragment.app.FragmentActivity r5 = r4.D()
            android.content.Context r6 = r4.K()
            int r0 = org.chromium.components.browser_ui.site_settings.R$string.cookies_title
            java.lang.String r6 = r6.getString(r0)
            r5.setTitle(r6)
            java.lang.String r5 = "subtitle"
            androidx.preference.Preference r5 = r4.f(r5)
            org.chromium.components.browser_ui.settings.TextMessagePreference r5 = (org.chromium.components.browser_ui.settings.TextMessagePreference) r5
            r4.j0 = r5
            java.lang.String r5 = "bullet_two"
            androidx.preference.Preference r5 = r4.f(r5)
            org.chromium.components.browser_ui.settings.TextMessagePreference r5 = (org.chromium.components.browser_ui.settings.TextMessagePreference) r5
            r4.k0 = r5
            java.lang.String r5 = "allow_fps"
            androidx.preference.Preference r5 = r4.f(r5)
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r5 = (org.chromium.components.browser_ui.settings.ChromeSwitchPreference) r5
            r4.i0 = r5
            android.os.Bundle r5 = r4.j
            java.lang.String r6 = "cookie_page_state"
            int r5 = r5.getInt(r6)
            r6 = 0
            r0 = 1
            if (r5 != r0) goto Lae
            org.chromium.components.browser_ui.site_settings.FPSCookieSettings$a r5 = new org.chromium.components.browser_ui.site_settings.FPSCookieSettings$a
            zn2 r1 = r4.h0
            kr r1 = (defpackage.kr) r1
            mb1 r1 = r1.a()
            r5.<init>(r1)
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r1 = r4.i0
            r1.b0(r5)
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r5 = r4.i0
            zn2 r1 = r4.h0
            kr r1 = (defpackage.kr) r1
            r1.getClass()
            boolean r1 = J.N.MhilDEgf()
            r5.Y(r1)
            zn2 r5 = r4.h0
            kr r5 = (defpackage.kr) r5
            org.chromium.chrome.browser.profiles.Profile r5 = r5.b
            org.chromium.components.prefs.PrefService r1 = defpackage.oj2.k(r5)
            zn2 r2 = r4.h0
            kr r2 = (defpackage.kr) r2
            boolean r2 = r2.c()
            java.lang.String r3 = "profile.cookie_controls_mode"
            if (r2 == 0) goto L7f
            int r5 = r1.b(r3)
            if (r5 != r0) goto L8d
            goto L8b
        L7f:
            boolean r5 = J.N.MJSt3Ocq(r5, r6)
            int r1 = r1.b(r3)
            if (r5 == 0) goto L8d
            if (r1 != r0) goto L8d
        L8b:
            r5 = r0
            goto L8e
        L8d:
            r5 = r6
        L8e:
            if (r5 != 0) goto L95
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r5 = r4.i0
            r5.J(r6)
        L95:
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r5 = r4.i0
            r5.setOnPreferenceChangeListener(r4)
            org.chromium.components.browser_ui.settings.TextMessagePreference r5 = r4.j0
            int r6 = org.chromium.components.browser_ui.site_settings.R$string.website_settings_category_cookie_block_third_party_subtitle
            r5.S(r6)
            org.chromium.components.browser_ui.settings.TextMessagePreference r5 = r4.k0
            int r6 = org.chromium.components.browser_ui.site_settings.R$string.website_settings_category_cookie_subpage_bullet_two
            r5.Q(r6)
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r4 = r4.i0
            r4.U(r0)
            goto Lc4
        Lae:
            r0 = 2
            if (r5 != r0) goto Lc4
            org.chromium.components.browser_ui.settings.TextMessagePreference r5 = r4.j0
            int r0 = org.chromium.components.browser_ui.site_settings.R$string.website_settings_category_cookie_block_third_party_incognito_subtitle
            r5.S(r0)
            org.chromium.components.browser_ui.settings.TextMessagePreference r5 = r4.k0
            int r0 = org.chromium.components.browser_ui.site_settings.R$string.website_settings_category_cookie_subpage_incognito_bullet_two
            r5.Q(r0)
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference r4 = r4.i0
            r4.U(r6)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.FPSCookieSettings.J0(android.os.Bundle, java.lang.String):void");
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!"allow_fps".equals(preference.p)) {
            return true;
        }
        zn2 zn2Var = this.h0;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        ((kr) zn2Var).getClass();
        N.MliKWVnz(zBooleanValue);
        return true;
    }
}

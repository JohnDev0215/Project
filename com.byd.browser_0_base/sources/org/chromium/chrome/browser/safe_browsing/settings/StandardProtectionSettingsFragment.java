package org.chromium.chrome.browser.safe_browsing.settings;

import J.N;
import androidx.preference.Preference;
import defpackage.gx;
import defpackage.oj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: loaded from: classes2.dex */
public class StandardProtectionSettingsFragment extends SafeBrowsingSettingsFragmentBase implements Preference.c {
    public static final /* synthetic */ int m0 = 0;
    public ChromeSwitchPreference i0;
    public ChromeSwitchPreference j0;
    public final gx k0 = new gx(19, this);
    public final PrefService l0 = oj2.k(Profile.d());

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public final int M0() {
        return R$xml.standard_protection_preferences;
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public final void N0() {
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("extended_reporting");
        this.i0 = chromeSwitchPreference;
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        ChromeSwitchPreference chromeSwitchPreference2 = this.i0;
        gx gxVar = this.k0;
        chromeSwitchPreference2.b0(gxVar);
        ChromeSwitchPreference chromeSwitchPreference3 = (ChromeSwitchPreference) f("password_leak_detection");
        this.j0 = chromeSwitchPreference3;
        chromeSwitchPreference3.setOnPreferenceChangeListener(this);
        this.j0.b0(gxVar);
        int iMdyQjr8h = N.MdyQjr8h();
        boolean z = false;
        boolean z2 = iMdyQjr8h == 2;
        boolean z3 = iMdyQjr8h == 1;
        boolean z4 = z2 || (z3 && N.MWJZTkWR());
        this.i0.J(z3 && !gxVar.g(this.i0));
        this.i0.Y(z4);
        boolean z5 = N.MiM2m7HY() || N.MqSYugfQ();
        boolean zA = this.l0.a("profile.password_manager_leak_detection");
        boolean zG = gxVar.g(this.j0);
        boolean z6 = z2 || (z3 && zA);
        boolean z7 = z6 && z5;
        ChromeSwitchPreference chromeSwitchPreference4 = this.j0;
        if (z3 && z5 && !zG) {
            z = true;
        }
        chromeSwitchPreference4.J(z);
        this.j0.Y(z7);
        if (!z6 || z5) {
            return;
        }
        this.j0.Q(R$string.passwords_leak_detection_switch_signed_out_enable_description);
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        String str = preference.p;
        if ("extended_reporting".equals(str)) {
            N.MjGeUNkF(((Boolean) obj).booleanValue());
            return true;
        }
        if (!"password_leak_detection".equals(str)) {
            return true;
        }
        this.l0.e("profile.password_manager_leak_detection", ((Boolean) obj).booleanValue());
        return true;
    }
}

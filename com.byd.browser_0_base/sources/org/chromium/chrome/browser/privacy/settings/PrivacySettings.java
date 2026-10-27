package org.chromium.chrome.browser.privacy.settings;

import J.N;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.C0179d;
import defpackage.bq;
import defpackage.by0;
import defpackage.gq;
import defpackage.gx;
import defpackage.j52;
import defpackage.k52;
import defpackage.m2;
import defpackage.ny0;
import defpackage.oj2;
import defpackage.qj2;
import defpackage.sk;
import defpackage.v42;
import java.util.ArrayList;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.incognito.reauth.IncognitoReauthSettingSwitchPreference;
import org.chromium.chrome.browser.privacy.secure_dns.a;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacySettings extends d implements Preference.c {
    public static final /* synthetic */ int i0 = 0;
    public by0 h0;

    public class a implements gq {
        @Override // defpackage.mb1
        public final boolean g(Preference preference) {
            return r(preference) || N.MLwRLVOo();
        }

        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            String str = preference.p;
            return oj2.k(Profile.d()).d("https_only_mode_enabled");
        }
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        v42.g();
        D().setTitle(R$string.prefs_privacy_security);
        sk skVar = bq.a;
        if (N.M09VlOh_("PrivacySandboxSettings4")) {
            qj2.a(this, R$xml.privacy_preferences_v2);
        } else {
            qj2.a(this, R$xml.privacy_preferences);
        }
        Preference preferenceF = f("privacy_sandbox");
        int i = 0;
        if (N.MewRKkCC()) {
            I0().d0(preferenceF);
        } else {
            preferenceF.setOnPreferenceClickListener(new j52(this, i));
        }
        Preference preferenceF2 = f("privacy_guide");
        preferenceF2.setOnPreferenceClickListener(new k52(i));
        if (!N.M09VlOh_("PrivacyGuideAndroid")) {
            I0().d0(preferenceF2);
        }
        IncognitoReauthSettingSwitchPreference incognitoReauthSettingSwitchPreference = (IncognitoReauthSettingSwitchPreference) f("incognito_lock");
        by0 by0Var = new by0(incognitoReauthSettingSwitchPreference);
        this.h0 = by0Var;
        FragmentActivity fragmentActivityD = D();
        if (ny0.b()) {
            incognitoReauthSettingSwitchPreference.f0 = new m2(fragmentActivityD, 2);
            incognitoReauthSettingSwitchPreference.setOnPreferenceChangeListener(new gx(18, by0Var));
            by0Var.a(fragmentActivityD);
        } else {
            incognitoReauthSettingSwitchPreference.U(false);
        }
        Preference preferenceF3 = f("safe_browsing");
        preferenceF3.R(SafeBrowsingSettingsFragment.O0(K()));
        preferenceF3.setOnPreferenceClickListener(new Preference.d() { // from class: l52
            @Override // androidx.preference.Preference.d
            public final boolean r(Preference preference) {
                int i2 = PrivacySettings.i0;
                preference.m().putInt("SafeBrowsingSettingsFragment.AccessPoint", 1);
                return false;
            }
        });
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("https_first_mode");
        chromeSwitchPreference.U(N.M09VlOh_("HttpsOnlyMode"));
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        chromeSwitchPreference.b0(new a());
        chromeSwitchPreference.Y(oj2.k(Profile.d()).a("https_only_mode_enabled"));
        if (N.MLwRLVOo()) {
            chromeSwitchPreference.R(K().getResources().getString(R$string.settings_https_first_mode_with_advanced_protection_summary));
        }
        f("secure_dns").U(N.M6bsIDpc("DnsOverHttps", "ShowUi", true));
        f("sync_and_services_link").R("");
        Preference preferenceF4 = f("third_party_cookies");
        if (preferenceF4 != null) {
            preferenceF4.m().putString("category", preferenceF4.p);
            preferenceF4.m().putString("title", preferenceF4.l.toString());
        }
        M0();
    }

    public final void M0() {
        String string;
        PrefService prefServiceK = oj2.k(Profile.d());
        Preference preferenceF = f("do_not_track");
        if (preferenceF != null) {
            preferenceF.Q(prefServiceK.a("enable_do_not_track") ? R$string.text_on : R$string.text_off);
        }
        Preference preferenceF2 = f("preload_pages");
        int i = 1;
        if (preferenceF2 != null) {
            Context contextK = K();
            int iMaV3tKHW = N.MaV3tKHW();
            preferenceF2.R(iMaV3tKHW == 2 ? contextK.getString(R$string.preload_pages_extended_preloading_title) : iMaV3tKHW == 1 ? contextK.getString(R$string.preload_pages_standard_preloading_title) : iMaV3tKHW == 0 ? contextK.getString(R$string.preload_pages_no_preloading_title) : "");
        }
        Preference preferenceF3 = f("secure_dns");
        int i2 = 0;
        if (preferenceF3 != null && preferenceF3.B) {
            Context contextK2 = K();
            int iMvJZm_HK = N.MvJZm_HK();
            if (iMvJZm_HK == 0) {
                string = contextK2.getString(R$string.text_off);
            } else if (iMvJZm_HK == 1) {
                string = contextK2.getString(R$string.settings_automatic_mode_summary);
            } else {
                String strMBuwU61d = N.MBuwU61d();
                ArrayList arrayListA = org.chromium.chrome.browser.privacy.secure_dns.a.a();
                int i3 = 0;
                while (true) {
                    if (i3 >= arrayListA.size()) {
                        break;
                    }
                    a.C0109a c0109a = (a.C0109a) arrayListA.get(i3);
                    if (c0109a.b.equals(strMBuwU61d)) {
                        strMBuwU61d = c0109a.a;
                        break;
                    }
                    i3++;
                }
                string = String.format("%s - %s", contextK2.getString(R$string.text_on), strMBuwU61d);
            }
            preferenceF3.R(string);
        }
        Preference preferenceF4 = f("safe_browsing");
        if (preferenceF4 != null && preferenceF4.B) {
            preferenceF4.R(SafeBrowsingSettingsFragment.O0(K()));
        }
        Preference preferenceF5 = f("usage_stats_reporting");
        if (preferenceF5 != null) {
            if (prefServiceK.a("usage_stats_reporting.enabled")) {
                preferenceF5.setOnPreferenceClickListener(new C0179d(i, this));
            } else {
                I0().d0(preferenceF5);
            }
        }
        Preference preferenceF6 = f("privacy_sandbox");
        if (preferenceF6 != null) {
            sk skVar = bq.a;
            if (!N.M09VlOh_("PrivacySandboxSettings4")) {
                preferenceF6.R(K().getString(N.MhaiireD() ? R$string.privacy_sandbox_status_enabled : R$string.privacy_sandbox_status_disabled));
            }
        }
        this.h0.a(D());
        Preference preferenceF7 = f("third_party_cookies");
        if (preferenceF7 != null) {
            int iB = prefServiceK.b("profile.cookie_controls_mode");
            if (iB == 0) {
                i2 = org.chromium.components.browser_ui.site_settings.R$string.third_party_cookies_link_row_sub_label_enabled;
            } else if (iB == 1) {
                i2 = org.chromium.components.browser_ui.site_settings.R$string.third_party_cookies_link_row_sub_label_disabled;
            } else if (iB == 2) {
                i2 = org.chromium.components.browser_ui.site_settings.R$string.third_party_cookies_link_row_sub_label_disabled_incognito;
            }
            preferenceF7.Q(i2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        M0();
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!"https_first_mode".equals(preference.p)) {
            return true;
        }
        oj2.k(Profile.d()).e("https_only_mode_enabled", ((Boolean) obj).booleanValue());
        return true;
    }
}

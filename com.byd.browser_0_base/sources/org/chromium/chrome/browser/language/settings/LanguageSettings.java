package org.chromium.chrome.browser.language.settings;

import J.N;
import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceCategory;
import androidx.preference.d;
import defpackage.a8;
import defpackage.bq;
import defpackage.c01;
import defpackage.gx;
import defpackage.i62;
import defpackage.o51;
import defpackage.oj2;
import defpackage.ol0;
import defpackage.p51;
import defpackage.pd;
import defpackage.pp0;
import defpackage.q51;
import defpackage.qj2;
import defpackage.r51;
import defpackage.sk;
import defpackage.u6;
import defpackage.wp2;
import defpackage.xs0;
import defpackage.yj;
import defpackage.yp2;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.base.BuildInfo;
import org.chromium.base.BundleUtils;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.language.settings.AlwaysTranslateListFragment;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.language.settings.NeverTranslateListFragment;
import org.chromium.chrome.browser.language.settings.SelectLanguageFragment;
import org.chromium.chrome.browser.language.settings.c;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.base.ResourceBundle;

/* JADX INFO: loaded from: classes.dex */
public class LanguageSettings extends d implements SelectLanguageFragment.c, pp0, i62 {
    public static final /* synthetic */ int l0 = 0;
    public SettingsLauncher h0;
    public final a i0 = new a();
    public PrefChangeRegistrar j0;
    public Profile k0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.language_settings);
        this.j0 = new PrefChangeRegistrar();
        sk skVar = bq.a;
        int i = 0;
        if (N.M09VlOh_("DetailedLanguageSettings") || N.M09VlOh_("AppLanguagePrompt") || xs0.d.c) {
            if (a8.s == null) {
                a8.s = new a8();
            }
            a8.s.getClass();
            Object obj = BundleUtils.a;
            Log.i("cr_LanguageSettings", TextUtils.concat("Installed Languages: ", TextUtils.join(",", new HashSet(Arrays.asList(ResourceBundle.a)))).toString());
            qj2.a(this, R$xml.languages_detailed_preferences);
            ((PreferenceCategory) f("app_language_section")).T(N().getString(R$string.app_language_title, BuildInfo.a.a.a));
            LanguageItemPickerPreference languageItemPickerPreference = (LanguageItemPickerPreference) f("app_language_preference");
            languageItemPickerPreference.Z(u6.a());
            languageItemPickerPreference.Z = true;
            languageItemPickerPreference.a0();
            languageItemPickerPreference.setOnPreferenceClickListener(new r51(this, 3, 1, 2));
            a aVar = this.i0;
            aVar.getClass();
            FragmentActivity fragmentActivityD = D();
            aVar.e = fragmentActivityD;
            aVar.d = languageItemPickerPreference;
            aVar.a = new wp2(fragmentActivityD, (ViewGroup) fragmentActivityD.findViewById(R.id.content), null);
            ContentLanguagesPreference contentLanguagesPreference = (ContentLanguagesPreference) f("content_languages_preference");
            contentLanguagesPreference.V = this;
            PreferenceCategory preferenceCategory = (PreferenceCategory) f("translation_advanced_settings_section");
            preferenceCategory.setOnExpandButtonClickListener(new pd(9));
            preferenceCategory.U(M0().a("translate.enabled"));
            LanguageItemPickerPreference languageItemPickerPreference2 = (LanguageItemPickerPreference) f("translate_settings_target_language");
            languageItemPickerPreference2.Z(LocaleUtils.a(N.MMKf4EpW()));
            languageItemPickerPreference2.setOnPreferenceClickListener(new r51(this, 5, 2, 3));
            this.j0.a("translate_recent_target", new ol0(1, languageItemPickerPreference2));
            LanguageItemListPreference languageItemListPreference = (LanguageItemListPreference) f("translate_settings_always_languages");
            languageItemListPreference.Y = new AlwaysTranslateListFragment.a();
            languageItemListPreference.Z();
            this.j0.a("translate_allowlists", languageItemListPreference);
            languageItemListPreference.setOnPreferenceClickListener(new o51(this, languageItemListPreference, i));
            LanguageItemListPreference languageItemListPreference2 = (LanguageItemListPreference) f("translate_settings_never_languages");
            languageItemListPreference2.Y = new NeverTranslateListFragment.a();
            languageItemListPreference2.Z();
            this.j0.a("translate_blocked_languages", languageItemListPreference2);
            languageItemListPreference2.setOnPreferenceClickListener(new o51(this, languageItemListPreference2, i));
            ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("translate_switch");
            chromeSwitchPreference.Y(M0().a("translate.enabled"));
            chromeSwitchPreference.setOnPreferenceChangeListener(new q51(this, contentLanguagesPreference, preferenceCategory));
            chromeSwitchPreference.b0(new gx(14, this));
        } else {
            qj2.a(this, R$xml.languages_preferences);
            ContentLanguagesPreference contentLanguagesPreference2 = (ContentLanguagesPreference) f("preferred_languages");
            contentLanguagesPreference2.V = this;
            ChromeSwitchPreference chromeSwitchPreference2 = (ChromeSwitchPreference) f("translate_switch");
            chromeSwitchPreference2.Y(M0().a("translate.enabled"));
            chromeSwitchPreference2.setOnPreferenceChangeListener(new p51(this, contentLanguagesPreference2));
            chromeSwitchPreference2.b0(new c01(12, this));
        }
        c.h(0);
    }

    public final PrefService M0() {
        return oj2.k(this.k0);
    }

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        if (i2 != -1) {
            return;
        }
        String stringExtra = intent.getStringExtra("SelectLanguageFragment.SelectedLanguage");
        if (i == 1) {
            c cVarB = c.b();
            cVarB.getClass();
            N.Me60Lv4_(stringExtra, true);
            c.a aVar = cVarB.b;
            if (aVar != null) {
                ((ContentLanguagesPreference.a) aVar).I();
            }
            c.g(2);
            return;
        }
        if (i != 2) {
            if (i == 3) {
                ((LanguageItemPickerPreference) f("translate_settings_target_language")).Z(stringExtra);
                N.MMJjRfp9(stringExtra);
                c.g(10);
                return;
            }
            return;
        }
        c.g(9);
        a aVar2 = this.i0;
        aVar2.d.Z(stringExtra);
        aVar2.d.R(aVar2.e.getResources().getString(R$string.languages_split_downloading, aVar2.d.Y.c));
        aVar2.d.J(false);
        c01 c01Var = new c01(11, aVar2);
        Object obj = BundleUtils.a;
        if (!yj.a()) {
            SharedPreferencesManager.getInstance().o("Chrome.Language.ApplicationOverrideLanguage", stringExtra);
        }
        c01Var.p();
        if (TextUtils.equals(stringExtra, null)) {
            stringExtra = xs0.d.a.getLanguage();
        }
        N.MMJjRfp9(stringExtra);
    }

    @Override // defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.h0 = settingsLauncher;
    }

    @Override // androidx.fragment.app.Fragment
    public final void i0() {
        this.H = true;
        c.c = null;
        this.j0.b();
    }

    @Override // defpackage.i62
    public final void m(Profile profile) {
        this.k0 = profile;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void o0() {
        super.o0();
        a aVar = this.i0;
        if (aVar.b != null) {
            wp2 wp2Var = aVar.a;
            if (wp2Var.i) {
                ViewGroup viewGroup = (ViewGroup) aVar.e.findViewById(R.id.content);
                wp2Var.k = viewGroup;
                yp2 yp2Var = wp2Var.f;
                if (yp2Var != null) {
                    yp2Var.b(viewGroup);
                }
                aVar.a.c(aVar.b);
                aVar.b = null;
            }
        }
    }
}

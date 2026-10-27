package org.chromium.chrome.browser.night_mode.settings;

import J.N;
import android.os.Bundle;
import androidx.preference.d;
import defpackage.bq;
import defpackage.i62;
import defpackage.oj2;
import defpackage.p20;
import defpackage.qj2;
import defpackage.qk0;
import defpackage.sk;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeSettingsFragment extends d implements p20, i62 {
    public static final /* synthetic */ int j0 = 0;
    public boolean h0;
    public Profile i0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.theme_preferences);
        D().setTitle(R$string.theme_settings);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        RadioButtonGroupThemePreference radioButtonGroupThemePreference = (RadioButtonGroupThemePreference) f("ui_theme_pref");
        this.h0 = N.MJSt3Ocq(this.i0, 72);
        int iD = SharedPreferencesManager.getInstance().d(-1, "ui_theme_setting");
        if (iD == -1) {
            iD = 0;
        }
        boolean z = this.h0;
        radioButtonGroupThemePreference.S = iD;
        radioButtonGroupThemePreference.X = z;
        radioButtonGroupThemePreference.setOnPreferenceChangeListener(new qk0(this, 2, radioButtonGroupThemePreference, sharedPreferencesManager));
        if (bundle == null) {
            oj2.U(this.j.getInt("theme_settings_entry"), 3, "Android.DarkTheme.ThemeSettingsEntry");
        }
        sk skVar = bq.a;
        if (N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting")) {
            oj2.t(this.i0).notifyEvent("auto_dark_settings_opened");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        this.H = true;
    }

    @Override // defpackage.i62
    public final void m(Profile profile) {
        this.i0 = profile;
    }

    @Override // defpackage.p20
    public final void t() {
    }
}

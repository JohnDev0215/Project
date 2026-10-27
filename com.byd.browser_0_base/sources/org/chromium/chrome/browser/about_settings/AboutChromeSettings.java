package org.chromium.chrome.browser.about_settings;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.C0179d;
import defpackage.qj2;
import defpackage.xz;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes.dex */
public class AboutChromeSettings extends d implements Preference.d {
    public static final /* synthetic */ int h0 = 0;

    public AboutChromeSettings() {
        SharedPreferencesManager.getInstance().readBoolean("developer", false);
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        String str2;
        D().setTitle(R$string.prefs_about_version);
        qj2.a(this, R$xml.about_chrome_preferences);
        Preference preferenceF = f("application_version");
        D();
        Context context = xz.a;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            Log.e("AboutSettingsBridge", e.toString());
            str2 = "";
        }
        preferenceF.R(str2);
        preferenceF.setOnPreferenceClickListener(this);
        f("os_version").R(N.M6bT9QjF());
        f("privacy_policy").setOnPreferenceClickListener(new C0179d(0, this));
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (!preference.p.equals("privacy_policy")) {
            return true;
        }
        F0(new Intent(D(), (Class<?>) PrivacyActivity.class));
        return true;
    }
}

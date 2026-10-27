package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.preference.Preference;
import defpackage.n3;
import defpackage.pd;
import defpackage.qj2;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacySandboxSettingsFragmentV3 extends PrivacySandboxSettingsBaseFragment implements Preference.c {
    public static final /* synthetic */ int l0 = 0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.prefs_privacy_sandbox);
        qj2.a(this, R$xml.privacy_sandbox_preferences_v3);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("privacy_sandbox_toggle");
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        chromeSwitchPreference.b0(new pd(11));
        chromeSwitchPreference.Y(N.MhaiireD());
        ChromeBasePreference chromeBasePreference = (ChromeBasePreference) f("privacy_sandbox_learn_more");
        SpannableString spannableString = new SpannableString(N().getString(R$string.privacy_sandbox_about_ad_personalization_link));
        spannableString.setSpan(new ForegroundColorSpan(K().getColor(R$color.default_text_color_link_baseline)), 0, spannableString.length(), 17);
        chromeBasePreference.T(spannableString);
        N0();
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!"privacy_sandbox_toggle".equals(preference.p)) {
            return true;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        n3.X0(zBooleanValue ? "Settings.PrivacySandbox.ApisEnabled" : "Settings.PrivacySandbox.ApisDisabled");
        N.Mx0_lgx5(zBooleanValue);
        return true;
    }
}

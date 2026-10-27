package org.chromium.chrome.browser.privacy_sandbox.v4;

import android.os.Bundle;
import androidx.preference.Preference;
import defpackage.i83;
import defpackage.n3;
import defpackage.oj2;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes2.dex */
public class AdMeasurementFragmentV4 extends PrivacySandboxSettingsBaseFragment implements Preference.c {
    public static final /* synthetic */ int l0 = 0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.settings_ad_measurement_page_title);
        qj2.a(this, R$xml.ad_measurement_preference_v4);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("ad_measurement_toggle");
        chromeSwitchPreference.Y(oj2.k(Profile.d()).a("privacy_sandbox.m1.ad_measurement_enabled"));
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        chromeSwitchPreference.b0(new i83(11));
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!preference.p.equals("ad_measurement_toggle")) {
            return false;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        n3.X0(zBooleanValue ? "Settings.PrivacySandbox.AdMeasurement.Enabled" : "Settings.PrivacySandbox.AdMeasurement.Disabled");
        oj2.k(Profile.d()).e("privacy_sandbox.m1.ad_measurement_enabled", zBooleanValue);
        return true;
    }
}

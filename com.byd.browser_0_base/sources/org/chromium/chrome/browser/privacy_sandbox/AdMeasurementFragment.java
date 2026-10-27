package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.os.Bundle;
import defpackage.iq2;
import defpackage.ko0;
import defpackage.qj2;
import defpackage.un1;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* JADX INFO: loaded from: classes2.dex */
public class AdMeasurementFragment extends PrivacySandboxSettingsBaseFragment {
    public Runnable l0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.privacy_sandbox_ad_measurement_title);
        qj2.a(this, R$xml.ad_measurement_preference);
        ((ChromeBasePreference) f("ad_measurement_description")).R(iq2.a(N().getString(N.MhaiireD() ? R$string.privacy_sandbox_ad_measurement_description_trials_on : R$string.privacy_sandbox_ad_measurement_description_trials_off), new iq2.a(new un1(K(), new ko0(29, this)), "<link>", "</link>")));
    }
}

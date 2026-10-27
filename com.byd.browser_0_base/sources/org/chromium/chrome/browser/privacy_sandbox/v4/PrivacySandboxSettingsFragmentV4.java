package org.chromium.chrome.browser.privacy_sandbox.v4;

import android.os.Bundle;
import defpackage.gq2;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacySandboxSettingsFragmentV4 extends PrivacySandboxSettingsBaseFragment {
    public ChromeBasePreference l0;
    public ChromeBasePreference m0;
    public ChromeBasePreference n0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.ad_privacy_page_title);
        qj2.a(this, R$xml.privacy_sandbox_preferences_v4);
        N0();
        this.l0 = (ChromeBasePreference) f("topics");
        this.m0 = (ChromeBasePreference) f("fledge");
        this.n0 = (ChromeBasePreference) f("ad_measurement");
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        this.l0.Q(gq2.l("privacy_sandbox.m1.topics_enabled") ? R$string.ad_privacy_page_topics_link_row_sub_label_enabled : R$string.ad_privacy_page_topics_link_row_sub_label_disabled);
        this.m0.Q(gq2.l("privacy_sandbox.m1.fledge_enabled") ? R$string.ad_privacy_page_fledge_link_row_sub_label_enabled : R$string.ad_privacy_page_fledge_link_row_sub_label_disabled);
        this.n0.Q(gq2.l("privacy_sandbox.m1.ad_measurement_enabled") ? R$string.ad_privacy_page_ad_measurement_link_row_sub_label_enabled : R$string.ad_privacy_page_ad_measurement_link_row_sub_label_disabled);
    }
}

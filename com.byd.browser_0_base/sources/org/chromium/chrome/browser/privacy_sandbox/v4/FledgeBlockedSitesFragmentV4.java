package org.chromium.chrome.browser.privacy_sandbox.v4;

import J.N;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import defpackage.n3;
import defpackage.qj2;
import java.util.Arrays;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy_sandbox.FledgePreference;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: loaded from: classes2.dex */
public class FledgeBlockedSitesFragmentV4 extends PrivacySandboxSettingsBaseFragment implements Preference.d {
    public PreferenceCategory l0;
    public LargeIconBridge m0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.settings_fledge_page_blocked_sites_sub_page_title);
        qj2.a(this, R$xml.block_list_preference_v4);
        this.l0 = (PreferenceCategory) f("block_list");
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void g0() {
        super.g0();
        LargeIconBridge largeIconBridge = this.m0;
        if (largeIconBridge != null) {
            largeIconBridge.a();
        }
        this.m0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        if (this.m0 == null) {
            this.m0 = new LargeIconBridge(Profile.d());
        }
        this.l0.c0();
        for (String str : Arrays.asList(N.MCGJWOhZ())) {
            FledgePreference fledgePreference = new FledgePreference(K(), str, this.m0);
            int i = R$drawable.ic_add;
            String string = N().getString(R$string.settings_fledge_page_allow_site_a11y_label, str);
            fledgePreference.Y = i;
            fledgePreference.Z = string;
            fledgePreference.Y(false);
            fledgePreference.setOnPreferenceClickListener(this);
            this.l0.Y(fledgePreference);
        }
        PreferenceCategory preferenceCategory = this.l0;
        preferenceCategory.Q(preferenceCategory.b0() == 0 ? R$string.settings_fledge_page_blocked_sites_description_empty : R$string.settings_fledge_page_blocked_sites_description);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        super.q0(view, bundle);
        this.b0.setItemAnimator(null);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (!(preference instanceof FledgePreference)) {
            return false;
        }
        N.MK6T9EFy(((FledgePreference) preference).a0, true);
        this.l0.d0(preference);
        PreferenceCategory preferenceCategory = this.l0;
        preferenceCategory.Q(preferenceCategory.b0() == 0 ? R$string.settings_fledge_page_blocked_sites_description_empty : R$string.settings_fledge_page_blocked_sites_description);
        O0(R$string.settings_fledge_page_add_site_snackbar, 54);
        n3.X0("Settings.PrivacySandbox.Fledge.SiteAdded");
        return true;
    }
}

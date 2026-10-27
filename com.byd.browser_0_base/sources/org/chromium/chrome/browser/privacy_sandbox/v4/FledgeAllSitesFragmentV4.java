package org.chromium.chrome.browser.privacy_sandbox.v4;

import J.N;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import defpackage.ad0;
import defpackage.n3;
import defpackage.td2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.privacy_sandbox.FledgePreference;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: loaded from: classes2.dex */
public class FledgeAllSitesFragmentV4 extends PrivacySandboxSettingsBaseFragment implements Preference.d {
    public PreferenceScreen l0;
    public LargeIconBridge m0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.settings_fledge_all_sites_sub_page_title);
        PreferenceManager preferenceManager = this.a0;
        PreferenceScreen preferenceScreenA = preferenceManager.a(preferenceManager.a);
        this.l0 = preferenceScreenA;
        L0(preferenceScreenA);
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
        N.MfWQDaSM(new ad0(new td2(15, this), 3));
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
        N.MK6T9EFy(((FledgePreference) preference).a0, false);
        this.l0.d0(preference);
        O0(R$string.settings_fledge_page_block_site_snackbar, 55);
        n3.X0("Settings.PrivacySandbox.Fledge.SiteRemoved");
        return true;
    }
}

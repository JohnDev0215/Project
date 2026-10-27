package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import defpackage.ad0;
import defpackage.dm0;
import defpackage.n3;
import defpackage.qj2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: loaded from: classes2.dex */
public class AdPersonalizationFragment extends PrivacySandboxSettingsBaseFragment implements Preference.d {
    public static final /* synthetic */ int u0 = 0;
    public PreferenceCategory l0;
    public ChromeBasePreference m0;
    public Preference n0;
    public PreferenceCategory o0;
    public ChromeBasePreference p0;
    public Preference q0;
    public Preference r0;
    public LargeIconBridge s0;
    public SettingsLauncher t0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.privacy_sandbox_ad_personalization_title);
        qj2.a(this, R$xml.ad_personalization_preference);
        this.r0 = f("ad_personalization_description");
        this.l0 = (PreferenceCategory) f("topic_interests");
        this.m0 = (ChromeBasePreference) f("empty_topics");
        this.n0 = f("removed_topics");
        this.o0 = (PreferenceCategory) f("fledge_interests");
        this.p0 = (ChromeBasePreference) f("empty_fledge");
        this.q0 = f("removed_sites");
    }

    public final void P0() {
        this.m0.U(this.l0.b0() == 0);
        this.p0.U(this.o0.b0() == 0);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewE0 = super.e0(layoutInflater, viewGroup, bundle);
        this.b0.setItemAnimator(null);
        return viewE0;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void g0() {
        super.g0();
        LargeIconBridge largeIconBridge = this.s0;
        if (largeIconBridge != null) {
            largeIconBridge.a();
            this.s0 = null;
        }
    }

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.t0 = settingsLauncher;
    }

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_targeted_help) {
            return false;
        }
        this.t0.g(K(), LearnMoreFragment.class);
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        N.MfWQDaSM(new ad0(new dm0(23, this), 3));
        this.H = true;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (preference instanceof TopicPreference) {
            Topic topic = ((TopicPreference) preference).a0;
            N.MUKJJ8VA(topic.a, topic.b, false);
            this.l0.d0(preference);
            O0(R$string.privacy_sandbox_remove_interest_snackbar, 50);
            n3.X0("Settings.PrivacySandbox.AdPersonalization.TopicRemoved");
        } else if (preference instanceof FledgePreference) {
            N.MK6T9EFy(((FledgePreference) preference).a0, false);
            this.o0.d0(preference);
            O0(R$string.privacy_sandbox_remove_site_snackbar, 55);
            n3.X0("Settings.PrivacySandbox.AdPersonalization.SiteRemoved");
        }
        P0();
        return true;
    }
}

package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import defpackage.n3;
import defpackage.no0;
import defpackage.qj2;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: loaded from: classes2.dex */
public class AdPersonalizationRemovedFragment extends PrivacySandboxSettingsBaseFragment implements Preference.d {
    public PreferenceCategory l0;
    public Preference m0;
    public PreferenceCategory n0;
    public Preference o0;
    public LargeIconBridge p0;
    public SettingsLauncher q0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.privacy_sandbox_remove_interest_title);
        qj2.a(this, R$xml.ad_personalization_removed_preference);
        this.l0 = (PreferenceCategory) f("topic_interests");
        this.m0 = f("empty_topics");
        this.n0 = (PreferenceCategory) f("fledge_interests");
        this.o0 = f("empty_fledge");
        if (this.p0 == null) {
            this.p0 = new LargeIconBridge(Profile.d());
        }
        List<Topic> listAsList = Arrays.asList((Topic[]) N.M7p7P4Yj());
        Collections.sort(listAsList, new no0(2));
        for (Topic topic : listAsList) {
            TopicPreference topicPreference = new TopicPreference(K(), topic);
            int i = R$drawable.ic_add;
            String string = N().getString(R$string.privacy_sandbox_add_interest_button_description, topic.c);
            topicPreference.Y = i;
            topicPreference.Z = string;
            topicPreference.Y(false);
            topicPreference.setOnPreferenceClickListener(this);
            this.l0.Y(topicPreference);
        }
        for (String str2 : Arrays.asList(N.MCGJWOhZ())) {
            FledgePreference fledgePreference = new FledgePreference(K(), str2, this.p0);
            int i2 = R$drawable.ic_add;
            String string2 = N().getString(R$string.privacy_sandbox_add_site_button_description, str2);
            fledgePreference.Y = i2;
            fledgePreference.Z = string2;
            fledgePreference.Y(false);
            fledgePreference.setOnPreferenceClickListener(this);
            this.n0.Y(fledgePreference);
        }
        P0();
    }

    public final void P0() {
        this.m0.U(this.l0.b0() == 0);
        this.o0.U(this.n0.b0() == 0);
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
        LargeIconBridge largeIconBridge = this.p0;
        if (largeIconBridge != null) {
            largeIconBridge.a();
            this.p0 = null;
        }
    }

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.q0 = settingsLauncher;
    }

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_targeted_help) {
            return false;
        }
        this.q0.g(K(), LearnMoreFragment.class);
        return true;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (preference instanceof TopicPreference) {
            Topic topic = ((TopicPreference) preference).a0;
            N.MUKJJ8VA(topic.a, topic.b, true);
            this.l0.d0(preference);
            O0(R$string.privacy_sandbox_add_interest_snackbar, 49);
            n3.X0("Settings.PrivacySandbox.RemovedInterests.TopicAdded");
        } else if (preference instanceof FledgePreference) {
            N.MK6T9EFy(((FledgePreference) preference).a0, true);
            this.n0.d0(preference);
            O0(R$string.privacy_sandbox_add_site_snackbar, 54);
            n3.X0("Settings.PrivacySandbox.RemovedInterests.SiteAdded");
        }
        P0();
        return true;
    }
}

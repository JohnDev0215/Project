package org.chromium.chrome.browser.privacy_sandbox.v4;

import J.N;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import defpackage.gq2;
import defpackage.i83;
import defpackage.iq2;
import defpackage.lj1;
import defpackage.n3;
import defpackage.no0;
import defpackage.oj2;
import defpackage.on0;
import defpackage.qj2;
import defpackage.td2;
import defpackage.un1;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.privacy_sandbox.Topic;
import org.chromium.chrome.browser.privacy_sandbox.TopicPreference;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.ClickableSpansTextMessagePreference;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* JADX INFO: loaded from: classes2.dex */
public class TopicsFragmentV4 extends PrivacySandboxSettingsBaseFragment implements Preference.c, Preference.d {
    public static final /* synthetic */ int r0 = 0;
    public ChromeSwitchPreference l0;
    public PreferenceCategoryWithClickableSummary m0;
    public PreferenceCategory n0;
    public TextMessagePreference o0;
    public TextMessagePreference p0;
    public ClickableSpansTextMessagePreference q0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.settings_topics_page_title);
        qj2.a(this, R$xml.topics_preference_v4);
        this.l0 = (ChromeSwitchPreference) f("topics_toggle");
        this.m0 = (PreferenceCategoryWithClickableSummary) f("topics_heading");
        this.n0 = (PreferenceCategory) f("current_topics");
        this.o0 = (TextMessagePreference) f("topics_empty");
        this.p0 = (TextMessagePreference) f("topics_disabled");
        this.q0 = (ClickableSpansTextMessagePreference) f("topics_page_footer");
        this.l0.Y(oj2.k(Profile.d()).a("privacy_sandbox.m1.topics_enabled"));
        this.l0.setOnPreferenceChangeListener(this);
        this.l0.b0(new i83(12));
        this.m0.R(iq2.a(N().getString(R$string.settings_topics_page_current_topics_description), new iq2.a(new un1(K(), new td2(17, this)), "<link>", "</link>")));
        this.q0.R(iq2.a(N().getString(R$string.settings_topics_page_footer), new iq2.a(new un1(K(), new lj1(17, this)), "<link1>", "</link1>"), new iq2.a(new un1(K(), new on0(1, this)), "<link2>", "</link2>")));
    }

    public final void P0() {
        boolean zL = gq2.l("privacy_sandbox.m1.topics_enabled");
        boolean z = this.n0.b0() == 0;
        this.p0.U(!zL);
        this.o0.U(zL && z);
        this.n0.U(zL && !z);
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        this.n0.c0();
        List<Topic> listAsList = Arrays.asList((Topic[]) N.M9$8x7Sf());
        Collections.sort(listAsList, new no0(2));
        for (Topic topic : listAsList) {
            TopicPreference topicPreference = new TopicPreference(K(), topic);
            int i = R$drawable.btn_close;
            String string = N().getString(R$string.privacy_sandbox_remove_interest_button_description, topic.c);
            topicPreference.Y = i;
            topicPreference.Z = string;
            topicPreference.V = Boolean.FALSE;
            topicPreference.setOnPreferenceClickListener(this);
            this.n0.Y(topicPreference);
        }
        P0();
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!preference.p.equals("topics_toggle")) {
            return false;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        n3.X0(zBooleanValue ? "Settings.PrivacySandbox.Topics.Enabled" : "Settings.PrivacySandbox.Topics.Disabled");
        oj2.k(Profile.d()).e("privacy_sandbox.m1.topics_enabled", zBooleanValue);
        P0();
        N.MydrSrPL(zBooleanValue);
        return true;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        super.q0(view, bundle);
        this.b0.setItemAnimator(null);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (!(preference instanceof TopicPreference)) {
            return false;
        }
        Topic topic = ((TopicPreference) preference).a0;
        N.MUKJJ8VA(topic.a, topic.b, false);
        this.n0.d0(preference);
        P0();
        O0(R$string.settings_topics_page_block_topic_snackbar, 50);
        n3.X0("Settings.PrivacySandbox.Topics.TopicRemoved");
        return true;
    }
}

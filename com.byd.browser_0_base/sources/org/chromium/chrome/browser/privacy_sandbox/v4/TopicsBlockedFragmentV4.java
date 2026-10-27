package org.chromium.chrome.browser.privacy_sandbox.v4;

import J.N;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import defpackage.n3;
import defpackage.no0;
import defpackage.qj2;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.privacy_sandbox.Topic;
import org.chromium.chrome.browser.privacy_sandbox.TopicPreference;

/* JADX INFO: loaded from: classes2.dex */
public class TopicsBlockedFragmentV4 extends PrivacySandboxSettingsBaseFragment implements Preference.d {
    public PreferenceCategory l0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.settings_topics_page_blocked_topics_sub_page_title);
        qj2.a(this, R$xml.block_list_preference_v4);
        this.l0 = (PreferenceCategory) f("block_list");
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        this.l0.c0();
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
        PreferenceCategory preferenceCategory = this.l0;
        preferenceCategory.Q(preferenceCategory.b0() == 0 ? R$string.settings_topics_page_blocked_topics_description_empty : R$string.settings_topics_page_blocked_topics_description);
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
        N.MUKJJ8VA(topic.a, topic.b, true);
        this.l0.d0(preference);
        PreferenceCategory preferenceCategory = this.l0;
        preferenceCategory.Q(preferenceCategory.b0() == 0 ? R$string.settings_topics_page_blocked_topics_description_empty : R$string.settings_topics_page_blocked_topics_description);
        O0(R$string.settings_topics_page_add_topic_snackbar, 49);
        n3.X0("Settings.PrivacySandbox.Topics.TopicAdded");
        return true;
    }
}

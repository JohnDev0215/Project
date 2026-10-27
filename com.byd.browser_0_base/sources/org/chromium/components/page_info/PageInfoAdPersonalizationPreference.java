package org.chromium.components.page_info;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import defpackage.an2;
import defpackage.kr;
import defpackage.qj2;
import java.util.List;
import org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment;

/* JADX INFO: loaded from: classes3.dex */
public class PageInfoAdPersonalizationPreference extends SiteSettingsPreferenceFragment implements Preference.d {
    public a i0;

    public static class a {
        public boolean a;
        public List<String> b;
        public an2 c;
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        if (!(this.h0 != null)) {
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(M());
            aVar.k(this);
            aVar.g();
        } else {
            qj2.a(this, R$xml.page_info_ad_personalization_preference);
            Preference preferenceF = f("manage_interest_button");
            preferenceF.setOnPreferenceClickListener(this);
            preferenceF.S(((kr) this.h0).c() ? R$string.page_info_ad_privacy_subpage_manage_button : R$string.page_info_ad_manage_interests);
            M0();
        }
    }

    public final void M0() {
        a aVar;
        if (this.a0 == null || (aVar = this.i0) == null) {
            return;
        }
        f("personalization_summary").Q((!aVar.a || aVar.b.isEmpty()) ? this.i0.a ? ((kr) this.h0).c() ? R$string.page_info_ad_privacy_fledge_description : R$string.page_info_ad_personalization_interest_group_description : ((kr) this.h0).c() ? R$string.page_info_ad_privacy_topics_description : R$string.page_info_ad_personalization_topics_description : ((kr) this.h0).c() ? R$string.page_info_ad_privacy_topics_and_fledge_description : R$string.page_info_ad_personalization_topics_and_interest_group_description);
        Preference preferenceF = f("topic_info");
        preferenceF.U(!this.i0.b.isEmpty());
        preferenceF.T(TextUtils.join("\n\n", this.i0.b));
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (!preference.p.equals("manage_interest_button")) {
            return false;
        }
        a aVar = this.i0;
        if (aVar == null) {
            return true;
        }
        aVar.c.run();
        return true;
    }
}

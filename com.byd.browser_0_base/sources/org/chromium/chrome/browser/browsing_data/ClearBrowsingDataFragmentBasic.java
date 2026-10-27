package org.chromium.chrome.browser.browsing_data;

import android.os.Bundle;
import androidx.preference.Preference;
import defpackage.iq2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.qe3;
import defpackage.qt;
import defpackage.un1;
import defpackage.z7;
import java.util.Arrays;
import java.util.List;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ClickableSpansTextMessagePreference;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* JADX INFO: loaded from: classes.dex */
public class ClearBrowsingDataFragmentBasic extends ClearBrowsingDataFragment {
    public static final /* synthetic */ int q0 = 0;
    public a p0;

    public interface a {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        super.J0(bundle, str);
        Profile profileD = Profile.d();
        z7.m().getClass();
        IdentityManager identityManagerV = z7.v(profileD);
        ClickableSpansTextMessagePreference clickableSpansTextMessagePreference = (ClickableSpansTextMessagePreference) f("clear_google_data_text");
        Preference preferenceF = f("clear_search_history_non_google_text");
        TemplateUrlService templateUrlServiceQ = oj2.q(profileD);
        TemplateUrl templateUrlC = templateUrlServiceQ.c();
        boolean zE = templateUrlServiceQ.e();
        boolean z = true;
        int i = 0;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        Object[] objArr4 = 0;
        if (templateUrlC == null || !identityManagerV.b(0)) {
            Preference preferenceF2 = f("clear_google_data_text");
            if (preferenceF2 != null) {
                I0().d0(preferenceF2);
            }
        } else if (zE) {
            clickableSpansTextMessagePreference.R(iq2.a(K().getString(R$string.clear_search_history_link), new iq2.a(new un1(K(), new qt(this, i, z)), "<link1>", "</link1>"), new iq2.a(new un1(K(), new qt(this, objArr4 == true ? 1 : 0, objArr3 == true ? 1 : 0)), "<link2>", "</link2>")));
        } else {
            clickableSpansTextMessagePreference.R(iq2.a(K().getString(R$string.clear_search_history_link_other_forms), new iq2.a(new un1(K(), new qt(this, objArr2 == true ? 1 : 0, objArr == true ? 1 : 0)), "<link1>", "</link1>")));
        }
        if (templateUrlC == null || zE) {
            Preference preferenceF3 = f("clear_search_history_non_google_text");
            if (preferenceF3 != null) {
                I0().d0(preferenceF3);
                return;
            }
            return;
        }
        if (templateUrlC.a()) {
            preferenceF.R(K().getString(R$string.clear_search_history_non_google_dse, templateUrlC.c()));
        } else {
            preferenceF.Q(R$string.clear_search_history_non_google_dse_unknown);
        }
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public final int O0() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public final List<Integer> Q0() {
        return Arrays.asList(0, 1, 2);
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public final void T0() {
        oj2.U(0, 2, "History.ClearBrowsingData.UserDeletedFromTab");
        n3.X0("ClearBrowsingData_BasicTab");
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference = (ClearBrowsingDataCheckBoxPreference) f(ClearBrowsingDataFragment.R0(0));
        ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference2 = (ClearBrowsingDataCheckBoxPreference) f(ClearBrowsingDataFragment.R0(1));
        clearBrowsingDataCheckBoxPreference.Z = new qe3(4);
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        if (z7.v(profileD).b(0)) {
            clearBrowsingDataCheckBoxPreference2.Q(R$string.clear_cookies_and_site_data_summary_basic_signed_in);
        }
    }
}

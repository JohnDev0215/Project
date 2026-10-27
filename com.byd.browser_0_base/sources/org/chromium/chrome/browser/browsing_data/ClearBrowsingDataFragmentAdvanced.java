package org.chromium.chrome.browser.browsing_data;

import android.os.Bundle;
import androidx.preference.Preference;
import defpackage.n3;
import defpackage.oj2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ClearBrowsingDataFragmentAdvanced extends ClearBrowsingDataFragment {
    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        super.J0(bundle, str);
        Preference preferenceF = f("clear_google_data_text");
        if (preferenceF != null) {
            I0().d0(preferenceF);
        }
        Preference preferenceF2 = f("clear_search_history_non_google_text");
        if (preferenceF2 != null) {
            I0().d0(preferenceF2);
        }
        f("sign_out_of_chrome_text");
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public final int O0() {
        return 1;
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public final List<Integer> Q0() {
        return Arrays.asList(0, 1, 2, 3, 4, 5);
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public final void T0() {
        oj2.U(1, 2, "History.ClearBrowsingData.UserDeletedFromTab");
        n3.X0("ClearBrowsingData_AdvancedTab");
    }
}

package org.chromium.chrome.browser.safe_browsing.settings;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.preference.d;
import defpackage.fu0;
import defpackage.gp0;
import defpackage.hu0;
import defpackage.qj2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SafeBrowsingSettingsFragmentBase extends d implements gp0 {
    public fu0 h0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, M0());
        D().setTitle(R$string.prefs_section_safe_browsing_title);
        N0();
        D0();
    }

    public abstract int M0();

    @Override // defpackage.gp0
    public final void d(fu0 fu0Var) {
        this.h0 = fu0Var;
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_targeted_help) {
            return false;
        }
        ((hu0) this.h0).c(D(), O(R$string.help_context_safe_browsing), null, Profile.d());
        return true;
    }

    public void N0() {
    }
}

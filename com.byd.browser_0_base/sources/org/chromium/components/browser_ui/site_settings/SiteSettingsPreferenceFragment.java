package org.chromium.components.browser_ui.site_settings;

import defpackage.kr;
import defpackage.zn2;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SiteSettingsPreferenceFragment extends androidx.preference.d {
    public zn2 h0;

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public void g0() {
        kr krVar;
        LargeIconBridge largeIconBridge;
        super.g0();
        zn2 zn2Var = this.h0;
        if (zn2Var == null || (largeIconBridge = (krVar = (kr) zn2Var).e) == null) {
            return;
        }
        largeIconBridge.a();
        krVar.e = null;
    }
}

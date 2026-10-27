package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import defpackage.bq;
import defpackage.iv;
import defpackage.kr;
import defpackage.p20;
import defpackage.qj2;
import defpackage.sk;
import defpackage.yn2;

/* JADX INFO: loaded from: classes2.dex */
public class SiteSettings extends SiteSettingsPreferenceFragment implements Preference.d, p20 {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, ((kr) this.h0).c() ? R$xml.site_settings_preferences_with_categories : R$xml.site_settings_preferences);
        D().setTitle(K().getString(R$string.prefs_site_settings));
        for (int i = 0; i < 29; i++) {
            ((kr) this.h0).getClass();
            boolean zMK7GTxrW = true;
            if (i == 1) {
                zMK7GTxrW = N.MK7GTxrW("SubresourceFilter");
            } else if (i == 5) {
                zMK7GTxrW = iv.e().g("enable-experimental-web-platform-features");
            } else if (i == 13) {
                zMK7GTxrW = N.Mudil8Bg("WebNFC");
            } else if (i == 20) {
                zMK7GTxrW = N.Mudil8Bg("WebBluetoothNewPermissionsBackend");
            } else if (i == 23) {
                sk skVar = bq.a;
                zMK7GTxrW = N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting");
            } else if (i == 25) {
                zMK7GTxrW = N.Mudil8Bg("FedCm");
            } else if (i == 28) {
                sk skVar2 = bq.a;
                zMK7GTxrW = N.M09VlOh_("TrustTokens");
            }
            if (!zMK7GTxrW) {
                I0().d0(f(yn2.m(i)));
            }
        }
        M0();
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x016e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void M0() {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SiteSettings.M0():void");
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        M0();
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        preference.m().putString("category", preference.p);
        preference.m().putString("title", preference.l.toString());
        return false;
    }

    @Override // defpackage.p20
    public final void t() {
    }
}

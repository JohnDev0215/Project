package org.chromium.chrome.browser.privacy.secure_dns;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import defpackage.c01;
import defpackage.i83;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsProviderPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes2.dex */
public class SecureDnsSettings extends androidx.preference.d {
    public static final /* synthetic */ int j0 = 0;
    public ChromeSwitchPreference h0;
    public SecureDnsProviderPreference i0;

    public static boolean M0(SecureDnsSettings secureDnsSettings, Object obj) {
        secureDnsSettings.getClass();
        SecureDnsProviderPreference.a aVar = (SecureDnsProviderPreference.a) obj;
        boolean zO0 = O0(secureDnsSettings.h0.S, aVar);
        if (zO0 == aVar.c) {
            return true;
        }
        SecureDnsProviderPreference secureDnsProviderPreference = secureDnsSettings.i0;
        SecureDnsProviderPreference.a aVar2 = new SecureDnsProviderPreference.a(aVar.b, aVar.a, zO0);
        if (!aVar2.equals(secureDnsProviderPreference.d0)) {
            secureDnsProviderPreference.d0 = aVar2;
            secureDnsProviderPreference.Z();
        }
        return false;
    }

    public static boolean O0(boolean z, SecureDnsProviderPreference.a aVar) {
        if (!z) {
            N.M7D0A6Nn(0);
            N.MPV_PP8Y("");
        } else if (aVar.a) {
            String str = aVar.b;
            if (str.isEmpty() || !N.MPV_PP8Y(str)) {
                return false;
            }
            N.M7D0A6Nn(2);
        } else {
            N.M7D0A6Nn(1);
            N.MPV_PP8Y("");
        }
        return true;
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        int iMPUFHf86;
        D().setTitle(R$string.settings_secure_dns_title);
        qj2.a(this, R$xml.secure_dns_settings);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("secure_dns_switch");
        this.h0 = chromeSwitchPreference;
        chromeSwitchPreference.b0(new i83(10));
        this.h0.setOnPreferenceChangeListener(new Preference.c() { // from class: org.chromium.chrome.browser.privacy.secure_dns.e
            @Override // androidx.preference.Preference.c
            public final boolean n(Preference preference, Object obj) {
                int i = SecureDnsSettings.j0;
                SecureDnsSettings secureDnsSettings = this.e;
                secureDnsSettings.getClass();
                SecureDnsSettings.O0(((Boolean) obj).booleanValue(), secureDnsSettings.i0.d0);
                secureDnsSettings.N0();
                return true;
            }
        });
        if (!N.M_qct0Io() && (iMPUFHf86 = N.MPUFHf86()) != 0) {
            this.h0.J(false);
            boolean z = iMPUFHf86 == 2;
            ChromeSwitchPreference chromeSwitchPreference2 = this.h0;
            chromeSwitchPreference2.U = chromeSwitchPreference2.e.getString(z ? R$string.settings_secure_dns_disabled_for_parental_control : R$string.settings_secure_dns_disabled_for_managed_environment);
            if (!chromeSwitchPreference2.S) {
                chromeSwitchPreference2.s();
            }
        }
        SecureDnsProviderPreference secureDnsProviderPreference = (SecureDnsProviderPreference) f("secure_dns_provider");
        this.i0 = secureDnsProviderPreference;
        secureDnsProviderPreference.setOnPreferenceChangeListener(new c01(15, this));
        N0();
    }

    public final void N0() {
        int iMvJZm_HK = N.MvJZm_HK();
        boolean z = iMvJZm_HK != 0;
        boolean z2 = N.M_qct0Io() || N.MPUFHf86() != 0;
        this.h0.Y(z);
        this.i0.J(z && !z2);
        boolean z3 = iMvJZm_HK == 2;
        String strMBuwU61d = N.MBuwU61d();
        SecureDnsProviderPreference secureDnsProviderPreference = this.i0;
        SecureDnsProviderPreference.a aVar = new SecureDnsProviderPreference.a(strMBuwU61d, z3, true);
        if (aVar.equals(secureDnsProviderPreference.d0)) {
            return;
        }
        secureDnsProviderPreference.d0 = aVar;
        secureDnsProviderPreference.Z();
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        N0();
    }
}

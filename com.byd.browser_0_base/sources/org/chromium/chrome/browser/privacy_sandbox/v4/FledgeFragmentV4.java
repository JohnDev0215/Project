package org.chromium.chrome.browser.privacy_sandbox.v4;

import J.N;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import defpackage.ad0;
import defpackage.dm0;
import defpackage.gq2;
import defpackage.iq2;
import defpackage.lj1;
import defpackage.n3;
import defpackage.oj2;
import defpackage.on0;
import defpackage.qj2;
import defpackage.td2;
import defpackage.un1;
import defpackage.wd0;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.privacy_sandbox.FledgePreference;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.ClickableSpansTextMessagePreference;
import org.chromium.components.browser_ui.settings.TextMessagePreference;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: loaded from: classes2.dex */
public class FledgeFragmentV4 extends PrivacySandboxSettingsBaseFragment implements Preference.c, Preference.d {
    public static final /* synthetic */ int u0 = 0;
    public ChromeSwitchPreference l0;
    public PreferenceCategoryWithClickableSummary m0;
    public PreferenceCategory n0;
    public TextMessagePreference o0;
    public TextMessagePreference p0;
    public ChromeBasePreference q0;
    public LargeIconBridge r0;
    public ClickableSpansTextMessagePreference s0;
    public boolean t0;

    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.settings_fledge_page_title);
        qj2.a(this, R$xml.fledge_preference_v4);
        this.l0 = (ChromeSwitchPreference) f("fledge_toggle");
        this.m0 = (PreferenceCategoryWithClickableSummary) f("fledge_heading");
        this.n0 = (PreferenceCategory) f("current_fledge_sites");
        this.o0 = (TextMessagePreference) f("fledge_empty");
        this.p0 = (TextMessagePreference) f("fledge_disabled");
        this.q0 = (ChromeBasePreference) f("fledge_all_sites");
        this.s0 = (ClickableSpansTextMessagePreference) f("fledge_page_footer");
        this.l0.Y(oj2.k(Profile.d()).a("privacy_sandbox.m1.fledge_enabled"));
        this.l0.setOnPreferenceChangeListener(this);
        this.l0.b0(new wd0(15));
        this.t0 = false;
        this.m0.R(iq2.a(N().getString(R$string.settings_fledge_page_current_sites_description), new iq2.a(new un1(K(), new lj1(16, this)), "<link>", "</link>")));
        this.s0.R(iq2.a(N().getString(R$string.settings_fledge_page_footer), new iq2.a(new un1(K(), new on0(0, this)), "<link1>", "</link1>"), new iq2.a(new un1(K(), new dm0(24, this)), "<link2>", "</link2>")));
    }

    public final void P0() {
        boolean zL = gq2.l("privacy_sandbox.m1.fledge_enabled");
        boolean z = this.n0.b0() == 0;
        this.p0.U(!zL);
        this.o0.U(zL && z);
        this.n0.U(zL && !z);
        this.q0.U(zL && this.t0);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void g0() {
        super.g0();
        LargeIconBridge largeIconBridge = this.r0;
        if (largeIconBridge != null) {
            largeIconBridge.a();
        }
        this.r0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        N.MfWQDaSM(new ad0(new td2(16, this), 3));
        P0();
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!preference.p.equals("fledge_toggle")) {
            return false;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        n3.X0(zBooleanValue ? "Settings.PrivacySandbox.Fledge.Enabled" : "Settings.PrivacySandbox.Fledge.Disabled");
        oj2.k(Profile.d()).e("privacy_sandbox.m1.fledge_enabled", zBooleanValue);
        P0();
        return true;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        super.q0(view, bundle);
        this.b0.setItemAnimator(null);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (!(preference instanceof FledgePreference)) {
            return false;
        }
        N.MK6T9EFy(((FledgePreference) preference).a0, false);
        this.n0.d0(preference);
        P0();
        O0(R$string.settings_fledge_page_block_site_snackbar, 55);
        n3.X0("Settings.PrivacySandbox.Fledge.SiteRemoved");
        return true;
    }
}

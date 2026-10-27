package org.chromium.chrome.browser.password_manager.settings;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.d;
import defpackage.c01;
import defpackage.ke0;
import defpackage.ku2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.ox1;
import defpackage.px1;
import defpackage.tx1;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_check.e;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;
import org.chromium.chrome.browser.password_manager.settings.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreferenceSecondary;
import org.chromium.components.browser_ui.settings.TextMessagePreference;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordSettings extends d implements ox1, Preference.d {
    public static final /* synthetic */ int n0 = 0;
    public boolean h0;
    public boolean i0;
    public String j0;
    public org.chromium.chrome.browser.password_check.a k0;
    public int l0;
    public final b m0 = new b();

    public class a implements b.c {
        public a() {
        }
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        a aVar = new a();
        b bVar = this.m0;
        bVar.g = aVar;
        if (bundle != null) {
            if (bundle.containsKey("saved-state-export-state")) {
                int i = bundle.getInt("saved-state-export-state");
                bVar.a = i;
                if (i == 2) {
                    bVar.b();
                }
            }
            if (bundle.containsKey("saved-state-export-file-uri")) {
                String string = bundle.getString("saved-state-export-file-uri");
                if (string.isEmpty()) {
                    bVar.b = Uri.EMPTY;
                } else {
                    bVar.b = Uri.parse(string);
                }
            }
            if (bundle.containsKey("saved-state-entries-count")) {
                bVar.c = Integer.valueOf(bundle.getInt("saved-state-entries-count"));
            }
        }
        D().setTitle(R$string.password_settings_title);
        PreferenceManager preferenceManager = this.a0;
        L0(preferenceManager.a(preferenceManager.a));
        px1 px1Var = px1.a.a;
        px1Var.getClass();
        Object obj = ThreadUtils.a;
        if (px1Var.e == null) {
            px1Var.e = new PasswordUIView(px1Var);
        }
        px1Var.f.a(this);
        D0();
        this.l0 = (bundle == null || !bundle.containsKey("manage-passwords-referrer")) ? this.j.getInt("manage-passwords-referrer") : bundle.getInt("manage-passwords-referrer");
        if (bundle != null && bundle.containsKey("saved-state-search-query")) {
            this.j0 = bundle.getString("saved-state-search-query");
        }
    }

    public final void M0() {
        TextMessagePreference textMessagePreference = new TextMessagePreference(this.a0.a, null);
        textMessagePreference.f17J = R$layout.preference_settings_no_padding;
        textMessagePreference.Q(R$string.saved_passwords_none_text);
        textMessagePreference.M("saved_passwords_no_text");
        textMessagePreference.N(8);
        textMessagePreference.V = Boolean.TRUE;
        textMessagePreference.Y(false);
        I0().Y(textMessagePreference);
    }

    public final PrefService N0() {
        return oj2.k(Profile.d());
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        this.k0 = n3.i0(new n3());
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        this.H = true;
        if (D().isFinishing()) {
            px1 px1Var = px1.a.a;
            px1Var.getClass();
            Object obj = ThreadUtils.a;
            org.chromium.base.b<ox1> bVar = px1Var.f;
            bVar.c(this);
            if (bVar.isEmpty()) {
                PasswordUIView passwordUIView = px1Var.e;
                long j = passwordUIView.a;
                if (j != 0) {
                    N.MFhG46xL(j, passwordUIView);
                    passwordUIView.a = 0L;
                }
                px1Var.e = null;
            }
            if (this.k0 == null || this.l0 == 0) {
                return;
            }
            n3.E();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void i0() {
        this.H = true;
        n3.B = null;
        n3.C = 0;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        b bVar = this.m0;
        if (bVar.a == 1) {
            if (!n3.o(1)) {
                ExportWarningDialogFragment exportWarningDialogFragment = bVar.f;
                if (exportWarningDialogFragment != null) {
                    exportWarningDialogFragment.I0(false, false);
                }
                bVar.a = 0;
            } else if (bVar.f == null) {
                ExportWarningDialogFragment exportWarningDialogFragment2 = new ExportWarningDialogFragment();
                bVar.f = exportWarningDialogFragment2;
                exportWarningDialogFragment2.p0 = new org.chromium.chrome.browser.password_manager.settings.a(bVar);
                exportWarningDialogFragment2.M0(PasswordSettings.this.v, null);
            }
        }
        this.h0 = false;
        this.i0 = false;
        I0().c0();
        if (this.j0 != null) {
            px1 px1Var = px1.a.a;
            px1Var.getClass();
            Object obj = ThreadUtils.a;
            PasswordUIView passwordUIView = px1Var.e;
            N.MG_PqeQw(passwordUIView.a, passwordUIView);
            return;
        }
        ChromeSwitchPreferenceSecondary chromeSwitchPreferenceSecondary = new ChromeSwitchPreferenceSecondary(this.a0.a, null);
        chromeSwitchPreferenceSecondary.e0 = 0;
        chromeSwitchPreferenceSecondary.f0 = 0;
        chromeSwitchPreferenceSecondary.M("save_passwords_switch");
        chromeSwitchPreferenceSecondary.S(R$string.password_settings_save_passwords);
        chromeSwitchPreferenceSecondary.N(0);
        int i = R$string.text_on;
        Context context = chromeSwitchPreferenceSecondary.e;
        chromeSwitchPreferenceSecondary.T = context.getString(i);
        if (chromeSwitchPreferenceSecondary.S) {
            chromeSwitchPreferenceSecondary.s();
        }
        chromeSwitchPreferenceSecondary.U = context.getString(R$string.text_off);
        if (!chromeSwitchPreferenceSecondary.S) {
            chromeSwitchPreferenceSecondary.s();
        }
        chromeSwitchPreferenceSecondary.setOnPreferenceChangeListener(new ke0(17, this));
        chromeSwitchPreferenceSecondary.b0(new tx1(this));
        ku2 ku2VarT = ku2.t();
        try {
            I0().Y(chromeSwitchPreferenceSecondary);
            ku2VarT.close();
            chromeSwitchPreferenceSecondary.Y(N0().a("credentials_enable_service"));
            ChromeSwitchPreferenceSecondary chromeSwitchPreferenceSecondary2 = new ChromeSwitchPreferenceSecondary(this.a0.a, null);
            chromeSwitchPreferenceSecondary2.e0 = 0;
            chromeSwitchPreferenceSecondary2.f0 = 0;
            chromeSwitchPreferenceSecondary2.M("autosignin_switch");
            chromeSwitchPreferenceSecondary2.S(R$string.passwords_auto_signin_title);
            chromeSwitchPreferenceSecondary2.N(1);
            chromeSwitchPreferenceSecondary2.Q(R$string.passwords_auto_signin_description);
            chromeSwitchPreferenceSecondary2.setOnPreferenceChangeListener(new tx1(this));
            chromeSwitchPreferenceSecondary2.b0(new c01(14, this));
            I0().Y(chromeSwitchPreferenceSecondary2);
            chromeSwitchPreferenceSecondary2.Y(N0().a("credentials_enable_autosignin"));
            if (this.k0 != null) {
                ChromeBasePreference chromeBasePreference = new ChromeBasePreference(this.a0.a);
                chromeBasePreference.f17J = R$layout.preference_settings_no_padding;
                chromeBasePreference.M("check_passwords");
                chromeBasePreference.S(R$string.passwords_check_title);
                chromeBasePreference.N(2);
                chromeBasePreference.Q(R$string.passwords_check_description);
                chromeBasePreference.setOnPreferenceClickListener(new Preference.d() { // from class: rx1
                    @Override // androidx.preference.Preference.d
                    public final boolean r(Preference preference) {
                        int i2 = PasswordSettings.n0;
                        PasswordSettings passwordSettings = this.e;
                        passwordSettings.getClass();
                        ((e) n3.i0(new n3())).b(passwordSettings.a0.a, 0);
                        return true;
                    }
                });
                I0().Y(chromeBasePreference);
            }
            px1 px1Var2 = px1.a.a;
            px1Var2.getClass();
            Object obj2 = ThreadUtils.a;
            PasswordUIView passwordUIView2 = px1Var2.e;
            N.MG_PqeQw(passwordUIView2.a, passwordUIView2);
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        b bVar = this.m0;
        bundle.putInt("saved-state-export-state", bVar.a);
        Integer num = bVar.c;
        if (num != null) {
            bundle.putInt("saved-state-entries-count", num.intValue());
        }
        Uri uri = bVar.b;
        if (uri != null) {
            bundle.putString("saved-state-export-file-uri", uri.toString());
        }
        String str = this.j0;
        if (str != null) {
            bundle.putString("saved-state-search-query", str);
        }
        bundle.putInt("manage-passwords-referrer", this.l0);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        super.q0(view, bundle);
        this.b0.setItemAnimator(null);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (preference == null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(N.MTEEGYRd()));
            intent.setPackage(D().getPackageName());
            D().startActivity(intent);
        } else {
            boolean z = !preference.m().containsKey("name");
            px1 px1Var = px1.a.a;
            px1Var.getClass();
            Object obj = ThreadUtils.a;
            PasswordUIView passwordUIView = px1Var.e;
            FragmentActivity fragmentActivityD = D();
            n3 n3Var = new n3();
            int i = preference.m().getInt("id");
            if (z) {
                N.M$XUdEaY(passwordUIView.a, fragmentActivityD, n3Var, i, passwordUIView);
            } else {
                N.MH0CF$4w(passwordUIView.a, fragmentActivityD, n3Var, i, passwordUIView);
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    @Override // defpackage.ox1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void x(int r11) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.password_manager.settings.PasswordSettings.x(int):void");
    }

    @Override // defpackage.ox1
    public final void z(int i) {
        if (this.j0 != null) {
            return;
        }
        PreferenceCategory preferenceCategory = (PreferenceCategory) I0().Z("exceptions");
        if (preferenceCategory != null) {
            preferenceCategory.c0();
            I0().d0(preferenceCategory);
        }
        Preference preferenceZ = I0().Z("saved_passwords_no_text");
        if (preferenceZ != null) {
            I0().d0(preferenceZ);
        }
        boolean z = i == 0;
        this.i0 = z;
        if (z) {
            if (this.h0) {
                M0();
                return;
            }
            return;
        }
        PreferenceCategory preferenceCategory2 = new PreferenceCategory(this.a0.a);
        preferenceCategory2.M("exceptions");
        preferenceCategory2.S(R$string.section_saved_passwords_exceptions);
        preferenceCategory2.N(7);
        I0().Y(preferenceCategory2);
        for (int i2 = 0; i2 < i; i2++) {
            px1 px1Var = px1.a.a;
            px1Var.getClass();
            Object obj = ThreadUtils.a;
            PasswordUIView passwordUIView = px1Var.e;
            String strMtl3_dvG = N.Mtl3_dvG(passwordUIView.a, passwordUIView, i2);
            Preference preference = new Preference(this.a0.a);
            preference.T(strMtl3_dvG);
            preference.setOnPreferenceClickListener(this);
            Bundle bundleM = preference.m();
            bundleM.putString("url", strMtl3_dvG);
            bundleM.putInt("id", i2);
            preferenceCategory2.Y(preference);
        }
    }
}

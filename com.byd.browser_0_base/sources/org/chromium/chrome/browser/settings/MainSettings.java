package org.chromium.chrome.browser.settings;

import J.N;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.bi1;
import defpackage.f52;
import defpackage.hb1;
import defpackage.i62;
import defpackage.iq2;
import defpackage.md2;
import defpackage.n3;
import defpackage.n83;
import defpackage.nb1;
import defpackage.oj2;
import defpackage.on0;
import defpackage.p3;
import defpackage.qj2;
import defpackage.qx1;
import defpackage.sx1;
import defpackage.z7;
import defpackage.zp1;
import java.util.HashMap;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_check.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes2.dex */
public class MainSettings extends d implements TemplateUrlService.b, SigninManager.b, i62 {
    public static final /* synthetic */ int m0 = 0;
    public final hb1 h0;
    public final HashMap i0 = new HashMap();
    public a j0;
    public Profile k0;
    public zp1<bi1> l0;

    public MainSettings() {
        D0();
        this.h0 = new hb1(this);
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        HashMap map;
        qj2.a(this, org.chromium.chrome.R$xml.main_preferences);
        int iB0 = I0().b0();
        int i = 0;
        while (true) {
            map = this.i0;
            if (i >= iB0) {
                break;
            }
            Preference preferenceA0 = I0().a0(i);
            map.put(preferenceA0.p, preferenceA0);
            i++;
        }
        M0();
        boolean zD = qx1.d();
        hb1 hb1Var = this.h0;
        if (zD) {
            ChromeBasePreference chromeBasePreference = (ChromeBasePreference) map.get("passwords");
            chromeBasePreference.T = hb1Var;
            nb1.c(hb1Var, chromeBasePreference, true, chromeBasePreference.U);
        }
        ChromeBasePreference chromeBasePreference2 = (ChromeBasePreference) map.get("search_engine");
        chromeBasePreference2.T = hb1Var;
        nb1.c(hb1Var, chromeBasePreference2, true, chromeBasePreference2.U);
        TemplateUrlService templateUrlServiceQ = oj2.q(this.k0);
        if (!templateUrlServiceQ.f()) {
            templateUrlServiceQ.h(this);
            Object obj = ThreadUtils.a;
            N.MVKcMDBb(templateUrlServiceQ.c, templateUrlServiceQ);
        }
        new p3(null).b(new on0(8, this));
        f("statement").setOnPreferenceClickListener(new sx1(2, this));
    }

    public final void M0() {
        Preference preferenceF = f("passwords");
        if (qx1.c()) {
            preferenceF.T(iq2.b(K().getString(R$string.password_settings_title_gpm), new iq2.a(new Object[0])).toString().trim());
        }
        preferenceF.setOnPreferenceClickListener(new md2(2, this));
    }

    public final void N0() {
        z7 z7VarM = z7.m();
        Profile profile = this.k0;
        z7VarM.getClass();
        z7.D(profile).o();
        Preference preferenceZ = I0().Z("sign_in");
        if (preferenceZ != null) {
            I0().d0(preferenceZ);
        }
        z7 z7VarM2 = z7.m();
        Profile profile2 = this.k0;
        z7VarM2.getClass();
        CoreAccountInfo.b(z7.v(profile2).a(0));
        O0();
        M0();
        Preference preferenceZ2 = I0().Z("ui_theme");
        if (preferenceZ2 != null) {
            I0().d0(preferenceZ2);
        }
    }

    public final void O0() {
        TemplateUrlService templateUrlServiceQ = oj2.q(this.k0);
        if (!templateUrlServiceQ.f()) {
            ((ChromeBasePreference) f("search_engine")).J(false);
            return;
        }
        TemplateUrl templateUrlC = templateUrlServiceQ.c();
        String strC = templateUrlC != null ? templateUrlC.c() : null;
        Preference preferenceF = f("search_engine");
        preferenceF.J(true);
        Locale locale = Locale.getDefault();
        int i = n83.a;
        if (!(TextUtils.getLayoutDirectionFromLocale(locale) == 1)) {
            preferenceF.R(strC);
            return;
        }
        preferenceF.R("\u2066" + strC + "\u2069");
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void c() {
        new Handler().post(new f52(3, this));
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        D().setTitle(R$string.settings);
        this.j0 = n3.i0(new n3());
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        this.H = true;
        if (!D().isFinishing() || this.j0 == null) {
            return;
        }
        n3.E();
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.b
    public final void g() {
        TemplateUrlService templateUrlServiceQ = oj2.q(this.k0);
        templateUrlServiceQ.getClass();
        Object obj = ThreadUtils.a;
        templateUrlServiceQ.a.c(this);
        O0();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void j() {
        N0();
    }

    @Override // defpackage.i62
    public final void m(Profile profile) {
        this.k0 = profile;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        N0();
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void o0() {
        super.o0();
        z7 z7VarM = z7.m();
        Profile profile = this.k0;
        z7VarM.getClass();
        z7.D(profile).o();
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void p0() {
        super.p0();
        z7 z7VarM = z7.m();
        Profile profile = this.k0;
        z7VarM.getClass();
        z7.D(profile).o();
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        super.q0(view, bundle);
        this.b0.setItemAnimator(null);
    }
}

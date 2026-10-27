package org.chromium.components.browser_ui.accessibility;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.InterfaceC0182e0;
import defpackage.hv1;
import defpackage.iv1;
import defpackage.j52;
import defpackage.oj2;
import defpackage.p20;
import defpackage.po;
import defpackage.qj2;
import defpackage.qo;
import defpackage.vw0;
import defpackage.xz;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.accessibility.FontSizePrefs;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes2.dex */
public class AccessibilitySettings extends d implements Preference.c, p20 {
    public static final /* synthetic */ int r0 = 0;
    public TextScalePreference h0;
    public PageZoomPreference i0;
    public ChromeSwitchPreference j0;
    public ChromeSwitchPreference k0;
    public boolean l0;
    public InterfaceC0182e0 m0;
    public po.b n0;
    public double o0;
    public FontSizePrefs p0;
    public final a q0 = new a();

    public class a implements FontSizePrefs.a {
        public a() {
        }

        @Override // org.chromium.components.browser_ui.accessibility.FontSizePrefs.a
        public final void a(boolean z) {
            AccessibilitySettings.this.k0.Y(z);
        }

        @Override // org.chromium.components.browser_ui.accessibility.FontSizePrefs.a
        public final void b(float f, float f2) {
            TextScalePreference textScalePreference = AccessibilitySettings.this.h0;
            textScalePreference.T = f;
            textScalePreference.S = f2;
            textScalePreference.Y();
        }
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.accessibility_preferences);
        this.h0 = (TextScalePreference) f("text_scale");
        this.i0 = (PageZoomPreference) f("page_zoom_default_zoom");
        this.j0 = (ChromeSwitchPreference) f("page_zoom_always_show");
        ((po) this.m0).getClass();
        int i = iv1.a;
        if (N.Mudil8Bg("AccessibilityPageZoom")) {
            this.h0.U(false);
            PageZoomPreference pageZoomPreference = this.i0;
            ((po) this.m0).getClass();
            pageZoomPreference.S = iv1.c(N.MNitnPWj(Profile.d()));
            this.i0.setOnPreferenceChangeListener(this);
            this.j0.Y(iv1.e());
            this.j0.setOnPreferenceChangeListener(this);
        } else {
            this.i0.U(false);
            this.j0.U(false);
            this.h0.setOnPreferenceChangeListener(this);
            TextScalePreference textScalePreference = this.h0;
            FontSizePrefs fontSizePrefs = this.p0;
            float fMaawwu0p = N.Maawwu0p(fontSizePrefs.a, fontSizePrefs);
            float fA = this.p0.a();
            textScalePreference.T = fMaawwu0p;
            textScalePreference.S = fA;
        }
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("force_enable_zoom");
        this.k0 = chromeSwitchPreference;
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        ChromeSwitchPreference chromeSwitchPreference2 = this.k0;
        FontSizePrefs fontSizePrefs2 = this.p0;
        chromeSwitchPreference2.Y(N.MYwzxNqz(fontSizePrefs2.a, fontSizePrefs2));
        ChromeSwitchPreference chromeSwitchPreference3 = (ChromeSwitchPreference) f("reader_for_accessibility");
        ((po) this.m0).getClass();
        po.b bVar = new po.b();
        this.n0 = bVar;
        chromeSwitchPreference3.Y(bVar.a());
        chromeSwitchPreference3.setOnPreferenceChangeListener(this);
        ChromeSwitchPreference chromeSwitchPreference4 = (ChromeSwitchPreference) f("accessibility_tab_switcher");
        ((po) this.m0).getClass();
        po.a aVar = !qo.f().c() ? null : new po.a();
        if (aVar != null) {
            chromeSwitchPreference4.Y(aVar.a());
        } else {
            I0().d0(chromeSwitchPreference4);
        }
        f("captions").setOnPreferenceClickListener(new j52(this, 2));
        ((po) this.m0).getClass();
        vw0.a().getClass();
        if (qo.f().d()) {
            H0(org.chromium.chrome.R$xml.image_descriptions_settings_preference);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        this.H = true;
        D().setTitle(xz.a.getString(R$string.prefs_accessibility));
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if ("text_scale".equals(preference.p)) {
            this.l0 = true;
            FontSizePrefs fontSizePrefs = this.p0;
            float fFloatValue = ((Float) obj).floatValue();
            fontSizePrefs.getClass();
            xz.a.a.edit().putFloat("user_font_scale_factor", fFloatValue).apply();
            fontSizePrefs.b(xz.a.getResources().getConfiguration().fontScale * fFloatValue);
        } else if ("force_enable_zoom".equals(preference.p)) {
            this.p0.c(((Boolean) obj).booleanValue(), true);
        } else if ("reader_for_accessibility".equals(preference.p)) {
            po.b bVar = this.n0;
            if (bVar != null) {
                bVar.b(((Boolean) obj).booleanValue());
            }
        } else if ("page_zoom_default_zoom".equals(preference.p)) {
            Integer num = (Integer) obj;
            this.o0 = iv1.b(num.intValue());
            ((po) this.m0).getClass();
            N.MNh9C30M(Profile.d(), iv1.a(num.intValue()));
        } else if ("page_zoom_always_show".equals(preference.p)) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            int i = iv1.a;
            xz.a.a.edit().putBoolean("Chrome.PageZoom.AlwaysShowMenuItem", zBooleanValue).apply();
        }
        return true;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void o0() {
        super.o0();
        FontSizePrefs fontSizePrefs = this.p0;
        fontSizePrefs.b.a(this.q0);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void p0() {
        FontSizePrefs fontSizePrefs = this.p0;
        fontSizePrefs.b.c(this.q0);
        if (this.l0) {
            oj2.a0((int) (this.p0.a() * 100.0f), "Accessibility.Android.UserFontSizePref.Change");
            this.l0 = false;
        }
        if (this.o0 != 0.0d) {
            int i = hv1.a;
            oj2.O("Accessibility.Android.PageZoom.SettingsDefaultZoomLevelChanged", true);
            oj2.W((int) Math.round(this.o0 * 100.0d), 50, 300, hv1.a, "Accessibility.Android.PageZoom.SettingsDefaultZoomLevelValue");
        }
        super.p0();
    }

    @Override // defpackage.p20
    public final void t() {
    }
}

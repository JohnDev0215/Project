package org.chromium.chrome.browser.image_descriptions;

import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.oj2;
import defpackage.p20;
import defpackage.qj2;
import defpackage.uw0;
import defpackage.ww0;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class ImageDescriptionsSettings extends d implements Preference.c, p20 {
    public ChromeSwitchPreference h0;
    public RadioButtonGroupAccessibilityPreference i0;
    public ww0 j0;
    public boolean k0;
    public boolean l0;
    public Profile m0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.image_descriptions_preference);
        this.m0 = Profile.d();
        Bundle bundle2 = this.j;
        if (bundle2 != null) {
            this.k0 = bundle2.getBoolean("image_descriptions_switch");
            this.l0 = bundle2.getBoolean("image_descriptions_data_policy");
        }
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("image_descriptions_switch");
        this.h0 = chromeSwitchPreference;
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        this.h0.Y(this.k0);
        RadioButtonGroupAccessibilityPreference radioButtonGroupAccessibilityPreference = (RadioButtonGroupAccessibilityPreference) f("image_descriptions_data_policy");
        this.i0 = radioButtonGroupAccessibilityPreference;
        radioButtonGroupAccessibilityPreference.setOnPreferenceChangeListener(this);
        this.i0.J(this.k0);
        this.i0.U = this.l0;
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        this.H = true;
        D().setTitle(R$string.image_descriptions_settings_title);
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (preference.p.equals("image_descriptions_switch")) {
            if (((Boolean) obj).booleanValue()) {
                ww0 ww0Var = this.j0;
                Profile profile = this.m0;
                ((uw0) ww0Var).a.getClass();
                oj2.k(profile).e("settings.a11y.enable_accessibility_image_labels_android", true);
                ww0 ww0Var2 = this.j0;
                boolean z = this.i0.U;
                Profile profile2 = this.m0;
                ((uw0) ww0Var2).a.getClass();
                oj2.k(profile2).e("settings.a11y.enable_accessibility_image_labels_only_on_wifi", z);
                this.i0.J(true);
            } else {
                ww0 ww0Var3 = this.j0;
                Profile profile3 = this.m0;
                ((uw0) ww0Var3).a.getClass();
                oj2.k(profile3).e("settings.a11y.enable_accessibility_image_labels_android", false);
                this.i0.J(false);
            }
        } else if (preference.p.equals("image_descriptions_data_policy")) {
            ww0 ww0Var4 = this.j0;
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            Profile profile4 = this.m0;
            ((uw0) ww0Var4).a.getClass();
            oj2.k(profile4).e("settings.a11y.enable_accessibility_image_labels_only_on_wifi", zBooleanValue);
        }
        return true;
    }

    @Override // defpackage.p20
    public final void t() {
    }
}

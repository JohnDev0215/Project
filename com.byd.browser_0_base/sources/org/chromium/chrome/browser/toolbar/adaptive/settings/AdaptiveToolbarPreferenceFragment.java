package org.chromium.chrome.browser.toolbar.adaptive.settings;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.d;
import defpackage.gx;
import defpackage.l2;
import defpackage.oj2;
import defpackage.on0;
import defpackage.p3;
import defpackage.qj2;
import defpackage.to3;
import defpackage.wd0;
import java.lang.ref.WeakReference;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes2.dex */
public class AdaptiveToolbarPreferenceFragment extends d {
    public static final /* synthetic */ int j0 = 0;
    public ChromeSwitchPreference h0;
    public RadioButtonGroupAdaptiveToolbarPreference i0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.toolbar_shortcut);
        qj2.a(this, R$xml.adaptive_toolbar_preference);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("toolbar_shortcut_switch");
        this.h0 = chromeSwitchPreference;
        chromeSwitchPreference.Y(SharedPreferencesManager.getInstance().readBoolean("Chrome.AdaptiveToolbarCustomization.Enabled", true));
        this.h0.setOnPreferenceChangeListener(new gx(25, this));
        RadioButtonGroupAdaptiveToolbarPreference radioButtonGroupAdaptiveToolbarPreference = (RadioButtonGroupAdaptiveToolbarPreference) f("adaptive_toolbar_radio_group");
        this.i0 = radioButtonGroupAdaptiveToolbarPreference;
        FragmentActivity fragmentActivityD = D();
        radioButtonGroupAdaptiveToolbarPreference.Y = fragmentActivityD == null ? false : to3.b(new l2(new WeakReference(fragmentActivityD)));
        radioButtonGroupAdaptiveToolbarPreference.Y();
        RadioButtonGroupAdaptiveToolbarPreference radioButtonGroupAdaptiveToolbarPreference2 = this.i0;
        p3 p3Var = new p3(new l2(new WeakReference(D())));
        radioButtonGroupAdaptiveToolbarPreference2.X = p3Var;
        if (radioButtonGroupAdaptiveToolbarPreference2.S != null) {
            p3Var.b(new on0(24, radioButtonGroupAdaptiveToolbarPreference2));
            radioButtonGroupAdaptiveToolbarPreference2.X.b(new on0(23, "Android.AdaptiveToolbarButton.Settings.Startup"));
        }
        this.i0.setOnPreferenceChangeListener(new wd0(21));
        this.i0.J(SharedPreferencesManager.getInstance().readBoolean("Chrome.AdaptiveToolbarCustomization.Enabled", true));
        oj2.O("Android.AdaptiveToolbarButton.SettingsToggle.Startup", SharedPreferencesManager.getInstance().readBoolean("Chrome.AdaptiveToolbarCustomization.Enabled", true));
    }
}

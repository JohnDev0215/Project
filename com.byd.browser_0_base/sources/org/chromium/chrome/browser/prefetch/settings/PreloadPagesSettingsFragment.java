package org.chromium.chrome.browser.prefetch.settings;

import J.N;
import androidx.preference.Preference;
import defpackage.i83;
import defpackage.nb1;
import defpackage.oj2;
import defpackage.pp0;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.prefetch.settings.RadioButtonGroupPreloadPagesSettings;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* JADX INFO: loaded from: classes2.dex */
public class PreloadPagesSettingsFragment extends PreloadPagesSettingsFragmentBase implements pp0, RadioButtonGroupPreloadPagesSettings.a, Preference.c {
    public static final /* synthetic */ int k0 = 0;
    public SettingsLauncher i0;
    public RadioButtonGroupPreloadPagesSettings j0;

    @Override // org.chromium.chrome.browser.prefetch.settings.PreloadPagesSettingsFragmentBase
    public final int M0() {
        return R$xml.preload_pages_preferences;
    }

    @Override // org.chromium.chrome.browser.prefetch.settings.PreloadPagesSettingsFragmentBase
    public final void N0() {
        i83 i83Var = new i83(9);
        RadioButtonGroupPreloadPagesSettings radioButtonGroupPreloadPagesSettings = (RadioButtonGroupPreloadPagesSettings) f("preload_pages_radio_button_group");
        this.j0 = radioButtonGroupPreloadPagesSettings;
        radioButtonGroupPreloadPagesSettings.V = N.MaV3tKHW();
        RadioButtonGroupPreloadPagesSettings radioButtonGroupPreloadPagesSettings2 = this.j0;
        radioButtonGroupPreloadPagesSettings2.W = this;
        radioButtonGroupPreloadPagesSettings2.X = i83Var;
        nb1.c(i83Var, radioButtonGroupPreloadPagesSettings2, true, true);
        this.j0.setOnPreferenceChangeListener(this);
        Preference preferenceF = f("managed_disclaimer_text");
        TextMessagePreference textMessagePreference = (TextMessagePreference) f("text_managed_legacy");
        boolean zG = i83Var.g(this.j0);
        if (oj2.y()) {
            textMessagePreference.U(false);
            preferenceF.U(zG);
        } else {
            textMessagePreference.T = i83Var;
            nb1.c(i83Var, textMessagePreference, true, textMessagePreference.U);
            textMessagePreference.U(zG);
            preferenceF.U(false);
        }
    }

    public final void O0(int i) {
        if (i == 2) {
            this.i0.g(D(), ExtendedPreloadingSettingsFragment.class);
        } else if (i == 1) {
            this.i0.g(D(), StandardPreloadingSettingsFragment.class);
        }
    }

    @Override // defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.i0 = settingsLauncher;
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        String str = preference.p;
        int iIntValue = ((Integer) obj).intValue();
        if (iIntValue == N.MaV3tKHW()) {
            return true;
        }
        N.MhGHBfeJ(iIntValue);
        return true;
    }
}

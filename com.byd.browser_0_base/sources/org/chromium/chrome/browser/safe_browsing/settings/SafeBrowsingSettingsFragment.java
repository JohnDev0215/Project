package org.chromium.chrome.browser.safe_browsing.settings;

import J.N;
import android.content.Context;
import androidx.preference.Preference;
import defpackage.i83;
import defpackage.n3;
import defpackage.nb1;
import defpackage.oj2;
import defpackage.on0;
import defpackage.pp0;
import defpackage.q11;
import defpackage.tn1;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.safe_browsing.settings.RadioButtonGroupSafeBrowsingPreference;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* JADX INFO: loaded from: classes2.dex */
public class SafeBrowsingSettingsFragment extends SafeBrowsingSettingsFragmentBase implements pp0, RadioButtonGroupSafeBrowsingPreference.a, Preference.c {
    public static final /* synthetic */ int l0 = 0;
    public SettingsLauncher i0;
    public RadioButtonGroupSafeBrowsingPreference j0;
    public int k0;

    public static String O0(Context context) {
        String string;
        int iMdyQjr8h = N.MdyQjr8h();
        if (iMdyQjr8h == 2) {
            string = context.getString(R$string.safe_browsing_enhanced_protection_title);
        } else if (iMdyQjr8h == 1) {
            string = context.getString(R$string.safe_browsing_standard_protection_title);
        } else {
            if (iMdyQjr8h == 0) {
                return context.getString(R$string.prefs_safe_browsing_no_protection_summary);
            }
            string = "";
        }
        return context.getString(R$string.prefs_safe_browsing_summary, string);
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public final int M0() {
        return R$xml.safe_browsing_preferences;
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public final void N0() {
        this.k0 = q11.k("SafeBrowsingSettingsFragment.AccessPoint", 0, this.j);
        i83 i83Var = new i83(13);
        RadioButtonGroupSafeBrowsingPreference radioButtonGroupSafeBrowsingPreference = (RadioButtonGroupSafeBrowsingPreference) f("safe_browsing_radio_button_group");
        this.j0 = radioButtonGroupSafeBrowsingPreference;
        int iMdyQjr8h = N.MdyQjr8h();
        int i = this.k0;
        radioButtonGroupSafeBrowsingPreference.V = iMdyQjr8h;
        radioButtonGroupSafeBrowsingPreference.W = i;
        RadioButtonGroupSafeBrowsingPreference radioButtonGroupSafeBrowsingPreference2 = this.j0;
        radioButtonGroupSafeBrowsingPreference2.X = this;
        radioButtonGroupSafeBrowsingPreference2.Y = i83Var;
        nb1.c(i83Var, radioButtonGroupSafeBrowsingPreference2, true, true);
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
        Q0(0);
    }

    public final void P0(int i) {
        if (i == 1) {
            Q0(5);
        } else if (i == 2) {
            Q0(4);
        }
        if (i == 2) {
            this.i0.g(D(), EnhancedProtectionSettingsFragment.class);
        } else if (i == 1) {
            this.i0.g(D(), StandardProtectionSettingsFragment.class);
        }
    }

    public final void Q0(int i) {
        int i2 = this.k0;
        String strConcat = "";
        String str = i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : "TailoredSecurity" : "SecurityInterstitial" : "SurfaceExplorerPromoSlinger" : "SafetyCheck" : "ParentSettings" : "Default";
        oj2.U(i, 8, "SafeBrowsing.Settings.UserAction.".concat(str));
        switch (i) {
            case 0:
                strConcat = "ShowedFrom".concat(str);
                break;
            case 1:
                strConcat = "EnhancedProtectionClicked";
                break;
            case 2:
                strConcat = "StandardProtectionClicked";
                break;
            case 3:
                strConcat = "DisableSafeBrowsingClicked";
                break;
            case 4:
                strConcat = "EnhancedProtectionExpandArrowClicked";
                break;
            case 5:
                strConcat = "StandardProtectionExpandArrowClicked";
                break;
            case 6:
                strConcat = "DisableSafeBrowsingDialogConfirmed";
                break;
            case 7:
                strConcat = "DisableSafeBrowsingDialogDenied";
                break;
        }
        n3.X0("SafeBrowsing.Settings." + strConcat);
    }

    @Override // defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.i0 = settingsLauncher;
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        String str = preference.p;
        int iIntValue = ((Integer) obj).intValue();
        int iMdyQjr8h = N.MdyQjr8h();
        if (iIntValue == iMdyQjr8h) {
            return true;
        }
        if (iIntValue == 0) {
            Q0(3);
        } else if (iIntValue == 1) {
            Q0(2);
        } else if (iIntValue == 2) {
            Q0(1);
        }
        if (iIntValue == 0) {
            this.j0.Y(iMdyQjr8h);
            new tn1(K(), new on0(5, this)).show();
        } else {
            N.MzV0f_Xz(iIntValue);
        }
        return true;
    }
}

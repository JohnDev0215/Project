package org.chromium.chrome.browser.tracing.settings;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.d;
import defpackage.te3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;

/* JADX INFO: loaded from: classes2.dex */
public class TracingCategoriesSettings extends d implements Preference.c {
    public int h0;
    public HashSet i0;
    public ArrayList j0;
    public ChromeBaseCheckBoxPreference k0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle("Select categories");
        PreferenceManager preferenceManager = this.a0;
        PreferenceScreen preferenceScreenA = preferenceManager.a(preferenceManager.a);
        preferenceScreenA.V = true;
        this.h0 = this.j.getInt("type");
        this.i0 = new HashSet(TracingSettings.M0(this.h0));
        this.j0 = new ArrayList();
        ArrayList<String> arrayList = new ArrayList(te3.a().d);
        Collections.sort(arrayList);
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference = new ChromeBaseCheckBoxPreference(this.a0.a, null);
        this.k0 = chromeBaseCheckBoxPreference;
        chromeBaseCheckBoxPreference.M("select-all");
        this.k0.T("Select all");
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference2 = this.k0;
        chromeBaseCheckBoxPreference2.w = false;
        chromeBaseCheckBoxPreference2.setOnPreferenceChangeListener(this);
        preferenceScreenA.Y(this.k0);
        for (String str2 : arrayList) {
            if (str2.startsWith("disabled-by-default-") == this.h0) {
                ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference3 = new ChromeBaseCheckBoxPreference(this.a0.a, null);
                chromeBaseCheckBoxPreference3.M(str2);
                chromeBaseCheckBoxPreference3.T(str2.startsWith("disabled-by-default-") ? str2.substring(20) : str2);
                chromeBaseCheckBoxPreference3.Y(this.i0.contains(str2));
                chromeBaseCheckBoxPreference3.w = false;
                chromeBaseCheckBoxPreference3.setOnPreferenceChangeListener(this);
                this.j0.add(chromeBaseCheckBoxPreference3);
                preferenceScreenA.Y(chromeBaseCheckBoxPreference3);
            }
        }
        this.k0.Y(this.i0.size() == this.j0.size());
        L0(preferenceScreenA);
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (TextUtils.equals("select-all", preference.p)) {
            for (CheckBoxPreference checkBoxPreference : this.j0) {
                checkBoxPreference.Y(zBooleanValue);
                checkBoxPreference.h(Boolean.valueOf(checkBoxPreference.S));
            }
            return true;
        }
        if (zBooleanValue) {
            this.i0.add(preference.p);
        } else {
            this.i0.remove(preference.p);
        }
        this.k0.Y(this.i0.size() == this.j0.size());
        int i = this.h0;
        HashSet hashSet = this.i0;
        LinkedHashMap linkedHashMap = TracingSettings.n0;
        HashSet hashSet2 = new HashSet(hashSet);
        for (String str : TracingSettings.N0()) {
            if (i != str.startsWith("disabled-by-default-")) {
                hashSet2.add(str);
            }
        }
        SharedPreferencesManager.getInstance().p("tracing_categories", hashSet2);
        return true;
    }
}

package com.android.inputmethod.latin.settings;

import android.os.Build;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.SwitchPreference;
import com.android.inputmethod.latin.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class TwoStatePreferenceHelper {
    private static final String EMPTY_TEXT = "";

    private TwoStatePreferenceHelper() {
    }

    public static void replaceCheckBoxPreferencesBySwitchPreferences(final PreferenceGroup group) {
        if (Build.VERSION.SDK_INT <= 19) {
            return;
        }
        replaceAllCheckBoxPreferencesBySwitchPreferences(group);
    }

    private static void replaceAllCheckBoxPreferencesBySwitchPreferences(final PreferenceGroup group) {
        ArrayList arrayList = new ArrayList();
        int preferenceCount = group.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            arrayList.add(group.getPreference(i));
        }
        group.removeAll();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = (Preference) arrayList.get(i2);
            if (preference instanceof CheckBoxPreference) {
                addSwitchPreferenceBasedOnCheckBoxPreference((CheckBoxPreference) preference, group);
            } else {
                group.addPreference(preference);
                if (preference instanceof PreferenceGroup) {
                    replaceAllCheckBoxPreferencesBySwitchPreferences((PreferenceGroup) preference);
                }
            }
        }
    }

    static void addSwitchPreferenceBasedOnCheckBoxPreference(final CheckBoxPreference checkBox, final PreferenceGroup group) {
        SwitchPreference switchPreference = new SwitchPreference(checkBox.getContext());
        switchPreference.setWidgetLayoutResource(R.layout.byd_preference_switch);
        switchPreference.setTitle(checkBox.getTitle());
        switchPreference.setKey(checkBox.getKey());
        switchPreference.setOrder(checkBox.getOrder());
        switchPreference.setPersistent(checkBox.isPersistent());
        switchPreference.setEnabled(checkBox.isEnabled());
        switchPreference.setChecked(checkBox.isChecked());
        switchPreference.setSummary(checkBox.getSummary());
        switchPreference.setSummaryOn(checkBox.getSummaryOn());
        switchPreference.setSummaryOff(checkBox.getSummaryOff());
        switchPreference.setSwitchTextOn("");
        switchPreference.setSwitchTextOff("");
        group.addPreference(switchPreference);
        switchPreference.setDependency(checkBox.getDependency());
    }
}

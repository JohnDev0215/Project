package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import com.android.inputmethod.keyboard.KeyboardTheme;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.settings.RadioButtonPreference;

/* JADX INFO: loaded from: classes2.dex */
public final class ThemeSettingsFragment extends SubScreenFragment implements RadioButtonPreference.OnRadioButtonClickedListener {
    private int mSelectedThemeId;

    static class KeyboardThemePreference extends RadioButtonPreference {
        final int mThemeId;

        KeyboardThemePreference(final Context context, final String name, final int id) {
            super(context);
            setTitle(name);
            this.mThemeId = id;
        }
    }

    static void updateKeyboardThemeSummary(final Preference pref) {
        Context context = pref.getContext();
        Resources resources = context.getResources();
        KeyboardTheme keyboardTheme = KeyboardTheme.getKeyboardTheme(context);
        String[] stringArray = resources.getStringArray(R.array.keyboard_theme_names);
        int[] intArray = resources.getIntArray(R.array.keyboard_theme_ids);
        for (int i = 0; i < stringArray.length; i++) {
            if (keyboardTheme.mThemeId == intArray[i]) {
                pref.setSummary(stringArray[i]);
                return;
            }
        }
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_theme);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Activity activity = getActivity();
        Resources resources = getResources();
        String[] stringArray = resources.getStringArray(R.array.keyboard_theme_names);
        int[] intArray = resources.getIntArray(R.array.keyboard_theme_ids);
        for (int i = 0; i < stringArray.length; i++) {
            KeyboardThemePreference keyboardThemePreference = new KeyboardThemePreference(activity, stringArray[i], intArray[i]);
            preferenceScreen.addPreference(keyboardThemePreference);
            keyboardThemePreference.setOnRadioButtonClickedListener(this);
        }
        this.mSelectedThemeId = KeyboardTheme.getKeyboardTheme(activity).mThemeId;
    }

    @Override // com.android.inputmethod.latin.settings.RadioButtonPreference.OnRadioButtonClickedListener
    public void onRadioButtonClicked(final RadioButtonPreference preference) {
        if (preference instanceof KeyboardThemePreference) {
            this.mSelectedThemeId = ((KeyboardThemePreference) preference).mThemeId;
            updateSelected();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        updateSelected();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        KeyboardTheme.saveKeyboardThemeId(this.mSelectedThemeId, getSharedPreferences());
    }

    private void updateSelected() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof KeyboardThemePreference) {
                KeyboardThemePreference keyboardThemePreference = (KeyboardThemePreference) preference;
                keyboardThemePreference.setSelected(this.mSelectedThemeId == keyboardThemePreference.mThemeId);
            }
        }
    }
}

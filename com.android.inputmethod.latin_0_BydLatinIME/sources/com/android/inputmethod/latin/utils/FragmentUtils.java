package com.android.inputmethod.latin.utils;

import com.android.inputmethod.dictionarypack.DictionarySettingsFragment;
import com.android.inputmethod.latin.about.AboutPreferences;
import com.android.inputmethod.latin.settings.AccountsSettingsFragment;
import com.android.inputmethod.latin.settings.AdvancedSettingsFragment;
import com.android.inputmethod.latin.settings.AppearanceSettingsFragment;
import com.android.inputmethod.latin.settings.CorrectionSettingsFragment;
import com.android.inputmethod.latin.settings.CustomInputStyleSettingsFragment;
import com.android.inputmethod.latin.settings.DebugSettingsFragment;
import com.android.inputmethod.latin.settings.GestureSettingsFragment;
import com.android.inputmethod.latin.settings.PreferencesSettingsFragment;
import com.android.inputmethod.latin.settings.SettingsFragment;
import com.android.inputmethod.latin.settings.ThemeSettingsFragment;
import com.android.inputmethod.latin.spellcheck.SpellCheckerSettingsFragment;
import com.android.inputmethod.latin.userdictionary.UserDictionaryAddWordFragment;
import com.android.inputmethod.latin.userdictionary.UserDictionaryList;
import com.android.inputmethod.latin.userdictionary.UserDictionaryLocalePicker;
import com.android.inputmethod.latin.userdictionary.UserDictionarySettings;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentUtils {
    private static final HashSet<String> sLatinImeFragments;

    static {
        HashSet<String> hashSet = new HashSet<>();
        sLatinImeFragments = hashSet;
        hashSet.add(DictionarySettingsFragment.class.getName());
        hashSet.add(AboutPreferences.class.getName());
        hashSet.add(PreferencesSettingsFragment.class.getName());
        hashSet.add(AccountsSettingsFragment.class.getName());
        hashSet.add(AppearanceSettingsFragment.class.getName());
        hashSet.add(ThemeSettingsFragment.class.getName());
        hashSet.add(CustomInputStyleSettingsFragment.class.getName());
        hashSet.add(GestureSettingsFragment.class.getName());
        hashSet.add(CorrectionSettingsFragment.class.getName());
        hashSet.add(AdvancedSettingsFragment.class.getName());
        hashSet.add(DebugSettingsFragment.class.getName());
        hashSet.add(SettingsFragment.class.getName());
        hashSet.add(SpellCheckerSettingsFragment.class.getName());
        hashSet.add(UserDictionaryAddWordFragment.class.getName());
        hashSet.add(UserDictionaryList.class.getName());
        hashSet.add(UserDictionaryLocalePicker.class.getName());
        hashSet.add(UserDictionarySettings.class.getName());
    }

    public static boolean isValidFragment(String fragmentName) {
        return sLatinImeFragments.contains(fragmentName);
    }
}

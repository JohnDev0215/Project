package com.android.settingslib.inputmethod;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class InputMethodAndSubtypeUtilCompat {
    private static final TextUtils.SimpleStringSplitter sStringInputMethodSplitter = new TextUtils.SimpleStringSplitter(':');
    private static final TextUtils.SimpleStringSplitter sStringInputMethodSubtypeSplitter = new TextUtils.SimpleStringSplitter(';');

    public static String buildInputMethodsAndSubtypesString(HashMap<String, HashSet<String>> map) {
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            HashSet<String> hashSet = map.get(str);
            sb.append(str);
            for (String str2 : hashSet) {
                sb.append(';');
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    private static String buildInputMethodsString(HashSet<String> hashSet) {
        StringBuilder sb = new StringBuilder();
        for (String str : hashSet) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private static int getInputMethodSubtypeSelected(ContentResolver contentResolver) {
        try {
            return Settings.Secure.getInt(contentResolver, "selected_input_method_subtype");
        } catch (Settings.SettingNotFoundException unused) {
            return -1;
        }
    }

    private static boolean isInputMethodSubtypeSelected(ContentResolver contentResolver) {
        return getInputMethodSubtypeSelected(contentResolver) != -1;
    }

    private static void putSelectedInputMethodSubtype(ContentResolver contentResolver, int i) {
        Settings.Secure.putInt(contentResolver, "selected_input_method_subtype", i);
    }

    static HashMap<String, HashSet<String>> getEnabledInputMethodsAndSubtypeList(ContentResolver contentResolver) {
        return parseInputMethodsAndSubtypesString(Settings.Secure.getString(contentResolver, "enabled_input_methods"));
    }

    public static HashMap<String, HashSet<String>> parseInputMethodsAndSubtypesString(String str) {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        if (TextUtils.isEmpty(str)) {
            return map;
        }
        sStringInputMethodSplitter.setString(str);
        while (sStringInputMethodSplitter.hasNext()) {
            sStringInputMethodSubtypeSplitter.setString(sStringInputMethodSplitter.next());
            if (sStringInputMethodSubtypeSplitter.hasNext()) {
                HashSet<String> hashSet = new HashSet<>();
                String next = sStringInputMethodSubtypeSplitter.next();
                while (sStringInputMethodSubtypeSplitter.hasNext()) {
                    hashSet.add(sStringInputMethodSubtypeSplitter.next());
                }
                map.put(next, hashSet);
            }
        }
        return map;
    }

    private static HashSet<String> getDisabledSystemIMEs(ContentResolver contentResolver) {
        HashSet<String> hashSet = new HashSet<>();
        String string = Settings.Secure.getString(contentResolver, "disabled_system_input_methods");
        if (TextUtils.isEmpty(string)) {
            return hashSet;
        }
        sStringInputMethodSplitter.setString(string);
        while (sStringInputMethodSplitter.hasNext()) {
            hashSet.add(sStringInputMethodSplitter.next());
        }
        return hashSet;
    }

    public static void saveInputMethodSubtypeList(PreferenceFragmentCompat preferenceFragmentCompat, ContentResolver contentResolver, List<InputMethodInfo> list, boolean z) {
        boolean zContainsKey;
        Iterator<InputMethodInfo> it;
        String string = Settings.Secure.getString(contentResolver, "default_input_method");
        int inputMethodSubtypeSelected = getInputMethodSubtypeSelected(contentResolver);
        HashMap<String, HashSet<String>> enabledInputMethodsAndSubtypeList = getEnabledInputMethodsAndSubtypeList(contentResolver);
        HashSet<String> disabledSystemIMEs = getDisabledSystemIMEs(contentResolver);
        Iterator<InputMethodInfo> it2 = list.iterator();
        boolean z2 = false;
        while (it2.hasNext()) {
            InputMethodInfo next = it2.next();
            String id = next.getId();
            Preference preferenceFindPreference = preferenceFragmentCompat.findPreference(id);
            if (preferenceFindPreference != null) {
                if (preferenceFindPreference instanceof TwoStatePreference) {
                    zContainsKey = ((TwoStatePreference) preferenceFindPreference).isChecked();
                } else {
                    zContainsKey = enabledInputMethodsAndSubtypeList.containsKey(id);
                }
                boolean zEquals = id.equals(string);
                boolean zIsSystem = next.isSystem();
                if ((!z && InputMethodSettingValuesWrapper.getInstance(preferenceFragmentCompat.getActivity()).isAlwaysCheckedIme(next)) || zContainsKey) {
                    if (!enabledInputMethodsAndSubtypeList.containsKey(id)) {
                        enabledInputMethodsAndSubtypeList.put(id, new HashSet<>());
                    }
                    HashSet<String> hashSet = enabledInputMethodsAndSubtypeList.get(id);
                    int subtypeCount = next.getSubtypeCount();
                    it = it2;
                    int i = 0;
                    boolean z3 = false;
                    while (i < subtypeCount) {
                        InputMethodSubtype subtypeAt = next.getSubtypeAt(i);
                        int i2 = subtypeCount;
                        String strValueOf = String.valueOf(subtypeAt.hashCode());
                        boolean z4 = z2;
                        TwoStatePreference twoStatePreference = (TwoStatePreference) preferenceFragmentCompat.findPreference(id + strValueOf);
                        if (twoStatePreference != null) {
                            if (!z3) {
                                hashSet.clear();
                                z3 = true;
                                z4 = true;
                            }
                            if (twoStatePreference.isEnabled() && twoStatePreference.isChecked()) {
                                hashSet.add(strValueOf);
                                if (zEquals && inputMethodSubtypeSelected == subtypeAt.hashCode()) {
                                    z2 = false;
                                }
                            } else {
                                hashSet.remove(strValueOf);
                            }
                            z2 = z4;
                        } else {
                            z2 = z4;
                        }
                        i++;
                        subtypeCount = i2;
                    }
                } else {
                    it = it2;
                    enabledInputMethodsAndSubtypeList.remove(id);
                    if (zEquals) {
                        string = null;
                    }
                }
                if (zIsSystem && z) {
                    if (disabledSystemIMEs.contains(id)) {
                        if (zContainsKey) {
                            disabledSystemIMEs.remove(id);
                        }
                    } else if (!zContainsKey) {
                        disabledSystemIMEs.add(id);
                    }
                }
                it2 = it;
            }
        }
        String strBuildInputMethodsAndSubtypesString = buildInputMethodsAndSubtypesString(enabledInputMethodsAndSubtypeList);
        String strBuildInputMethodsString = buildInputMethodsString(disabledSystemIMEs);
        if (z2 || !isInputMethodSubtypeSelected(contentResolver)) {
            putSelectedInputMethodSubtype(contentResolver, -1);
        }
        Settings.Secure.putString(contentResolver, "enabled_input_methods", strBuildInputMethodsAndSubtypesString);
        if (strBuildInputMethodsString.length() > 0) {
            Settings.Secure.putString(contentResolver, "disabled_system_input_methods", strBuildInputMethodsString);
        }
        if (string == null) {
            string = "";
        }
        Settings.Secure.putString(contentResolver, "default_input_method", string);
    }

    public static void loadInputMethodSubtypeList(PreferenceFragmentCompat preferenceFragmentCompat, ContentResolver contentResolver, List<InputMethodInfo> list, Map<String, List<Preference>> map) {
        HashMap<String, HashSet<String>> enabledInputMethodsAndSubtypeList = getEnabledInputMethodsAndSubtypeList(contentResolver);
        Iterator<InputMethodInfo> it = list.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            Preference preferenceFindPreference = preferenceFragmentCompat.findPreference(id);
            if (preferenceFindPreference instanceof TwoStatePreference) {
                boolean zContainsKey = enabledInputMethodsAndSubtypeList.containsKey(id);
                ((TwoStatePreference) preferenceFindPreference).setChecked(zContainsKey);
                if (map != null) {
                    Iterator<Preference> it2 = map.get(id).iterator();
                    while (it2.hasNext()) {
                        it2.next().setEnabled(zContainsKey);
                    }
                }
                setSubtypesPreferenceEnabled(preferenceFragmentCompat, list, id, zContainsKey);
            }
        }
        updateSubtypesPreferenceChecked(preferenceFragmentCompat, list, enabledInputMethodsAndSubtypeList);
    }

    private static void setSubtypesPreferenceEnabled(PreferenceFragmentCompat preferenceFragmentCompat, List<InputMethodInfo> list, String str, boolean z) {
        PreferenceScreen preferenceScreen = preferenceFragmentCompat.getPreferenceScreen();
        for (InputMethodInfo inputMethodInfo : list) {
            if (str.equals(inputMethodInfo.getId())) {
                int subtypeCount = inputMethodInfo.getSubtypeCount();
                for (int i = 0; i < subtypeCount; i++) {
                    TwoStatePreference twoStatePreference = (TwoStatePreference) preferenceScreen.findPreference(str + inputMethodInfo.getSubtypeAt(i).hashCode());
                    if (twoStatePreference != null) {
                        twoStatePreference.setEnabled(z);
                    }
                }
            }
        }
    }

    private static void updateSubtypesPreferenceChecked(PreferenceFragmentCompat preferenceFragmentCompat, List<InputMethodInfo> list, HashMap<String, HashSet<String>> map) {
        PreferenceScreen preferenceScreen = preferenceFragmentCompat.getPreferenceScreen();
        for (InputMethodInfo inputMethodInfo : list) {
            String id = inputMethodInfo.getId();
            if (map.containsKey(id)) {
                HashSet<String> hashSet = map.get(id);
                int subtypeCount = inputMethodInfo.getSubtypeCount();
                for (int i = 0; i < subtypeCount; i++) {
                    String strValueOf = String.valueOf(inputMethodInfo.getSubtypeAt(i).hashCode());
                    TwoStatePreference twoStatePreference = (TwoStatePreference) preferenceScreen.findPreference(id + strValueOf);
                    if (twoStatePreference != null) {
                        twoStatePreference.setChecked(hashSet.contains(strValueOf));
                    }
                }
            }
        }
    }

    public static void removeUnnecessaryNonPersistentPreference(Preference preference) {
        SharedPreferences sharedPreferences;
        String key = preference.getKey();
        if (preference.isPersistent() || key == null || (sharedPreferences = preference.getSharedPreferences()) == null || !sharedPreferences.contains(key)) {
            return;
        }
        sharedPreferences.edit().remove(key).apply();
    }
}

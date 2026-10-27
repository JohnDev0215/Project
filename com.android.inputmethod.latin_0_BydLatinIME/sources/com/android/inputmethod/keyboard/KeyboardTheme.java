package com.android.inputmethod.keyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.inputmethod.compat.BuildCompatUtils;
import com.android.inputmethod.latin.R;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardTheme implements Comparable<KeyboardTheme> {
    private static KeyboardTheme[] AVAILABLE_KEYBOARD_THEMES = null;
    public static final int DEFAULT_THEME_ID = 2;
    static final KeyboardTheme[] KEYBOARD_THEMES;
    static final String KLP_KEYBOARD_THEME_KEY = "pref_keyboard_layout_20110916";
    public static final String LXX_KEYBOARD_THEME_KEY = "pref_keyboard_theme_20140509";
    private static final String TAG = "KeyboardTheme";
    public static final int THEME_ID_ICS = 0;
    public static final int THEME_ID_KLP = 2;
    public static final int THEME_ID_LXX_DARK = 4;
    public static final int THEME_ID_LXX_LIGHT = 3;
    public final int mMinApiVersion;
    public final int mStyleId;
    public final int mThemeId;
    public final String mThemeName;

    static String getPreferenceKey(final int sdkVersion) {
        return sdkVersion <= 19 ? KLP_KEYBOARD_THEME_KEY : LXX_KEYBOARD_THEME_KEY;
    }

    static {
        KeyboardTheme[] keyboardThemeArr = {new KeyboardTheme(0, "ICS", R.style.KeyboardTheme_ICS, 1), new KeyboardTheme(2, "KLP", R.style.KeyboardTheme_KLP, 14), new KeyboardTheme(3, "LXXLight", R.style.KeyboardTheme_LXX_Light, 21), new KeyboardTheme(4, "LXXDark", R.style.KeyboardTheme_LXX_Dark, 1)};
        KEYBOARD_THEMES = keyboardThemeArr;
        Arrays.sort(keyboardThemeArr);
    }

    private KeyboardTheme(final int themeId, final String themeName, final int styleId, final int minApiVersion) {
        this.mThemeId = themeId;
        this.mThemeName = themeName;
        this.mStyleId = styleId;
        this.mMinApiVersion = minApiVersion;
    }

    @Override // java.lang.Comparable
    public int compareTo(final KeyboardTheme rhs) {
        int i = this.mMinApiVersion;
        int i2 = rhs.mMinApiVersion;
        if (i > i2) {
            return -1;
        }
        return i < i2 ? 1 : 0;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof KeyboardTheme) && ((KeyboardTheme) o).mThemeId == this.mThemeId;
    }

    public int hashCode() {
        return this.mThemeId;
    }

    static KeyboardTheme searchKeyboardThemeById(final int themeId, final KeyboardTheme[] availableThemeIds) {
        for (KeyboardTheme keyboardTheme : availableThemeIds) {
            if (keyboardTheme.mThemeId == themeId) {
                return keyboardTheme;
            }
        }
        return null;
    }

    static KeyboardTheme getDefaultKeyboardTheme(final SharedPreferences prefs, final int sdkVersion, final KeyboardTheme[] availableThemeArray) {
        String string = prefs.getString(KLP_KEYBOARD_THEME_KEY, null);
        if (string != null) {
            if (sdkVersion <= 19) {
                try {
                    KeyboardTheme keyboardThemeSearchKeyboardThemeById = searchKeyboardThemeById(Integer.parseInt(string), availableThemeArray);
                    if (keyboardThemeSearchKeyboardThemeById != null) {
                        return keyboardThemeSearchKeyboardThemeById;
                    }
                    Log.w(TAG, "Unknown keyboard theme in KLP preference: " + string);
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Illegal keyboard theme in KLP preference: " + string, e);
                }
            }
            Log.i(TAG, "Remove KLP keyboard theme preference: " + string);
            prefs.edit().remove(KLP_KEYBOARD_THEME_KEY).apply();
        }
        for (KeyboardTheme keyboardTheme : availableThemeArray) {
            if (sdkVersion >= keyboardTheme.mMinApiVersion) {
                return keyboardTheme;
            }
        }
        return searchKeyboardThemeById(2, availableThemeArray);
    }

    public static String getKeyboardThemeName(final int themeId) {
        return searchKeyboardThemeById(themeId, KEYBOARD_THEMES).mThemeName;
    }

    public static void saveKeyboardThemeId(final int themeId, final SharedPreferences prefs) {
        saveKeyboardThemeId(themeId, prefs, BuildCompatUtils.EFFECTIVE_SDK_INT);
    }

    static void saveKeyboardThemeId(final int themeId, final SharedPreferences prefs, final int sdkVersion) {
        prefs.edit().putString(getPreferenceKey(sdkVersion), Integer.toString(themeId)).apply();
    }

    public static KeyboardTheme getKeyboardTheme(final Context context) {
        return getKeyboardTheme(PreferenceManager.getDefaultSharedPreferences(context), BuildCompatUtils.EFFECTIVE_SDK_INT, getAvailableThemeArray(context));
    }

    static KeyboardTheme[] getAvailableThemeArray(final Context context) {
        if (AVAILABLE_KEYBOARD_THEMES == null) {
            int[] intArray = context.getResources().getIntArray(R.array.keyboard_theme_ids);
            ArrayList arrayList = new ArrayList();
            for (int i : intArray) {
                KeyboardTheme keyboardThemeSearchKeyboardThemeById = searchKeyboardThemeById(i, KEYBOARD_THEMES);
                if (keyboardThemeSearchKeyboardThemeById != null) {
                    arrayList.add(keyboardThemeSearchKeyboardThemeById);
                }
            }
            KeyboardTheme[] keyboardThemeArr = (KeyboardTheme[]) arrayList.toArray(new KeyboardTheme[arrayList.size()]);
            AVAILABLE_KEYBOARD_THEMES = keyboardThemeArr;
            Arrays.sort(keyboardThemeArr);
        }
        return AVAILABLE_KEYBOARD_THEMES;
    }

    static KeyboardTheme getKeyboardTheme(final SharedPreferences prefs, final int sdkVersion, final KeyboardTheme[] availableThemeArray) {
        String string = prefs.getString(LXX_KEYBOARD_THEME_KEY, null);
        if (string == null) {
            return getDefaultKeyboardTheme(prefs, sdkVersion, availableThemeArray);
        }
        try {
            KeyboardTheme keyboardThemeSearchKeyboardThemeById = searchKeyboardThemeById(Integer.parseInt(string), availableThemeArray);
            if (keyboardThemeSearchKeyboardThemeById != null) {
                return keyboardThemeSearchKeyboardThemeById;
            }
            Log.w(TAG, "Unknown keyboard theme in LXX preference: " + string);
        } catch (NumberFormatException e) {
            Log.w(TAG, "Illegal keyboard theme in LXX preference: " + string, e);
        }
        prefs.edit().remove(LXX_KEYBOARD_THEME_KEY).apply();
        return getDefaultKeyboardTheme(prefs, sdkVersion, availableThemeArray);
    }
}

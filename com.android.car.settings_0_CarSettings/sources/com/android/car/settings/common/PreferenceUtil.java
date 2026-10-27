package com.android.car.settings.common;

import androidx.preference.Preference;

/* JADX INFO: loaded from: classes5.dex */
public class PreferenceUtil {
    public static boolean checkPreferenceType(Preference preference, Class expectedType) {
        return expectedType.isInstance(preference);
    }

    public static void requirePreferenceType(Preference preference, Class expectedType) {
        if (!checkPreferenceType(preference, expectedType)) {
            throw new IllegalArgumentException("Preference should be of type " + expectedType.getName());
        }
    }
}

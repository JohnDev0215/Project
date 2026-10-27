package com.android.inputmethod.latin.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdditionalFeaturesSettingUtils {
    public static final int ADDITIONAL_FEATURES_SETTINGS_SIZE = 0;

    public static void addAdditionalFeaturesPreferences(final Context context, final PreferenceFragment settingsFragment) {
    }

    public static void readAdditionalFeaturesPreferencesIntoArray(final Context context, final SharedPreferences prefs, final int[] additionalFeaturesPreferences) {
    }

    private AdditionalFeaturesSettingUtils() {
    }

    @Nonnull
    public static RichInputMethodSubtype createRichInputMethodSubtype(@Nonnull final RichInputMethodManager imm, @Nonnull final InputMethodSubtype subtype, final Context context) {
        return new RichInputMethodSubtype(subtype);
    }
}

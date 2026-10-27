package com.android.inputmethod.latin.utils;

import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class LanguageOnSpacebarUtils {
    public static final int FORMAT_TYPE_FULL_LOCALE = 2;
    public static final int FORMAT_TYPE_LANGUAGE_ONLY = 1;
    public static final int FORMAT_TYPE_NONE = 0;
    private static List<InputMethodSubtype> sEnabledSubtypes = Collections.emptyList();
    private static boolean sIsSystemLanguageSameAsInputLanguage;

    private LanguageOnSpacebarUtils() {
    }

    public static int getLanguageOnSpacebarFormatType(@Nonnull final RichInputMethodSubtype subtype) {
        Locale locale;
        if (subtype.isNoLanguage()) {
            return 2;
        }
        int i = 0;
        if ((sEnabledSubtypes.size() < 2 && sIsSystemLanguageSameAsInputLanguage) || (locale = subtype.getLocale()) == null) {
            return 0;
        }
        String language = locale.getLanguage();
        String keyboardLayoutSetName = subtype.getKeyboardLayoutSetName();
        for (InputMethodSubtype inputMethodSubtype : sEnabledSubtypes) {
            if (language.equals(SubtypeLocaleUtils.getSubtypeLocale(inputMethodSubtype).getLanguage()) && keyboardLayoutSetName.equals(SubtypeLocaleUtils.getKeyboardLayoutSetName(inputMethodSubtype))) {
                i++;
            }
        }
        return i > 1 ? 2 : 1;
    }

    public static void setEnabledSubtypes(@Nonnull final List<InputMethodSubtype> enabledSubtypes) {
        sEnabledSubtypes = enabledSubtypes;
    }

    public static void onSubtypeChanged(@Nonnull final RichInputMethodSubtype subtype, final boolean implicitlyEnabledSubtype, @Nonnull final Locale systemLocale) {
        Locale locale = subtype.getLocale();
        if (systemLocale.equals(locale)) {
            sIsSystemLanguageSameAsInputLanguage = true;
        } else if (!systemLocale.getLanguage().equals(locale.getLanguage())) {
            sIsSystemLanguageSameAsInputLanguage = false;
        } else {
            sIsSystemLanguageSameAsInputLanguage = implicitlyEnabledSubtype;
        }
    }
}

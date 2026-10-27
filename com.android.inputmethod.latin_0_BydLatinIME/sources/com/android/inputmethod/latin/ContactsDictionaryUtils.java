package com.android.inputmethod.latin;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsDictionaryUtils {
    public static int getWordEndPosition(final String string, final int len, final int startIndex) {
        int iCharCount = startIndex + 1;
        while (iCharCount < len) {
            int iCodePointAt = string.codePointAt(iCharCount);
            if (iCodePointAt != 45 && iCodePointAt != 39 && !Character.isLetter(iCodePointAt)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return iCharCount;
    }

    public static boolean useFirstLastBigramsForLocale(final Locale locale) {
        return locale != null && locale.getLanguage().equals(Locale.ENGLISH.getLanguage());
    }
}

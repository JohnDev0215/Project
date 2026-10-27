package com.android.inputmethod.compat;

import android.content.Context;
import android.provider.UserDictionary;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class UserDictionaryCompatUtils {
    public static void addWord(Context context, String str, int i, String str2, Locale locale) {
        if (BuildCompatUtils.EFFECTIVE_SDK_INT >= 16) {
            addWordWithShortcut(context, str, i, str2, locale);
        } else {
            UserDictionary.Words.addWord(context, str, i, context.getResources().getConfiguration().locale.equals(locale) ? 1 : 0);
        }
    }

    private static void addWordWithShortcut(final Context context, final String word, final int freq, final String shortcut, final Locale locale) {
        UserDictionary.Words.addWord(context, word, freq, shortcut, locale);
    }
}

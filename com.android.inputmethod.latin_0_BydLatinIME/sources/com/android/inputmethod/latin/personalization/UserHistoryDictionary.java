package com.android.inputmethod.latin.personalization;

import android.content.Context;
import com.android.inputmethod.latin.Dictionary;
import com.android.inputmethod.latin.ExpandableBinaryDictionary;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.makedict.DictionaryHeader;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UserHistoryDictionary extends ExpandableBinaryDictionary {
    static final String NAME = "UserHistoryDictionary";

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean isValidWord(final String word) {
        return false;
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary
    protected void loadInitialContentsLocked() {
    }

    UserHistoryDictionary(final Context context, final Locale locale, @Nullable final String account) {
        super(context, getUserHistoryDictName(NAME, locale, null, account), locale, Dictionary.TYPE_USER_HISTORY, null);
        if (this.mLocale == null || this.mLocale.toString().length() <= 1) {
            return;
        }
        reloadDictionaryIfRequired();
    }

    static String getUserHistoryDictName(final String name, final Locale locale, @Nullable final File dictFile, @Nullable final String account) {
        return getDictName(name, locale, dictFile);
    }

    private static String getUserHistoryDictNamePerAccount(final String name, final Locale locale, @Nullable final File dictFile, @Nullable final String account) {
        if (dictFile != null) {
            return dictFile.getName();
        }
        String str = name + "." + locale.toString();
        return account != null ? str + "." + account : str;
    }

    public static UserHistoryDictionary getDictionary(final Context context, final Locale locale, final File dictFile, final String dictNamePrefix, @Nullable final String account) {
        return PersonalizationHelper.getUserHistoryDictionary(context, locale, account);
    }

    public static void addToDictionary(final ExpandableBinaryDictionary userHistoryDictionary, @Nonnull final NgramContext ngramContext, final String word, final boolean isValid, final int timestamp) {
        if (word.length() > 48) {
            return;
        }
        userHistoryDictionary.updateEntriesForWord(ngramContext, word, isValid, 1, timestamp);
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary, com.android.inputmethod.latin.Dictionary
    public void close() {
        asyncFlushBinaryDictionary();
        super.close();
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary
    protected Map<String, String> getHeaderAttributeMap() {
        Map<String, String> headerAttributeMap = super.getHeaderAttributeMap();
        headerAttributeMap.put(DictionaryHeader.USES_FORGETTING_CURVE_KEY, DictionaryHeader.ATTRIBUTE_VALUE_TRUE);
        headerAttributeMap.put(DictionaryHeader.HAS_HISTORICAL_INFO_KEY, DictionaryHeader.ATTRIBUTE_VALUE_TRUE);
        return headerAttributeMap;
    }
}

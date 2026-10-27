package com.android.inputmethod.latin;

import android.content.Context;
import android.util.LruCache;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import com.android.inputmethod.latin.utils.SuggestionResults;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DictionaryFacilitator {
    public static final String[] ALL_DICTIONARY_TYPES = {"main", Dictionary.TYPE_CONTACTS, Dictionary.TYPE_USER_HISTORY, Dictionary.TYPE_USER};
    public static final String[] DYNAMIC_DICTIONARY_TYPES = {Dictionary.TYPE_CONTACTS, Dictionary.TYPE_USER_HISTORY, Dictionary.TYPE_USER};

    public interface DictionaryInitializationListener {
        void onUpdateMainDictionaryAvailability(boolean isMainDictionaryAvailable);
    }

    void addToUserHistory(final String suggestion, final boolean wasAutoCapitalized, @Nonnull final NgramContext ngramContext, final long timeStampInSeconds, final boolean blockPotentiallyOffensive);

    boolean clearUserHistoryDictionary(final Context context);

    void closeDictionaries();

    String dump(final Context context);

    void dumpDictionaryForDebug(final String dictName);

    String getAccount();

    @Nonnull
    List<DictionaryStats> getDictionaryStats(final Context context);

    Locale getLocale();

    ExpandableBinaryDictionary getSubDictForTesting(final String dictName);

    @Nonnull
    SuggestionResults getSuggestionResults(final ComposedData composedData, final NgramContext ngramContext, @Nonnull final Keyboard keyboard, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int sessionId, final int inputStyle);

    boolean hasAtLeastOneInitializedMainDictionary();

    boolean hasAtLeastOneUninitializedMainDictionary();

    boolean isActive();

    boolean isForAccount(@Nullable final String account);

    boolean isForLocale(final Locale locale);

    boolean isValidSpellingWord(final String word);

    boolean isValidSuggestionWord(final String word);

    void onFinishInput(Context context);

    void onStartInput();

    void resetDictionaries(final Context context, final Locale newLocale, final boolean useContactsDict, final boolean usePersonalizedDicts, final boolean forceReloadMainDictionary, @Nullable final String account, final String dictNamePrefix, @Nullable final DictionaryInitializationListener listener);

    void resetDictionariesForTesting(final Context context, final Locale locale, final ArrayList<String> dictionaryTypes, final HashMap<String, File> dictionaryFiles, final Map<String, Map<String, String>> additionalDictAttributes, @Nullable final String account);

    void setValidSpellingWordReadCache(final LruCache<String, Boolean> cache);

    void setValidSpellingWordWriteCache(final LruCache<String, Boolean> cache);

    void unlearnFromUserHistory(final String word, @Nonnull final NgramContext ngramContext, final long timeStampInSeconds, final int eventType);

    boolean usesContacts();

    void waitForLoadingDictionariesForTesting(final long timeout, final TimeUnit unit) throws InterruptedException;

    void waitForLoadingMainDictionaries(final long timeout, final TimeUnit unit) throws InterruptedException;
}

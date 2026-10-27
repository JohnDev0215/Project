package com.android.inputmethod.latin.spellcheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.service.textservice.SpellCheckerService;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodSubtype;
import android.view.textservice.SuggestionsInfo;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.latin.DictionaryFacilitatorLruCache;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import com.android.inputmethod.latin.utils.AdditionalSubtypeUtils;
import com.android.inputmethod.latin.utils.ScriptUtils;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import com.android.inputmethod.latin.utils.SuggestionResults;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidSpellCheckerService extends SpellCheckerService implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String APOSTROPHE = "’";
    private static final boolean DEBUG = false;
    private static final String DICTIONARY_NAME_PREFIX = "spellcheck_";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final String PREF_USE_CONTACTS_KEY = "pref_spellcheck_use_contacts";
    public static final String SINGLE_QUOTE = "'";
    private static final int SPELLCHECKER_DUMMY_KEYBOARD_HEIGHT = 301;
    private static final int SPELLCHECKER_DUMMY_KEYBOARD_WIDTH = 480;
    private static final String TAG = "AndroidSpellCheckerService";
    private float mRecommendedThreshold;
    private final int MAX_NUM_OF_THREADS_READ_DICTIONARY = 2;
    private final Semaphore mSemaphore = new Semaphore(2, true);
    private final ConcurrentLinkedQueue<Integer> mSessionIdPool = new ConcurrentLinkedQueue<>();
    private final DictionaryFacilitatorLruCache mDictionaryFacilitatorCache = new DictionaryFacilitatorLruCache(this, DICTIONARY_NAME_PREFIX);
    private final ConcurrentHashMap<Locale, Keyboard> mKeyboardCache = new ConcurrentHashMap<>();
    private final SettingsValuesForSuggestion mSettingsValuesForSuggestion = new SettingsValuesForSuggestion(true);

    public AndroidSpellCheckerService() {
        for (int i = 0; i < 2; i++) {
            this.mSessionIdPool.add(Integer.valueOf(i));
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mRecommendedThreshold = Float.parseFloat(getString(R.string.spellchecker_recommended_threshold_value));
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        onSharedPreferenceChanged(defaultSharedPreferences, PREF_USE_CONTACTS_KEY);
    }

    public float getRecommendedThreshold() {
        return this.mRecommendedThreshold;
    }

    private static String getKeyboardLayoutNameForLocale(final Locale locale) {
        if (locale.getLanguage().equals("sr")) {
            return "south_slavic";
        }
        int scriptFromSpellCheckerLocale = ScriptUtils.getScriptFromSpellCheckerLocale(locale);
        if (scriptFromSpellCheckerLocale == 3) {
            return "east_slavic";
        }
        if (scriptFromSpellCheckerLocale == 11) {
            return SubtypeLocaleUtils.QWERTY;
        }
        if (scriptFromSpellCheckerLocale == 6) {
            return "greek";
        }
        if (scriptFromSpellCheckerLocale == 7) {
            return "hebrew";
        }
        throw new RuntimeException("Wrong script supplied: " + scriptFromSpellCheckerLocale);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        if (PREF_USE_CONTACTS_KEY.equals(key)) {
            this.mDictionaryFacilitatorCache.setUseContactsDictionary(prefs.getBoolean(PREF_USE_CONTACTS_KEY, true));
        }
    }

    @Override // android.service.textservice.SpellCheckerService
    public SpellCheckerService.Session createSession() {
        return AndroidSpellCheckerSessionFactory.newInstance(this);
    }

    public static SuggestionsInfo getNotInDictEmptySuggestions(final boolean reportAsTypo) {
        return new SuggestionsInfo(reportAsTypo ? 2 : 0, EMPTY_STRING_ARRAY);
    }

    public static SuggestionsInfo getInDictEmptySuggestions() {
        return new SuggestionsInfo(1, EMPTY_STRING_ARRAY);
    }

    public boolean isValidWord(final Locale locale, final String word) {
        this.mSemaphore.acquireUninterruptibly();
        try {
            return this.mDictionaryFacilitatorCache.get(locale).isValidSpellingWord(word);
        } finally {
            this.mSemaphore.release();
        }
    }

    public SuggestionResults getSuggestionResults(final Locale locale, final ComposedData composedData, final NgramContext ngramContext, @Nonnull final Keyboard keyboard) throws Throwable {
        this.mSemaphore.acquireUninterruptibly();
        Integer num = null;
        try {
            Integer numPoll = this.mSessionIdPool.poll();
            try {
                SuggestionResults suggestionResults = this.mDictionaryFacilitatorCache.get(locale).getSuggestionResults(composedData, ngramContext, keyboard, this.mSettingsValuesForSuggestion, numPoll.intValue(), 1);
                if (numPoll != null) {
                    this.mSessionIdPool.add(numPoll);
                }
                this.mSemaphore.release();
                return suggestionResults;
            } catch (Throwable th) {
                th = th;
                num = numPoll;
                if (num != null) {
                    this.mSessionIdPool.add(num);
                }
                this.mSemaphore.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public boolean hasMainDictionaryForLocale(final Locale locale) {
        this.mSemaphore.acquireUninterruptibly();
        try {
            return this.mDictionaryFacilitatorCache.get(locale).hasAtLeastOneInitializedMainDictionary();
        } finally {
            this.mSemaphore.release();
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(final Intent intent) {
        this.mSemaphore.acquireUninterruptibly(2);
        try {
            this.mDictionaryFacilitatorCache.closeDictionaries();
            this.mSemaphore.release(2);
            this.mKeyboardCache.clear();
            return false;
        } catch (Throwable th) {
            this.mSemaphore.release(2);
            throw th;
        }
    }

    public Keyboard getKeyboardForLocale(final Locale locale) {
        Keyboard keyboardCreateKeyboardForLocale = this.mKeyboardCache.get(locale);
        if (keyboardCreateKeyboardForLocale == null && (keyboardCreateKeyboardForLocale = createKeyboardForLocale(locale)) != null) {
            this.mKeyboardCache.put(locale, keyboardCreateKeyboardForLocale);
        }
        return keyboardCreateKeyboardForLocale;
    }

    private Keyboard createKeyboardForLocale(final Locale locale) {
        return createKeyboardSetForSpellChecker(AdditionalSubtypeUtils.createDummyAdditionalSubtype(locale.toString(), getKeyboardLayoutNameForLocale(locale))).getKeyboard(0);
    }

    private KeyboardLayoutSet createKeyboardSetForSpellChecker(final InputMethodSubtype subtype) {
        EditorInfo editorInfo = new EditorInfo();
        editorInfo.inputType = 1;
        KeyboardLayoutSet.Builder builder = new KeyboardLayoutSet.Builder(this, editorInfo);
        builder.setKeyboardGeometry(480, 301);
        builder.setSubtype(RichInputMethodSubtype.getRichInputMethodSubtype(subtype));
        builder.setIsSpellChecker(true);
        builder.disableTouchPositionCorrectionData();
        return builder.build();
    }
}

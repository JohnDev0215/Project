package com.android.inputmethod.latin.spellcheck;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Binder;
import android.provider.UserDictionary;
import android.service.textservice.SpellCheckerService;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import com.android.inputmethod.compat.SuggestionsInfoCompatUtils;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.WordComposer;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.utils.BinaryDictionaryUtils;
import com.android.inputmethod.latin.utils.ScriptUtils;
import com.android.inputmethod.latin.utils.StatsUtils;
import com.android.inputmethod.latin.utils.SuggestionResults;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AndroidWordLevelSpellCheckerSession extends SpellCheckerService.Session {
    private static final int CHECKABILITY_CHECKABLE = 0;
    private static final int CHECKABILITY_CONTAINS_PERIOD = 2;
    private static final int CHECKABILITY_EMAIL_OR_URL = 3;
    private static final int CHECKABILITY_FIRST_LETTER_UNCHECKABLE = 4;
    private static final int CHECKABILITY_TOO_MANY_NON_LETTERS = 1;
    private static final int CHECKABILITY_TOO_SHORT = 5;
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String TAG = "AndroidWordLevelSpellCheckerSession";
    private static final String quotesRegexp = "(\\u0022|\\u0027|\\u0060|\\u00B4|\\u2018|\\u2018|\\u201C|\\u201D)";
    private Locale mLocale;
    private final ContentObserver mObserver;
    private int mScript;
    private final AndroidSpellCheckerService mService;
    protected final SuggestionsCache mSuggestionsCache = new SuggestionsCache();

    /* JADX INFO: Access modifiers changed from: private */
    static final class SuggestionsParams {
        public final int mFlags;
        public final String[] mSuggestions;

        public SuggestionsParams(String[] suggestions, int flags) {
            this.mSuggestions = suggestions;
            this.mFlags = flags;
        }
    }

    protected static final class SuggestionsCache {
        private static final int MAX_CACHE_SIZE = 50;
        private final LruCache<String, SuggestionsParams> mUnigramSuggestionsInfoCache = new LruCache<>(50);

        protected SuggestionsCache() {
        }

        private static String generateKey(final String query) {
            return query + "";
        }

        public SuggestionsParams getSuggestionsFromCache(final String query) {
            return this.mUnigramSuggestionsInfoCache.get(query);
        }

        public void putSuggestionsToCache(final String query, final String[] suggestions, final int flags) {
            if (suggestions == null || TextUtils.isEmpty(query)) {
                return;
            }
            this.mUnigramSuggestionsInfoCache.put(generateKey(query), new SuggestionsParams(suggestions, flags));
        }

        public void clearCache() {
            this.mUnigramSuggestionsInfoCache.evictAll();
        }
    }

    AndroidWordLevelSpellCheckerSession(final AndroidSpellCheckerService service) {
        this.mService = service;
        ContentResolver contentResolver = service.getContentResolver();
        ContentObserver contentObserver = new ContentObserver(null) { // from class: com.android.inputmethod.latin.spellcheck.AndroidWordLevelSpellCheckerSession.1
            @Override // android.database.ContentObserver
            public void onChange(boolean self) {
                AndroidWordLevelSpellCheckerSession.this.mSuggestionsCache.clearCache();
            }
        };
        this.mObserver = contentObserver;
        contentResolver.registerContentObserver(UserDictionary.Words.CONTENT_URI, true, contentObserver);
    }

    @Override // android.service.textservice.SpellCheckerService.Session
    public void onCreate() {
        String locale = getLocale();
        Locale localeConstructLocaleFromString = locale == null ? null : LocaleUtils.constructLocaleFromString(locale);
        this.mLocale = localeConstructLocaleFromString;
        this.mScript = ScriptUtils.getScriptFromSpellCheckerLocale(localeConstructLocaleFromString);
    }

    @Override // android.service.textservice.SpellCheckerService.Session
    public void onClose() {
        this.mService.getContentResolver().unregisterContentObserver(this.mObserver);
    }

    private static int getCheckabilityInScript(final String text, final int script) {
        if (TextUtils.isEmpty(text) || text.length() <= 1) {
            return 5;
        }
        int iCodePointAt = text.codePointAt(0);
        if (!ScriptUtils.isLetterPartOfScript(iCodePointAt, script) && 39 != iCodePointAt) {
            return 4;
        }
        int length = text.length();
        int iOffsetByCodePoints = 0;
        int i = 0;
        while (iOffsetByCodePoints < length) {
            int iCodePointAt2 = text.codePointAt(iOffsetByCodePoints);
            if (64 == iCodePointAt2 || 47 == iCodePointAt2) {
                return 3;
            }
            if (46 == iCodePointAt2) {
                return 2;
            }
            if (ScriptUtils.isLetterPartOfScript(iCodePointAt2, script)) {
                i++;
            }
            iOffsetByCodePoints = text.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        return i * 4 < length * 3 ? 1 : 0;
    }

    private boolean isInDictForAnyCapitalization(final String text, final int capitalizeType) {
        if (this.mService.isValidWord(this.mLocale, text)) {
            return true;
        }
        if (capitalizeType == 0) {
            return false;
        }
        String lowerCase = text.toLowerCase(this.mLocale);
        if (this.mService.isValidWord(this.mLocale, lowerCase)) {
            return true;
        }
        if (1 == capitalizeType) {
            return false;
        }
        AndroidSpellCheckerService androidSpellCheckerService = this.mService;
        Locale locale = this.mLocale;
        return androidSpellCheckerService.isValidWord(locale, StringUtils.capitalizeFirstAndDowncaseRest(lowerCase, locale));
    }

    private SuggestionsInfo onGetSuggestionsInternal(final TextInfo textInfo, final int suggestionsLimit) {
        return onGetSuggestionsInternal(textInfo, null, suggestionsLimit);
    }

    protected SuggestionsInfo onGetSuggestionsInternal(final TextInfo textInfo, final NgramContext ngramContext, final int suggestionsLimit) {
        boolean z;
        try {
            String strReplaceAll = textInfo.getText().replaceAll(AndroidSpellCheckerService.APOSTROPHE, AndroidSpellCheckerService.SINGLE_QUOTE).replaceAll("^(\\u0022|\\u0027|\\u0060|\\u00B4|\\u2018|\\u2018|\\u201C|\\u201D)", "").replaceAll("(\\u0022|\\u0027|\\u0060|\\u00B4|\\u2018|\\u2018|\\u201C|\\u201D)$", "");
            if (!this.mService.hasMainDictionaryForLocale(this.mLocale)) {
                return AndroidSpellCheckerService.getNotInDictEmptySuggestions(false);
            }
            int checkabilityInScript = getCheckabilityInScript(strReplaceAll, this.mScript);
            if (checkabilityInScript != 0) {
                boolean z2 = true;
                if (2 == checkabilityInScript) {
                    String[] strArrSplit = strReplaceAll.split(Constants.REGEXP_PERIOD);
                    int length = strArrSplit.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            z = true;
                            break;
                        }
                        if (!this.mService.isValidWord(this.mLocale, strArrSplit[i])) {
                            z = false;
                            break;
                        }
                        i++;
                    }
                    if (z) {
                        return new SuggestionsInfo(6, new String[]{TextUtils.join(" ", strArrSplit)});
                    }
                }
                if (this.mService.isValidWord(this.mLocale, strReplaceAll)) {
                    return AndroidSpellCheckerService.getInDictEmptySuggestions();
                }
                if (2 != checkabilityInScript) {
                    z2 = false;
                }
                return AndroidSpellCheckerService.getNotInDictEmptySuggestions(z2);
            }
            int capitalizationType = StringUtils.getCapitalizationType(strReplaceAll);
            if (isInDictForAnyCapitalization(strReplaceAll, capitalizationType)) {
                return AndroidSpellCheckerService.getInDictEmptySuggestions();
            }
            Keyboard keyboardForLocale = this.mService.getKeyboardForLocale(this.mLocale);
            if (keyboardForLocale == null) {
                Log.w(TAG, "onGetSuggestionsInternal() : No keyboard for locale: " + this.mLocale);
                return AndroidSpellCheckerService.getNotInDictEmptySuggestions(false);
            }
            WordComposer wordComposer = new WordComposer();
            int[] codePointArray = StringUtils.toCodePointArray(strReplaceAll);
            wordComposer.setComposingWord(codePointArray, keyboardForLocale.getCoordinates(codePointArray));
            Result result = getResult(capitalizationType, this.mLocale, suggestionsLimit, this.mService.getRecommendedThreshold(), strReplaceAll, this.mService.getSuggestionResults(this.mLocale, wordComposer.getComposedDataSnapshot(), ngramContext, keyboardForLocale));
            StatsUtils.onInvalidWordIdentification(strReplaceAll);
            int valueOf_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS = (result.mHasRecommendedSuggestions ? SuggestionsInfoCompatUtils.getValueOf_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS() : 0) | 2;
            SuggestionsInfo suggestionsInfo = new SuggestionsInfo(valueOf_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS, result.mSuggestions);
            this.mSuggestionsCache.putSuggestionsToCache(strReplaceAll, result.mSuggestions, valueOf_RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS);
            return suggestionsInfo;
        } catch (RuntimeException e) {
            Log.e(TAG, "Exception while spellchecking", e);
            return AndroidSpellCheckerService.getNotInDictEmptySuggestions(false);
        }
    }

    private static final class Result {
        public final boolean mHasRecommendedSuggestions;
        public final String[] mSuggestions;

        public Result(final String[] gatheredSuggestions, final boolean hasRecommendedSuggestions) {
            this.mSuggestions = gatheredSuggestions;
            this.mHasRecommendedSuggestions = hasRecommendedSuggestions;
        }
    }

    private static Result getResult(final int capitalizeType, final Locale locale, final int suggestionsLimit, final float recommendedThreshold, final String originalText, final SuggestionResults suggestionResults) {
        String strCapitalizeFirstCodePoint;
        if (suggestionResults.isEmpty() || suggestionsLimit <= 0) {
            return new Result(null, false);
        }
        ArrayList arrayList = new ArrayList();
        for (SuggestedWords.SuggestedWordInfo suggestedWordInfo : suggestionResults) {
            if (2 == capitalizeType) {
                strCapitalizeFirstCodePoint = suggestedWordInfo.mWord.toUpperCase(locale);
            } else if (1 == capitalizeType) {
                strCapitalizeFirstCodePoint = StringUtils.capitalizeFirstCodePoint(suggestedWordInfo.mWord, locale);
            } else {
                strCapitalizeFirstCodePoint = suggestedWordInfo.mWord;
            }
            arrayList.add(strCapitalizeFirstCodePoint);
        }
        StringUtils.removeDupes(arrayList);
        List listSubList = arrayList.subList(0, Math.min(arrayList.size(), suggestionsLimit));
        return new Result((String[]) listSubList.toArray(new String[listSubList.size()]), BinaryDictionaryUtils.calcNormalizedScore(originalText, (String) arrayList.get(0), suggestionResults.first().mScore) > recommendedThreshold);
    }

    @Override // android.service.textservice.SpellCheckerService.Session
    public SuggestionsInfo onGetSuggestions(final TextInfo textInfo, final int suggestionsLimit) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return onGetSuggestionsInternal(textInfo, suggestionsLimit);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}

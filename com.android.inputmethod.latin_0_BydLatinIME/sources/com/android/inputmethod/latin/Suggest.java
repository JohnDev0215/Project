package com.android.inputmethod.latin;

import android.text.TextUtils;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import com.android.inputmethod.latin.utils.BinaryDictionaryUtils;
import com.android.inputmethod.latin.utils.SuggestionResults;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Suggest {
    private static final boolean DBG = false;
    private static final int MAXIMUM_AUTO_CORRECT_LENGTH_FOR_GERMAN = 12;
    public static final int SESSION_ID_GESTURE = 0;
    public static final int SESSION_ID_TYPING = 0;
    private static final int SUPPRESS_SUGGEST_THRESHOLD = -2000000000;
    public static final String TAG = "Suggest";
    private static final HashMap<String, Integer> sLanguageToMaximumAutoCorrectionWithSpaceLength;
    private float mAutoCorrectionThreshold;
    private final DictionaryFacilitator mDictionaryFacilitator;
    private float mPlausibilityThreshold;

    public interface OnGetSuggestedWordsCallback {
        void onGetSuggestedWords(final SuggestedWords suggestedWords);
    }

    static {
        HashMap<String, Integer> map = new HashMap<>();
        sLanguageToMaximumAutoCorrectionWithSpaceLength = map;
        map.put(Locale.GERMAN.getLanguage(), 12);
    }

    public Suggest(final DictionaryFacilitator dictionaryFacilitator) {
        this.mDictionaryFacilitator = dictionaryFacilitator;
    }

    public void setAutoCorrectionThreshold(final float threshold) {
        this.mAutoCorrectionThreshold = threshold;
    }

    public void setPlausibilityThreshold(final float threshold) {
        this.mPlausibilityThreshold = threshold;
    }

    public void getSuggestedWords(final WordComposer wordComposer, final NgramContext ngramContext, final Keyboard keyboard, final SettingsValuesForSuggestion settingsValuesForSuggestion, final boolean isCorrectionEnabled, final int inputStyle, final int sequenceNumber, final OnGetSuggestedWordsCallback callback) {
        if (wordComposer.isBatchMode()) {
            getSuggestedWordsForBatchInput(wordComposer, ngramContext, keyboard, settingsValuesForSuggestion, inputStyle, sequenceNumber, callback);
        } else {
            getSuggestedWordsForNonBatchInput(wordComposer, ngramContext, keyboard, settingsValuesForSuggestion, inputStyle, isCorrectionEnabled, sequenceNumber, callback);
        }
    }

    private static ArrayList<SuggestedWords.SuggestedWordInfo> getTransformedSuggestedWordInfoList(final WordComposer wordComposer, final SuggestionResults results, final int trailingSingleQuotesCount, final Locale defaultLocale) {
        boolean z = wordComposer.isAllUpperCase() && !wordComposer.isResumed();
        boolean zIsOrWillBeOnlyFirstCharCapitalized = wordComposer.isOrWillBeOnlyFirstCharCapitalized();
        ArrayList<SuggestedWords.SuggestedWordInfo> arrayList = new ArrayList<>(results);
        int size = arrayList.size();
        if (zIsOrWillBeOnlyFirstCharCapitalized || z || trailingSingleQuotesCount != 0) {
            for (int i = 0; i < size; i++) {
                SuggestedWords.SuggestedWordInfo suggestedWordInfo = arrayList.get(i);
                Locale locale = suggestedWordInfo.mSourceDict.mLocale;
                if (locale == null) {
                    locale = defaultLocale;
                }
                arrayList.set(i, getTransformedSuggestedWordInfo(suggestedWordInfo, locale, z, zIsOrWillBeOnlyFirstCharCapitalized, trailingSingleQuotesCount));
            }
        }
        return arrayList;
    }

    private static SuggestedWords.SuggestedWordInfo getWhitelistedWordInfoOrNull(@Nonnull final ArrayList<SuggestedWords.SuggestedWordInfo> suggestions) {
        if (suggestions.isEmpty()) {
            return null;
        }
        SuggestedWords.SuggestedWordInfo suggestedWordInfo = suggestions.get(0);
        if (suggestedWordInfo.isKindOf(3)) {
            return suggestedWordInfo;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getSuggestedWordsForNonBatchInput(final com.android.inputmethod.latin.WordComposer r22, final com.android.inputmethod.latin.NgramContext r23, final com.android.inputmethod.keyboard.Keyboard r24, final com.android.inputmethod.latin.settings.SettingsValuesForSuggestion r25, final int r26, final boolean r27, final int r28, final com.android.inputmethod.latin.Suggest.OnGetSuggestedWordsCallback r29) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.Suggest.getSuggestedWordsForNonBatchInput(com.android.inputmethod.latin.WordComposer, com.android.inputmethod.latin.NgramContext, com.android.inputmethod.keyboard.Keyboard, com.android.inputmethod.latin.settings.SettingsValuesForSuggestion, int, boolean, int, com.android.inputmethod.latin.Suggest$OnGetSuggestedWordsCallback):void");
    }

    private void getSuggestedWordsForBatchInput(final WordComposer wordComposer, final NgramContext ngramContext, final Keyboard keyboard, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int inputStyle, final int sequenceNumber, final OnGetSuggestedWordsCallback callback) {
        SuggestionResults suggestionResults = this.mDictionaryFacilitator.getSuggestionResults(wordComposer.getComposedDataSnapshot(), ngramContext, keyboard, settingsValuesForSuggestion, 0, inputStyle);
        Locale locale = this.mDictionaryFacilitator.getLocale();
        ArrayList arrayList = new ArrayList(suggestionResults);
        int size = arrayList.size();
        boolean zWasShiftedNoLock = wordComposer.wasShiftedNoLock();
        boolean zIsAllUpperCase = wordComposer.isAllUpperCase();
        if (zWasShiftedNoLock || zIsAllUpperCase) {
            for (int i = 0; i < size; i++) {
                SuggestedWords.SuggestedWordInfo suggestedWordInfo = (SuggestedWords.SuggestedWordInfo) arrayList.get(i);
                Locale locale2 = suggestedWordInfo.mSourceDict.mLocale;
                if (locale2 == null) {
                    locale2 = locale;
                }
                arrayList.set(i, getTransformedSuggestedWordInfo(suggestedWordInfo, locale2, zIsAllUpperCase, zWasShiftedNoLock, 0));
            }
        }
        if (arrayList.size() > 1 && TextUtils.equals(((SuggestedWords.SuggestedWordInfo) arrayList.get(0)).mWord, wordComposer.getRejectedBatchModeSuggestion())) {
            arrayList.add(1, (SuggestedWords.SuggestedWordInfo) arrayList.remove(0));
        }
        SuggestedWords.SuggestedWordInfo.removeDups(null, arrayList);
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            if (((SuggestedWords.SuggestedWordInfo) arrayList.get(size2)).mScore < SUPPRESS_SUGGEST_THRESHOLD) {
                arrayList.remove(size2);
            }
        }
        callback.onGetSuggestedWords(new SuggestedWords(arrayList, suggestionResults.mRawSuggestions, arrayList.isEmpty() ? null : (SuggestedWords.SuggestedWordInfo) arrayList.get(0), true, false, false, inputStyle, sequenceNumber));
    }

    private static ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestionsInfoListWithDebugInfo(final String typedWord, final ArrayList<SuggestedWords.SuggestedWordInfo> suggestions) {
        String string;
        SuggestedWords.SuggestedWordInfo suggestedWordInfo = suggestions.get(0);
        suggestedWordInfo.setDebugString("+");
        int size = suggestions.size();
        ArrayList<SuggestedWords.SuggestedWordInfo> arrayList = new ArrayList<>(size);
        arrayList.add(suggestedWordInfo);
        int i = 0;
        while (i < size - 1) {
            i++;
            SuggestedWords.SuggestedWordInfo suggestedWordInfo2 = suggestions.get(i);
            float fCalcNormalizedScore = BinaryDictionaryUtils.calcNormalizedScore(typedWord, suggestedWordInfo2.toString(), suggestedWordInfo2.mScore);
            if (fCalcNormalizedScore > 0.0f) {
                string = String.format(Locale.ROOT, "%d (%4.2f), %s", Integer.valueOf(suggestedWordInfo2.mScore), Float.valueOf(fCalcNormalizedScore), suggestedWordInfo2.mSourceDict.mDictType);
            } else {
                string = Integer.toString(suggestedWordInfo2.mScore);
            }
            suggestedWordInfo2.setDebugString(string);
            arrayList.add(suggestedWordInfo2);
        }
        return arrayList;
    }

    private static boolean isAllowedByAutoCorrectionWithSpaceFilter(final SuggestedWords.SuggestedWordInfo info) {
        Integer num;
        Locale locale = info.mSourceDict.mLocale;
        return locale == null || (num = sLanguageToMaximumAutoCorrectionWithSpaceLength.get(locale.getLanguage())) == null || info.mWord.length() <= num.intValue() || -1 == info.mWord.indexOf(32);
    }

    static SuggestedWords.SuggestedWordInfo getTransformedSuggestedWordInfo(final SuggestedWords.SuggestedWordInfo wordInfo, final Locale locale, final boolean isAllUpperCase, final boolean isOnlyFirstCharCapitalized, final int trailingSingleQuotesCount) {
        StringBuilder sb = new StringBuilder(wordInfo.mWord.length());
        if (isAllUpperCase) {
            sb.append(wordInfo.mWord.toUpperCase(locale));
        } else if (isOnlyFirstCharCapitalized) {
            sb.append(StringUtils.capitalizeFirstCodePoint(wordInfo.mWord, locale));
        } else {
            sb.append(wordInfo.mWord);
        }
        for (int i = (trailingSingleQuotesCount - (-1 == wordInfo.mWord.indexOf(39) ? 0 : 1)) - 1; i >= 0; i--) {
            sb.appendCodePoint(39);
        }
        return new SuggestedWords.SuggestedWordInfo(sb.toString(), wordInfo.mPrevWordsContext, wordInfo.mScore, wordInfo.mKindAndFlags, wordInfo.mSourceDict, wordInfo.mIndexOfTouchPointOfSecondWord, wordInfo.mAutoCommitFirstWordConfidence);
    }
}

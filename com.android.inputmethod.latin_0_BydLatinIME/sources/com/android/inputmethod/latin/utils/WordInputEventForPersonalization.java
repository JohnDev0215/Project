package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.settings.SpacingAndPunctuations;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class WordInputEventForPersonalization {
    private static final boolean DEBUG_TOKEN = false;
    private static final String TAG = "WordInputEventForPersonalization";
    public final boolean[] mIsPrevWordBeginningOfSentenceArray;
    public final int[][] mPrevWordArray;
    public final int mPrevWordsCount;
    public final int[] mTargetWord;
    public final int mTimestamp;

    public WordInputEventForPersonalization(final CharSequence targetWord, final NgramContext ngramContext, final int timestamp) {
        int[][] iArr = new int[3][];
        this.mPrevWordArray = iArr;
        boolean[] zArr = new boolean[3];
        this.mIsPrevWordBeginningOfSentenceArray = zArr;
        this.mTargetWord = StringUtils.toCodePointArray(targetWord);
        this.mPrevWordsCount = ngramContext.getPrevWordCount();
        ngramContext.outputToArray(iArr, zArr);
        this.mTimestamp = timestamp;
    }

    public static ArrayList<WordInputEventForPersonalization> createInputEventFrom(final List<String> tokens, final int timestamp, final SpacingAndPunctuations spacingAndPunctuations, final Locale locale) {
        ArrayList<WordInputEventForPersonalization> arrayList = new ArrayList<>();
        int size = tokens.size();
        NgramContext nextNgramContext = NgramContext.EMPTY_PREV_WORDS_INFO;
        for (int i = 0; i < size; i++) {
            String str = tokens.get(i);
            if (!StringUtils.isEmptyStringOrWhiteSpaces(str)) {
                if (!DictionaryInfoUtils.looksValidForDictionaryInsertion(str, spacingAndPunctuations)) {
                    nextNgramContext = NgramContext.EMPTY_PREV_WORDS_INFO;
                } else {
                    WordInputEventForPersonalization wordInputEventForPersonalizationDetectWhetherVaildWordOrNotAndGetInputEvent = detectWhetherVaildWordOrNotAndGetInputEvent(nextNgramContext, str, timestamp, locale);
                    if (wordInputEventForPersonalizationDetectWhetherVaildWordOrNotAndGetInputEvent != null) {
                        arrayList.add(wordInputEventForPersonalizationDetectWhetherVaildWordOrNotAndGetInputEvent);
                        nextNgramContext = nextNgramContext.getNextNgramContext(new NgramContext.WordInfo(str));
                    }
                }
            }
        }
        return arrayList;
    }

    private static WordInputEventForPersonalization detectWhetherVaildWordOrNotAndGetInputEvent(final NgramContext ngramContext, final String targetWord, final int timestamp, final Locale locale) {
        if (locale == null) {
            return null;
        }
        return new WordInputEventForPersonalization(targetWord, ngramContext, timestamp);
    }
}

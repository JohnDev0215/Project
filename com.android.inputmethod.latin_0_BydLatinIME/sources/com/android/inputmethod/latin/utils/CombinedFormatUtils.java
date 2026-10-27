package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.makedict.NgramProperty;
import com.android.inputmethod.latin.makedict.ProbabilityInfo;
import com.android.inputmethod.latin.makedict.WordProperty;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class CombinedFormatUtils {
    public static final String BEGINNING_OF_SENTENCE_TAG = "beginning_of_sentence";
    public static final String BIGRAM_TAG = "bigram";
    public static final String DICTIONARY_TAG = "dictionary";
    public static final String HISTORICAL_INFO_SEPARATOR = ":";
    public static final String HISTORICAL_INFO_TAG = "historicalInfo";
    public static final String NGRAM_PREV_WORD_TAG = "prev_word";
    public static final String NGRAM_TAG = "ngram";
    public static final String NOT_A_WORD_TAG = "not_a_word";
    public static final String POSSIBLY_OFFENSIVE_TAG = "possibly_offensive";
    public static final String PROBABILITY_TAG = "f";
    public static final String TRUE_VALUE = "true";
    public static final String WORD_TAG = "word";

    public static String formatAttributeMap(final HashMap<String, String> attributeMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("dictionary=");
        if (attributeMap.containsKey("dictionary")) {
            sb.append(attributeMap.get("dictionary"));
        }
        for (String str : attributeMap.keySet()) {
            if (!str.equals("dictionary")) {
                sb.append("," + str + "=" + attributeMap.get(str));
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public static String formatWordProperty(final WordProperty wordProperty) {
        StringBuilder sb = new StringBuilder();
        sb.append(" word=" + wordProperty.mWord);
        sb.append(",");
        sb.append(formatProbabilityInfo(wordProperty.mProbabilityInfo));
        if (wordProperty.mIsBeginningOfSentence) {
            sb.append(",beginning_of_sentence=true");
        }
        if (wordProperty.mIsNotAWord) {
            sb.append(",not_a_word=true");
        }
        if (wordProperty.mIsPossiblyOffensive) {
            sb.append(",possibly_offensive=true");
        }
        sb.append("\n");
        if (wordProperty.mHasNgrams) {
            for (NgramProperty ngramProperty : wordProperty.mNgrams) {
                sb.append(" ngram=" + ngramProperty.mTargetWord.mWord);
                sb.append(",");
                sb.append(formatProbabilityInfo(ngramProperty.mTargetWord.mProbabilityInfo));
                sb.append("\n");
                int i = 0;
                while (i < ngramProperty.mNgramContext.getPrevWordCount()) {
                    StringBuilder sbAppend = new StringBuilder().append("  prev_word[").append(i).append("]=");
                    i++;
                    sb.append(sbAppend.append((Object) ngramProperty.mNgramContext.getNthPrevWord(i)).toString());
                    if (ngramProperty.mNgramContext.isNthPrevWordBeginningOfSentence(i)) {
                        sb.append(",beginning_of_sentence=true");
                    }
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static String formatProbabilityInfo(final ProbabilityInfo probabilityInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("f=" + probabilityInfo.mProbability);
        if (probabilityInfo.hasHistoricalInfo()) {
            sb.append(",");
            sb.append("historicalInfo=");
            sb.append(probabilityInfo.mTimestamp);
            sb.append(":");
            sb.append(probabilityInfo.mLevel);
            sb.append(":");
            sb.append(probabilityInfo.mCount);
        }
        return sb.toString();
    }

    public static boolean isLiteralTrue(final String value) {
        return TRUE_VALUE.equalsIgnoreCase(value);
    }
}

package com.android.inputmethod.latin.makedict;

import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.utils.CombinedFormatUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class WordProperty implements Comparable<WordProperty> {
    public final boolean mHasNgrams;
    private int mHashCode = 0;
    public final boolean mIsBeginningOfSentence;
    public final boolean mIsNotAWord;
    public final boolean mIsPossiblyOffensive;
    public final ArrayList<NgramProperty> mNgrams;
    public final ProbabilityInfo mProbabilityInfo;
    public final String mWord;

    public WordProperty(final String word, final ProbabilityInfo probabilityInfo, @Nullable final ArrayList<WeightedString> bigrams, final boolean isNotAWord, final boolean isPossiblyOffensive) {
        boolean z = false;
        this.mWord = word;
        this.mProbabilityInfo = probabilityInfo;
        if (bigrams == null) {
            this.mNgrams = null;
        } else {
            this.mNgrams = new ArrayList<>();
            NgramContext ngramContext = new NgramContext(new NgramContext.WordInfo(word));
            Iterator<WeightedString> it = bigrams.iterator();
            while (it.hasNext()) {
                this.mNgrams.add(new NgramProperty(it.next(), ngramContext));
            }
        }
        this.mIsBeginningOfSentence = false;
        this.mIsNotAWord = isNotAWord;
        this.mIsPossiblyOffensive = isPossiblyOffensive;
        if (bigrams != null && !bigrams.isEmpty()) {
            z = true;
        }
        this.mHasNgrams = z;
    }

    private static ProbabilityInfo createProbabilityInfoFromArray(final int[] probabilityInfo) {
        return new ProbabilityInfo(probabilityInfo[0], probabilityInfo[1], probabilityInfo[2], probabilityInfo[3]);
    }

    public WordProperty(final int[] codePoints, final boolean isNotAWord, final boolean isPossiblyOffensive, final boolean hasBigram, final boolean isBeginningOfSentence, final int[] probabilityInfo, final ArrayList<int[][]> ngramPrevWordsArray, final ArrayList<boolean[]> ngramPrevWordIsBeginningOfSentenceArray, final ArrayList<int[]> ngramTargets, final ArrayList<int[]> ngramProbabilityInfo) {
        NgramContext.WordInfo wordInfo;
        this.mWord = StringUtils.getStringFromNullTerminatedCodePointArray(codePoints);
        this.mProbabilityInfo = createProbabilityInfoFromArray(probabilityInfo);
        ArrayList<NgramProperty> arrayList = new ArrayList<>();
        this.mIsBeginningOfSentence = isBeginningOfSentence;
        this.mIsNotAWord = isNotAWord;
        this.mIsPossiblyOffensive = isPossiblyOffensive;
        this.mHasNgrams = hasBigram;
        int size = ngramTargets.size();
        for (int i = 0; i < size; i++) {
            WeightedString weightedString = new WeightedString(StringUtils.getStringFromNullTerminatedCodePointArray(ngramTargets.get(i)), createProbabilityInfoFromArray(ngramProbabilityInfo.get(i)));
            int[][] iArr = ngramPrevWordsArray.get(i);
            boolean[] zArr = ngramPrevWordIsBeginningOfSentenceArray.get(i);
            NgramContext.WordInfo[] wordInfoArr = new NgramContext.WordInfo[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (zArr[i2]) {
                    wordInfo = NgramContext.WordInfo.BEGINNING_OF_SENTENCE_WORD_INFO;
                } else {
                    wordInfo = new NgramContext.WordInfo(StringUtils.getStringFromNullTerminatedCodePointArray(iArr[i2]));
                }
                wordInfoArr[i2] = wordInfo;
            }
            arrayList.add(new NgramProperty(weightedString, new NgramContext(wordInfoArr)));
        }
        this.mNgrams = arrayList.isEmpty() ? null : arrayList;
    }

    public ArrayList<WeightedString> getBigrams() {
        if (this.mNgrams == null) {
            return null;
        }
        ArrayList<WeightedString> arrayList = new ArrayList<>();
        for (NgramProperty ngramProperty : this.mNgrams) {
            if (ngramProperty.mNgramContext.getPrevWordCount() == 1) {
                arrayList.add(ngramProperty.mTargetWord);
            }
        }
        return arrayList;
    }

    public int getProbability() {
        return this.mProbabilityInfo.mProbability;
    }

    private static int computeHashCode(WordProperty word) {
        return Arrays.hashCode(new Object[]{word.mWord, word.mProbabilityInfo, word.mNgrams, Boolean.valueOf(word.mIsNotAWord), Boolean.valueOf(word.mIsPossiblyOffensive)});
    }

    @Override // java.lang.Comparable
    public int compareTo(final WordProperty w) {
        if (getProbability() < w.getProbability()) {
            return 1;
        }
        if (getProbability() > w.getProbability()) {
            return -1;
        }
        return this.mWord.compareTo(w.mWord);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WordProperty)) {
            return false;
        }
        WordProperty wordProperty = (WordProperty) o;
        return this.mProbabilityInfo.equals(wordProperty.mProbabilityInfo) && this.mWord.equals(wordProperty.mWord) && equals(this.mNgrams, wordProperty.mNgrams) && this.mIsNotAWord == wordProperty.mIsNotAWord && this.mIsPossiblyOffensive == wordProperty.mIsPossiblyOffensive && this.mHasNgrams == wordProperty.mHasNgrams;
    }

    private static <T> boolean equals(final ArrayList<T> a, final ArrayList<T> b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    public int hashCode() {
        if (this.mHashCode == 0) {
            this.mHashCode = computeHashCode(this);
        }
        return this.mHashCode;
    }

    public boolean isValid() {
        return getProbability() != -1;
    }

    public String toString() {
        return CombinedFormatUtils.formatWordProperty(this);
    }
}

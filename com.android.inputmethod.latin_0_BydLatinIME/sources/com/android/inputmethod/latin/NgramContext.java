package com.android.inputmethod.latin;

import android.text.TextUtils;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NgramContext {
    public static final String BEGINNING_OF_SENTENCE_TAG = "<S>";
    public static final String CONTEXT_SEPARATOR = " ";
    private final int mMaxPrevWordCount;
    private final int mPrevWordsCount;
    private final WordInfo[] mPrevWordsInfo;

    @Nonnull
    public static final NgramContext EMPTY_PREV_WORDS_INFO = new NgramContext(WordInfo.EMPTY_WORD_INFO);

    @Nonnull
    public static final NgramContext BEGINNING_OF_SENTENCE = new NgramContext(WordInfo.BEGINNING_OF_SENTENCE_WORD_INFO);

    public static NgramContext getEmptyPrevWordsContext(int maxPrevWordCount) {
        return new NgramContext(maxPrevWordCount, WordInfo.EMPTY_WORD_INFO);
    }

    public static class WordInfo {
        public final boolean mIsBeginningOfSentence;
        public final CharSequence mWord;

        @Nonnull
        public static final WordInfo EMPTY_WORD_INFO = new WordInfo(null);

        @Nonnull
        public static final WordInfo BEGINNING_OF_SENTENCE_WORD_INFO = new WordInfo();

        private WordInfo() {
            this.mWord = "";
            this.mIsBeginningOfSentence = true;
        }

        public WordInfo(final CharSequence word) {
            this.mWord = word;
            this.mIsBeginningOfSentence = false;
        }

        public boolean isValid() {
            return this.mWord != null;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.mWord, Boolean.valueOf(this.mIsBeginningOfSentence)});
        }

        public boolean equals(Object o) {
            CharSequence charSequence;
            if (this == o) {
                return true;
            }
            if (!(o instanceof WordInfo)) {
                return false;
            }
            WordInfo wordInfo = (WordInfo) o;
            CharSequence charSequence2 = this.mWord;
            return (charSequence2 == null || (charSequence = wordInfo.mWord) == null) ? charSequence2 == wordInfo.mWord && this.mIsBeginningOfSentence == wordInfo.mIsBeginningOfSentence : TextUtils.equals(charSequence2, charSequence) && this.mIsBeginningOfSentence == wordInfo.mIsBeginningOfSentence;
        }
    }

    public NgramContext(final WordInfo... prevWordsInfo) {
        this(3, prevWordsInfo);
    }

    public NgramContext(final int maxPrevWordCount, final WordInfo... prevWordsInfo) {
        this.mPrevWordsInfo = prevWordsInfo;
        this.mPrevWordsCount = prevWordsInfo.length;
        this.mMaxPrevWordCount = maxPrevWordCount;
    }

    @Nonnull
    public NgramContext getNextNgramContext(final WordInfo wordInfo) {
        int iMin = Math.min(this.mMaxPrevWordCount, this.mPrevWordsCount + 1);
        WordInfo[] wordInfoArr = new WordInfo[iMin];
        wordInfoArr[0] = wordInfo;
        System.arraycopy(this.mPrevWordsInfo, 0, wordInfoArr, 1, iMin - 1);
        return new NgramContext(this.mMaxPrevWordCount, wordInfoArr);
    }

    public String extractPrevWordsContext() {
        ArrayList arrayList = new ArrayList();
        for (int length = this.mPrevWordsInfo.length - 1; length >= 0; length--) {
            WordInfo[] wordInfoArr = this.mPrevWordsInfo;
            if (wordInfoArr[length] != null && wordInfoArr[length].isValid()) {
                WordInfo wordInfo = this.mPrevWordsInfo[length];
                if (wordInfo.mIsBeginningOfSentence) {
                    arrayList.add(BEGINNING_OF_SENTENCE_TAG);
                } else {
                    String string = wordInfo.mWord.toString();
                    if (!string.isEmpty()) {
                        arrayList.add(string);
                    }
                }
            }
        }
        return TextUtils.join(" ", arrayList);
    }

    public String[] extractPrevWordsContextArray() {
        ArrayList arrayList = new ArrayList();
        for (int length = this.mPrevWordsInfo.length - 1; length >= 0; length--) {
            WordInfo[] wordInfoArr = this.mPrevWordsInfo;
            if (wordInfoArr[length] != null && wordInfoArr[length].isValid()) {
                WordInfo wordInfo = this.mPrevWordsInfo[length];
                if (wordInfo.mIsBeginningOfSentence) {
                    arrayList.add(BEGINNING_OF_SENTENCE_TAG);
                } else {
                    String string = wordInfo.mWord.toString();
                    if (!string.isEmpty()) {
                        arrayList.add(string);
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean isValid() {
        return this.mPrevWordsCount > 0 && this.mPrevWordsInfo[0].isValid();
    }

    public boolean isBeginningOfSentenceContext() {
        return this.mPrevWordsCount > 0 && this.mPrevWordsInfo[0].mIsBeginningOfSentence;
    }

    public CharSequence getNthPrevWord(final int n) {
        if (n <= 0 || n > this.mPrevWordsCount) {
            return null;
        }
        return this.mPrevWordsInfo[n - 1].mWord;
    }

    public boolean isNthPrevWordBeginningOfSentence(final int n) {
        if (n <= 0 || n > this.mPrevWordsCount) {
            return false;
        }
        return this.mPrevWordsInfo[n - 1].mIsBeginningOfSentence;
    }

    public void outputToArray(final int[][] codePointArrays, final boolean[] isBeginningOfSentenceArray) {
        for (int i = 0; i < this.mPrevWordsCount; i++) {
            WordInfo wordInfo = this.mPrevWordsInfo[i];
            if (wordInfo == null || !wordInfo.isValid()) {
                codePointArrays[i] = new int[0];
                isBeginningOfSentenceArray[i] = false;
            } else {
                codePointArrays[i] = StringUtils.toCodePointArray(wordInfo.mWord);
                isBeginningOfSentenceArray[i] = wordInfo.mIsBeginningOfSentence;
            }
        }
    }

    public int getPrevWordCount() {
        return this.mPrevWordsCount;
    }

    public int hashCode() {
        int iHashCode = 0;
        for (WordInfo wordInfo : this.mPrevWordsInfo) {
            if (wordInfo == null || !WordInfo.EMPTY_WORD_INFO.equals(wordInfo)) {
                break;
            }
            iHashCode ^= wordInfo.hashCode();
        }
        return iHashCode;
    }

    public boolean equals(Object o) {
        WordInfo[] wordInfoArr;
        if (this == o) {
            return true;
        }
        if (!(o instanceof NgramContext)) {
            return false;
        }
        NgramContext ngramContext = (NgramContext) o;
        int iMin = Math.min(this.mPrevWordsCount, ngramContext.mPrevWordsCount);
        for (int i = 0; i < iMin; i++) {
            if (!this.mPrevWordsInfo[i].equals(ngramContext.mPrevWordsInfo[i])) {
                return false;
            }
        }
        int i2 = this.mPrevWordsCount;
        int i3 = ngramContext.mPrevWordsCount;
        if (i2 > i3) {
            wordInfoArr = this.mPrevWordsInfo;
        } else {
            wordInfoArr = ngramContext.mPrevWordsInfo;
            i2 = i3;
        }
        while (iMin < i2) {
            if (wordInfoArr[iMin] != null && !WordInfo.EMPTY_WORD_INFO.equals(wordInfoArr[iMin])) {
                return false;
            }
            iMin++;
        }
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.mPrevWordsCount; i++) {
            WordInfo wordInfo = this.mPrevWordsInfo[i];
            stringBuffer.append("PrevWord[");
            stringBuffer.append(i);
            stringBuffer.append("]: ");
            if (wordInfo == null) {
                stringBuffer.append("null. ");
            } else if (!wordInfo.isValid()) {
                stringBuffer.append("Empty. ");
            } else {
                stringBuffer.append(wordInfo.mWord);
                stringBuffer.append(", isBeginningOfSentence: ");
                stringBuffer.append(wordInfo.mIsBeginningOfSentence);
                stringBuffer.append(". ");
            }
        }
        return stringBuffer.toString();
    }
}

package com.android.inputmethod.latin;

import android.text.TextUtils;
import android.view.inputmethod.CompletionInfo;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SuggestedWords {

    @Nonnull
    private static final SuggestedWords EMPTY;
    private static final ArrayList<SuggestedWordInfo> EMPTY_WORD_INFO_LIST;
    public static final int INDEX_OF_AUTO_CORRECTION = 1;
    public static final int INDEX_OF_TYPED_WORD = 0;
    public static final int INPUT_STYLE_APPLICATION_SPECIFIED = 4;
    public static final int INPUT_STYLE_BEGINNING_OF_SENTENCE_PREDICTION = 7;
    public static final int INPUT_STYLE_NONE = 0;
    public static final int INPUT_STYLE_PREDICTION = 6;
    public static final int INPUT_STYLE_RECORRECTION = 5;
    public static final int INPUT_STYLE_TAIL_BATCH = 3;
    public static final int INPUT_STYLE_TYPING = 1;
    public static final int INPUT_STYLE_UPDATE_BATCH = 2;
    public static final int MAX_SUGGESTIONS = 18;
    public static final int NOT_A_SEQUENCE_NUMBER = -1;
    public final int mInputStyle;
    public final boolean mIsObsoleteSuggestions;

    @Nullable
    public final ArrayList<SuggestedWordInfo> mRawSuggestions;
    public final int mSequenceNumber;

    @Nonnull
    protected final ArrayList<SuggestedWordInfo> mSuggestedWordInfoList;

    @Nullable
    public final SuggestedWordInfo mTypedWordInfo;
    public final boolean mTypedWordValid;
    public final boolean mWillAutoCorrect;

    private static boolean isPrediction(final int inputStyle) {
        return 6 == inputStyle || 7 == inputStyle;
    }

    public String getDebugString(final int pos) {
        return null;
    }

    public boolean isPunctuationSuggestions() {
        return false;
    }

    static {
        ArrayList<SuggestedWordInfo> arrayList = new ArrayList<>(0);
        EMPTY_WORD_INFO_LIST = arrayList;
        EMPTY = new SuggestedWords(arrayList, null, null, false, false, false, 0, -1);
    }

    public SuggestedWords(@Nonnull final ArrayList<SuggestedWordInfo> suggestedWordInfoList, @Nullable final ArrayList<SuggestedWordInfo> rawSuggestions, @Nullable final SuggestedWordInfo typedWordInfo, final boolean typedWordValid, final boolean willAutoCorrect, final boolean isObsoleteSuggestions, final int inputStyle, final int sequenceNumber) {
        this.mSuggestedWordInfoList = suggestedWordInfoList;
        this.mRawSuggestions = rawSuggestions;
        this.mTypedWordValid = typedWordValid;
        this.mWillAutoCorrect = willAutoCorrect;
        this.mIsObsoleteSuggestions = isObsoleteSuggestions;
        this.mInputStyle = inputStyle;
        this.mSequenceNumber = sequenceNumber;
        this.mTypedWordInfo = typedWordInfo;
    }

    public boolean isEmpty() {
        return this.mSuggestedWordInfoList.isEmpty();
    }

    public int size() {
        return this.mSuggestedWordInfoList.size();
    }

    public int getWordCountToShow(final boolean shouldShowLxxSuggestionUi) {
        if (isPrediction() || !shouldShowLxxSuggestionUi) {
            return size();
        }
        return size() - 1;
    }

    public SuggestedWordInfo getTypedWordInfo() {
        return this.mTypedWordInfo;
    }

    public String getWord(final int index) {
        return this.mSuggestedWordInfoList.get(index).mWord;
    }

    public String getLabel(final int index) {
        return this.mSuggestedWordInfoList.get(index).mWord;
    }

    public SuggestedWordInfo getInfo(final int index) {
        return this.mSuggestedWordInfoList.get(index);
    }

    public int indexOf(SuggestedWordInfo suggestedWordInfo) {
        return this.mSuggestedWordInfoList.indexOf(suggestedWordInfo);
    }

    public String toString() {
        return "SuggestedWords: mTypedWordValid=" + this.mTypedWordValid + " mWillAutoCorrect=" + this.mWillAutoCorrect + " mInputStyle=" + this.mInputStyle + " words=" + Arrays.toString(this.mSuggestedWordInfoList.toArray());
    }

    public static ArrayList<SuggestedWordInfo> getFromApplicationSpecifiedCompletions(final CompletionInfo[] infos) {
        ArrayList<SuggestedWordInfo> arrayList = new ArrayList<>();
        for (CompletionInfo completionInfo : infos) {
            if (completionInfo != null && completionInfo.getText() != null) {
                arrayList.add(new SuggestedWordInfo(completionInfo));
            }
        }
        return arrayList;
    }

    @Nonnull
    public static final SuggestedWords getEmptyInstance() {
        return EMPTY;
    }

    public static ArrayList<SuggestedWordInfo> getTypedWordAndPreviousSuggestions(@Nonnull final SuggestedWordInfo typedWordInfo, @Nonnull final SuggestedWords previousSuggestions) {
        ArrayList<SuggestedWordInfo> arrayList = new ArrayList<>();
        HashSet hashSet = new HashSet();
        arrayList.add(typedWordInfo);
        hashSet.add(typedWordInfo.mWord);
        int size = previousSuggestions.size();
        for (int i = 1; i < size; i++) {
            SuggestedWordInfo info = previousSuggestions.getInfo(i);
            String str = info.mWord;
            if (!hashSet.contains(str)) {
                arrayList.add(info);
                hashSet.add(str);
            }
        }
        return arrayList;
    }

    public SuggestedWordInfo getAutoCommitCandidate() {
        if (this.mSuggestedWordInfoList.size() <= 0) {
            return null;
        }
        SuggestedWordInfo suggestedWordInfo = this.mSuggestedWordInfoList.get(0);
        if (suggestedWordInfo.isEligibleForAutoCommit()) {
            return suggestedWordInfo;
        }
        return null;
    }

    public static class SuggestedWordInfo {
        public static final int KIND_APP_DEFINED = 6;
        public static final int KIND_BLACKLIST = 4;
        public static final int KIND_COMPLETION = 2;
        public static final int KIND_CORRECTION = 1;
        public static final int KIND_FLAG_APPROPRIATE_FOR_AUTO_CORRECTION = 268435456;
        public static final int KIND_FLAG_EXACT_MATCH = 1073741824;
        public static final int KIND_FLAG_EXACT_MATCH_WITH_INTENTIONAL_OMISSION = 536870912;
        public static final int KIND_FLAG_POSSIBLY_OFFENSIVE = Integer.MIN_VALUE;
        public static final int KIND_HARDCODED = 5;
        private static final int KIND_MASK_KIND = 255;
        public static final int KIND_OOV_CORRECTION = 10;
        public static final int KIND_PREDICTION = 8;
        public static final int KIND_RESUMED = 9;
        public static final int KIND_SHORTCUT = 7;
        public static final int KIND_TYPED = 0;
        public static final int KIND_WHITELIST = 3;
        public static final int MAX_SCORE = Integer.MAX_VALUE;
        public static final int NOT_AN_INDEX = -1;
        public static final int NOT_A_CONFIDENCE = -1;
        public final CompletionInfo mApplicationSpecifiedCompletionInfo;
        public final int mAutoCommitFirstWordConfidence;
        public final int mCodePointCount;
        private String mDebugString;
        public final int mIndexOfTouchPointOfSecondWord;
        public final int mKindAndFlags;
        public final String mPrevWordsContext;
        public final int mScore;

        @Deprecated
        public final Dictionary mSourceDict;
        public final String mWord;

        public SuggestedWordInfo(final String word, final String prevWordsContext, final int score, final int kindAndFlags, final Dictionary sourceDict, final int indexOfTouchPointOfSecondWord, final int autoCommitFirstWordConfidence) {
            this.mDebugString = "";
            this.mWord = word;
            this.mPrevWordsContext = prevWordsContext;
            this.mApplicationSpecifiedCompletionInfo = null;
            this.mScore = score;
            this.mKindAndFlags = kindAndFlags;
            this.mSourceDict = sourceDict;
            this.mCodePointCount = StringUtils.codePointCount(word);
            this.mIndexOfTouchPointOfSecondWord = indexOfTouchPointOfSecondWord;
            this.mAutoCommitFirstWordConfidence = autoCommitFirstWordConfidence;
        }

        public SuggestedWordInfo(final CompletionInfo applicationSpecifiedCompletion) {
            this.mDebugString = "";
            String string = applicationSpecifiedCompletion.getText().toString();
            this.mWord = string;
            this.mPrevWordsContext = "";
            this.mApplicationSpecifiedCompletionInfo = applicationSpecifiedCompletion;
            this.mScore = Integer.MAX_VALUE;
            this.mKindAndFlags = 6;
            this.mSourceDict = Dictionary.DICTIONARY_APPLICATION_DEFINED;
            this.mCodePointCount = StringUtils.codePointCount(string);
            this.mIndexOfTouchPointOfSecondWord = -1;
            this.mAutoCommitFirstWordConfidence = -1;
        }

        public boolean isEligibleForAutoCommit() {
            return isKindOf(1) && -1 != this.mIndexOfTouchPointOfSecondWord;
        }

        public int getKind() {
            return this.mKindAndFlags & 255;
        }

        public boolean isKindOf(final int kind) {
            return getKind() == kind;
        }

        public boolean isPossiblyOffensive() {
            return (this.mKindAndFlags & Integer.MIN_VALUE) != 0;
        }

        public boolean isExactMatch() {
            return (this.mKindAndFlags & 1073741824) != 0;
        }

        public boolean isExactMatchWithIntentionalOmission() {
            return (this.mKindAndFlags & KIND_FLAG_EXACT_MATCH_WITH_INTENTIONAL_OMISSION) != 0;
        }

        public boolean isAprapreateForAutoCorrection() {
            return (this.mKindAndFlags & KIND_FLAG_APPROPRIATE_FOR_AUTO_CORRECTION) != 0;
        }

        public void setDebugString(final String str) {
            Objects.requireNonNull(str, "Debug info is null");
            this.mDebugString = str;
        }

        public String getDebugString() {
            return this.mDebugString;
        }

        public String getWord() {
            return this.mWord;
        }

        @Deprecated
        public Dictionary getSourceDictionary() {
            return this.mSourceDict;
        }

        public int codePointAt(int i) {
            return this.mWord.codePointAt(i);
        }

        public String toString() {
            if (TextUtils.isEmpty(this.mDebugString)) {
                return this.mWord;
            }
            return this.mWord + " (" + this.mDebugString + ")";
        }

        public static int removeDups(@Nullable final String typedWord, @Nonnull final ArrayList<SuggestedWordInfo> candidates) {
            if (candidates.isEmpty()) {
                return -1;
            }
            int iRemoveSuggestedWordInfoFromList = TextUtils.isEmpty(typedWord) ? -1 : removeSuggestedWordInfoFromList(typedWord, candidates, -1);
            for (int i = 0; i < candidates.size(); i++) {
                removeSuggestedWordInfoFromList(candidates.get(i).mWord, candidates, i);
            }
            return iRemoveSuggestedWordInfoFromList;
        }

        private static int removeSuggestedWordInfoFromList(@Nonnull final String word, @Nonnull final ArrayList<SuggestedWordInfo> candidates, final int startIndexExclusive) {
            int i = startIndexExclusive + 1;
            int i2 = -1;
            while (i < candidates.size()) {
                if (word.equals(candidates.get(i).mWord)) {
                    if (i2 == -1) {
                        i2 = i;
                    }
                    candidates.remove(i);
                    i--;
                }
                i++;
            }
            return i2;
        }
    }

    public boolean isPrediction() {
        return isPrediction(this.mInputStyle);
    }

    public SuggestedWordInfo getTypedWordInfoOrNull() {
        if (size() <= 0) {
            return null;
        }
        SuggestedWordInfo info = getInfo(0);
        if (info.getKind() == 0) {
            return info;
        }
        return null;
    }
}

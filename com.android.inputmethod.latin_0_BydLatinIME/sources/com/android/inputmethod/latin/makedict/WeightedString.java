package com.android.inputmethod.latin.makedict;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class WeightedString {
    public ProbabilityInfo mProbabilityInfo;
    public final String mWord;

    public WeightedString(final String word, final int probability) {
        this(word, new ProbabilityInfo(probability));
    }

    public WeightedString(final String word, final ProbabilityInfo probabilityInfo) {
        this.mWord = word;
        this.mProbabilityInfo = probabilityInfo;
    }

    public int getProbability() {
        return this.mProbabilityInfo.mProbability;
    }

    public void setProbability(final int probability) {
        this.mProbabilityInfo = new ProbabilityInfo(probability);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mWord, this.mProbabilityInfo});
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WeightedString)) {
            return false;
        }
        WeightedString weightedString = (WeightedString) o;
        return this.mWord.equals(weightedString.mWord) && this.mProbabilityInfo.equals(weightedString.mProbabilityInfo);
    }
}

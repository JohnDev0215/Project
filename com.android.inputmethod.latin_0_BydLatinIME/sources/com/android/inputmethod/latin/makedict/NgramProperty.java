package com.android.inputmethod.latin.makedict;

import com.android.inputmethod.latin.NgramContext;

/* JADX INFO: loaded from: classes2.dex */
public class NgramProperty {
    public final NgramContext mNgramContext;
    public final WeightedString mTargetWord;

    public NgramProperty(final WeightedString targetWord, final NgramContext ngramContext) {
        this.mTargetWord = targetWord;
        this.mNgramContext = ngramContext;
    }

    public int hashCode() {
        return this.mTargetWord.hashCode() ^ this.mNgramContext.hashCode();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NgramProperty)) {
            return false;
        }
        NgramProperty ngramProperty = (NgramProperty) o;
        return this.mTargetWord.equals(ngramProperty.mTargetWord) && this.mNgramContext.equals(ngramProperty.mNgramContext);
    }
}

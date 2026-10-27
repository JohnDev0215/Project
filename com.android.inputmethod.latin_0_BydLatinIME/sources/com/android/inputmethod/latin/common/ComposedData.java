package com.android.inputmethod.latin.common;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ComposedData {

    @Nonnull
    public final InputPointers mInputPointers;
    public final boolean mIsBatchMode;

    @Nonnull
    public final String mTypedWord;

    public ComposedData(@Nonnull final InputPointers inputPointers, final boolean isBatchMode, @Nonnull final String typedWord) {
        this.mInputPointers = inputPointers;
        this.mIsBatchMode = isBatchMode;
        this.mTypedWord = typedWord;
    }

    public int copyCodePointsExceptTrailingSingleQuotesAndReturnCodePointCount(@Nonnull final int[] destination) {
        int length = this.mTypedWord.length() - StringUtils.getTrailingSingleQuotesCount(this.mTypedWord);
        if (length <= 0) {
            return 0;
        }
        if (Character.codePointCount(this.mTypedWord, 0, length) > destination.length) {
            return -1;
        }
        return StringUtils.copyCodePointsAndReturnCodePointCount(destination, this.mTypedWord, 0, length, true);
    }
}

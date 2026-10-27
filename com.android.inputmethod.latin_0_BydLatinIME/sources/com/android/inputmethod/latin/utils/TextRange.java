package com.android.inputmethod.latin.utils;

import android.text.Spanned;
import android.text.style.SuggestionSpan;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class TextRange {
    private final int mCursorIndex;
    public final boolean mHasUrlSpans;
    private final CharSequence mTextAtCursor;
    public final CharSequence mWord;
    private final int mWordAtCursorEndIndex;
    private final int mWordAtCursorStartIndex;

    public int getNumberOfCharsInWordBeforeCursor() {
        return this.mCursorIndex - this.mWordAtCursorStartIndex;
    }

    public int getNumberOfCharsInWordAfterCursor() {
        return this.mWordAtCursorEndIndex - this.mCursorIndex;
    }

    public int length() {
        return this.mWord.length();
    }

    public SuggestionSpan[] getSuggestionSpansAtWord() {
        CharSequence charSequence = this.mTextAtCursor;
        if (!(charSequence instanceof Spanned) || !(this.mWord instanceof Spanned)) {
            return new SuggestionSpan[0];
        }
        Spanned spanned = (Spanned) charSequence;
        SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) spanned.getSpans(this.mWordAtCursorStartIndex - 1, this.mWordAtCursorEndIndex + 1, SuggestionSpan.class);
        int i = 0;
        int i2 = 0;
        while (i < suggestionSpanArr.length) {
            SuggestionSpan suggestionSpan = suggestionSpanArr[i];
            if (suggestionSpan != null) {
                int spanStart = spanned.getSpanStart(suggestionSpan);
                int spanEnd = spanned.getSpanEnd(suggestionSpan);
                for (int i3 = i + 1; i3 < suggestionSpanArr.length; i3++) {
                    if (suggestionSpan.equals(suggestionSpanArr[i3])) {
                        spanStart = Math.min(spanStart, spanned.getSpanStart(suggestionSpanArr[i3]));
                        spanEnd = Math.max(spanEnd, spanned.getSpanEnd(suggestionSpanArr[i3]));
                        suggestionSpanArr[i3] = null;
                    }
                }
                if (spanStart == this.mWordAtCursorStartIndex && spanEnd == this.mWordAtCursorEndIndex) {
                    suggestionSpanArr[i2] = suggestionSpanArr[i];
                    i2++;
                }
            }
            i++;
        }
        return i2 == i ? suggestionSpanArr : (SuggestionSpan[]) Arrays.copyOfRange(suggestionSpanArr, 0, i2);
    }

    public TextRange(final CharSequence textAtCursor, final int wordAtCursorStartIndex, final int wordAtCursorEndIndex, final int cursorIndex, final boolean hasUrlSpans) {
        if (wordAtCursorStartIndex < 0 || cursorIndex < wordAtCursorStartIndex || cursorIndex > wordAtCursorEndIndex || wordAtCursorEndIndex > textAtCursor.length()) {
            throw new IndexOutOfBoundsException();
        }
        this.mTextAtCursor = textAtCursor;
        this.mWordAtCursorStartIndex = wordAtCursorStartIndex;
        this.mWordAtCursorEndIndex = wordAtCursorEndIndex;
        this.mCursorIndex = cursorIndex;
        this.mHasUrlSpans = hasUrlSpans;
        this.mWord = textAtCursor.subSequence(wordAtCursorStartIndex, wordAtCursorEndIndex);
    }
}

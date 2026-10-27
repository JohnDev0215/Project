package org.chromium.content.browser.input;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class TextInputState {
    private final Range mComposition;
    private final boolean mFromIme;
    private final Range mSelection;
    private final boolean mSingleLine;
    private final CharSequence mText;

    public boolean shouldUnblock() {
        return false;
    }

    public TextInputState(CharSequence charSequence, Range range, Range range2, boolean z, boolean z2) {
        range.clamp(0, charSequence.length());
        if (range2.start() != -1 || range2.end() != -1) {
            range2.clamp(0, charSequence.length());
        }
        this.mText = charSequence;
        this.mSelection = range;
        this.mComposition = range2;
        this.mSingleLine = z;
        this.mFromIme = z2;
    }

    public CharSequence text() {
        return this.mText;
    }

    public Range selection() {
        return this.mSelection;
    }

    public Range composition() {
        return this.mComposition;
    }

    public boolean singleLine() {
        return this.mSingleLine;
    }

    public boolean fromIme() {
        return this.mFromIme;
    }

    public CharSequence getSelectedText() {
        if (this.mSelection.start() == this.mSelection.end()) {
            return null;
        }
        return TextUtils.substring(this.mText, this.mSelection.start(), this.mSelection.end());
    }

    public CharSequence getTextAfterSelection(int i) {
        return TextUtils.substring(this.mText, this.mSelection.end(), Math.min(this.mText.length(), this.mSelection.end() + i));
    }

    public CharSequence getTextBeforeSelection(int i) {
        return TextUtils.substring(this.mText, Math.max(0, this.mSelection.start() - i), this.mSelection.start());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TextInputState)) {
            return false;
        }
        TextInputState textInputState = (TextInputState) obj;
        if (textInputState == this) {
            return true;
        }
        return TextUtils.equals(this.mText, textInputState.mText) && this.mSelection.equals(textInputState.mSelection) && this.mComposition.equals(textInputState.mComposition) && this.mSingleLine == textInputState.mSingleLine && this.mFromIme == textInputState.mFromIme;
    }

    public int hashCode() {
        return (this.mText.hashCode() * 7) + (this.mSelection.hashCode() * 11) + (this.mComposition.hashCode() * 13) + (this.mSingleLine ? 19 : 0) + (this.mFromIme ? 23 : 0);
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[5];
        objArr[0] = this.mText;
        objArr[1] = this.mSelection;
        objArr[2] = this.mComposition;
        objArr[3] = this.mSingleLine ? "SIN" : "MUL";
        objArr[4] = this.mFromIme ? "fromIME" : "NOTfromIME";
        return String.format(locale, "TextInputState {[%s] SEL%s COM%s %s %s}", objArr);
    }
}

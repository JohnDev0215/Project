package com.android.car.developeroptions.bluetooth;

import android.text.InputFilter;
import android.text.Spanned;
import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
public class Utf8ByteLengthFilter implements InputFilter {
    private final int mMaxBytes;

    @Keep
    Utf8ByteLengthFilter(int i) {
        this.mMaxBytes = i;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        int i5 = i;
        int i6 = 0;
        while (true) {
            int i7 = 2;
            if (i5 >= i2) {
                break;
            }
            char cCharAt = charSequence.charAt(i5);
            if (cCharAt < 128) {
                i7 = 1;
            } else if (cCharAt >= 2048) {
                i7 = 3;
            }
            i6 += i7;
            i5++;
        }
        int length = spanned.length();
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 < i3 || i9 >= i4) {
                char cCharAt2 = spanned.charAt(i9);
                i8 += cCharAt2 < 128 ? 1 : cCharAt2 < 2048 ? 2 : 3;
            }
        }
        int i10 = this.mMaxBytes - i8;
        if (i10 <= 0) {
            return "";
        }
        if (i10 >= i6) {
            return null;
        }
        for (int i11 = i; i11 < i2; i11++) {
            char cCharAt3 = charSequence.charAt(i11);
            i10 -= cCharAt3 < 128 ? 1 : cCharAt3 < 2048 ? 2 : 3;
            if (i10 < 0) {
                return charSequence.subSequence(i, i11);
            }
        }
        return null;
    }
}

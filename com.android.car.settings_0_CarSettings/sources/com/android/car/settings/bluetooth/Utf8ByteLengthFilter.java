package com.android.car.settings.bluetooth;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: classes5.dex */
class Utf8ByteLengthFilter implements InputFilter {
    private final int mMaxBytes;

    Utf8ByteLengthFilter(int maxBytes) {
        this.mMaxBytes = maxBytes;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int srcByteCount = 0;
        for (int i = start; i < end; i++) {
            char c = source.charAt(i);
            srcByteCount += c < 128 ? 1 : c < 2048 ? 2 : 3;
        }
        int destLen = dest.length();
        int destByteCount = 0;
        for (int i2 = 0; i2 < destLen; i2++) {
            if (i2 < dstart || i2 >= dend) {
                char c2 = dest.charAt(i2);
                destByteCount += c2 < 128 ? 1 : c2 < 2048 ? 2 : 3;
            }
        }
        int keepBytes = this.mMaxBytes - destByteCount;
        if (keepBytes <= 0) {
            return "";
        }
        if (keepBytes >= srcByteCount) {
            return null;
        }
        for (int i3 = start; i3 < end; i3++) {
            char c3 = source.charAt(i3);
            keepBytes -= c3 < 128 ? 1 : c3 < 2048 ? 2 : 3;
            if (keepBytes < 0) {
                return source.subSequence(start, i3);
            }
        }
        return null;
    }
}

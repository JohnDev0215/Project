package com.amazon.ion.impl;

import androidx.core.internal.view.SupportMenu;
import com.amazon.ion.IonException;
import com.fasterxml.jackson.core.base.GeneratorBase;

/* JADX INFO: loaded from: classes3.dex */
class IonUTF8 {
    public static final int fourByteScalar(int i, int i2, int i3, int i4) {
        return ((i & 7) << 18) | ((i2 & 63) << 12) | ((i3 & 63) << 6) | (i4 & 63);
    }

    public static final char highSurrogate(int i) {
        return (char) ((((i - 65536) >> 10) | GeneratorBase.SURR1_FIRST) & SupportMenu.USER_MASK);
    }

    public static final boolean isFourByteUTF8(int i) {
        return (i & (-8)) == 240;
    }

    public static final boolean isHighSurrogate(int i) {
        return (i & (-1024)) == 55296;
    }

    public static final boolean isLowSurrogate(int i) {
        return (i & (-1024)) == 56320;
    }

    public static final boolean isOneByteUTF8(int i) {
        return (i & 128) == 0;
    }

    public static final boolean isThreeByteUTF8(int i) {
        return (i & (-16)) == 224;
    }

    public static final boolean isTwoByteUTF8(int i) {
        return (i & (-32)) == 192;
    }

    public static final char lowSurrogate(int i) {
        return (char) ((((i - 65536) & 1023) | GeneratorBase.SURR2_FIRST) & SupportMenu.USER_MASK);
    }

    public static final int threeByteScalar(int i, int i2, int i3) {
        return ((i & 15) << 12) | ((i2 & 63) << 6) | (i3 & 63);
    }

    public static final char twoByteScalar(int i, int i2) {
        return (char) (((i & 31) << 6) | (i2 & 63));
    }

    IonUTF8() {
    }

    public static final int getUTF8LengthFromFirstByte(int i) {
        int i2 = i & 255;
        if (isOneByteUTF8(i2)) {
            return 1;
        }
        if (isTwoByteUTF8(i2)) {
            return 2;
        }
        if (isThreeByteUTF8(i2)) {
            return 3;
        }
        return isFourByteUTF8(i2) ? 4 : -1;
    }

    public static final boolean needsSurrogateEncoding(int i) {
        if (i <= 1114111) {
            return i > 65535;
        }
        throw new IonException("Invalid encoding: encountered non-Unicode character.");
    }
}

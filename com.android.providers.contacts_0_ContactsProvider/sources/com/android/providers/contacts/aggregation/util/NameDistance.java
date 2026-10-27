package com.android.providers.contacts.aggregation.util;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class NameDistance {
    private final boolean[] mMatchFlags1;
    private final boolean[] mMatchFlags2;
    private final int mMaxLength;
    private final boolean mPrefixOnly;

    public NameDistance(int i) {
        this.mMaxLength = i;
        this.mPrefixOnly = false;
        this.mMatchFlags1 = new boolean[i];
        this.mMatchFlags2 = new boolean[i];
    }

    public NameDistance() {
        this.mPrefixOnly = true;
        this.mMaxLength = 0;
        this.mMatchFlags2 = null;
        this.mMatchFlags1 = null;
    }

    public float getDistance(byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        byte[] bArr4;
        boolean z;
        if (bArr.length > bArr2.length) {
            bArr4 = bArr;
            bArr3 = bArr2;
        } else {
            bArr3 = bArr;
            bArr4 = bArr2;
        }
        int length = bArr3.length;
        int i = 0;
        if (length >= 3) {
            int i2 = 0;
            while (true) {
                if (i2 >= bArr3.length) {
                    z = true;
                    break;
                }
                if (bArr3[i2] != bArr4[i2]) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (z) {
                return 1.0f;
            }
        }
        if (this.mPrefixOnly) {
            return 0.0f;
        }
        int i3 = this.mMaxLength;
        if (length > i3) {
            length = i3;
        }
        int length2 = bArr4.length;
        int i4 = this.mMaxLength;
        if (length2 > i4) {
            length2 = i4;
        }
        Arrays.fill(this.mMatchFlags1, 0, length, false);
        Arrays.fill(this.mMatchFlags2, 0, length2, false);
        int i5 = (length2 / 2) - 1;
        if (i5 < 0) {
            i5 = 0;
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < length) {
            byte b = bArr3[i6];
            int i8 = i6 - i5;
            if (i8 < 0) {
                i8 = i;
            }
            int i9 = i6 + i5 + 1;
            if (i9 > length2) {
                i9 = length2;
            }
            while (true) {
                if (i8 < i9) {
                    boolean[] zArr = this.mMatchFlags2;
                    if (!zArr[i8] && b == bArr4[i8]) {
                        boolean[] zArr2 = this.mMatchFlags1;
                        zArr[i8] = true;
                        zArr2[i6] = true;
                        i7++;
                        break;
                    }
                    i8++;
                }
            }
            i6++;
            i = 0;
        }
        if (i7 == 0) {
            return 0.0f;
        }
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < length; i12++) {
            if (this.mMatchFlags1[i12]) {
                while (!this.mMatchFlags2[i11]) {
                    i11++;
                }
                if (bArr3[i12] != bArr4[i11]) {
                    i10++;
                }
                i11++;
            }
        }
        float f = i7;
        float f2 = length2;
        float f3 = (((f / length) + (f / f2)) + ((f - (i10 / 2.0f)) / f)) / 3.0f;
        if (f3 < 0.7f) {
            return f3;
        }
        int i13 = 0;
        for (int i14 = 0; i14 < length && bArr[i14] == bArr2[i14]; i14++) {
            i13++;
        }
        return f3 + (Math.min(0.1f, 1.0f / f2) * i13 * (1.0f - f3));
    }
}

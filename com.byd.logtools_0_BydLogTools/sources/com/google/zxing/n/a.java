package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: CodaBarReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final char[] f4315a = "0123456789-$:/.+ABCD".toCharArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f4316b = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final char[] f4317c = {'A', 'B', 'C', 'D'};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final StringBuilder f4318d = new StringBuilder(20);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f4319e = new int[80];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4320f = 0;

    static boolean g(char[] cArr, char c2) {
        if (cArr != null) {
            for (char c3 : cArr) {
                if (c3 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void h(int i) {
        int[] iArr = this.f4319e;
        int i2 = this.f4320f;
        iArr[i2] = i;
        int i3 = i2 + 1;
        this.f4320f = i3;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[i3 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.f4319e = iArr2;
        }
    }

    private int i() throws NotFoundException {
        for (int i = 1; i < this.f4320f; i += 2) {
            int iK = k(i);
            if (iK != -1 && g(f4317c, f4315a[iK])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.f4319e[i3];
                }
                if (i == 1 || this.f4319e[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void j(com.google.zxing.common.a aVar) throws NotFoundException {
        int i = 0;
        this.f4320f = 0;
        int iK = aVar.k(0);
        int iL = aVar.l();
        if (iK >= iL) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = true;
        while (iK < iL) {
            if (aVar.h(iK) != z) {
                i++;
            } else {
                h(i);
                z = !z;
                i = 1;
            }
            iK++;
        }
        h(i);
    }

    private int k(int i) {
        int i2 = i + 7;
        if (i2 >= this.f4320f) {
            return -1;
        }
        int[] iArr = this.f4319e;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        for (int i7 = i; i7 < i2; i7 += 2) {
            int i8 = iArr[i7];
            if (i8 < i5) {
                i5 = i8;
            }
            if (i8 > i6) {
                i6 = i8;
            }
        }
        int i9 = (i5 + i6) / 2;
        int i10 = 0;
        for (int i11 = i + 1; i11 < i2; i11 += 2) {
            int i12 = iArr[i11];
            if (i12 < i3) {
                i3 = i12;
            }
            if (i12 > i10) {
                i10 = i12;
            }
        }
        int i13 = (i3 + i10) / 2;
        int i14 = 128;
        int i15 = 0;
        for (int i16 = 0; i16 < 7; i16++) {
            i14 >>= 1;
            if (iArr[i + i16] > ((i16 & 1) == 0 ? i9 : i13)) {
                i15 |= i14;
            }
        }
        while (true) {
            int[] iArr2 = f4316b;
            if (i4 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i4] == i15) {
                return i4;
            }
            i4++;
        }
    }

    private void l(int i) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.f4318d.length() - 1;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = f4316b[this.f4318d.charAt(i4)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) << 1);
                iArr[i7] = iArr[i7] + this.f4319e[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i8 = 0; i8 < 2; i8++) {
            fArr2[i8] = 0.0f;
            int i9 = i8 + 2;
            fArr2[i9] = ((iArr[i8] / iArr2[i8]) + (iArr[i9] / iArr2[i9])) / 2.0f;
            fArr[i8] = fArr2[i9];
            fArr[i9] = ((iArr[i9] * 2.0f) + 1.5f) / iArr2[i9];
        }
        loop3: while (true) {
            int i10 = f4316b[this.f4318d.charAt(i2)];
            for (int i11 = 6; i11 >= 0; i11--) {
                int i12 = (i11 & 1) + ((i10 & 1) << 1);
                float f2 = this.f4319e[i + i11];
                if (f2 < fArr2[i12] || f2 > fArr[i12]) {
                    break loop3;
                }
                i10 >>= 1;
            }
            if (i2 >= length) {
                return;
            }
            i += 8;
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.n.k
    public com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.f4319e, 0);
        j(aVar);
        int i2 = i();
        this.f4318d.setLength(0);
        int i3 = i2;
        do {
            int iK = k(i3);
            if (iK == -1) {
                throw NotFoundException.getNotFoundInstance();
            }
            this.f4318d.append((char) iK);
            i3 += 8;
            if (this.f4318d.length() > 1 && g(f4317c, f4315a[iK])) {
                break;
            }
        } while (i3 < this.f4320f);
        int i4 = i3 - 1;
        int i5 = this.f4319e[i4];
        int i6 = 0;
        for (int i7 = -8; i7 < -1; i7++) {
            i6 += this.f4319e[i3 + i7];
        }
        if (i3 < this.f4320f && i5 < i6 / 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        l(i2);
        for (int i8 = 0; i8 < this.f4318d.length(); i8++) {
            StringBuilder sb = this.f4318d;
            sb.setCharAt(i8, f4315a[sb.charAt(i8)]);
        }
        char cCharAt = this.f4318d.charAt(0);
        char[] cArr = f4317c;
        if (!g(cArr, cCharAt)) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb2 = this.f4318d;
        if (!g(cArr, sb2.charAt(sb2.length() - 1))) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (this.f4318d.length() <= 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
            StringBuilder sb3 = this.f4318d;
            sb3.deleteCharAt(sb3.length() - 1);
            this.f4318d.deleteCharAt(0);
        }
        int i9 = 0;
        for (int i10 = 0; i10 < i2; i10++) {
            i9 += this.f4319e[i10];
        }
        float f2 = i9;
        while (i2 < i4) {
            i9 += this.f4319e[i2];
            i2++;
        }
        float f3 = i;
        return new com.google.zxing.h(this.f4318d.toString(), null, new com.google.zxing.i[]{new com.google.zxing.i(f2, f3), new com.google.zxing.i(i9, f3)}, BarcodeFormat.CODABAR);
    }
}

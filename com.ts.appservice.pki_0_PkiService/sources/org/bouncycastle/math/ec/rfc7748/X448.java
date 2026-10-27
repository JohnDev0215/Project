package org.bouncycastle.math.ec.rfc7748;

import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class X448 {
    private static final int C_A = 156326;
    private static final int C_A24 = 39082;
    private static final int[] S_x = {268435454, 268435455, 268435455, 268435455, 268435455, 268435455, 268435455, 268435455, 268435454, 268435455, 268435455, 268435455, 268435455, 268435455, 268435455, 268435455};
    private static final int[] PsubS_x = {161294112, 185702364, 163248300, 54522310, 189866924, 105098465, 66174309, 139206530, 156517789, 136025714, 231801628, 246922668, 59251455, 69446896, 83964484, 252685170};
    private static int[] precompBase = null;

    private static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & 255) << 16);
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < 14; i2++) {
            iArr[i2] = decode32(bArr, (i2 * 4) + i);
        }
        iArr[0] = iArr[0] & (-4);
        iArr[13] = iArr[13] | PKIFailureInfo.systemUnavail;
    }

    private static void pointDouble(int[] iArr, int[] iArr2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.add(iArr, iArr2, iArrCreate);
        X448Field.sub(iArr, iArr2, iArrCreate2);
        X448Field.sqr(iArrCreate, iArrCreate);
        X448Field.sqr(iArrCreate2, iArrCreate2);
        X448Field.mul(iArrCreate, iArrCreate2, iArr);
        X448Field.sub(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.mul(iArrCreate, C_A24, iArr2);
        X448Field.add(iArr2, iArrCreate2, iArr2);
        X448Field.mul(iArr2, iArrCreate, iArr2);
    }

    public static synchronized void precompute() {
        if (precompBase != null) {
            return;
        }
        int[] iArr = new int[7136];
        precompBase = iArr;
        int[] iArr2 = new int[7120];
        int[] iArrCreate = X448Field.create();
        iArrCreate[0] = 5;
        int[] iArrCreate2 = X448Field.create();
        iArrCreate2[0] = 1;
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        X448Field.add(iArrCreate, iArrCreate2, iArrCreate3);
        X448Field.sub(iArrCreate, iArrCreate2, iArrCreate4);
        int[] iArrCreate5 = X448Field.create();
        X448Field.copy(iArrCreate4, 0, iArrCreate5, 0);
        int i = 0;
        while (true) {
            X448Field.copy(iArrCreate3, 0, iArr, i);
            if (i == 7120) {
                break;
            }
            pointDouble(iArrCreate, iArrCreate2);
            X448Field.add(iArrCreate, iArrCreate2, iArrCreate3);
            X448Field.sub(iArrCreate, iArrCreate2, iArrCreate4);
            X448Field.mul(iArrCreate3, iArrCreate5, iArrCreate3);
            X448Field.mul(iArrCreate5, iArrCreate4, iArrCreate5);
            X448Field.copy(iArrCreate4, 0, iArr2, i);
            i += 16;
        }
        int[] iArrCreate6 = X448Field.create();
        X448Field.inv(iArrCreate5, iArrCreate6);
        while (true) {
            X448Field.copy(iArr, i, iArrCreate, 0);
            X448Field.mul(iArrCreate, iArrCreate6, iArrCreate);
            X448Field.copy(iArrCreate, 0, precompBase, i);
            if (i == 0) {
                return;
            }
            i -= 16;
            X448Field.copy(iArr2, i, iArrCreate2, 0);
            X448Field.mul(iArrCreate6, iArrCreate2, iArrCreate6);
        }
    }

    public static void scalarMult(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int[] iArr = new int[14];
        decodeScalar(bArr, i, iArr);
        int[] iArrCreate = X448Field.create();
        X448Field.decode(bArr2, i2, iArrCreate);
        int[] iArrCreate2 = X448Field.create();
        X448Field.copy(iArrCreate, 0, iArrCreate2, 0);
        int[] iArrCreate3 = X448Field.create();
        iArrCreate3[0] = 1;
        int[] iArrCreate4 = X448Field.create();
        iArrCreate4[0] = 1;
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int i4 = 447;
        int i5 = 1;
        while (true) {
            X448Field.add(iArrCreate4, iArrCreate5, iArrCreate6);
            X448Field.sub(iArrCreate4, iArrCreate5, iArrCreate4);
            X448Field.add(iArrCreate2, iArrCreate3, iArrCreate5);
            X448Field.sub(iArrCreate2, iArrCreate3, iArrCreate2);
            X448Field.mul(iArrCreate6, iArrCreate2, iArrCreate6);
            X448Field.mul(iArrCreate4, iArrCreate5, iArrCreate4);
            X448Field.sqr(iArrCreate5, iArrCreate5);
            X448Field.sqr(iArrCreate2, iArrCreate2);
            X448Field.sub(iArrCreate5, iArrCreate2, iArrCreate7);
            X448Field.mul(iArrCreate7, C_A24, iArrCreate3);
            X448Field.add(iArrCreate3, iArrCreate2, iArrCreate3);
            X448Field.mul(iArrCreate3, iArrCreate7, iArrCreate3);
            X448Field.mul(iArrCreate2, iArrCreate5, iArrCreate2);
            X448Field.sub(iArrCreate6, iArrCreate4, iArrCreate5);
            X448Field.add(iArrCreate6, iArrCreate4, iArrCreate4);
            X448Field.sqr(iArrCreate4, iArrCreate4);
            X448Field.sqr(iArrCreate5, iArrCreate5);
            X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
            i4--;
            int i6 = (iArr[i4 >>> 5] >>> (i4 & 31)) & 1;
            int i7 = i5 ^ i6;
            X448Field.cswap(i7, iArrCreate2, iArrCreate4);
            X448Field.cswap(i7, iArrCreate3, iArrCreate5);
            if (i4 < 2) {
                break;
            } else {
                i5 = i6;
            }
        }
        for (int i8 = 0; i8 < 2; i8++) {
            pointDouble(iArrCreate2, iArrCreate3);
        }
        X448Field.inv(iArrCreate3, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.normalize(iArrCreate2);
        X448Field.encode(iArrCreate2, bArr3, i3);
    }

    public static void scalarMultBase(byte[] bArr, int i, byte[] bArr2, int i2) {
        precompute();
        int[] iArr = new int[14];
        decodeScalar(bArr, i, iArr);
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.copy(S_x, 0, iArrCreate2, 0);
        int[] iArrCreate3 = X448Field.create();
        iArrCreate3[0] = 1;
        int[] iArrCreate4 = X448Field.create();
        X448Field.copy(PsubS_x, 0, iArrCreate4, 0);
        int[] iArrCreate5 = X448Field.create();
        iArrCreate5[0] = 1;
        int[] iArrCreate6 = X448Field.create();
        int i3 = 0;
        int i4 = 1;
        int i5 = 2;
        while (true) {
            X448Field.copy(precompBase, i3, iArrCreate, 0);
            i3 += 16;
            int i6 = (iArr[i5 >>> 5] >>> (i5 & 31)) & 1;
            int i7 = i4 ^ i6;
            X448Field.cswap(i7, iArrCreate2, iArrCreate4);
            X448Field.cswap(i7, iArrCreate3, iArrCreate5);
            X448Field.add(iArrCreate2, iArrCreate3, iArrCreate6);
            X448Field.sub(iArrCreate2, iArrCreate3, iArrCreate3);
            X448Field.mul(iArrCreate, iArrCreate3, iArrCreate);
            X448Field.carry(iArrCreate6);
            X448Field.add(iArrCreate6, iArrCreate, iArrCreate2);
            X448Field.sub(iArrCreate6, iArrCreate, iArrCreate3);
            X448Field.sqr(iArrCreate2, iArrCreate2);
            X448Field.sqr(iArrCreate3, iArrCreate3);
            X448Field.mul(iArrCreate5, iArrCreate2, iArrCreate2);
            X448Field.mul(iArrCreate4, iArrCreate3, iArrCreate3);
            i5++;
            if (i5 >= 448) {
                break;
            } else {
                i4 = i6;
            }
        }
        for (int i8 = 0; i8 < 2; i8++) {
            pointDouble(iArrCreate2, iArrCreate3);
        }
        X448Field.inv(iArrCreate3, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.normalize(iArrCreate2);
        X448Field.encode(iArrCreate2, bArr2, i2);
    }
}

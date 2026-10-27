package org.bouncycastle.math.ec.rfc7748;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class X25519 {
    private static final int C_A = 486662;
    private static final int C_A24 = 121666;
    private static final int[] PsubS_x = {64258704, 46628941, 18905110, 42949224, 8920788, 10663709, 35115447, 21804323, 8973338, 4366948};
    private static int[] precompBase = null;

    private static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & 255) << 16);
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = decode32(bArr, (i2 * 4) + i);
        }
        iArr[0] = iArr[0] & (-8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        iArr[7] = iArr[7] | 1073741824;
    }

    private static void pointDouble(int[] iArr, int[] iArr2) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        X25519Field.apm(iArr, iArr2, iArrCreate, iArrCreate2);
        X25519Field.sqr(iArrCreate, iArrCreate);
        X25519Field.sqr(iArrCreate2, iArrCreate2);
        X25519Field.mul(iArrCreate, iArrCreate2, iArr);
        X25519Field.sub(iArrCreate, iArrCreate2, iArrCreate);
        X25519Field.mul(iArrCreate, C_A24, iArr2);
        X25519Field.add(iArr2, iArrCreate2, iArr2);
        X25519Field.mul(iArr2, iArrCreate, iArr2);
    }

    public static synchronized void precompute() {
        if (precompBase != null) {
            return;
        }
        int[] iArr = new int[2520];
        precompBase = iArr;
        int[] iArr2 = new int[2510];
        int[] iArrCreate = X25519Field.create();
        iArrCreate[0] = 9;
        int[] iArrCreate2 = X25519Field.create();
        iArrCreate2[0] = 1;
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        X25519Field.apm(iArrCreate, iArrCreate2, iArrCreate3, iArrCreate4);
        int[] iArrCreate5 = X25519Field.create();
        X25519Field.copy(iArrCreate4, 0, iArrCreate5, 0);
        int i = 0;
        while (true) {
            X25519Field.copy(iArrCreate3, 0, iArr, i);
            if (i == 2510) {
                break;
            }
            pointDouble(iArrCreate, iArrCreate2);
            X25519Field.apm(iArrCreate, iArrCreate2, iArrCreate3, iArrCreate4);
            X25519Field.mul(iArrCreate3, iArrCreate5, iArrCreate3);
            X25519Field.mul(iArrCreate5, iArrCreate4, iArrCreate5);
            X25519Field.copy(iArrCreate4, 0, iArr2, i);
            i += 10;
        }
        int[] iArrCreate6 = X25519Field.create();
        X25519Field.inv(iArrCreate5, iArrCreate6);
        while (true) {
            X25519Field.copy(iArr, i, iArrCreate, 0);
            X25519Field.mul(iArrCreate, iArrCreate6, iArrCreate);
            X25519Field.copy(iArrCreate, 0, precompBase, i);
            if (i == 0) {
                return;
            }
            i -= 10;
            X25519Field.copy(iArr2, i, iArrCreate2, 0);
            X25519Field.mul(iArrCreate6, iArrCreate2, iArrCreate6);
        }
    }

    public static void scalarMult(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int[] iArr = new int[8];
        decodeScalar(bArr, i, iArr);
        int[] iArrCreate = X25519Field.create();
        X25519Field.decode(bArr2, i2, iArrCreate);
        int[] iArrCreate2 = X25519Field.create();
        X25519Field.copy(iArrCreate, 0, iArrCreate2, 0);
        int[] iArrCreate3 = X25519Field.create();
        iArrCreate3[0] = 1;
        int[] iArrCreate4 = X25519Field.create();
        iArrCreate4[0] = 1;
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArrCreate7 = X25519Field.create();
        int i4 = 254;
        int i5 = 1;
        while (true) {
            X25519Field.apm(iArrCreate4, iArrCreate5, iArrCreate6, iArrCreate4);
            X25519Field.apm(iArrCreate2, iArrCreate3, iArrCreate5, iArrCreate2);
            X25519Field.mul(iArrCreate6, iArrCreate2, iArrCreate6);
            X25519Field.mul(iArrCreate4, iArrCreate5, iArrCreate4);
            X25519Field.sqr(iArrCreate5, iArrCreate5);
            X25519Field.sqr(iArrCreate2, iArrCreate2);
            X25519Field.sub(iArrCreate5, iArrCreate2, iArrCreate7);
            X25519Field.mul(iArrCreate7, C_A24, iArrCreate3);
            X25519Field.add(iArrCreate3, iArrCreate2, iArrCreate3);
            X25519Field.mul(iArrCreate3, iArrCreate7, iArrCreate3);
            X25519Field.mul(iArrCreate2, iArrCreate5, iArrCreate2);
            X25519Field.apm(iArrCreate6, iArrCreate4, iArrCreate4, iArrCreate5);
            X25519Field.sqr(iArrCreate4, iArrCreate4);
            X25519Field.sqr(iArrCreate5, iArrCreate5);
            X25519Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
            i4--;
            int i6 = (iArr[i4 >>> 5] >>> (i4 & 31)) & 1;
            int i7 = i5 ^ i6;
            X25519Field.cswap(i7, iArrCreate2, iArrCreate4);
            X25519Field.cswap(i7, iArrCreate3, iArrCreate5);
            if (i4 < 3) {
                break;
            } else {
                i5 = i6;
            }
        }
        for (int i8 = 0; i8 < 3; i8++) {
            pointDouble(iArrCreate2, iArrCreate3);
        }
        X25519Field.inv(iArrCreate3, iArrCreate3);
        X25519Field.mul(iArrCreate2, iArrCreate3, iArrCreate2);
        X25519Field.normalize(iArrCreate2);
        X25519Field.encode(iArrCreate2, bArr3, i3);
    }

    public static void scalarMultBase(byte[] bArr, int i, byte[] bArr2, int i2) {
        precompute();
        int[] iArr = new int[8];
        decodeScalar(bArr, i, iArr);
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        iArrCreate2[0] = 1;
        int[] iArrCreate3 = X25519Field.create();
        iArrCreate3[0] = 1;
        int[] iArrCreate4 = X25519Field.create();
        X25519Field.copy(PsubS_x, 0, iArrCreate4, 0);
        int[] iArrCreate5 = X25519Field.create();
        iArrCreate5[0] = 1;
        int i3 = 0;
        int i4 = 1;
        int i5 = 3;
        while (true) {
            X25519Field.copy(precompBase, i3, iArrCreate, 0);
            i3 += 10;
            int i6 = (iArr[i5 >>> 5] >>> (i5 & 31)) & 1;
            int i7 = i4 ^ i6;
            X25519Field.cswap(i7, iArrCreate2, iArrCreate4);
            X25519Field.cswap(i7, iArrCreate3, iArrCreate5);
            X25519Field.apm(iArrCreate2, iArrCreate3, iArrCreate2, iArrCreate3);
            X25519Field.mul(iArrCreate, iArrCreate3, iArrCreate);
            X25519Field.carry(iArrCreate2);
            X25519Field.apm(iArrCreate2, iArrCreate, iArrCreate2, iArrCreate3);
            X25519Field.sqr(iArrCreate2, iArrCreate2);
            X25519Field.sqr(iArrCreate3, iArrCreate3);
            X25519Field.mul(iArrCreate5, iArrCreate2, iArrCreate2);
            X25519Field.mul(iArrCreate4, iArrCreate3, iArrCreate3);
            i5++;
            if (i5 >= 255) {
                break;
            } else {
                i4 = i6;
            }
        }
        for (int i8 = 0; i8 < 3; i8++) {
            pointDouble(iArrCreate2, iArrCreate3);
        }
        X25519Field.inv(iArrCreate3, iArrCreate3);
        X25519Field.mul(iArrCreate2, iArrCreate3, iArrCreate2);
        X25519Field.normalize(iArrCreate2);
        X25519Field.encode(iArrCreate2, bArr2, i2);
    }
}

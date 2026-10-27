package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.crypto.util.Pack;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
abstract class GCMUtil {
    GCMUtil() {
    }

    static int[] asInts(byte[] bArr) {
        return new int[]{Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
    }

    static void multiply(byte[] bArr, byte[] bArr2) {
        byte[] bArrClone = Arrays.clone(bArr);
        byte[] bArr3 = new byte[16];
        for (int i = 0; i < 16; i++) {
            byte b = bArr2[i];
            for (int i2 = 7; i2 >= 0; i2--) {
                if (((1 << i2) & b) != 0) {
                    xor(bArr3, bArrClone);
                }
                boolean z = (bArrClone[15] & 1) != 0;
                shiftRight(bArrClone);
                if (z) {
                    bArrClone[0] = (byte) (bArrClone[0] ^ (-31));
                }
            }
        }
        System.arraycopy(bArr3, 0, bArr, 0, 16);
    }

    static void multiplyP(int[] iArr) {
        boolean z = (iArr[3] & 1) != 0;
        shiftRight(iArr);
        if (z) {
            iArr[0] = iArr[0] ^ (-520093696);
        }
    }

    static void multiplyP8(int[] iArr) {
        for (int i = 8; i != 0; i--) {
            multiplyP(iArr);
        }
    }

    static byte[] oneAsBytes() {
        byte[] bArr = new byte[16];
        bArr[0] = -128;
        return bArr;
    }

    static int[] oneAsInts() {
        int[] iArr = new int[4];
        iArr[0] = Integer.MIN_VALUE;
        return iArr;
    }

    static void shiftRight(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = bArr[i] & 255;
            bArr[i] = (byte) (i2 | (i3 >>> 1));
            i++;
            if (i == 16) {
                return;
            } else {
                i2 = (i3 & 1) << 7;
            }
        }
    }

    static void shiftRight(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = iArr[i];
            iArr[i] = i2 | (i3 >>> 1);
            i++;
            if (i == 4) {
                return;
            } else {
                i2 = i3 << 31;
            }
        }
    }

    static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 15; i >= 0; i--) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    static void xor(int[] iArr, int[] iArr2) {
        for (int i = 3; i >= 0; i--) {
            iArr[i] = iArr[i] ^ iArr2[i];
        }
    }
}

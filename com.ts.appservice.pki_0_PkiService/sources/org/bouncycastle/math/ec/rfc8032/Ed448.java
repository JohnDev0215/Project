package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class Ed448 {
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = Strings.toByteArray("SigEd448");
    private static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    private static final int[] L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, 1073741823};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class PointExt {
        int[] x;
        int[] y;
        int[] z;

        private PointExt() {
            this.x = X448Field.create();
            this.y = X448Field.create();
            this.z = X448Field.create();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class PointPrecomp {
        int[] x;
        int[] y;

        private PointPrecomp() {
            this.x = X448Field.create();
            this.y = X448Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[SIGNATURE_SIZE];
        for (int i = 0; i < 28; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & 127) != 0) {
            return false;
        }
        decode32(bArr, 0, new int[14], 0, 14);
        return !Nat.gte(14, r2, P);
    }

    private static boolean checkScalarVar(byte[] bArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, new int[14]);
        return !Nat.gte(14, r2, L);
    }

    private static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    private static int decode24(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        return ((bArr[i3 + 1] & 255) << 16) | i2 | ((bArr[i3] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointExt pointExt) {
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i + 57);
        if (!checkPointVar(bArrCopyOfRange)) {
            return false;
        }
        int i2 = (bArrCopyOfRange[56] & 128) >>> 7;
        bArrCopyOfRange[56] = (byte) (bArrCopyOfRange[56] & 127);
        X448Field.decode(bArrCopyOfRange, 0, pointExt.y);
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.sqr(pointExt.y, iArrCreate);
        X448Field.mul(iArrCreate, 39081, iArrCreate2);
        X448Field.negate(iArrCreate, iArrCreate);
        X448Field.addOne(iArrCreate);
        X448Field.addOne(iArrCreate2);
        if (!X448Field.sqrtRatioVar(iArrCreate, iArrCreate2, pointExt.x)) {
            return false;
        }
        X448Field.normalize(pointExt.x);
        if (i2 == 1 && X448Field.isZeroVar(pointExt.x)) {
            return false;
        }
        if (z ^ (i2 != (pointExt.x[0] & 1))) {
            int[] iArr = pointExt.x;
            X448Field.negate(iArr, iArr);
        }
        pointExtendXY(pointExt);
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    private static void dom4(SHAKEDigest sHAKEDigest, byte b2, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        sHAKEDigest.update(bArr2, 0, bArr2.length);
        sHAKEDigest.update(b2);
        sHAKEDigest.update((byte) bArr.length);
        sHAKEDigest.update(bArr, 0, bArr.length);
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static void encodePoint(PointExt pointExt, byte[] bArr, int i) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.inv(pointExt.z, iArrCreate2);
        X448Field.mul(pointExt.x, iArrCreate2, iArrCreate);
        X448Field.mul(pointExt.y, iArrCreate2, iArrCreate2);
        X448Field.normalize(iArrCreate);
        X448Field.normalize(iArrCreate2);
        X448Field.encode(iArrCreate2, bArr, i);
        bArr[(i + 57) - 1] = (byte) ((iArrCreate[0] & 1) << 7);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        byte[] bArr3 = new byte[SIGNATURE_SIZE];
        sHAKEDigest.update(bArr, i, 57);
        sHAKEDigest.doFinal(bArr3, 0, SIGNATURE_SIZE);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncodedVar(bArr4, bArr2, i2);
    }

    private static byte[] getWNAF(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int i2 = 0;
        int i3 = 14;
        int i4 = 28;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i6 = iArr[i3];
            int i7 = i4 - 1;
            iArr2[i7] = (i5 << 16) | (i6 >>> 16);
            i4 = i7 - 1;
            iArr2[i4] = i6;
            i5 = i6;
        }
        byte[] bArr = new byte[448];
        int i8 = 1 << i;
        int i9 = i8 - 1;
        int i10 = i8 >>> 1;
        int i11 = 0;
        int i12 = 0;
        while (i2 < 28) {
            int i13 = iArr2[i2];
            while (i11 < 16) {
                int i14 = i13 >>> i11;
                if ((i14 & 1) == i12) {
                    i11++;
                } else {
                    int i15 = (i14 & i9) + i12;
                    int i16 = i15 & i10;
                    int i17 = i15 - (i16 << 1);
                    i12 = i16 >>> (i - 1);
                    bArr[(i2 << 4) + i11] = (byte) i17;
                    i11 += i;
                }
            }
            i2++;
            i11 -= 16;
        }
        return bArr;
    }

    private static void implSignVar(SHAKEDigest sHAKEDigest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(sHAKEDigest, (byte) 0, bArr4);
        sHAKEDigest.update(bArr, 57, 57);
        sHAKEDigest.update(bArr5, i2, i3);
        sHAKEDigest.doFinal(bArr, 0, bArr.length);
        byte[] bArrReduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncodedVar(bArrReduceScalar, bArr7, 0);
        dom4(sHAKEDigest, (byte) 0, bArr4);
        sHAKEDigest.update(bArr7, 0, 57);
        sHAKEDigest.update(bArr3, i, 57);
        sHAKEDigest.update(bArr5, i2, i3);
        sHAKEDigest.doFinal(bArr, 0, bArr.length);
        byte[] bArrCalculateS = calculateS(bArrReduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(bArrCalculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        X448Field.sqr(pointExt.z, iArrCreate);
        X448Field.mul(pointPrecomp.x, pointExt.x, iArrCreate2);
        X448Field.mul(pointPrecomp.y, pointExt.y, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate4);
        X448Field.mul(iArrCreate4, 39081, iArrCreate4);
        X448Field.add(iArrCreate, iArrCreate4, iArrCreate5);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate6);
        X448Field.add(pointPrecomp.x, pointPrecomp.y, iArrCreate);
        X448Field.add(pointExt.x, pointExt.y, iArrCreate4);
        X448Field.mul(iArrCreate, iArrCreate4, iArrCreate7);
        X448Field.add(iArrCreate3, iArrCreate2, iArrCreate);
        X448Field.sub(iArrCreate3, iArrCreate2, iArrCreate4);
        X448Field.carry(iArrCreate);
        X448Field.sub(iArrCreate7, iArrCreate, iArrCreate7);
        X448Field.mul(iArrCreate7, pointExt.z, iArrCreate7);
        X448Field.mul(iArrCreate4, pointExt.z, iArrCreate4);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt.x);
        X448Field.mul(iArrCreate4, iArrCreate6, pointExt.y);
        X448Field.mul(iArrCreate5, iArrCreate6, pointExt.z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int[] iArrCreate8 = X448Field.create();
        if (z) {
            X448Field.sub(pointExt.y, pointExt.x, iArrCreate8);
            iArr2 = iArrCreate2;
            iArr = iArrCreate5;
            iArr4 = iArrCreate6;
            iArr3 = iArrCreate7;
        } else {
            X448Field.add(pointExt.y, pointExt.x, iArrCreate8);
            iArr = iArrCreate2;
            iArr2 = iArrCreate5;
            iArr3 = iArrCreate6;
            iArr4 = iArrCreate7;
        }
        X448Field.mul(pointExt.z, pointExt2.z, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate2);
        X448Field.mul(pointExt.x, pointExt2.x, iArrCreate3);
        X448Field.mul(pointExt.y, pointExt2.y, iArrCreate4);
        X448Field.mul(iArrCreate3, iArrCreate4, iArrCreate5);
        X448Field.mul(iArrCreate5, 39081, iArrCreate5);
        X448Field.add(iArrCreate2, iArrCreate5, iArr3);
        X448Field.sub(iArrCreate2, iArrCreate5, iArr4);
        X448Field.add(pointExt2.x, pointExt2.y, iArrCreate5);
        X448Field.mul(iArrCreate8, iArrCreate5, iArrCreate8);
        X448Field.add(iArrCreate4, iArrCreate3, iArr);
        X448Field.sub(iArrCreate4, iArrCreate3, iArr2);
        X448Field.carry(iArr);
        X448Field.sub(iArrCreate8, iArrCreate2, iArrCreate8);
        X448Field.mul(iArrCreate8, iArrCreate, iArrCreate8);
        X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
        X448Field.mul(iArrCreate6, iArrCreate8, pointExt2.x);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt2.y);
        X448Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        X448Field.copy(pointExt.x, 0, pointExt2.x, 0);
        X448Field.copy(pointExt.y, 0, pointExt2.y, 0);
        X448Field.copy(pointExt.z, 0, pointExt2.z, 0);
        return pointExt2;
    }

    private static void pointDouble(PointExt pointExt) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        X448Field.add(pointExt.x, pointExt.y, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate);
        X448Field.sqr(pointExt.x, iArrCreate2);
        X448Field.sqr(pointExt.y, iArrCreate3);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate4);
        X448Field.carry(iArrCreate4);
        X448Field.sqr(pointExt.z, iArrCreate5);
        X448Field.add(iArrCreate5, iArrCreate5, iArrCreate5);
        X448Field.carry(iArrCreate5);
        X448Field.sub(iArrCreate4, iArrCreate5, iArrCreate6);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate);
        X448Field.sub(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate, iArrCreate6, pointExt.x);
        X448Field.mul(iArrCreate4, iArrCreate2, pointExt.y);
        X448Field.mul(iArrCreate4, iArrCreate6, pointExt.z);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X448Field.one(pointExt.z);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 16 * 2 * 16;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            Nat.cmov(16, i5, precompBase, i3, pointPrecomp.x, 0);
            int i6 = i3 + 16;
            Nat.cmov(16, i5, precompBase, i6, pointPrecomp.y, 0);
            i3 = i6 + 16;
        }
    }

    private static PointExt[] pointPrecompVar(PointExt pointExt, int i) {
        PointExt pointExtPointCopy = pointCopy(pointExt);
        pointDouble(pointExtPointCopy);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            pointExtArr[i2] = pointCopy(pointExtArr[i2 - 1]);
            pointAddVar(false, pointExtPointCopy, pointExtArr[i2]);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X448Field.zero(pointExt.x);
        X448Field.one(pointExt.y);
        X448Field.one(pointExt.z);
    }

    public static synchronized void precompute() {
        if (precompBase != null) {
            return;
        }
        PointExt pointExt = new PointExt();
        X448Field.copy(B_x, 0, pointExt.x, 0);
        X448Field.copy(B_y, 0, pointExt.y, 0);
        pointExtendXY(pointExt);
        precompBaseTable = pointPrecompVar(pointExt, 32);
        precompBase = new int[2560];
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            PointExt[] pointExtArr = new PointExt[5];
            PointExt pointExt2 = new PointExt();
            pointSetNeutral(pointExt2);
            int i3 = 0;
            while (true) {
                if (i3 >= 5) {
                    break;
                }
                pointAddVar(true, pointExt, pointExt2);
                pointDouble(pointExt);
                pointExtArr[i3] = pointCopy(pointExt);
                for (int i4 = 1; i4 < 18; i4++) {
                    pointDouble(pointExt);
                }
                i3++;
            }
            PointExt[] pointExtArr2 = new PointExt[16];
            pointExtArr2[0] = pointExt2;
            int i5 = 1;
            for (int i6 = 0; i6 < 4; i6++) {
                int i7 = 1 << i6;
                int i8 = 0;
                while (i8 < i7) {
                    pointExtArr2[i5] = pointCopy(pointExtArr2[i5 - i7]);
                    pointAddVar(false, pointExtArr[i6], pointExtArr2[i5]);
                    i8++;
                    i5++;
                }
            }
            for (int i9 = 0; i9 < 16; i9++) {
                PointExt pointExt3 = pointExtArr2[i9];
                X448Field.inv(pointExt3.z, pointExt3.z);
                X448Field.mul(pointExt3.x, pointExt3.z, pointExt3.x);
                X448Field.mul(pointExt3.y, pointExt3.z, pointExt3.y);
                X448Field.copy(pointExt3.x, 0, precompBase, i);
                int i10 = i + 16;
                X448Field.copy(pointExt3.y, 0, precompBase, i10);
                i = i10 + 16;
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 57);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = (byte) (bArr2[56] & 0);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long jDecode32 = ((long) decode32(bArr, 0)) & 4294967295L;
        long jDecode24 = ((long) (decode24(bArr, 4) << 4)) & 4294967295L;
        long jDecode322 = ((long) decode32(bArr, 7)) & 4294967295L;
        long jDecode242 = ((long) (decode24(bArr, 11) << 4)) & 4294967295L;
        long jDecode323 = ((long) decode32(bArr, 14)) & 4294967295L;
        long jDecode243 = ((long) (decode24(bArr, 18) << 4)) & 4294967295L;
        long jDecode324 = ((long) decode32(bArr, 21)) & 4294967295L;
        long jDecode244 = ((long) (decode24(bArr, 25) << 4)) & 4294967295L;
        long jDecode325 = ((long) decode32(bArr, 28)) & 4294967295L;
        long jDecode245 = ((long) (decode24(bArr, 32) << 4)) & 4294967295L;
        long jDecode326 = ((long) decode32(bArr, 35)) & 4294967295L;
        long jDecode246 = ((long) (decode24(bArr, 39) << 4)) & 4294967295L;
        long jDecode327 = ((long) decode32(bArr, 42)) & 4294967295L;
        long jDecode247 = ((long) (decode24(bArr, 46) << 4)) & 4294967295L;
        long jDecode328 = ((long) decode32(bArr, 49)) & 4294967295L;
        long jDecode248 = ((long) (decode24(bArr, 53) << 4)) & 4294967295L;
        long jDecode329 = ((long) decode32(bArr, 56)) & 4294967295L;
        long jDecode249 = ((long) (decode24(bArr, 60) << 4)) & 4294967295L;
        long jDecode3210 = ((long) decode32(bArr, 63)) & 4294967295L;
        long jDecode2410 = ((long) (decode24(bArr, 67) << 4)) & 4294967295L;
        long jDecode3211 = ((long) decode32(bArr, 70)) & 4294967295L;
        long jDecode2411 = ((long) (decode24(bArr, 74) << 4)) & 4294967295L;
        long jDecode3212 = ((long) decode32(bArr, 77)) & 4294967295L;
        long jDecode2412 = ((long) (decode24(bArr, 81) << 4)) & 4294967295L;
        long jDecode3213 = ((long) decode32(bArr, 84)) & 4294967295L;
        long jDecode2413 = ((long) (decode24(bArr, 88) << 4)) & 4294967295L;
        long jDecode3214 = ((long) decode32(bArr, 91)) & 4294967295L;
        long jDecode2414 = ((long) (decode24(bArr, 95) << 4)) & 4294967295L;
        long jDecode3215 = ((long) decode32(bArr, 98)) & 4294967295L;
        long jDecode2415 = ((long) (decode24(bArr, 102) << 4)) & 4294967295L;
        long jDecode3216 = ((long) decode32(bArr, CipherSuite.TLS_DH_RSA_WITH_AES_256_CBC_SHA256)) & 4294967295L;
        long jDecode2416 = ((long) (decode24(bArr, CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256) << 4)) & 4294967295L;
        long jDecode16 = ((long) decode16(bArr, 112)) & 4294967295L;
        long j = jDecode2416 + (jDecode3216 >>> 28);
        long j2 = jDecode3216 & M28L;
        long j3 = jDecode2411 + (jDecode16 * 227822194) + (j * 149865618);
        long j4 = jDecode3212 + (jDecode16 * 149865618) + (j * 550336261);
        long j5 = jDecode328 + (j2 * 43969588);
        long j6 = jDecode248 + (j * 43969588) + (j2 * 30366549);
        long j7 = jDecode329 + (jDecode16 * 43969588) + (j * 30366549) + (j2 * 163752818);
        long j8 = jDecode249 + (jDecode16 * 30366549) + (j * 163752818) + (j2 * 258169998);
        long j9 = jDecode3210 + (jDecode16 * 163752818) + (j * 258169998) + (j2 * 96434764);
        long j10 = jDecode2410 + (jDecode16 * 258169998) + (j * 96434764) + (j2 * 227822194);
        long j11 = jDecode3211 + (jDecode16 * 96434764) + (j * 227822194) + (j2 * 149865618);
        long j12 = jDecode2415 + (jDecode3215 >>> 28);
        long j13 = jDecode3215 & M28L;
        long j14 = jDecode247 + (j12 * 43969588);
        long j15 = j10 + (j12 * 149865618);
        long j16 = j11 + (j12 * 550336261);
        long j17 = jDecode327 + (j13 * 43969588);
        long j18 = j5 + (j12 * 30366549) + (j13 * 163752818);
        long j19 = j6 + (j12 * 163752818) + (j13 * 258169998);
        long j20 = j7 + (j12 * 258169998) + (j13 * 96434764);
        long j21 = j8 + (j12 * 96434764) + (j13 * 227822194);
        long j22 = j9 + (j12 * 227822194) + (j13 * 149865618);
        long j23 = jDecode2414 + (jDecode3214 >>> 28);
        long j24 = jDecode3214 & M28L;
        long j25 = jDecode246 + (j23 * 43969588);
        long j26 = j22 + (j23 * 550336261);
        long j27 = jDecode326 + (j24 * 43969588);
        long j28 = j17 + (j23 * 30366549) + (j24 * 163752818);
        long j29 = j14 + (j13 * 30366549) + (j23 * 163752818) + (j24 * 258169998);
        long j30 = j18 + (j23 * 258169998) + (j24 * 96434764);
        long j31 = j19 + (j23 * 96434764) + (j24 * 227822194);
        long j32 = j20 + (j23 * 227822194) + (j24 * 149865618);
        long j33 = j21 + (j23 * 149865618) + (j24 * 550336261);
        long j34 = jDecode2413 + (jDecode3213 >>> 28);
        long j35 = jDecode3213 & M28L;
        long j36 = j3 + (j2 * 550336261) + (j16 >>> 28);
        long j37 = j16 & M28L;
        long j38 = j4 + (j36 >>> 28);
        long j39 = j36 & M28L;
        long j40 = jDecode2412 + (jDecode16 * 550336261) + (j38 >>> 28);
        long j41 = j38 & M28L;
        long j42 = j35 + (j40 >>> 28);
        long j43 = j40 & M28L;
        long j44 = jDecode244 + (j43 * 43969588);
        long j45 = jDecode325 + (j42 * 43969588) + (j43 * 30366549);
        long j46 = jDecode245 + (j34 * 43969588) + (j42 * 30366549) + (j43 * 163752818);
        long j47 = j27 + (j34 * 30366549) + (j42 * 163752818) + (j43 * 258169998);
        long j48 = j25 + (j24 * 30366549) + (j34 * 163752818) + (j42 * 258169998) + (j43 * 96434764);
        long j49 = j28 + (j34 * 258169998) + (j42 * 96434764) + (j43 * 227822194);
        long j50 = j29 + (j34 * 96434764) + (j42 * 227822194) + (j43 * 149865618);
        long j51 = j30 + (j34 * 227822194) + (j42 * 149865618) + (j43 * 550336261);
        long j52 = jDecode324 + (j41 * 43969588);
        long j53 = j26 + (j33 >>> 28);
        long j54 = j33 & M28L;
        long j55 = j15 + (j13 * 550336261) + (j53 >>> 28);
        long j56 = j53 & M28L;
        long j57 = j37 + (j55 >>> 28);
        long j58 = j55 & M28L;
        long j59 = j39 + (j57 >>> 28);
        long j60 = j57 & M28L;
        long j61 = jDecode323 + (j60 * 43969588);
        long j62 = jDecode243 + (j59 * 43969588) + (j60 * 30366549);
        long j63 = j52 + (j59 * 30366549) + (j60 * 163752818);
        long j64 = j44 + (j41 * 30366549) + (j59 * 163752818) + (j60 * 258169998);
        long j65 = j45 + (j41 * 163752818) + (j59 * 258169998) + (j60 * 96434764);
        long j66 = j46 + (j41 * 258169998) + (j59 * 96434764) + (j60 * 227822194);
        long j67 = j47 + (j41 * 96434764) + (j59 * 227822194) + (j60 * 149865618);
        long j68 = j48 + (j41 * 227822194) + (j59 * 149865618) + (j60 * 550336261);
        long j69 = jDecode242 + (j58 * 43969588);
        long j70 = j61 + (j58 * 30366549);
        long j71 = j62 + (j58 * 163752818);
        long j72 = j63 + (j58 * 258169998);
        long j73 = j64 + (j58 * 96434764);
        long j74 = j65 + (j58 * 227822194);
        long j75 = j66 + (j58 * 149865618);
        long j76 = j67 + (j58 * 550336261);
        long j77 = j31 + (j34 * 149865618) + (j42 * 550336261) + (j51 >>> 28);
        long j78 = j51 & M28L;
        long j79 = j32 + (j34 * 550336261) + (j77 >>> 28);
        long j80 = j77 & M28L;
        long j81 = j54 + (j79 >>> 28);
        long j82 = j79 & M28L;
        long j83 = j56 + (j81 >>> 28);
        long j84 = j81 & M28L;
        long j85 = jDecode322 + (j83 * 43969588);
        long j86 = j69 + (j83 * 30366549);
        long j87 = j70 + (j83 * 163752818);
        long j88 = j71 + (j83 * 258169998);
        long j89 = j72 + (j83 * 96434764);
        long j90 = j73 + (j83 * 227822194);
        long j91 = j74 + (j83 * 149865618);
        long j92 = j75 + (j83 * 550336261);
        long j93 = j80 & M26L;
        long j94 = (j82 * 4) + (j80 >>> 26) + 1;
        long j95 = jDecode32 + (78101261 * j94);
        long j96 = j85 + (30366549 * j84) + (175155932 * j94);
        long j97 = j86 + (163752818 * j84) + (64542499 * j94);
        long j98 = j87 + (258169998 * j84) + (158326419 * j94);
        long j99 = j88 + (96434764 * j84) + (191173276 * j94);
        long j100 = j89 + (227822194 * j84) + (104575268 * j94);
        long j101 = j90 + (149865618 * j84) + (j94 * 137584065);
        long j102 = jDecode24 + (43969588 * j84) + (141809365 * j94) + (j95 >>> 28);
        long j103 = j95 & M28L;
        long j104 = j96 + (j102 >>> 28);
        long j105 = j102 & M28L;
        long j106 = j97 + (j104 >>> 28);
        long j107 = j104 & M28L;
        long j108 = j98 + (j106 >>> 28);
        long j109 = j106 & M28L;
        long j110 = j99 + (j108 >>> 28);
        long j111 = j108 & M28L;
        long j112 = j100 + (j110 >>> 28);
        long j113 = j110 & M28L;
        long j114 = j101 + (j112 >>> 28);
        long j115 = j112 & M28L;
        long j116 = j91 + (j84 * 550336261) + (j114 >>> 28);
        long j117 = j114 & M28L;
        long j118 = j92 + (j116 >>> 28);
        long j119 = j116 & M28L;
        long j120 = j76 + (j118 >>> 28);
        long j121 = j118 & M28L;
        long j122 = j68 + (j120 >>> 28);
        long j123 = j120 & M28L;
        long j124 = j49 + (j41 * 149865618) + (j59 * 550336261) + (j122 >>> 28);
        long j125 = j122 & M28L;
        long j126 = j50 + (j41 * 550336261) + (j124 >>> 28);
        long j127 = j124 & M28L;
        long j128 = j78 + (j126 >>> 28);
        long j129 = j126 & M28L;
        long j130 = j93 + (j128 >>> 28);
        long j131 = j128 & M28L;
        long j132 = j130 & M26L;
        long j133 = (j130 >>> 26) - 1;
        long j134 = j103 - (j133 & 78101261);
        long j135 = (j105 - (j133 & 141809365)) + (j134 >> 28);
        long j136 = j134 & M28L;
        long j137 = (j107 - (j133 & 175155932)) + (j135 >> 28);
        long j138 = j135 & M28L;
        long j139 = (j109 - (j133 & 64542499)) + (j137 >> 28);
        long j140 = j137 & M28L;
        long j141 = (j111 - (j133 & 158326419)) + (j139 >> 28);
        long j142 = j139 & M28L;
        long j143 = (j113 - (j133 & 191173276)) + (j141 >> 28);
        long j144 = j141 & M28L;
        long j145 = (j115 - (j133 & 104575268)) + (j143 >> 28);
        long j146 = j143 & M28L;
        long j147 = (j117 - (j133 & 137584065)) + (j145 >> 28);
        long j148 = j145 & M28L;
        long j149 = j119 + (j147 >> 28);
        long j150 = j147 & M28L;
        long j151 = j121 + (j149 >> 28);
        long j152 = j149 & M28L;
        long j153 = j123 + (j151 >> 28);
        long j154 = j151 & M28L;
        long j155 = j125 + (j153 >> 28);
        long j156 = j153 & M28L;
        long j157 = j127 + (j155 >> 28);
        long j158 = j155 & M28L;
        long j159 = j129 + (j157 >> 28);
        long j160 = j157 & M28L;
        long j161 = j131 + (j159 >> 28);
        long j162 = j159 & M28L;
        long j163 = j132 + (j161 >> 28);
        long j164 = j161 & M28L;
        byte[] bArr2 = new byte[57];
        encode56((j138 << 28) | j136, bArr2, 0);
        encode56((j142 << 28) | j140, bArr2, 7);
        encode56(j144 | (j146 << 28), bArr2, 14);
        encode56(j148 | (j150 << 28), bArr2, 21);
        encode56(j152 | (j154 << 28), bArr2, 28);
        encode56(j156 | (j158 << 28), bArr2, 35);
        encode56(j160 | (j162 << 28), bArr2, 42);
        encode56((j163 << 28) | j164, bArr2, 49);
        return bArr2;
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        pointSetNeutral(pointExt);
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr) + 4;
        Nat.shiftDownBit(15, iArr, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        int i = 17;
        while (true) {
            int i2 = i;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                for (int i5 = 0; i5 < 5; i5++) {
                    i4 |= ((iArr[i2 >>> 5] >>> (i2 & 31)) & 1) << i5;
                    i2 += 18;
                }
                int i6 = (i4 >>> 4) & 1;
                pointLookup(i3, ((-i6) ^ i4) & 15, pointPrecomp);
                X448Field.cnegate(i6, pointPrecomp.x);
                pointAddPrecomp(pointPrecomp, pointExt);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt);
            }
        }
    }

    private static void scalarMultBaseEncodedVar(byte[] bArr, byte[] bArr2, int i) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        encodePoint(pointExt, bArr2, i);
    }

    private static void scalarMultStraussVar(int[] iArr, int[] iArr2, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnaf = getWNAF(iArr, 7);
        byte[] wnaf2 = getWNAF(iArr2, 5);
        PointExt[] pointExtArrPointPrecompVar = pointPrecompVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i = 447;
        while (i > 0 && (wnaf[i] | wnaf2[i]) == 0) {
            i--;
        }
        while (true) {
            byte b2 = wnaf[i];
            if (b2 != 0) {
                int i2 = b2 >> 31;
                pointAddVar(i2 != 0, precompBaseTable[(b2 ^ i2) >>> 1], pointExt2);
            }
            byte b3 = wnaf2[i];
            if (b3 != 0) {
                int i3 = b3 >> 31;
                pointAddVar(i3 != 0, pointExtArrPointPrecompVar[(b3 ^ i3) >>> 1], pointExt2);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt2);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        byte[] bArr6 = new byte[SIGNATURE_SIZE];
        sHAKEDigest.update(bArr, i, 57);
        sHAKEDigest.doFinal(bArr6, 0, SIGNATURE_SIZE);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSignVar(sHAKEDigest, bArr6, bArr7, bArr2, i2, bArr3, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        byte[] bArr5 = new byte[SIGNATURE_SIZE];
        sHAKEDigest.update(bArr, i, 57);
        sHAKEDigest.doFinal(bArr5, 0, SIGNATURE_SIZE);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncodedVar(bArr6, bArr7, 0);
        implSignVar(sHAKEDigest, bArr5, bArr6, bArr7, 0, bArr2, bArr3, i2, i3, bArr4, i4);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        int i5 = i + 57;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i5);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, i5, i + SIGNATURE_SIZE);
        if (!checkPointVar(bArrCopyOfRange) || !checkScalarVar(bArrCopyOfRange2)) {
            return false;
        }
        PointExt pointExt = new PointExt();
        if (!decodePointVar(bArr2, i2, true, pointExt)) {
            return false;
        }
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        byte[] bArr5 = new byte[SIGNATURE_SIZE];
        dom4(sHAKEDigest, (byte) 0, bArr3);
        sHAKEDigest.update(bArrCopyOfRange, 0, 57);
        sHAKEDigest.update(bArr2, i2, 57);
        sHAKEDigest.update(bArr4, i3, i4);
        sHAKEDigest.doFinal(bArr5, 0, SIGNATURE_SIZE);
        byte[] bArrReduceScalar = reduceScalar(bArr5);
        int[] iArr = new int[14];
        decodeScalar(bArrCopyOfRange2, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArrReduceScalar, 0, iArr2);
        PointExt pointExt2 = new PointExt();
        scalarMultStraussVar(iArr, iArr2, pointExt, pointExt2);
        byte[] bArr6 = new byte[57];
        encodePoint(pointExt2, bArr6, 0);
        return Arrays.areEqual(bArr6, bArrCopyOfRange);
    }
}

package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class Ed25519 {
    private static final int L0 = -50998291;
    private static final int L1 = 19280294;
    private static final int L2 = 127719000;
    private static final int L3 = -6428113;
    private static final int L4 = 5343;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final int[] P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class PointExt {
        int[] t;
        int[] x;
        int[] y;
        int[] z;

        private PointExt() {
            this.x = X25519Field.create();
            this.y = X25519Field.create();
            this.z = X25519Field.create();
            this.t = X25519Field.create();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class PointPrecomp {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        private PointPrecomp() {
            this.ypx_h = X25519Field.create();
            this.ymx_h = X25519Field.create();
            this.xyd = X25519Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < 16; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, P);
    }

    private static boolean checkScalarVar(byte[] bArr) {
        decodeScalar(bArr, 0, new int[8]);
        return !Nat256.gte(r0, L);
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
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i + 32);
        if (!checkPointVar(bArrCopyOfRange)) {
            return false;
        }
        int i2 = (bArrCopyOfRange[31] & 128) >>> 7;
        bArrCopyOfRange[31] = (byte) (bArrCopyOfRange[31] & 127);
        X25519Field.decode(bArrCopyOfRange, 0, pointExt.y);
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        X25519Field.sqr(pointExt.y, iArrCreate);
        X25519Field.mul(C_d, iArrCreate, iArrCreate2);
        X25519Field.subOne(iArrCreate);
        X25519Field.addOne(iArrCreate2);
        if (!X25519Field.sqrtRatioVar(iArrCreate, iArrCreate2, pointExt.x)) {
            return false;
        }
        X25519Field.normalize(pointExt.x);
        if (i2 == 1 && X25519Field.isZeroVar(pointExt.x)) {
            return false;
        }
        if (z ^ (i2 != (pointExt.x[0] & 1))) {
            int[] iArr = pointExt.x;
            X25519Field.negate(iArr, iArr);
        }
        pointExtendXY(pointExt);
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 8);
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
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        X25519Field.inv(pointExt.z, iArrCreate2);
        X25519Field.mul(pointExt.x, iArrCreate2, iArrCreate);
        X25519Field.mul(pointExt.y, iArrCreate2, iArrCreate2);
        X25519Field.normalize(iArrCreate);
        X25519Field.normalize(iArrCreate2);
        X25519Field.encode(iArrCreate2, bArr, i);
        int i2 = (i + 32) - 1;
        bArr[i2] = (byte) (bArr[i2] | ((iArrCreate[0] & 1) << 7));
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        SHA512Digest sHA512Digest = new SHA512Digest();
        byte[] bArr3 = new byte[sHA512Digest.getDigestSize()];
        sHA512Digest.update(bArr, i, 32);
        sHA512Digest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static byte[] getWNAF(int[] iArr, int i) {
        int[] iArr2 = new int[16];
        int i2 = 0;
        int i3 = 8;
        int i4 = 16;
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
        byte[] bArr = new byte[256];
        int i8 = 1 << i;
        int i9 = i8 - 1;
        int i10 = i8 >>> 1;
        int i11 = 0;
        int i12 = 0;
        while (i2 < 16) {
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

    private static void implSign(SHA512Digest sHA512Digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, byte[] bArr5, int i4) {
        sHA512Digest.update(bArr, 32, 32);
        sHA512Digest.update(bArr4, i2, i3);
        sHA512Digest.doFinal(bArr, 0);
        byte[] bArrReduceScalar = reduceScalar(bArr);
        byte[] bArr6 = new byte[32];
        scalarMultBaseEncoded(bArrReduceScalar, bArr6, 0);
        sHA512Digest.update(bArr6, 0, 32);
        sHA512Digest.update(bArr3, 0, 32);
        sHA512Digest.update(bArr4, i2, i3);
        sHA512Digest.doFinal(bArr, 0);
        byte[] bArrCalculateS = calculateS(bArrReduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr6, 0, bArr5, i4, 32);
        System.arraycopy(bArrCalculateS, 0, bArr5, i4 + 32, 32);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArrCreate7 = X25519Field.create();
        X25519Field.apm(pointExt.y, pointExt.x, iArrCreate2, iArrCreate);
        X25519Field.mul(iArrCreate, pointPrecomp.ymx_h, iArrCreate);
        X25519Field.mul(iArrCreate2, pointPrecomp.ypx_h, iArrCreate2);
        X25519Field.mul(pointExt.t, pointPrecomp.xyd, iArrCreate3);
        X25519Field.apm(iArrCreate2, iArrCreate, iArrCreate7, iArrCreate4);
        X25519Field.apm(pointExt.z, iArrCreate3, iArrCreate6, iArrCreate5);
        X25519Field.carry(iArrCreate6);
        X25519Field.mul(iArrCreate4, iArrCreate5, pointExt.x);
        X25519Field.mul(iArrCreate6, iArrCreate7, pointExt.y);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointExt.z);
        X25519Field.mul(iArrCreate4, iArrCreate7, pointExt.t);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArrCreate7 = X25519Field.create();
        int[] iArrCreate8 = X25519Field.create();
        if (z) {
            iArr2 = iArrCreate3;
            iArr = iArrCreate4;
            iArr4 = iArrCreate6;
            iArr3 = iArrCreate7;
        } else {
            iArr = iArrCreate3;
            iArr2 = iArrCreate4;
            iArr3 = iArrCreate6;
            iArr4 = iArrCreate7;
        }
        X25519Field.apm(pointExt2.y, pointExt2.x, iArrCreate2, iArrCreate);
        X25519Field.apm(pointExt.y, pointExt.x, iArr2, iArr);
        X25519Field.mul(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X25519Field.mul(pointExt2.t, pointExt.t, iArrCreate3);
        X25519Field.mul(iArrCreate3, C_d2, iArrCreate3);
        X25519Field.mul(pointExt2.z, pointExt.z, iArrCreate4);
        X25519Field.add(iArrCreate4, iArrCreate4, iArrCreate4);
        X25519Field.apm(iArrCreate2, iArrCreate, iArrCreate8, iArrCreate5);
        X25519Field.apm(iArrCreate4, iArrCreate3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointExt2.x);
        X25519Field.mul(iArrCreate7, iArrCreate8, pointExt2.y);
        X25519Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
        X25519Field.mul(iArrCreate5, iArrCreate8, pointExt2.t);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        X25519Field.copy(pointExt.x, 0, pointExt2.x, 0);
        X25519Field.copy(pointExt.y, 0, pointExt2.y, 0);
        X25519Field.copy(pointExt.z, 0, pointExt2.z, 0);
        X25519Field.copy(pointExt.t, 0, pointExt2.t, 0);
        return pointExt2;
    }

    private static void pointDouble(PointExt pointExt) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArrCreate7 = X25519Field.create();
        X25519Field.sqr(pointExt.x, iArrCreate);
        X25519Field.sqr(pointExt.y, iArrCreate2);
        X25519Field.sqr(pointExt.z, iArrCreate3);
        X25519Field.add(iArrCreate3, iArrCreate3, iArrCreate3);
        X25519Field.apm(iArrCreate, iArrCreate2, iArrCreate7, iArrCreate6);
        X25519Field.add(pointExt.x, pointExt.y, iArrCreate4);
        X25519Field.sqr(iArrCreate4, iArrCreate4);
        X25519Field.sub(iArrCreate7, iArrCreate4, iArrCreate4);
        X25519Field.add(iArrCreate3, iArrCreate6, iArrCreate5);
        X25519Field.carry(iArrCreate5);
        X25519Field.mul(iArrCreate4, iArrCreate5, pointExt.x);
        X25519Field.mul(iArrCreate6, iArrCreate7, pointExt.y);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointExt.z);
        X25519Field.mul(iArrCreate4, iArrCreate7, pointExt.t);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X25519Field.one(pointExt.z);
        X25519Field.mul(pointExt.x, pointExt.y, pointExt.t);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 8 * 3 * 10;
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            Nat.cmov(10, i5, precompBase, i3, pointPrecomp.ypx_h, 0);
            int i6 = i3 + 10;
            Nat.cmov(10, i5, precompBase, i6, pointPrecomp.ymx_h, 0);
            int i7 = i6 + 10;
            Nat.cmov(10, i5, precompBase, i7, pointPrecomp.xyd, 0);
            i3 = i7 + 10;
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
        X25519Field.zero(pointExt.x);
        X25519Field.one(pointExt.y);
        X25519Field.one(pointExt.z);
        X25519Field.zero(pointExt.t);
    }

    public static synchronized void precompute() {
        if (precompBase != null) {
            return;
        }
        PointExt pointExt = new PointExt();
        X25519Field.copy(B_x, 0, pointExt.x, 0);
        X25519Field.copy(B_y, 0, pointExt.y, 0);
        pointExtendXY(pointExt);
        precompBaseTable = pointPrecompVar(pointExt, 32);
        precompBase = new int[1920];
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            PointExt[] pointExtArr = new PointExt[4];
            PointExt pointExt2 = new PointExt();
            pointSetNeutral(pointExt2);
            int i3 = 0;
            while (true) {
                if (i3 >= 4) {
                    break;
                }
                pointAddVar(true, pointExt, pointExt2);
                pointDouble(pointExt);
                pointExtArr[i3] = pointCopy(pointExt);
                for (int i4 = 1; i4 < 8; i4++) {
                    pointDouble(pointExt);
                }
                i3++;
            }
            PointExt[] pointExtArr2 = new PointExt[8];
            pointExtArr2[0] = pointExt2;
            int i5 = 1;
            for (int i6 = 0; i6 < 3; i6++) {
                int i7 = 1 << i6;
                int i8 = 0;
                while (i8 < i7) {
                    pointExtArr2[i5] = pointCopy(pointExtArr2[i5 - i7]);
                    pointAddVar(false, pointExtArr[i6], pointExtArr2[i5]);
                    i8++;
                    i5++;
                }
            }
            for (int i9 = 0; i9 < 8; i9++) {
                PointExt pointExt3 = pointExtArr2[i9];
                int[] iArrCreate = X25519Field.create();
                int[] iArrCreate2 = X25519Field.create();
                X25519Field.add(pointExt3.z, pointExt3.z, iArrCreate);
                X25519Field.inv(iArrCreate, iArrCreate2);
                X25519Field.mul(pointExt3.x, iArrCreate2, iArrCreate);
                X25519Field.mul(pointExt3.y, iArrCreate2, iArrCreate2);
                PointPrecomp pointPrecomp = new PointPrecomp();
                X25519Field.apm(iArrCreate2, iArrCreate, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                X25519Field.mul(iArrCreate, iArrCreate2, pointPrecomp.xyd);
                X25519Field.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                X25519Field.normalize(pointPrecomp.ypx_h);
                X25519Field.normalize(pointPrecomp.ymx_h);
                X25519Field.copy(pointPrecomp.ypx_h, 0, precompBase, i);
                int i10 = i + 10;
                X25519Field.copy(pointPrecomp.ymx_h, 0, precompBase, i10);
                int i11 = i10 + 10;
                X25519Field.copy(pointPrecomp.xyd, 0, precompBase, i11);
                i = i11 + 10;
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        bArr2[31] = (byte) (bArr2[31] & 127);
        bArr2[31] = (byte) (bArr2[31] | 64);
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
        long j = ((long) bArr[63]) & 255;
        long j2 = jDecode249 + (jDecode329 >> 28);
        long j3 = jDecode329 & M28L;
        long j4 = jDecode325 - (j2 * (-50998291));
        long j5 = (jDecode245 - (j * (-50998291))) - (j2 * 19280294);
        long j6 = (jDecode326 - (j * 19280294)) - (j2 * 127719000);
        long j7 = (jDecode246 - (j * 127719000)) - (j2 * (-6428113));
        long j8 = (jDecode327 - (j * (-6428113))) - (j2 * 5343);
        long j9 = j7 - (j3 * 5343);
        long j10 = jDecode248 + (jDecode328 >> 28);
        long j11 = jDecode328 & M28L;
        long j12 = (j6 - (j3 * (-6428113))) - (j10 * 5343);
        long j13 = ((jDecode244 - (j3 * (-50998291))) - (j10 * 19280294)) - (j11 * 127719000);
        long j14 = ((j5 - (j3 * 127719000)) - (j10 * (-6428113))) - (j11 * 5343);
        long j15 = (jDecode247 - (j * 5343)) + (j8 >> 28);
        long j16 = (j8 & M28L) + (j9 >> 28);
        long j17 = jDecode242 - (j16 * (-50998291));
        long j18 = (jDecode323 - (j15 * (-50998291))) - (j16 * 19280294);
        long j19 = ((jDecode243 - (j11 * (-50998291))) - (j15 * 19280294)) - (j16 * 127719000);
        long j20 = (((jDecode324 - (j10 * (-50998291))) - (j11 * 19280294)) - (j15 * 127719000)) - (j16 * (-6428113));
        long j21 = (j13 - (j15 * (-6428113))) - (j16 * 5343);
        long j22 = (j9 & M28L) + (j12 >> 28);
        long j23 = j12 & M28L;
        long j24 = jDecode322 - (j22 * (-50998291));
        long j25 = j17 - (j22 * 19280294);
        long j26 = j18 - (j22 * 127719000);
        long j27 = j19 - (j22 * (-6428113));
        long j28 = j20 - (j22 * 5343);
        long j29 = j23 + (j14 >> 28);
        long j30 = j14 & M28L;
        long j31 = jDecode24 - (j29 * (-50998291));
        long j32 = j24 - (j29 * 19280294);
        long j33 = j25 - (j29 * 127719000);
        long j34 = j26 - (j29 * (-6428113));
        long j35 = j27 - (j29 * 5343);
        long j36 = ((((j4 - (j3 * 19280294)) - (j10 * 127719000)) - (j11 * (-6428113))) - (j15 * 5343)) + (j21 >> 28);
        long j37 = j21 & M28L;
        long j38 = j36 & M28L;
        long j39 = j38 >>> 27;
        long j40 = j30 + (j36 >> 28) + j39;
        long j41 = jDecode32 - (j40 * (-50998291));
        long j42 = (j31 - (j40 * 19280294)) + (j41 >> 28);
        long j43 = j41 & M28L;
        long j44 = (j32 - (j40 * 127719000)) + (j42 >> 28);
        long j45 = j42 & M28L;
        long j46 = (j33 - (j40 * (-6428113))) + (j44 >> 28);
        long j47 = j44 & M28L;
        long j48 = (j34 - (j40 * 5343)) + (j46 >> 28);
        long j49 = j46 & M28L;
        long j50 = j35 + (j48 >> 28);
        long j51 = j48 & M28L;
        long j52 = j28 + (j50 >> 28);
        long j53 = j50 & M28L;
        long j54 = j37 + (j52 >> 28);
        long j55 = j52 & M28L;
        long j56 = j38 + (j54 >> 28);
        long j57 = j54 & M28L;
        long j58 = j56 >> 28;
        long j59 = j56 & M28L;
        long j60 = j58 - j39;
        long j61 = j43 + (j60 & (-50998291));
        long j62 = j45 + (j60 & 19280294) + (j61 >> 28);
        long j63 = j61 & M28L;
        long j64 = j47 + (j60 & 127719000) + (j62 >> 28);
        long j65 = j62 & M28L;
        long j66 = j49 + (j60 & (-6428113)) + (j64 >> 28);
        long j67 = j64 & M28L;
        long j68 = j51 + (j60 & 5343) + (j66 >> 28);
        long j69 = j66 & M28L;
        long j70 = j53 + (j68 >> 28);
        long j71 = j68 & M28L;
        long j72 = j55 + (j70 >> 28);
        long j73 = j70 & M28L;
        long j74 = j57 + (j72 >> 28);
        long j75 = j72 & M28L;
        long j76 = j59 + (j74 >> 28);
        long j77 = j74 & M28L;
        byte[] bArr2 = new byte[32];
        encode56(j63 | (j65 << 28), bArr2, 0);
        encode56((j69 << 28) | j67, bArr2, 7);
        encode56(j71 | (j73 << 28), bArr2, 14);
        encode56(j75 | (j77 << 28), bArr2, 21);
        encode32((int) j76, bArr2, 28);
        return bArr2;
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        pointSetNeutral(pointExt);
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i = 0; i < 8; i++) {
            iArr[i] = Interleave.shuffle2(iArr[i]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        int i2 = 28;
        while (true) {
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = iArr[i3] >>> i2;
                int i5 = (i4 >>> 3) & 1;
                pointLookup(i3, (i4 ^ (-i5)) & 7, pointPrecomp);
                X25519Field.cswap(i5, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                X25519Field.cnegate(i5, pointPrecomp.xyd);
                pointAddPrecomp(pointPrecomp, pointExt);
            }
            i2 -= 4;
            if (i2 < 0) {
                return;
            } else {
                pointDouble(pointExt);
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
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
        int i = 255;
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

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        SHA512Digest sHA512Digest = new SHA512Digest();
        byte[] bArr4 = new byte[sHA512Digest.getDigestSize()];
        sHA512Digest.update(bArr, i, 32);
        sHA512Digest.doFinal(bArr4, 0);
        byte[] bArr5 = new byte[32];
        pruneScalar(bArr4, 0, bArr5);
        byte[] bArr6 = new byte[32];
        scalarMultBaseEncoded(bArr5, bArr6, 0);
        implSign(sHA512Digest, bArr4, bArr5, bArr6, 0, bArr2, i2, i3, bArr3, i4);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        SHA512Digest sHA512Digest = new SHA512Digest();
        byte[] bArr5 = new byte[sHA512Digest.getDigestSize()];
        sHA512Digest.update(bArr, i, 32);
        sHA512Digest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        implSign(sHA512Digest, bArr5, bArr6, bArr2, i2, bArr3, i3, i4, bArr4, i5);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        int i5 = i + 32;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i5);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, i5, i + 64);
        if (!checkPointVar(bArrCopyOfRange) || !checkScalarVar(bArrCopyOfRange2)) {
            return false;
        }
        PointExt pointExt = new PointExt();
        if (!decodePointVar(bArr2, i2, true, pointExt)) {
            return false;
        }
        SHA512Digest sHA512Digest = new SHA512Digest();
        byte[] bArr4 = new byte[sHA512Digest.getDigestSize()];
        sHA512Digest.update(bArrCopyOfRange, 0, 32);
        sHA512Digest.update(bArr2, i2, 32);
        sHA512Digest.update(bArr3, i3, i4);
        sHA512Digest.doFinal(bArr4, 0);
        byte[] bArrReduceScalar = reduceScalar(bArr4);
        int[] iArr = new int[8];
        decodeScalar(bArrCopyOfRange2, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArrReduceScalar, 0, iArr2);
        PointExt pointExt2 = new PointExt();
        scalarMultStraussVar(iArr, iArr2, pointExt, pointExt2);
        byte[] bArr5 = new byte[32];
        encodePoint(pointExt2, bArr5, 0);
        return Arrays.areEqual(bArr5, bArrCopyOfRange);
    }
}

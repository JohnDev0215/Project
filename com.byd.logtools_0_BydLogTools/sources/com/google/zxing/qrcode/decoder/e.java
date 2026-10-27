package com.google.zxing.qrcode.decoder;

/* JADX INFO: compiled from: FormatInformation.java */
/* JADX INFO: loaded from: classes.dex */
final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[][] f4495a = {new int[]{21522, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ErrorCorrectionLevel f4496b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final byte f4497c;

    private e(int i) {
        this.f4496b = ErrorCorrectionLevel.forBits((i >> 3) & 3);
        this.f4497c = (byte) (i & 7);
    }

    static e a(int i, int i2) {
        e eVarB = b(i, i2);
        return eVarB != null ? eVarB : b(i ^ 21522, i2 ^ 21522);
    }

    private static e b(int i, int i2) {
        int iE;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        for (int[] iArr : f4495a) {
            int i5 = iArr[0];
            if (i5 == i || i5 == i2) {
                return new e(iArr[1]);
            }
            int iE2 = e(i, i5);
            if (iE2 < i3) {
                i4 = iArr[1];
                i3 = iE2;
            }
            if (i != i2 && (iE = e(i2, i5)) < i3) {
                i4 = iArr[1];
                i3 = iE;
            }
        }
        if (i3 <= 3) {
            return new e(i4);
        }
        return null;
    }

    static int e(int i, int i2) {
        return Integer.bitCount(i ^ i2);
    }

    byte c() {
        return this.f4497c;
    }

    ErrorCorrectionLevel d() {
        return this.f4496b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f4496b == eVar.f4496b && this.f4497c == eVar.f4497c;
    }

    public int hashCode() {
        return (this.f4496b.ordinal() << 3) | this.f4497c;
    }
}

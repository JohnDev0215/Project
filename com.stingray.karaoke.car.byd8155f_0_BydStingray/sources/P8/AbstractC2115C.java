package p8;

/* JADX INFO: renamed from: p8.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2115C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2113A f20946a = new C2113A();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2114B f20947b = new C2114B();

    public static int a(int i7, int i10) {
        if (i7 > -12 || i10 > -65) {
            return -1;
        }
        return i7 ^ (i10 << 8);
    }

    public static int b(int i7, int i10, byte[] bArr) {
        byte b10 = bArr[i7 - 1];
        int i11 = i10 - i7;
        if (i11 == 0) {
            if (b10 > -12) {
                return -1;
            }
            return b10;
        }
        if (i11 == 1) {
            return a(b10, bArr[i7]);
        }
        if (i11 != 2) {
            throw new AssertionError();
        }
        byte b11 = bArr[i7];
        byte b12 = bArr[i7 + 1];
        if (b10 > -12 || b11 > -65 || b12 > -65) {
            return -1;
        }
        return ((b11 << 8) ^ b10) ^ (b12 << 16);
    }

    public static int c(int i7, int i10, byte[] bArr) {
        while (i7 < i10 && bArr[i7] >= 0) {
            i7++;
        }
        if (i7 >= i10) {
            return 0;
        }
        while (i7 < i10) {
            int i11 = i7 + 1;
            byte b10 = bArr[i7];
            if (b10 < 0) {
                if (b10 < -32) {
                    if (i11 >= i10) {
                        return b10;
                    }
                    if (b10 >= -62) {
                        i7 += 2;
                        if (bArr[i11] > -65) {
                        }
                    }
                    return -1;
                }
                if (b10 < -16) {
                    if (i11 >= i10 - 1) {
                        return b(i11, i10, bArr);
                    }
                    int i12 = i7 + 2;
                    byte b11 = bArr[i11];
                    if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                        i7 += 3;
                        if (bArr[i12] > -65) {
                        }
                    }
                    return -1;
                }
                if (i11 >= i10 - 2) {
                    return b(i11, i10, bArr);
                }
                int i13 = i7 + 2;
                byte b12 = bArr[i11];
                if (b12 <= -65) {
                    if ((((b12 + 112) + (b10 << 28)) >> 30) == 0) {
                        int i14 = i7 + 3;
                        if (bArr[i13] <= -65) {
                            i7 += 4;
                            if (bArr[i14] > -65) {
                            }
                        }
                    }
                }
                return -1;
            }
            i7 = i11;
        }
        return 0;
    }
}

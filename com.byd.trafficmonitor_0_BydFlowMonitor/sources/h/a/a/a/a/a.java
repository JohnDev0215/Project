package h.a.a.a.a;

import h.a.a.a.a.b;
import org.apache.commons.codec.CodecPolicy;

/* JADX INFO: compiled from: Base64.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b {
    public static final byte[] j = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public static final byte[] k = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    public static final byte[] l = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    public final byte[] m;
    public final byte[] n;
    public final byte[] o;
    public final int p;
    public final int q;

    public a(boolean z) {
        this(76, b.f5511b, z);
    }

    public static byte[] n(byte[] bArr, boolean z) {
        return o(bArr, z, false);
    }

    public static byte[] o(byte[] bArr, boolean z, boolean z2) {
        return p(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] p(byte[] bArr, boolean z, boolean z2, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = z ? new a(z2) : new a(0, b.f5511b, z2);
        long j2 = aVar.j(bArr);
        if (j2 <= i2) {
            return aVar.f(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + j2 + ") than the specified maximum size of " + i2);
    }

    public static String q(byte[] bArr) {
        return c.b(n(bArr, false));
    }

    @Override // h.a.a.a.a.b
    public void e(byte[] bArr, int i2, int i3, b.a aVar) {
        if (aVar.f5524f) {
            return;
        }
        if (i3 >= 0) {
            int i4 = 0;
            while (i4 < i3) {
                byte[] bArrH = h(this.q, aVar);
                int i5 = (aVar.f5526h + 1) % 3;
                aVar.f5526h = i5;
                int i6 = i2 + 1;
                int i7 = bArr[i2];
                if (i7 < 0) {
                    i7 += 256;
                }
                int i8 = (aVar.f5519a << 8) + i7;
                aVar.f5519a = i8;
                if (i5 == 0) {
                    int i9 = aVar.f5522d;
                    int i10 = i9 + 1;
                    aVar.f5522d = i10;
                    byte[] bArr2 = this.m;
                    bArrH[i9] = bArr2[(i8 >> 18) & 63];
                    int i11 = i10 + 1;
                    aVar.f5522d = i11;
                    bArrH[i10] = bArr2[(i8 >> 12) & 63];
                    int i12 = i11 + 1;
                    aVar.f5522d = i12;
                    bArrH[i11] = bArr2[(i8 >> 6) & 63];
                    int i13 = i12 + 1;
                    aVar.f5522d = i13;
                    bArrH[i12] = bArr2[i8 & 63];
                    int i14 = aVar.f5525g + 4;
                    aVar.f5525g = i14;
                    int i15 = this.f5516g;
                    if (i15 > 0 && i15 <= i14) {
                        byte[] bArr3 = this.o;
                        System.arraycopy(bArr3, 0, bArrH, i13, bArr3.length);
                        aVar.f5522d += this.o.length;
                        aVar.f5525g = 0;
                    }
                }
                i4++;
                i2 = i6;
            }
            return;
        }
        aVar.f5524f = true;
        if (aVar.f5526h == 0 && this.f5516g == 0) {
            return;
        }
        byte[] bArrH2 = h(this.q, aVar);
        int i16 = aVar.f5522d;
        int i17 = aVar.f5526h;
        if (i17 != 0) {
            if (i17 == 1) {
                int i18 = i16 + 1;
                aVar.f5522d = i18;
                byte[] bArr4 = this.m;
                int i19 = aVar.f5519a;
                bArrH2[i16] = bArr4[(i19 >> 2) & 63];
                int i20 = i18 + 1;
                aVar.f5522d = i20;
                bArrH2[i18] = bArr4[(i19 << 4) & 63];
                if (bArr4 == j) {
                    int i21 = i20 + 1;
                    aVar.f5522d = i21;
                    byte b2 = this.f5513d;
                    bArrH2[i20] = b2;
                    aVar.f5522d = i21 + 1;
                    bArrH2[i21] = b2;
                }
            } else {
                if (i17 != 2) {
                    throw new IllegalStateException("Impossible modulus " + aVar.f5526h);
                }
                int i22 = i16 + 1;
                aVar.f5522d = i22;
                byte[] bArr5 = this.m;
                int i23 = aVar.f5519a;
                bArrH2[i16] = bArr5[(i23 >> 10) & 63];
                int i24 = i22 + 1;
                aVar.f5522d = i24;
                bArrH2[i22] = bArr5[(i23 >> 4) & 63];
                int i25 = i24 + 1;
                aVar.f5522d = i25;
                bArrH2[i24] = bArr5[(i23 << 2) & 63];
                if (bArr5 == j) {
                    aVar.f5522d = i25 + 1;
                    bArrH2[i25] = this.f5513d;
                }
            }
        }
        int i26 = aVar.f5525g;
        int i27 = aVar.f5522d;
        int i28 = i26 + (i27 - i16);
        aVar.f5525g = i28;
        if (this.f5516g <= 0 || i28 <= 0) {
            return;
        }
        byte[] bArr6 = this.o;
        System.arraycopy(bArr6, 0, bArrH2, i27, bArr6.length);
        aVar.f5522d += this.o.length;
    }

    @Override // h.a.a.a.a.b
    public boolean k(byte b2) {
        if (b2 >= 0) {
            byte[] bArr = this.n;
            if (b2 < bArr.length && bArr[b2] != -1) {
                return true;
            }
        }
        return false;
    }

    public a(int i2, byte[] bArr, boolean z) {
        this(i2, bArr, z, b.f5510a);
    }

    public a(int i2, byte[] bArr, boolean z, CodecPolicy codecPolicy) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length, (byte) 61, codecPolicy);
        this.n = l;
        if (bArr != null) {
            if (c(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + c.c(bArr) + "]");
            }
            if (i2 > 0) {
                this.q = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.o = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.q = 4;
                this.o = null;
            }
        } else {
            this.q = 4;
            this.o = null;
        }
        this.p = this.q - 1;
        this.m = z ? k : j;
    }
}

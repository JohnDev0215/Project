package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class e0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static byte[] f4461a = {-39, 120, -7, -60, 25, -35, -75, -19, 40, -23, -3, 121, 74, -96, -40, -99, -58, 126, 55, -125, 43, 118, 83, -114, 98, 76, 100, -120, 68, -117, -5, -94, 23, -102, 89, -11, -121, -77, 79, 19, 97, 69, 109, -115, 9, -127, 125, 50, -67, -113, 64, -21, -122, -73, 123, 11, -16, -107, 33, 34, 92, 107, 78, -126, 84, -42, 101, -109, -50, 96, -78, 28, 115, 86, -64, 20, -89, -116, -15, -36, 18, 117, -54, 31, 59, -66, -28, -47, 66, 61, -44, 48, -93, 60, -74, 38, 111, -65, 14, -38, 70, 105, 7, 87, 39, -14, 29, -101, -68, -108, 67, 3, -8, 17, -57, -10, -112, -17, 62, -25, 6, -61, -43, 47, -56, 102, 30, -41, 8, -24, -22, -34, -128, 82, -18, -9, -124, -86, 114, -84, 53, 77, 106, 42, -106, 26, -46, 113, 90, 21, 73, 116, 75, -97, -48, 94, 4, 24, -92, -20, -62, -32, 65, 110, 15, 81, -53, -52, 36, -111, -81, 80, -95, -12, 112, 57, -103, 124, 58, -123, 35, -72, -76, 122, -4, 2, 54, 91, 37, 85, -105, 49, 45, 93, -6, -104, -29, -118, -110, -82, 5, -33, 41, 16, 103, 108, -70, -55, -45, 0, -26, -49, -31, -98, -88, 44, 99, 22, 1, 63, 88, -30, -119, -87, 13, 56, 52, 27, -85, 51, -1, -80, -69, 72, 12, 95, -71, -79, -51, 46, -59, -13, -37, 71, -27, -91, -100, 119, 10, -90, 32, 104, -2, 127, -63, -83};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4462b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4463c;

    @Override // g.a.b.e
    public final int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.f4462b == null) {
            throw new IllegalStateException("RC2 engine not initialised");
        }
        if (i2 + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.f4463c) {
            d(bArr, i2, bArr2, i3);
            return 8;
        }
        c(bArr, i2, bArr2, i3);
        return 8;
    }

    @Override // g.a.b.e
    public int b() {
        return 8;
    }

    public final void c(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iF = ((bArr[i2 + 7] & 255) << 8) + (bArr[i2 + 6] & 255);
        int iF2 = ((bArr[i2 + 5] & 255) << 8) + (bArr[i2 + 4] & 255);
        int iF3 = ((bArr[i2 + 3] & 255) << 8) + (bArr[i2 + 2] & 255);
        int iF4 = ((bArr[i2 + 1] & 255) << 8) + (bArr[i2 + 0] & 255);
        for (int i4 = 60; i4 >= 44; i4 -= 4) {
            iF = f(iF, 11) - ((((~iF2) & iF4) + (iF3 & iF2)) + this.f4462b[i4 + 3]);
            iF2 = f(iF2, 13) - ((((~iF3) & iF) + (iF4 & iF3)) + this.f4462b[i4 + 2]);
            iF3 = f(iF3, 14) - ((((~iF4) & iF2) + (iF & iF4)) + this.f4462b[i4 + 1]);
            iF4 = f(iF4, 15) - ((((~iF) & iF3) + (iF2 & iF)) + this.f4462b[i4]);
        }
        int[] iArr = this.f4462b;
        int iF5 = iF - iArr[iF2 & 63];
        int iF6 = iF2 - iArr[iF3 & 63];
        int iF7 = iF3 - iArr[iF4 & 63];
        int iF8 = iF4 - iArr[iF5 & 63];
        for (int i5 = 40; i5 >= 20; i5 -= 4) {
            iF5 = f(iF5, 11) - ((((~iF6) & iF8) + (iF7 & iF6)) + this.f4462b[i5 + 3]);
            iF6 = f(iF6, 13) - ((((~iF7) & iF5) + (iF8 & iF7)) + this.f4462b[i5 + 2]);
            iF7 = f(iF7, 14) - ((((~iF8) & iF6) + (iF5 & iF8)) + this.f4462b[i5 + 1]);
            iF8 = f(iF8, 15) - ((((~iF5) & iF7) + (iF6 & iF5)) + this.f4462b[i5]);
        }
        int[] iArr2 = this.f4462b;
        int iF9 = iF5 - iArr2[iF6 & 63];
        int iF10 = iF6 - iArr2[iF7 & 63];
        int iF11 = iF7 - iArr2[iF8 & 63];
        int iF12 = iF8 - iArr2[iF9 & 63];
        for (int i6 = 16; i6 >= 0; i6 -= 4) {
            iF9 = f(iF9, 11) - ((((~iF10) & iF12) + (iF11 & iF10)) + this.f4462b[i6 + 3]);
            iF10 = f(iF10, 13) - ((((~iF11) & iF9) + (iF12 & iF11)) + this.f4462b[i6 + 2]);
            iF11 = f(iF11, 14) - ((((~iF12) & iF10) + (iF9 & iF12)) + this.f4462b[i6 + 1]);
            iF12 = f(iF12, 15) - ((((~iF9) & iF11) + (iF10 & iF9)) + this.f4462b[i6]);
        }
        bArr2[i3 + 0] = (byte) iF12;
        bArr2[i3 + 1] = (byte) (iF12 >> 8);
        bArr2[i3 + 2] = (byte) iF11;
        bArr2[i3 + 3] = (byte) (iF11 >> 8);
        bArr2[i3 + 4] = (byte) iF10;
        bArr2[i3 + 5] = (byte) (iF10 >> 8);
        bArr2[i3 + 6] = (byte) iF9;
        bArr2[i3 + 7] = (byte) (iF9 >> 8);
    }

    public final void d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iF = ((bArr[i2 + 7] & 255) << 8) + (bArr[i2 + 6] & 255);
        int iF2 = ((bArr[i2 + 5] & 255) << 8) + (bArr[i2 + 4] & 255);
        int iF3 = ((bArr[i2 + 3] & 255) << 8) + (bArr[i2 + 2] & 255);
        int iF4 = ((bArr[i2 + 1] & 255) << 8) + (bArr[i2 + 0] & 255);
        for (int i4 = 0; i4 <= 16; i4 += 4) {
            iF4 = f(iF4 + ((~iF) & iF3) + (iF2 & iF) + this.f4462b[i4], 1);
            iF3 = f(iF3 + ((~iF4) & iF2) + (iF & iF4) + this.f4462b[i4 + 1], 2);
            iF2 = f(iF2 + ((~iF3) & iF) + (iF4 & iF3) + this.f4462b[i4 + 2], 3);
            iF = f(iF + ((~iF2) & iF4) + (iF3 & iF2) + this.f4462b[i4 + 3], 5);
        }
        int[] iArr = this.f4462b;
        int iF5 = iF4 + iArr[iF & 63];
        int iF6 = iF3 + iArr[iF5 & 63];
        int iF7 = iF2 + iArr[iF6 & 63];
        int iF8 = iF + iArr[iF7 & 63];
        for (int i5 = 20; i5 <= 40; i5 += 4) {
            iF5 = f(iF5 + ((~iF8) & iF6) + (iF7 & iF8) + this.f4462b[i5], 1);
            iF6 = f(iF6 + ((~iF5) & iF7) + (iF8 & iF5) + this.f4462b[i5 + 1], 2);
            iF7 = f(iF7 + ((~iF6) & iF8) + (iF5 & iF6) + this.f4462b[i5 + 2], 3);
            iF8 = f(iF8 + ((~iF7) & iF5) + (iF6 & iF7) + this.f4462b[i5 + 3], 5);
        }
        int[] iArr2 = this.f4462b;
        int iF9 = iF5 + iArr2[iF8 & 63];
        int iF10 = iF6 + iArr2[iF9 & 63];
        int iF11 = iF7 + iArr2[iF10 & 63];
        int iF12 = iF8 + iArr2[iF11 & 63];
        for (int i6 = 44; i6 < 64; i6 += 4) {
            iF9 = f(iF9 + ((~iF12) & iF10) + (iF11 & iF12) + this.f4462b[i6], 1);
            iF10 = f(iF10 + ((~iF9) & iF11) + (iF12 & iF9) + this.f4462b[i6 + 1], 2);
            iF11 = f(iF11 + ((~iF10) & iF12) + (iF9 & iF10) + this.f4462b[i6 + 2], 3);
            iF12 = f(iF12 + ((~iF11) & iF9) + (iF10 & iF11) + this.f4462b[i6 + 3], 5);
        }
        bArr2[i3 + 0] = (byte) iF9;
        bArr2[i3 + 1] = (byte) (iF9 >> 8);
        bArr2[i3 + 2] = (byte) iF10;
        bArr2[i3 + 3] = (byte) (iF10 >> 8);
        bArr2[i3 + 4] = (byte) iF11;
        bArr2[i3 + 5] = (byte) (iF11 >> 8);
        bArr2[i3 + 6] = (byte) iF12;
        bArr2[i3 + 7] = (byte) (iF12 >> 8);
    }

    public final int[] e(byte[] bArr, int i2) {
        int[] iArr = new int[128];
        for (int i3 = 0; i3 != bArr.length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        int length = bArr.length;
        if (length < 128) {
            int i4 = iArr[length - 1];
            int i5 = 0;
            while (true) {
                int i6 = i5 + 1;
                i4 = f4461a[(i4 + iArr[i5]) & 255] & 255;
                int i7 = length + 1;
                iArr[length] = i4;
                if (i7 >= 128) {
                    break;
                }
                length = i7;
                i5 = i6;
            }
        }
        int i8 = (i2 + 7) >> 3;
        int i9 = 128 - i8;
        int i10 = f4461a[(255 >> ((-i2) & 7)) & iArr[i9]] & 255;
        iArr[i9] = i10;
        for (int i11 = i9 - 1; i11 >= 0; i11--) {
            i10 = f4461a[i10 ^ iArr[i11 + i8]] & 255;
            iArr[i11] = i10;
        }
        int[] iArr2 = new int[64];
        for (int i12 = 0; i12 != 64; i12++) {
            int i13 = i12 * 2;
            iArr2[i12] = iArr[i13] + (iArr[i13 + 1] << 8);
        }
        return iArr2;
    }

    public final int f(int i2, int i3) {
        int i4 = i2 & 65535;
        return (i4 >> (16 - i3)) | (i4 << i3);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        this.f4463c = z;
        if (iVar instanceof g.a.b.j0.a1) {
            g.a.b.j0.a1 a1Var = (g.a.b.j0.a1) iVar;
            this.f4462b = e(a1Var.a(), a1Var.b());
        } else if (iVar instanceof g.a.b.j0.s0) {
            byte[] bArrA = ((g.a.b.j0.s0) iVar).a();
            this.f4462b = e(bArrA, bArrA.length * 8);
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC2 init - " + iVar.getClass().getName());
        }
    }

    @Override // g.a.b.e
    public void reset() {
    }
}

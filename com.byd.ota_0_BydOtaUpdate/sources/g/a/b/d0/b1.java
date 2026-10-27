package g.a.b.d0;

import android.R;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public final class b1 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[][] f4425a = {new byte[]{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, -128, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 37, 11, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, 127, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, 15, 0, 111, -99, 54, 66, 74, 94, -63, -32}, new byte[]{117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, -68, -99, 109, -63, -79, 14, -128, 93, -46, -43, -96, -124, 7, 20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, -114, 120, 92, 88, 25, -115, -27, -104, 87, 103, 127, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f4431g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f4432h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4426b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f4427c = new int[256];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4428d = new int[256];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4429e = new int[256];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f4430f = new int[256];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4433i = 0;
    public byte[] j = null;

    public b1() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i2 = 0; i2 < 256; i2++) {
            byte[][] bArr = f4425a;
            int i3 = bArr[0][i2] & 255;
            iArr[0] = i3;
            iArr2[0] = j(i3) & 255;
            iArr3[0] = k(i3) & 255;
            int i4 = bArr[1][i2] & 255;
            iArr[1] = i4;
            iArr2[1] = j(i4) & 255;
            iArr3[1] = k(i4) & 255;
            this.f4427c[i2] = iArr[1] | (iArr2[1] << 8) | (iArr3[1] << 16) | (iArr3[1] << 24);
            this.f4428d[i2] = iArr3[0] | (iArr3[0] << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            this.f4429e[i2] = (iArr3[1] << 24) | iArr2[1] | (iArr3[1] << 8) | (iArr[1] << 16);
            this.f4430f[i2] = iArr2[0] | (iArr[0] << 8) | (iArr3[0] << 16) | (iArr2[0] << 24);
        }
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.j == null) {
            throw new IllegalStateException("Twofish not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.f4426b) {
            s(bArr, i2, bArr2, i3);
            return 16;
        }
        r(bArr, i2, bArr2, i3);
        return 16;
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final void c(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 3] = (byte) (i2 >> 24);
    }

    public final int d(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public final int e(int i2, int[] iArr) {
        int i3;
        int i4;
        int iN = n(i2);
        int iO = o(i2);
        int iP = p(i2);
        int iQ = q(i2);
        int i5 = iArr[0];
        int i6 = iArr[1];
        int i7 = iArr[2];
        int i8 = iArr[3];
        int i9 = this.f4433i & 3;
        if (i9 != 0) {
            if (i9 == 1) {
                int[] iArr2 = this.f4427c;
                byte[][] bArr = f4425a;
                i3 = (iArr2[(bArr[0][iN] & 255) ^ n(i5)] ^ this.f4428d[(bArr[0][iO] & 255) ^ o(i5)]) ^ this.f4429e[(bArr[1][iP] & 255) ^ p(i5)];
                i4 = this.f4430f[(bArr[1][iQ] & 255) ^ q(i5)];
                return i3 ^ i4;
            }
            if (i9 != 2) {
                if (i9 != 3) {
                    return 0;
                }
            }
            int[] iArr3 = this.f4427c;
            byte[][] bArr2 = f4425a;
            i3 = (iArr3[(bArr2[0][(bArr2[0][iN] & 255) ^ n(i6)] & 255) ^ n(i5)] ^ this.f4428d[(bArr2[0][(bArr2[1][iO] & 255) ^ o(i6)] & 255) ^ o(i5)]) ^ this.f4429e[(bArr2[1][(bArr2[0][iP] & 255) ^ p(i6)] & 255) ^ p(i5)];
            i4 = this.f4430f[(bArr2[1][(bArr2[1][iQ] & 255) ^ q(i6)] & 255) ^ q(i5)];
            return i3 ^ i4;
        }
        byte[][] bArr3 = f4425a;
        iN = (bArr3[1][iN] & 255) ^ n(i8);
        iO = (bArr3[0][iO] & 255) ^ o(i8);
        iP = (bArr3[0][iP] & 255) ^ p(i8);
        iQ = (bArr3[1][iQ] & 255) ^ q(i8);
        byte[][] bArr4 = f4425a;
        iN = (bArr4[1][iN] & 255) ^ n(i7);
        iO = (bArr4[1][iO] & 255) ^ o(i7);
        iP = (bArr4[0][iP] & 255) ^ p(i7);
        iQ = (bArr4[0][iQ] & 255) ^ q(i7);
        int[] iArr32 = this.f4427c;
        byte[][] bArr22 = f4425a;
        i3 = (iArr32[(bArr22[0][(bArr22[0][iN] & 255) ^ n(i6)] & 255) ^ n(i5)] ^ this.f4428d[(bArr22[0][(bArr22[1][iO] & 255) ^ o(i6)] & 255) ^ o(i5)]) ^ this.f4429e[(bArr22[1][(bArr22[0][iP] & 255) ^ p(i6)] & 255) ^ p(i5)];
        i4 = this.f4430f[(bArr22[1][(bArr22[1][iQ] & 255) ^ q(i6)] & 255) ^ q(i5)];
        return i3 ^ i4;
    }

    public final int f(int i2) {
        int[] iArr = this.f4432h;
        return iArr[(((i2 >>> 24) & 255) * 2) + 513] ^ ((iArr[((i2 & 255) * 2) + 0] ^ iArr[(((i2 >>> 8) & 255) * 2) + 1]) ^ iArr[(((i2 >>> 16) & 255) * 2) + 512]);
    }

    public final int g(int i2) {
        int[] iArr = this.f4432h;
        return iArr[(((i2 >>> 16) & 255) * 2) + 513] ^ ((iArr[(((i2 >>> 24) & 255) * 2) + 0] ^ iArr[((i2 & 255) * 2) + 1]) ^ iArr[(((i2 >>> 8) & 255) * 2) + 512]);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "Twofish";
    }

    public final int h(int i2) {
        return ((i2 & 1) != 0 ? 180 : 0) ^ (i2 >> 1);
    }

    public final int i(int i2) {
        return ((i2 >> 2) ^ ((i2 & 2) != 0 ? 180 : 0)) ^ ((i2 & 1) != 0 ? 90 : 0);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (!(iVar instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + iVar.getClass().getName());
        }
        this.f4426b = z;
        byte[] bArrA = ((g.a.b.j0.s0) iVar).a();
        this.j = bArrA;
        this.f4433i = bArrA.length / 8;
        t(bArrA);
    }

    public final int j(int i2) {
        return i2 ^ i(i2);
    }

    public final int k(int i2) {
        return i(i2) ^ (h(i2) ^ i2);
    }

    public final int l(int i2, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            i3 = m(i3);
        }
        int iM = i2 ^ i3;
        for (int i5 = 0; i5 < 4; i5++) {
            iM = m(iM);
        }
        return iM;
    }

    public final int m(int i2) {
        int i3 = (i2 >>> 24) & 255;
        int i4 = ((i3 << 1) ^ ((i3 & 128) != 0 ? 333 : 0)) & 255;
        int i5 = ((i3 >>> 1) ^ ((i3 & 1) != 0 ? 166 : 0)) ^ i4;
        return ((((i2 << 8) ^ (i5 << 24)) ^ (i4 << 16)) ^ (i5 << 8)) ^ i3;
    }

    public final int n(int i2) {
        return i2 & 255;
    }

    public final int o(int i2) {
        return (i2 >>> 8) & 255;
    }

    public final int p(int i2) {
        return (i2 >>> 16) & 255;
    }

    public final int q(int i2) {
        return (i2 >>> 24) & 255;
    }

    public final void r(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iD = d(bArr, i2) ^ this.f4431g[4];
        int iD2 = d(bArr, i2 + 4) ^ this.f4431g[5];
        int iD3 = d(bArr, i2 + 8) ^ this.f4431g[6];
        int iD4 = d(bArr, i2 + 12) ^ this.f4431g[7];
        int i4 = 39;
        for (int i5 = 0; i5 < 16; i5 += 2) {
            int iF = f(iD);
            int iG = g(iD2);
            int[] iArr = this.f4431g;
            int i6 = i4 - 1;
            int i7 = iD4 ^ (((iG * 2) + iF) + iArr[i4]);
            int i8 = i6 - 1;
            iD3 = ((iD3 >>> 31) | (iD3 << 1)) ^ ((iF + iG) + iArr[i6]);
            iD4 = (i7 << 31) | (i7 >>> 1);
            int iF2 = f(iD3);
            int iG2 = g(iD4);
            int[] iArr2 = this.f4431g;
            int i9 = i8 - 1;
            int i10 = iD2 ^ (((iG2 * 2) + iF2) + iArr2[i8]);
            i4 = i9 - 1;
            iD = ((iD >>> 31) | (iD << 1)) ^ ((iF2 + iG2) + iArr2[i9]);
            iD2 = (i10 << 31) | (i10 >>> 1);
        }
        c(this.f4431g[0] ^ iD3, bArr2, i3);
        c(iD4 ^ this.f4431g[1], bArr2, i3 + 4);
        c(this.f4431g[2] ^ iD, bArr2, i3 + 8);
        c(this.f4431g[3] ^ iD2, bArr2, i3 + 12);
    }

    @Override // g.a.b.e
    public void reset() {
        byte[] bArr = this.j;
        if (bArr != null) {
            t(bArr);
        }
    }

    public final void s(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        int iD = d(bArr, i2) ^ this.f4431g[0];
        int iD2 = d(bArr, i2 + 4) ^ this.f4431g[1];
        int iD3 = d(bArr, i2 + 8) ^ this.f4431g[2];
        int iD4 = d(bArr, i2 + 12) ^ this.f4431g[3];
        int i5 = 8;
        while (i4 < 16) {
            int iF = f(iD);
            int iG = g(iD2);
            int[] iArr = this.f4431g;
            int i6 = i5 + 1;
            int i7 = iD3 ^ ((iF + iG) + iArr[i5]);
            iD3 = (i7 >>> 1) | (i7 << 31);
            int i8 = (iD4 >>> 31) | (iD4 << 1);
            int i9 = i6 + 1;
            iD4 = i8 ^ ((iF + (iG * 2)) + iArr[i6]);
            int iF2 = f(iD3);
            int iG2 = g(iD4);
            int[] iArr2 = this.f4431g;
            int i10 = i9 + 1;
            int i11 = iD ^ ((iF2 + iG2) + iArr2[i9]);
            iD = (i11 >>> 1) | (i11 << 31);
            i4 += 2;
            iD2 = ((iD2 << 1) | (iD2 >>> 31)) ^ ((iF2 + (iG2 * 2)) + iArr2[i10]);
            i5 = i10 + 1;
        }
        c(this.f4431g[4] ^ iD3, bArr2, i3);
        c(iD4 ^ this.f4431g[5], bArr2, i3 + 4);
        c(this.f4431g[6] ^ iD, bArr2, i3 + 8);
        c(this.f4431g[7] ^ iD2, bArr2, i3 + 12);
    }

    public final void t(byte[] bArr) {
        int iN;
        int iO;
        int iP;
        int iQ;
        int iP2;
        int iO2;
        int iN2;
        int iQ2;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.f4431g = new int[40];
        int i2 = this.f4433i;
        if (i2 < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        }
        if (i2 > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        }
        for (int i3 = 0; i3 < this.f4433i; i3++) {
            int i4 = i3 * 8;
            iArr[i3] = d(bArr, i4);
            iArr2[i3] = d(bArr, i4 + 4);
            iArr3[(this.f4433i - 1) - i3] = l(iArr[i3], iArr2[i3]);
        }
        for (int i5 = 0; i5 < 20; i5++) {
            int i6 = 33686018 * i5;
            int iE = e(i6, iArr);
            int iE2 = e(i6 + R.attr.cacheColorHint, iArr2);
            int i7 = (iE2 >>> 24) | (iE2 << 8);
            int i8 = iE + i7;
            int[] iArr4 = this.f4431g;
            int i9 = i5 * 2;
            iArr4[i9] = i8;
            int i10 = i8 + i7;
            iArr4[i9 + 1] = (i10 << 9) | (i10 >>> 23);
        }
        int i11 = iArr3[0];
        int i12 = iArr3[1];
        int i13 = 2;
        int i14 = iArr3[2];
        int i15 = iArr3[3];
        this.f4432h = new int[1024];
        int i16 = 0;
        while (i16 < 256) {
            int i17 = this.f4433i & 3;
            if (i17 != 0) {
                if (i17 == 1) {
                    int[] iArr5 = this.f4432h;
                    int i18 = i16 * 2;
                    int[] iArr6 = this.f4427c;
                    byte[][] bArr2 = f4425a;
                    iArr5[i18] = iArr6[(bArr2[0][i16] & 255) ^ n(i11)];
                    this.f4432h[i18 + 1] = this.f4428d[(bArr2[0][i16] & 255) ^ o(i11)];
                    this.f4432h[i18 + 512] = this.f4429e[(bArr2[1][i16] & 255) ^ p(i11)];
                    this.f4432h[i18 + 513] = this.f4430f[(bArr2[1][i16] & 255) ^ q(i11)];
                } else if (i17 == i13) {
                    iQ2 = i16;
                    iN2 = iQ2;
                    iO2 = iN2;
                    iP2 = iO2;
                    int[] iArr7 = this.f4432h;
                    int i19 = i16 * 2;
                    int[] iArr8 = this.f4427c;
                    byte[][] bArr3 = f4425a;
                    iArr7[i19] = iArr8[(bArr3[0][(bArr3[0][iN2] & 255) ^ n(i12)] & 255) ^ n(i11)];
                    this.f4432h[i19 + 1] = this.f4428d[(bArr3[0][(bArr3[1][iO2] & 255) ^ o(i12)] & 255) ^ o(i11)];
                    this.f4432h[i19 + 512] = this.f4429e[(bArr3[1][(bArr3[0][iP2] & 255) ^ p(i12)] & 255) ^ p(i11)];
                    this.f4432h[i19 + 513] = this.f4430f[(bArr3[1][(bArr3[1][iQ2] & 255) ^ q(i12)] & 255) ^ q(i11)];
                } else if (i17 == 3) {
                    iQ = i16;
                    iN = iQ;
                    iO = iN;
                    iP = iO;
                }
                i16++;
                i13 = 2;
            } else {
                byte[][] bArr4 = f4425a;
                iN = (bArr4[1][i16] & 255) ^ n(i15);
                iO = (bArr4[0][i16] & 255) ^ o(i15);
                iP = (bArr4[0][i16] & 255) ^ p(i15);
                iQ = (bArr4[1][i16] & 255) ^ q(i15);
            }
            byte[][] bArr5 = f4425a;
            iN2 = (bArr5[1][iN] & 255) ^ n(i14);
            iO2 = (bArr5[1][iO] & 255) ^ o(i14);
            iP2 = (bArr5[0][iP] & 255) ^ p(i14);
            iQ2 = (bArr5[0][iQ] & 255) ^ q(i14);
            int[] iArr72 = this.f4432h;
            int i192 = i16 * 2;
            int[] iArr82 = this.f4427c;
            byte[][] bArr32 = f4425a;
            iArr72[i192] = iArr82[(bArr32[0][(bArr32[0][iN2] & 255) ^ n(i12)] & 255) ^ n(i11)];
            this.f4432h[i192 + 1] = this.f4428d[(bArr32[0][(bArr32[1][iO2] & 255) ^ o(i12)] & 255) ^ o(i11)];
            this.f4432h[i192 + 512] = this.f4429e[(bArr32[1][(bArr32[0][iP2] & 255) ^ p(i12)] & 255) ^ p(i11)];
            this.f4432h[i192 + 513] = this.f4430f[(bArr32[1][(bArr32[1][iQ2] & 255) ^ q(i12)] & 255) ^ q(i11)];
            i16++;
            i13 = 2;
        }
    }
}

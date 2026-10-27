package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class w implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4626a = {-4, -18, -35, 17, -49, 110, 49, 22, -5, -60, -6, -38, 35, -59, 4, 77, -23, 119, -16, -37, -109, 46, -103, -70, 23, 54, -15, -69, 20, -51, 95, -63, -7, 24, 101, 90, -30, 92, -17, 33, -127, 28, 60, 66, -117, 1, -114, 79, 5, -124, 2, -82, -29, 106, -113, -96, 6, 11, -19, -104, 127, -44, -45, 31, -21, 52, 44, 81, -22, -56, 72, -85, -14, 42, 104, -94, -3, 58, -50, -52, -75, 112, 14, 86, 8, 12, 118, 18, -65, 114, 19, 71, -100, -73, 93, -121, 21, -95, -106, 41, 16, 123, -102, -57, -13, -111, 120, 111, -99, -98, -78, -79, 50, 117, 25, 61, -1, 53, -118, 126, 109, 84, -58, -128, -61, -67, 13, 87, -33, -11, 36, -87, 62, -88, 67, -55, -41, 121, -42, -10, 124, 34, -71, 3, -32, 15, -20, -34, 122, -108, -80, -68, -36, -24, 40, 80, 78, 51, 10, 74, -89, -105, 96, 115, 30, 0, 98, 68, 26, -72, 56, -126, 100, -97, 38, 65, -83, 69, 70, -110, 39, 94, 85, 47, -116, -93, -91, 125, 105, -43, -107, 59, 7, 88, -77, 64, -122, -84, 29, -9, 48, 55, 107, -28, -120, -39, -25, -119, -31, 27, -125, 73, 76, 63, -8, -2, -115, 83, -86, -112, -54, -40, -123, 97, 32, 113, 103, -92, 45, 43, 9, 91, -53, -101, 37, -48, -66, -27, 108, 82, 89, -90, 116, -46, -26, -12, -76, -64, -47, 102, -81, -62, 57, 75, 99, -74};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f4627b = {-91, 45, 50, -113, 14, 48, 56, -64, 84, -26, -98, 57, 85, 126, 82, -111, 100, 3, 87, 90, 28, 96, 7, 24, 33, 114, -88, -47, 41, -58, -92, 63, -32, 39, -115, 12, -126, -22, -82, -76, -102, 99, 73, -27, 66, -28, 21, -73, -56, 6, 112, -99, 65, 117, 25, -55, -86, -4, 77, -65, 42, 115, -124, -43, -61, -81, 43, -122, -89, -79, -78, 91, 70, -45, -97, -3, -44, 15, -100, 47, -101, 67, -17, -39, 121, -74, 83, 127, -63, -16, 35, -25, 37, 94, -75, 30, -94, -33, -90, -2, -84, 34, -7, -30, 74, -68, 53, -54, -18, 120, 5, 107, 81, -31, 89, -93, -14, 113, 86, 17, 106, -119, -108, 101, -116, -69, 119, 60, 123, 40, -85, -46, 49, -34, -60, 95, -52, -49, 118, 44, -72, -40, 46, 54, -37, 105, -77, 20, -107, -66, 98, -95, 59, 22, 102, -23, 92, 108, 109, -83, 55, 97, 75, -71, -29, -70, -15, -96, -123, -125, -38, 71, -59, -80, 51, -6, -106, 111, 110, -62, -10, 80, -1, 93, -87, -114, 23, 27, -105, 125, -20, 88, -9, 31, -5, 124, 9, 13, 122, 103, 69, -121, -36, -24, 79, 29, 78, 4, -21, -8, -13, 62, 61, -67, -118, -120, -35, -51, 11, 19, -104, 2, -109, -128, -112, -48, 36, 52, -53, -19, -12, -50, -103, 16, 68, 64, -110, 58, 1, 38, 18, 26, 72, 104, -11, -127, -117, -57, -42, 32, 10, 8, 0, 76, -41, 116};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4632g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f4628c = {-108, 32, -123, 16, -62, -64, 1, -5, 1, -64, -62, 16, -123, 32, -108, 1};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4629d = 32;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4630e = 32 / 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[][] f4631f = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[][] f4633h = m();

    public static byte[][] m() {
        byte[][] bArr = new byte[256][];
        for (int i2 = 0; i2 < 256; i2++) {
            bArr[i2] = new byte[256];
            for (int i3 = 0; i3 < 256; i3++) {
                bArr[i2][i3] = q((byte) i2, (byte) i3);
            }
        }
        return bArr;
    }

    public static byte q(byte b2, byte b3) {
        byte b4 = 0;
        for (byte b5 = 0; b5 < 8 && b2 != 0 && b3 != 0; b5 = (byte) (b5 + 1)) {
            if ((b3 & 1) != 0) {
                b4 = (byte) (b4 ^ b2);
            }
            byte b6 = (byte) (b2 & 128);
            b2 = (byte) (b2 << 1);
            if (b6 != 0) {
                b2 = (byte) (b2 ^ 195);
            }
            b3 = (byte) (b3 >> 1);
        }
        return b4;
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        if (this.f4631f == null) {
            throw new IllegalStateException("GOST3412_2015 engine not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        e(bArr, i2, bArr2, i3);
        return 16;
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final void c(byte[] bArr, int i2) {
        g.a.f.a.e(bArr);
        bArr[15] = (byte) i2;
        f(bArr);
    }

    public final void d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArrG = g(bArr, bArr2);
        j(bArrG, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.f4630e);
        System.arraycopy(bArrG, 0, bArr2, 0, this.f4630e);
    }

    public final void e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[][] bArr3;
        byte[] bArrR = new byte[16];
        System.arraycopy(bArr, i2, bArrR, 0, 16);
        int i4 = 9;
        if (this.f4632g) {
            for (int i5 = 0; i5 < 9; i5++) {
                bArrR = g.a.f.a.r(g(this.f4631f[i5], bArrR), 16);
            }
            j(bArrR, this.f4631f[9]);
        } else {
            while (true) {
                bArr3 = this.f4631f;
                if (i4 <= 0) {
                    break;
                }
                bArrR = g.a.f.a.r(k(bArr3[i4], bArrR), 16);
                i4--;
            }
            j(bArrR, bArr3[0]);
        }
        System.arraycopy(bArrR, 0, bArr2, i3, 16);
    }

    public final void f(byte[] bArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            h(bArr);
        }
    }

    public final byte[] g(byte[] bArr, byte[] bArr2) {
        byte[] bArrR = g.a.f.a.r(bArr, bArr.length);
        j(bArrR, bArr2);
        i(bArrR);
        f(bArrR);
        return bArrR;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    public final void h(byte[] bArr) {
        byte bR = r(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = bR;
    }

    public final void i(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = f4626a[s(bArr[i2])];
        }
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4632g = z;
            l(((g.a.b.j0.s0) iVar).a());
        } else {
            if (iVar == null) {
                return;
            }
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + iVar.getClass().getName());
        }
    }

    public final void j(byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    public final byte[] k(byte[] bArr, byte[] bArr2) {
        byte[] bArrR = g.a.f.a.r(bArr, bArr.length);
        j(bArrR, bArr2);
        n(bArrR);
        p(bArrR);
        return bArrR;
    }

    public final void l(byte[] bArr) {
        int i2;
        if (bArr.length != this.f4629d) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.f4631f = new byte[10][];
        for (int i3 = 0; i3 < 10; i3++) {
            this.f4631f[i3] = new byte[this.f4630e];
        }
        int i4 = this.f4630e;
        byte[] bArr2 = new byte[i4];
        byte[] bArr3 = new byte[i4];
        int i5 = 0;
        while (true) {
            i2 = this.f4630e;
            if (i5 >= i2) {
                break;
            }
            byte[][] bArr4 = this.f4631f;
            byte[] bArr5 = bArr4[0];
            byte b2 = bArr[i5];
            bArr2[i5] = b2;
            bArr5[i5] = b2;
            byte[] bArr6 = bArr4[1];
            byte b3 = bArr[i2 + i5];
            bArr3[i5] = b3;
            bArr6[i5] = b3;
            i5++;
        }
        byte[] bArr7 = new byte[i2];
        for (int i6 = 1; i6 < 5; i6++) {
            for (int i7 = 1; i7 <= 8; i7++) {
                c(bArr7, ((i6 - 1) * 8) + i7);
                d(bArr7, bArr2, bArr3);
            }
            int i8 = i6 * 2;
            System.arraycopy(bArr2, 0, this.f4631f[i8], 0, this.f4630e);
            System.arraycopy(bArr3, 0, this.f4631f[i8 + 1], 0, this.f4630e);
        }
    }

    public final void n(byte[] bArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            o(bArr);
        }
    }

    public final void o(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte bR = r(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = bR;
    }

    public final void p(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = f4627b[s(bArr[i2])];
        }
    }

    public final byte r(byte[] bArr) {
        byte b2 = bArr[15];
        for (int i2 = 14; i2 >= 0; i2--) {
            b2 = (byte) (b2 ^ this.f4633h[s(bArr[i2])][s(this.f4628c[i2])]);
        }
        return b2;
    }

    @Override // g.a.b.e
    public void reset() {
    }

    public final int s(byte b2) {
        return b2 & 255;
    }
}

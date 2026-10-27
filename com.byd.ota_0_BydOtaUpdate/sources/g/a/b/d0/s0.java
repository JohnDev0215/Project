package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class s0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4588a = {-42, -112, -23, -2, -52, -31, 61, -73, 22, -74, 20, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, -128, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, 15, 75, 112, 86, -99, 53, 30, 36, 14, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, 52, 26, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, 13, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, -35, -68, 127, 17, -39, 92, 65, 31, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, 18, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f4589b = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f4590c = {-1548633402, 1453994832, 1736282519, -1301273892};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f4591d = new int[4];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4592e;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        if (this.f4592e == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.f4591d[0] = g.a.f.f.a(bArr, i2);
        this.f4591d[1] = g.a.f.f.a(bArr, i2 + 4);
        this.f4591d[2] = g.a.f.f.a(bArr, i2 + 8);
        this.f4591d[3] = g.a.f.f.a(bArr, i2 + 12);
        for (int i4 = 0; i4 < 32; i4 += 4) {
            int[] iArr = this.f4591d;
            iArr[0] = c(iArr, this.f4592e[i4]);
            int[] iArr2 = this.f4591d;
            iArr2[1] = d(iArr2, this.f4592e[i4 + 1]);
            int[] iArr3 = this.f4591d;
            iArr3[2] = e(iArr3, this.f4592e[i4 + 2]);
            int[] iArr4 = this.f4591d;
            iArr4[3] = f(iArr4, this.f4592e[i4 + 3]);
        }
        i(this.f4591d, 0);
        g.a.f.f.d(this.f4591d[0], bArr2, i3);
        g.a.f.f.d(this.f4591d[1], bArr2, i3 + 4);
        g.a.f.f.d(this.f4591d[2], bArr2, i3 + 8);
        g.a.f.f.d(this.f4591d[3], bArr2, i3 + 12);
        return 16;
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final int c(int[] iArr, int i2) {
        return j((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i2) ^ iArr[0];
    }

    public final int d(int[] iArr, int i2) {
        return j((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i2) ^ iArr[1];
    }

    public final int e(int[] iArr, int i2) {
        return j((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i2) ^ iArr[2];
    }

    public final int f(int[] iArr, int i2) {
        return j((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i2) ^ iArr[3];
    }

    public final int g(int i2) {
        return m(i2, 24) ^ (((m(i2, 2) ^ i2) ^ m(i2, 10)) ^ m(i2, 18));
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "SM4";
    }

    public final int h(int i2) {
        return m(i2, 23) ^ (m(i2, 13) ^ i2);
    }

    public final void i(int[] iArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i2 + 2;
        int i5 = i2 + 3;
        iArr[i2] = iArr[i2] ^ iArr[i5];
        iArr[i5] = iArr[i2] ^ iArr[i5];
        iArr[i2] = iArr[i5] ^ iArr[i2];
        iArr[i3] = iArr[i3] ^ iArr[i4];
        iArr[i4] = iArr[i3] ^ iArr[i4];
        iArr[i3] = iArr[i3] ^ iArr[i4];
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + iVar.getClass().getName());
        }
        byte[] bArrA = ((g.a.b.j0.s0) iVar).a();
        if (bArrA.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.f4592e = l(z, bArrA);
    }

    public final int j(int i2) {
        return g(n(i2));
    }

    public final int k(int i2) {
        return h(n(i2));
    }

    public final int[] l(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {g.a.f.f.a(bArr, 0), g.a.f.f.a(bArr, 4), g.a.f.f.a(bArr, 8), g.a.f.f.a(bArr, 12)};
        int i2 = iArr2[0];
        int[] iArr3 = f4590c;
        int[] iArr4 = {i2 ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            int i3 = iArr4[0];
            int i4 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr5 = f4589b;
            iArr[0] = i3 ^ k(i4 ^ iArr5[0]);
            iArr[1] = iArr4[1] ^ k(((iArr4[2] ^ iArr4[3]) ^ iArr[0]) ^ iArr5[1]);
            iArr[2] = iArr4[2] ^ k(((iArr4[3] ^ iArr[0]) ^ iArr[1]) ^ iArr5[2]);
            iArr[3] = iArr4[3] ^ k(((iArr[0] ^ iArr[1]) ^ iArr[2]) ^ iArr5[3]);
            for (int i5 = 4; i5 < 32; i5++) {
                iArr[i5] = iArr[i5 - 4] ^ k(((iArr[i5 - 3] ^ iArr[i5 - 2]) ^ iArr[i5 - 1]) ^ f4589b[i5]);
            }
        } else {
            int i6 = iArr4[0];
            int i7 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr6 = f4589b;
            iArr[31] = i6 ^ k(i7 ^ iArr6[0]);
            iArr[30] = iArr4[1] ^ k(((iArr4[2] ^ iArr4[3]) ^ iArr[31]) ^ iArr6[1]);
            iArr[29] = iArr4[2] ^ k(((iArr4[3] ^ iArr[31]) ^ iArr[30]) ^ iArr6[2]);
            iArr[28] = iArr4[3] ^ k(((iArr[31] ^ iArr[30]) ^ iArr[29]) ^ iArr6[3]);
            for (int i8 = 27; i8 >= 0; i8--) {
                iArr[i8] = iArr[i8 + 4] ^ k(((iArr[i8 + 3] ^ iArr[i8 + 2]) ^ iArr[i8 + 1]) ^ f4589b[31 - i8]);
            }
        }
        return iArr;
    }

    public final int m(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public final int n(int i2) {
        byte[] bArr = f4588a;
        return (bArr[i2 & 255] & 255) | ((bArr[(i2 >> 24) & 255] & 255) << 24) | ((bArr[(i2 >> 16) & 255] & 255) << 16) | ((bArr[(i2 >> 8) & 255] & 255) << 8);
    }

    @Override // g.a.b.e
    public void reset() {
    }
}

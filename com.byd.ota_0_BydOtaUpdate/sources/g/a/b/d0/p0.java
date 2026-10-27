package g.a.b.d0;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class p0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4547a = {0, 0, 25, 1, 50, 2, 26, -58, 75, -57, 27, 104, 51, -18, -33, 3, 100, 4, -32, 14, 52, -115, -127, -17, 76, 113, 8, -56, -8, 105, 28, -63, 125, -62, 29, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, 120, 101, 47, -118, 5, 33, 15, -31, 36, 18, -16, -126, 69, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, -15, 64, 70, -125, 56, 102, -35, -3, 48, -65, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, 110, 72, -61, -93, -74, 30, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, 21, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, -89, 87, -81, 88, -88, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, 22, 11, -11, 89, -53, 95, -80, -100, -87, 81, -96, 127, 12, -10, 111, 23, -60, 73, -20, -40, 67, 31, 45, -92, 118, 123, -73, -52, -69, 62, 90, -5, 96, -79, -122, 59, 82, -95, 108, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, -68, -107, -49, -51, 55, 63, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, 20, 42, -98, 93, 86, -14, -45, -85, 68, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, -91, 103, 74, -19, -34, -59, 49, -2, 24, 13, 99, -116, -128, -64, -9, 112, 7};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f4548b = {0, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, 127, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, 64, -64, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, -126, -99, -68, -33, 122, -114, -119, -128, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, 31, 33, 99, -91, -12, 7, 9, 27, 45, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, 18, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, 28, 36, 108, -76, -57, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, 127, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, 64, -64, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, -126, -99, -68, -33, 122, -114, -119, -128, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, 31, 33, 99, -91, -12, 7, 9, 27, 45, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, 18, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, 28, 36, 108, -76, -57, 82, -10, 1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f4549c = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f4550d = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f4551e = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, 145};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static byte[][] f4552f = {new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, 24, 32}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static byte[][] f4553g = {new byte[]{0, 24, 16, 8}, new byte[]{0, 32, 24, 16}, new byte[]{0, 40, 32, 24}, new byte[]{0, 48, 40, 24}, new byte[]{0, 56, 40, 32}};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4554h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f4555i;
    public int j;
    public int k;
    public long[][] l;
    public long m;
    public long n;
    public long o;
    public long p;
    public boolean q;
    public byte[] r;
    public byte[] s;

    public p0() {
        this(128);
    }

    public p0(int i2) {
        if (i2 == 128) {
            this.f4554h = 32;
            this.f4555i = 4294967295L;
            this.r = f4552f[0];
            this.s = f4553g[0];
        } else if (i2 == 160) {
            this.f4554h = 40;
            this.f4555i = 1099511627775L;
            this.r = f4552f[1];
            this.s = f4553g[1];
        } else if (i2 == 192) {
            this.f4554h = 48;
            this.f4555i = 281474976710655L;
            this.r = f4552f[2];
            this.s = f4553g[2];
        } else if (i2 == 224) {
            this.f4554h = 56;
            this.f4555i = 72057594037927935L;
            this.r = f4552f[3];
            this.s = f4553g[3];
        } else {
            if (i2 != 256) {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
            this.f4554h = 64;
            this.f4555i = -1L;
            this.r = f4552f[4];
            this.s = f4553g[4];
        }
        this.k = i2;
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.l == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        int i4 = this.f4554h;
        if ((i4 / 2) + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((i4 / 2) + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.q;
        t(bArr, i2);
        long[][] jArr = this.l;
        if (z) {
            j(jArr);
        } else {
            i(jArr);
        }
        r(bArr2, i3);
        return this.f4554h / 2;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4554h / 2;
    }

    public final void c() {
        long jQ = 0;
        long jQ2 = 0;
        long jQ3 = 0;
        long jQ4 = 0;
        for (int i2 = 0; i2 < this.f4554h; i2 += 8) {
            int i3 = (int) ((this.m >> i2) & 255);
            int i4 = (int) ((this.n >> i2) & 255);
            int i5 = (int) ((this.o >> i2) & 255);
            long j = jQ3;
            int i6 = (int) ((this.p >> i2) & 255);
            int i7 = -1;
            int i8 = i3 != 0 ? f4547a[i3 & 255] & 255 : -1;
            int i9 = i4 != 0 ? f4547a[i4 & 255] & 255 : -1;
            int i10 = i5 != 0 ? f4547a[i5 & 255] & 255 : -1;
            if (i6 != 0) {
                i7 = f4547a[i6 & 255] & 255;
            }
            jQ |= ((long) ((((q(i8) ^ o(i9)) ^ p(i10)) ^ n(i7)) & 255)) << i2;
            jQ4 |= ((long) ((((q(i9) ^ o(i10)) ^ p(i7)) ^ n(i8)) & 255)) << i2;
            jQ2 |= ((long) ((((q(i10) ^ o(i7)) ^ p(i8)) ^ n(i9)) & 255)) << i2;
            jQ3 = (((long) ((((q(i7) ^ o(i8)) ^ p(i9)) ^ n(i10)) & 255)) << i2) | j;
        }
        this.m = jQ;
        this.n = jQ4;
        this.o = jQ2;
        this.p = jQ3;
    }

    public final void d(long[] jArr) {
        this.m ^= jArr[0];
        this.n ^= jArr[1];
        this.o ^= jArr[2];
        this.p ^= jArr[3];
    }

    public final void e() {
        long jL = 0;
        long jL2 = 0;
        long jL3 = 0;
        long jL4 = 0;
        for (int i2 = 0; i2 < this.f4554h; i2 += 8) {
            int i3 = (int) ((this.m >> i2) & 255);
            int i4 = (int) ((this.n >> i2) & 255);
            int i5 = (int) ((this.o >> i2) & 255);
            long j = jL3;
            int i6 = (int) ((this.p >> i2) & 255);
            jL |= ((long) ((((l(i3) ^ m(i4)) ^ i5) ^ i6) & 255)) << i2;
            jL4 |= ((long) ((((l(i4) ^ m(i5)) ^ i6) ^ i3) & 255)) << i2;
            jL2 |= ((long) ((((l(i5) ^ m(i6)) ^ i3) ^ i4) & 255)) << i2;
            jL3 = (((long) ((((l(i6) ^ m(i3)) ^ i4) ^ i5) & 255)) << i2) | j;
        }
        this.m = jL;
        this.n = jL4;
        this.o = jL2;
        this.p = jL3;
    }

    public final void f(byte[] bArr) {
        this.n = s(this.n, bArr[1]);
        this.o = s(this.o, bArr[2]);
        this.p = s(this.p, bArr[3]);
    }

    public final void g(byte[] bArr) {
        this.m = h(this.m, bArr);
        this.n = h(this.n, bArr);
        this.o = h(this.o, bArr);
        this.p = h(this.p, bArr);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "Rijndael";
    }

    public final long h(long j, byte[] bArr) {
        long j2 = 0;
        for (int i2 = 0; i2 < this.f4554h; i2 += 8) {
            j2 |= ((long) (bArr[(int) ((j >> i2) & 255)] & 255)) << i2;
        }
        return j2;
    }

    public final void i(long[][] jArr) {
        d(jArr[this.j]);
        g(f4550d);
        f(this.s);
        for (int i2 = this.j - 1; i2 > 0; i2--) {
            d(jArr[i2]);
            c();
            g(f4550d);
            f(this.s);
        }
        d(jArr[0]);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.l = k(((g.a.b.j0.s0) iVar).a());
            this.q = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + iVar.getClass().getName());
        }
    }

    public final void j(long[][] jArr) {
        d(jArr[0]);
        for (int i2 = 1; i2 < this.j; i2++) {
            g(f4549c);
            f(this.r);
            e();
            d(jArr[i2]);
        }
        g(f4549c);
        f(this.r);
        d(jArr[this.j]);
    }

    public final long[][] k(byte[] bArr) {
        int i2;
        int i3;
        int i4 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) long.class, 15, 4);
        int i5 = 4;
        if (length == 128) {
            i2 = 4;
        } else if (length == 160) {
            i2 = 5;
        } else if (length == 192) {
            i2 = 6;
        } else if (length == 224) {
            i2 = 7;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
            i2 = 8;
        }
        this.j = length >= this.k ? i2 + 6 : (this.f4554h / 8) + 6;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < bArr.length) {
            bArr2[i7 % 4][i7 / 4] = bArr[i8];
            i7++;
            i8++;
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i2 && i10 < (this.j + 1) * (this.f4554h / 8)) {
            int i11 = 0;
            while (i11 < i5) {
                int i12 = this.f4554h;
                long[] jArr2 = jArr[i10 / (i12 / 8)];
                jArr2[i11] = (((long) (bArr2[i11][i9] & 255)) << ((i10 * 8) % i12)) | jArr2[i11];
                i11++;
                i5 = 4;
            }
            i9++;
            i10++;
            i5 = 4;
        }
        int i13 = 0;
        while (i10 < (this.j + 1) * (this.f4554h / i4)) {
            int i14 = i6;
            while (i14 < 4) {
                byte[] bArr3 = bArr2[i14];
                i14++;
                bArr3[i6] = (byte) (bArr3[i6] ^ f4549c[bArr2[i14 % 4][i2 - 1] & 255]);
            }
            byte[] bArr4 = bArr2[i6];
            int i15 = i13 + 1;
            bArr4[i6] = (byte) (f4551e[i13] ^ bArr4[i6]);
            int i16 = 1;
            if (i2 <= 6) {
                while (i16 < i2) {
                    for (int i17 = i6; i17 < 4; i17++) {
                        byte[] bArr5 = bArr2[i17];
                        bArr5[i16] = (byte) (bArr5[i16] ^ bArr2[i17][i16 - 1]);
                    }
                    i16++;
                }
            } else {
                while (true) {
                    i3 = 4;
                    if (i16 >= 4) {
                        break;
                    }
                    int i18 = i6;
                    while (i18 < i3) {
                        byte[] bArr6 = bArr2[i18];
                        bArr6[i16] = (byte) (bArr6[i16] ^ bArr2[i18][i16 - 1]);
                        i18++;
                        i3 = 4;
                    }
                    i16++;
                }
                for (int i19 = i6; i19 < 4; i19++) {
                    byte[] bArr7 = bArr2[i19];
                    bArr7[4] = (byte) (bArr7[4] ^ f4549c[bArr2[i19][3] & 255]);
                }
                int i20 = 5;
                while (i20 < i2) {
                    int i21 = i6;
                    while (i21 < i3) {
                        byte[] bArr8 = bArr2[i21];
                        bArr8[i20] = (byte) (bArr8[i20] ^ bArr2[i21][i20 - 1]);
                        i21++;
                        i3 = 4;
                    }
                    i20++;
                    i3 = 4;
                }
            }
            int i22 = i6;
            while (i22 < i2 && i10 < (this.j + 1) * (this.f4554h / i4)) {
                for (int i23 = i6; i23 < 4; i23++) {
                    int i24 = this.f4554h;
                    long[] jArr3 = jArr[i10 / (i24 / 8)];
                    jArr3[i23] = (((long) (bArr2[i23][i22] & 255)) << ((i10 * 8) % i24)) | jArr3[i23];
                }
                i22++;
                i10++;
                i6 = 0;
                i4 = 8;
            }
            i13 = i15;
            i6 = 0;
            i4 = 8;
        }
        return jArr;
    }

    public final byte l(int i2) {
        if (i2 != 0) {
            return f4548b[(f4547a[i2] & 255) + 25];
        }
        return (byte) 0;
    }

    public final byte m(int i2) {
        if (i2 != 0) {
            return f4548b[(f4547a[i2] & 255) + 1];
        }
        return (byte) 0;
    }

    public final byte n(int i2) {
        if (i2 >= 0) {
            return f4548b[i2 + 199];
        }
        return (byte) 0;
    }

    public final byte o(int i2) {
        if (i2 >= 0) {
            return f4548b[i2 + 104];
        }
        return (byte) 0;
    }

    public final byte p(int i2) {
        if (i2 >= 0) {
            return f4548b[i2 + 238];
        }
        return (byte) 0;
    }

    public final byte q(int i2) {
        if (i2 >= 0) {
            return f4548b[i2 + 223];
        }
        return (byte) 0;
    }

    public final void r(byte[] bArr, int i2) {
        for (int i3 = 0; i3 != this.f4554h; i3 += 8) {
            int i4 = i2 + 1;
            bArr[i2] = (byte) (this.m >> i3);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.n >> i3);
            int i6 = i5 + 1;
            bArr[i5] = (byte) (this.o >> i3);
            i2 = i6 + 1;
            bArr[i6] = (byte) (this.p >> i3);
        }
    }

    @Override // g.a.b.e
    public void reset() {
    }

    public final long s(long j, int i2) {
        return ((j << (this.f4554h - i2)) | (j >>> i2)) & this.f4555i;
    }

    public final void t(byte[] bArr, int i2) {
        this.m = bArr[i2] & 255;
        this.n = bArr[r0] & 255;
        this.o = bArr[r8] & 255;
        int i3 = i2 + 1 + 1 + 1 + 1;
        this.p = bArr[r0] & 255;
        for (int i4 = 8; i4 != this.f4554h; i4 += 8) {
            int i5 = i3 + 1;
            this.m |= ((long) (bArr[i3] & 255)) << i4;
            int i6 = i5 + 1;
            this.n |= ((long) (bArr[i5] & 255)) << i4;
            int i7 = i6 + 1;
            this.o |= ((long) (bArr[i6] & 255)) << i4;
            i3 = i7 + 1;
            this.p |= ((long) (bArr[i7] & 255)) << i4;
        }
    }
}

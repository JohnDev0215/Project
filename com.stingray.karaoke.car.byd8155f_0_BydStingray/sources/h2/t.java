package h2;

import d5.M;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class t {
    public static final char[] d = {'\r', '\n'};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final char[] f15239e = {'\n'};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final M f15240f = M.u(5, StandardCharsets.US_ASCII, StandardCharsets.UTF_8, StandardCharsets.UTF_16, StandardCharsets.UTF_16BE, StandardCharsets.UTF_16LE);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f15241a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15242b;
    public int c;

    public t() {
        this.f15241a = AbstractC1343A.f15198f;
    }

    public final long A() {
        long jP = p();
        if (jP >= 0) {
            return jP;
        }
        throw new IllegalStateException(M5.b.f("Top bit not zero: ", jP));
    }

    public final int B() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = (bArr[i7] & 255) << 8;
        this.f15242b = i7 + 2;
        return (bArr[i10] & 255) | i11;
    }

    public final long C() {
        int i7;
        int i10;
        long j3 = this.f15241a[this.f15242b];
        int i11 = 7;
        while (true) {
            if (i11 < 0) {
                break;
            }
            int i12 = 1 << i11;
            if ((((long) i12) & j3) != 0) {
                i11--;
            } else if (i11 < 6) {
                j3 &= (long) (i12 - 1);
                i10 = 7 - i11;
            } else if (i11 == 7) {
                i10 = 1;
            }
        }
        i10 = 0;
        if (i10 == 0) {
            throw new NumberFormatException(M5.b.f("Invalid UTF-8 sequence first byte: ", j3));
        }
        for (i7 = 1; i7 < i10; i7++) {
            byte b10 = this.f15241a[this.f15242b + i7];
            if ((b10 & 192) != 128) {
                throw new NumberFormatException(M5.b.f("Invalid UTF-8 sequence continuation byte: ", j3));
            }
            j3 = (j3 << 6) | ((long) (b10 & 63));
        }
        this.f15242b += i10;
        return j3;
    }

    public final Charset D() {
        if (a() >= 3) {
            byte[] bArr = this.f15241a;
            int i7 = this.f15242b;
            if (bArr[i7] == -17 && bArr[i7 + 1] == -69 && bArr[i7 + 2] == -65) {
                this.f15242b = i7 + 3;
                return StandardCharsets.UTF_8;
            }
        }
        if (a() < 2) {
            return null;
        }
        byte[] bArr2 = this.f15241a;
        int i10 = this.f15242b;
        byte b10 = bArr2[i10];
        if (b10 == -2 && bArr2[i10 + 1] == -1) {
            this.f15242b = i10 + 2;
            return StandardCharsets.UTF_16BE;
        }
        if (b10 != -1 || bArr2[i10 + 1] != -2) {
            return null;
        }
        this.f15242b = i10 + 2;
        return StandardCharsets.UTF_16LE;
    }

    public final void E(int i7) {
        byte[] bArr = this.f15241a;
        if (bArr.length < i7) {
            bArr = new byte[i7];
        }
        F(i7, bArr);
    }

    public final void F(int i7, byte[] bArr) {
        this.f15241a = bArr;
        this.c = i7;
        this.f15242b = 0;
    }

    public final void G(int i7) {
        AbstractC1346c.c(i7 >= 0 && i7 <= this.f15241a.length);
        this.c = i7;
    }

    public final void H(int i7) {
        AbstractC1346c.c(i7 >= 0 && i7 <= this.c);
        this.f15242b = i7;
    }

    public final void I(int i7) {
        H(this.f15242b + i7);
    }

    public final int a() {
        return this.c - this.f15242b;
    }

    public final void b(int i7) {
        byte[] bArr = this.f15241a;
        if (i7 > bArr.length) {
            this.f15241a = Arrays.copyOf(bArr, i7);
        }
    }

    public final int c() {
        return this.f15242b;
    }

    public final char d(Charset charset) {
        AbstractC1346c.b("Unsupported charset: " + charset, f15240f.contains(charset));
        return (char) (e(charset) >> 16);
    }

    public final int e(Charset charset) {
        byte b10;
        int i7;
        byte b11;
        byte b12;
        if ((charset.equals(StandardCharsets.UTF_8) || charset.equals(StandardCharsets.US_ASCII)) && a() >= 1) {
            long j3 = this.f15241a[this.f15242b] & 255;
            char c = (char) j3;
            P8.n.o(j3, "Out of range: %s", ((long) c) == j3);
            b10 = (byte) c;
            i7 = 1;
        } else {
            i7 = 2;
            if ((charset.equals(StandardCharsets.UTF_16) || charset.equals(StandardCharsets.UTF_16BE)) && a() >= 2) {
                byte[] bArr = this.f15241a;
                int i10 = this.f15242b;
                b11 = bArr[i10];
                b12 = bArr[i10 + 1];
            } else {
                if (!charset.equals(StandardCharsets.UTF_16LE) || a() < 2) {
                    return 0;
                }
                byte[] bArr2 = this.f15241a;
                int i11 = this.f15242b;
                b11 = bArr2[i11 + 1];
                b12 = bArr2[i11];
            }
            b10 = (byte) ((char) ((b12 & 255) | (b11 << 8)));
        }
        long j10 = b10;
        char c10 = (char) j10;
        P8.n.o(j10, "Out of range: %s", ((long) c10) == j10);
        return (c10 << 16) + i7;
    }

    public final void f(int i7, int i10, byte[] bArr) {
        System.arraycopy(this.f15241a, this.f15242b, bArr, i7, i10);
        this.f15242b += i10;
    }

    public final char g(Charset charset, char[] cArr) {
        int iE = e(charset);
        if (iE != 0) {
            char c = (char) (iE >> 16);
            for (char c10 : cArr) {
                if (c10 == c) {
                    this.f15242b += iE & 65535;
                    return c;
                }
            }
        }
        return (char) 0;
    }

    public final int h() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = (bArr[i7] & 255) << 24;
        int i12 = i7 + 2;
        this.f15242b = i12;
        int i13 = ((bArr[i10] & 255) << 16) | i11;
        int i14 = i7 + 3;
        this.f15242b = i14;
        int i15 = i13 | ((bArr[i12] & 255) << 8);
        this.f15242b = i7 + 4;
        return (bArr[i14] & 255) | i15;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String i(java.nio.charset.Charset r8) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.t.i(java.nio.charset.Charset):java.lang.String");
    }

    public final int j() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = bArr[i7] & 255;
        int i12 = i7 + 2;
        this.f15242b = i12;
        int i13 = ((bArr[i10] & 255) << 8) | i11;
        int i14 = i7 + 3;
        this.f15242b = i14;
        int i15 = i13 | ((bArr[i12] & 255) << 16);
        this.f15242b = i7 + 4;
        return ((bArr[i14] & 255) << 24) | i15;
    }

    public final long k() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        long j3 = ((long) bArr[i7]) & 255;
        int i11 = i7 + 2;
        this.f15242b = i11;
        long j10 = j3 | ((((long) bArr[i10]) & 255) << 8);
        int i12 = i7 + 3;
        this.f15242b = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 16);
        int i13 = i7 + 4;
        this.f15242b = i13;
        long j12 = j11 | ((((long) bArr[i12]) & 255) << 24);
        int i14 = i7 + 5;
        this.f15242b = i14;
        long j13 = j12 | ((((long) bArr[i13]) & 255) << 32);
        int i15 = i7 + 6;
        this.f15242b = i15;
        long j14 = j13 | ((((long) bArr[i14]) & 255) << 40);
        int i16 = i7 + 7;
        this.f15242b = i16;
        long j15 = j14 | ((((long) bArr[i15]) & 255) << 48);
        this.f15242b = i7 + 8;
        return ((((long) bArr[i16]) & 255) << 56) | j15;
    }

    public final short l() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = bArr[i7] & 255;
        this.f15242b = i7 + 2;
        return (short) (((bArr[i10] & 255) << 8) | i11);
    }

    public final long m() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        long j3 = ((long) bArr[i7]) & 255;
        int i11 = i7 + 2;
        this.f15242b = i11;
        long j10 = j3 | ((((long) bArr[i10]) & 255) << 8);
        int i12 = i7 + 3;
        this.f15242b = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 16);
        this.f15242b = i7 + 4;
        return ((((long) bArr[i12]) & 255) << 24) | j11;
    }

    public final int n() {
        int iJ = j();
        if (iJ >= 0) {
            return iJ;
        }
        throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.e(iJ, "Top bit not zero: "));
    }

    public final int o() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = bArr[i7] & 255;
        this.f15242b = i7 + 2;
        return ((bArr[i10] & 255) << 8) | i11;
    }

    public final long p() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        long j3 = (((long) bArr[i7]) & 255) << 56;
        int i11 = i7 + 2;
        this.f15242b = i11;
        long j10 = j3 | ((((long) bArr[i10]) & 255) << 48);
        int i12 = i7 + 3;
        this.f15242b = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 40);
        int i13 = i7 + 4;
        this.f15242b = i13;
        long j12 = j11 | ((((long) bArr[i12]) & 255) << 32);
        int i14 = i7 + 5;
        this.f15242b = i14;
        long j13 = j12 | ((((long) bArr[i13]) & 255) << 24);
        int i15 = i7 + 6;
        this.f15242b = i15;
        long j14 = j13 | ((((long) bArr[i14]) & 255) << 16);
        int i16 = i7 + 7;
        this.f15242b = i16;
        long j15 = j14 | ((((long) bArr[i15]) & 255) << 8);
        this.f15242b = i7 + 8;
        return (((long) bArr[i16]) & 255) | j15;
    }

    public final String q() {
        if (a() == 0) {
            return null;
        }
        int i7 = this.f15242b;
        while (i7 < this.c && this.f15241a[i7] != 0) {
            i7++;
        }
        byte[] bArr = this.f15241a;
        int i10 = this.f15242b;
        int i11 = AbstractC1343A.f15195a;
        String str = new String(bArr, i10, i7 - i10, StandardCharsets.UTF_8);
        this.f15242b = i7;
        if (i7 < this.c) {
            this.f15242b = i7 + 1;
        }
        return str;
    }

    public final String r(int i7) {
        if (i7 == 0) {
            return "";
        }
        int i10 = this.f15242b;
        int i11 = (i10 + i7) - 1;
        int i12 = (i11 >= this.c || this.f15241a[i11] != 0) ? i7 : i7 - 1;
        byte[] bArr = this.f15241a;
        int i13 = AbstractC1343A.f15195a;
        String str = new String(bArr, i10, i12, StandardCharsets.UTF_8);
        this.f15242b += i7;
        return str;
    }

    public final short s() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = (bArr[i7] & 255) << 8;
        this.f15242b = i7 + 2;
        return (short) ((bArr[i10] & 255) | i11);
    }

    public final String t(int i7, Charset charset) {
        String str = new String(this.f15241a, this.f15242b, i7, charset);
        this.f15242b += i7;
        return str;
    }

    public final int u() {
        return v() | (v() << 21) | (v() << 14) | (v() << 7);
    }

    public final int v() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        this.f15242b = i7 + 1;
        return bArr[i7] & 255;
    }

    public final int w() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = (bArr[i7] & 255) << 8;
        this.f15242b = i7 + 2;
        int i12 = (bArr[i10] & 255) | i11;
        this.f15242b = i7 + 4;
        return i12;
    }

    public final long x() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        long j3 = (((long) bArr[i7]) & 255) << 24;
        int i11 = i7 + 2;
        this.f15242b = i11;
        long j10 = j3 | ((((long) bArr[i10]) & 255) << 16);
        int i12 = i7 + 3;
        this.f15242b = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 8);
        this.f15242b = i7 + 4;
        return (((long) bArr[i12]) & 255) | j11;
    }

    public final int y() {
        byte[] bArr = this.f15241a;
        int i7 = this.f15242b;
        int i10 = i7 + 1;
        this.f15242b = i10;
        int i11 = (bArr[i7] & 255) << 16;
        int i12 = i7 + 2;
        this.f15242b = i12;
        int i13 = ((bArr[i10] & 255) << 8) | i11;
        this.f15242b = i7 + 3;
        return (bArr[i12] & 255) | i13;
    }

    public final int z() {
        int iH = h();
        if (iH >= 0) {
            return iH;
        }
        throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.e(iH, "Top bit not zero: "));
    }

    public t(int i7) {
        this.f15241a = new byte[i7];
        this.c = i7;
    }

    public t(byte[] bArr) {
        this.f15241a = bArr;
        this.c = bArr.length;
    }

    public t(int i7, byte[] bArr) {
        this.f15241a = bArr;
        this.c = i7;
    }
}

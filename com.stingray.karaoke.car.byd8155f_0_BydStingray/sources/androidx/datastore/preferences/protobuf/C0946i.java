package androidx.datastore.preferences.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0946i extends AbstractC0947j {
    public final InputStream c;
    public final byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f11852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f11853f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f11854g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f11855h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f11856i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f11857j = Integer.MAX_VALUE;

    public C0946i(InputStream inputStream) {
        Charset charset = AbstractC0959w.f11890a;
        this.c = inputStream;
        this.d = new byte[4096];
        this.f11852e = 0;
        this.f11854g = 0;
        this.f11856i = 0;
    }

    public final ArrayList A(int i7) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i7 > 0) {
            int iMin = Math.min(i7, 4096);
            byte[] bArr = new byte[iMin];
            int i10 = 0;
            while (i10 < iMin) {
                int i11 = this.c.read(bArr, i10, iMin - i10);
                if (i11 == -1) {
                    throw C0961y.e();
                }
                this.f11856i += i11;
                i10 += i11;
            }
            i7 -= iMin;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public final int B() throws C0961y {
        int i7 = this.f11854g;
        if (this.f11852e - i7 < 4) {
            H(4);
            i7 = this.f11854g;
        }
        this.f11854g = i7 + 4;
        byte[] bArr = this.d;
        return ((bArr[i7 + 3] & 255) << 24) | (bArr[i7] & 255) | ((bArr[i7 + 1] & 255) << 8) | ((bArr[i7 + 2] & 255) << 16);
    }

    public final long C() throws C0961y {
        int i7 = this.f11854g;
        if (this.f11852e - i7 < 8) {
            H(8);
            i7 = this.f11854g;
        }
        this.f11854g = i7 + 8;
        byte[] bArr = this.d;
        return ((((long) bArr[i7 + 1]) & 255) << 8) | (((long) bArr[i7]) & 255) | ((((long) bArr[i7 + 2]) & 255) << 16) | ((((long) bArr[i7 + 3]) & 255) << 24) | ((((long) bArr[i7 + 4]) & 255) << 32) | ((((long) bArr[i7 + 5]) & 255) << 40) | ((((long) bArr[i7 + 6]) & 255) << 48) | ((((long) bArr[i7 + 7]) & 255) << 56);
    }

    public final int D() {
        int i7;
        int i10 = this.f11854g;
        int i11 = this.f11852e;
        if (i11 != i10) {
            int i12 = i10 + 1;
            byte[] bArr = this.d;
            byte b10 = bArr[i10];
            if (b10 >= 0) {
                this.f11854g = i12;
                return b10;
            }
            if (i11 - i12 >= 9) {
                int i13 = i10 + 2;
                int i14 = (bArr[i12] << 7) ^ b10;
                if (i14 < 0) {
                    i7 = i14 ^ (-128);
                } else {
                    int i15 = i10 + 3;
                    int i16 = (bArr[i13] << 14) ^ i14;
                    if (i16 >= 0) {
                        i7 = i16 ^ 16256;
                    } else {
                        int i17 = i10 + 4;
                        int i18 = i16 ^ (bArr[i15] << 21);
                        if (i18 < 0) {
                            i7 = (-2080896) ^ i18;
                        } else {
                            i15 = i10 + 5;
                            byte b11 = bArr[i17];
                            int i19 = (i18 ^ (b11 << 28)) ^ 266354560;
                            if (b11 < 0) {
                                i17 = i10 + 6;
                                if (bArr[i15] < 0) {
                                    i15 = i10 + 7;
                                    if (bArr[i17] < 0) {
                                        i17 = i10 + 8;
                                        if (bArr[i15] < 0) {
                                            i15 = i10 + 9;
                                            if (bArr[i17] < 0) {
                                                int i20 = i10 + 10;
                                                if (bArr[i15] >= 0) {
                                                    i13 = i20;
                                                    i7 = i19;
                                                }
                                            }
                                        }
                                    }
                                }
                                i7 = i19;
                            }
                            i7 = i19;
                        }
                        i13 = i17;
                    }
                    i13 = i15;
                }
                this.f11854g = i13;
                return i7;
            }
        }
        return (int) F();
    }

    public final long E() {
        long j3;
        long j10;
        long j11;
        long j12;
        int i7 = this.f11854g;
        int i10 = this.f11852e;
        if (i10 != i7) {
            int i11 = i7 + 1;
            byte[] bArr = this.d;
            byte b10 = bArr[i7];
            if (b10 >= 0) {
                this.f11854g = i11;
                return b10;
            }
            if (i10 - i11 >= 9) {
                int i12 = i7 + 2;
                int i13 = (bArr[i11] << 7) ^ b10;
                if (i13 < 0) {
                    j3 = i13 ^ (-128);
                } else {
                    int i14 = i7 + 3;
                    int i15 = (bArr[i12] << 14) ^ i13;
                    if (i15 >= 0) {
                        j3 = i15 ^ 16256;
                        i12 = i14;
                    } else {
                        int i16 = i7 + 4;
                        int i17 = i15 ^ (bArr[i14] << 21);
                        if (i17 < 0) {
                            j12 = (-2080896) ^ i17;
                        } else {
                            long j13 = i17;
                            i12 = i7 + 5;
                            long j14 = j13 ^ (((long) bArr[i16]) << 28);
                            if (j14 >= 0) {
                                j11 = 266354560;
                            } else {
                                i16 = i7 + 6;
                                long j15 = j14 ^ (((long) bArr[i12]) << 35);
                                if (j15 < 0) {
                                    j10 = -34093383808L;
                                } else {
                                    i12 = i7 + 7;
                                    j14 = j15 ^ (((long) bArr[i16]) << 42);
                                    if (j14 >= 0) {
                                        j11 = 4363953127296L;
                                    } else {
                                        i16 = i7 + 8;
                                        j15 = j14 ^ (((long) bArr[i12]) << 49);
                                        if (j15 < 0) {
                                            j10 = -558586000294016L;
                                        } else {
                                            i12 = i7 + 9;
                                            long j16 = (j15 ^ (((long) bArr[i16]) << 56)) ^ 71499008037633920L;
                                            if (j16 < 0) {
                                                int i18 = i7 + 10;
                                                if (bArr[i12] >= 0) {
                                                    i12 = i18;
                                                }
                                            }
                                            j3 = j16;
                                        }
                                    }
                                }
                                j12 = j10 ^ j15;
                            }
                            j3 = j11 ^ j14;
                        }
                        i12 = i16;
                        j3 = j12;
                    }
                }
                this.f11854g = i12;
                return j3;
            }
        }
        return F();
    }

    public final long F() throws C0961y {
        long j3 = 0;
        for (int i7 = 0; i7 < 64; i7 += 7) {
            if (this.f11854g == this.f11852e) {
                H(1);
            }
            int i10 = this.f11854g;
            this.f11854g = i10 + 1;
            byte b10 = this.d[i10];
            j3 |= ((long) (b10 & 127)) << i7;
            if ((b10 & 128) == 0) {
                return j3;
            }
        }
        throw C0961y.c();
    }

    public final void G() {
        int i7 = this.f11852e + this.f11853f;
        this.f11852e = i7;
        int i10 = this.f11856i + i7;
        int i11 = this.f11857j;
        if (i10 <= i11) {
            this.f11853f = 0;
            return;
        }
        int i12 = i10 - i11;
        this.f11853f = i12;
        this.f11852e = i7 - i12;
    }

    public final void H(int i7) throws C0961y {
        if (J(i7)) {
            return;
        }
        if (i7 <= (Integer.MAX_VALUE - this.f11856i) - this.f11854g) {
            throw C0961y.e();
        }
        throw new C0961y("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public final void I(int i7) throws C0961y {
        int i10 = this.f11852e;
        int i11 = this.f11854g;
        if (i7 <= i10 - i11 && i7 >= 0) {
            this.f11854g = i11 + i7;
            return;
        }
        InputStream inputStream = this.c;
        if (i7 < 0) {
            throw C0961y.d();
        }
        int i12 = this.f11856i;
        int i13 = i12 + i11;
        int i14 = i13 + i7;
        int i15 = this.f11857j;
        if (i14 > i15) {
            I((i15 - i12) - i11);
            throw C0961y.e();
        }
        this.f11856i = i13;
        int i16 = i10 - i11;
        this.f11852e = 0;
        this.f11854g = 0;
        while (i16 < i7) {
            long j3 = i7 - i16;
            try {
                try {
                    long jSkip = inputStream.skip(j3);
                    if (jSkip < 0 || jSkip > j3) {
                        throw new IllegalStateException(inputStream.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                    }
                    if (jSkip == 0) {
                        break;
                    } else {
                        i16 += (int) jSkip;
                    }
                } catch (C0961y e10) {
                    e10.l = true;
                    throw e10;
                }
            } catch (Throwable th) {
                this.f11856i += i16;
                G();
                throw th;
            }
        }
        this.f11856i += i16;
        G();
        if (i16 >= i7) {
            return;
        }
        int i17 = this.f11852e;
        int i18 = i17 - this.f11854g;
        this.f11854g = i17;
        H(1);
        while (true) {
            int i19 = i7 - i18;
            int i20 = this.f11852e;
            if (i19 <= i20) {
                this.f11854g = i19;
                return;
            } else {
                i18 += i20;
                this.f11854g = i20;
                H(1);
            }
        }
    }

    public final boolean J(int i7) throws IOException {
        int i10 = this.f11854g;
        int i11 = i10 + i7;
        int i12 = this.f11852e;
        if (i11 <= i12) {
            throw new IllegalStateException(A7.k.g("refillBuffer() called when ", i7, " bytes were already available in buffer"));
        }
        int i13 = this.f11856i;
        if (i7 > (Integer.MAX_VALUE - i13) - i10 || i13 + i10 + i7 > this.f11857j) {
            return false;
        }
        byte[] bArr = this.d;
        if (i10 > 0) {
            if (i12 > i10) {
                System.arraycopy(bArr, i10, bArr, 0, i12 - i10);
            }
            this.f11856i += i10;
            this.f11852e -= i10;
            this.f11854g = 0;
        }
        int i14 = this.f11852e;
        int iMin = Math.min(bArr.length - i14, (Integer.MAX_VALUE - this.f11856i) - i14);
        InputStream inputStream = this.c;
        try {
            int i15 = inputStream.read(bArr, i14, iMin);
            if (i15 == 0 || i15 < -1 || i15 > bArr.length) {
                throw new IllegalStateException(inputStream.getClass() + "#read(byte[]) returned invalid result: " + i15 + "\nThe InputStream implementation is buggy.");
            }
            if (i15 <= 0) {
                return false;
            }
            this.f11852e += i15;
            G();
            if (this.f11852e >= i7) {
                return true;
            }
            return J(i7);
        } catch (C0961y e10) {
            e10.l = true;
            throw e10;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final void a(int i7) throws C0961y {
        if (this.f11855h != i7) {
            throw new C0961y("Protocol message end-group tag did not match expected tag.");
        }
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int b() {
        return this.f11856i + this.f11854g;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final boolean c() {
        return this.f11854g == this.f11852e && !J(1);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final void d(int i7) {
        this.f11857j = i7;
        G();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int e(int i7) throws C0961y {
        if (i7 < 0) {
            throw C0961y.d();
        }
        int i10 = this.f11856i + this.f11854g + i7;
        int i11 = this.f11857j;
        if (i10 > i11) {
            throw C0961y.e();
        }
        this.f11857j = i10;
        G();
        return i11;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final boolean f() {
        return E() != 0;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final C0944g g() throws IOException {
        int iD = D();
        int i7 = this.f11852e;
        int i10 = this.f11854g;
        int i11 = i7 - i10;
        byte[] bArr = this.d;
        if (iD <= i11 && iD > 0) {
            C0944g c0944gL = C0944g.l(i10, iD, bArr);
            this.f11854g += iD;
            return c0944gL;
        }
        if (iD == 0) {
            return C0944g.f11844n;
        }
        byte[] bArrZ = z(iD);
        if (bArrZ != null) {
            return C0944g.l(0, bArrZ.length, bArrZ);
        }
        int i12 = this.f11854g;
        int i13 = this.f11852e;
        int length = i13 - i12;
        this.f11856i += i13;
        this.f11854g = 0;
        this.f11852e = 0;
        ArrayList<byte[]> arrayListA = A(iD - length);
        byte[] bArr2 = new byte[iD];
        System.arraycopy(bArr, i12, bArr2, 0, length);
        for (byte[] bArr3 : arrayListA) {
            System.arraycopy(bArr3, 0, bArr2, length, bArr3.length);
            length += bArr3.length;
        }
        C0944g c0944g = C0944g.f11844n;
        return new C0944g(bArr2);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final double h() {
        return Double.longBitsToDouble(C());
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int i() {
        return D();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int j() {
        return B();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long k() {
        return C();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final float l() {
        return Float.intBitsToFloat(B());
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int m() {
        return D();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long n() {
        return E();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int o() {
        return B();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long p() {
        return C();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int q() {
        int iD = D();
        return (-(iD & 1)) ^ (iD >>> 1);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long r() {
        long jE = E();
        return (-(jE & 1)) ^ (jE >>> 1);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final String s() throws C0961y {
        int iD = D();
        byte[] bArr = this.d;
        if (iD > 0) {
            int i7 = this.f11852e;
            int i10 = this.f11854g;
            if (iD <= i7 - i10) {
                String str = new String(bArr, i10, iD, AbstractC0959w.f11890a);
                this.f11854g += iD;
                return str;
            }
        }
        if (iD == 0) {
            return "";
        }
        if (iD > this.f11852e) {
            return new String(y(iD), AbstractC0959w.f11890a);
        }
        H(iD);
        String str2 = new String(bArr, this.f11854g, iD, AbstractC0959w.f11890a);
        this.f11854g += iD;
        return str2;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final String t() throws IOException {
        int iD = D();
        int i7 = this.f11854g;
        int i10 = this.f11852e;
        int i11 = i10 - i7;
        byte[] bArrY = this.d;
        if (iD <= i11 && iD > 0) {
            this.f11854g = i7 + iD;
        } else {
            if (iD == 0) {
                return "";
            }
            i7 = 0;
            if (iD <= i10) {
                H(iD);
                this.f11854g = iD;
            } else {
                bArrY = y(iD);
            }
        }
        return u0.f11889a.a(i7, iD, bArrY);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int u() throws C0961y {
        if (c()) {
            this.f11855h = 0;
            return 0;
        }
        int iD = D();
        this.f11855h = iD;
        if ((iD >>> 3) != 0) {
            return iD;
        }
        throw new C0961y("Protocol message contained an invalid tag (zero).");
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int v() {
        return D();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long w() {
        return E();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final boolean x(int i7) throws C0961y {
        int iU;
        int i10 = i7 & 7;
        int i11 = 0;
        if (i10 == 0) {
            int i12 = this.f11852e - this.f11854g;
            byte[] bArr = this.d;
            if (i12 >= 10) {
                while (i11 < 10) {
                    int i13 = this.f11854g;
                    this.f11854g = i13 + 1;
                    if (bArr[i13] < 0) {
                        i11++;
                    }
                }
                throw C0961y.c();
            }
            while (i11 < 10) {
                if (this.f11854g == this.f11852e) {
                    H(1);
                }
                int i14 = this.f11854g;
                this.f11854g = i14 + 1;
                if (bArr[i14] < 0) {
                    i11++;
                }
            }
            throw C0961y.c();
            return true;
        }
        if (i10 == 1) {
            I(8);
            return true;
        }
        if (i10 == 2) {
            I(D());
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw C0961y.b();
            }
            I(4);
            return true;
        }
        do {
            iU = u();
            if (iU == 0) {
                break;
            }
        } while (x(iU));
        a(((i7 >>> 3) << 3) | 4);
        return true;
    }

    public final byte[] y(int i7) throws IOException {
        byte[] bArrZ = z(i7);
        if (bArrZ != null) {
            return bArrZ;
        }
        int i10 = this.f11854g;
        int i11 = this.f11852e;
        int length = i11 - i10;
        this.f11856i += i11;
        this.f11854g = 0;
        this.f11852e = 0;
        ArrayList<byte[]> arrayListA = A(i7 - length);
        byte[] bArr = new byte[i7];
        System.arraycopy(this.d, i10, bArr, 0, length);
        for (byte[] bArr2 : arrayListA) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }

    public final byte[] z(int i7) throws IOException {
        if (i7 == 0) {
            return AbstractC0959w.f11891b;
        }
        if (i7 < 0) {
            throw C0961y.d();
        }
        int i10 = this.f11856i;
        int i11 = this.f11854g;
        int i12 = i10 + i11 + i7;
        if (i12 - Integer.MAX_VALUE > 0) {
            throw new C0961y("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }
        int i13 = this.f11857j;
        if (i12 > i13) {
            I((i13 - i10) - i11);
            throw C0961y.e();
        }
        int i14 = this.f11852e - i11;
        int i15 = i7 - i14;
        InputStream inputStream = this.c;
        if (i15 >= 4096) {
            try {
                if (i15 > inputStream.available()) {
                    return null;
                }
            } catch (C0961y e10) {
                e10.l = true;
                throw e10;
            }
        }
        byte[] bArr = new byte[i7];
        System.arraycopy(this.d, this.f11854g, bArr, 0, i14);
        this.f11856i += this.f11852e;
        this.f11854g = 0;
        this.f11852e = 0;
        while (i14 < i7) {
            try {
                int i16 = inputStream.read(bArr, i14, i7 - i14);
                if (i16 == -1) {
                    throw C0961y.e();
                }
                this.f11856i += i16;
                i14 += i16;
            } catch (C0961y e11) {
                e11.l = true;
                throw e11;
            }
        }
        return bArr;
    }
}

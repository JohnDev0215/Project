package p;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import okio.ByteString;
import okio.SegmentedByteString;

/* JADX INFO: loaded from: classes.dex */
public final class fo implements ko, io, Cloneable, ByteChannel {
    public bb2 c;
    public long e;

    @Override // p.ko
    public final String A0(Charset charset) {
        return P0(this.e, charset);
    }

    @Override // p.io
    public final io B() {
        return this;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io C(int i) {
        b1(i);
        return this;
    }

    @Override // p.ko
    public final wo C0() {
        return new wo(this, 1);
    }

    public final void E0(long j, long j2, fo foVar) {
        v03.j(foVar, "out");
        q30.i(this.e, j, j2);
        if (j2 == 0) {
            return;
        }
        foVar.e += j2;
        bb2 bb2Var = this.c;
        while (true) {
            v03.h(bb2Var);
            long j3 = bb2Var.c - bb2Var.b;
            if (j < j3) {
                break;
            }
            j -= j3;
            bb2Var = bb2Var.f;
        }
        while (j2 > 0) {
            v03.h(bb2Var);
            bb2 bb2VarC = bb2Var.c();
            int i = bb2VarC.b + ((int) j);
            bb2VarC.b = i;
            bb2VarC.c = Math.min(i + ((int) j2), bb2VarC.c);
            bb2 bb2Var2 = foVar.c;
            if (bb2Var2 == null) {
                bb2VarC.g = bb2VarC;
                bb2VarC.f = bb2VarC;
                foVar.c = bb2VarC;
            } else {
                bb2 bb2Var3 = bb2Var2.g;
                v03.h(bb2Var3);
                bb2Var3.b(bb2VarC);
            }
            j2 -= (long) (bb2VarC.c - bb2VarC.b);
            bb2Var = bb2Var.f;
            j = 0;
        }
    }

    public final byte G0(long j) {
        q30.i(this.e, j, 1L);
        bb2 bb2Var = this.c;
        if (bb2Var == null) {
            v03.h(null);
            throw null;
        }
        long j2 = this.e;
        if (j2 - j < j) {
            while (j2 > j) {
                bb2Var = bb2Var.g;
                v03.h(bb2Var);
                j2 -= (long) (bb2Var.c - bb2Var.b);
            }
            return bb2Var.a[(int) ((((long) bb2Var.b) + j) - j2)];
        }
        long j3 = 0;
        while (true) {
            int i = bb2Var.c;
            int i2 = bb2Var.b;
            long j4 = ((long) (i - i2)) + j3;
            if (j4 > j) {
                return bb2Var.a[(int) ((((long) i2) + j) - j3)];
            }
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
            j3 = j4;
        }
    }

    public final long H0(byte b, long j, long j2) {
        bb2 bb2Var;
        long j3 = 0;
        if (0 > j || j > j2) {
            throw new IllegalArgumentException(("size=" + this.e + " fromIndex=" + j + " toIndex=" + j2).toString());
        }
        long j4 = this.e;
        if (j2 > j4) {
            j2 = j4;
        }
        if (j == j2 || (bb2Var = this.c) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                bb2Var = bb2Var.g;
                v03.h(bb2Var);
                j4 -= (long) (bb2Var.c - bb2Var.b);
            }
            while (j4 < j2) {
                int iMin = (int) Math.min(bb2Var.c, (((long) bb2Var.b) + j2) - j4);
                for (int i = (int) ((((long) bb2Var.b) + j) - j4); i < iMin; i++) {
                    if (bb2Var.a[i] == b) {
                        return ((long) (i - bb2Var.b)) + j4;
                    }
                }
                j4 += (long) (bb2Var.c - bb2Var.b);
                bb2Var = bb2Var.f;
                v03.h(bb2Var);
                j = j4;
            }
            return -1L;
        }
        while (true) {
            long j5 = ((long) (bb2Var.c - bb2Var.b)) + j3;
            if (j5 > j) {
                break;
            }
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
            j3 = j5;
        }
        while (j3 < j2) {
            int iMin2 = (int) Math.min(bb2Var.c, (((long) bb2Var.b) + j2) - j3);
            for (int i2 = (int) ((((long) bb2Var.b) + j) - j3); i2 < iMin2; i2++) {
                if (bb2Var.a[i2] == b) {
                    return ((long) (i2 - bb2Var.b)) + j3;
                }
            }
            j3 += (long) (bb2Var.c - bb2Var.b);
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
            j = j3;
        }
        return -1L;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io I(int i) {
        Z0(i);
        return this;
    }

    public final long I0(long j, ByteString byteString) {
        long j2 = j;
        v03.j(byteString, "bytes");
        if (byteString.e() <= 0) {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
        long j3 = 0;
        if (j2 < 0) {
            throw new IllegalArgumentException(u4.g("fromIndex < 0: ", j2).toString());
        }
        bb2 bb2Var = this.c;
        if (bb2Var != null) {
            long j4 = this.e;
            if (j4 - j2 < j2) {
                while (j4 > j2) {
                    bb2Var = bb2Var.g;
                    v03.h(bb2Var);
                    j4 -= (long) (bb2Var.c - bb2Var.b);
                }
                byte[] bArrJ = byteString.j();
                byte b = bArrJ[0];
                int iE = byteString.e();
                long j5 = (this.e - ((long) iE)) + 1;
                while (j4 < j5) {
                    int iMin = (int) Math.min(bb2Var.c, (((long) bb2Var.b) + j5) - j4);
                    for (int i = (int) ((((long) bb2Var.b) + j2) - j4); i < iMin; i++) {
                        if (bb2Var.a[i] == b && d.a(bb2Var, i + 1, bArrJ, iE)) {
                            return ((long) (i - bb2Var.b)) + j4;
                        }
                    }
                    j4 += (long) (bb2Var.c - bb2Var.b);
                    bb2Var = bb2Var.f;
                    v03.h(bb2Var);
                    j2 = j4;
                }
            } else {
                while (true) {
                    long j6 = ((long) (bb2Var.c - bb2Var.b)) + j3;
                    if (j6 > j2) {
                        break;
                    }
                    bb2Var = bb2Var.f;
                    v03.h(bb2Var);
                    j3 = j6;
                }
                byte[] bArrJ2 = byteString.j();
                byte b2 = bArrJ2[0];
                int iE2 = byteString.e();
                long j7 = (this.e - ((long) iE2)) + 1;
                while (j3 < j7) {
                    int iMin2 = (int) Math.min(bb2Var.c, (((long) bb2Var.b) + j7) - j3);
                    for (int i2 = (int) ((((long) bb2Var.b) + j2) - j3); i2 < iMin2; i2++) {
                        if (bb2Var.a[i2] == b2 && d.a(bb2Var, i2 + 1, bArrJ2, iE2)) {
                            return ((long) (i2 - bb2Var.b)) + j3;
                        }
                    }
                    j3 += (long) (bb2Var.c - bb2Var.b);
                    bb2Var = bb2Var.f;
                    v03.h(bb2Var);
                    j2 = j3;
                }
            }
        }
        return -1L;
    }

    public final long J0(long j, ByteString byteString) {
        int i;
        int i2;
        int i3;
        int i4;
        v03.j(byteString, "targetBytes");
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("fromIndex < 0: ", j).toString());
        }
        bb2 bb2Var = this.c;
        if (bb2Var == null) {
            return -1L;
        }
        long j3 = this.e;
        if (j3 - j < j) {
            while (j3 > j) {
                bb2Var = bb2Var.g;
                v03.h(bb2Var);
                j3 -= (long) (bb2Var.c - bb2Var.b);
            }
            if (byteString.e() == 2) {
                byte bK = byteString.k(0);
                byte bK2 = byteString.k(1);
                while (j3 < this.e) {
                    i3 = (int) ((((long) bb2Var.b) + j) - j3);
                    int i5 = bb2Var.c;
                    while (i3 < i5) {
                        byte b = bb2Var.a[i3];
                        if (b == bK || b == bK2) {
                            i4 = bb2Var.b;
                        } else {
                            i3++;
                        }
                    }
                    j3 += (long) (bb2Var.c - bb2Var.b);
                    bb2Var = bb2Var.f;
                    v03.h(bb2Var);
                    j = j3;
                }
                return -1L;
            }
            byte[] bArrJ = byteString.j();
            while (j3 < this.e) {
                i3 = (int) ((((long) bb2Var.b) + j) - j3);
                int i6 = bb2Var.c;
                while (i3 < i6) {
                    byte b2 = bb2Var.a[i3];
                    for (byte b3 : bArrJ) {
                        if (b2 == b3) {
                            i4 = bb2Var.b;
                        }
                    }
                    i3++;
                }
                j3 += (long) (bb2Var.c - bb2Var.b);
                bb2Var = bb2Var.f;
                v03.h(bb2Var);
                j = j3;
            }
            return -1L;
            return ((long) (i3 - i4)) + j3;
        }
        while (true) {
            long j4 = ((long) (bb2Var.c - bb2Var.b)) + j2;
            if (j4 > j) {
                break;
            }
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
            j2 = j4;
        }
        if (byteString.e() == 2) {
            byte bK3 = byteString.k(0);
            byte bK4 = byteString.k(1);
            while (j2 < this.e) {
                i = (int) ((((long) bb2Var.b) + j) - j2);
                int i7 = bb2Var.c;
                while (i < i7) {
                    byte b4 = bb2Var.a[i];
                    if (b4 == bK3 || b4 == bK4) {
                        i2 = bb2Var.b;
                    } else {
                        i++;
                    }
                }
                j2 += (long) (bb2Var.c - bb2Var.b);
                bb2Var = bb2Var.f;
                v03.h(bb2Var);
                j = j2;
            }
            return -1L;
        }
        byte[] bArrJ2 = byteString.j();
        while (j2 < this.e) {
            i = (int) ((((long) bb2Var.b) + j) - j2);
            int i8 = bb2Var.c;
            while (i < i8) {
                byte b5 = bb2Var.a[i];
                for (byte b6 : bArrJ2) {
                    if (b5 == b6) {
                        i2 = bb2Var.b;
                    }
                }
                i++;
            }
            j2 += (long) (bb2Var.c - bb2Var.b);
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
            j = j2;
        }
        return -1L;
        return ((long) (i - i2)) + j2;
    }

    public final int K0(byte[] bArr, int i, int i2) {
        v03.j(bArr, "sink");
        q30.i(bArr.length, i, i2);
        bb2 bb2Var = this.c;
        if (bb2Var == null) {
            return -1;
        }
        int iMin = Math.min(i2, bb2Var.c - bb2Var.b);
        int i3 = bb2Var.b;
        rb.i0(i, i3, i3 + iMin, bb2Var.a, bArr);
        int i4 = bb2Var.b + iMin;
        bb2Var.b = i4;
        this.e -= (long) iMin;
        if (i4 == bb2Var.c) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        }
        return iMin;
    }

    @Override // p.io
    public final long L(de2 de2Var) {
        v03.j(de2Var, "source");
        long j = 0;
        while (true) {
            long jT = de2Var.t(this, 8192L);
            if (jT == -1) {
                return j;
            }
            j += jT;
        }
    }

    public final eo L0(eo eoVar) {
        v03.j(eoVar, "unsafeCursor");
        byte[] bArr = d.a;
        if (eoVar == q30.a) {
            eoVar = new eo();
        }
        if (eoVar.c != null) {
            throw new IllegalStateException("already attached to a buffer".toString());
        }
        eoVar.c = this;
        eoVar.e = true;
        return eoVar;
    }

    @Override // p.ko
    public final String M() {
        return g0(Long.MAX_VALUE);
    }

    public final byte[] M0(long j) throws EOFException {
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(u4.g("byteCount: ", j).toString());
        }
        if (this.e < j) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j];
        readFully(bArr);
        return bArr;
    }

    public final long N0() throws EOFException {
        if (this.e == 0) {
            throw new EOFException();
        }
        long j = -7;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        long j2 = 0;
        do {
            bb2 bb2Var = this.c;
            v03.h(bb2Var);
            int i2 = bb2Var.b;
            int i3 = bb2Var.c;
            while (i2 < i3) {
                byte b = bb2Var.a[i2];
                if (b >= 48 && b <= 57) {
                    int i4 = 48 - b;
                    if (j2 < -922337203685477580L || (j2 == -922337203685477580L && i4 < j)) {
                        fo foVar = new fo();
                        foVar.X0(j2);
                        foVar.W0(b);
                        if (!z) {
                            foVar.readByte();
                        }
                        throw new NumberFormatException("Number too large: ".concat(foVar.Q0()));
                    }
                    j2 = (j2 * 10) + ((long) i4);
                } else {
                    if (b != 45 || i != 0) {
                        z2 = true;
                        break;
                    }
                    j--;
                    z = true;
                }
                i2++;
                i++;
            }
            if (i2 == i3) {
                this.c = bb2Var.a();
                db2.a(bb2Var);
            } else {
                bb2Var.b = i2;
            }
            if (z2) {
                break;
            }
        } while (this.c != null);
        long j3 = this.e - ((long) i);
        this.e = j3;
        if (i >= (z ? 2 : 1)) {
            return z ? j2 : -j2;
        }
        if (j3 == 0) {
            throw new EOFException();
        }
        StringBuilder sbF = hf2.f(z ? "Expected a digit" : "Expected a digit or '-'", " but was 0x");
        byte bG0 = G0(0L);
        char[] cArr = zu.c;
        sbF.append(new String(new char[]{cArr[(bG0 >> 4) & 15], cArr[bG0 & 15]}));
        throw new NumberFormatException(sbF.toString());
    }

    @Override // p.ko
    public final int O() throws EOFException {
        int i = readInt();
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public final short O0() throws EOFException {
        short s = readShort();
        return (short) (((s & 255) << 8) | ((65280 & s) >>> 8));
    }

    @Override // p.ko
    public final long P(ByteString byteString) {
        v03.j(byteString, "bytes");
        return I0(0L, byteString);
    }

    public final String P0(long j, Charset charset) throws EOFException {
        v03.j(charset, "charset");
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(u4.g("byteCount: ", j).toString());
        }
        if (this.e < j) {
            throw new EOFException();
        }
        if (j == 0) {
            return "";
        }
        bb2 bb2Var = this.c;
        v03.h(bb2Var);
        int i = bb2Var.b;
        if (((long) i) + j > bb2Var.c) {
            return new String(M0(j), charset);
        }
        int i2 = (int) j;
        String str = new String(bb2Var.a, i, i2, charset);
        int i3 = bb2Var.b + i2;
        bb2Var.b = i3;
        this.e -= j;
        if (i3 == bb2Var.c) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        }
        return str;
    }

    @Override // p.ko
    public final boolean Q() {
        return this.e == 0;
    }

    public final String Q0() {
        return P0(this.e, cs.a);
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io R(int i) {
        W0(i);
        return this;
    }

    public final ByteString R0(int i) {
        if (i == 0) {
            return ByteString.k;
        }
        q30.i(this.e, 0L, i);
        bb2 bb2Var = this.c;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            v03.h(bb2Var);
            int i5 = bb2Var.c;
            int i6 = bb2Var.b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            bb2Var = bb2Var.f;
        }
        byte[][] bArr = new byte[i4][];
        int[] iArr = new int[i4 * 2];
        bb2 bb2Var2 = this.c;
        int i7 = 0;
        while (i2 < i) {
            v03.h(bb2Var2);
            bArr[i7] = bb2Var2.a;
            i2 += bb2Var2.c - bb2Var2.b;
            iArr[i7] = Math.min(i2, i);
            iArr[i7 + i4] = bb2Var2.b;
            bb2Var2.d = true;
            i7++;
            bb2Var2 = bb2Var2.f;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    public final bb2 S0(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        bb2 bb2Var = this.c;
        if (bb2Var == null) {
            bb2 bb2VarB = db2.b();
            this.c = bb2VarB;
            bb2VarB.g = bb2VarB;
            bb2VarB.f = bb2VarB;
            return bb2VarB;
        }
        bb2 bb2Var2 = bb2Var.g;
        v03.h(bb2Var2);
        if (bb2Var2.c + i <= 8192 && bb2Var2.e) {
            return bb2Var2;
        }
        bb2 bb2VarB2 = db2.b();
        bb2Var2.b(bb2VarB2);
        return bb2VarB2;
    }

    public final void T0(ByteString byteString) {
        v03.j(byteString, "byteString");
        byteString.t(this, byteString.e());
    }

    public final void U0(byte[] bArr) {
        v03.j(bArr, "source");
        V0(bArr, 0, bArr.length);
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io V(byte[] bArr) {
        U0(bArr);
        return this;
    }

    public final void V0(byte[] bArr, int i, int i2) {
        v03.j(bArr, "source");
        long j = i2;
        q30.i(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            bb2 bb2VarS0 = S0(1);
            int iMin = Math.min(i3 - i, 8192 - bb2VarS0.c);
            int i4 = i + iMin;
            rb.i0(bb2VarS0.c, i, i4, bArr, bb2VarS0.a);
            bb2VarS0.c += iMin;
            i = i4;
        }
        this.e += j;
    }

    public final void W0(int i) {
        bb2 bb2VarS0 = S0(1);
        int i2 = bb2VarS0.c;
        bb2VarS0.c = i2 + 1;
        bb2VarS0.a[i2] = (byte) i;
        this.e++;
    }

    public final void X() throws EOFException {
        y(this.e);
    }

    public final void X0(long j) {
        boolean z;
        byte[] bArr;
        if (j == 0) {
            W0(48);
            return;
        }
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                d1("-9223372036854775808");
                return;
            }
            z = true;
        } else {
            z = false;
        }
        if (j >= 100000000) {
            i = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i = 2;
        }
        if (z) {
            i++;
        }
        bb2 bb2VarS0 = S0(i);
        int i2 = bb2VarS0.c + i;
        while (true) {
            bArr = bb2VarS0.a;
            if (j == 0) {
                break;
            }
            long j2 = 10;
            i2--;
            bArr[i2] = d.a[(int) (j % j2)];
            j /= j2;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        bb2VarS0.c += i;
        this.e += (long) i;
    }

    @Override // p.pd2
    public final void Y(fo foVar, long j) {
        bb2 bb2VarB;
        v03.j(foVar, "source");
        if (foVar == this) {
            throw new IllegalArgumentException("source == this".toString());
        }
        q30.i(foVar.e, 0L, j);
        while (j > 0) {
            bb2 bb2Var = foVar.c;
            v03.h(bb2Var);
            int i = bb2Var.c;
            bb2 bb2Var2 = foVar.c;
            v03.h(bb2Var2);
            long j2 = i - bb2Var2.b;
            int i2 = 0;
            if (j < j2) {
                bb2 bb2Var3 = this.c;
                bb2 bb2Var4 = bb2Var3 != null ? bb2Var3.g : null;
                if (bb2Var4 != null && bb2Var4.e) {
                    if ((((long) bb2Var4.c) + j) - ((long) (bb2Var4.d ? 0 : bb2Var4.b)) <= 8192) {
                        bb2 bb2Var5 = foVar.c;
                        v03.h(bb2Var5);
                        bb2Var5.d(bb2Var4, (int) j);
                        foVar.e -= j;
                        this.e += j;
                        return;
                    }
                }
                bb2 bb2Var6 = foVar.c;
                v03.h(bb2Var6);
                int i3 = (int) j;
                if (i3 <= 0 || i3 > bb2Var6.c - bb2Var6.b) {
                    throw new IllegalArgumentException("byteCount out of range".toString());
                }
                if (i3 >= 1024) {
                    bb2VarB = bb2Var6.c();
                } else {
                    bb2VarB = db2.b();
                    int i4 = bb2Var6.b;
                    rb.i0(0, i4, i4 + i3, bb2Var6.a, bb2VarB.a);
                }
                bb2VarB.c = bb2VarB.b + i3;
                bb2Var6.b += i3;
                bb2 bb2Var7 = bb2Var6.g;
                v03.h(bb2Var7);
                bb2Var7.b(bb2VarB);
                foVar.c = bb2VarB;
            }
            bb2 bb2Var8 = foVar.c;
            v03.h(bb2Var8);
            long j3 = bb2Var8.c - bb2Var8.b;
            foVar.c = bb2Var8.a();
            bb2 bb2Var9 = this.c;
            if (bb2Var9 == null) {
                this.c = bb2Var8;
                bb2Var8.g = bb2Var8;
                bb2Var8.f = bb2Var8;
            } else {
                bb2 bb2Var10 = bb2Var9.g;
                v03.h(bb2Var10);
                bb2Var10.b(bb2Var8);
                bb2 bb2Var11 = bb2Var8.g;
                if (bb2Var11 == bb2Var8) {
                    throw new IllegalStateException("cannot compact".toString());
                }
                v03.h(bb2Var11);
                if (bb2Var11.e) {
                    int i5 = bb2Var8.c - bb2Var8.b;
                    bb2 bb2Var12 = bb2Var8.g;
                    v03.h(bb2Var12);
                    int i6 = 8192 - bb2Var12.c;
                    bb2 bb2Var13 = bb2Var8.g;
                    v03.h(bb2Var13);
                    if (!bb2Var13.d) {
                        bb2 bb2Var14 = bb2Var8.g;
                        v03.h(bb2Var14);
                        i2 = bb2Var14.b;
                    }
                    if (i5 <= i6 + i2) {
                        bb2 bb2Var15 = bb2Var8.g;
                        v03.h(bb2Var15);
                        bb2Var8.d(bb2Var15, i5);
                        bb2Var8.a();
                        db2.a(bb2Var8);
                    }
                }
            }
            foVar.e -= j3;
            this.e += j3;
            j -= j3;
        }
    }

    public final void Y0(long j) {
        if (j == 0) {
            W0(48);
            return;
        }
        long j2 = (j >>> 1) | j;
        long j3 = j2 | (j2 >>> 2);
        long j4 = j3 | (j3 >>> 4);
        long j5 = j4 | (j4 >>> 8);
        long j6 = j5 | (j5 >>> 16);
        long j7 = j6 | (j6 >>> 32);
        long j8 = j7 - ((j7 >>> 1) & 6148914691236517205L);
        long j9 = ((j8 >>> 2) & 3689348814741910323L) + (j8 & 3689348814741910323L);
        long j10 = ((j9 >>> 4) + j9) & 1085102592571150095L;
        long j11 = j10 + (j10 >>> 8);
        long j12 = j11 + (j11 >>> 16);
        int i = (int) ((((j12 & 63) + ((j12 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        bb2 bb2VarS0 = S0(i);
        int i2 = bb2VarS0.c;
        for (int i3 = (i2 + i) - 1; i3 >= i2; i3--) {
            bb2VarS0.a[i3] = d.a[(int) (15 & j)];
            j >>>= 4;
        }
        bb2VarS0.c += i;
        this.e += (long) i;
    }

    public final void Z0(int i) {
        bb2 bb2VarS0 = S0(4);
        int i2 = bb2VarS0.c;
        byte[] bArr = bb2VarS0.a;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >>> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
        bb2VarS0.c = i2 + 4;
        this.e += 4;
    }

    @Override // p.ko
    public final boolean a(long j) {
        return this.e >= j;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io a0(ByteString byteString) {
        T0(byteString);
        return this;
    }

    public final void a1(long j) {
        bb2 bb2VarS0 = S0(8);
        int i = bb2VarS0.c;
        byte[] bArr = bb2VarS0.a;
        bArr[i] = (byte) ((j >>> 56) & 255);
        bArr[i + 1] = (byte) ((j >>> 48) & 255);
        bArr[i + 2] = (byte) ((j >>> 40) & 255);
        bArr[i + 3] = (byte) ((j >>> 32) & 255);
        bArr[i + 4] = (byte) ((j >>> 24) & 255);
        bArr[i + 5] = (byte) ((j >>> 16) & 255);
        bArr[i + 6] = (byte) ((j >>> 8) & 255);
        bArr[i + 7] = (byte) (j & 255);
        bb2VarS0.c = i + 8;
        this.e += 8;
    }

    @Override // p.ko
    public final long b0(ByteString byteString) {
        v03.j(byteString, "targetBytes");
        return J0(0L, byteString);
    }

    public final void b1(int i) {
        bb2 bb2VarS0 = S0(2);
        int i2 = bb2VarS0.c;
        byte[] bArr = bb2VarS0.a;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 1] = (byte) (i & 255);
        bb2VarS0.c = i2 + 2;
        this.e += 2;
    }

    @Override // p.io
    public final io c0() {
        return this;
    }

    public final void c1(String str, int i, int i2, Charset charset) {
        v03.j(str, "string");
        if (i < 0) {
            throw new IllegalArgumentException(u4.e("beginIndex < 0: ", i).toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(hf2.c("endIndex < beginIndex: ", i2, " < ", i).toString());
        }
        if (i2 > str.length()) {
            StringBuilder sbO = u4.o("endIndex > string.length: ", i2, " > ");
            sbO.append(str.length());
            throw new IllegalArgumentException(sbO.toString().toString());
        }
        if (v03.b(charset, cs.a)) {
            e1(str, i, i2);
            return;
        }
        String strSubstring = str.substring(i, i2);
        v03.i(strSubstring, "substring(...)");
        byte[] bytes = strSubstring.getBytes(charset);
        v03.i(bytes, "getBytes(...)");
        V0(bytes, 0, bytes.length);
    }

    public final Object clone() {
        fo foVar = new fo();
        if (this.e != 0) {
            bb2 bb2Var = this.c;
            v03.h(bb2Var);
            bb2 bb2VarC = bb2Var.c();
            foVar.c = bb2VarC;
            bb2VarC.g = bb2VarC;
            bb2VarC.f = bb2VarC;
            for (bb2 bb2Var2 = bb2Var.f; bb2Var2 != bb2Var; bb2Var2 = bb2Var2.f) {
                bb2 bb2Var3 = bb2VarC.g;
                v03.h(bb2Var3);
                v03.h(bb2Var2);
                bb2Var3.b(bb2Var2.c());
            }
            foVar.e = this.e;
        }
        return foVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, p.pd2
    public final void close() {
    }

    @Override // p.ko, p.io
    public final fo d() {
        return this;
    }

    @Override // p.ko
    public final long d0(io ioVar) {
        long j = this.e;
        if (j > 0) {
            ioVar.Y(this, j);
        }
        return j;
    }

    public final void d1(String str) {
        v03.j(str, "string");
        e1(str, 0, str.length());
    }

    @Override // p.de2
    public final tl2 e() {
        return tl2.d;
    }

    public final void e1(String str, int i, int i2) {
        char cCharAt;
        v03.j(str, "string");
        if (i < 0) {
            throw new IllegalArgumentException(u4.e("beginIndex < 0: ", i).toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(hf2.c("endIndex < beginIndex: ", i2, " < ", i).toString());
        }
        if (i2 > str.length()) {
            StringBuilder sbO = u4.o("endIndex > string.length: ", i2, " > ");
            sbO.append(str.length());
            throw new IllegalArgumentException(sbO.toString().toString());
        }
        while (i < i2) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 < 128) {
                bb2 bb2VarS0 = S0(1);
                int i3 = bb2VarS0.c - i;
                int iMin = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                byte[] bArr = bb2VarS0.a;
                bArr[i + i3] = (byte) cCharAt2;
                while (true) {
                    i = i4;
                    if (i >= iMin || (cCharAt = str.charAt(i)) >= 128) {
                        break;
                    }
                    i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt;
                }
                int i5 = bb2VarS0.c;
                int i6 = (i3 + i) - i5;
                bb2VarS0.c = i5 + i6;
                this.e += (long) i6;
            } else {
                if (cCharAt2 < 2048) {
                    bb2 bb2VarS02 = S0(2);
                    int i7 = bb2VarS02.c;
                    byte[] bArr2 = bb2VarS02.a;
                    bArr2[i7] = (byte) ((cCharAt2 >> 6) | 192);
                    bArr2[i7 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    bb2VarS02.c = i7 + 2;
                    this.e += 2;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    bb2 bb2VarS03 = S0(3);
                    int i8 = bb2VarS03.c;
                    byte[] bArr3 = bb2VarS03.a;
                    bArr3[i8] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i8 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i8 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    bb2VarS03.c = i8 + 3;
                    this.e += 3;
                } else {
                    int i9 = i + 1;
                    char cCharAt3 = i9 < i2 ? str.charAt(i9) : (char) 0;
                    if (cCharAt2 > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        W0(63);
                        i = i9;
                    } else {
                        int i10 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        bb2 bb2VarS04 = S0(4);
                        int i11 = bb2VarS04.c;
                        byte[] bArr4 = bb2VarS04.a;
                        bArr4[i11] = (byte) ((i10 >> 18) | 240);
                        bArr4[i11 + 1] = (byte) (((i10 >> 12) & 63) | 128);
                        bArr4[i11 + 2] = (byte) (((i10 >> 6) & 63) | 128);
                        bArr4[i11 + 3] = (byte) ((i10 & 63) | 128);
                        bb2VarS04.c = i11 + 4;
                        this.e += 4;
                        i += 2;
                    }
                }
                i++;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof fo) {
                long j = this.e;
                fo foVar = (fo) obj;
                if (j == foVar.e) {
                    if (j != 0) {
                        bb2 bb2Var = this.c;
                        v03.h(bb2Var);
                        bb2 bb2Var2 = foVar.c;
                        v03.h(bb2Var2);
                        int i = bb2Var.b;
                        int i2 = bb2Var2.b;
                        long j2 = 0;
                        while (j2 < this.e) {
                            long jMin = Math.min(bb2Var.c - i, bb2Var2.c - i2);
                            long j3 = 0;
                            while (j3 < jMin) {
                                int i3 = i + 1;
                                byte b = bb2Var.a[i];
                                int i4 = i2 + 1;
                                if (b == bb2Var2.a[i2]) {
                                    j3++;
                                    i2 = i4;
                                    i = i3;
                                }
                            }
                            if (i == bb2Var.c) {
                                bb2 bb2Var3 = bb2Var.f;
                                v03.h(bb2Var3);
                                i = bb2Var3.b;
                                bb2Var = bb2Var3;
                            }
                            if (i2 == bb2Var2.c) {
                                bb2Var2 = bb2Var2.f;
                                v03.h(bb2Var2);
                                i2 = bb2Var2.b;
                            }
                            j2 += jMin;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // p.ko
    public final long f0() throws EOFException {
        long j = readLong();
        return ((j & 255) << 56) | (((-72057594037927936L) & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public final void f1(int i) {
        String str;
        if (i < 128) {
            W0(i);
            return;
        }
        if (i < 2048) {
            bb2 bb2VarS0 = S0(2);
            int i2 = bb2VarS0.c;
            byte[] bArr = bb2VarS0.a;
            bArr[i2] = (byte) ((i >> 6) | 192);
            bArr[i2 + 1] = (byte) ((i & 63) | 128);
            bb2VarS0.c = i2 + 2;
            this.e += 2;
            return;
        }
        if (55296 <= i && i < 57344) {
            W0(63);
            return;
        }
        if (i < 65536) {
            bb2 bb2VarS02 = S0(3);
            int i3 = bb2VarS02.c;
            byte[] bArr2 = bb2VarS02.a;
            bArr2[i3] = (byte) ((i >> 12) | 224);
            bArr2[i3 + 1] = (byte) (((i >> 6) & 63) | 128);
            bArr2[i3 + 2] = (byte) ((i & 63) | 128);
            bb2VarS02.c = i3 + 3;
            this.e += 3;
            return;
        }
        if (i <= 1114111) {
            bb2 bb2VarS03 = S0(4);
            int i4 = bb2VarS03.c;
            byte[] bArr3 = bb2VarS03.a;
            bArr3[i4] = (byte) ((i >> 18) | 240);
            bArr3[i4 + 1] = (byte) (((i >> 12) & 63) | 128);
            bArr3[i4 + 2] = (byte) (((i >> 6) & 63) | 128);
            bArr3[i4 + 3] = (byte) ((i & 63) | 128);
            bb2VarS03.c = i4 + 4;
            this.e += 4;
            return;
        }
        StringBuilder sb = new StringBuilder("Unexpected code point: 0x");
        if (i != 0) {
            char[] cArr = zu.c;
            int i5 = 0;
            char[] cArr2 = {cArr[(i >> 28) & 15], cArr[(i >> 24) & 15], cArr[(i >> 20) & 15], cArr[(i >> 16) & 15], cArr[(i >> 12) & 15], cArr[(i >> 8) & 15], cArr[(i >> 4) & 15], cArr[i & 15]};
            while (i5 < 8 && cArr2[i5] == '0') {
                i5++;
            }
            if (i5 < 0) {
                throw new IndexOutOfBoundsException(u4.f("startIndex: ", i5, ", endIndex: 8, size: 8"));
            }
            if (i5 > 8) {
                throw new IllegalArgumentException(u4.f("startIndex: ", i5, " > endIndex: 8"));
            }
            str = new String(cArr2, i5, 8 - i5);
        } else {
            str = "0";
        }
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // p.io, p.pd2, java.io.Flushable
    public final void flush() {
    }

    @Override // p.ko
    public final String g0(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("limit < 0: ", j).toString());
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jH0 = H0((byte) 10, 0L, j2);
        if (jH0 != -1) {
            return d.b(this, jH0);
        }
        if (j2 < this.e && G0(j2 - 1) == 13 && G0(j2) == 10) {
            return d.b(this, j2);
        }
        fo foVar = new fo();
        E0(0L, Math.min(32, this.e), foVar);
        throw new EOFException("\\n not found: limit=" + Math.min(this.e, j) + " content=" + foVar.v(foVar.e).f() + (char) 8230);
    }

    public final int hashCode() {
        bb2 bb2Var = this.c;
        if (bb2Var == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = bb2Var.c;
            for (int i3 = bb2Var.b; i3 < i2; i3++) {
                i = (i * 31) + bb2Var.a[i3];
            }
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
        } while (bb2Var != this.c);
        return i;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io l(byte[] bArr, int i, int i2) {
        V0(bArr, i, i2);
        return this;
    }

    public final long o0() {
        long j = this.e;
        if (j == 0) {
            return 0L;
        }
        bb2 bb2Var = this.c;
        v03.h(bb2Var);
        bb2 bb2Var2 = bb2Var.g;
        v03.h(bb2Var2);
        int i = bb2Var2.c;
        if (i < 8192 && bb2Var2.e) {
            j -= (long) (i - bb2Var2.b);
        }
        return j;
    }

    @Override // p.ko
    public final void p(fo foVar, long j) throws EOFException {
        v03.j(foVar, "sink");
        long j2 = this.e;
        if (j2 >= j) {
            foVar.Y(this, j);
        } else {
            foVar.Y(this, j2);
            throw new EOFException();
        }
    }

    @Override // p.ko
    public final int p0(ar1 ar1Var) throws EOFException {
        v03.j(ar1Var, "options");
        int iC = d.c(this, ar1Var, false);
        if (iC == -1) {
            return -1;
        }
        y(ar1Var.c[iC].e());
        return iC;
    }

    @Override // p.ko
    public final w02 peek() {
        return v03.d(new ss1(this));
    }

    @Override // p.ko
    public final void q0(long j) throws EOFException {
        if (this.e < j) {
            throw new EOFException();
        }
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io r(String str, int i, int i2) {
        e1(str, i, i2);
        return this;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        v03.j(byteBuffer, "sink");
        bb2 bb2Var = this.c;
        if (bb2Var == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), bb2Var.c - bb2Var.b);
        byteBuffer.put(bb2Var.a, bb2Var.b, iMin);
        int i = bb2Var.b + iMin;
        bb2Var.b = i;
        this.e -= (long) iMin;
        if (i == bb2Var.c) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        }
        return iMin;
    }

    @Override // p.ko
    public final byte readByte() throws EOFException {
        if (this.e == 0) {
            throw new EOFException();
        }
        bb2 bb2Var = this.c;
        v03.h(bb2Var);
        int i = bb2Var.b;
        int i2 = bb2Var.c;
        int i3 = i + 1;
        byte b = bb2Var.a[i];
        this.e--;
        if (i3 == i2) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        } else {
            bb2Var.b = i3;
        }
        return b;
    }

    @Override // p.ko
    public final void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iK0 = K0(bArr, i, bArr.length - i);
            if (iK0 == -1) {
                throw new EOFException();
            }
            i += iK0;
        }
    }

    @Override // p.ko
    public final int readInt() throws EOFException {
        if (this.e < 4) {
            throw new EOFException();
        }
        bb2 bb2Var = this.c;
        v03.h(bb2Var);
        int i = bb2Var.b;
        int i2 = bb2Var.c;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = bb2Var.a;
        int i3 = i + 3;
        int i4 = ((bArr[i + 1] & 255) << 16) | ((bArr[i] & 255) << 24) | ((bArr[i + 2] & 255) << 8);
        int i5 = i + 4;
        int i6 = i4 | (bArr[i3] & 255);
        this.e -= 4;
        if (i5 == i2) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        } else {
            bb2Var.b = i5;
        }
        return i6;
    }

    @Override // p.ko
    public final long readLong() throws EOFException {
        if (this.e < 8) {
            throw new EOFException();
        }
        bb2 bb2Var = this.c;
        v03.h(bb2Var);
        int i = bb2Var.b;
        int i2 = bb2Var.c;
        if (i2 - i < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
        }
        byte[] bArr = bb2Var.a;
        int i3 = i + 7;
        long j = ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8);
        int i4 = i + 8;
        long j2 = j | (((long) bArr[i3]) & 255);
        this.e -= 8;
        if (i4 == i2) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        } else {
            bb2Var.b = i4;
        }
        return j2;
    }

    @Override // p.ko
    public final short readShort() throws EOFException {
        if (this.e < 2) {
            throw new EOFException();
        }
        bb2 bb2Var = this.c;
        v03.h(bb2Var);
        int i = bb2Var.b;
        int i2 = bb2Var.c;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        int i3 = i + 1;
        byte[] bArr = bb2Var.a;
        int i4 = (bArr[i] & 255) << 8;
        int i5 = i + 2;
        int i6 = (bArr[i3] & 255) | i4;
        this.e -= 2;
        if (i5 == i2) {
            this.c = bb2Var.a();
            db2.a(bb2Var);
        } else {
            bb2Var.b = i5;
        }
        return (short) i6;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io s(long j) {
        Y0(j);
        return this;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io s0(String str) {
        d1(str);
        return this;
    }

    @Override // p.de2
    public final long t(fo foVar, long j) {
        v03.j(foVar, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        long j2 = this.e;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        foVar.Y(this, j);
        return j;
    }

    @Override // p.io
    public final /* bridge */ /* synthetic */ io t0(long j) {
        X0(j);
        return this;
    }

    public final String toString() {
        long j = this.e;
        if (j <= 2147483647L) {
            return R0((int) j).toString();
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.e).toString());
    }

    @Override // p.ko
    public final ByteString v(long j) throws EOFException {
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(u4.g("byteCount: ", j).toString());
        }
        if (this.e < j) {
            throw new EOFException();
        }
        if (j < 4096) {
            return new ByteString(M0(j));
        }
        ByteString byteStringR0 = R0((int) j);
        y(j);
        return byteStringR0;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        v03.j(byteBuffer, "source");
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            bb2 bb2VarS0 = S0(1);
            int iMin = Math.min(i, 8192 - bb2VarS0.c);
            byteBuffer.get(bb2VarS0.a, bb2VarS0.c, iMin);
            i -= iMin;
            bb2VarS0.c += iMin;
        }
        this.e += (long) iRemaining;
        return iRemaining;
    }

    @Override // p.ko
    public final boolean x0(long j, ByteString byteString) {
        v03.j(byteString, "bytes");
        int iE = byteString.e();
        if (j < 0 || iE < 0 || this.e - j < iE || byteString.e() < iE) {
            return false;
        }
        for (int i = 0; i < iE; i++) {
            if (G0(((long) i) + j) != byteString.k(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // p.ko
    public final void y(long j) throws EOFException {
        while (j > 0) {
            bb2 bb2Var = this.c;
            if (bb2Var == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, bb2Var.c - bb2Var.b);
            long j2 = iMin;
            this.e -= j2;
            j -= j2;
            int i = bb2Var.b + iMin;
            bb2Var.b = i;
            if (i == bb2Var.c) {
                this.c = bb2Var.a();
                db2.a(bb2Var);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a2 A[EDGE_INSN: B:44:0x00a2->B:38:0x00a2 BREAK  A[LOOP:0: B:5:0x000c->B:46:?], SYNTHETIC] */
    @Override // p.ko
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long y0() throws java.io.EOFException {
        /*
            r14 = this;
            long r0 = r14.e
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto La9
            r0 = 0
            r1 = r0
            r6 = r1
            r4 = r2
        Lc:
            p.bb2 r7 = r14.c
            p.v03.h(r7)
            int r8 = r7.b
            int r9 = r7.c
        L15:
            if (r8 >= r9) goto L8e
            byte[] r10 = r7.a
            r10 = r10[r8]
            r11 = 48
            if (r10 < r11) goto L26
            r11 = 57
            if (r10 > r11) goto L26
            int r11 = r10 + (-48)
            goto L3b
        L26:
            r11 = 97
            if (r10 < r11) goto L31
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L31
            int r11 = r10 + (-87)
            goto L3b
        L31:
            r11 = 65
            if (r10 < r11) goto L66
            r11 = 70
            if (r10 > r11) goto L66
            int r11 = r10 + (-55)
        L3b:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L4b
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r1 = r1 + 1
            goto L15
        L4b:
            p.fo r0 = new p.fo
            r0.<init>()
            r0.Y0(r4)
            r0.W0(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r0 = r0.Q0()
            java.lang.String r2 = "Number too large: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L66:
            r6 = 1
            if (r1 == 0) goto L6a
            goto L8e
        L6a:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            r2 = 2
            char[] r2 = new char[r2]
            char[] r3 = p.zu.c
            int r4 = r10 >> 4
            r4 = r4 & 15
            char r4 = r3[r4]
            r2[r0] = r4
            r0 = r10 & 15
            char r0 = r3[r0]
            r2[r6] = r0
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L8e:
            if (r8 != r9) goto L9a
            p.bb2 r8 = r7.a()
            r14.c = r8
            p.db2.a(r7)
            goto L9c
        L9a:
            r7.b = r8
        L9c:
            if (r6 != 0) goto La2
            p.bb2 r7 = r14.c
            if (r7 != 0) goto Lc
        La2:
            long r2 = r14.e
            long r0 = (long) r1
            long r2 = r2 - r0
            r14.e = r2
            return r4
        La9:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.fo.y0():long");
    }

    @Override // p.ko
    public final int read(byte[] bArr) {
        return K0(bArr, 0, bArr.length);
    }
}

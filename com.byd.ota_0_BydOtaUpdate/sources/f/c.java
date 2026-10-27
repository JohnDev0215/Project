package f;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okio.ByteString;
import okio.SegmentedByteString;

/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements e, d, Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f3700c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public n f3701d;
    public long s;

    /* JADX INFO: compiled from: Buffer.java */
    public class a extends OutputStream {
        public a() {
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return c.this + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            c.this.A((byte) i2);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            c.this.g(bArr, i2, i3);
        }
    }

    /* JADX INFO: compiled from: Buffer.java */
    public class b extends InputStream {
        public b() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(c.this.s, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            c cVar = c.this;
            if (cVar.s > 0) {
                return cVar.readByte() & 255;
            }
            return -1;
        }

        public String toString() {
            return c.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            return c.this.read(bArr, i2, i3);
        }
    }

    @Override // f.e
    public long B(byte b2) {
        return N(b2, 0L, Long.MAX_VALUE);
    }

    @Override // f.e
    public byte[] C(long j) throws EOFException {
        s.b(this.s, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    @Override // f.e
    public boolean D(long j, ByteString byteString) {
        return P(j, byteString, 0, byteString.size());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5 A[EDGE_INSN: B:44:0x00a5->B:38:0x00a5 BREAK  A[LOOP:0: B:5:0x000b->B:46:?], SYNTHETIC] */
    @Override // f.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long E() {
        /*
            r14 = this;
            long r0 = r14.s
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lac
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            f.n r6 = r14.f3701d
            byte[] r7 = r6.f3721a
            int r8 = r6.f3722b
            int r9 = r6.f3723c
        L13:
            if (r8 >= r9) goto L91
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L3a
        L22:
            r11 = 97
            if (r10 < r11) goto L2f
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2f
            int r11 = r10 + (-97)
        L2c:
            int r11 = r11 + 10
            goto L3a
        L2f:
            r11 = 65
            if (r10 < r11) goto L72
            r11 = 70
            if (r10 > r11) goto L72
            int r11 = r10 + (-65)
            goto L2c
        L3a:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L4a
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L4a:
            f.c r0 = new f.c
            r0.<init>()
            f.c r0 = r0.m(r4)
            f.c r0 = r0.A(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.G()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L72:
            if (r0 == 0) goto L76
            r1 = 1
            goto L91
        L76:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.append(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L91:
            if (r8 != r9) goto L9d
            f.n r7 = r6.b()
            r14.f3701d = r7
            f.o.a(r6)
            goto L9f
        L9d:
            r6.f3722b = r8
        L9f:
            if (r1 != 0) goto La5
            f.n r6 = r14.f3701d
            if (r6 != 0) goto Lb
        La5:
            long r1 = r14.s
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.s = r1
            return r4
        Lac:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f.c.E():long");
    }

    @Override // f.e
    public InputStream F() {
        return new b();
    }

    @Override // f.e
    public String G() {
        try {
            return S(this.s, s.f3734a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public void H() {
        try {
            skip(this.s);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public c clone() {
        c cVar = new c();
        if (this.s == 0) {
            return cVar;
        }
        n nVar = new n(this.f3701d);
        cVar.f3701d = nVar;
        nVar.f3727g = nVar;
        nVar.f3726f = nVar;
        n nVar2 = this.f3701d;
        while (true) {
            nVar2 = nVar2.f3726f;
            if (nVar2 == this.f3701d) {
                cVar.s = this.s;
                return cVar;
            }
            cVar.f3701d.f3727g.c(new n(nVar2));
        }
    }

    public long J() {
        long j = this.s;
        if (j == 0) {
            return 0L;
        }
        n nVar = this.f3701d.f3727g;
        int i2 = nVar.f3723c;
        return (i2 >= 8192 || !nVar.f3725e) ? j : j - ((long) (i2 - nVar.f3722b));
    }

    public c K(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        s.b(this.s, j, j2);
        if (j2 == 0) {
            return this;
        }
        cVar.s += j2;
        n nVar = this.f3701d;
        while (true) {
            int i2 = nVar.f3723c;
            int i3 = nVar.f3722b;
            if (j < i2 - i3) {
                break;
            }
            j -= (long) (i2 - i3);
            nVar = nVar.f3726f;
        }
        while (j2 > 0) {
            n nVar2 = new n(nVar);
            int i4 = (int) (((long) nVar2.f3722b) + j);
            nVar2.f3722b = i4;
            nVar2.f3723c = Math.min(i4 + ((int) j2), nVar2.f3723c);
            n nVar3 = cVar.f3701d;
            if (nVar3 == null) {
                nVar2.f3727g = nVar2;
                nVar2.f3726f = nVar2;
                cVar.f3701d = nVar2;
            } else {
                nVar3.f3727g.c(nVar2);
            }
            j2 -= (long) (nVar2.f3723c - nVar2.f3722b);
            nVar = nVar.f3726f;
            j = 0;
        }
        return this;
    }

    @Override // f.d
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public c l() {
        return this;
    }

    public byte M(long j) {
        s.b(this.s, j, 1L);
        n nVar = this.f3701d;
        while (true) {
            int i2 = nVar.f3723c;
            int i3 = nVar.f3722b;
            long j2 = i2 - i3;
            if (j < j2) {
                return nVar.f3721a[i3 + ((int) j)];
            }
            j -= j2;
            nVar = nVar.f3726f;
        }
    }

    public long N(byte b2, long j, long j2) {
        n nVar;
        long j3 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.s), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j4 = this.s;
        long j5 = j2 > j4 ? j4 : j2;
        if (j == j5 || (nVar = this.f3701d) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                nVar = nVar.f3727g;
                j4 -= (long) (nVar.f3723c - nVar.f3722b);
            }
        } else {
            while (true) {
                long j6 = ((long) (nVar.f3723c - nVar.f3722b)) + j3;
                if (j6 >= j) {
                    break;
                }
                nVar = nVar.f3726f;
                j3 = j6;
            }
            j4 = j3;
        }
        long j7 = j;
        while (j4 < j5) {
            byte[] bArr = nVar.f3721a;
            int iMin = (int) Math.min(nVar.f3723c, (((long) nVar.f3722b) + j5) - j4);
            for (int i2 = (int) ((((long) nVar.f3722b) + j7) - j4); i2 < iMin; i2++) {
                if (bArr[i2] == b2) {
                    return ((long) (i2 - nVar.f3722b)) + j4;
                }
            }
            j4 += (long) (nVar.f3723c - nVar.f3722b);
            nVar = nVar.f3726f;
            j7 = j4;
        }
        return -1L;
    }

    public OutputStream O() {
        return new a();
    }

    public boolean P(long j, ByteString byteString, int i2, int i3) {
        if (j < 0 || i2 < 0 || i3 < 0 || this.s - j < i3 || byteString.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (M(((long) i4) + j) != byteString.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    public byte[] Q() {
        try {
            return C(this.s);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public ByteString R() {
        return new ByteString(Q());
    }

    public String S(long j, Charset charset) throws EOFException {
        s.b(this.s, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        n nVar = this.f3701d;
        if (((long) nVar.f3722b) + j > nVar.f3723c) {
            return new String(C(j), charset);
        }
        String str = new String(nVar.f3721a, nVar.f3722b, (int) j, charset);
        int i2 = (int) (((long) nVar.f3722b) + j);
        nVar.f3722b = i2;
        this.s -= j;
        if (i2 == nVar.f3723c) {
            this.f3701d = nVar.b();
            o.a(nVar);
        }
        return str;
    }

    public String T(Charset charset) {
        try {
            return S(this.s, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public String U(long j) throws EOFException {
        return S(j, s.f3734a);
    }

    public int V() throws EOFException {
        int i2;
        int i3;
        int i4;
        if (this.s == 0) {
            throw new EOFException();
        }
        byte bM = M(0L);
        if ((bM & 128) == 0) {
            i2 = bM & 127;
            i4 = 0;
            i3 = 1;
        } else if ((bM & 224) == 192) {
            i2 = bM & 31;
            i3 = 2;
            i4 = 128;
        } else if ((bM & 240) == 224) {
            i2 = bM & 15;
            i3 = 3;
            i4 = 2048;
        } else {
            if ((bM & 248) != 240) {
                skip(1L);
                return 65533;
            }
            i2 = bM & 7;
            i3 = 4;
            i4 = 65536;
        }
        long j = i3;
        if (this.s < j) {
            throw new EOFException("size < " + i3 + ": " + this.s + " (to read code point prefixed 0x" + Integer.toHexString(bM) + ")");
        }
        for (int i5 = 1; i5 < i3; i5++) {
            long j2 = i5;
            byte bM2 = M(j2);
            if ((bM2 & 192) != 128) {
                skip(j2);
                return 65533;
            }
            i2 = (i2 << 6) | (bM2 & 63);
        }
        skip(j);
        if (i2 > 1114111) {
            return 65533;
        }
        if ((i2 < 55296 || i2 > 57343) && i2 >= i4) {
            return i2;
        }
        return 65533;
    }

    public String W(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (M(j2) == 13) {
                String strU = U(j2);
                skip(2L);
                return strU;
            }
        }
        String strU2 = U(j);
        skip(1L);
        return strU2;
    }

    public String X(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jN = N((byte) 10, 0L, j2);
        if (jN != -1) {
            return W(jN);
        }
        if (j2 < Y() && M(j2 - 1) == 13 && M(j2) == 10) {
            return W(j2);
        }
        c cVar = new c();
        K(cVar, 0L, Math.min(32L, Y()));
        throw new EOFException("\\n not found: limit=" + Math.min(Y(), j) + " content=" + cVar.R().hex() + (char) 8230);
    }

    public long Y() {
        return this.s;
    }

    public ByteString Z() {
        long j = this.s;
        if (j <= 2147483647L) {
            return a0((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.s);
    }

    @Override // f.e, f.d
    public c a() {
        return this;
    }

    public ByteString a0(int i2) {
        return i2 == 0 ? ByteString.EMPTY : new SegmentedByteString(this, i2);
    }

    @Override // f.e
    public boolean b(long j) {
        return this.s >= j;
    }

    public n b0(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        n nVar = this.f3701d;
        if (nVar != null) {
            n nVar2 = nVar.f3727g;
            return (nVar2.f3723c + i2 > 8192 || !nVar2.f3725e) ? nVar2.c(o.b()) : nVar2;
        }
        n nVarB = o.b();
        this.f3701d = nVarB;
        nVarB.f3727g = nVarB;
        nVarB.f3726f = nVarB;
        return nVarB;
    }

    @Override // f.d
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public c i(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // f.d
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public c f(byte[] bArr) {
        if (bArr != null) {
            return g(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // f.q
    public r e() {
        return r.f3730a;
    }

    @Override // f.d
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
    public c g(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i3;
        s.b(bArr.length, i2, j);
        int i4 = i3 + i2;
        while (i2 < i4) {
            n nVarB0 = b0(1);
            int iMin = Math.min(i4 - i2, 8192 - nVarB0.f3723c);
            System.arraycopy(bArr, i2, nVarB0.f3721a, nVarB0.f3723c, iMin);
            i2 += iMin;
            nVarB0.f3723c += iMin;
        }
        this.s += j;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        long j = this.s;
        if (j != cVar.s) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        n nVar = this.f3701d;
        n nVar2 = cVar.f3701d;
        int i2 = nVar.f3722b;
        int i3 = nVar2.f3722b;
        while (j2 < this.s) {
            long jMin = Math.min(nVar.f3723c - i2, nVar2.f3723c - i3);
            int i4 = 0;
            while (i4 < jMin) {
                int i5 = i2 + 1;
                int i6 = i3 + 1;
                if (nVar.f3721a[i2] != nVar2.f3721a[i3]) {
                    return false;
                }
                i4++;
                i2 = i5;
                i3 = i6;
            }
            if (i2 == nVar.f3723c) {
                nVar = nVar.f3726f;
                i2 = nVar.f3722b;
            }
            if (i3 == nVar2.f3723c) {
                nVar2 = nVar2.f3726f;
                i3 = nVar2.f3722b;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // f.d
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public c A(int i2) {
        n nVarB0 = b0(1);
        byte[] bArr = nVarB0.f3721a;
        int i3 = nVarB0.f3723c;
        nVarB0.f3723c = i3 + 1;
        bArr[i3] = (byte) i2;
        this.s++;
        return this;
    }

    @Override // f.d, f.p, java.io.Flushable
    public void flush() {
    }

    @Override // f.d
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public c y(long j) {
        if (j == 0) {
            return A(48);
        }
        boolean z = false;
        int i2 = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return w("-9223372036854775808");
            }
            z = true;
        }
        if (j >= 100000000) {
            i2 = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i2 = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i2 = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        n nVarB0 = b0(i2);
        byte[] bArr = nVarB0.f3721a;
        int i3 = nVarB0.f3723c + i2;
        while (j != 0) {
            i3--;
            bArr[i3] = f3700c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        nVarB0.f3723c += i2;
        this.s += (long) i2;
        return this;
    }

    @Override // f.p
    public void h(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        s.b(cVar.s, 0L, j);
        while (j > 0) {
            n nVar = cVar.f3701d;
            if (j < nVar.f3723c - nVar.f3722b) {
                n nVar2 = this.f3701d;
                n nVar3 = nVar2 != null ? nVar2.f3727g : null;
                if (nVar3 != null && nVar3.f3725e) {
                    if ((((long) nVar3.f3723c) + j) - ((long) (nVar3.f3724d ? 0 : nVar3.f3722b)) <= 8192) {
                        nVar.e(nVar3, (int) j);
                        cVar.s -= j;
                        this.s += j;
                        return;
                    }
                }
                cVar.f3701d = nVar.d((int) j);
            }
            n nVar4 = cVar.f3701d;
            long j2 = nVar4.f3723c - nVar4.f3722b;
            cVar.f3701d = nVar4.b();
            n nVar5 = this.f3701d;
            if (nVar5 == null) {
                this.f3701d = nVar4;
                nVar4.f3727g = nVar4;
                nVar4.f3726f = nVar4;
            } else {
                nVar5.f3727g.c(nVar4).a();
            }
            cVar.s -= j2;
            this.s += j2;
            j -= j2;
        }
    }

    @Override // f.d
    /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] */
    public c m(long j) {
        if (j == 0) {
            return A(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        n nVarB0 = b0(iNumberOfTrailingZeros);
        byte[] bArr = nVarB0.f3721a;
        int i2 = nVarB0.f3723c;
        for (int i3 = (i2 + iNumberOfTrailingZeros) - 1; i3 >= i2; i3--) {
            bArr[i3] = f3700c[(int) (15 & j)];
            j >>>= 4;
        }
        nVarB0.f3723c += iNumberOfTrailingZeros;
        this.s += (long) iNumberOfTrailingZeros;
        return this;
    }

    public int hashCode() {
        n nVar = this.f3701d;
        if (nVar == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = nVar.f3723c;
            for (int i4 = nVar.f3722b; i4 < i3; i4++) {
                i2 = (i2 * 31) + nVar.f3721a[i4];
            }
            nVar = nVar.f3726f;
        } while (nVar != this.f3701d);
        return i2;
    }

    @Override // f.d
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public c r(int i2) {
        n nVarB0 = b0(4);
        byte[] bArr = nVarB0.f3721a;
        int i3 = nVarB0.f3723c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        nVarB0.f3723c = i6 + 1;
        this.s += 4;
        return this;
    }

    @Override // f.e
    public short j() {
        return s.d(readShort());
    }

    @Override // f.d
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public c q(int i2) {
        n nVarB0 = b0(2);
        byte[] bArr = nVarB0.f3721a;
        int i3 = nVarB0.f3723c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        nVarB0.f3723c = i4 + 1;
        this.s += 2;
        return this;
    }

    @Override // f.d
    public long k(q qVar) throws IOException {
        if (qVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jN = qVar.n(this, 8192L);
            if (jN == -1) {
                return j;
            }
            j += jN;
        }
    }

    @Override // f.d
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public c w(String str) {
        return l0(str, 0, str.length());
    }

    public c l0(String str, int i2, int i3) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
        }
        if (i3 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
        }
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128) {
                n nVarB0 = b0(1);
                byte[] bArr = nVarB0.f3721a;
                int i4 = nVarB0.f3723c - i2;
                int iMin = Math.min(i3, 8192 - i4);
                int i5 = i2 + 1;
                bArr[i2 + i4] = (byte) cCharAt;
                while (i5 < iMin) {
                    char cCharAt2 = str.charAt(i5);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i5 + i4] = (byte) cCharAt2;
                    i5++;
                }
                int i6 = nVarB0.f3723c;
                int i7 = (i4 + i5) - i6;
                nVarB0.f3723c = i6 + i7;
                this.s += (long) i7;
                i2 = i5;
            } else {
                if (cCharAt < 2048) {
                    A((cCharAt >> 6) | 192);
                    A((cCharAt & '?') | 128);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    A((cCharAt >> '\f') | 224);
                    A(((cCharAt >> 6) & 63) | 128);
                    A((cCharAt & '?') | 128);
                } else {
                    int i8 = i2 + 1;
                    char cCharAt3 = i8 < i3 ? str.charAt(i8) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        A(63);
                        i2 = i8;
                    } else {
                        int i9 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        A((i9 >> 18) | 240);
                        A(((i9 >> 12) & 63) | 128);
                        A(((i9 >> 6) & 63) | 128);
                        A((i9 & 63) | 128);
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
        return this;
    }

    public c m0(int i2) {
        if (i2 < 128) {
            A(i2);
        } else if (i2 < 2048) {
            A((i2 >> 6) | 192);
            A((i2 & 63) | 128);
        } else if (i2 < 65536) {
            if (i2 < 55296 || i2 > 57343) {
                A((i2 >> 12) | 224);
                A(((i2 >> 6) & 63) | 128);
                A((i2 & 63) | 128);
            } else {
                A(63);
            }
        } else {
            if (i2 > 1114111) {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
            }
            A((i2 >> 18) | 240);
            A(((i2 >> 12) & 63) | 128);
            A(((i2 >> 6) & 63) | 128);
            A((i2 & 63) | 128);
        }
        return this;
    }

    @Override // f.q
    public long n(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.s;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        cVar.h(this, j);
        return j;
    }

    @Override // f.e
    public ByteString o(long j) throws EOFException {
        return new ByteString(C(j));
    }

    @Override // f.e
    public long p(p pVar) throws IOException {
        long j = this.s;
        if (j > 0) {
            pVar.h(this, j);
        }
        return j;
    }

    public int read(byte[] bArr, int i2, int i3) {
        s.b(bArr.length, i2, i3);
        n nVar = this.f3701d;
        if (nVar == null) {
            return -1;
        }
        int iMin = Math.min(i3, nVar.f3723c - nVar.f3722b);
        System.arraycopy(nVar.f3721a, nVar.f3722b, bArr, i2, iMin);
        int i4 = nVar.f3722b + iMin;
        nVar.f3722b = i4;
        this.s -= (long) iMin;
        if (i4 == nVar.f3723c) {
            this.f3701d = nVar.b();
            o.a(nVar);
        }
        return iMin;
    }

    @Override // f.e
    public byte readByte() {
        long j = this.s;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        n nVar = this.f3701d;
        int i2 = nVar.f3722b;
        int i3 = nVar.f3723c;
        int i4 = i2 + 1;
        byte b2 = nVar.f3721a[i2];
        this.s = j - 1;
        if (i4 == i3) {
            this.f3701d = nVar.b();
            o.a(nVar);
        } else {
            nVar.f3722b = i4;
        }
        return b2;
    }

    @Override // f.e
    public void readFully(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = read(bArr, i2, bArr.length - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
    }

    @Override // f.e
    public int readInt() {
        long j = this.s;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.s);
        }
        n nVar = this.f3701d;
        int i2 = nVar.f3722b;
        int i3 = nVar.f3723c;
        if (i3 - i2 < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = nVar.f3721a;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
        int i7 = i5 + 1;
        int i8 = i6 | ((bArr[i5] & 255) << 8);
        int i9 = i7 + 1;
        int i10 = i8 | (bArr[i7] & 255);
        this.s = j - 4;
        if (i9 == i3) {
            this.f3701d = nVar.b();
            o.a(nVar);
        } else {
            nVar.f3722b = i9;
        }
        return i10;
    }

    @Override // f.e
    public short readShort() {
        long j = this.s;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.s);
        }
        n nVar = this.f3701d;
        int i2 = nVar.f3722b;
        int i3 = nVar.f3723c;
        if (i3 - i2 < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = nVar.f3721a;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
        this.s = j - 2;
        if (i5 == i3) {
            this.f3701d = nVar.b();
            o.a(nVar);
        } else {
            nVar.f3722b = i5;
        }
        return (short) i6;
    }

    @Override // f.e
    public void skip(long j) throws EOFException {
        while (j > 0) {
            if (this.f3701d == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, r0.f3723c - r0.f3722b);
            long j2 = iMin;
            this.s -= j2;
            j -= j2;
            n nVar = this.f3701d;
            int i2 = nVar.f3722b + iMin;
            nVar.f3722b = i2;
            if (i2 == nVar.f3723c) {
                this.f3701d = nVar.b();
                o.a(nVar);
            }
        }
    }

    @Override // f.e
    public String t() throws EOFException {
        return X(Long.MAX_VALUE);
    }

    public String toString() {
        return Z().toString();
    }

    @Override // f.e
    public void u(long j) throws EOFException {
        if (this.s < j) {
            throw new EOFException();
        }
    }

    @Override // f.e
    public int x() {
        return s.c(readInt());
    }

    @Override // f.e
    public boolean z() {
        return this.s == 0;
    }
}

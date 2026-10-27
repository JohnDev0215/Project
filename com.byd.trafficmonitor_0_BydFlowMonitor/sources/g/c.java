package g;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okio.ByteString;
import okio.SegmentedByteString;

/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements e, d, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f5469b = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public m f5470c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5471d;

    /* JADX INFO: compiled from: Buffer.java */
    public class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(c.this.f5471d, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            c cVar = c.this;
            if (cVar.f5471d > 0) {
                return cVar.N() & 255;
            }
            return -1;
        }

        public String toString() {
            return c.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            return c.this.W(bArr, i2, i3);
        }
    }

    @Override // g.e
    public int B() {
        return r.c(t());
    }

    @Override // g.e
    public boolean D() {
        return this.f5471d == 0;
    }

    @Override // g.e
    public long F(byte b2) {
        return U(b2, 0L, Long.MAX_VALUE);
    }

    @Override // g.e
    public byte[] G(long j) throws EOFException {
        r.b(this.f5471d, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            Z(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    @Override // g.e
    public boolean H(long j, ByteString byteString) {
        return V(j, byteString, 0, byteString.size());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5 A[EDGE_INSN: B:44:0x00a5->B:38:0x00a5 BREAK  A[LOOP:0: B:5:0x000b->B:46:?], SYNTHETIC] */
    @Override // g.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long J() {
        /*
            r14 = this;
            long r0 = r14.f5471d
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lac
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            g.m r6 = r14.f5470c
            byte[] r7 = r6.f5496a
            int r8 = r6.f5497b
            int r9 = r6.f5498c
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
            g.c r0 = new g.c
            r0.<init>()
            g.c r0 = r0.l(r4)
            g.c r0 = r0.E(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.b0()
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
            g.m r7 = r6.b()
            r14.f5470c = r7
            g.n.a(r6)
            goto L9f
        L9d:
            r6.f5497b = r8
        L9f:
            if (r1 != 0) goto La5
            g.m r6 = r14.f5470c
            if (r6 != 0) goto Lb
        La5:
            long r1 = r14.f5471d
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.f5471d = r1
            return r4
        Lac:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: g.c.J():long");
    }

    @Override // g.e
    public String L(Charset charset) {
        try {
            return a0(this.f5471d, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // g.e
    public InputStream M() {
        return new a();
    }

    @Override // g.e
    public byte N() {
        long j = this.f5471d;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        m mVar = this.f5470c;
        int i2 = mVar.f5497b;
        int i3 = mVar.f5498c;
        int i4 = i2 + 1;
        byte b2 = mVar.f5496a[i2];
        this.f5471d = j - 1;
        if (i4 == i3) {
            this.f5470c = mVar.b();
            n.a(mVar);
        } else {
            mVar.f5497b = i4;
        }
        return b2;
    }

    public final void O() {
        try {
            o(this.f5471d);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public c clone() {
        c cVar = new c();
        if (this.f5471d == 0) {
            return cVar;
        }
        m mVarD = this.f5470c.d();
        cVar.f5470c = mVarD;
        mVarD.f5502g = mVarD;
        mVarD.f5501f = mVarD;
        m mVar = this.f5470c;
        while (true) {
            mVar = mVar.f5501f;
            if (mVar == this.f5470c) {
                cVar.f5471d = this.f5471d;
                return cVar;
            }
            cVar.f5470c.f5502g.c(mVar.d());
        }
    }

    public final long Q() {
        long j = this.f5471d;
        if (j == 0) {
            return 0L;
        }
        m mVar = this.f5470c.f5502g;
        int i2 = mVar.f5498c;
        return (i2 >= 8192 || !mVar.f5500e) ? j : j - ((long) (i2 - mVar.f5497b));
    }

    public final c R(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        r.b(this.f5471d, j, j2);
        if (j2 == 0) {
            return this;
        }
        cVar.f5471d += j2;
        m mVar = this.f5470c;
        while (true) {
            int i2 = mVar.f5498c;
            int i3 = mVar.f5497b;
            if (j < i2 - i3) {
                break;
            }
            j -= (long) (i2 - i3);
            mVar = mVar.f5501f;
        }
        while (j2 > 0) {
            m mVarD = mVar.d();
            int i4 = (int) (((long) mVarD.f5497b) + j);
            mVarD.f5497b = i4;
            mVarD.f5498c = Math.min(i4 + ((int) j2), mVarD.f5498c);
            m mVar2 = cVar.f5470c;
            if (mVar2 == null) {
                mVarD.f5502g = mVarD;
                mVarD.f5501f = mVarD;
                cVar.f5470c = mVarD;
            } else {
                mVar2.f5502g.c(mVarD);
            }
            j2 -= (long) (mVarD.f5498c - mVarD.f5497b);
            mVar = mVar.f5501f;
            j = 0;
        }
        return this;
    }

    @Override // g.d
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public c k() {
        return this;
    }

    public final byte T(long j) {
        int i2;
        r.b(this.f5471d, j, 1L);
        long j2 = this.f5471d;
        if (j2 - j <= j) {
            long j3 = j - j2;
            m mVar = this.f5470c;
            do {
                mVar = mVar.f5502g;
                int i3 = mVar.f5498c;
                i2 = mVar.f5497b;
                j3 += (long) (i3 - i2);
            } while (j3 < 0);
            return mVar.f5496a[i2 + ((int) j3)];
        }
        m mVar2 = this.f5470c;
        while (true) {
            int i4 = mVar2.f5498c;
            int i5 = mVar2.f5497b;
            long j4 = i4 - i5;
            if (j < j4) {
                return mVar2.f5496a[i5 + ((int) j)];
            }
            j -= j4;
            mVar2 = mVar2.f5501f;
        }
    }

    public long U(byte b2, long j, long j2) {
        m mVar;
        long j3 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f5471d), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j4 = this.f5471d;
        long j5 = j2 > j4 ? j4 : j2;
        if (j == j5 || (mVar = this.f5470c) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                mVar = mVar.f5502g;
                j4 -= (long) (mVar.f5498c - mVar.f5497b);
            }
        } else {
            while (true) {
                long j6 = ((long) (mVar.f5498c - mVar.f5497b)) + j3;
                if (j6 >= j) {
                    break;
                }
                mVar = mVar.f5501f;
                j3 = j6;
            }
            j4 = j3;
        }
        long j7 = j;
        while (j4 < j5) {
            byte[] bArr = mVar.f5496a;
            int iMin = (int) Math.min(mVar.f5498c, (((long) mVar.f5497b) + j5) - j4);
            for (int i2 = (int) ((((long) mVar.f5497b) + j7) - j4); i2 < iMin; i2++) {
                if (bArr[i2] == b2) {
                    return ((long) (i2 - mVar.f5497b)) + j4;
                }
            }
            j4 += (long) (mVar.f5498c - mVar.f5497b);
            mVar = mVar.f5501f;
            j7 = j4;
        }
        return -1L;
    }

    public boolean V(long j, ByteString byteString, int i2, int i3) {
        if (j < 0 || i2 < 0 || i3 < 0 || this.f5471d - j < i3 || byteString.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (T(((long) i4) + j) != byteString.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    public int W(byte[] bArr, int i2, int i3) {
        r.b(bArr.length, i2, i3);
        m mVar = this.f5470c;
        if (mVar == null) {
            return -1;
        }
        int iMin = Math.min(i3, mVar.f5498c - mVar.f5497b);
        System.arraycopy(mVar.f5496a, mVar.f5497b, bArr, i2, iMin);
        int i4 = mVar.f5497b + iMin;
        mVar.f5497b = i4;
        this.f5471d -= (long) iMin;
        if (i4 == mVar.f5498c) {
            this.f5470c = mVar.b();
            n.a(mVar);
        }
        return iMin;
    }

    public byte[] X() {
        try {
            return G(this.f5471d);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public ByteString Y() {
        return new ByteString(X());
    }

    public void Z(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int iW = W(bArr, i2, bArr.length - i2);
            if (iW == -1) {
                throw new EOFException();
            }
            i2 += iW;
        }
    }

    @Override // g.e, g.d
    public c a() {
        return this;
    }

    public String a0(long j, Charset charset) throws EOFException {
        r.b(this.f5471d, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        m mVar = this.f5470c;
        if (((long) mVar.f5497b) + j > mVar.f5498c) {
            return new String(G(j), charset);
        }
        String str = new String(mVar.f5496a, mVar.f5497b, (int) j, charset);
        int i2 = (int) (((long) mVar.f5497b) + j);
        mVar.f5497b = i2;
        this.f5471d -= j;
        if (i2 == mVar.f5498c) {
            this.f5470c = mVar.b();
            n.a(mVar);
        }
        return str;
    }

    @Override // g.p
    public q b() {
        return q.f5505a;
    }

    public String b0() {
        try {
            return a0(this.f5471d, r.f5509a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public String c0(long j) throws EOFException {
        return a0(j, r.f5509a);
    }

    @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public String d0(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (T(j2) == 13) {
                String strC0 = c0(j2);
                o(2L);
                return strC0;
            }
        }
        String strC02 = c0(j);
        o(1L);
        return strC02;
    }

    public String e0(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jU = U((byte) 10, 0L, j2);
        if (jU != -1) {
            return d0(jU);
        }
        if (j2 < f0() && T(j2 - 1) == 13 && T(j2) == 10) {
            return d0(j2);
        }
        c cVar = new c();
        R(cVar, 0L, Math.min(32L, f0()));
        throw new EOFException("\\n not found: limit=" + Math.min(f0(), j) + " content=" + cVar.Y().hex() + (char) 8230);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        long j = this.f5471d;
        if (j != cVar.f5471d) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        m mVar = this.f5470c;
        m mVar2 = cVar.f5470c;
        int i2 = mVar.f5497b;
        int i3 = mVar2.f5497b;
        while (j2 < this.f5471d) {
            long jMin = Math.min(mVar.f5498c - i2, mVar2.f5498c - i3);
            int i4 = 0;
            while (i4 < jMin) {
                int i5 = i2 + 1;
                int i6 = i3 + 1;
                if (mVar.f5496a[i2] != mVar2.f5496a[i3]) {
                    return false;
                }
                i4++;
                i2 = i5;
                i3 = i6;
            }
            if (i2 == mVar.f5498c) {
                mVar = mVar.f5501f;
                i2 = mVar.f5497b;
            }
            if (i3 == mVar2.f5498c) {
                mVar2 = mVar2.f5501f;
                i3 = mVar2.f5497b;
            }
            j2 += jMin;
        }
        return true;
    }

    public final long f0() {
        return this.f5471d;
    }

    @Override // g.d, g.o, java.io.Flushable
    public void flush() {
    }

    @Override // g.o
    public void g(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        r.b(cVar.f5471d, 0L, j);
        while (j > 0) {
            m mVar = cVar.f5470c;
            if (j < mVar.f5498c - mVar.f5497b) {
                m mVar2 = this.f5470c;
                m mVar3 = mVar2 != null ? mVar2.f5502g : null;
                if (mVar3 != null && mVar3.f5500e) {
                    if ((((long) mVar3.f5498c) + j) - ((long) (mVar3.f5499d ? 0 : mVar3.f5497b)) <= 8192) {
                        mVar.f(mVar3, (int) j);
                        cVar.f5471d -= j;
                        this.f5471d += j;
                        return;
                    }
                }
                cVar.f5470c = mVar.e((int) j);
            }
            m mVar4 = cVar.f5470c;
            long j2 = mVar4.f5498c - mVar4.f5497b;
            cVar.f5470c = mVar4.b();
            m mVar5 = this.f5470c;
            if (mVar5 == null) {
                this.f5470c = mVar4;
                mVar4.f5502g = mVar4;
                mVar4.f5501f = mVar4;
            } else {
                mVar5.f5502g.c(mVar4).a();
            }
            cVar.f5471d -= j2;
            this.f5471d += j2;
            j -= j2;
        }
    }

    public final ByteString g0() {
        long j = this.f5471d;
        if (j <= 2147483647L) {
            return h0((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f5471d);
    }

    public final ByteString h0(int i2) {
        return i2 == 0 ? ByteString.EMPTY : new SegmentedByteString(this, i2);
    }

    public int hashCode() {
        m mVar = this.f5470c;
        if (mVar == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = mVar.f5498c;
            for (int i4 = mVar.f5497b; i4 < i3; i4++) {
                i2 = (i2 * 31) + mVar.f5496a[i4];
            }
            mVar = mVar.f5501f;
        } while (mVar != this.f5470c);
        return i2;
    }

    @Override // g.e
    public short i() {
        return r.d(p());
    }

    public m i0(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        m mVar = this.f5470c;
        if (mVar != null) {
            m mVar2 = mVar.f5502g;
            return (mVar2.f5498c + i2 > 8192 || !mVar2.f5500e) ? mVar2.c(n.b()) : mVar2;
        }
        m mVarB = n.b();
        this.f5470c = mVarB;
        mVarB.f5502g = mVarB;
        mVarB.f5501f = mVarB;
        return mVarB;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // g.d
    public long j(p pVar) throws IOException {
        if (pVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jM = pVar.m(this, 8192L);
            if (jM == -1) {
                return j;
            }
            j += jM;
        }
    }

    @Override // g.d
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public c h(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    @Override // g.d
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public c c(byte[] bArr) {
        if (bArr != null) {
            return d(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // g.d
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public c d(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i3;
        r.b(bArr.length, i2, j);
        int i4 = i3 + i2;
        while (i2 < i4) {
            m mVarI0 = i0(1);
            int iMin = Math.min(i4 - i2, 8192 - mVarI0.f5498c);
            System.arraycopy(bArr, i2, mVarI0.f5496a, mVarI0.f5498c, iMin);
            i2 += iMin;
            mVarI0.f5498c += iMin;
        }
        this.f5471d += j;
        return this;
    }

    @Override // g.p
    public long m(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.f5471d;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        cVar.g(this, j);
        return j;
    }

    @Override // g.d
    /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
    public c E(int i2) {
        m mVarI0 = i0(1);
        byte[] bArr = mVarI0.f5496a;
        int i3 = mVarI0.f5498c;
        mVarI0.f5498c = i3 + 1;
        bArr[i3] = (byte) i2;
        this.f5471d++;
        return this;
    }

    @Override // g.e
    public ByteString n(long j) throws EOFException {
        return new ByteString(G(j));
    }

    @Override // g.d
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public c C(long j) {
        if (j == 0) {
            return E(48);
        }
        boolean z = false;
        int i2 = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return A("-9223372036854775808");
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
        m mVarI0 = i0(i2);
        byte[] bArr = mVarI0.f5496a;
        int i3 = mVarI0.f5498c + i2;
        while (j != 0) {
            i3--;
            bArr[i3] = f5469b[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        mVarI0.f5498c += i2;
        this.f5471d += (long) i2;
        return this;
    }

    @Override // g.e
    public void o(long j) throws EOFException {
        while (j > 0) {
            if (this.f5470c == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, r0.f5498c - r0.f5497b);
            long j2 = iMin;
            this.f5471d -= j2;
            j -= j2;
            m mVar = this.f5470c;
            int i2 = mVar.f5497b + iMin;
            mVar.f5497b = i2;
            if (i2 == mVar.f5498c) {
                this.f5470c = mVar.b();
                n.a(mVar);
            }
        }
    }

    @Override // g.d
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public c l(long j) {
        if (j == 0) {
            return E(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        m mVarI0 = i0(iNumberOfTrailingZeros);
        byte[] bArr = mVarI0.f5496a;
        int i2 = mVarI0.f5498c;
        for (int i3 = (i2 + iNumberOfTrailingZeros) - 1; i3 >= i2; i3--) {
            bArr[i3] = f5469b[(int) (15 & j)];
            j >>>= 4;
        }
        mVarI0.f5498c += iNumberOfTrailingZeros;
        this.f5471d += (long) iNumberOfTrailingZeros;
        return this;
    }

    @Override // g.e
    public short p() {
        long j = this.f5471d;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.f5471d);
        }
        m mVar = this.f5470c;
        int i2 = mVar.f5497b;
        int i3 = mVar.f5498c;
        if (i3 - i2 < 2) {
            return (short) (((N() & 255) << 8) | (N() & 255));
        }
        byte[] bArr = mVar.f5496a;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
        this.f5471d = j - 2;
        if (i5 == i3) {
            this.f5470c = mVar.b();
            n.a(mVar);
        } else {
            mVar.f5497b = i5;
        }
        return (short) i6;
    }

    @Override // g.d
    /* JADX INFO: renamed from: p0, reason: merged with bridge method [inline-methods] */
    public c u(int i2) {
        m mVarI0 = i0(4);
        byte[] bArr = mVarI0.f5496a;
        int i3 = mVarI0.f5498c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        mVarI0.f5498c = i6 + 1;
        this.f5471d += 4;
        return this;
    }

    @Override // g.d
    /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
    public c q(int i2) {
        m mVarI0 = i0(2);
        byte[] bArr = mVarI0.f5496a;
        int i3 = mVarI0.f5498c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        mVarI0.f5498c = i4 + 1;
        this.f5471d += 2;
        return this;
    }

    @Override // g.d
    /* JADX INFO: renamed from: r0, reason: merged with bridge method [inline-methods] */
    public c A(String str) {
        return s0(str, 0, str.length());
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        m mVar = this.f5470c;
        if (mVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), mVar.f5498c - mVar.f5497b);
        byteBuffer.put(mVar.f5496a, mVar.f5497b, iMin);
        int i2 = mVar.f5497b + iMin;
        mVar.f5497b = i2;
        this.f5471d -= (long) iMin;
        if (i2 == mVar.f5498c) {
            this.f5470c = mVar.b();
            n.a(mVar);
        }
        return iMin;
    }

    public c s0(String str, int i2, int i3) {
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
                m mVarI0 = i0(1);
                byte[] bArr = mVarI0.f5496a;
                int i4 = mVarI0.f5498c - i2;
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
                int i6 = mVarI0.f5498c;
                int i7 = (i4 + i5) - i6;
                mVarI0.f5498c = i6 + i7;
                this.f5471d += (long) i7;
                i2 = i5;
            } else {
                if (cCharAt < 2048) {
                    E((cCharAt >> 6) | 192);
                    E((cCharAt & '?') | 128);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    E((cCharAt >> '\f') | 224);
                    E(((cCharAt >> 6) & 63) | 128);
                    E((cCharAt & '?') | 128);
                } else {
                    int i8 = i2 + 1;
                    char cCharAt3 = i8 < i3 ? str.charAt(i8) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        E(63);
                        i2 = i8;
                    } else {
                        int i9 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        E((i9 >> 18) | 240);
                        E(((i9 >> 12) & 63) | 128);
                        E(((i9 >> 6) & 63) | 128);
                        E((i9 & 63) | 128);
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
        return this;
    }

    @Override // g.e
    public int t() {
        long j = this.f5471d;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.f5471d);
        }
        m mVar = this.f5470c;
        int i2 = mVar.f5497b;
        int i3 = mVar.f5498c;
        if (i3 - i2 < 4) {
            return ((N() & 255) << 24) | ((N() & 255) << 16) | ((N() & 255) << 8) | (N() & 255);
        }
        byte[] bArr = mVar.f5496a;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
        int i7 = i5 + 1;
        int i8 = i6 | ((bArr[i5] & 255) << 8);
        int i9 = i7 + 1;
        int i10 = i8 | (bArr[i7] & 255);
        this.f5471d = j - 4;
        if (i9 == i3) {
            this.f5470c = mVar.b();
            n.a(mVar);
        } else {
            mVar.f5497b = i9;
        }
        return i10;
    }

    public c t0(int i2) {
        if (i2 < 128) {
            E(i2);
        } else if (i2 < 2048) {
            E((i2 >> 6) | 192);
            E((i2 & 63) | 128);
        } else if (i2 < 65536) {
            if (i2 < 55296 || i2 > 57343) {
                E((i2 >> 12) | 224);
                E(((i2 >> 6) & 63) | 128);
                E((i2 & 63) | 128);
            } else {
                E(63);
            }
        } else {
            if (i2 > 1114111) {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
            }
            E((i2 >> 18) | 240);
            E(((i2 >> 12) & 63) | 128);
            E(((i2 >> 6) & 63) | 128);
            E((i2 & 63) | 128);
        }
        return this;
    }

    public String toString() {
        return g0().toString();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i2 = iRemaining;
        while (i2 > 0) {
            m mVarI0 = i0(1);
            int iMin = Math.min(i2, 8192 - mVarI0.f5498c);
            byteBuffer.get(mVarI0.f5496a, mVarI0.f5498c, iMin);
            i2 -= iMin;
            mVarI0.f5498c += iMin;
        }
        this.f5471d += (long) iRemaining;
        return iRemaining;
    }

    @Override // g.e
    public String x() throws EOFException {
        return e0(Long.MAX_VALUE);
    }

    @Override // g.e
    public void y(long j) throws EOFException {
        if (this.f5471d < j) {
            throw new EOFException();
        }
    }
}

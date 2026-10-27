package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f5769b = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    Segment f5770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long f5771d;

    /* JADX INFO: renamed from: okio.Buffer$2, reason: invalid class name */
    class AnonymousClass2 extends InputStream {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Buffer f5773b;

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(this.f5773b.f5771d, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            Buffer buffer = this.f5773b;
            if (buffer.f5771d > 0) {
                return buffer.P() & 255;
            }
            return -1;
        }

        public String toString() {
            return this.f5773b + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            return this.f5773b.Z(bArr, i2, i3);
        }
    }

    public static final class UnsafeCursor implements Closeable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Buffer f5774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Segment f5775c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public byte[] f5777e;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f5776d = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5778f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5779g = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f5774b == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.f5774b = null;
            this.f5775c = null;
            this.f5776d = -1L;
            this.f5777e = null;
            this.f5778f = -1;
            this.f5779g = -1;
        }
    }

    @Override // okio.BufferedSource
    public byte[] A() {
        try {
            return L(this.f5771d);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public void B(long j) throws EOFException {
        if (this.f5771d < j) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public int E() {
        return Util.c(w());
    }

    @Override // okio.BufferedSource
    public Buffer G() {
        return this;
    }

    @Override // okio.BufferedSource
    public boolean I() {
        return this.f5771d == 0;
    }

    @Override // okio.BufferedSource
    public long K(byte b2) {
        return X(b2, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public byte[] L(long j) throws EOFException {
        Util.b(this.f5771d, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            k(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5 A[EDGE_INSN: B:44:0x00a5->B:38:0x00a5 BREAK  A[LOOP:0: B:5:0x000b->B:46:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long M() {
        /*
            r14 = this;
            long r0 = r14.f5771d
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lac
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            okio.Segment r6 = r14.f5770c
            byte[] r7 = r6.f5842a
            int r8 = r6.f5843b
            int r9 = r6.f5844c
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
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.o(r4)
            okio.Buffer r0 = r0.J(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.O()
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
            okio.Segment r7 = r6.b()
            r14.f5770c = r7
            okio.SegmentPool.a(r6)
            goto L9f
        L9d:
            r6.f5843b = r8
        L9f:
            if (r1 != 0) goto La5
            okio.Segment r6 = r14.f5770c
            if (r6 != 0) goto Lb
        La5:
            long r1 = r14.f5771d
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.f5771d = r1
            return r4
        Lac:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.M():long");
    }

    @Override // okio.BufferedSource
    public String N(Charset charset) {
        try {
            return b0(this.f5771d, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public String O() {
        try {
            return b0(this.f5771d, Util.f5857a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public byte P() {
        long j = this.f5771d;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.f5770c;
        int i2 = segment.f5843b;
        int i3 = segment.f5844c;
        int i4 = i2 + 1;
        byte b2 = segment.f5842a[i2];
        this.f5771d = j - 1;
        if (i4 == i3) {
            this.f5770c = segment.b();
            SegmentPool.a(segment);
        } else {
            segment.f5843b = i4;
        }
        return b2;
    }

    @Override // okio.BufferedSource
    public int Q(Options options) {
        int iF0 = f0(options, false);
        if (iF0 == -1) {
            return -1;
        }
        try {
            s(options.f5813b[iF0].s());
            return iF0;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public final void R() {
        try {
            s(this.f5771d);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.f5771d == 0) {
            return buffer;
        }
        Segment segmentD = this.f5770c.d();
        buffer.f5770c = segmentD;
        segmentD.f5848g = segmentD;
        segmentD.f5847f = segmentD;
        Segment segment = this.f5770c;
        while (true) {
            segment = segment.f5847f;
            if (segment == this.f5770c) {
                buffer.f5771d = this.f5771d;
                return buffer;
            }
            buffer.f5770c.f5848g.c(segment.d());
        }
    }

    public final long T() {
        long j = this.f5771d;
        if (j == 0) {
            return 0L;
        }
        Segment segment = this.f5770c.f5848g;
        int i2 = segment.f5844c;
        return (i2 >= 8192 || !segment.f5846e) ? j : j - ((long) (i2 - segment.f5843b));
    }

    public final Buffer U(Buffer buffer, long j, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.b(this.f5771d, j, j2);
        if (j2 == 0) {
            return this;
        }
        buffer.f5771d += j2;
        Segment segment = this.f5770c;
        while (true) {
            int i2 = segment.f5844c;
            int i3 = segment.f5843b;
            if (j < i2 - i3) {
                break;
            }
            j -= (long) (i2 - i3);
            segment = segment.f5847f;
        }
        while (j2 > 0) {
            Segment segmentD = segment.d();
            int i4 = (int) (((long) segmentD.f5843b) + j);
            segmentD.f5843b = i4;
            segmentD.f5844c = Math.min(i4 + ((int) j2), segmentD.f5844c);
            Segment segment2 = buffer.f5770c;
            if (segment2 == null) {
                segmentD.f5848g = segmentD;
                segmentD.f5847f = segmentD;
                buffer.f5770c = segmentD;
            } else {
                segment2.f5848g.c(segmentD);
            }
            j2 -= (long) (segmentD.f5844c - segmentD.f5843b);
            segment = segment.f5847f;
            j = 0;
        }
        return this;
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public Buffer n() {
        return this;
    }

    public final byte W(long j) {
        int i2;
        Util.b(this.f5771d, j, 1L);
        long j2 = this.f5771d;
        if (j2 - j <= j) {
            long j3 = j - j2;
            Segment segment = this.f5770c;
            do {
                segment = segment.f5848g;
                int i3 = segment.f5844c;
                i2 = segment.f5843b;
                j3 += (long) (i3 - i2);
            } while (j3 < 0);
            return segment.f5842a[i2 + ((int) j3)];
        }
        Segment segment2 = this.f5770c;
        while (true) {
            int i4 = segment2.f5844c;
            int i5 = segment2.f5843b;
            long j4 = i4 - i5;
            if (j < j4) {
                return segment2.f5842a[i5 + ((int) j)];
            }
            j -= j4;
            segment2 = segment2.f5847f;
        }
    }

    public long X(byte b2, long j, long j2) {
        Segment segment;
        long j3 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f5771d), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j4 = this.f5771d;
        long j5 = j2 > j4 ? j4 : j2;
        if (j == j5 || (segment = this.f5770c) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                segment = segment.f5848g;
                j4 -= (long) (segment.f5844c - segment.f5843b);
            }
        } else {
            while (true) {
                long j6 = ((long) (segment.f5844c - segment.f5843b)) + j3;
                if (j6 >= j) {
                    break;
                }
                segment = segment.f5847f;
                j3 = j6;
            }
            j4 = j3;
        }
        long j7 = j;
        while (j4 < j5) {
            byte[] bArr = segment.f5842a;
            int iMin = (int) Math.min(segment.f5844c, (((long) segment.f5843b) + j5) - j4);
            for (int i2 = (int) ((((long) segment.f5843b) + j7) - j4); i2 < iMin; i2++) {
                if (bArr[i2] == b2) {
                    return ((long) (i2 - segment.f5843b)) + j4;
                }
            }
            j4 += (long) (segment.f5844c - segment.f5843b);
            segment = segment.f5847f;
            j7 = j4;
        }
        return -1L;
    }

    public OutputStream Y() {
        return new OutputStream() { // from class: okio.Buffer.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i2) {
                Buffer.this.J((byte) i2);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) {
                Buffer.this.g(bArr, i2, i3);
            }
        };
    }

    public int Z(byte[] bArr, int i2, int i3) {
        Util.b(bArr.length, i2, i3);
        Segment segment = this.f5770c;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(i3, segment.f5844c - segment.f5843b);
        System.arraycopy(segment.f5842a, segment.f5843b, bArr, i2, iMin);
        int i4 = segment.f5843b + iMin;
        segment.f5843b = i4;
        this.f5771d -= (long) iMin;
        if (i4 == segment.f5844c) {
            this.f5770c = segment.b();
            SegmentPool.a(segment);
        }
        return iMin;
    }

    public ByteString a0() {
        return new ByteString(A());
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer b() {
        return this;
    }

    public String b0(long j, Charset charset) {
        Util.b(this.f5771d, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        Segment segment = this.f5770c;
        if (((long) segment.f5843b) + j > segment.f5844c) {
            return new String(L(j), charset);
        }
        String str = new String(segment.f5842a, segment.f5843b, (int) j, charset);
        int i2 = (int) (((long) segment.f5843b) + j);
        segment.f5843b = i2;
        this.f5771d -= j;
        if (i2 == segment.f5844c) {
            this.f5770c = segment.b();
            SegmentPool.a(segment);
        }
        return str;
    }

    @Override // okio.BufferedSource
    public boolean c(long j) {
        return this.f5771d >= j;
    }

    public String c0(long j) {
        return b0(j, Util.f5857a);
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public int d0() throws EOFException {
        int i2;
        int i3;
        int i4;
        if (this.f5771d == 0) {
            throw new EOFException();
        }
        byte bW = W(0L);
        if ((bW & 128) == 0) {
            i2 = bW & 127;
            i4 = 0;
            i3 = 1;
        } else if ((bW & 224) == 192) {
            i2 = bW & 31;
            i3 = 2;
            i4 = 128;
        } else if ((bW & 240) == 224) {
            i2 = bW & 15;
            i3 = 3;
            i4 = 2048;
        } else {
            if ((bW & 248) != 240) {
                s(1L);
                return 65533;
            }
            i2 = bW & 7;
            i3 = 4;
            i4 = 65536;
        }
        long j = i3;
        if (this.f5771d < j) {
            throw new EOFException("size < " + i3 + ": " + this.f5771d + " (to read code point prefixed 0x" + Integer.toHexString(bW) + ")");
        }
        for (int i5 = 1; i5 < i3; i5++) {
            long j2 = i5;
            byte bW2 = W(j2);
            if ((bW2 & 192) != 128) {
                s(j2);
                return 65533;
            }
            i2 = (i2 << 6) | (bW2 & 63);
        }
        s(j);
        if (i2 > 1114111) {
            return 65533;
        }
        if ((i2 < 55296 || i2 > 57343) && i2 >= i4) {
            return i2;
        }
        return 65533;
    }

    @Override // okio.Source
    public Timeout e() {
        return Timeout.f5853a;
    }

    String e0(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (W(j2) == 13) {
                String strC0 = c0(j2);
                s(2L);
                return strC0;
            }
        }
        String strC02 = c0(j);
        s(1L);
        return strC02;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        long j = this.f5771d;
        if (j != buffer.f5771d) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        Segment segment = this.f5770c;
        Segment segment2 = buffer.f5770c;
        int i2 = segment.f5843b;
        int i3 = segment2.f5843b;
        while (j2 < this.f5771d) {
            long jMin = Math.min(segment.f5844c - i2, segment2.f5844c - i3);
            int i4 = 0;
            while (i4 < jMin) {
                int i5 = i2 + 1;
                int i6 = i3 + 1;
                if (segment.f5842a[i2] != segment2.f5842a[i3]) {
                    return false;
                }
                i4++;
                i2 = i5;
                i3 = i6;
            }
            if (i2 == segment.f5844c) {
                segment = segment.f5847f;
                i2 = segment.f5843b;
            }
            if (i3 == segment2.f5844c) {
                segment2 = segment2.f5847f;
                i3 = segment2.f5843b;
            }
            j2 += jMin;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
    
        if (r19 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0058, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int f0(okio.Options r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            okio.Segment r2 = r1.f5770c
            r3 = -2
            if (r2 != 0) goto L13
            if (r19 == 0) goto Lc
            return r3
        Lc:
            okio.ByteString r2 = okio.ByteString.f5781c
            int r0 = r0.indexOf(r2)
            return r0
        L13:
            byte[] r4 = r2.f5842a
            int r5 = r2.f5843b
            int r6 = r2.f5844c
            int[] r0 = r0.f5814c
            r7 = 0
            r8 = -1
            r10 = r2
            r9 = r7
            r11 = r8
        L20:
            int r12 = r9 + 1
            r9 = r0[r9]
            int r13 = r12 + 1
            r12 = r0[r12]
            if (r12 == r8) goto L2b
            r11 = r12
        L2b:
            if (r10 != 0) goto L2e
            goto L55
        L2e:
            r12 = 0
            if (r9 >= 0) goto L75
            int r9 = r9 * (-1)
            int r14 = r13 + r9
        L35:
            int r9 = r5 + 1
            r5 = r4[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r15 = r13 + 1
            r13 = r0[r13]
            if (r5 == r13) goto L42
            return r11
        L42:
            if (r15 != r14) goto L46
            r5 = 1
            goto L47
        L46:
            r5 = r7
        L47:
            if (r9 != r6) goto L62
            okio.Segment r4 = r10.f5847f
            int r6 = r4.f5843b
            byte[] r9 = r4.f5842a
            int r10 = r4.f5844c
            if (r4 != r2) goto L5c
            if (r5 != 0) goto L59
        L55:
            if (r19 == 0) goto L58
            return r3
        L58:
            return r11
        L59:
            r4 = r9
            r9 = r12
            goto L68
        L5c:
            r16 = r9
            r9 = r4
            r4 = r16
            goto L68
        L62:
            r16 = r10
            r10 = r6
            r6 = r9
            r9 = r16
        L68:
            if (r5 == 0) goto L70
            r5 = r0[r15]
            r3 = r6
            r6 = r10
            r10 = r9
            goto L96
        L70:
            r5 = r6
            r6 = r10
            r13 = r15
            r10 = r9
            goto L35
        L75:
            int r14 = r5 + 1
            r5 = r4[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r15 = r13 + r9
        L7d:
            if (r13 != r15) goto L80
            return r11
        L80:
            r3 = r0[r13]
            if (r5 != r3) goto L9d
            int r13 = r13 + r9
            r5 = r0[r13]
            if (r14 != r6) goto L95
            okio.Segment r10 = r10.f5847f
            int r3 = r10.f5843b
            byte[] r4 = r10.f5842a
            int r6 = r10.f5844c
            if (r10 != r2) goto L96
            r10 = r12
            goto L96
        L95:
            r3 = r14
        L96:
            if (r5 < 0) goto L99
            return r5
        L99:
            int r9 = -r5
            r5 = r3
            r3 = -2
            goto L20
        L9d:
            int r13 = r13 + 1
            r3 = -2
            goto L7d
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.f0(okio.Options, boolean):int");
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    public final long g0() {
        return this.f5771d;
    }

    @Override // okio.Sink
    public void h(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer == this) {
            throw new IllegalArgumentException("source == this");
        }
        Util.b(buffer.f5771d, 0L, j);
        while (j > 0) {
            Segment segment = buffer.f5770c;
            if (j < segment.f5844c - segment.f5843b) {
                Segment segment2 = this.f5770c;
                Segment segment3 = segment2 != null ? segment2.f5848g : null;
                if (segment3 != null && segment3.f5846e) {
                    if ((((long) segment3.f5844c) + j) - ((long) (segment3.f5845d ? 0 : segment3.f5843b)) <= 8192) {
                        segment.f(segment3, (int) j);
                        buffer.f5771d -= j;
                        this.f5771d += j;
                        return;
                    }
                }
                buffer.f5770c = segment.e((int) j);
            }
            Segment segment4 = buffer.f5770c;
            long j2 = segment4.f5844c - segment4.f5843b;
            buffer.f5770c = segment4.b();
            Segment segment5 = this.f5770c;
            if (segment5 == null) {
                this.f5770c = segment4;
                segment4.f5848g = segment4;
                segment4.f5847f = segment4;
            } else {
                segment5.f5848g.c(segment4).a();
            }
            buffer.f5771d -= j2;
            this.f5771d += j2;
            j -= j2;
        }
    }

    public final ByteString h0() {
        long j = this.f5771d;
        if (j <= 2147483647L) {
            return i0((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f5771d);
    }

    public int hashCode() {
        Segment segment = this.f5770c;
        if (segment == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.f5844c;
            for (int i4 = segment.f5843b; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.f5842a[i4];
            }
            segment = segment.f5847f;
        } while (segment != this.f5770c);
        return i2;
    }

    public final ByteString i0(int i2) {
        return i2 == 0 ? ByteString.f5781c : new SegmentedByteString(this, i2);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    Segment j0(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.f5770c;
        if (segment != null) {
            Segment segment2 = segment.f5848g;
            return (segment2.f5844c + i2 > 8192 || !segment2.f5846e) ? segment2.c(SegmentPool.b()) : segment2;
        }
        Segment segmentB = SegmentPool.b();
        this.f5770c = segmentB;
        segmentB.f5848g = segmentB;
        segmentB.f5847f = segmentB;
        return segmentB;
    }

    @Override // okio.BufferedSource
    public void k(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int iZ = Z(bArr, i2, bArr.length - i2);
            if (iZ == -1) {
                throw new EOFException();
            }
            i2 += iZ;
        }
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public Buffer i(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.y(this);
        return this;
    }

    @Override // okio.BufferedSource
    public short l() {
        return Util.d(u());
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public Buffer f(byte[] bArr) {
        if (bArr != null) {
            return g(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public long m(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jP = source.p(this, 8192L);
            if (jP == -1) {
                return j;
            }
            j += jP;
        }
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
    public Buffer g(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i3;
        Util.b(bArr.length, i2, j);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment segmentJ0 = j0(1);
            int iMin = Math.min(i4 - i2, 8192 - segmentJ0.f5844c);
            System.arraycopy(bArr, i2, segmentJ0.f5842a, segmentJ0.f5844c, iMin);
            i2 += iMin;
            segmentJ0.f5844c += iMin;
        }
        this.f5771d += j;
        return this;
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public Buffer J(int i2) {
        Segment segmentJ0 = j0(1);
        byte[] bArr = segmentJ0.f5842a;
        int i3 = segmentJ0.f5844c;
        segmentJ0.f5844c = i3 + 1;
        bArr[i3] = (byte) i2;
        this.f5771d++;
        return this;
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public Buffer F(long j) {
        if (j == 0) {
            return J(48);
        }
        boolean z = false;
        int i2 = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return D("-9223372036854775808");
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
        Segment segmentJ0 = j0(i2);
        byte[] bArr = segmentJ0.f5842a;
        int i3 = segmentJ0.f5844c + i2;
        while (j != 0) {
            i3--;
            bArr[i3] = f5769b[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        segmentJ0.f5844c += i2;
        this.f5771d += (long) i2;
        return this;
    }

    @Override // okio.Source
    public long p(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.f5771d;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        buffer.h(this, j);
        return j;
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: p0, reason: merged with bridge method [inline-methods] */
    public Buffer o(long j) {
        if (j == 0) {
            return J(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment segmentJ0 = j0(iNumberOfTrailingZeros);
        byte[] bArr = segmentJ0.f5842a;
        int i2 = segmentJ0.f5844c;
        for (int i3 = (i2 + iNumberOfTrailingZeros) - 1; i3 >= i2; i3--) {
            bArr[i3] = f5769b[(int) (15 & j)];
            j >>>= 4;
        }
        segmentJ0.f5844c += iNumberOfTrailingZeros;
        this.f5771d += (long) iNumberOfTrailingZeros;
        return this;
    }

    @Override // okio.BufferedSource
    public ByteString q(long j) {
        return new ByteString(L(j));
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
    public Buffer x(int i2) {
        Segment segmentJ0 = j0(4);
        byte[] bArr = segmentJ0.f5842a;
        int i3 = segmentJ0.f5844c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        segmentJ0.f5844c = i6 + 1;
        this.f5771d += 4;
        return this;
    }

    @Override // okio.BufferedSource
    public String r(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jX = X((byte) 10, 0L, j2);
        if (jX != -1) {
            return e0(jX);
        }
        if (j2 < g0() && W(j2 - 1) == 13 && W(j2) == 10) {
            return e0(j2);
        }
        Buffer buffer = new Buffer();
        U(buffer, 0L, Math.min(32L, g0()));
        throw new EOFException("\\n not found: limit=" + Math.min(g0(), j) + " content=" + buffer.a0().k() + (char) 8230);
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: r0, reason: merged with bridge method [inline-methods] */
    public Buffer H(int i2) {
        return x(Util.c(i2));
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        Segment segment = this.f5770c;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), segment.f5844c - segment.f5843b);
        byteBuffer.put(segment.f5842a, segment.f5843b, iMin);
        int i2 = segment.f5843b + iMin;
        segment.f5843b = i2;
        this.f5771d -= (long) iMin;
        if (i2 == segment.f5844c) {
            this.f5770c = segment.b();
            SegmentPool.a(segment);
        }
        return iMin;
    }

    @Override // okio.BufferedSource
    public void s(long j) throws EOFException {
        while (j > 0) {
            if (this.f5770c == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, r0.f5844c - r0.f5843b);
            long j2 = iMin;
            this.f5771d -= j2;
            j -= j2;
            Segment segment = this.f5770c;
            int i2 = segment.f5843b + iMin;
            segment.f5843b = i2;
            if (i2 == segment.f5844c) {
                this.f5770c = segment.b();
                SegmentPool.a(segment);
            }
        }
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public Buffer v(int i2) {
        Segment segmentJ0 = j0(2);
        byte[] bArr = segmentJ0.f5842a;
        int i3 = segmentJ0.f5844c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        segmentJ0.f5844c = i4 + 1;
        this.f5771d += 2;
        return this;
    }

    @Override // okio.BufferedSource
    public long t(Sink sink) {
        long j = this.f5771d;
        if (j > 0) {
            sink.h(this, j);
        }
        return j;
    }

    public Buffer t0(String str, int i2, int i3, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i2 < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
        }
        if (i3 <= str.length()) {
            if (charset == null) {
                throw new IllegalArgumentException("charset == null");
            }
            if (charset.equals(Util.f5857a)) {
                return v0(str, i2, i3);
            }
            byte[] bytes = str.substring(i2, i3).getBytes(charset);
            return g(bytes, 0, bytes.length);
        }
        throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
    }

    public String toString() {
        return h0().toString();
    }

    @Override // okio.BufferedSource
    public short u() {
        long j = this.f5771d;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.f5771d);
        }
        Segment segment = this.f5770c;
        int i2 = segment.f5843b;
        int i3 = segment.f5844c;
        if (i3 - i2 < 2) {
            return (short) (((P() & 255) << 8) | (P() & 255));
        }
        byte[] bArr = segment.f5842a;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
        this.f5771d = j - 2;
        if (i5 == i3) {
            this.f5770c = segment.b();
            SegmentPool.a(segment);
        } else {
            segment.f5843b = i5;
        }
        return (short) i6;
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
    public Buffer D(String str) {
        return v0(str, 0, str.length());
    }

    public Buffer v0(String str, int i2, int i3) {
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
                Segment segmentJ0 = j0(1);
                byte[] bArr = segmentJ0.f5842a;
                int i4 = segmentJ0.f5844c - i2;
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
                int i6 = segmentJ0.f5844c;
                int i7 = (i4 + i5) - i6;
                segmentJ0.f5844c = i6 + i7;
                this.f5771d += (long) i7;
                i2 = i5;
            } else {
                if (cCharAt < 2048) {
                    J((cCharAt >> 6) | 192);
                    J((cCharAt & '?') | 128);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    J((cCharAt >> '\f') | 224);
                    J(((cCharAt >> 6) & 63) | 128);
                    J((cCharAt & '?') | 128);
                } else {
                    int i8 = i2 + 1;
                    char cCharAt3 = i8 < i3 ? str.charAt(i8) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        J(63);
                        i2 = i8;
                    } else {
                        int i9 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        J((i9 >> 18) | 240);
                        J(((i9 >> 12) & 63) | 128);
                        J(((i9 >> 6) & 63) | 128);
                        J((i9 & 63) | 128);
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
        return this;
    }

    @Override // okio.BufferedSource
    public int w() {
        long j = this.f5771d;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.f5771d);
        }
        Segment segment = this.f5770c;
        int i2 = segment.f5843b;
        int i3 = segment.f5844c;
        if (i3 - i2 < 4) {
            return ((P() & 255) << 24) | ((P() & 255) << 16) | ((P() & 255) << 8) | (P() & 255);
        }
        byte[] bArr = segment.f5842a;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
        int i7 = i5 + 1;
        int i8 = i6 | ((bArr[i5] & 255) << 8);
        int i9 = i7 + 1;
        int i10 = i8 | (bArr[i7] & 255);
        this.f5771d = j - 4;
        if (i9 == i3) {
            this.f5770c = segment.b();
            SegmentPool.a(segment);
        } else {
            segment.f5843b = i9;
        }
        return i10;
    }

    public Buffer w0(int i2) {
        if (i2 < 128) {
            J(i2);
        } else if (i2 < 2048) {
            J((i2 >> 6) | 192);
            J((i2 & 63) | 128);
        } else if (i2 < 65536) {
            if (i2 < 55296 || i2 > 57343) {
                J((i2 >> 12) | 224);
                J(((i2 >> 6) & 63) | 128);
                J((i2 & 63) | 128);
            } else {
                J(63);
            }
        } else {
            if (i2 > 1114111) {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
            }
            J((i2 >> 18) | 240);
            J(((i2 >> 12) & 63) | 128);
            J(((i2 >> 6) & 63) | 128);
            J((i2 & 63) | 128);
        }
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i2 = iRemaining;
        while (i2 > 0) {
            Segment segmentJ0 = j0(1);
            int iMin = Math.min(i2, 8192 - segmentJ0.f5844c);
            byteBuffer.get(segmentJ0.f5842a, segmentJ0.f5844c, iMin);
            i2 -= iMin;
            segmentJ0.f5844c += iMin;
        }
        this.f5771d += (long) iRemaining;
        return iRemaining;
    }

    @Override // okio.BufferedSource
    public String z() {
        return r(Long.MAX_VALUE);
    }
}

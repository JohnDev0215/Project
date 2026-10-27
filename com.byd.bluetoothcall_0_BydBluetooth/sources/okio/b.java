package okio;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements d, c, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f4370b = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    h f4371c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long f4372d;

    private boolean q(h hVar, int i, ByteString byteString, int i2, int i3) {
        int i4 = hVar.f4383c;
        byte[] bArr = hVar.f4381a;
        while (i2 < i3) {
            if (i == i4) {
                hVar = hVar.f;
                byte[] bArr2 = hVar.f4381a;
                bArr = bArr2;
                i = hVar.f4382b;
                i4 = hVar.f4383c;
            }
            if (bArr[i] != byteString.getByte(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public String A(long j) throws EOFException {
        return y(j, l.f4391a);
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
    int B(okio.f r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            okio.h r2 = r1.f4371c
            r3 = -2
            if (r2 != 0) goto L13
            if (r19 == 0) goto Lc
            return r3
        Lc:
            okio.ByteString r2 = okio.ByteString.EMPTY
            int r0 = r0.indexOf(r2)
            return r0
        L13:
            byte[] r4 = r2.f4381a
            int r5 = r2.f4382b
            int r6 = r2.f4383c
            int[] r0 = r0.f4377c
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
            okio.h r4 = r10.f
            int r6 = r4.f4382b
            byte[] r9 = r4.f4381a
            int r10 = r4.f4383c
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
            okio.h r10 = r10.f
            int r3 = r10.f4382b
            byte[] r4 = r10.f4381a
            int r6 = r10.f4383c
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
        throw new UnsupportedOperationException("Method not decompiled: okio.b.B(okio.f, boolean):int");
    }

    public final long C() {
        return this.f4372d;
    }

    public void D(long j) throws EOFException {
        while (j > 0) {
            if (this.f4371c == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, r0.f4383c - r0.f4382b);
            long j2 = iMin;
            this.f4372d -= j2;
            j -= j2;
            h hVar = this.f4371c;
            int i = hVar.f4382b + iMin;
            hVar.f4382b = i;
            if (i == hVar.f4383c) {
                this.f4371c = hVar.b();
                i.a(hVar);
            }
        }
    }

    public final ByteString E() {
        long j = this.f4372d;
        if (j <= 2147483647L) {
            return F((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f4372d);
    }

    public final ByteString F(int i) {
        return i == 0 ? ByteString.EMPTY : new SegmentedByteString(this, i);
    }

    h G(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        h hVar = this.f4371c;
        if (hVar != null) {
            h hVar2 = hVar.g;
            return (hVar2.f4383c + i > 8192 || !hVar2.f4385e) ? hVar2.c(i.b()) : hVar2;
        }
        h hVarB = i.b();
        this.f4371c = hVarB;
        hVarB.g = hVarB;
        hVarB.f = hVarB;
        return hVarB;
    }

    @Override // okio.c
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public b b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i2;
        l.b(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            h hVarG = G(1);
            int iMin = Math.min(i3 - i, 8192 - hVarG.f4383c);
            System.arraycopy(bArr, i, hVarG.f4381a, hVarG.f4383c, iMin);
            i += iMin;
            hVarG.f4383c += iMin;
        }
        this.f4372d += j;
        return this;
    }

    public void I(b bVar, long j) {
        if (bVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (bVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        l.b(bVar.f4372d, 0L, j);
        while (j > 0) {
            h hVar = bVar.f4371c;
            if (j < hVar.f4383c - hVar.f4382b) {
                h hVar2 = this.f4371c;
                h hVar3 = hVar2 != null ? hVar2.g : null;
                if (hVar3 != null && hVar3.f4385e) {
                    if ((((long) hVar3.f4383c) + j) - ((long) (hVar3.f4384d ? 0 : hVar3.f4382b)) <= 8192) {
                        hVar.f(hVar3, (int) j);
                        bVar.f4372d -= j;
                        this.f4372d += j;
                        return;
                    }
                }
                bVar.f4371c = hVar.e((int) j);
            }
            h hVar4 = bVar.f4371c;
            long j2 = hVar4.f4383c - hVar4.f4382b;
            bVar.f4371c = hVar4.b();
            h hVar5 = this.f4371c;
            if (hVar5 == null) {
                this.f4371c = hVar4;
                hVar4.g = hVar4;
                hVar4.f = hVar4;
            } else {
                hVar5.g.c(hVar4).a();
            }
            bVar.f4372d -= j2;
            this.f4372d += j2;
            j -= j2;
        }
    }

    @Override // okio.c
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public b i(int i) {
        h hVarG = G(1);
        byte[] bArr = hVarG.f4381a;
        int i2 = hVarG.f4383c;
        hVarG.f4383c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f4372d++;
        return this;
    }

    public b K(int i) {
        h hVarG = G(4);
        byte[] bArr = hVarG.f4381a;
        int i2 = hVarG.f4383c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        hVarG.f4383c = i5 + 1;
        this.f4372d += 4;
        return this;
    }

    @Override // okio.c
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public b f(String str) {
        return c(str, 0, str.length());
    }

    @Override // okio.c
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public b c(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                h hVarG = G(1);
                byte[] bArr = hVarG.f4381a;
                int i3 = hVarG.f4383c - i;
                int iMin = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                bArr[i + i3] = (byte) cCharAt;
                while (i4 < iMin) {
                    char cCharAt2 = str.charAt(i4);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i4 + i3] = (byte) cCharAt2;
                    i4++;
                }
                int i5 = hVarG.f4383c;
                int i6 = (i3 + i4) - i5;
                hVarG.f4383c = i5 + i6;
                this.f4372d += (long) i6;
                i = i4;
            } else {
                if (cCharAt < 2048) {
                    i((cCharAt >> 6) | 192);
                    i((cCharAt & '?') | 128);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i((cCharAt >> '\f') | 224);
                    i(((cCharAt >> 6) & 63) | 128);
                    i((cCharAt & '?') | 128);
                } else {
                    int i7 = i + 1;
                    char cCharAt3 = i7 < i2 ? str.charAt(i7) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        i(63);
                        i = i7;
                    } else {
                        int i8 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2;
                        i((i8 >> 18) | 240);
                        i(((i8 >> 12) & 63) | 128);
                        i(((i8 >> 6) & 63) | 128);
                        i((i8 & 63) | 128);
                        i += 2;
                    }
                }
                i++;
            }
        }
        return this;
    }

    @Override // okio.d
    public boolean a(long j) {
        return this.f4372d >= j;
    }

    @Override // okio.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
    }

    @Override // okio.d
    public long d(ByteString byteString) {
        return p(byteString, 0L);
    }

    @Override // okio.j
    public long e(b bVar, long j) {
        if (bVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.f4372d;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        bVar.I(this, j);
        return j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        long j = this.f4372d;
        if (j != bVar.f4372d) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        h hVar = this.f4371c;
        h hVar2 = bVar.f4371c;
        int i = hVar.f4382b;
        int i2 = hVar2.f4382b;
        while (j2 < this.f4372d) {
            long jMin = Math.min(hVar.f4383c - i, hVar2.f4383c - i2);
            int i3 = 0;
            while (i3 < jMin) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (hVar.f4381a[i] != hVar2.f4381a[i2]) {
                    return false;
                }
                i3++;
                i = i4;
                i2 = i5;
            }
            if (i == hVar.f4383c) {
                hVar = hVar.f;
                i = hVar.f4382b;
            }
            if (i2 == hVar2.f4383c) {
                hVar2 = hVar2.f;
                i2 = hVar2.f4382b;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // java.io.Flushable
    public void flush() {
    }

    @Override // okio.d
    public long g(ByteString byteString) throws IOException {
        return o(byteString, 0L);
    }

    @Override // okio.d
    public b h() {
        return this;
    }

    public int hashCode() {
        h hVar = this.f4371c;
        if (hVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = hVar.f4383c;
            for (int i3 = hVar.f4382b; i3 < i2; i3++) {
                i = (i * 31) + hVar.f4381a[i3];
            }
            hVar = hVar.f;
        } while (hVar != this.f4371c);
        return i;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // okio.d
    public int j(f fVar) {
        int iB = B(fVar, false);
        if (iB == -1) {
            return -1;
        }
        try {
            D(fVar.f4376b[iB].size());
            return iB;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public final void k() {
        try {
            D(this.f4372d);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public b clone() {
        b bVar = new b();
        if (this.f4372d == 0) {
            return bVar;
        }
        h hVarD = this.f4371c.d();
        bVar.f4371c = hVarD;
        hVarD.g = hVarD;
        hVarD.f = hVarD;
        h hVar = this.f4371c;
        while (true) {
            hVar = hVar.f;
            if (hVar == this.f4371c) {
                bVar.f4372d = this.f4372d;
                return bVar;
            }
            bVar.f4371c.g.c(hVar.d());
        }
    }

    public boolean m() {
        return this.f4372d == 0;
    }

    public final byte n(long j) {
        int i;
        l.b(this.f4372d, j, 1L);
        long j2 = this.f4372d;
        if (j2 - j <= j) {
            long j3 = j - j2;
            h hVar = this.f4371c;
            do {
                hVar = hVar.g;
                int i2 = hVar.f4383c;
                i = hVar.f4382b;
                j3 += (long) (i2 - i);
            } while (j3 < 0);
            return hVar.f4381a[i + ((int) j3)];
        }
        h hVar2 = this.f4371c;
        while (true) {
            int i3 = hVar2.f4383c;
            int i4 = hVar2.f4382b;
            long j4 = i3 - i4;
            if (j < j4) {
                return hVar2.f4381a[i4 + ((int) j)];
            }
            j -= j4;
            hVar2 = hVar2.f;
        }
    }

    public long o(ByteString byteString, long j) throws IOException {
        byte[] bArr;
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        h hVar = this.f4371c;
        long j3 = -1;
        if (hVar == null) {
            return -1L;
        }
        long j4 = this.f4372d;
        if (j4 - j < j) {
            while (j4 > j) {
                hVar = hVar.g;
                j4 -= (long) (hVar.f4383c - hVar.f4382b);
            }
        } else {
            while (true) {
                long j5 = ((long) (hVar.f4383c - hVar.f4382b)) + j2;
                if (j5 >= j) {
                    break;
                }
                hVar = hVar.f;
                j2 = j5;
            }
            j4 = j2;
        }
        byte b2 = byteString.getByte(0);
        int size = byteString.size();
        long j6 = 1 + (this.f4372d - ((long) size));
        long j7 = j;
        h hVar2 = hVar;
        long j8 = j4;
        while (j8 < j6) {
            byte[] bArr2 = hVar2.f4381a;
            int iMin = (int) Math.min(hVar2.f4383c, (((long) hVar2.f4382b) + j6) - j8);
            int i = (int) ((((long) hVar2.f4382b) + j7) - j8);
            while (i < iMin) {
                if (bArr2[i] == b2) {
                    bArr = bArr2;
                    if (q(hVar2, i + 1, byteString, 1, size)) {
                        return ((long) (i - hVar2.f4382b)) + j8;
                    }
                } else {
                    bArr = bArr2;
                }
                i++;
                bArr2 = bArr;
            }
            j8 += (long) (hVar2.f4383c - hVar2.f4382b);
            hVar2 = hVar2.f;
            j7 = j8;
            j3 = -1;
        }
        return j3;
    }

    public long p(ByteString byteString, long j) {
        int i;
        int i2;
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        h hVar = this.f4371c;
        if (hVar == null) {
            return -1L;
        }
        long j3 = this.f4372d;
        if (j3 - j < j) {
            while (j3 > j) {
                hVar = hVar.g;
                j3 -= (long) (hVar.f4383c - hVar.f4382b);
            }
        } else {
            while (true) {
                long j4 = ((long) (hVar.f4383c - hVar.f4382b)) + j2;
                if (j4 >= j) {
                    break;
                }
                hVar = hVar.f;
                j2 = j4;
            }
            j3 = j2;
        }
        if (byteString.size() == 2) {
            byte b2 = byteString.getByte(0);
            byte b3 = byteString.getByte(1);
            while (j3 < this.f4372d) {
                byte[] bArr = hVar.f4381a;
                i = (int) ((((long) hVar.f4382b) + j) - j3);
                int i3 = hVar.f4383c;
                while (i < i3) {
                    byte b4 = bArr[i];
                    if (b4 == b2 || b4 == b3) {
                        i2 = hVar.f4382b;
                        return ((long) (i - i2)) + j3;
                    }
                    i++;
                }
                j3 += (long) (hVar.f4383c - hVar.f4382b);
                hVar = hVar.f;
                j = j3;
            }
            return -1L;
        }
        byte[] bArrInternalArray = byteString.internalArray();
        while (j3 < this.f4372d) {
            byte[] bArr2 = hVar.f4381a;
            i = (int) ((((long) hVar.f4382b) + j) - j3);
            int i4 = hVar.f4383c;
            while (i < i4) {
                byte b5 = bArr2[i];
                for (byte b6 : bArrInternalArray) {
                    if (b5 == b6) {
                        i2 = hVar.f4382b;
                        return ((long) (i - i2)) + j3;
                    }
                }
                i++;
            }
            j3 += (long) (hVar.f4383c - hVar.f4382b);
            hVar = hVar.f;
            j = j3;
        }
        return -1L;
    }

    public int r(byte[] bArr, int i, int i2) {
        l.b(bArr.length, i, i2);
        h hVar = this.f4371c;
        if (hVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, hVar.f4383c - hVar.f4382b);
        System.arraycopy(hVar.f4381a, hVar.f4382b, bArr, i, iMin);
        int i3 = hVar.f4382b + iMin;
        hVar.f4382b = i3;
        this.f4372d -= (long) iMin;
        if (i3 == hVar.f4383c) {
            this.f4371c = hVar.b();
            i.a(hVar);
        }
        return iMin;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        h hVar = this.f4371c;
        if (hVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), hVar.f4383c - hVar.f4382b);
        byteBuffer.put(hVar.f4381a, hVar.f4382b, iMin);
        int i = hVar.f4382b + iMin;
        hVar.f4382b = i;
        this.f4372d -= (long) iMin;
        if (i == hVar.f4383c) {
            this.f4371c = hVar.b();
            i.a(hVar);
        }
        return iMin;
    }

    public byte s() {
        long j = this.f4372d;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        h hVar = this.f4371c;
        int i = hVar.f4382b;
        int i2 = hVar.f4383c;
        int i3 = i + 1;
        byte b2 = hVar.f4381a[i];
        this.f4372d = j - 1;
        if (i3 == i2) {
            this.f4371c = hVar.b();
            i.a(hVar);
        } else {
            hVar.f4382b = i3;
        }
        return b2;
    }

    public byte[] t() {
        try {
            return u(this.f4372d);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public String toString() {
        return E().toString();
    }

    public byte[] u(long j) throws EOFException {
        l.b(this.f4372d, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            w(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    public ByteString v() {
        return new ByteString(t());
    }

    public void w(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iR = r(bArr, i, bArr.length - i);
            if (iR == -1) {
                throw new EOFException();
            }
            i += iR;
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            h hVarG = G(1);
            int iMin = Math.min(i, 8192 - hVarG.f4383c);
            byteBuffer.get(hVarG.f4381a, hVarG.f4383c, iMin);
            i -= iMin;
            hVarG.f4383c += iMin;
        }
        this.f4372d += (long) iRemaining;
        return iRemaining;
    }

    public int x() {
        long j = this.f4372d;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.f4372d);
        }
        h hVar = this.f4371c;
        int i = hVar.f4382b;
        int i2 = hVar.f4383c;
        if (i2 - i < 4) {
            return ((s() & 255) << 24) | ((s() & 255) << 16) | ((s() & 255) << 8) | (s() & 255);
        }
        byte[] bArr = hVar.f4381a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f4372d = j - 4;
        if (i8 == i2) {
            this.f4371c = hVar.b();
            i.a(hVar);
        } else {
            hVar.f4382b = i8;
        }
        return i9;
    }

    public String y(long j, Charset charset) throws EOFException {
        l.b(this.f4372d, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        h hVar = this.f4371c;
        if (((long) hVar.f4382b) + j > hVar.f4383c) {
            return new String(u(j), charset);
        }
        String str = new String(hVar.f4381a, hVar.f4382b, (int) j, charset);
        int i = (int) (((long) hVar.f4382b) + j);
        hVar.f4382b = i;
        this.f4372d -= j;
        if (i == hVar.f4383c) {
            this.f4371c = hVar.b();
            i.a(hVar);
        }
        return str;
    }

    public String z() {
        try {
            return y(this.f4372d, l.f4391a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }
}

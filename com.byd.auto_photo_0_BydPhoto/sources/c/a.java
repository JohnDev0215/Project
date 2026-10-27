package c;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements c, b, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final byte[] f625a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    h f626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f627c;

    private boolean p(h hVar, int i, d dVar, int i2, int i3) {
        int i4 = hVar.f641c;
        byte[] bArr = hVar.f639a;
        while (i2 < i3) {
            if (i == i4) {
                hVar = hVar.f;
                byte[] bArr2 = hVar.f639a;
                bArr = bArr2;
                i = hVar.f640b;
                i4 = hVar.f641c;
            }
            if (bArr[i] != dVar.d(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public final long A() {
        return this.f627c;
    }

    public void B(long j) throws EOFException {
        while (j > 0) {
            if (this.f626b == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, r0.f641c - r0.f640b);
            long j2 = iMin;
            this.f627c -= j2;
            j -= j2;
            h hVar = this.f626b;
            int i = hVar.f640b + iMin;
            hVar.f640b = i;
            if (i == hVar.f641c) {
                this.f626b = hVar.b();
                i.a(hVar);
            }
        }
    }

    public final d C() {
        long j = this.f627c;
        if (j <= 2147483647L) {
            return D((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f627c);
    }

    public final d D(int i) {
        return i == 0 ? d.f629b : new j(this, i);
    }

    h E(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        h hVar = this.f626b;
        if (hVar != null) {
            h hVar2 = hVar.g;
            return (hVar2.f641c + i > 8192 || !hVar2.e) ? hVar2.c(i.b()) : hVar2;
        }
        h hVarB = i.b();
        this.f626b = hVarB;
        hVarB.g = hVarB;
        hVarB.f = hVarB;
        return hVarB;
    }

    public void F(a aVar, long j) {
        if (aVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (aVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        m.b(aVar.f627c, 0L, j);
        while (j > 0) {
            h hVar = aVar.f626b;
            if (j < hVar.f641c - hVar.f640b) {
                h hVar2 = this.f626b;
                h hVar3 = hVar2 != null ? hVar2.g : null;
                if (hVar3 != null && hVar3.e) {
                    if ((((long) hVar3.f641c) + j) - ((long) (hVar3.d ? 0 : hVar3.f640b)) <= 8192) {
                        hVar.f(hVar3, (int) j);
                        aVar.f627c -= j;
                        this.f627c += j;
                        return;
                    }
                }
                aVar.f626b = hVar.e((int) j);
            }
            h hVar4 = aVar.f626b;
            long j2 = hVar4.f641c - hVar4.f640b;
            aVar.f626b = hVar4.b();
            h hVar5 = this.f626b;
            if (hVar5 == null) {
                this.f626b = hVar4;
                hVar4.g = hVar4;
                hVar4.f = hVar4;
            } else {
                hVar5.g.c(hVar4).a();
            }
            aVar.f627c -= j2;
            this.f627c += j2;
            j -= j2;
        }
    }

    @Override // c.b
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public a e(int i) {
        h hVarE = E(1);
        byte[] bArr = hVarE.f639a;
        int i2 = hVarE.f641c;
        hVarE.f641c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f627c++;
        return this;
    }

    public a H(int i) {
        h hVarE = E(4);
        byte[] bArr = hVarE.f639a;
        int i2 = hVarE.f641c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        hVarE.f641c = i5 + 1;
        this.f627c += 4;
        return this;
    }

    @Override // c.b
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public a h(String str) {
        return a(str, 0, str.length());
    }

    @Override // c.b
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public a a(String str, int i, int i2) {
        int i3;
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
                h hVarE = E(1);
                byte[] bArr = hVarE.f639a;
                int i4 = hVarE.f641c - i;
                int iMin = Math.min(i2, 8192 - i4);
                int i5 = i + 1;
                bArr[i + i4] = (byte) cCharAt;
                while (i5 < iMin) {
                    char cCharAt2 = str.charAt(i5);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i5 + i4] = (byte) cCharAt2;
                    i5++;
                }
                int i6 = hVarE.f641c;
                int i7 = (i4 + i5) - i6;
                hVarE.f641c = i6 + i7;
                this.f627c += (long) i7;
                i = i5;
            } else {
                if (cCharAt < 2048) {
                    i3 = (cCharAt >> 6) | 192;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    e((cCharAt >> '\f') | 224);
                    i3 = ((cCharAt >> 6) & 63) | 128;
                } else {
                    int i8 = i + 1;
                    char cCharAt3 = i8 < i2 ? str.charAt(i8) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        e(63);
                        i = i8;
                    } else {
                        int i9 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        e((i9 >> 18) | 240);
                        e(((i9 >> 12) & 63) | 128);
                        e(((i9 >> 6) & 63) | 128);
                        e((i9 & 63) | 128);
                        i += 2;
                    }
                }
                e(i3);
                e((cCharAt & '?') | 128);
                i++;
            }
        }
        return this;
    }

    @Override // c.c
    public boolean b(long j) {
        return this.f627c >= j;
    }

    @Override // c.c
    public long c(d dVar) {
        return n(dVar, 0L);
    }

    @Override // c.k, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
    }

    @Override // c.c
    public a d() {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        long j = this.f627c;
        if (j != aVar.f627c) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        h hVar = this.f626b;
        h hVar2 = aVar.f626b;
        int i = hVar.f640b;
        int i2 = hVar2.f640b;
        while (j2 < this.f627c) {
            long jMin = Math.min(hVar.f641c - i, hVar2.f641c - i2);
            int i3 = 0;
            while (i3 < jMin) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (hVar.f639a[i] != hVar2.f639a[i2]) {
                    return false;
                }
                i3++;
                i = i4;
                i2 = i5;
            }
            if (i == hVar.f641c) {
                hVar = hVar.f;
                i = hVar.f640b;
            }
            if (i2 == hVar2.f641c) {
                hVar2 = hVar2.f;
                i2 = hVar2.f640b;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // c.c
    public long f(d dVar) {
        return o(dVar, 0L);
    }

    @Override // java.io.Flushable
    public void flush() {
    }

    @Override // c.k
    public long g(a aVar, long j) {
        if (aVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.f627c;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        aVar.F(this, j);
        return j;
    }

    public int hashCode() {
        h hVar = this.f626b;
        if (hVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = hVar.f641c;
            for (int i3 = hVar.f640b; i3 < i2; i3++) {
                i = (i * 31) + hVar.f639a[i3];
            }
            hVar = hVar.f;
        } while (hVar != this.f626b);
        return i;
    }

    @Override // c.c
    public int i(f fVar) {
        int iZ = z(fVar, false);
        if (iZ == -1) {
            return -1;
        }
        try {
            B(fVar.f634a[iZ].j());
            return iZ;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final void j() {
        try {
            B(this.f627c);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public a clone() {
        a aVar = new a();
        if (this.f627c == 0) {
            return aVar;
        }
        h hVarD = this.f626b.d();
        aVar.f626b = hVarD;
        hVarD.g = hVarD;
        hVarD.f = hVarD;
        h hVar = this.f626b;
        while (true) {
            hVar = hVar.f;
            if (hVar == this.f626b) {
                aVar.f627c = this.f627c;
                return aVar;
            }
            aVar.f626b.g.c(hVar.d());
        }
    }

    public boolean l() {
        return this.f627c == 0;
    }

    public final byte m(long j) {
        int i;
        m.b(this.f627c, j, 1L);
        long j2 = this.f627c;
        if (j2 - j <= j) {
            long j3 = j - j2;
            h hVar = this.f626b;
            do {
                hVar = hVar.g;
                int i2 = hVar.f641c;
                i = hVar.f640b;
                j3 += (long) (i2 - i);
            } while (j3 < 0);
            return hVar.f639a[i + ((int) j3)];
        }
        h hVar2 = this.f626b;
        while (true) {
            int i3 = hVar2.f641c;
            int i4 = hVar2.f640b;
            long j4 = i3 - i4;
            if (j < j4) {
                return hVar2.f639a[i4 + ((int) j)];
            }
            j -= j4;
            hVar2 = hVar2.f;
        }
    }

    public long n(d dVar, long j) {
        byte[] bArr;
        if (dVar.j() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        h hVar = this.f626b;
        long j3 = -1;
        if (hVar == null) {
            return -1L;
        }
        long j4 = this.f627c;
        if (j4 - j < j) {
            while (j4 > j) {
                hVar = hVar.g;
                j4 -= (long) (hVar.f641c - hVar.f640b);
            }
        } else {
            while (true) {
                long j5 = ((long) (hVar.f641c - hVar.f640b)) + j2;
                if (j5 >= j) {
                    break;
                }
                hVar = hVar.f;
                j2 = j5;
            }
            j4 = j2;
        }
        byte bD = dVar.d(0);
        int iJ = dVar.j();
        long j6 = 1 + (this.f627c - ((long) iJ));
        long j7 = j;
        h hVar2 = hVar;
        long j8 = j4;
        while (j8 < j6) {
            byte[] bArr2 = hVar2.f639a;
            int iMin = (int) Math.min(hVar2.f641c, (((long) hVar2.f640b) + j6) - j8);
            int i = (int) ((((long) hVar2.f640b) + j7) - j8);
            while (i < iMin) {
                if (bArr2[i] == bD) {
                    bArr = bArr2;
                    if (p(hVar2, i + 1, dVar, 1, iJ)) {
                        return ((long) (i - hVar2.f640b)) + j8;
                    }
                } else {
                    bArr = bArr2;
                }
                i++;
                bArr2 = bArr;
            }
            j8 += (long) (hVar2.f641c - hVar2.f640b);
            hVar2 = hVar2.f;
            j7 = j8;
            j3 = -1;
        }
        return j3;
    }

    public long o(d dVar, long j) {
        int i;
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        h hVar = this.f626b;
        if (hVar == null) {
            return -1L;
        }
        long j3 = this.f627c;
        if (j3 - j < j) {
            while (j3 > j) {
                hVar = hVar.g;
                j3 -= (long) (hVar.f641c - hVar.f640b);
            }
        } else {
            while (true) {
                long j4 = ((long) (hVar.f641c - hVar.f640b)) + j2;
                if (j4 >= j) {
                    break;
                }
                hVar = hVar.f;
                j2 = j4;
            }
            j3 = j2;
        }
        if (dVar.j() == 2) {
            byte bD = dVar.d(0);
            byte bD2 = dVar.d(1);
            while (j3 < this.f627c) {
                byte[] bArr = hVar.f639a;
                i = (int) ((((long) hVar.f640b) + j) - j3);
                int i2 = hVar.f641c;
                while (i < i2) {
                    byte b2 = bArr[i];
                    if (b2 == bD || b2 == bD2) {
                        return ((long) (i - hVar.f640b)) + j3;
                    }
                    i++;
                }
                j3 += (long) (hVar.f641c - hVar.f640b);
                hVar = hVar.f;
                j = j3;
            }
            return -1L;
        }
        byte[] bArrF = dVar.f();
        while (j3 < this.f627c) {
            byte[] bArr2 = hVar.f639a;
            i = (int) ((((long) hVar.f640b) + j) - j3);
            int i3 = hVar.f641c;
            while (i < i3) {
                byte b3 = bArr2[i];
                for (byte b4 : bArrF) {
                    if (b3 == b4) {
                        return ((long) (i - hVar.f640b)) + j3;
                    }
                }
                i++;
            }
            j3 += (long) (hVar.f641c - hVar.f640b);
            hVar = hVar.f;
            j = j3;
        }
        return -1L;
    }

    public byte q() {
        long j = this.f627c;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        h hVar = this.f626b;
        int i = hVar.f640b;
        int i2 = hVar.f641c;
        int i3 = i + 1;
        byte b2 = hVar.f639a[i];
        this.f627c = j - 1;
        if (i3 == i2) {
            this.f626b = hVar.b();
            i.a(hVar);
        } else {
            hVar.f640b = i3;
        }
        return b2;
    }

    public byte[] r() {
        try {
            return s(this.f627c);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        h hVar = this.f626b;
        if (hVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), hVar.f641c - hVar.f640b);
        byteBuffer.put(hVar.f639a, hVar.f640b, iMin);
        int i = hVar.f640b + iMin;
        hVar.f640b = i;
        this.f627c -= (long) iMin;
        if (i == hVar.f641c) {
            this.f626b = hVar.b();
            i.a(hVar);
        }
        return iMin;
    }

    public int read(byte[] bArr, int i, int i2) {
        m.b(bArr.length, i, i2);
        h hVar = this.f626b;
        if (hVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, hVar.f641c - hVar.f640b);
        System.arraycopy(hVar.f639a, hVar.f640b, bArr, i, iMin);
        int i3 = hVar.f640b + iMin;
        hVar.f640b = i3;
        this.f627c -= (long) iMin;
        if (i3 == hVar.f641c) {
            this.f626b = hVar.b();
            i.a(hVar);
        }
        return iMin;
    }

    public byte[] s(long j) throws EOFException {
        m.b(this.f627c, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            u(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    public d t() {
        return new d(r());
    }

    public String toString() {
        return C().toString();
    }

    public void u(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int i2 = read(bArr, i, bArr.length - i);
            if (i2 == -1) {
                throw new EOFException();
            }
            i += i2;
        }
    }

    public int v() {
        long j = this.f627c;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.f627c);
        }
        h hVar = this.f626b;
        int i = hVar.f640b;
        int i2 = hVar.f641c;
        if (i2 - i < 4) {
            return ((q() & 255) << 24) | ((q() & 255) << 16) | ((q() & 255) << 8) | (q() & 255);
        }
        byte[] bArr = hVar.f639a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f627c = j - 4;
        if (i8 == i2) {
            this.f626b = hVar.b();
            i.a(hVar);
        } else {
            hVar.f640b = i8;
        }
        return i9;
    }

    public String w(long j, Charset charset) {
        m.b(this.f627c, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        h hVar = this.f626b;
        if (((long) hVar.f640b) + j > hVar.f641c) {
            return new String(s(j), charset);
        }
        String str = new String(hVar.f639a, hVar.f640b, (int) j, charset);
        int i = (int) (((long) hVar.f640b) + j);
        hVar.f640b = i;
        this.f627c -= j;
        if (i == hVar.f641c) {
            this.f626b = hVar.b();
            i.a(hVar);
        }
        return str;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            h hVarE = E(1);
            int iMin = Math.min(i, 8192 - hVarE.f641c);
            byteBuffer.get(hVarE.f639a, hVarE.f641c, iMin);
            i -= iMin;
            hVarE.f641c += iMin;
        }
        this.f627c += (long) iRemaining;
        return iRemaining;
    }

    public String x() {
        try {
            return w(this.f627c, m.f647a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String y(long j) {
        return w(j, m.f647a);
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
    int z(c.f r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            c.h r2 = r1.f626b
            r3 = -2
            if (r2 != 0) goto L13
            if (r19 == 0) goto Lc
            return r3
        Lc:
            c.d r2 = c.d.f629b
            int r0 = r0.indexOf(r2)
            return r0
        L13:
            byte[] r4 = r2.f639a
            int r5 = r2.f640b
            int r6 = r2.f641c
            int[] r0 = r0.f635b
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
            c.h r4 = r10.f
            int r6 = r4.f640b
            byte[] r9 = r4.f639a
            int r10 = r4.f641c
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
            c.h r10 = r10.f
            int r3 = r10.f640b
            byte[] r4 = r10.f639a
            int r6 = r10.f641c
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
        throw new UnsupportedOperationException("Method not decompiled: c.a.z(c.f, boolean):int");
    }
}

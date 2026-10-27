package i7;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okio.ByteString;
import okio.SegmentedByteString;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements d, c, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f5511d = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    public h f5512e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5513f;

    public String B(long j8, Charset charset) throws EOFException {
        l.b(this.f5513f, 0L, j8);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j8 > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j8);
        }
        if (j8 == 0) {
            return "";
        }
        h hVar = this.f5512e;
        if (((long) hVar.f5523b) + j8 > hVar.f5524c) {
            return new String(t(j8), charset);
        }
        String str = new String(hVar.f5522a, hVar.f5523b, (int) j8, charset);
        int i8 = (int) (((long) hVar.f5523b) + j8);
        hVar.f5523b = i8;
        this.f5513f -= j8;
        if (i8 == hVar.f5524c) {
            this.f5512e = hVar.b();
            i.a(hVar);
        }
        return str;
    }

    public String C() {
        try {
            return B(this.f5513f, l.f5534a);
        } catch (EOFException e8) {
            throw new AssertionError(e8);
        }
    }

    @Override // i7.d
    public long D(ByteString byteString) throws IOException {
        return m(byteString, 0L);
    }

    public String E(long j8) throws EOFException {
        return B(j8, l.f5534a);
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
    public int F(i7.f r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            i7.h r2 = r1.f5512e
            r3 = -2
            if (r2 != 0) goto L13
            if (r19 == 0) goto Lc
            return r3
        Lc:
            okio.ByteString r2 = okio.ByteString.EMPTY
            int r0 = r0.indexOf(r2)
            return r0
        L13:
            byte[] r4 = r2.f5522a
            int r5 = r2.f5523b
            int r6 = r2.f5524c
            int[] r0 = r0.f5518e
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
            i7.h r4 = r10.f5527f
            int r6 = r4.f5523b
            byte[] r9 = r4.f5522a
            int r10 = r4.f5524c
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
            i7.h r10 = r10.f5527f
            int r3 = r10.f5523b
            byte[] r4 = r10.f5522a
            int r6 = r10.f5524c
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
        throw new UnsupportedOperationException("Method not decompiled: i7.b.F(i7.f, boolean):int");
    }

    @Override // i7.d
    public b G() {
        return this;
    }

    public final long I() {
        return this.f5513f;
    }

    public void L(long j8) throws EOFException {
        while (j8 > 0) {
            if (this.f5512e == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j8, r0.f5524c - r0.f5523b);
            long j9 = iMin;
            this.f5513f -= j9;
            j8 -= j9;
            h hVar = this.f5512e;
            int i8 = hVar.f5523b + iMin;
            hVar.f5523b = i8;
            if (i8 == hVar.f5524c) {
                this.f5512e = hVar.b();
                i.a(hVar);
            }
        }
    }

    public final ByteString M() {
        long j8 = this.f5513f;
        if (j8 <= 2147483647L) {
            return N((int) j8);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f5513f);
    }

    public final ByteString N(int i8) {
        return i8 == 0 ? ByteString.EMPTY : new SegmentedByteString(this, i8);
    }

    public h O(int i8) {
        if (i8 < 1 || i8 > 8192) {
            throw new IllegalArgumentException();
        }
        h hVar = this.f5512e;
        if (hVar != null) {
            h hVar2 = hVar.f5528g;
            return (hVar2.f5524c + i8 > 8192 || !hVar2.f5526e) ? hVar2.c(i.b()) : hVar2;
        }
        h hVarB = i.b();
        this.f5512e = hVarB;
        hVarB.f5528g = hVarB;
        hVarB.f5527f = hVarB;
        return hVarB;
    }

    @Override // i7.c
    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public b b(byte[] bArr, int i8, int i9) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j8 = i9;
        l.b(bArr.length, i8, j8);
        int i10 = i9 + i8;
        while (i8 < i10) {
            h hVarO = O(1);
            int iMin = Math.min(i10 - i8, 8192 - hVarO.f5524c);
            System.arraycopy(bArr, i8, hVarO.f5522a, hVarO.f5524c, iMin);
            i8 += iMin;
            hVarO.f5524c += iMin;
        }
        this.f5513f += j8;
        return this;
    }

    @Override // i7.d
    public long Q(ByteString byteString) {
        return n(byteString, 0L);
    }

    public void R(b bVar, long j8) {
        if (bVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (bVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        l.b(bVar.f5513f, 0L, j8);
        while (j8 > 0) {
            h hVar = bVar.f5512e;
            if (j8 < hVar.f5524c - hVar.f5523b) {
                h hVar2 = this.f5512e;
                h hVar3 = hVar2 != null ? hVar2.f5528g : null;
                if (hVar3 != null && hVar3.f5526e) {
                    if ((((long) hVar3.f5524c) + j8) - ((long) (hVar3.f5525d ? 0 : hVar3.f5523b)) <= 8192) {
                        hVar.f(hVar3, (int) j8);
                        bVar.f5513f -= j8;
                        this.f5513f += j8;
                        return;
                    }
                }
                bVar.f5512e = hVar.e((int) j8);
            }
            h hVar4 = bVar.f5512e;
            long j9 = hVar4.f5524c - hVar4.f5523b;
            bVar.f5512e = hVar4.b();
            h hVar5 = this.f5512e;
            if (hVar5 == null) {
                this.f5512e = hVar4;
                hVar4.f5528g = hVar4;
                hVar4.f5527f = hVar4;
            } else {
                hVar5.f5528g.c(hVar4).a();
            }
            bVar.f5513f -= j9;
            this.f5513f += j9;
            j8 -= j9;
        }
    }

    @Override // i7.j
    public long S(b bVar, long j8) {
        if (bVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j8 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j8);
        }
        long j9 = this.f5513f;
        if (j9 == 0) {
            return -1L;
        }
        if (j8 > j9) {
            j8 = j9;
        }
        bVar.R(this, j8);
        return j8;
    }

    @Override // i7.c
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public b H(int i8) {
        h hVarO = O(1);
        byte[] bArr = hVarO.f5522a;
        int i9 = hVarO.f5524c;
        hVarO.f5524c = i9 + 1;
        bArr[i9] = (byte) i8;
        this.f5513f++;
        return this;
    }

    public b V(int i8) {
        h hVarO = O(4);
        byte[] bArr = hVarO.f5522a;
        int i9 = hVarO.f5524c;
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((i8 >>> 24) & 255);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i8 >>> 16) & 255);
        int i12 = i11 + 1;
        bArr[i11] = (byte) ((i8 >>> 8) & 255);
        bArr[i12] = (byte) (i8 & 255);
        hVarO.f5524c = i12 + 1;
        this.f5513f += 4;
        return this;
    }

    @Override // i7.c
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public b f0(String str) {
        return f(str, 0, str.length());
    }

    @Override // i7.c
    /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
    public b f(String str, int i8, int i9) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i8);
        }
        if (i9 < i8) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i9 + " < " + i8);
        }
        if (i9 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i9 + " > " + str.length());
        }
        while (i8 < i9) {
            char cCharAt = str.charAt(i8);
            if (cCharAt < 128) {
                h hVarO = O(1);
                byte[] bArr = hVarO.f5522a;
                int i10 = hVarO.f5524c - i8;
                int iMin = Math.min(i9, 8192 - i10);
                int i11 = i8 + 1;
                bArr[i8 + i10] = (byte) cCharAt;
                while (i11 < iMin) {
                    char cCharAt2 = str.charAt(i11);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i11 + i10] = (byte) cCharAt2;
                    i11++;
                }
                int i12 = hVarO.f5524c;
                int i13 = (i10 + i11) - i12;
                hVarO.f5524c = i12 + i13;
                this.f5513f += (long) i13;
                i8 = i11;
            } else {
                if (cCharAt < 2048) {
                    H((cCharAt >> 6) | 192);
                    H((cCharAt & '?') | 128);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    H((cCharAt >> '\f') | 224);
                    H(((cCharAt >> 6) & 63) | 128);
                    H((cCharAt & '?') | 128);
                } else {
                    int i14 = i8 + 1;
                    char cCharAt3 = i14 < i9 ? str.charAt(i14) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        H(63);
                        i8 = i14;
                    } else {
                        int i15 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2;
                        H((i15 >> 18) | 240);
                        H(((i15 >> 12) & 63) | 128);
                        H(((i15 >> 6) & 63) | 128);
                        H((i15 & 63) | 128);
                        i8 += 2;
                    }
                }
                i8++;
            }
        }
        return this;
    }

    @Override // i7.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
    }

    public final void e() {
        try {
            L(this.f5513f);
        } catch (EOFException e8) {
            throw new AssertionError(e8);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        long j8 = this.f5513f;
        if (j8 != bVar.f5513f) {
            return false;
        }
        long j9 = 0;
        if (j8 == 0) {
            return true;
        }
        h hVar = this.f5512e;
        h hVar2 = bVar.f5512e;
        int i8 = hVar.f5523b;
        int i9 = hVar2.f5523b;
        while (j9 < this.f5513f) {
            long jMin = Math.min(hVar.f5524c - i8, hVar2.f5524c - i9);
            int i10 = 0;
            while (i10 < jMin) {
                int i11 = i8 + 1;
                int i12 = i9 + 1;
                if (hVar.f5522a[i8] != hVar2.f5522a[i9]) {
                    return false;
                }
                i10++;
                i8 = i11;
                i9 = i12;
            }
            if (i8 == hVar.f5524c) {
                hVar = hVar.f5527f;
                i8 = hVar.f5523b;
            }
            if (i9 == hVar2.f5524c) {
                hVar2 = hVar2.f5527f;
                i9 = hVar2.f5523b;
            }
            j9 += jMin;
        }
        return true;
    }

    @Override // java.io.Flushable
    public void flush() {
    }

    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public b clone() {
        b bVar = new b();
        if (this.f5513f == 0) {
            return bVar;
        }
        h hVarD = this.f5512e.d();
        bVar.f5512e = hVarD;
        hVarD.f5528g = hVarD;
        hVarD.f5527f = hVarD;
        h hVar = this.f5512e;
        while (true) {
            hVar = hVar.f5527f;
            if (hVar == this.f5512e) {
                bVar.f5513f = this.f5513f;
                return bVar;
            }
            bVar.f5512e.f5528g.c(hVar.d());
        }
    }

    public int hashCode() {
        h hVar = this.f5512e;
        if (hVar == null) {
            return 0;
        }
        int i8 = 1;
        do {
            int i9 = hVar.f5524c;
            for (int i10 = hVar.f5523b; i10 < i9; i10++) {
                i8 = (i8 * 31) + hVar.f5522a[i10];
            }
            hVar = hVar.f5527f;
        } while (hVar != this.f5512e);
        return i8;
    }

    public boolean i() {
        return this.f5513f == 0;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final byte j(long j8) {
        int i8;
        l.b(this.f5513f, j8, 1L);
        long j9 = this.f5513f;
        if (j9 - j8 <= j8) {
            long j10 = j8 - j9;
            h hVar = this.f5512e;
            do {
                hVar = hVar.f5528g;
                int i9 = hVar.f5524c;
                i8 = hVar.f5523b;
                j10 += (long) (i9 - i8);
            } while (j10 < 0);
            return hVar.f5522a[i8 + ((int) j10)];
        }
        h hVar2 = this.f5512e;
        while (true) {
            int i10 = hVar2.f5524c;
            int i11 = hVar2.f5523b;
            long j11 = i10 - i11;
            if (j8 < j11) {
                return hVar2.f5522a[i11 + ((int) j8)];
            }
            j8 -= j11;
            hVar2 = hVar2.f5527f;
        }
    }

    public long m(ByteString byteString, long j8) throws IOException {
        byte[] bArr;
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j9 = 0;
        if (j8 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        h hVar = this.f5512e;
        long j10 = -1;
        if (hVar == null) {
            return -1L;
        }
        long j11 = this.f5513f;
        if (j11 - j8 < j8) {
            while (j11 > j8) {
                hVar = hVar.f5528g;
                j11 -= (long) (hVar.f5524c - hVar.f5523b);
            }
        } else {
            while (true) {
                long j12 = ((long) (hVar.f5524c - hVar.f5523b)) + j9;
                if (j12 >= j8) {
                    break;
                }
                hVar = hVar.f5527f;
                j9 = j12;
            }
            j11 = j9;
        }
        byte b8 = byteString.getByte(0);
        int size = byteString.size();
        long j13 = 1 + (this.f5513f - ((long) size));
        long j14 = j8;
        h hVar2 = hVar;
        long j15 = j11;
        while (j15 < j13) {
            byte[] bArr2 = hVar2.f5522a;
            int iMin = (int) Math.min(hVar2.f5524c, (((long) hVar2.f5523b) + j13) - j15);
            int i8 = (int) ((((long) hVar2.f5523b) + j14) - j15);
            while (i8 < iMin) {
                if (bArr2[i8] == b8) {
                    bArr = bArr2;
                    if (o(hVar2, i8 + 1, byteString, 1, size)) {
                        return ((long) (i8 - hVar2.f5523b)) + j15;
                    }
                } else {
                    bArr = bArr2;
                }
                i8++;
                bArr2 = bArr;
            }
            j15 += (long) (hVar2.f5524c - hVar2.f5523b);
            hVar2 = hVar2.f5527f;
            j14 = j15;
            j10 = -1;
        }
        return j10;
    }

    public long n(ByteString byteString, long j8) {
        int i8;
        int i9;
        long j9 = 0;
        if (j8 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        h hVar = this.f5512e;
        if (hVar == null) {
            return -1L;
        }
        long j10 = this.f5513f;
        if (j10 - j8 < j8) {
            while (j10 > j8) {
                hVar = hVar.f5528g;
                j10 -= (long) (hVar.f5524c - hVar.f5523b);
            }
        } else {
            while (true) {
                long j11 = ((long) (hVar.f5524c - hVar.f5523b)) + j9;
                if (j11 >= j8) {
                    break;
                }
                hVar = hVar.f5527f;
                j9 = j11;
            }
            j10 = j9;
        }
        if (byteString.size() == 2) {
            byte b8 = byteString.getByte(0);
            byte b9 = byteString.getByte(1);
            while (j10 < this.f5513f) {
                byte[] bArr = hVar.f5522a;
                i8 = (int) ((((long) hVar.f5523b) + j8) - j10);
                int i10 = hVar.f5524c;
                while (i8 < i10) {
                    byte b10 = bArr[i8];
                    if (b10 == b8 || b10 == b9) {
                        i9 = hVar.f5523b;
                        return ((long) (i8 - i9)) + j10;
                    }
                    i8++;
                }
                j10 += (long) (hVar.f5524c - hVar.f5523b);
                hVar = hVar.f5527f;
                j8 = j10;
            }
            return -1L;
        }
        byte[] bArrInternalArray = byteString.internalArray();
        while (j10 < this.f5513f) {
            byte[] bArr2 = hVar.f5522a;
            i8 = (int) ((((long) hVar.f5523b) + j8) - j10);
            int i11 = hVar.f5524c;
            while (i8 < i11) {
                byte b11 = bArr2[i8];
                for (byte b12 : bArrInternalArray) {
                    if (b11 == b12) {
                        i9 = hVar.f5523b;
                        return ((long) (i8 - i9)) + j10;
                    }
                }
                i8++;
            }
            j10 += (long) (hVar.f5524c - hVar.f5523b);
            hVar = hVar.f5527f;
            j8 = j10;
        }
        return -1L;
    }

    public final boolean o(h hVar, int i8, ByteString byteString, int i9, int i10) {
        int i11 = hVar.f5524c;
        byte[] bArr = hVar.f5522a;
        while (i9 < i10) {
            if (i8 == i11) {
                hVar = hVar.f5527f;
                byte[] bArr2 = hVar.f5522a;
                bArr = bArr2;
                i8 = hVar.f5523b;
                i11 = hVar.f5524c;
            }
            if (bArr[i8] != byteString.getByte(i9)) {
                return false;
            }
            i8++;
            i9++;
        }
        return true;
    }

    @Override // i7.d
    public boolean p(long j8) {
        return this.f5513f >= j8;
    }

    @Override // i7.d
    public int p0(f fVar) {
        int iF = F(fVar, false);
        if (iF == -1) {
            return -1;
        }
        try {
            L(fVar.f5517d[iF].size());
            return iF;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public byte q() {
        long j8 = this.f5513f;
        if (j8 == 0) {
            throw new IllegalStateException("size == 0");
        }
        h hVar = this.f5512e;
        int i8 = hVar.f5523b;
        int i9 = hVar.f5524c;
        int i10 = i8 + 1;
        byte b8 = hVar.f5522a[i8];
        this.f5513f = j8 - 1;
        if (i10 == i9) {
            this.f5512e = hVar.b();
            i.a(hVar);
        } else {
            hVar.f5523b = i10;
        }
        return b8;
    }

    public byte[] r() {
        try {
            return t(this.f5513f);
        } catch (EOFException e8) {
            throw new AssertionError(e8);
        }
    }

    public int read(byte[] bArr, int i8, int i9) {
        l.b(bArr.length, i8, i9);
        h hVar = this.f5512e;
        if (hVar == null) {
            return -1;
        }
        int iMin = Math.min(i9, hVar.f5524c - hVar.f5523b);
        System.arraycopy(hVar.f5522a, hVar.f5523b, bArr, i8, iMin);
        int i10 = hVar.f5523b + iMin;
        hVar.f5523b = i10;
        this.f5513f -= (long) iMin;
        if (i10 == hVar.f5524c) {
            this.f5512e = hVar.b();
            i.a(hVar);
        }
        return iMin;
    }

    public byte[] t(long j8) throws EOFException {
        l.b(this.f5513f, 0L, j8);
        if (j8 <= 2147483647L) {
            byte[] bArr = new byte[(int) j8];
            w(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j8);
    }

    public String toString() {
        return M().toString();
    }

    public ByteString v() {
        return new ByteString(r());
    }

    public void w(byte[] bArr) throws EOFException {
        int i8 = 0;
        while (i8 < bArr.length) {
            int i9 = read(bArr, i8, bArr.length - i8);
            if (i9 == -1) {
                throw new EOFException();
            }
            i8 += i9;
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i8 = iRemaining;
        while (i8 > 0) {
            h hVarO = O(1);
            int iMin = Math.min(i8, 8192 - hVarO.f5524c);
            byteBuffer.get(hVarO.f5522a, hVarO.f5524c, iMin);
            i8 -= iMin;
            hVarO.f5524c += iMin;
        }
        this.f5513f += (long) iRemaining;
        return iRemaining;
    }

    public int x() {
        long j8 = this.f5513f;
        if (j8 < 4) {
            throw new IllegalStateException("size < 4: " + this.f5513f);
        }
        h hVar = this.f5512e;
        int i8 = hVar.f5523b;
        int i9 = hVar.f5524c;
        if (i9 - i8 < 4) {
            return ((q() & 255) << 24) | ((q() & 255) << 16) | ((q() & 255) << 8) | (q() & 255);
        }
        byte[] bArr = hVar.f5522a;
        int i10 = i8 + 1;
        int i11 = i10 + 1;
        int i12 = ((bArr[i8] & 255) << 24) | ((bArr[i10] & 255) << 16);
        int i13 = i11 + 1;
        int i14 = i12 | ((bArr[i11] & 255) << 8);
        int i15 = i13 + 1;
        int i16 = i14 | (bArr[i13] & 255);
        this.f5513f = j8 - 4;
        if (i15 == i9) {
            this.f5512e = hVar.b();
            i.a(hVar);
        } else {
            hVar.f5523b = i15;
        }
        return i16;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        h hVar = this.f5512e;
        if (hVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), hVar.f5524c - hVar.f5523b);
        byteBuffer.put(hVar.f5522a, hVar.f5523b, iMin);
        int i8 = hVar.f5523b + iMin;
        hVar.f5523b = i8;
        this.f5513f -= (long) iMin;
        if (i8 == hVar.f5524c) {
            this.f5512e = hVar.b();
            i.a(hVar);
        }
        return iMin;
    }
}

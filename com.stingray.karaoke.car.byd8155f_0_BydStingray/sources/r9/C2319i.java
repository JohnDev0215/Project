package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: r9.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2319i implements InterfaceC2321k, InterfaceC2320j, Cloneable, ByteChannel {
    public E l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f21932m;

    @Override // r9.InterfaceC2320j
    public final /* bridge */ /* synthetic */ InterfaceC2320j A(C2322l c2322l) {
        U(c2322l);
        return this;
    }

    @Override // r9.InterfaceC2320j
    public final /* bridge */ /* synthetic */ InterfaceC2320j B(int i7, int i10, byte[] bArr) {
        W(bArr, i7, i10);
        return this;
    }

    public final byte[] C(long j3) throws EOFException {
        if (j3 < 0 || j3 > 2147483647L) {
            throw new IllegalArgumentException(M5.b.f("byteCount: ", j3).toString());
        }
        if (this.f21932m < j3) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j3];
        I(bArr);
        return bArr;
    }

    @Override // r9.InterfaceC2320j
    public final /* bridge */ /* synthetic */ InterfaceC2320j D(String str) {
        c0(str);
        return this;
    }

    @Override // r9.InterfaceC2320j
    public final /* bridge */ /* synthetic */ InterfaceC2320j E(long j3) {
        Y(j3);
        return this;
    }

    public final C2322l F(long j3) throws EOFException {
        if (j3 < 0 || j3 > 2147483647L) {
            throw new IllegalArgumentException(M5.b.f("byteCount: ", j3).toString());
        }
        if (this.f21932m < j3) {
            throw new EOFException();
        }
        if (j3 < 4096) {
            return new C2322l(C(j3));
        }
        C2322l c2322lS = S((int) j3);
        R(j3);
        return c2322lS;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        r0 = new r9.C2319i();
        r0.Y(r9);
        r0.X(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
    
        if (r12 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
    
        r0.z();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0062, code lost:
    
        throw new java.lang.NumberFormatException("Number too large: ".concat(r0.Q()));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long G() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r9.C2319i.G():long");
    }

    @Override // r9.InterfaceC2321k
    public final String H(Charset charset) {
        return P(this.f21932m, charset);
    }

    public final void I(byte[] bArr) throws EOFException {
        A7.m.f(bArr, "sink");
        int i7 = 0;
        while (i7 < bArr.length) {
            int iV = v(bArr, i7, bArr.length - i7);
            if (iV == -1) {
                throw new EOFException();
            }
            i7 += iV;
        }
    }

    @Override // r9.InterfaceC2321k
    public final InputStream J() {
        return new C2318h(this, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a4 A[EDGE_INSN: B:43:0x00a4->B:37:0x00a4 BREAK  A[LOOP:0: B:5:0x0012->B:45:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long K() throws java.io.EOFException {
        /*
            r18 = this;
            r0 = r18
            r1 = 4
            r2 = 48
            r3 = 0
            r4 = 1
            long r5 = r0.f21932m
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 == 0) goto Lab
            r9 = r3
            r10 = r9
            r5 = r7
        L12:
            r9.E r11 = r0.l
            A7.m.c(r11)
            int r12 = r11.f21900b
            int r13 = r11.c
        L1b:
            if (r12 >= r13) goto L90
            byte[] r14 = r11.f21899a
            r14 = r14[r12]
            if (r14 < r2) goto L2a
            r15 = 57
            if (r14 > r15) goto L2a
            int r15 = r14 + (-48)
            goto L3f
        L2a:
            r15 = 97
            if (r14 < r15) goto L35
            r15 = 102(0x66, float:1.43E-43)
            if (r14 > r15) goto L35
            int r15 = r14 + (-87)
            goto L3f
        L35:
            r15 = 65
            if (r14 < r15) goto L68
            r15 = 70
            if (r14 > r15) goto L68
            int r15 = r14 + (-55)
        L3f:
            r16 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r16 = r5 & r16
            int r16 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r16 != 0) goto L4d
            long r5 = r5 << r1
            long r14 = (long) r15
            long r5 = r5 | r14
            int r12 = r12 + r4
            int r9 = r9 + r4
            goto L1b
        L4d:
            r9.i r0 = new r9.i
            r0.<init>()
            r0.Z(r5)
            r0.X(r14)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r0 = r0.Q()
            java.lang.String r2 = "Number too large: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L68:
            if (r9 == 0) goto L6c
            r10 = r4
            goto L90
        L6c:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            char[] r2 = s9.b.f22381a
            int r1 = r14 >> 4
            r1 = r1 & 15
            char r1 = r2[r1]
            r5 = r14 & 15
            char r2 = r2[r5]
            r5 = 2
            char[] r5 = new char[r5]
            r5[r3] = r1
            r5[r4] = r2
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.String r1 = r2.concat(r1)
            r0.<init>(r1)
            throw r0
        L90:
            if (r12 != r13) goto L9c
            r9.E r12 = r11.a()
            r0.l = r12
            r9.F.a(r11)
            goto L9e
        L9c:
            r11.f21900b = r12
        L9e:
            if (r10 != 0) goto La4
            r9.E r11 = r0.l
            if (r11 != 0) goto L12
        La4:
            long r1 = r0.f21932m
            long r3 = (long) r9
            long r1 = r1 - r3
            r0.f21932m = r1
            return r5
        Lab:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: r9.C2319i.K():long");
    }

    public final int L() throws EOFException {
        if (this.f21932m < 4) {
            throw new EOFException();
        }
        E e10 = this.l;
        A7.m.c(e10);
        int i7 = e10.f21900b;
        int i10 = e10.c;
        if (i10 - i7 < 4) {
            return (z() & 255) | ((z() & 255) << 24) | ((z() & 255) << 16) | ((z() & 255) << 8);
        }
        byte[] bArr = e10.f21899a;
        int i11 = i7 + 3;
        int i12 = ((bArr[i7 + 1] & 255) << 16) | ((bArr[i7] & 255) << 24) | ((bArr[i7 + 2] & 255) << 8);
        int i13 = i7 + 4;
        int i14 = i12 | (bArr[i11] & 255);
        this.f21932m -= 4;
        if (i13 == i10) {
            this.l = e10.a();
            F.a(e10);
        } else {
            e10.f21900b = i13;
        }
        return i14;
    }

    public final long M() throws EOFException {
        if (this.f21932m < 8) {
            throw new EOFException();
        }
        E e10 = this.l;
        A7.m.c(e10);
        int i7 = e10.f21900b;
        int i10 = e10.c;
        if (i10 - i7 < 8) {
            return ((((long) L()) & 4294967295L) << 32) | (4294967295L & ((long) L()));
        }
        byte[] bArr = e10.f21899a;
        int i11 = i7 + 7;
        long j3 = ((((long) bArr[i7 + 3]) & 255) << 32) | ((((long) bArr[i7]) & 255) << 56) | ((((long) bArr[i7 + 1]) & 255) << 48) | ((((long) bArr[i7 + 2]) & 255) << 40) | ((((long) bArr[i7 + 4]) & 255) << 24) | ((((long) bArr[i7 + 5]) & 255) << 16) | ((((long) bArr[i7 + 6]) & 255) << 8);
        int i12 = i7 + 8;
        long j10 = j3 | (((long) bArr[i11]) & 255);
        this.f21932m -= 8;
        if (i12 == i10) {
            this.l = e10.a();
            F.a(e10);
        } else {
            e10.f21900b = i12;
        }
        return j10;
    }

    public final short N() throws EOFException {
        if (this.f21932m < 2) {
            throw new EOFException();
        }
        E e10 = this.l;
        A7.m.c(e10);
        int i7 = e10.f21900b;
        int i10 = e10.c;
        if (i10 - i7 < 2) {
            return (short) ((z() & 255) | ((z() & 255) << 8));
        }
        int i11 = i7 + 1;
        byte[] bArr = e10.f21899a;
        int i12 = (bArr[i7] & 255) << 8;
        int i13 = i7 + 2;
        int i14 = (bArr[i11] & 255) | i12;
        this.f21932m -= 2;
        if (i13 == i10) {
            this.l = e10.a();
            F.a(e10);
        } else {
            e10.f21900b = i13;
        }
        return (short) i14;
    }

    public final short O() throws EOFException {
        short sN = N();
        return (short) (((sN & 255) << 8) | ((65280 & sN) >>> 8));
    }

    public final String P(long j3, Charset charset) {
        A7.m.f(charset, "charset");
        if (j3 < 0 || j3 > 2147483647L) {
            throw new IllegalArgumentException(M5.b.f("byteCount: ", j3).toString());
        }
        if (this.f21932m < j3) {
            throw new EOFException();
        }
        if (j3 == 0) {
            return "";
        }
        E e10 = this.l;
        A7.m.c(e10);
        int i7 = e10.f21900b;
        if (((long) i7) + j3 > e10.c) {
            return new String(C(j3), charset);
        }
        int i10 = (int) j3;
        String str = new String(e10.f21899a, i7, i10, charset);
        int i11 = e10.f21900b + i10;
        e10.f21900b = i11;
        this.f21932m -= j3;
        if (i11 == e10.c) {
            this.l = e10.a();
            F.a(e10);
        }
        return str;
    }

    public final String Q() {
        return P(this.f21932m, Q8.a.f7211a);
    }

    public final void R(long j3) {
        while (j3 > 0) {
            E e10 = this.l;
            if (e10 == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j3, e10.c - e10.f21900b);
            long j10 = iMin;
            this.f21932m -= j10;
            j3 -= j10;
            int i7 = e10.f21900b + iMin;
            e10.f21900b = i7;
            if (i7 == e10.c) {
                this.l = e10.a();
                F.a(e10);
            }
        }
    }

    public final C2322l S(int i7) {
        if (i7 == 0) {
            return C2322l.o;
        }
        AbstractC2312b.e(this.f21932m, 0L, i7);
        E e10 = this.l;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i7) {
            A7.m.c(e10);
            int i13 = e10.c;
            int i14 = e10.f21900b;
            if (i13 == i14) {
                throw new AssertionError("s.limit == s.pos");
            }
            i11 += i13 - i14;
            i12++;
            e10 = e10.f21902f;
        }
        byte[][] bArr = new byte[i12][];
        int[] iArr = new int[i12 * 2];
        E e11 = this.l;
        int i15 = 0;
        while (i10 < i7) {
            A7.m.c(e11);
            bArr[i15] = e11.f21899a;
            i10 += e11.c - e11.f21900b;
            iArr[i15] = Math.min(i10, i7);
            iArr[i15 + i12] = e11.f21900b;
            e11.d = true;
            i15++;
            e11 = e11.f21902f;
        }
        return new G(bArr, iArr);
    }

    public final E T(int i7) {
        if (i7 < 1 || i7 > 8192) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        E e10 = this.l;
        if (e10 == null) {
            E eB = F.b();
            this.l = eB;
            eB.f21903g = eB;
            eB.f21902f = eB;
            return eB;
        }
        E e11 = e10.f21903g;
        A7.m.c(e11);
        if (e11.c + i7 <= 8192 && e11.f21901e) {
            return e11;
        }
        E eB2 = F.b();
        e11.b(eB2);
        return eB2;
    }

    public final void U(C2322l c2322l) {
        A7.m.f(c2322l, "byteString");
        c2322l.s(this, c2322l.d());
    }

    public final void V(byte[] bArr) {
        A7.m.f(bArr, BYDAutoMultimediaDevice.PARAM_SOURCE);
        W(bArr, 0, bArr.length);
    }

    public final void W(byte[] bArr, int i7, int i10) {
        A7.m.f(bArr, BYDAutoMultimediaDevice.PARAM_SOURCE);
        long j3 = i10;
        AbstractC2312b.e(bArr.length, i7, j3);
        int i11 = i10 + i7;
        while (i7 < i11) {
            E eT = T(1);
            int iMin = Math.min(i11 - i7, 8192 - eT.c);
            int i12 = i7 + iMin;
            AbstractC1996k.B(eT.c, i7, i12, bArr, eT.f21899a);
            eT.c += iMin;
            i7 = i12;
        }
        this.f21932m += j3;
    }

    public final void X(int i7) {
        E eT = T(1);
        int i10 = eT.c;
        eT.c = i10 + 1;
        eT.f21899a[i10] = (byte) i7;
        this.f21932m++;
    }

    public final void Y(long j3) {
        boolean z6;
        byte[] bArr;
        if (j3 == 0) {
            X(48);
            return;
        }
        int i7 = 1;
        if (j3 < 0) {
            j3 = -j3;
            if (j3 < 0) {
                c0("-9223372036854775808");
                return;
            }
            z6 = true;
        } else {
            z6 = false;
        }
        if (j3 >= 100000000) {
            i7 = j3 < 1000000000000L ? j3 < 10000000000L ? j3 < 1000000000 ? 9 : 10 : j3 < 100000000000L ? 11 : 12 : j3 < 1000000000000000L ? j3 < 10000000000000L ? 13 : j3 < 100000000000000L ? 14 : 15 : j3 < 100000000000000000L ? j3 < 10000000000000000L ? 16 : 17 : j3 < 1000000000000000000L ? 18 : 19;
        } else if (j3 >= 10000) {
            i7 = j3 < 1000000 ? j3 < 100000 ? 5 : 6 : j3 < 10000000 ? 7 : 8;
        } else if (j3 >= 100) {
            i7 = j3 < 1000 ? 3 : 4;
        } else if (j3 >= 10) {
            i7 = 2;
        }
        if (z6) {
            i7++;
        }
        E eT = T(i7);
        int i10 = eT.c + i7;
        while (true) {
            bArr = eT.f21899a;
            if (j3 == 0) {
                break;
            }
            long j10 = 10;
            i10--;
            bArr[i10] = s9.a.f22380a[(int) (j3 % j10)];
            j3 /= j10;
        }
        if (z6) {
            bArr[i10 - 1] = 45;
        }
        eT.c += i7;
        this.f21932m += (long) i7;
    }

    public final void Z(long j3) {
        if (j3 == 0) {
            X(48);
            return;
        }
        long j10 = (j3 >>> 1) | j3;
        long j11 = j10 | (j10 >>> 2);
        long j12 = j11 | (j11 >>> 4);
        long j13 = j12 | (j12 >>> 8);
        long j14 = j13 | (j13 >>> 16);
        long j15 = j14 | (j14 >>> 32);
        long j16 = j15 - ((j15 >>> 1) & 6148914691236517205L);
        long j17 = ((j16 >>> 2) & 3689348814741910323L) + (j16 & 3689348814741910323L);
        long j18 = ((j17 >>> 4) + j17) & 1085102592571150095L;
        long j19 = j18 + (j18 >>> 8);
        long j20 = j19 + (j19 >>> 16);
        int i7 = (int) ((((j20 & 63) + ((j20 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        E eT = T(i7);
        int i10 = eT.c;
        for (int i11 = (i10 + i7) - 1; i11 >= i10; i11--) {
            eT.f21899a[i11] = s9.a.f22380a[(int) (15 & j3)];
            j3 >>>= 4;
        }
        eT.c += i7;
        this.f21932m += (long) i7;
    }

    public final void a0(int i7) {
        E eT = T(4);
        int i10 = eT.c;
        byte[] bArr = eT.f21899a;
        bArr[i10] = (byte) ((i7 >>> 24) & 255);
        bArr[i10 + 1] = (byte) ((i7 >>> 16) & 255);
        bArr[i10 + 2] = (byte) ((i7 >>> 8) & 255);
        bArr[i10 + 3] = (byte) (i7 & 255);
        eT.c = i10 + 4;
        this.f21932m += 4;
    }

    public final void b() {
        R(this.f21932m);
    }

    public final void b0(int i7) {
        E eT = T(2);
        int i10 = eT.c;
        byte[] bArr = eT.f21899a;
        bArr[i10] = (byte) ((i7 >>> 8) & 255);
        bArr[i10 + 1] = (byte) (i7 & 255);
        eT.c = i10 + 2;
        this.f21932m += 2;
    }

    @Override // r9.InterfaceC2321k
    public final C2319i c() {
        return this;
    }

    public final void c0(String str) {
        A7.m.f(str, "string");
        d0(str, 0, str.length());
    }

    public final Object clone() {
        C2319i c2319i = new C2319i();
        if (this.f21932m != 0) {
            E e10 = this.l;
            A7.m.c(e10);
            E eC = e10.c();
            c2319i.l = eC;
            eC.f21903g = eC;
            eC.f21902f = eC;
            for (E e11 = e10.f21902f; e11 != e10; e11 = e11.f21902f) {
                E e12 = eC.f21903g;
                A7.m.c(e12);
                A7.m.c(e11);
                e12.b(e11.c());
            }
            c2319i.f21932m = this.f21932m;
        }
        return c2319i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, r9.H
    public final void close() {
    }

    public final void d0(String str, int i7, int i10) {
        char cCharAt;
        A7.m.f(str, "string");
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "beginIndex < 0: ").toString());
        }
        if (i10 < i7) {
            throw new IllegalArgumentException(A7.k.i("endIndex < beginIndex: ", " < ", i10, i7).toString());
        }
        if (i10 > str.length()) {
            StringBuilder sbJ = M5.b.j("endIndex > string.length: ", i10, " > ");
            sbJ.append(str.length());
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        while (i7 < i10) {
            char cCharAt2 = str.charAt(i7);
            if (cCharAt2 < 128) {
                E eT = T(1);
                int i11 = eT.c - i7;
                int iMin = Math.min(i10, 8192 - i11);
                int i12 = i7 + 1;
                byte[] bArr = eT.f21899a;
                bArr[i7 + i11] = (byte) cCharAt2;
                while (true) {
                    i7 = i12;
                    if (i7 >= iMin || (cCharAt = str.charAt(i7)) >= 128) {
                        break;
                    }
                    i12 = i7 + 1;
                    bArr[i7 + i11] = (byte) cCharAt;
                }
                int i13 = eT.c;
                int i14 = (i11 + i7) - i13;
                eT.c = i13 + i14;
                this.f21932m += (long) i14;
            } else {
                if (cCharAt2 < 2048) {
                    E eT2 = T(2);
                    int i15 = eT2.c;
                    byte[] bArr2 = eT2.f21899a;
                    bArr2[i15] = (byte) ((cCharAt2 >> 6) | 192);
                    bArr2[i15 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    eT2.c = i15 + 2;
                    this.f21932m += 2;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    E eT3 = T(3);
                    int i16 = eT3.c;
                    byte[] bArr3 = eT3.f21899a;
                    bArr3[i16] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i16 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i16 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    eT3.c = i16 + 3;
                    this.f21932m += 3;
                } else {
                    int i17 = i7 + 1;
                    char cCharAt3 = i17 < i10 ? str.charAt(i17) : (char) 0;
                    if (cCharAt2 > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        X(63);
                        i7 = i17;
                    } else {
                        int i18 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        E eT4 = T(4);
                        int i19 = eT4.c;
                        byte[] bArr4 = eT4.f21899a;
                        bArr4[i19] = (byte) ((i18 >> 18) | 240);
                        bArr4[i19 + 1] = (byte) (((i18 >> 12) & 63) | 128);
                        bArr4[i19 + 2] = (byte) (((i18 >> 6) & 63) | 128);
                        bArr4[i19 + 3] = (byte) ((i18 & 63) | 128);
                        eT4.c = i19 + 4;
                        this.f21932m += 4;
                        i7 += 2;
                    }
                }
                i7++;
            }
        }
    }

    @Override // r9.J
    public final L e() {
        return L.d;
    }

    public final void e0(int i7) {
        if (i7 < 128) {
            X(i7);
            return;
        }
        if (i7 < 2048) {
            E eT = T(2);
            int i10 = eT.c;
            byte[] bArr = eT.f21899a;
            bArr[i10] = (byte) ((i7 >> 6) | 192);
            bArr[i10 + 1] = (byte) ((i7 & 63) | 128);
            eT.c = i10 + 2;
            this.f21932m += 2;
            return;
        }
        if (55296 <= i7 && i7 < 57344) {
            X(63);
            return;
        }
        if (i7 < 65536) {
            E eT2 = T(3);
            int i11 = eT2.c;
            byte[] bArr2 = eT2.f21899a;
            bArr2[i11] = (byte) ((i7 >> 12) | 224);
            bArr2[i11 + 1] = (byte) (((i7 >> 6) & 63) | 128);
            bArr2[i11 + 2] = (byte) ((i7 & 63) | 128);
            eT2.c = i11 + 3;
            this.f21932m += 3;
            return;
        }
        if (i7 > 1114111) {
            throw new IllegalArgumentException("Unexpected code point: 0x".concat(AbstractC2312b.l(i7)));
        }
        E eT3 = T(4);
        int i12 = eT3.c;
        byte[] bArr3 = eT3.f21899a;
        bArr3[i12] = (byte) ((i7 >> 18) | 240);
        bArr3[i12 + 1] = (byte) (((i7 >> 12) & 63) | 128);
        bArr3[i12 + 2] = (byte) (((i7 >> 6) & 63) | 128);
        bArr3[i12 + 3] = (byte) ((i7 & 63) | 128);
        eT3.c = i12 + 4;
        this.f21932m += 4;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof C2319i) {
                long j3 = this.f21932m;
                C2319i c2319i = (C2319i) obj;
                if (j3 == c2319i.f21932m) {
                    if (j3 != 0) {
                        E e10 = this.l;
                        A7.m.c(e10);
                        E e11 = c2319i.l;
                        A7.m.c(e11);
                        int i7 = e10.f21900b;
                        int i10 = e11.f21900b;
                        long j10 = 0;
                        while (j10 < this.f21932m) {
                            long jMin = Math.min(e10.c - i7, e11.c - i10);
                            long j11 = 0;
                            while (j11 < jMin) {
                                int i11 = i7 + 1;
                                byte b10 = e10.f21899a[i7];
                                int i12 = i10 + 1;
                                if (b10 == e11.f21899a[i10]) {
                                    j11++;
                                    i10 = i12;
                                    i7 = i11;
                                }
                            }
                            if (i7 == e10.c) {
                                E e12 = e10.f21902f;
                                A7.m.c(e12);
                                i7 = e12.f21900b;
                                e10 = e12;
                            }
                            if (i10 == e11.c) {
                                e11 = e11.f21902f;
                                A7.m.c(e11);
                                i10 = e11.f21900b;
                            }
                            j10 += jMin;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() {
    }

    public final long g() {
        long j3 = this.f21932m;
        if (j3 == 0) {
            return 0L;
        }
        E e10 = this.l;
        A7.m.c(e10);
        E e11 = e10.f21903g;
        A7.m.c(e11);
        int i7 = e11.c;
        if (i7 < 8192 && e11.f21901e) {
            j3 -= (long) (i7 - e11.f21900b);
        }
        return j3;
    }

    public final int hashCode() {
        E e10 = this.l;
        if (e10 == null) {
            return 0;
        }
        int i7 = 1;
        do {
            int i10 = e10.c;
            for (int i11 = e10.f21900b; i11 < i10; i11++) {
                i7 = (i7 * 31) + e10.f21899a[i11];
            }
            e10 = e10.f21902f;
            A7.m.c(e10);
        } while (e10 != this.l);
        return i7;
    }

    public final void i(C2319i c2319i, long j3, long j10) {
        A7.m.f(c2319i, "out");
        AbstractC2312b.e(this.f21932m, j3, j10);
        if (j10 == 0) {
            return;
        }
        c2319i.f21932m += j10;
        E e10 = this.l;
        while (true) {
            A7.m.c(e10);
            long j11 = e10.c - e10.f21900b;
            if (j3 < j11) {
                break;
            }
            j3 -= j11;
            e10 = e10.f21902f;
        }
        while (j10 > 0) {
            A7.m.c(e10);
            E eC = e10.c();
            int i7 = eC.f21900b + ((int) j3);
            eC.f21900b = i7;
            eC.c = Math.min(i7 + ((int) j10), eC.c);
            E e11 = c2319i.l;
            if (e11 == null) {
                eC.f21903g = eC;
                eC.f21902f = eC;
                c2319i.l = eC;
            } else {
                E e12 = e11.f21903g;
                A7.m.c(e12);
                e12.b(eC);
            }
            j10 -= (long) (eC.c - eC.f21900b);
            e10 = e10.f21902f;
            j3 = 0;
        }
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    public final boolean j() {
        return this.f21932m == 0;
    }

    public final byte k(long j3) {
        AbstractC2312b.e(this.f21932m, j3, 1L);
        E e10 = this.l;
        if (e10 == null) {
            A7.m.c(null);
            throw null;
        }
        long j10 = this.f21932m;
        if (j10 - j3 < j3) {
            while (j10 > j3) {
                e10 = e10.f21903g;
                A7.m.c(e10);
                j10 -= (long) (e10.c - e10.f21900b);
            }
            return e10.f21899a[(int) ((((long) e10.f21900b) + j3) - j10)];
        }
        long j11 = 0;
        while (true) {
            int i7 = e10.c;
            int i10 = e10.f21900b;
            long j12 = ((long) (i7 - i10)) + j11;
            if (j12 > j3) {
                return e10.f21899a[(int) ((((long) i10) + j3) - j11)];
            }
            e10 = e10.f21902f;
            A7.m.c(e10);
            j11 = j12;
        }
    }

    @Override // r9.InterfaceC2321k
    public final boolean l(long j3) {
        return this.f21932m >= j3;
    }

    public final long m(long j3, C2322l c2322l) {
        int i7;
        int i10;
        int i11;
        int i12;
        A7.m.f(c2322l, "targetBytes");
        long j10 = 0;
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("fromIndex < 0: ", j3).toString());
        }
        E e10 = this.l;
        if (e10 == null) {
            return -1L;
        }
        long j11 = this.f21932m;
        if (j11 - j3 < j3) {
            while (j11 > j3) {
                e10 = e10.f21903g;
                A7.m.c(e10);
                j11 -= (long) (e10.c - e10.f21900b);
            }
            if (c2322l.d() == 2) {
                byte bI = c2322l.i(0);
                byte bI2 = c2322l.i(1);
                while (j11 < this.f21932m) {
                    i11 = (int) ((((long) e10.f21900b) + j3) - j11);
                    int i13 = e10.c;
                    while (i11 < i13) {
                        byte b10 = e10.f21899a[i11];
                        if (b10 == bI || b10 == bI2) {
                            i12 = e10.f21900b;
                        } else {
                            i11++;
                        }
                    }
                    j11 += (long) (e10.c - e10.f21900b);
                    e10 = e10.f21902f;
                    A7.m.c(e10);
                    j3 = j11;
                }
                return -1L;
            }
            byte[] bArrH = c2322l.h();
            while (j11 < this.f21932m) {
                i11 = (int) ((((long) e10.f21900b) + j3) - j11);
                int i14 = e10.c;
                while (i11 < i14) {
                    byte b11 = e10.f21899a[i11];
                    for (byte b12 : bArrH) {
                        if (b11 == b12) {
                            i12 = e10.f21900b;
                        }
                    }
                    i11++;
                }
                j11 += (long) (e10.c - e10.f21900b);
                e10 = e10.f21902f;
                A7.m.c(e10);
                j3 = j11;
            }
            return -1L;
            return ((long) (i11 - i12)) + j11;
        }
        while (true) {
            long j12 = ((long) (e10.c - e10.f21900b)) + j10;
            if (j12 > j3) {
                break;
            }
            e10 = e10.f21902f;
            A7.m.c(e10);
            j10 = j12;
        }
        if (c2322l.d() == 2) {
            byte bI3 = c2322l.i(0);
            byte bI4 = c2322l.i(1);
            while (j10 < this.f21932m) {
                i7 = (int) ((((long) e10.f21900b) + j3) - j10);
                int i15 = e10.c;
                while (i7 < i15) {
                    byte b13 = e10.f21899a[i7];
                    if (b13 == bI3 || b13 == bI4) {
                        i10 = e10.f21900b;
                    } else {
                        i7++;
                    }
                }
                j10 += (long) (e10.c - e10.f21900b);
                e10 = e10.f21902f;
                A7.m.c(e10);
                j3 = j10;
            }
            return -1L;
        }
        byte[] bArrH2 = c2322l.h();
        while (j10 < this.f21932m) {
            i7 = (int) ((((long) e10.f21900b) + j3) - j10);
            int i16 = e10.c;
            while (i7 < i16) {
                byte b14 = e10.f21899a[i7];
                for (byte b15 : bArrH2) {
                    if (b14 == b15) {
                        i10 = e10.f21900b;
                    }
                }
                i7++;
            }
            j10 += (long) (e10.c - e10.f21900b);
            e10 = e10.f21902f;
            A7.m.c(e10);
            j3 = j10;
        }
        return -1L;
        return ((long) (i7 - i10)) + j10;
    }

    public final long o(C2322l c2322l) {
        A7.m.f(c2322l, "targetBytes");
        return m(0L, c2322l);
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) {
        A7.m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        long j10 = this.f21932m;
        if (j10 == 0) {
            return -1L;
        }
        if (j3 > j10) {
            j3 = j10;
        }
        c2319i.t(this, j3);
        return j3;
    }

    public final boolean q(long j3, C2322l c2322l) {
        A7.m.f(c2322l, "bytes");
        byte[] bArr = c2322l.l;
        int length = bArr.length;
        if (j3 < 0 || length < 0 || this.f21932m - j3 < length || bArr.length < length) {
            return false;
        }
        for (int i7 = 0; i7 < length; i7++) {
            if (k(((long) i7) + j3) != bArr[i7]) {
                return false;
            }
        }
        return true;
    }

    @Override // r9.InterfaceC2320j
    public final long r(J j3) {
        A7.m.f(j3, BYDAutoMultimediaDevice.PARAM_SOURCE);
        long j10 = 0;
        while (true) {
            long jP = j3.p(this, 8192L);
            if (jP == -1) {
                return j10;
            }
            j10 += jP;
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        A7.m.f(byteBuffer, "sink");
        E e10 = this.l;
        if (e10 == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), e10.c - e10.f21900b);
        byteBuffer.put(e10.f21899a, e10.f21900b, iMin);
        int i7 = e10.f21900b + iMin;
        e10.f21900b = i7;
        this.f21932m -= (long) iMin;
        if (i7 == e10.c) {
            this.l = e10.a();
            F.a(e10);
        }
        return iMin;
    }

    @Override // r9.InterfaceC2320j
    public final /* bridge */ /* synthetic */ InterfaceC2320j s(int i7) {
        X(i7);
        return this;
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) {
        E eB;
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (c2319i == this) {
            throw new IllegalArgumentException("source == this");
        }
        AbstractC2312b.e(c2319i.f21932m, 0L, j3);
        while (j3 > 0) {
            E e10 = c2319i.l;
            A7.m.c(e10);
            int i7 = e10.c;
            E e11 = c2319i.l;
            A7.m.c(e11);
            long j10 = i7 - e11.f21900b;
            int i10 = 0;
            if (j3 < j10) {
                E e12 = this.l;
                E e13 = e12 != null ? e12.f21903g : null;
                if (e13 != null && e13.f21901e) {
                    if ((((long) e13.c) + j3) - ((long) (e13.d ? 0 : e13.f21900b)) <= 8192) {
                        E e14 = c2319i.l;
                        A7.m.c(e14);
                        e14.d(e13, (int) j3);
                        c2319i.f21932m -= j3;
                        this.f21932m += j3;
                        return;
                    }
                }
                E e15 = c2319i.l;
                A7.m.c(e15);
                int i11 = (int) j3;
                if (i11 <= 0 || i11 > e15.c - e15.f21900b) {
                    throw new IllegalArgumentException("byteCount out of range");
                }
                if (i11 >= 1024) {
                    eB = e15.c();
                } else {
                    eB = F.b();
                    int i12 = e15.f21900b;
                    AbstractC1996k.B(0, i12, i12 + i11, e15.f21899a, eB.f21899a);
                }
                eB.c = eB.f21900b + i11;
                e15.f21900b += i11;
                E e16 = e15.f21903g;
                A7.m.c(e16);
                e16.b(eB);
                c2319i.l = eB;
            }
            E e17 = c2319i.l;
            A7.m.c(e17);
            long j11 = e17.c - e17.f21900b;
            c2319i.l = e17.a();
            E e18 = this.l;
            if (e18 == null) {
                this.l = e17;
                e17.f21903g = e17;
                e17.f21902f = e17;
            } else {
                E e19 = e18.f21903g;
                A7.m.c(e19);
                e19.b(e17);
                E e20 = e17.f21903g;
                if (e20 == e17) {
                    throw new IllegalStateException("cannot compact");
                }
                A7.m.c(e20);
                if (e20.f21901e) {
                    int i13 = e17.c - e17.f21900b;
                    E e21 = e17.f21903g;
                    A7.m.c(e21);
                    int i14 = 8192 - e21.c;
                    E e22 = e17.f21903g;
                    A7.m.c(e22);
                    if (!e22.d) {
                        E e23 = e17.f21903g;
                        A7.m.c(e23);
                        i10 = e23.f21900b;
                    }
                    if (i13 <= i14 + i10) {
                        E e24 = e17.f21903g;
                        A7.m.c(e24);
                        e17.d(e24, i13);
                        e17.a();
                        F.a(e17);
                    }
                }
            }
            c2319i.f21932m -= j11;
            this.f21932m += j11;
            j3 -= j11;
        }
    }

    public final String toString() {
        long j3 = this.f21932m;
        if (j3 <= 2147483647L) {
            return S((int) j3).toString();
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.f21932m).toString());
    }

    @Override // r9.InterfaceC2320j
    public final /* bridge */ /* synthetic */ InterfaceC2320j u(byte[] bArr) {
        V(bArr);
        return this;
    }

    public final int v(byte[] bArr, int i7, int i10) {
        A7.m.f(bArr, "sink");
        AbstractC2312b.e(bArr.length, i7, i10);
        E e10 = this.l;
        if (e10 == null) {
            return -1;
        }
        int iMin = Math.min(i10, e10.c - e10.f21900b);
        int i11 = e10.f21900b;
        AbstractC1996k.B(i7, i11, i11 + iMin, e10.f21899a, bArr);
        int i12 = e10.f21900b + iMin;
        e10.f21900b = i12;
        this.f21932m -= (long) iMin;
        if (i12 == e10.c) {
            this.l = e10.a();
            F.a(e10);
        }
        return iMin;
    }

    @Override // r9.InterfaceC2321k
    public final long w(C c) {
        long j3 = this.f21932m;
        if (j3 > 0) {
            c.t(this, j3);
        }
        return j3;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        A7.m.f(byteBuffer, BYDAutoMultimediaDevice.PARAM_SOURCE);
        int iRemaining = byteBuffer.remaining();
        int i7 = iRemaining;
        while (i7 > 0) {
            E eT = T(1);
            int iMin = Math.min(i7, 8192 - eT.c);
            byteBuffer.get(eT.f21899a, eT.c, iMin);
            i7 -= iMin;
            eT.c += iMin;
        }
        this.f21932m += (long) iRemaining;
        return iRemaining;
    }

    @Override // r9.InterfaceC2321k
    public final int x(y yVar) {
        A7.m.f(yVar, "options");
        int iB = s9.a.b(this, yVar, false);
        if (iB == -1) {
            return -1;
        }
        R(yVar.l[iB].d());
        return iB;
    }

    public final C2317g y(C2317g c2317g) {
        A7.m.f(c2317g, "unsafeCursor");
        byte[] bArr = s9.a.f22380a;
        if (c2317g == AbstractC2312b.f21914a) {
            c2317g = new C2317g();
        }
        if (c2317g.l != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        c2317g.l = this;
        c2317g.f21926m = true;
        return c2317g;
    }

    public final byte z() {
        if (this.f21932m == 0) {
            throw new EOFException();
        }
        E e10 = this.l;
        A7.m.c(e10);
        int i7 = e10.f21900b;
        int i10 = e10.c;
        int i11 = i7 + 1;
        byte b10 = e10.f21899a[i7];
        this.f21932m--;
        if (i11 == i10) {
            this.l = e10.a();
            F.a(e10);
        } else {
            e10.f21900b = i11;
        }
        return b10;
    }
}

package p6;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.util.Objects;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e implements g, f, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public v f8866e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f8867g;

    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public e clone() {
        return I();
    }

    @Override // p6.g
    public String D() {
        return p(Long.MAX_VALUE);
    }

    @Override // p6.g
    public void F(long j7) throws EOFException {
        if (this.f8867g < j7) {
            throw new EOFException();
        }
    }

    public final long G() {
        long jJ0 = j0();
        if (jJ0 == 0) {
            return 0L;
        }
        v vVar = this.f8866e;
        kotlin.jvm.internal.l.c(vVar);
        v vVar2 = vVar.f8908g;
        kotlin.jvm.internal.l.c(vVar2);
        int i7 = vVar2.f8904c;
        if (i7 < 8192 && vVar2.f8906e) {
            jJ0 -= (long) (i7 - vVar2.f8903b);
        }
        return jJ0;
    }

    public final e I() {
        e eVar = new e();
        if (j0() != 0) {
            v vVar = this.f8866e;
            kotlin.jvm.internal.l.c(vVar);
            v vVarD = vVar.d();
            eVar.f8866e = vVarD;
            vVarD.f8908g = vVarD;
            vVarD.f8907f = vVarD;
            for (v vVar2 = vVar.f8907f; vVar2 != vVar; vVar2 = vVar2.f8907f) {
                v vVar3 = vVarD.f8908g;
                kotlin.jvm.internal.l.c(vVar3);
                kotlin.jvm.internal.l.c(vVar2);
                vVar3.c(vVar2.d());
            }
            eVar.i0(j0());
        }
        return eVar;
    }

    @Override // p6.g
    public boolean J() {
        return this.f8867g == 0;
    }

    public final e K(e out, long j7, long j8) {
        kotlin.jvm.internal.l.e(out, "out");
        c.b(j0(), j7, j8);
        if (j8 != 0) {
            out.i0(out.j0() + j8);
            v vVar = this.f8866e;
            while (true) {
                kotlin.jvm.internal.l.c(vVar);
                int i7 = vVar.f8904c;
                int i8 = vVar.f8903b;
                if (j7 < i7 - i8) {
                    break;
                }
                j7 -= (long) (i7 - i8);
                vVar = vVar.f8907f;
            }
            while (j8 > 0) {
                kotlin.jvm.internal.l.c(vVar);
                v vVarD = vVar.d();
                int i9 = vVarD.f8903b + ((int) j7);
                vVarD.f8903b = i9;
                vVarD.f8904c = Math.min(i9 + ((int) j8), vVarD.f8904c);
                v vVar2 = out.f8866e;
                if (vVar2 == null) {
                    vVarD.f8908g = vVarD;
                    vVarD.f8907f = vVarD;
                    out.f8866e = vVarD;
                } else {
                    kotlin.jvm.internal.l.c(vVar2);
                    v vVar3 = vVar2.f8908g;
                    kotlin.jvm.internal.l.c(vVar3);
                    vVar3.c(vVarD);
                }
                j8 -= (long) (vVarD.f8904c - vVarD.f8903b);
                vVar = vVar.f8907f;
                j7 = 0;
            }
        }
        return this;
    }

    public final byte N(long j7) {
        c.b(j0(), j7, 1L);
        v vVar = this.f8866e;
        if (vVar == null) {
            kotlin.jvm.internal.l.c(null);
            throw null;
        }
        if (j0() - j7 < j7) {
            long jJ0 = j0();
            while (jJ0 > j7) {
                vVar = vVar.f8908g;
                kotlin.jvm.internal.l.c(vVar);
                jJ0 -= (long) (vVar.f8904c - vVar.f8903b);
            }
            kotlin.jvm.internal.l.c(vVar);
            return vVar.f8902a[(int) ((((long) vVar.f8903b) + j7) - jJ0)];
        }
        long j8 = 0;
        while (true) {
            long j9 = ((long) (vVar.f8904c - vVar.f8903b)) + j8;
            if (j9 > j7) {
                kotlin.jvm.internal.l.c(vVar);
                return vVar.f8902a[(int) ((((long) vVar.f8903b) + j7) - j8)];
            }
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
            j8 = j9;
        }
    }

    public long P(byte b7, long j7, long j8) {
        v vVar;
        int i7;
        long jJ0 = 0;
        if (!(0 <= j7 && j8 >= j7)) {
            throw new IllegalArgumentException(("size=" + j0() + " fromIndex=" + j7 + " toIndex=" + j8).toString());
        }
        if (j8 > j0()) {
            j8 = j0();
        }
        if (j7 == j8 || (vVar = this.f8866e) == null) {
            return -1L;
        }
        if (j0() - j7 < j7) {
            jJ0 = j0();
            while (jJ0 > j7) {
                vVar = vVar.f8908g;
                kotlin.jvm.internal.l.c(vVar);
                jJ0 -= (long) (vVar.f8904c - vVar.f8903b);
            }
            while (jJ0 < j8) {
                byte[] bArr = vVar.f8902a;
                int iMin = (int) Math.min(vVar.f8904c, (((long) vVar.f8903b) + j8) - jJ0);
                i7 = (int) ((((long) vVar.f8903b) + j7) - jJ0);
                while (i7 < iMin) {
                    if (bArr[i7] != b7) {
                        i7++;
                    }
                }
                jJ0 += (long) (vVar.f8904c - vVar.f8903b);
                vVar = vVar.f8907f;
                kotlin.jvm.internal.l.c(vVar);
                j7 = jJ0;
            }
            return -1L;
        }
        while (true) {
            long j9 = ((long) (vVar.f8904c - vVar.f8903b)) + jJ0;
            if (j9 > j7) {
                break;
            }
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
            jJ0 = j9;
        }
        while (jJ0 < j8) {
            byte[] bArr2 = vVar.f8902a;
            int iMin2 = (int) Math.min(vVar.f8904c, (((long) vVar.f8903b) + j8) - jJ0);
            i7 = (int) ((((long) vVar.f8903b) + j7) - jJ0);
            while (i7 < iMin2) {
                if (bArr2[i7] != b7) {
                    i7++;
                }
            }
            jJ0 += (long) (vVar.f8904c - vVar.f8903b);
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
            j7 = jJ0;
        }
        return -1L;
        return ((long) (i7 - vVar.f8903b)) + jJ0;
    }

    @Override // p6.g
    public byte[] S(long j7) throws EOFException {
        if (!(j7 >= 0 && j7 <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + j7).toString());
        }
        if (j0() < j7) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j7];
        c0(bArr);
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ae A[EDGE_INSN: B:43:0x00ae->B:37:0x00ae BREAK  A[LOOP:0: B:5:0x000d->B:45:?], SYNTHETIC] */
    @Override // p6.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long T() throws java.io.EOFException {
        /*
            r14 = this;
            long r0 = r14.j0()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lb8
            r0 = 0
            r1 = r0
            r4 = r2
        Ld:
            p6.v r6 = r14.f8866e
            kotlin.jvm.internal.l.c(r6)
            byte[] r7 = r6.f8902a
            int r8 = r6.f8903b
            int r9 = r6.f8904c
        L18:
            if (r8 >= r9) goto L9a
            r10 = r7[r8]
            r11 = 48
            byte r11 = (byte) r11
            if (r10 < r11) goto L29
            r12 = 57
            byte r12 = (byte) r12
            if (r10 > r12) goto L29
            int r11 = r10 - r11
            goto L43
        L29:
            r11 = 97
            byte r11 = (byte) r11
            if (r10 < r11) goto L38
            r12 = 102(0x66, float:1.43E-43)
            byte r12 = (byte) r12
            if (r10 > r12) goto L38
        L33:
            int r11 = r10 - r11
            int r11 = r11 + 10
            goto L43
        L38:
            r11 = 65
            byte r11 = (byte) r11
            if (r10 < r11) goto L7b
            r12 = 70
            byte r12 = (byte) r12
            if (r10 > r12) goto L7b
            goto L33
        L43:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L53
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L18
        L53:
            p6.e r0 = new p6.e
            r0.<init>()
            p6.e r0 = r0.j(r4)
            p6.e r0 = r0.M(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.g0()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L7b:
            if (r0 == 0) goto L7f
            r1 = 1
            goto L9a
        L7f:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.append(r2)
            java.lang.String r2 = p6.c.e(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L9a:
            if (r8 != r9) goto La6
            p6.v r7 = r6.b()
            r14.f8866e = r7
            p6.w.b(r6)
            goto La8
        La6:
            r6.f8903b = r8
        La8:
            if (r1 != 0) goto Lae
            p6.v r6 = r14.f8866e
            if (r6 != 0) goto Ld
        Lae:
            long r1 = r14.j0()
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.i0(r1)
            return r4
        Lb8:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p6.e.T():long");
    }

    public long U(h targetBytes) {
        kotlin.jvm.internal.l.e(targetBytes, "targetBytes");
        return V(targetBytes, 0L);
    }

    public long V(h targetBytes, long j7) {
        int i7;
        int i8;
        kotlin.jvm.internal.l.e(targetBytes, "targetBytes");
        long jJ0 = 0;
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + j7).toString());
        }
        v vVar = this.f8866e;
        if (vVar == null) {
            return -1L;
        }
        if (j0() - j7 < j7) {
            jJ0 = j0();
            while (jJ0 > j7) {
                vVar = vVar.f8908g;
                kotlin.jvm.internal.l.c(vVar);
                jJ0 -= (long) (vVar.f8904c - vVar.f8903b);
            }
            if (targetBytes.r() == 2) {
                byte bD = targetBytes.d(0);
                byte bD2 = targetBytes.d(1);
                while (jJ0 < j0()) {
                    byte[] bArr = vVar.f8902a;
                    i7 = (int) ((((long) vVar.f8903b) + j7) - jJ0);
                    int i9 = vVar.f8904c;
                    while (i7 < i9) {
                        byte b7 = bArr[i7];
                        if (b7 == bD || b7 == bD2) {
                            i8 = vVar.f8903b;
                        } else {
                            i7++;
                        }
                    }
                    jJ0 += (long) (vVar.f8904c - vVar.f8903b);
                    vVar = vVar.f8907f;
                    kotlin.jvm.internal.l.c(vVar);
                    j7 = jJ0;
                }
                return -1L;
            }
            byte[] bArrJ = targetBytes.j();
            while (jJ0 < j0()) {
                byte[] bArr2 = vVar.f8902a;
                i7 = (int) ((((long) vVar.f8903b) + j7) - jJ0);
                int i10 = vVar.f8904c;
                while (i7 < i10) {
                    byte b8 = bArr2[i7];
                    for (byte b9 : bArrJ) {
                        if (b8 == b9) {
                            i8 = vVar.f8903b;
                        }
                    }
                    i7++;
                }
                jJ0 += (long) (vVar.f8904c - vVar.f8903b);
                vVar = vVar.f8907f;
                kotlin.jvm.internal.l.c(vVar);
                j7 = jJ0;
            }
            return -1L;
        }
        while (true) {
            long j8 = ((long) (vVar.f8904c - vVar.f8903b)) + jJ0;
            if (j8 > j7) {
                break;
            }
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
            jJ0 = j8;
        }
        if (targetBytes.r() == 2) {
            byte bD3 = targetBytes.d(0);
            byte bD4 = targetBytes.d(1);
            while (jJ0 < j0()) {
                byte[] bArr3 = vVar.f8902a;
                i7 = (int) ((((long) vVar.f8903b) + j7) - jJ0);
                int i11 = vVar.f8904c;
                while (i7 < i11) {
                    byte b10 = bArr3[i7];
                    if (b10 == bD3 || b10 == bD4) {
                        i8 = vVar.f8903b;
                    } else {
                        i7++;
                    }
                }
                jJ0 += (long) (vVar.f8904c - vVar.f8903b);
                vVar = vVar.f8907f;
                kotlin.jvm.internal.l.c(vVar);
                j7 = jJ0;
            }
            return -1L;
        }
        byte[] bArrJ2 = targetBytes.j();
        while (jJ0 < j0()) {
            byte[] bArr4 = vVar.f8902a;
            i7 = (int) ((((long) vVar.f8903b) + j7) - jJ0);
            int i12 = vVar.f8904c;
            while (i7 < i12) {
                byte b11 = bArr4[i7];
                for (byte b12 : bArrJ2) {
                    if (b11 == b12) {
                        i8 = vVar.f8903b;
                    }
                }
                i7++;
            }
            jJ0 += (long) (vVar.f8904c - vVar.f8903b);
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
            j7 = jJ0;
        }
        return -1L;
        return ((long) (i7 - i8)) + jJ0;
    }

    @Override // p6.g
    public InputStream X() {
        return new a();
    }

    @Override // p6.g
    public byte Y() throws EOFException {
        if (j0() == 0) {
            throw new EOFException();
        }
        v vVar = this.f8866e;
        kotlin.jvm.internal.l.c(vVar);
        int i7 = vVar.f8903b;
        int i8 = vVar.f8904c;
        int i9 = i7 + 1;
        byte b7 = vVar.f8902a[i7];
        i0(j0() - 1);
        if (i9 == i8) {
            this.f8866e = vVar.b();
            w.b(vVar);
        } else {
            vVar.f8903b = i9;
        }
        return b7;
    }

    public int Z(byte[] sink, int i7, int i8) {
        kotlin.jvm.internal.l.e(sink, "sink");
        c.b(sink.length, i7, i8);
        v vVar = this.f8866e;
        if (vVar == null) {
            return -1;
        }
        int iMin = Math.min(i8, vVar.f8904c - vVar.f8903b);
        byte[] bArr = vVar.f8902a;
        int i9 = vVar.f8903b;
        h5.k.c(bArr, sink, i7, i9, i9 + iMin);
        vVar.f8903b += iMin;
        i0(j0() - ((long) iMin));
        if (vVar.f8903b != vVar.f8904c) {
            return iMin;
        }
        this.f8866e = vVar.b();
        w.b(vVar);
        return iMin;
    }

    @Override // p6.g
    public void a(long j7) throws EOFException {
        while (j7 > 0) {
            v vVar = this.f8866e;
            if (vVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j7, vVar.f8904c - vVar.f8903b);
            long j8 = iMin;
            i0(j0() - j8);
            j7 -= j8;
            int i7 = vVar.f8903b + iMin;
            vVar.f8903b = i7;
            if (i7 == vVar.f8904c) {
                this.f8866e = vVar.b();
                w.b(vVar);
            }
        }
    }

    public byte[] a0() {
        return S(j0());
    }

    @Override // p6.g, p6.f
    public e b() {
        return this;
    }

    public h b0() {
        return n(j0());
    }

    @Override // p6.a0
    public b0 c() {
        return b0.f8850d;
    }

    public void c0(byte[] sink) throws EOFException {
        kotlin.jvm.internal.l.e(sink, "sink");
        int i7 = 0;
        while (i7 < sink.length) {
            int iZ = Z(sink, i7, sink.length - i7);
            if (iZ == -1) {
                throw new EOFException();
            }
            i7 += iZ;
        }
    }

    @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public int d0() {
        return c.c(x());
    }

    public short e0() {
        return c.d(s());
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof e)) {
                return false;
            }
            e eVar = (e) obj;
            if (j0() != eVar.j0()) {
                return false;
            }
            if (j0() != 0) {
                v vVar = this.f8866e;
                kotlin.jvm.internal.l.c(vVar);
                v vVar2 = eVar.f8866e;
                kotlin.jvm.internal.l.c(vVar2);
                int i7 = vVar.f8903b;
                int i8 = vVar2.f8903b;
                long j7 = 0;
                while (j7 < j0()) {
                    long jMin = Math.min(vVar.f8904c - i7, vVar2.f8904c - i8);
                    long j8 = 0;
                    while (j8 < jMin) {
                        int i9 = i7 + 1;
                        int i10 = i8 + 1;
                        if (vVar.f8902a[i7] != vVar2.f8902a[i8]) {
                            return false;
                        }
                        j8++;
                        i7 = i9;
                        i8 = i10;
                    }
                    if (i7 == vVar.f8904c) {
                        vVar = vVar.f8907f;
                        kotlin.jvm.internal.l.c(vVar);
                        i7 = vVar.f8903b;
                    }
                    if (i8 == vVar2.f8904c) {
                        vVar2 = vVar2.f8907f;
                        kotlin.jvm.internal.l.c(vVar2);
                        i8 = vVar2.f8903b;
                    }
                    j7 += jMin;
                }
            }
        }
        return true;
    }

    public String f0(long j7, Charset charset) throws EOFException {
        kotlin.jvm.internal.l.e(charset, "charset");
        if (!(j7 >= 0 && j7 <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + j7).toString());
        }
        if (this.f8867g < j7) {
            throw new EOFException();
        }
        if (j7 == 0) {
            return "";
        }
        v vVar = this.f8866e;
        kotlin.jvm.internal.l.c(vVar);
        int i7 = vVar.f8903b;
        if (((long) i7) + j7 > vVar.f8904c) {
            return new String(S(j7), charset);
        }
        int i8 = (int) j7;
        String str = new String(vVar.f8902a, i7, i8, charset);
        int i9 = vVar.f8903b + i8;
        vVar.f8903b = i9;
        this.f8867g -= j7;
        if (i9 == vVar.f8904c) {
            this.f8866e = vVar.b();
            w.b(vVar);
        }
        return str;
    }

    @Override // p6.f, p6.y, java.io.Flushable
    public void flush() {
    }

    public String g0() {
        return f0(this.f8867g, y5.d.f9992b);
    }

    public String h0(long j7) {
        return f0(j7, y5.d.f9992b);
    }

    public int hashCode() {
        v vVar = this.f8866e;
        if (vVar == null) {
            return 0;
        }
        int i7 = 1;
        do {
            int i8 = vVar.f8904c;
            for (int i9 = vVar.f8903b; i9 < i8; i9++) {
                i7 = (i7 * 31) + vVar.f8902a[i9];
            }
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
        } while (vVar != this.f8866e);
        return i7;
    }

    @Override // p6.a0
    public long i(e sink, long j7) {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (j0() == 0) {
            return -1L;
        }
        if (j7 > j0()) {
            j7 = j0();
        }
        sink.l(this, j7);
        return j7;
    }

    public final void i0(long j7) {
        this.f8867g = j7;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final long j0() {
        return this.f8867g;
    }

    public final h k0() {
        if (j0() <= ((long) Integer.MAX_VALUE)) {
            return l0((int) j0());
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + j0()).toString());
    }

    @Override // p6.y
    public void l(e source, long j7) {
        v vVar;
        kotlin.jvm.internal.l.e(source, "source");
        if (!(source != this)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        c.b(source.j0(), 0L, j7);
        while (j7 > 0) {
            v vVar2 = source.f8866e;
            kotlin.jvm.internal.l.c(vVar2);
            int i7 = vVar2.f8904c;
            kotlin.jvm.internal.l.c(source.f8866e);
            if (j7 < i7 - r2.f8903b) {
                v vVar3 = this.f8866e;
                if (vVar3 != null) {
                    kotlin.jvm.internal.l.c(vVar3);
                    vVar = vVar3.f8908g;
                } else {
                    vVar = null;
                }
                if (vVar != null && vVar.f8906e) {
                    if ((((long) vVar.f8904c) + j7) - ((long) (vVar.f8905d ? 0 : vVar.f8903b)) <= 8192) {
                        v vVar4 = source.f8866e;
                        kotlin.jvm.internal.l.c(vVar4);
                        vVar4.f(vVar, (int) j7);
                        source.i0(source.j0() - j7);
                        i0(j0() + j7);
                        return;
                    }
                }
                v vVar5 = source.f8866e;
                kotlin.jvm.internal.l.c(vVar5);
                source.f8866e = vVar5.e((int) j7);
            }
            v vVar6 = source.f8866e;
            kotlin.jvm.internal.l.c(vVar6);
            long j8 = vVar6.f8904c - vVar6.f8903b;
            source.f8866e = vVar6.b();
            v vVar7 = this.f8866e;
            if (vVar7 == null) {
                this.f8866e = vVar6;
                vVar6.f8908g = vVar6;
                vVar6.f8907f = vVar6;
            } else {
                kotlin.jvm.internal.l.c(vVar7);
                v vVar8 = vVar7.f8908g;
                kotlin.jvm.internal.l.c(vVar8);
                vVar8.c(vVar6).a();
            }
            source.i0(source.j0() - j8);
            i0(j0() + j8);
            j7 -= j8;
        }
    }

    public final h l0(int i7) {
        if (i7 == 0) {
            return h.f8869i;
        }
        c.b(j0(), 0L, i7);
        v vVar = this.f8866e;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            kotlin.jvm.internal.l.c(vVar);
            int i11 = vVar.f8904c;
            int i12 = vVar.f8903b;
            if (i11 == i12) {
                throw new AssertionError("s.limit == s.pos");
            }
            i9 += i11 - i12;
            i10++;
            vVar = vVar.f8907f;
        }
        byte[][] bArr = new byte[i10][];
        int[] iArr = new int[i10 * 2];
        v vVar2 = this.f8866e;
        int i13 = 0;
        while (i8 < i7) {
            kotlin.jvm.internal.l.c(vVar2);
            bArr[i13] = vVar2.f8902a;
            i8 += vVar2.f8904c - vVar2.f8903b;
            iArr[i13] = Math.min(i8, i7);
            iArr[i13 + i10] = vVar2.f8903b;
            vVar2.f8905d = true;
            i13++;
            vVar2 = vVar2.f8907f;
        }
        return new x(bArr, iArr);
    }

    public final v m0(int i7) {
        if (!(i7 >= 1 && i7 <= 8192)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        v vVar = this.f8866e;
        if (vVar != null) {
            kotlin.jvm.internal.l.c(vVar);
            v vVar2 = vVar.f8908g;
            kotlin.jvm.internal.l.c(vVar2);
            return (vVar2.f8904c + i7 > 8192 || !vVar2.f8906e) ? vVar2.c(w.c()) : vVar2;
        }
        v vVarC = w.c();
        this.f8866e = vVarC;
        vVarC.f8908g = vVarC;
        vVarC.f8907f = vVarC;
        return vVarC;
    }

    @Override // p6.g
    public h n(long j7) throws EOFException {
        if (!(j7 >= 0 && j7 <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + j7).toString());
        }
        if (j0() < j7) {
            throw new EOFException();
        }
        if (j7 < 4096) {
            return new h(S(j7));
        }
        h hVarL0 = l0((int) j7);
        a(j7);
        return hVarL0;
    }

    @Override // p6.f
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public e Q(h byteString) {
        kotlin.jvm.internal.l.e(byteString, "byteString");
        byteString.v(this, 0, byteString.r());
        return this;
    }

    @Override // p6.f
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public e d(byte[] source) {
        kotlin.jvm.internal.l.e(source, "source");
        return e(source, 0, source.length);
    }

    @Override // p6.g
    public String p(long j7) throws EOFException {
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + j7).toString());
        }
        long j8 = j7 != Long.MAX_VALUE ? j7 + 1 : Long.MAX_VALUE;
        byte b7 = (byte) 10;
        long jP = P(b7, 0L, j8);
        if (jP != -1) {
            return q6.a.b(this, jP);
        }
        if (j8 < j0() && N(j8 - 1) == ((byte) 13) && N(j8) == b7) {
            return q6.a.b(this, j8);
        }
        e eVar = new e();
        K(eVar, 0L, Math.min(32, j0()));
        throw new EOFException("\\n not found: limit=" + Math.min(j0(), j7) + " content=" + eVar.b0().i() + (char) 8230);
    }

    @Override // p6.f
    /* JADX INFO: renamed from: p0, reason: merged with bridge method [inline-methods] */
    public e e(byte[] source, int i7, int i8) {
        kotlin.jvm.internal.l.e(source, "source");
        long j7 = i8;
        c.b(source.length, i7, j7);
        int i9 = i8 + i7;
        while (i7 < i9) {
            v vVarM0 = m0(1);
            int iMin = Math.min(i9 - i7, 8192 - vVarM0.f8904c);
            int i10 = i7 + iMin;
            h5.k.c(source, vVarM0.f8902a, vVarM0.f8904c, i7, i10);
            vVarM0.f8904c += iMin;
            i7 = i10;
        }
        i0(j0() + j7);
        return this;
    }

    public long q0(a0 source) {
        kotlin.jvm.internal.l.e(source, "source");
        long j7 = 0;
        while (true) {
            long jI = source.i(this, 8192);
            if (jI == -1) {
                return j7;
            }
            j7 += jI;
        }
    }

    @Override // p6.f
    /* JADX INFO: renamed from: r0, reason: merged with bridge method [inline-methods] */
    public e M(int i7) {
        v vVarM0 = m0(1);
        byte[] bArr = vVarM0.f8902a;
        int i8 = vVarM0.f8904c;
        vVarM0.f8904c = i8 + 1;
        bArr[i8] = (byte) i7;
        i0(j0() + 1);
        return this;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) {
        kotlin.jvm.internal.l.e(sink, "sink");
        v vVar = this.f8866e;
        if (vVar == null) {
            return -1;
        }
        int iMin = Math.min(sink.remaining(), vVar.f8904c - vVar.f8903b);
        sink.put(vVar.f8902a, vVar.f8903b, iMin);
        int i7 = vVar.f8903b + iMin;
        vVar.f8903b = i7;
        this.f8867g -= (long) iMin;
        if (i7 == vVar.f8904c) {
            this.f8866e = vVar.b();
            w.b(vVar);
        }
        return iMin;
    }

    @Override // p6.g
    public short s() throws EOFException {
        if (j0() < 2) {
            throw new EOFException();
        }
        v vVar = this.f8866e;
        kotlin.jvm.internal.l.c(vVar);
        int i7 = vVar.f8903b;
        int i8 = vVar.f8904c;
        if (i8 - i7 < 2) {
            return (short) (((Y() & 255) << 8) | (Y() & 255));
        }
        byte[] bArr = vVar.f8902a;
        int i9 = i7 + 1;
        int i10 = i9 + 1;
        int i11 = ((bArr[i7] & 255) << 8) | (bArr[i9] & 255);
        i0(j0() - 2);
        if (i10 == i8) {
            this.f8866e = vVar.b();
            w.b(vVar);
        } else {
            vVar.f8903b = i10;
        }
        return (short) i11;
    }

    @Override // p6.f
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public e j(long j7) {
        if (j7 == 0) {
            return M(48);
        }
        long j8 = (j7 >>> 1) | j7;
        long j9 = j8 | (j8 >>> 2);
        long j10 = j9 | (j9 >>> 4);
        long j11 = j10 | (j10 >>> 8);
        long j12 = j11 | (j11 >>> 16);
        long j13 = j12 | (j12 >>> 32);
        long j14 = j13 - ((j13 >>> 1) & 6148914691236517205L);
        long j15 = ((j14 >>> 2) & 3689348814741910323L) + (j14 & 3689348814741910323L);
        long j16 = ((j15 >>> 4) + j15) & 1085102592571150095L;
        long j17 = j16 + (j16 >>> 8);
        long j18 = j17 + (j17 >>> 16);
        int i7 = (int) ((((j18 & 63) + ((j18 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        v vVarM0 = m0(i7);
        byte[] bArr = vVarM0.f8902a;
        int i8 = vVarM0.f8904c;
        for (int i9 = (i8 + i7) - 1; i9 >= i8; i9--) {
            bArr[i9] = q6.a.a()[(int) (15 & j7)];
            j7 >>>= 4;
        }
        vVarM0.f8904c += i7;
        i0(j0() + ((long) i7));
        return this;
    }

    @Override // p6.f
    /* JADX INFO: renamed from: t0, reason: merged with bridge method [inline-methods] */
    public e y(int i7) {
        v vVarM0 = m0(4);
        byte[] bArr = vVarM0.f8902a;
        int i8 = vVarM0.f8904c;
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((i7 >>> 24) & 255);
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((i7 >>> 16) & 255);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i7 >>> 8) & 255);
        bArr[i11] = (byte) (i7 & 255);
        vVarM0.f8904c = i11 + 1;
        i0(j0() + 4);
        return this;
    }

    public String toString() {
        return k0().toString();
    }

    @Override // p6.f
    /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
    public e t(int i7) {
        v vVarM0 = m0(2);
        byte[] bArr = vVarM0.f8902a;
        int i8 = vVarM0.f8904c;
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((i7 >>> 8) & 255);
        bArr[i9] = (byte) (i7 & 255);
        vVarM0.f8904c = i9 + 1;
        i0(j0() + 2);
        return this;
    }

    public e v0(String string, int i7, int i8, Charset charset) {
        kotlin.jvm.internal.l.e(string, "string");
        kotlin.jvm.internal.l.e(charset, "charset");
        if (!(i7 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i7).toString());
        }
        if (!(i8 >= i7)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i8 + " < " + i7).toString());
        }
        if (!(i8 <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i8 + " > " + string.length()).toString());
        }
        if (kotlin.jvm.internal.l.a(charset, y5.d.f9992b)) {
            return x0(string, i7, i8);
        }
        String strSubstring = string.substring(i7, i8);
        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        Objects.requireNonNull(strSubstring, "null cannot be cast to non-null type java.lang.String");
        byte[] bytes = strSubstring.getBytes(charset);
        kotlin.jvm.internal.l.d(bytes, "(this as java.lang.String).getBytes(charset)");
        return e(bytes, 0, bytes.length);
    }

    @Override // p6.f
    /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
    public e H(String string) {
        kotlin.jvm.internal.l.e(string, "string");
        return x0(string, 0, string.length());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) {
        kotlin.jvm.internal.l.e(source, "source");
        int iRemaining = source.remaining();
        int i7 = iRemaining;
        while (i7 > 0) {
            v vVarM0 = m0(1);
            int iMin = Math.min(i7, 8192 - vVarM0.f8904c);
            source.get(vVarM0.f8902a, vVarM0.f8904c, iMin);
            i7 -= iMin;
            vVarM0.f8904c += iMin;
        }
        this.f8867g += (long) iRemaining;
        return iRemaining;
    }

    @Override // p6.g
    public int x() throws EOFException {
        if (j0() < 4) {
            throw new EOFException();
        }
        v vVar = this.f8866e;
        kotlin.jvm.internal.l.c(vVar);
        int i7 = vVar.f8903b;
        int i8 = vVar.f8904c;
        if (i8 - i7 < 4) {
            return ((Y() & 255) << 24) | ((Y() & 255) << 16) | ((Y() & 255) << 8) | (Y() & 255);
        }
        byte[] bArr = vVar.f8902a;
        int i9 = i7 + 1;
        int i10 = i9 + 1;
        int i11 = ((bArr[i7] & 255) << 24) | ((bArr[i9] & 255) << 16);
        int i12 = i10 + 1;
        int i13 = i11 | ((bArr[i10] & 255) << 8);
        int i14 = i12 + 1;
        int i15 = i13 | (bArr[i12] & 255);
        i0(j0() - 4);
        if (i14 == i8) {
            this.f8866e = vVar.b();
            w.b(vVar);
        } else {
            vVar.f8903b = i14;
        }
        return i15;
    }

    public e x0(String string, int i7, int i8) {
        kotlin.jvm.internal.l.e(string, "string");
        if (!(i7 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i7).toString());
        }
        if (!(i8 >= i7)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i8 + " < " + i7).toString());
        }
        if (!(i8 <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i8 + " > " + string.length()).toString());
        }
        while (i7 < i8) {
            char cCharAt = string.charAt(i7);
            if (cCharAt < 128) {
                v vVarM0 = m0(1);
                byte[] bArr = vVarM0.f8902a;
                int i9 = vVarM0.f8904c - i7;
                int iMin = Math.min(i8, 8192 - i9);
                int i10 = i7 + 1;
                bArr[i7 + i9] = (byte) cCharAt;
                while (i10 < iMin) {
                    char cCharAt2 = string.charAt(i10);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i10 + i9] = (byte) cCharAt2;
                    i10++;
                }
                int i11 = vVarM0.f8904c;
                int i12 = (i9 + i10) - i11;
                vVarM0.f8904c = i11 + i12;
                i0(j0() + ((long) i12));
                i7 = i10;
            } else {
                if (cCharAt < 2048) {
                    v vVarM02 = m0(2);
                    byte[] bArr2 = vVarM02.f8902a;
                    int i13 = vVarM02.f8904c;
                    bArr2[i13] = (byte) ((cCharAt >> 6) | 192);
                    bArr2[i13 + 1] = (byte) ((cCharAt & '?') | 128);
                    vVarM02.f8904c = i13 + 2;
                    i0(j0() + 2);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    v vVarM03 = m0(3);
                    byte[] bArr3 = vVarM03.f8902a;
                    int i14 = vVarM03.f8904c;
                    bArr3[i14] = (byte) ((cCharAt >> '\f') | 224);
                    bArr3[i14 + 1] = (byte) ((63 & (cCharAt >> 6)) | 128);
                    bArr3[i14 + 2] = (byte) ((cCharAt & '?') | 128);
                    vVarM03.f8904c = i14 + 3;
                    i0(j0() + 3);
                } else {
                    int i15 = i7 + 1;
                    char cCharAt3 = i15 < i8 ? string.charAt(i15) : (char) 0;
                    if (cCharAt > 56319 || 56320 > cCharAt3 || 57343 < cCharAt3) {
                        M(63);
                        i7 = i15;
                    } else {
                        int i16 = (((cCharAt & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        v vVarM04 = m0(4);
                        byte[] bArr4 = vVarM04.f8902a;
                        int i17 = vVarM04.f8904c;
                        bArr4[i17] = (byte) ((i16 >> 18) | 240);
                        bArr4[i17 + 1] = (byte) (((i16 >> 12) & 63) | 128);
                        bArr4[i17 + 2] = (byte) (((i16 >> 6) & 63) | 128);
                        bArr4[i17 + 3] = (byte) ((i16 & 63) | 128);
                        vVarM04.f8904c = i17 + 4;
                        i0(j0() + 4);
                        i7 += 2;
                    }
                }
                i7++;
            }
        }
        return this;
    }

    public e y0(int i7) {
        if (i7 < 128) {
            M(i7);
        } else if (i7 < 2048) {
            v vVarM0 = m0(2);
            byte[] bArr = vVarM0.f8902a;
            int i8 = vVarM0.f8904c;
            bArr[i8] = (byte) ((i7 >> 6) | 192);
            bArr[i8 + 1] = (byte) ((i7 & 63) | 128);
            vVarM0.f8904c = i8 + 2;
            i0(j0() + 2);
        } else if (55296 <= i7 && 57343 >= i7) {
            M(63);
        } else if (i7 < 65536) {
            v vVarM02 = m0(3);
            byte[] bArr2 = vVarM02.f8902a;
            int i9 = vVarM02.f8904c;
            bArr2[i9] = (byte) ((i7 >> 12) | 224);
            bArr2[i9 + 1] = (byte) (((i7 >> 6) & 63) | 128);
            bArr2[i9 + 2] = (byte) ((i7 & 63) | 128);
            vVarM02.f8904c = i9 + 3;
            i0(j0() + 3);
        } else {
            if (i7 > 1114111) {
                throw new IllegalArgumentException("Unexpected code point: 0x" + c.f(i7));
            }
            v vVarM03 = m0(4);
            byte[] bArr3 = vVarM03.f8902a;
            int i10 = vVarM03.f8904c;
            bArr3[i10] = (byte) ((i7 >> 18) | 240);
            bArr3[i10 + 1] = (byte) (((i7 >> 12) & 63) | 128);
            bArr3[i10 + 2] = (byte) (((i7 >> 6) & 63) | 128);
            bArr3[i10 + 3] = (byte) ((i7 & 63) | 128);
            vVarM03.f8904c = i10 + 4;
            i0(j0() + 4);
        }
        return this;
    }

    public final void z() throws EOFException {
        a(j0());
    }

    /* JADX INFO: compiled from: Buffer.kt */
    public static final class a extends InputStream {
        a() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(e.this.j0(), Integer.MAX_VALUE);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            if (e.this.j0() > 0) {
                return e.this.Y() & 255;
            }
            return -1;
        }

        public String toString() {
            return e.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] sink, int i7, int i8) {
            kotlin.jvm.internal.l.e(sink, "sink");
            return e.this.Z(sink, i7, i8);
        }
    }
}

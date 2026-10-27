package spi;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.util.Objects;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import okio.ByteString;
import okio.SegmentedByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class g2 implements l2, k2, Cloneable, ByteChannel {

    @JvmField
    @Nullable
    public vo a;
    public long b;

    public static final class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public final int available() {
            return (int) Math.min(g2.this.b, Integer.MAX_VALUE);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }

        @Override // java.io.InputStream
        public final int read() {
            g2 g2Var = g2.this;
            if (g2Var.b > 0) {
                return g2Var.readByte() & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public final int read(@NotNull byte[] sink, int i, int i2) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            return g2.this.read(sink, i, i2);
        }

        @NotNull
        public final String toString() {
            return g2.this + ".inputStream()";
        }
    }

    @Override // spi.l2
    @NotNull
    public final InputStream A() {
        return new a();
    }

    public final void B() throws EOFException {
        skip(this.b);
    }

    @NotNull
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final g2 clone() {
        g2 g2Var = new g2();
        if (this.b != 0) {
            vo voVar = this.a;
            Intrinsics.checkNotNull(voVar);
            vo voVarC = voVar.c();
            g2Var.a = voVarC;
            voVarC.g = voVarC;
            voVarC.f = voVarC;
            for (vo voVar2 = voVar.f; voVar2 != voVar; voVar2 = voVar2.f) {
                vo voVar3 = voVarC.g;
                Intrinsics.checkNotNull(voVar3);
                Intrinsics.checkNotNull(voVar2);
                voVar3.b(voVar2.c());
            }
            g2Var.b = this.b;
        }
        return g2Var;
    }

    public final long D() {
        long j = this.b;
        if (j == 0) {
            return 0L;
        }
        vo voVar = this.a;
        Intrinsics.checkNotNull(voVar);
        vo voVar2 = voVar.g;
        Intrinsics.checkNotNull(voVar2);
        int i = voVar2.c;
        if (i < 8192 && voVar2.e) {
            j -= (long) (i - voVar2.b);
        }
        return j;
    }

    @NotNull
    public final g2 E(@NotNull g2 out, long j, long j2) {
        Intrinsics.checkNotNullParameter(out, "out");
        c.d(this.b, j, j2);
        if (j2 != 0) {
            out.b += j2;
            vo voVar = this.a;
            while (true) {
                Intrinsics.checkNotNull(voVar);
                int i = voVar.c;
                int i2 = voVar.b;
                if (j < i - i2) {
                    break;
                }
                j -= (long) (i - i2);
                voVar = voVar.f;
            }
            while (j2 > 0) {
                Intrinsics.checkNotNull(voVar);
                vo voVarC = voVar.c();
                int i3 = voVarC.b + ((int) j);
                voVarC.b = i3;
                voVarC.c = Math.min(i3 + ((int) j2), voVarC.c);
                vo voVar2 = out.a;
                if (voVar2 == null) {
                    voVarC.g = voVarC;
                    voVarC.f = voVarC;
                    out.a = voVarC;
                } else {
                    Intrinsics.checkNotNull(voVar2);
                    vo voVar3 = voVar2.g;
                    Intrinsics.checkNotNull(voVar3);
                    voVar3.b(voVarC);
                }
                j2 -= (long) (voVarC.c - voVarC.b);
                voVar = voVar.f;
                j = 0;
            }
        }
        return this;
    }

    @JvmName(name = "getByte")
    public final byte F(long j) {
        c.d(this.b, j, 1L);
        vo voVar = this.a;
        if (voVar == null) {
            Intrinsics.checkNotNull(null);
            throw null;
        }
        long j2 = this.b;
        if (j2 - j < j) {
            while (j2 > j) {
                voVar = voVar.g;
                Intrinsics.checkNotNull(voVar);
                j2 -= (long) (voVar.c - voVar.b);
            }
            Intrinsics.checkNotNull(voVar);
            return voVar.a[(int) ((((long) voVar.b) + j) - j2)];
        }
        long j3 = 0;
        while (true) {
            long j4 = ((long) (voVar.c - voVar.b)) + j3;
            if (j4 > j) {
                Intrinsics.checkNotNull(voVar);
                return voVar.a[(int) ((((long) voVar.b) + j) - j3)];
            }
            voVar = voVar.f;
            Intrinsics.checkNotNull(voVar);
            j3 = j4;
        }
    }

    public final long G(byte b, long j, long j2) {
        vo voVar;
        long j3 = 0;
        if (!(0 <= j && j2 >= j)) {
            StringBuilder sbK = e.k("size=");
            sbK.append(this.b);
            sbK.append(" fromIndex=");
            sbK.append(j);
            sbK.append(" toIndex=");
            sbK.append(j2);
            throw new IllegalArgumentException(sbK.toString().toString());
        }
        long j4 = this.b;
        if (j2 > j4) {
            j2 = j4;
        }
        if (j != j2 && (voVar = this.a) != null) {
            if (j4 - j < j) {
                while (j4 > j) {
                    voVar = voVar.g;
                    Intrinsics.checkNotNull(voVar);
                    j4 -= (long) (voVar.c - voVar.b);
                }
                while (j4 < j2) {
                    byte[] bArr = voVar.a;
                    int iMin = (int) Math.min(voVar.c, (((long) voVar.b) + j2) - j4);
                    for (int i = (int) ((((long) voVar.b) + j) - j4); i < iMin; i++) {
                        if (bArr[i] == b) {
                            return ((long) (i - voVar.b)) + j4;
                        }
                    }
                    j4 += (long) (voVar.c - voVar.b);
                    voVar = voVar.f;
                    Intrinsics.checkNotNull(voVar);
                    j = j4;
                }
            } else {
                while (true) {
                    long j5 = ((long) (voVar.c - voVar.b)) + j3;
                    if (j5 > j) {
                        break;
                    }
                    voVar = voVar.f;
                    Intrinsics.checkNotNull(voVar);
                    j3 = j5;
                }
                while (j3 < j2) {
                    byte[] bArr2 = voVar.a;
                    int iMin2 = (int) Math.min(voVar.c, (((long) voVar.b) + j2) - j3);
                    for (int i2 = (int) ((((long) voVar.b) + j) - j3); i2 < iMin2; i2++) {
                        if (bArr2[i2] == b) {
                            return ((long) (i2 - voVar.b)) + j3;
                        }
                    }
                    j3 += (long) (voVar.c - voVar.b);
                    voVar = voVar.f;
                    Intrinsics.checkNotNull(voVar);
                    j = j3;
                }
            }
        }
        return -1L;
    }

    public final long H(@NotNull ByteString targetBytes) {
        int i;
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        vo voVar = this.a;
        if (voVar != null) {
            long j = this.b;
            long j2 = 0;
            if (j - 0 < 0) {
                while (j > 0) {
                    voVar = voVar.g;
                    Intrinsics.checkNotNull(voVar);
                    j -= (long) (voVar.c - voVar.b);
                }
                if (targetBytes.size() == 2) {
                    byte b = targetBytes.getByte(0);
                    byte b2 = targetBytes.getByte(1);
                    while (j < this.b) {
                        byte[] bArr = voVar.a;
                        i = (int) ((((long) voVar.b) + j2) - j);
                        int i2 = voVar.c;
                        while (i < i2) {
                            byte b3 = bArr[i];
                            if (b3 == b || b3 == b2) {
                                return ((long) (i - voVar.b)) + j;
                            }
                            i++;
                        }
                        j2 = ((long) (voVar.c - voVar.b)) + j;
                        voVar = voVar.f;
                        Intrinsics.checkNotNull(voVar);
                        j = j2;
                    }
                } else {
                    byte[] bArrInternalArray$okio = targetBytes.internalArray$okio();
                    while (j < this.b) {
                        byte[] bArr2 = voVar.a;
                        i = (int) ((((long) voVar.b) + j2) - j);
                        int i3 = voVar.c;
                        while (i < i3) {
                            byte b4 = bArr2[i];
                            for (byte b5 : bArrInternalArray$okio) {
                                if (b4 == b5) {
                                    return ((long) (i - voVar.b)) + j;
                                }
                            }
                            i++;
                        }
                        j2 = ((long) (voVar.c - voVar.b)) + j;
                        voVar = voVar.f;
                        Intrinsics.checkNotNull(voVar);
                        j = j2;
                    }
                }
            } else {
                j = 0;
                while (true) {
                    long j3 = ((long) (voVar.c - voVar.b)) + j;
                    if (j3 > 0) {
                        break;
                    }
                    voVar = voVar.f;
                    Intrinsics.checkNotNull(voVar);
                    j = j3;
                }
                if (targetBytes.size() == 2) {
                    byte b6 = targetBytes.getByte(0);
                    byte b7 = targetBytes.getByte(1);
                    while (j < this.b) {
                        byte[] bArr3 = voVar.a;
                        i = (int) ((((long) voVar.b) + j2) - j);
                        int i4 = voVar.c;
                        while (i < i4) {
                            byte b8 = bArr3[i];
                            if (b8 == b6 || b8 == b7) {
                                return ((long) (i - voVar.b)) + j;
                            }
                            i++;
                        }
                        j2 = ((long) (voVar.c - voVar.b)) + j;
                        voVar = voVar.f;
                        Intrinsics.checkNotNull(voVar);
                        j = j2;
                    }
                } else {
                    byte[] bArrInternalArray$okio2 = targetBytes.internalArray$okio();
                    while (j < this.b) {
                        byte[] bArr4 = voVar.a;
                        i = (int) ((((long) voVar.b) + j2) - j);
                        int i5 = voVar.c;
                        while (i < i5) {
                            byte b9 = bArr4[i];
                            for (byte b10 : bArrInternalArray$okio2) {
                                if (b9 == b10) {
                                    return ((long) (i - voVar.b)) + j;
                                }
                            }
                            i++;
                        }
                        j2 = ((long) (voVar.c - voVar.b)) + j;
                        voVar = voVar.f;
                        Intrinsics.checkNotNull(voVar);
                        j = j2;
                    }
                }
            }
        }
        return -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b3 A[EDGE_INSN: B:51:0x00b3->B:41:0x00b3 BREAK  A[LOOP:0: B:5:0x000f->B:53:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long I() throws java.io.EOFException {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.b
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto Lbe
            r1 = -7
            r5 = 0
            r6 = r5
            r7 = r6
        Lf:
            spi.vo r8 = r0.a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            byte[] r9 = r8.a
            int r10 = r8.b
            int r11 = r8.c
        L1a:
            r12 = 1
            if (r10 >= r11) goto L9d
            r13 = r9[r10]
            r14 = 48
            byte r14 = (byte) r14
            if (r13 < r14) goto L6c
            r15 = 57
            byte r15 = (byte) r15
            if (r13 > r15) goto L6c
            int r14 = r14 - r13
            r15 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r12 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r12 < 0) goto L44
            r15 = r8
            r16 = r9
            if (r12 != 0) goto L3e
            long r8 = (long) r14
            int r8 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r8 >= 0) goto L3e
            goto L44
        L3e:
            r8 = 10
            long r3 = r3 * r8
            long r8 = (long) r14
            long r3 = r3 + r8
            goto L7a
        L44:
            spi.g2 r1 = new spi.g2
            r1.<init>()
            spi.g2 r1 = r1.y(r3)
            r1.S(r13)
            if (r7 != 0) goto L55
            r1.readByte()
        L55:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.String r3 = "Number too large: "
            java.lang.StringBuilder r3 = spi.e.k(r3)
            java.lang.String r1 = r1.L()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L6c:
            r15 = r8
            r16 = r9
            r8 = 45
            byte r8 = (byte) r8
            if (r13 != r8) goto L82
            if (r5 != 0) goto L82
            r7 = 1
            long r1 = r1 - r7
            r7 = r12
        L7a:
            int r10 = r10 + 1
            int r5 = r5 + 1
            r8 = r15
            r9 = r16
            goto L1a
        L82:
            if (r5 == 0) goto L86
            r6 = r12
            goto L9e
        L86:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Expected leading [0-9] or '-' character but was 0x"
            java.lang.StringBuilder r2 = spi.e.k(r2)
            java.lang.String r3 = spi.c.h(r13)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L9d:
            r15 = r8
        L9e:
            if (r10 != r11) goto Laa
            spi.vo r8 = r15.a()
            r0.a = r8
            spi.wo.b(r15)
            goto Lad
        Laa:
            r8 = r15
            r8.b = r10
        Lad:
            if (r6 != 0) goto Lb3
            spi.vo r8 = r0.a
            if (r8 != 0) goto Lf
        Lb3:
            long r1 = r0.b
            long r5 = (long) r5
            long r1 = r1 - r5
            r0.b = r1
            if (r7 == 0) goto Lbc
            goto Lbd
        Lbc:
            long r3 = -r3
        Lbd:
            return r3
        Lbe:
            java.io.EOFException r1 = new java.io.EOFException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.g2.I():long");
    }

    public final short J() throws EOFException {
        int i = readShort() & UShort.MAX_VALUE;
        return (short) (((i & 255) << 8) | ((65280 & i) >>> 8));
    }

    @NotNull
    public final String K(long j, @NotNull Charset charset) throws EOFException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!(j >= 0 && j <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(d.d("byteCount: ", j).toString());
        }
        if (this.b < j) {
            throw new EOFException();
        }
        if (j == 0) {
            return "";
        }
        vo voVar = this.a;
        Intrinsics.checkNotNull(voVar);
        int i = voVar.b;
        if (((long) i) + j > voVar.c) {
            return new String(s(j), charset);
        }
        int i2 = (int) j;
        String str = new String(voVar.a, i, i2, charset);
        int i3 = voVar.b + i2;
        voVar.b = i3;
        this.b -= j;
        if (i3 == voVar.c) {
            this.a = voVar.a();
            wo.b(voVar);
        }
        return str;
    }

    @NotNull
    public final String L() {
        return K(this.b, Charsets.UTF_8);
    }

    @NotNull
    public final String M(long j) throws EOFException {
        return K(j, Charsets.UTF_8);
    }

    @NotNull
    public final ByteString N(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        c.d(this.b, 0L, i);
        vo voVar = this.a;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Intrinsics.checkNotNull(voVar);
            int i5 = voVar.c;
            int i6 = voVar.b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            voVar = voVar.f;
        }
        byte[][] bArr = new byte[i4][];
        int[] iArr = new int[i4 * 2];
        vo voVar2 = this.a;
        int i7 = 0;
        while (i2 < i) {
            Intrinsics.checkNotNull(voVar2);
            bArr[i7] = voVar2.a;
            i2 += voVar2.c - voVar2.b;
            iArr[i7] = Math.min(i2, i);
            iArr[i7 + i4] = voVar2.b;
            voVar2.d = true;
            i7++;
            voVar2 = voVar2.f;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    @NotNull
    public final vo O(int i) {
        if (!(i >= 1 && i <= 8192)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        vo voVar = this.a;
        if (voVar == null) {
            vo voVarC = wo.c();
            this.a = voVarC;
            voVarC.g = voVarC;
            voVarC.f = voVarC;
            return voVarC;
        }
        Intrinsics.checkNotNull(voVar);
        vo voVar2 = voVar.g;
        Intrinsics.checkNotNull(voVar2);
        if (voVar2.c + i <= 8192 && voVar2.e) {
            return voVar2;
        }
        vo voVarC2 = wo.c();
        voVar2.b(voVarC2);
        return voVarC2;
    }

    @NotNull
    public final g2 P(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, 0, byteString.size());
        return this;
    }

    @NotNull
    public final g2 Q(@NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        R(source, 0, source.length);
        return this;
    }

    @NotNull
    public final g2 R(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = i2;
        c.d(source.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            vo voVarO = O(1);
            int iMin = Math.min(i3 - i, 8192 - voVarO.c);
            int i4 = i + iMin;
            ArraysKt.copyInto(source, voVarO.a, voVarO.c, i, i4);
            voVarO.c += iMin;
            i = i4;
        }
        this.b += j;
        return this;
    }

    @NotNull
    public final g2 S(int i) {
        vo voVarO = O(1);
        byte[] bArr = voVarO.a;
        int i2 = voVarO.c;
        voVarO.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f0 A[LOOP:0: B:70:0x00ec->B:72:0x00f0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0100  */
    @Override // spi.k2
    @org.jetbrains.annotations.NotNull
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.g2 y(long r13) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.g2.y(long):spi.g2");
    }

    @Override // spi.k2
    @NotNull
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public final g2 o(long j) {
        if (j == 0) {
            S(48);
        } else {
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
            vo voVarO = O(i);
            byte[] bArr = voVarO.a;
            int i2 = voVarO.c;
            for (int i3 = (i2 + i) - 1; i3 >= i2; i3--) {
                bArr[i3] = i2.a[(int) (15 & j)];
                j >>>= 4;
            }
            voVarO.c += i;
            this.b += (long) i;
        }
        return this;
    }

    @NotNull
    public final g2 V(int i) {
        vo voVarO = O(4);
        byte[] bArr = voVarO.a;
        int i2 = voVarO.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        voVarO.c = i5 + 1;
        this.b += 4;
        return this;
    }

    @NotNull
    public final g2 W(int i) {
        vo voVarO = O(2);
        byte[] bArr = voVarO.a;
        int i2 = voVarO.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        voVarO.c = i3 + 1;
        this.b += 2;
        return this;
    }

    @NotNull
    public final g2 X(@NotNull String string, int i, int i2, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(e.c("beginIndex < 0: ", i).toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(d.c("endIndex < beginIndex: ", i2, " < ", i).toString());
        }
        if (!(i2 <= string.length())) {
            StringBuilder sbJ = d.j("endIndex > string.length: ", i2, " > ");
            sbJ.append(string.length());
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            Z(string, i, i2);
            return this;
        }
        String strSubstring = string.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        Objects.requireNonNull(strSubstring, "null cannot be cast to non-null type java.lang.String");
        byte[] bytes = strSubstring.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        R(bytes, 0, bytes.length);
        return this;
    }

    @NotNull
    public final g2 Y(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        Z(string, 0, string.length());
        return this;
    }

    @NotNull
    public final g2 Z(@NotNull String string, int i, int i2) {
        char cCharAt;
        long j;
        long j2;
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(e.c("beginIndex < 0: ", i).toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(d.c("endIndex < beginIndex: ", i2, " < ", i).toString());
        }
        if (!(i2 <= string.length())) {
            StringBuilder sbJ = d.j("endIndex > string.length: ", i2, " > ");
            sbJ.append(string.length());
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        while (i < i2) {
            char cCharAt2 = string.charAt(i);
            if (cCharAt2 < 128) {
                vo voVarO = O(1);
                byte[] bArr = voVarO.a;
                int i3 = voVarO.c - i;
                int iMin = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                bArr[i + i3] = (byte) cCharAt2;
                while (true) {
                    i = i4;
                    if (i >= iMin || (cCharAt = string.charAt(i)) >= 128) {
                        break;
                    }
                    i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt;
                }
                int i5 = voVarO.c;
                int i6 = (i3 + i) - i5;
                voVarO.c = i5 + i6;
                this.b += (long) i6;
            } else {
                if (cCharAt2 < 2048) {
                    vo voVarO2 = O(2);
                    byte[] bArr2 = voVarO2.a;
                    int i7 = voVarO2.c;
                    bArr2[i7] = (byte) ((cCharAt2 >> 6) | 192);
                    bArr2[i7 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    voVarO2.c = i7 + 2;
                    j = this.b;
                    j2 = 2;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    vo voVarO3 = O(3);
                    byte[] bArr3 = voVarO3.a;
                    int i8 = voVarO3.c;
                    bArr3[i8] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i8 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i8 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    voVarO3.c = i8 + 3;
                    j = this.b;
                    j2 = 3;
                } else {
                    int i9 = i + 1;
                    char cCharAt3 = i9 < i2 ? string.charAt(i9) : (char) 0;
                    if (cCharAt2 > 56319 || 56320 > cCharAt3 || 57343 < cCharAt3) {
                        S(63);
                        i = i9;
                    } else {
                        int i10 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        vo voVarO4 = O(4);
                        byte[] bArr4 = voVarO4.a;
                        int i11 = voVarO4.c;
                        bArr4[i11] = (byte) ((i10 >> 18) | 240);
                        bArr4[i11 + 1] = (byte) (((i10 >> 12) & 63) | 128);
                        bArr4[i11 + 2] = (byte) (((i10 >> 6) & 63) | 128);
                        bArr4[i11 + 3] = (byte) ((i10 & 63) | 128);
                        voVarO4.c = i11 + 4;
                        this.b += 4;
                        i += 2;
                    }
                }
                this.b = j + j2;
                i++;
            }
        }
        return this;
    }

    @Override // spi.l2, spi.k2
    @NotNull
    public final g2 a() {
        return this;
    }

    @NotNull
    public final g2 a0(int i) {
        String str;
        long j;
        long j2;
        if (i < 128) {
            S(i);
        } else {
            if (i < 2048) {
                vo voVarO = O(2);
                byte[] bArr = voVarO.a;
                int i2 = voVarO.c;
                bArr[i2] = (byte) ((i >> 6) | 192);
                bArr[i2 + 1] = (byte) ((i & 63) | 128);
                voVarO.c = i2 + 2;
                j = this.b;
                j2 = 2;
            } else if (55296 <= i && 57343 >= i) {
                S(63);
            } else if (i < 65536) {
                vo voVarO2 = O(3);
                byte[] bArr2 = voVarO2.a;
                int i3 = voVarO2.c;
                bArr2[i3] = (byte) ((i >> 12) | 224);
                bArr2[i3 + 1] = (byte) (((i >> 6) & 63) | 128);
                bArr2[i3 + 2] = (byte) ((i & 63) | 128);
                voVarO2.c = i3 + 3;
                j = this.b;
                j2 = 3;
            } else {
                if (i > 1114111) {
                    StringBuilder sbK = e.k("Unexpected code point: 0x");
                    if (i != 0) {
                        char[] cArr = c.a;
                        int i4 = 0;
                        char[] cArr2 = {cArr[(i >> 28) & 15], cArr[(i >> 24) & 15], cArr[(i >> 20) & 15], cArr[(i >> 16) & 15], cArr[(i >> 12) & 15], cArr[(i >> 8) & 15], cArr[(i >> 4) & 15], cArr[i & 15]};
                        while (i4 < 8 && cArr2[i4] == '0') {
                            i4++;
                        }
                        str = new String(cArr2, i4, 8 - i4);
                    } else {
                        str = "0";
                    }
                    sbK.append(str);
                    throw new IllegalArgumentException(sbK.toString());
                }
                vo voVarO3 = O(4);
                byte[] bArr3 = voVarO3.a;
                int i5 = voVarO3.c;
                bArr3[i5] = (byte) ((i >> 18) | 240);
                bArr3[i5 + 1] = (byte) (((i >> 12) & 63) | 128);
                bArr3[i5 + 2] = (byte) (((i >> 6) & 63) | 128);
                bArr3[i5 + 3] = (byte) ((i & 63) | 128);
                voVarO3.c = i5 + 4;
                j = this.b;
                j2 = 4;
            }
            this.b = j + j2;
        }
        return this;
    }

    @Override // spi.l2
    @NotNull
    public final ByteString b(long j) throws EOFException {
        if (!(j >= 0 && j <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(d.d("byteCount: ", j).toString());
        }
        if (this.b < j) {
            throw new EOFException();
        }
        if (j < 4096) {
            return new ByteString(s(j));
        }
        ByteString byteStringN = N((int) j);
        skip(j);
        return byteStringN;
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 c(int i) {
        W(i);
        return this;
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 d(int i) {
        V(i);
        return this;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof g2)) {
                return false;
            }
            long j = this.b;
            g2 g2Var = (g2) obj;
            if (j != g2Var.b) {
                return false;
            }
            if (j != 0) {
                vo voVar = this.a;
                Intrinsics.checkNotNull(voVar);
                vo voVar2 = g2Var.a;
                Intrinsics.checkNotNull(voVar2);
                int i = voVar.b;
                int i2 = voVar2.b;
                long j2 = 0;
                while (j2 < this.b) {
                    long jMin = Math.min(voVar.c - i, voVar2.c - i2);
                    long j3 = 0;
                    while (j3 < jMin) {
                        int i3 = i + 1;
                        int i4 = i2 + 1;
                        if (voVar.a[i] != voVar2.a[i2]) {
                            return false;
                        }
                        j3++;
                        i = i3;
                        i2 = i4;
                    }
                    if (i == voVar.c) {
                        voVar = voVar.f;
                        Intrinsics.checkNotNull(voVar);
                        i = voVar.b;
                    }
                    if (i2 == voVar2.c) {
                        voVar2 = voVar2.f;
                        Intrinsics.checkNotNull(voVar2);
                        i2 = voVar2.b;
                    }
                    j2 += jMin;
                }
            }
        }
        return true;
    }

    @Override // spi.l2
    @NotNull
    public final byte[] f() {
        return s(this.b);
    }

    @Override // spi.k2, spi.rp, java.io.Flushable
    public final void flush() {
    }

    @Override // spi.l2
    public final long g(@NotNull rp sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j = this.b;
        if (j > 0) {
            ((g2) sink).p(this, j);
        }
        return j;
    }

    @Override // spi.l2
    public final boolean h() {
        return this.b == 0;
    }

    public final int hashCode() {
        vo voVar = this.a;
        if (voVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = voVar.c;
            for (int i3 = voVar.b; i3 < i2; i3++) {
                i = (i * 31) + voVar.a[i3];
            }
            voVar = voVar.f;
            Intrinsics.checkNotNull(voVar);
        } while (voVar != this.a);
        return i;
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 i(int i) {
        S(i);
        return this;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // spi.k2
    public final k2 j() {
        return this;
    }

    @Override // spi.l2
    @NotNull
    public final String k(long j) throws EOFException {
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("limit < 0: ", j).toString());
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        byte b = (byte) 10;
        long jG = G(b, 0L, j2);
        if (jG != -1) {
            return i2.a(this, jG);
        }
        if (j2 < this.b && F(j2 - 1) == ((byte) 13) && F(j2) == b) {
            return i2.a(this, j2);
        }
        g2 g2Var = new g2();
        E(g2Var, 0L, Math.min(32, this.b));
        StringBuilder sbK = e.k("\\n not found: limit=");
        sbK.append(Math.min(this.b, j));
        sbK.append(" content=");
        sbK.append(g2Var.q().hex());
        sbK.append(Typography.ellipsis);
        throw new EOFException(sbK.toString());
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 l(String str) {
        Y(str);
        return this;
    }

    @Override // spi.l2
    @NotNull
    public final String m(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return K(this.b, charset);
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 n(byte[] bArr, int i, int i2) {
        R(bArr, i, i2);
        return this;
    }

    @Override // spi.rp
    public final void p(@NotNull g2 source, long j) {
        int i;
        vo voVar;
        vo voVarC;
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(source != this)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        c.d(source.b, 0L, j);
        while (j > 0) {
            vo voVar2 = source.a;
            Intrinsics.checkNotNull(voVar2);
            int i2 = voVar2.c;
            Intrinsics.checkNotNull(source.a);
            if (j < i2 - r3.b) {
                vo voVar3 = this.a;
                if (voVar3 != null) {
                    Intrinsics.checkNotNull(voVar3);
                    voVar = voVar3.g;
                } else {
                    voVar = null;
                }
                if (voVar != null && voVar.e) {
                    if ((((long) voVar.c) + j) - ((long) (voVar.d ? 0 : voVar.b)) <= 8192) {
                        vo voVar4 = source.a;
                        Intrinsics.checkNotNull(voVar4);
                        voVar4.d(voVar, (int) j);
                        source.b -= j;
                        this.b += j;
                        return;
                    }
                }
                vo voVar5 = source.a;
                Intrinsics.checkNotNull(voVar5);
                int i3 = (int) j;
                Objects.requireNonNull(voVar5);
                if (!(i3 > 0 && i3 <= voVar5.c - voVar5.b)) {
                    throw new IllegalArgumentException("byteCount out of range".toString());
                }
                if (i3 >= 1024) {
                    voVarC = voVar5.c();
                } else {
                    voVarC = wo.c();
                    byte[] bArr = voVar5.a;
                    byte[] bArr2 = voVarC.a;
                    int i4 = voVar5.b;
                    ArraysKt___ArraysJvmKt.copyInto$default(bArr, bArr2, 0, i4, i4 + i3, 2, (Object) null);
                }
                voVarC.c = voVarC.b + i3;
                voVar5.b += i3;
                vo voVar6 = voVar5.g;
                Intrinsics.checkNotNull(voVar6);
                voVar6.b(voVarC);
                source.a = voVarC;
            }
            vo voVar7 = source.a;
            Intrinsics.checkNotNull(voVar7);
            long j2 = voVar7.c - voVar7.b;
            source.a = voVar7.a();
            vo voVar8 = this.a;
            if (voVar8 == null) {
                this.a = voVar7;
                voVar7.g = voVar7;
                voVar7.f = voVar7;
            } else {
                Intrinsics.checkNotNull(voVar8);
                vo voVar9 = voVar8.g;
                Intrinsics.checkNotNull(voVar9);
                voVar9.b(voVar7);
                vo voVar10 = voVar7.g;
                if (!(voVar10 != voVar7)) {
                    throw new IllegalStateException("cannot compact".toString());
                }
                Intrinsics.checkNotNull(voVar10);
                if (voVar10.e) {
                    int i5 = voVar7.c - voVar7.b;
                    vo voVar11 = voVar7.g;
                    Intrinsics.checkNotNull(voVar11);
                    int i6 = 8192 - voVar11.c;
                    vo voVar12 = voVar7.g;
                    Intrinsics.checkNotNull(voVar12);
                    if (voVar12.d) {
                        i = 0;
                    } else {
                        vo voVar13 = voVar7.g;
                        Intrinsics.checkNotNull(voVar13);
                        i = voVar13.b;
                    }
                    if (i5 <= i6 + i) {
                        vo voVar14 = voVar7.g;
                        Intrinsics.checkNotNull(voVar14);
                        voVar7.d(voVar14, i5);
                        voVar7.a();
                        wo.b(voVar7);
                    }
                }
            }
            source.b -= j2;
            this.b += j2;
            j -= j2;
        }
    }

    @Override // spi.l2
    @NotNull
    public final ByteString q() {
        return b(this.b);
    }

    @Override // spi.l2
    @NotNull
    public final String r() throws EOFException {
        return k(Long.MAX_VALUE);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(@NotNull ByteBuffer sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        vo voVar = this.a;
        if (voVar == null) {
            return -1;
        }
        int iMin = Math.min(sink.remaining(), voVar.c - voVar.b);
        sink.put(voVar.a, voVar.b, iMin);
        int i = voVar.b + iMin;
        voVar.b = i;
        this.b -= (long) iMin;
        if (i == voVar.c) {
            this.a = voVar.a();
            wo.b(voVar);
        }
        return iMin;
    }

    public final int read(@NotNull byte[] sink, int i, int i2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        c.d(sink.length, i, i2);
        vo voVar = this.a;
        if (voVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, voVar.c - voVar.b);
        byte[] bArr = voVar.a;
        int i3 = voVar.b;
        ArraysKt.copyInto(bArr, sink, i, i3, i3 + iMin);
        int i4 = voVar.b + iMin;
        voVar.b = i4;
        this.b -= (long) iMin;
        if (i4 != voVar.c) {
            return iMin;
        }
        this.a = voVar.a();
        wo.b(voVar);
        return iMin;
    }

    @Override // spi.l2
    public final byte readByte() throws EOFException {
        if (this.b == 0) {
            throw new EOFException();
        }
        vo voVar = this.a;
        Intrinsics.checkNotNull(voVar);
        int i = voVar.b;
        int i2 = voVar.c;
        int i3 = i + 1;
        byte b = voVar.a[i];
        this.b--;
        if (i3 == i2) {
            this.a = voVar.a();
            wo.b(voVar);
        } else {
            voVar.b = i3;
        }
        return b;
    }

    @Override // spi.l2
    public final int readInt() throws EOFException {
        if (this.b < 4) {
            throw new EOFException();
        }
        vo voVar = this.a;
        Intrinsics.checkNotNull(voVar);
        int i = voVar.b;
        int i2 = voVar.c;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = voVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.b -= 4;
        if (i8 == i2) {
            this.a = voVar.a();
            wo.b(voVar);
        } else {
            voVar.b = i8;
        }
        return i9;
    }

    @Override // spi.l2
    public final short readShort() throws EOFException {
        if (this.b < 2) {
            throw new EOFException();
        }
        vo voVar = this.a;
        Intrinsics.checkNotNull(voVar);
        int i = voVar.b;
        int i2 = voVar.c;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = voVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.b -= 2;
        if (i4 == i2) {
            this.a = voVar.a();
            wo.b(voVar);
        } else {
            voVar.b = i4;
        }
        return (short) i5;
    }

    @Override // spi.l2
    public final boolean request(long j) {
        return this.b >= Long.MAX_VALUE;
    }

    @Override // spi.l2
    @NotNull
    public final byte[] s(long j) throws EOFException {
        int i = 0;
        if (!(j >= 0 && j <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(d.d("byteCount: ", j).toString());
        }
        if (this.b < j) {
            throw new EOFException();
        }
        int i2 = (int) j;
        byte[] sink = new byte[i2];
        Intrinsics.checkNotNullParameter(sink, "sink");
        while (i < i2) {
            int i3 = read(sink, i, i2 - i);
            if (i3 == -1) {
                throw new EOFException();
            }
            i += i3;
        }
        return sink;
    }

    @Override // spi.l2
    public final void skip(long j) throws EOFException {
        while (j > 0) {
            vo voVar = this.a;
            if (voVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, voVar.c - voVar.b);
            long j2 = iMin;
            this.b -= j2;
            j -= j2;
            int i = voVar.b + iMin;
            voVar.b = i;
            if (i == voVar.c) {
                this.a = voVar.a();
                wo.b(voVar);
            }
        }
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 t(byte[] bArr) {
        Q(bArr);
        return this;
    }

    @Override // spi.vr
    @NotNull
    public final dt timeout() {
        return dt.d;
    }

    @NotNull
    public final String toString() {
        long j = this.b;
        if (j <= ((long) Integer.MAX_VALUE)) {
            return N((int) j).toString();
        }
        StringBuilder sbK = e.k("size > Int.MAX_VALUE: ");
        sbK.append(this.b);
        throw new IllegalStateException(sbK.toString().toString());
    }

    @Override // spi.k2
    public final /* bridge */ /* synthetic */ k2 u(ByteString byteString) {
        P(byteString);
        return this;
    }

    @Override // spi.l2
    public final int v(@NotNull kk options) throws EOFException {
        Intrinsics.checkNotNullParameter(options, "options");
        int iB = i2.b(this, options, false);
        if (iB == -1) {
            return -1;
        }
        skip(options.a[iB].size());
        return iB;
    }

    @Override // spi.l2
    public final void w(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(@NotNull ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        int iRemaining = source.remaining();
        int i = iRemaining;
        while (i > 0) {
            vo voVarO = O(1);
            int iMin = Math.min(i, 8192 - voVarO.c);
            source.get(voVarO.a, voVarO.c, iMin);
            i -= iMin;
            voVarO.c += iMin;
        }
        this.b += (long) iRemaining;
        return iRemaining;
    }

    @Override // spi.k2
    public final long x(@NotNull vr source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long j2 = source.read(this, 8192);
            if (j2 == -1) {
                return j;
            }
            j += j2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a2 A[EDGE_INSN: B:43:0x00a2->B:37:0x00a2 BREAK  A[LOOP:0: B:5:0x000b->B:45:?], SYNTHETIC] */
    @Override // spi.l2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long z() throws java.io.EOFException {
        /*
            r14 = this;
            long r0 = r14.b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto La9
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            spi.vo r6 = r14.a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            byte[] r7 = r6.a
            int r8 = r6.b
            int r9 = r6.c
        L16:
            if (r8 >= r9) goto L8e
            r10 = r7[r8]
            r11 = 48
            byte r11 = (byte) r11
            if (r10 < r11) goto L27
            r12 = 57
            byte r12 = (byte) r12
            if (r10 > r12) goto L27
            int r11 = r10 - r11
            goto L40
        L27:
            r11 = 97
            byte r11 = (byte) r11
            if (r10 < r11) goto L32
            r12 = 102(0x66, float:1.43E-43)
            byte r12 = (byte) r12
            if (r10 > r12) goto L32
            goto L3c
        L32:
            r11 = 65
            byte r11 = (byte) r11
            if (r10 < r11) goto L73
            r12 = 70
            byte r12 = (byte) r12
            if (r10 > r12) goto L73
        L3c:
            int r11 = r10 - r11
            int r11 = r11 + 10
        L40:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L50
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L16
        L50:
            spi.g2 r0 = new spi.g2
            r0.<init>()
            spi.g2 r0 = r0.o(r4)
            r0.S(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Number too large: "
            java.lang.StringBuilder r2 = spi.e.k(r2)
            java.lang.String r0 = r0.L()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L73:
            if (r0 == 0) goto L77
            r1 = 1
            goto L8e
        L77:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.StringBuilder r1 = spi.e.k(r1)
            java.lang.String r2 = spi.c.h(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L8e:
            if (r8 != r9) goto L9a
            spi.vo r7 = r6.a()
            r14.a = r7
            spi.wo.b(r6)
            goto L9c
        L9a:
            r6.b = r8
        L9c:
            if (r1 != 0) goto La2
            spi.vo r6 = r14.a
            if (r6 != 0) goto Lb
        La2:
            long r1 = r14.b
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.b = r1
            return r4
        La9:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.g2.z():long");
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (j >= 0) {
            long j2 = this.b;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            sink.p(this, j);
            return j;
        }
        throw new IllegalArgumentException(d.d("byteCount < 0: ", j).toString());
    }
}

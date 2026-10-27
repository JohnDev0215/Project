package com.google.protobuf;

import com.google.protobuf.d0;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import defpackage.dj3;
import defpackage.lk;
import defpackage.sk3;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: CodedInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public int a;
    public final int b = 100;
    public h c;

    /* JADX INFO: compiled from: CodedInputStream.java */
    public static final class a extends g {
        public final byte[] d;
        public int e;
        public int f;
        public int g;
        public final int h;
        public int i;
        public int j = Integer.MAX_VALUE;

        public a(byte[] bArr, int i, int i2, boolean z) {
            this.d = bArr;
            this.e = i2 + i;
            this.g = i;
            this.h = i;
        }

        @Override // com.google.protobuf.g
        public final long A() {
            return K();
        }

        @Override // com.google.protobuf.g
        public final int B() {
            return g.c(y());
        }

        @Override // com.google.protobuf.g
        public final long C() {
            return g.d(L());
        }

        @Override // com.google.protobuf.g
        public final String D() throws d0 {
            int iY = y();
            if (iY > 0) {
                int i = this.e;
                int i2 = this.g;
                if (iY <= i - i2) {
                    String str = new String(this.d, i2, iY, c0.a);
                    this.g += iY;
                    return str;
                }
            }
            if (iY == 0) {
                return "";
            }
            if (iY < 0) {
                throw d0.f();
            }
            throw d0.k();
        }

        @Override // com.google.protobuf.g
        public final String E() throws d0 {
            int iY = y();
            if (iY > 0) {
                int i = this.e;
                int i2 = this.g;
                if (iY <= i - i2) {
                    String strB = sk3.a.b(this.d, i2, iY);
                    this.g += iY;
                    return strB;
                }
            }
            if (iY == 0) {
                return "";
            }
            if (iY <= 0) {
                throw d0.f();
            }
            throw d0.k();
        }

        @Override // com.google.protobuf.g
        public final int F() throws d0 {
            if (g()) {
                this.i = 0;
                return 0;
            }
            int iY = y();
            this.i = iY;
            if ((iY >>> 3) != 0) {
                return iY;
            }
            throw d0.b();
        }

        @Override // com.google.protobuf.g
        public final int G() {
            return y();
        }

        @Override // com.google.protobuf.g
        public final long H() {
            return L();
        }

        @Override // com.google.protobuf.g
        public final boolean I(int i) throws d0 {
            int iF;
            int i2 = i & 7;
            int i3 = 0;
            if (i2 == 0) {
                int i4 = this.e - this.g;
                byte[] bArr = this.d;
                if (i4 >= 10) {
                    while (i3 < 10) {
                        int i5 = this.g;
                        this.g = i5 + 1;
                        if (bArr[i5] < 0) {
                            i3++;
                        }
                    }
                    throw d0.e();
                }
                while (i3 < 10) {
                    int i6 = this.g;
                    if (i6 == this.e) {
                        throw d0.k();
                    }
                    this.g = i6 + 1;
                    if (bArr[i6] < 0) {
                        i3++;
                    }
                }
                throw d0.e();
                return true;
            }
            if (i2 == 1) {
                N(8);
                return true;
            }
            if (i2 == 2) {
                N(y());
                return true;
            }
            if (i2 == 3) {
                do {
                    iF = F();
                    if (iF == 0) {
                        break;
                    }
                } while (I(iF));
                a(((i >>> 3) << 3) | 4);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                N(4);
                return true;
            }
            int i7 = d0.f;
            throw new d0.a();
        }

        public final int J() throws d0 {
            int i = this.g;
            if (this.e - i < 4) {
                throw d0.k();
            }
            this.g = i + 4;
            byte[] bArr = this.d;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        public final long K() throws d0 {
            int i = this.g;
            if (this.e - i < 8) {
                throw d0.k();
            }
            this.g = i + 8;
            byte[] bArr = this.d;
            return ((((long) bArr[i + 1]) & 255) << 8) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
        }

        public final long L() {
            long j;
            long j2;
            long j3;
            int i;
            int i2 = this.g;
            int i3 = this.e;
            if (i3 != i2) {
                int i4 = i2 + 1;
                byte[] bArr = this.d;
                byte b = bArr[i2];
                if (b >= 0) {
                    this.g = i4;
                    return b;
                }
                if (i3 - i4 >= 9) {
                    int i5 = i4 + 1;
                    int i6 = b ^ (bArr[i4] << 7);
                    if (i6 >= 0) {
                        int i7 = i5 + 1;
                        int i8 = i6 ^ (bArr[i5] << 14);
                        if (i8 >= 0) {
                            j = i8 ^ 16256;
                        } else {
                            i5 = i7 + 1;
                            int i9 = i8 ^ (bArr[i7] << 21);
                            if (i9 >= 0) {
                                long j4 = i9;
                                int i10 = i5 + 1;
                                long j5 = (((long) bArr[i5]) << 28) ^ j4;
                                if (j5 >= 0) {
                                    j2 = j5 ^ 266354560;
                                    i5 = i10;
                                } else {
                                    int i11 = i10 + 1;
                                    long j6 = j5 ^ (((long) bArr[i10]) << 35);
                                    if (j6 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        i7 = i11 + 1;
                                        long j7 = j6 ^ (((long) bArr[i11]) << 42);
                                        if (j7 >= 0) {
                                            j = j7 ^ 4363953127296L;
                                        } else {
                                            i11 = i7 + 1;
                                            j6 = j7 ^ (((long) bArr[i7]) << 49);
                                            if (j6 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                i7 = i11 + 1;
                                                j = (j6 ^ (((long) bArr[i11]) << 56)) ^ 71499008037633920L;
                                                if (j < 0) {
                                                    i11 = i7 + 1;
                                                    if (bArr[i7] >= 0) {
                                                        j2 = j;
                                                        i5 = i11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    j2 = j3 ^ j6;
                                    i5 = i11;
                                }
                                this.g = i5;
                                return j2;
                            }
                            i = i9 ^ (-2080896);
                        }
                        i5 = i7;
                        j2 = j;
                        this.g = i5;
                        return j2;
                    }
                    i = i6 ^ (-128);
                    j2 = i;
                    this.g = i5;
                    return j2;
                }
            }
            return M();
        }

        public final long M() throws d0 {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                int i2 = this.g;
                if (i2 == this.e) {
                    throw d0.k();
                }
                this.g = i2 + 1;
                byte b = this.d[i2];
                j |= ((long) (b & 127)) << i;
                if ((b & 128) == 0) {
                    return j;
                }
            }
            throw d0.e();
        }

        public final void N(int i) throws d0 {
            if (i >= 0) {
                int i2 = this.e;
                int i3 = this.g;
                if (i <= i2 - i3) {
                    this.g = i3 + i;
                    return;
                }
            }
            if (i >= 0) {
                throw d0.k();
            }
            throw d0.f();
        }

        @Override // com.google.protobuf.g
        public final void a(int i) throws d0 {
            if (this.i != i) {
                throw d0.a();
            }
        }

        @Override // com.google.protobuf.g
        public final int e() {
            int i = this.j;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - (this.g - this.h);
        }

        @Override // com.google.protobuf.g
        public final int f() {
            return this.g - this.h;
        }

        @Override // com.google.protobuf.g
        public final boolean g() {
            return this.g == this.e;
        }

        @Override // com.google.protobuf.g
        public final void j(int i) {
            this.j = i;
            int i2 = this.e + this.f;
            this.e = i2;
            int i3 = i2 - this.h;
            if (i3 <= i) {
                this.f = 0;
                return;
            }
            int i4 = i3 - i;
            this.f = i4;
            this.e = i2 - i4;
        }

        @Override // com.google.protobuf.g
        public final int k(int i) throws d0 {
            if (i < 0) {
                throw d0.f();
            }
            int i2 = this.g;
            int i3 = this.h;
            int i4 = (i2 - i3) + i;
            if (i4 < 0) {
                throw d0.h();
            }
            int i5 = this.j;
            if (i4 > i5) {
                throw d0.k();
            }
            this.j = i4;
            int i6 = this.e + this.f;
            this.e = i6;
            int i7 = i6 - i3;
            if (i7 > i4) {
                int i8 = i7 - i4;
                this.f = i8;
                this.e = i6 - i8;
            } else {
                this.f = 0;
            }
            return i5;
        }

        @Override // com.google.protobuf.g
        public final boolean l() {
            return L() != 0;
        }

        @Override // com.google.protobuf.g
        public final lk.f m() throws d0 {
            int iY = y();
            if (iY > 0) {
                int i = this.e;
                int i2 = this.g;
                if (iY <= i - i2) {
                    lk.f fVarD = lk.d(this.d, i2, iY);
                    this.g += iY;
                    return fVarD;
                }
            }
            if (iY == 0) {
                return lk.f;
            }
            byte[] bArrX = x(iY);
            lk.f fVar = lk.f;
            return new lk.f(bArrX);
        }

        @Override // com.google.protobuf.g
        public final double n() {
            return Double.longBitsToDouble(K());
        }

        @Override // com.google.protobuf.g
        public final int o() {
            return y();
        }

        @Override // com.google.protobuf.g
        public final int p() {
            return J();
        }

        @Override // com.google.protobuf.g
        public final long q() {
            return K();
        }

        @Override // com.google.protobuf.g
        public final float r() {
            return Float.intBitsToFloat(J());
        }

        @Override // com.google.protobuf.g
        public final void s(int i, r0.a aVar, s sVar) throws d0 {
            b();
            this.a++;
            aVar.L(this, sVar);
            a((i << 3) | 4);
            this.a--;
        }

        @Override // com.google.protobuf.g
        public final int t() {
            return y();
        }

        @Override // com.google.protobuf.g
        public final long u() {
            return L();
        }

        @Override // com.google.protobuf.g
        public final r0 v(c cVar, s sVar) throws d0 {
            int iY = y();
            b();
            int iK = k(iY);
            this.a++;
            r0 r0Var = (r0) cVar.b(this, sVar);
            a(0);
            this.a--;
            if (e() != 0) {
                throw d0.k();
            }
            j(iK);
            return r0Var;
        }

        @Override // com.google.protobuf.g
        public final void w(q0.a aVar, s sVar) throws d0 {
            int iY = y();
            b();
            int iK = k(iY);
            this.a++;
            aVar.L(this, sVar);
            a(0);
            this.a--;
            if (e() != 0) {
                throw d0.k();
            }
            j(iK);
        }

        @Override // com.google.protobuf.g
        public final byte[] x(int i) throws d0 {
            if (i > 0) {
                int i2 = this.e;
                int i3 = this.g;
                if (i <= i2 - i3) {
                    int i4 = i + i3;
                    this.g = i4;
                    return Arrays.copyOfRange(this.d, i3, i4);
                }
            }
            if (i > 0) {
                throw d0.k();
            }
            if (i == 0) {
                return c0.c;
            }
            throw d0.f();
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r3[r2] < 0) goto L34;
         */
        @Override // com.google.protobuf.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int y() {
            /*
                r5 = this;
                int r0 = r5.g
                int r1 = r5.e
                if (r1 != r0) goto L7
                goto L6a
            L7:
                int r2 = r0 + 1
                byte[] r3 = r5.d
                r0 = r3[r0]
                if (r0 < 0) goto L12
                r5.g = r2
                return r0
            L12:
                int r1 = r1 - r2
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r2 + 1
                r2 = r3[r2]
                int r2 = r2 << 7
                r0 = r0 ^ r2
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r2 = r1 + 1
                r1 = r3[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r2
                goto L70
            L31:
                int r1 = r2 + 1
                r2 = r3[r2]
                int r2 = r2 << 21
                r0 = r0 ^ r2
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r2 = r1 + 1
                r1 = r3[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r2 + 1
                r2 = r3[r2]
                if (r2 >= 0) goto L70
                int r2 = r1 + 1
                r1 = r3[r1]
                if (r1 >= 0) goto L2f
                int r1 = r2 + 1
                r2 = r3[r2]
                if (r2 >= 0) goto L70
                int r2 = r1 + 1
                r1 = r3[r1]
                if (r1 >= 0) goto L2f
                int r1 = r2 + 1
                r2 = r3[r2]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.M()
                int r5 = (int) r0
                return r5
            L70:
                r5.g = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.g.a.y():int");
        }

        @Override // com.google.protobuf.g
        public final int z() {
            return J();
        }
    }

    /* JADX INFO: compiled from: CodedInputStream.java */
    public static final class b extends g {
        public final ByteBuffer d;
        public final long e;
        public long f;
        public long g;
        public final long h;
        public int i;
        public int j;
        public int k = Integer.MAX_VALUE;

        public b(ByteBuffer byteBuffer, boolean z) {
            this.d = byteBuffer;
            long jI = dj3.c.i(dj3.g, byteBuffer);
            this.e = jI;
            this.f = ((long) byteBuffer.limit()) + jI;
            long jPosition = jI + ((long) byteBuffer.position());
            this.g = jPosition;
            this.h = jPosition;
        }

        @Override // com.google.protobuf.g
        public final long A() {
            return K();
        }

        @Override // com.google.protobuf.g
        public final int B() {
            return g.c(y());
        }

        @Override // com.google.protobuf.g
        public final long C() {
            return g.d(L());
        }

        @Override // com.google.protobuf.g
        public final String D() throws d0 {
            int iY = y();
            if (iY <= 0 || iY > O()) {
                if (iY == 0) {
                    return "";
                }
                if (iY < 0) {
                    throw d0.f();
                }
                throw d0.k();
            }
            byte[] bArr = new byte[iY];
            long j = iY;
            dj3.e(this.g, bArr, 0L, j);
            String str = new String(bArr, c0.a);
            this.g += j;
            return str;
        }

        @Override // com.google.protobuf.g
        public final String E() throws d0 {
            int iY = y();
            if (iY > 0 && iY <= O()) {
                String strB = sk3.b(this.d, (int) (this.g - this.e), iY);
                this.g += (long) iY;
                return strB;
            }
            if (iY == 0) {
                return "";
            }
            if (iY <= 0) {
                throw d0.f();
            }
            throw d0.k();
        }

        @Override // com.google.protobuf.g
        public final int F() throws d0 {
            if (g()) {
                this.j = 0;
                return 0;
            }
            int iY = y();
            this.j = iY;
            if ((iY >>> 3) != 0) {
                return iY;
            }
            throw d0.b();
        }

        @Override // com.google.protobuf.g
        public final int G() {
            return y();
        }

        @Override // com.google.protobuf.g
        public final long H() {
            return L();
        }

        @Override // com.google.protobuf.g
        public final boolean I(int i) throws d0 {
            int iF;
            int i2 = i & 7;
            int i3 = 0;
            if (i2 == 0) {
                if (O() >= 10) {
                    while (i3 < 10) {
                        long j = this.g;
                        this.g = j + 1;
                        if (dj3.h(j) < 0) {
                            i3++;
                        }
                    }
                    throw d0.e();
                }
                while (i3 < 10) {
                    long j2 = this.g;
                    if (j2 == this.f) {
                        throw d0.k();
                    }
                    this.g = j2 + 1;
                    if (dj3.h(j2) < 0) {
                        i3++;
                    }
                }
                throw d0.e();
                return true;
            }
            if (i2 == 1) {
                P(8);
                return true;
            }
            if (i2 == 2) {
                P(y());
                return true;
            }
            if (i2 == 3) {
                do {
                    iF = F();
                    if (iF == 0) {
                        break;
                    }
                } while (I(iF));
                a(((i >>> 3) << 3) | 4);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                P(4);
                return true;
            }
            int i4 = d0.f;
            throw new d0.a();
        }

        public final int J() throws d0 {
            long j = this.g;
            if (this.f - j < 4) {
                throw d0.k();
            }
            this.g = 4 + j;
            return (dj3.h(j) & 255) | ((dj3.h(1 + j) & 255) << 8) | ((dj3.h(2 + j) & 255) << 16) | ((dj3.h(j + 3) & 255) << 24);
        }

        public final long K() throws d0 {
            long j = this.g;
            if (this.f - j < 8) {
                throw d0.k();
            }
            this.g = 8 + j;
            return ((((long) dj3.h(j + 7)) & 255) << 56) | (((long) dj3.h(j)) & 255) | ((((long) dj3.h(1 + j)) & 255) << 8) | ((((long) dj3.h(2 + j)) & 255) << 16) | ((((long) dj3.h(3 + j)) & 255) << 24) | ((((long) dj3.h(4 + j)) & 255) << 32) | ((((long) dj3.h(5 + j)) & 255) << 40) | ((((long) dj3.h(6 + j)) & 255) << 48);
        }

        public final long L() {
            long jH;
            long j;
            long j2;
            int i;
            long j3 = this.g;
            if (this.f != j3) {
                long j4 = j3 + 1;
                byte bH = dj3.h(j3);
                if (bH >= 0) {
                    this.g = j4;
                    return bH;
                }
                if (this.f - j4 >= 9) {
                    long j5 = j4 + 1;
                    int iH = bH ^ (dj3.h(j4) << 7);
                    if (iH >= 0) {
                        long j6 = j5 + 1;
                        int iH2 = iH ^ (dj3.h(j5) << 14);
                        if (iH2 >= 0) {
                            jH = iH2 ^ 16256;
                        } else {
                            j5 = j6 + 1;
                            int iH3 = iH2 ^ (dj3.h(j6) << 21);
                            if (iH3 < 0) {
                                i = iH3 ^ (-2080896);
                            } else {
                                j6 = j5 + 1;
                                long jH2 = ((long) iH3) ^ (((long) dj3.h(j5)) << 28);
                                if (jH2 < 0) {
                                    long j7 = j6 + 1;
                                    long jH3 = jH2 ^ (((long) dj3.h(j6)) << 35);
                                    if (jH3 < 0) {
                                        j = -34093383808L;
                                    } else {
                                        j6 = j7 + 1;
                                        jH2 = jH3 ^ (((long) dj3.h(j7)) << 42);
                                        if (jH2 >= 0) {
                                            j2 = 4363953127296L;
                                        } else {
                                            j7 = j6 + 1;
                                            jH3 = jH2 ^ (((long) dj3.h(j6)) << 49);
                                            if (jH3 >= 0) {
                                                j6 = j7 + 1;
                                                jH = (jH3 ^ (((long) dj3.h(j7)) << 56)) ^ 71499008037633920L;
                                                if (jH < 0) {
                                                    long j8 = 1 + j6;
                                                    if (dj3.h(j6) >= 0) {
                                                        j5 = j8;
                                                    }
                                                }
                                                this.g = j5;
                                                return jH;
                                            }
                                            j = -558586000294016L;
                                        }
                                    }
                                    jH = jH3 ^ j;
                                    j5 = j7;
                                    this.g = j5;
                                    return jH;
                                }
                                j2 = 266354560;
                                jH = jH2 ^ j2;
                            }
                        }
                        j5 = j6;
                        this.g = j5;
                        return jH;
                    }
                    i = iH ^ (-128);
                    jH = i;
                    this.g = j5;
                    return jH;
                }
            }
            return M();
        }

        public final long M() throws d0 {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                long j2 = this.g;
                if (j2 == this.f) {
                    throw d0.k();
                }
                this.g = 1 + j2;
                byte bH = dj3.h(j2);
                j |= ((long) (bH & 127)) << i;
                if ((bH & 128) == 0) {
                    return j;
                }
            }
            throw d0.e();
        }

        public final void N() {
            long j = this.f + ((long) this.i);
            this.f = j;
            int i = (int) (j - this.h);
            int i2 = this.k;
            if (i <= i2) {
                this.i = 0;
                return;
            }
            int i3 = i - i2;
            this.i = i3;
            this.f = j - ((long) i3);
        }

        public final int O() {
            return (int) (this.f - this.g);
        }

        public final void P(int i) throws d0 {
            if (i >= 0 && i <= O()) {
                this.g += (long) i;
            } else {
                if (i >= 0) {
                    throw d0.k();
                }
                throw d0.f();
            }
        }

        @Override // com.google.protobuf.g
        public final void a(int i) throws d0 {
            if (this.j != i) {
                throw d0.a();
            }
        }

        @Override // com.google.protobuf.g
        public final int e() {
            int i = this.k;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - f();
        }

        @Override // com.google.protobuf.g
        public final int f() {
            return (int) (this.g - this.h);
        }

        @Override // com.google.protobuf.g
        public final boolean g() {
            return this.g == this.f;
        }

        @Override // com.google.protobuf.g
        public final void j(int i) {
            this.k = i;
            N();
        }

        @Override // com.google.protobuf.g
        public final int k(int i) throws d0 {
            if (i < 0) {
                throw d0.f();
            }
            int iF = f() + i;
            int i2 = this.k;
            if (iF > i2) {
                throw d0.k();
            }
            this.k = iF;
            N();
            return i2;
        }

        @Override // com.google.protobuf.g
        public final boolean l() {
            return L() != 0;
        }

        @Override // com.google.protobuf.g
        public final lk.f m() throws d0 {
            int iY = y();
            if (iY <= 0 || iY > O()) {
                if (iY == 0) {
                    return lk.f;
                }
                if (iY < 0) {
                    throw d0.f();
                }
                throw d0.k();
            }
            byte[] bArr = new byte[iY];
            long j = iY;
            dj3.e(this.g, bArr, 0L, j);
            this.g += j;
            lk.f fVar = lk.f;
            return new lk.f(bArr);
        }

        @Override // com.google.protobuf.g
        public final double n() {
            return Double.longBitsToDouble(K());
        }

        @Override // com.google.protobuf.g
        public final int o() {
            return y();
        }

        @Override // com.google.protobuf.g
        public final int p() {
            return J();
        }

        @Override // com.google.protobuf.g
        public final long q() {
            return K();
        }

        @Override // com.google.protobuf.g
        public final float r() {
            return Float.intBitsToFloat(J());
        }

        @Override // com.google.protobuf.g
        public final void s(int i, r0.a aVar, s sVar) throws d0 {
            b();
            this.a++;
            aVar.L(this, sVar);
            a((i << 3) | 4);
            this.a--;
        }

        @Override // com.google.protobuf.g
        public final int t() {
            return y();
        }

        @Override // com.google.protobuf.g
        public final long u() {
            return L();
        }

        @Override // com.google.protobuf.g
        public final r0 v(c cVar, s sVar) throws d0 {
            int iY = y();
            b();
            int iK = k(iY);
            this.a++;
            r0 r0Var = (r0) cVar.b(this, sVar);
            a(0);
            this.a--;
            if (e() != 0) {
                throw d0.k();
            }
            j(iK);
            return r0Var;
        }

        @Override // com.google.protobuf.g
        public final void w(q0.a aVar, s sVar) throws d0 {
            int iY = y();
            b();
            int iK = k(iY);
            this.a++;
            aVar.L(this, sVar);
            a(0);
            this.a--;
            if (e() != 0) {
                throw d0.k();
            }
            j(iK);
        }

        @Override // com.google.protobuf.g
        public final byte[] x(int i) throws d0 {
            if (i < 0 || i > O()) {
                if (i > 0) {
                    throw d0.k();
                }
                if (i == 0) {
                    return c0.c;
                }
                throw d0.f();
            }
            byte[] bArr = new byte[i];
            long j = this.g;
            long j2 = i;
            long j3 = j + j2;
            ByteBuffer byteBuffer = this.d;
            int iPosition = byteBuffer.position();
            int iLimit = byteBuffer.limit();
            long j4 = this.e;
            try {
                try {
                    byteBuffer.position((int) (j - j4));
                    byteBuffer.limit((int) (j3 - j4));
                    ByteBuffer byteBufferSlice = byteBuffer.slice();
                    byteBuffer.position(iPosition);
                    byteBuffer.limit(iLimit);
                    byteBufferSlice.get(bArr);
                    this.g += j2;
                    return bArr;
                } catch (IllegalArgumentException e) {
                    d0 d0VarK = d0.k();
                    d0VarK.initCause(e);
                    throw d0VarK;
                }
            } catch (Throwable th) {
                byteBuffer.position(iPosition);
                byteBuffer.limit(iLimit);
                throw th;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        
            if (defpackage.dj3.h(r4) < 0) goto L34;
         */
        @Override // com.google.protobuf.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int y() {
            /*
                r10 = this;
                long r0 = r10.g
                long r2 = r10.f
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L85
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = defpackage.dj3.h(r0)
                if (r0 < 0) goto L17
                r10.g = r4
                return r0
            L17:
                long r6 = r10.f
                long r6 = r6 - r4
                r8 = 9
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L21
                goto L85
            L21:
                long r6 = r4 + r2
                byte r1 = defpackage.dj3.h(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L2f
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L8b
            L2f:
                long r4 = r6 + r2
                byte r1 = defpackage.dj3.h(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3e
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L3c:
                r6 = r4
                goto L8b
            L3e:
                long r6 = r4 + r2
                byte r1 = defpackage.dj3.h(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L4e
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L8b
            L4e:
                long r4 = r6 + r2
                byte r1 = defpackage.dj3.h(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = defpackage.dj3.h(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = defpackage.dj3.h(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = defpackage.dj3.h(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = defpackage.dj3.h(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = defpackage.dj3.h(r4)
                if (r1 >= 0) goto L8b
            L85:
                long r0 = r10.M()
                int r10 = (int) r0
                return r10
            L8b:
                r10.g = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.g.b.y():int");
        }

        @Override // com.google.protobuf.g
        public final int z() {
            return J();
        }
    }

    public static int c(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long d(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static a h(byte[] bArr, int i, int i2, boolean z) {
        a aVar = new a(bArr, i, i2, z);
        try {
            aVar.k(i2);
            return aVar;
        } catch (d0 e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static g i(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return h(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && dj3.d) {
            return new b(byteBuffer, z);
        }
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.duplicate().get(bArr);
        return h(bArr, 0, iRemaining, true);
    }

    public abstract long A();

    public abstract int B();

    public abstract long C();

    public abstract String D();

    public abstract String E();

    public abstract int F();

    public abstract int G();

    public abstract long H();

    public abstract boolean I(int i);

    public abstract void a(int i);

    public final void b() throws d0 {
        if (this.a >= this.b) {
            throw new d0("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
    }

    public abstract int e();

    public abstract int f();

    public abstract boolean g();

    public abstract void j(int i);

    public abstract int k(int i);

    public abstract boolean l();

    public abstract lk.f m();

    public abstract double n();

    public abstract int o();

    public abstract int p();

    public abstract long q();

    public abstract float r();

    public abstract void s(int i, r0.a aVar, s sVar);

    public abstract int t();

    public abstract long u();

    public abstract r0 v(c cVar, s sVar);

    public abstract void w(q0.a aVar, s sVar);

    public abstract byte[] x(int i);

    public abstract int y();

    public abstract int z();
}

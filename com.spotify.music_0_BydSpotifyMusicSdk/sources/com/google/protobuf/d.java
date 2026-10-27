package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.nio.ByteBuffer;
import p.au;
import p.cr2;
import p.dr2;
import p.p02;
import p.w51;

/* JADX INFO: loaded from: classes.dex */
public final class d extends au {
    public final ByteBuffer e;
    public final long f;
    public long g;
    public long h;
    public final long i;
    public int j;
    public int k;
    public int l = Integer.MAX_VALUE;

    public d(ByteBuffer byteBuffer, boolean z) {
        this.e = byteBuffer;
        long j = dr2.c.j(dr2.g, byteBuffer);
        this.f = j;
        this.g = ((long) byteBuffer.limit()) + j;
        long jPosition = j + ((long) byteBuffer.position());
        this.h = jPosition;
        this.i = jPosition;
    }

    @Override // p.au
    public final int A() throws InvalidProtocolBufferException {
        if (e()) {
            this.k = 0;
            return 0;
        }
        int iG = G();
        this.k = iG;
        if ((iG >>> 3) != 0) {
            return iG;
        }
        throw InvalidProtocolBufferException.b();
    }

    @Override // p.au
    public final int B() {
        return G();
    }

    @Override // p.au
    public final long C() {
        return H();
    }

    @Override // p.au
    public final boolean D(int i) throws InvalidProtocolBufferException {
        int iA;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (((int) (this.g - this.h)) >= 10) {
                while (i3 < 10) {
                    long j = this.h;
                    this.h = j + 1;
                    if (dr2.c.e(j) < 0) {
                        i3++;
                    }
                }
                throw InvalidProtocolBufferException.e();
            }
            while (i3 < 10) {
                long j2 = this.h;
                if (j2 == this.g) {
                    throw InvalidProtocolBufferException.j();
                }
                this.h = j2 + 1;
                if (dr2.c.e(j2) < 0) {
                    i3++;
                }
            }
            throw InvalidProtocolBufferException.e();
            return true;
        }
        if (i2 == 1) {
            K(8);
            return true;
        }
        if (i2 == 2) {
            K(G());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw InvalidProtocolBufferException.d();
            }
            K(4);
            return true;
        }
        do {
            iA = A();
            if (iA == 0) {
                break;
            }
        } while (D(iA));
        a(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int E() throws InvalidProtocolBufferException {
        long j = this.h;
        if (this.g - j < 4) {
            throw InvalidProtocolBufferException.j();
        }
        this.h = 4 + j;
        cr2 cr2Var = dr2.c;
        return ((cr2Var.e(j + 3) & 255) << 24) | (cr2Var.e(j) & 255) | ((cr2Var.e(1 + j) & 255) << 8) | ((cr2Var.e(2 + j) & 255) << 16);
    }

    public final long F() throws InvalidProtocolBufferException {
        long j = this.h;
        if (this.g - j < 8) {
            throw InvalidProtocolBufferException.j();
        }
        this.h = 8 + j;
        cr2 cr2Var = dr2.c;
        return ((((long) cr2Var.e(j + 7)) & 255) << 56) | (((long) cr2Var.e(j)) & 255) | ((((long) cr2Var.e(1 + j)) & 255) << 8) | ((((long) cr2Var.e(2 + j)) & 255) << 16) | ((((long) cr2Var.e(3 + j)) & 255) << 24) | ((((long) cr2Var.e(4 + j)) & 255) << 32) | ((((long) cr2Var.e(5 + j)) & 255) << 40) | ((((long) cr2Var.e(6 + j)) & 255) << 48);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0091, code lost:
    
        if (r4.e(r8) < 0) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0099 A[PHI: r6
  0x0099: PHI (r6v7 long) = (r6v6 long), (r6v8 long), (r6v10 long) binds: [B:25:0x006d, B:29:0x0080, B:33:0x0091] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int G() {
        /*
            r12 = this;
            long r0 = r12.h
            long r2 = r12.g
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 != 0) goto La
            goto L93
        La:
            r2 = 1
            long r2 = r2 + r0
            p.cr2 r4 = p.dr2.c
            byte r5 = r4.e(r0)
            if (r5 < 0) goto L18
            r12.h = r2
            return r5
        L18:
            long r6 = r12.g
            long r6 = r6 - r2
            r8 = 9
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L23
            goto L93
        L23:
            r6 = 2
            long r6 = r6 + r0
            byte r2 = r4.e(r2)
            int r2 = r2 << 7
            r2 = r2 ^ r5
            if (r2 >= 0) goto L33
            r0 = r2 ^ (-128(0xffffffffffffff80, float:NaN))
            goto La0
        L33:
            r10 = 3
            long r10 = r10 + r0
            byte r3 = r4.e(r6)
            int r3 = r3 << 14
            r2 = r2 ^ r3
            if (r2 < 0) goto L43
            r0 = r2 ^ 16256(0x3f80, float:2.278E-41)
        L41:
            r6 = r10
            goto La0
        L43:
            r5 = 4
            long r6 = r0 + r5
            byte r3 = r4.e(r10)
            int r3 = r3 << 21
            r2 = r2 ^ r3
            if (r2 >= 0) goto L55
            r0 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto La0
        L55:
            r10 = 5
            long r10 = r10 + r0
            byte r3 = r4.e(r6)
            int r5 = r3 << 28
            r2 = r2 ^ r5
            r5 = 266354560(0xfe03f80, float:2.2112565E-29)
            r2 = r2 ^ r5
            if (r3 >= 0) goto L9e
            r5 = 6
            long r6 = r0 + r5
            byte r3 = r4.e(r10)
            if (r3 >= 0) goto L99
            r10 = 7
            long r10 = r10 + r0
            byte r3 = r4.e(r6)
            if (r3 >= 0) goto L9e
            r5 = 8
            long r6 = r0 + r5
            byte r3 = r4.e(r10)
            if (r3 >= 0) goto L99
            long r8 = r8 + r0
            byte r3 = r4.e(r6)
            if (r3 >= 0) goto L9b
            r5 = 10
            long r6 = r0 + r5
            byte r0 = r4.e(r8)
            if (r0 >= 0) goto L99
        L93:
            long r0 = r12.I()
            int r0 = (int) r0
            return r0
        L99:
            r0 = r2
            goto La0
        L9b:
            r0 = r2
            r6 = r8
            goto La0
        L9e:
            r0 = r2
            goto L41
        La0:
            r12.h = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.d.G():int");
    }

    public final long H() {
        long j;
        long j2;
        long j3;
        int i;
        long j4 = this.h;
        if (this.g != j4) {
            long j5 = 1 + j4;
            cr2 cr2Var = dr2.c;
            byte bE = cr2Var.e(j4);
            if (bE >= 0) {
                this.h = j5;
                return bE;
            }
            if (this.g - j5 >= 9) {
                long j6 = 2 + j4;
                int iE = (cr2Var.e(j5) << 7) ^ bE;
                if (iE >= 0) {
                    long j7 = 3 + j4;
                    int iE2 = iE ^ (cr2Var.e(j6) << 14);
                    if (iE2 >= 0) {
                        j = iE2 ^ 16256;
                    } else {
                        j6 = j4 + 4;
                        int iE3 = iE2 ^ (cr2Var.e(j7) << 21);
                        if (iE3 < 0) {
                            i = (-2080896) ^ iE3;
                        } else {
                            j7 = 5 + j4;
                            long jE = ((long) iE3) ^ (((long) cr2Var.e(j6)) << 28);
                            if (jE < 0) {
                                long j8 = 6 + j4;
                                long jE2 = jE ^ (((long) cr2Var.e(j7)) << 35);
                                if (jE2 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    j7 = 7 + j4;
                                    jE = jE2 ^ (((long) cr2Var.e(j8)) << 42);
                                    if (jE >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        j8 = 8 + j4;
                                        jE2 = jE ^ (((long) cr2Var.e(j7)) << 49);
                                        if (jE2 >= 0) {
                                            long j9 = j4 + 9;
                                            long jE3 = (jE2 ^ (((long) cr2Var.e(j8)) << 56)) ^ 71499008037633920L;
                                            if (jE3 < 0) {
                                                long j10 = j4 + 10;
                                                if (cr2Var.e(j9) >= 0) {
                                                    j6 = j10;
                                                    j = jE3;
                                                }
                                            } else {
                                                j = jE3;
                                                j6 = j9;
                                            }
                                            this.h = j6;
                                            return j;
                                        }
                                        j2 = -558586000294016L;
                                    }
                                }
                                j = j2 ^ jE2;
                                j6 = j8;
                                this.h = j6;
                                return j;
                            }
                            j3 = 266354560;
                            j = j3 ^ jE;
                        }
                    }
                    j6 = j7;
                    this.h = j6;
                    return j;
                }
                i = iE ^ (-128);
                j = i;
                this.h = j6;
                return j;
            }
        }
        return I();
    }

    public final long I() throws InvalidProtocolBufferException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            long j2 = this.h;
            if (j2 == this.g) {
                throw InvalidProtocolBufferException.j();
            }
            this.h = 1 + j2;
            byte bE = dr2.c.e(j2);
            j |= ((long) (bE & 127)) << i;
            if ((bE & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.e();
    }

    public final void J() {
        long j = this.g + ((long) this.j);
        this.g = j;
        int i = (int) (j - this.i);
        int i2 = this.l;
        if (i <= i2) {
            this.j = 0;
            return;
        }
        int i3 = i - i2;
        this.j = i3;
        this.g = j - ((long) i3);
    }

    public final void K(int i) throws InvalidProtocolBufferException {
        if (i >= 0) {
            long j = this.g;
            long j2 = this.h;
            if (i <= ((int) (j - j2))) {
                this.h = j2 + ((long) i);
                return;
            }
        }
        if (i >= 0) {
            throw InvalidProtocolBufferException.j();
        }
        throw InvalidProtocolBufferException.f();
    }

    @Override // p.au
    public final void a(int i) throws InvalidProtocolBufferException {
        if (this.k != i) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
    }

    @Override // p.au
    public final int d() {
        return (int) (this.h - this.i);
    }

    @Override // p.au
    public final boolean e() {
        return this.h == this.g;
    }

    @Override // p.au
    public final void i(int i) {
        this.l = i;
        J();
    }

    @Override // p.au
    public final int j(int i) throws InvalidProtocolBufferException {
        if (i < 0) {
            throw InvalidProtocolBufferException.f();
        }
        int iD = d() + i;
        int i2 = this.l;
        if (iD > i2) {
            throw InvalidProtocolBufferException.j();
        }
        this.l = iD;
        J();
        return i2;
    }

    @Override // p.au
    public final boolean k() {
        return H() != 0;
    }

    @Override // p.au
    public final ByteString l() throws InvalidProtocolBufferException {
        int iG = G();
        if (iG > 0) {
            long j = this.g;
            long j2 = this.h;
            if (iG <= ((int) (j - j2))) {
                byte[] bArr = new byte[iG];
                long j3 = iG;
                dr2.c.c(j2, bArr, j3);
                this.h += j3;
                ByteString byteString = ByteString.c;
                return new ByteString.LiteralByteString(bArr);
            }
        }
        if (iG == 0) {
            return ByteString.c;
        }
        if (iG < 0) {
            throw InvalidProtocolBufferException.f();
        }
        throw InvalidProtocolBufferException.j();
    }

    @Override // p.au
    public final double m() {
        return Double.longBitsToDouble(F());
    }

    @Override // p.au
    public final int n() {
        return G();
    }

    @Override // p.au
    public final int o() {
        return E();
    }

    @Override // p.au
    public final long p() {
        return F();
    }

    @Override // p.au
    public final float q() {
        return Float.intBitsToFloat(E());
    }

    @Override // p.au
    public final int r() {
        return G();
    }

    @Override // p.au
    public final long s() {
        return H();
    }

    @Override // p.au
    public final int u() {
        return E();
    }

    @Override // p.au
    public final long v() {
        return F();
    }

    @Override // p.au
    public final int w() {
        return au.b(G());
    }

    @Override // p.au
    public final long x() {
        return au.c(H());
    }

    @Override // p.au
    public final String y() throws InvalidProtocolBufferException {
        int iG = G();
        if (iG > 0) {
            long j = this.g;
            long j2 = this.h;
            if (iG <= ((int) (j - j2))) {
                byte[] bArr = new byte[iG];
                long j3 = iG;
                dr2.c.c(j2, bArr, j3);
                String str = new String(bArr, w51.a);
                this.h += j3;
                return str;
            }
        }
        if (iG == 0) {
            return "";
        }
        if (iG < 0) {
            throw InvalidProtocolBufferException.f();
        }
        throw InvalidProtocolBufferException.j();
    }

    @Override // p.au
    public final String z() throws InvalidProtocolBufferException {
        int iG = G();
        if (iG > 0) {
            long j = this.g;
            long j2 = this.h;
            if (iG <= ((int) (j - j2))) {
                int i = (int) (j2 - this.f);
                q qVar = r.a;
                qVar.getClass();
                ByteBuffer byteBuffer = this.e;
                String strU = byteBuffer.hasArray() ? qVar.u(byteBuffer.array(), byteBuffer.arrayOffset() + i, iG) : byteBuffer.isDirect() ? qVar.v(byteBuffer, i, iG) : p02.f(byteBuffer, i, iG);
                this.h += (long) iG;
                return strU;
            }
        }
        if (iG == 0) {
            return "";
        }
        if (iG <= 0) {
            throw InvalidProtocolBufferException.f();
        }
        throw InvalidProtocolBufferException.j();
    }
}

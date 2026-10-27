package com.google.protobuf;

import p.au;
import p.w51;

/* JADX INFO: loaded from: classes.dex */
public final class b extends au {
    public final byte[] e;
    public int f;
    public int g;
    public int h;
    public final int i;
    public int j;
    public int k = Integer.MAX_VALUE;

    public b(byte[] bArr, int i, int i2, boolean z) {
        this.e = bArr;
        this.f = i2 + i;
        this.h = i;
        this.i = i;
    }

    @Override // p.au
    public final int A() throws InvalidProtocolBufferException {
        if (e()) {
            this.j = 0;
            return 0;
        }
        int iG = G();
        this.j = iG;
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
            int i4 = this.f - this.h;
            byte[] bArr = this.e;
            if (i4 >= 10) {
                while (i3 < 10) {
                    int i5 = this.h;
                    this.h = i5 + 1;
                    if (bArr[i5] < 0) {
                        i3++;
                    }
                }
                throw InvalidProtocolBufferException.e();
            }
            while (i3 < 10) {
                int i6 = this.h;
                if (i6 == this.f) {
                    throw InvalidProtocolBufferException.j();
                }
                this.h = i6 + 1;
                if (bArr[i6] < 0) {
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
        int i = this.h;
        if (this.f - i < 4) {
            throw InvalidProtocolBufferException.j();
        }
        this.h = i + 4;
        byte[] bArr = this.e;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public final long F() throws InvalidProtocolBufferException {
        int i = this.h;
        if (this.f - i < 8) {
            throw InvalidProtocolBufferException.j();
        }
        this.h = i + 8;
        byte[] bArr = this.e;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final int G() {
        int i;
        int i2 = this.h;
        int i3 = this.f;
        if (i3 != i2) {
            int i4 = i2 + 1;
            byte[] bArr = this.e;
            byte b = bArr[i2];
            if (b >= 0) {
                this.h = i4;
                return b;
            }
            if (i3 - i4 >= 9) {
                int i5 = i2 + 2;
                int i6 = (bArr[i4] << 7) ^ b;
                if (i6 < 0) {
                    i = i6 ^ (-128);
                } else {
                    int i7 = i2 + 3;
                    int i8 = (bArr[i5] << 14) ^ i6;
                    if (i8 >= 0) {
                        i = i8 ^ 16256;
                    } else {
                        int i9 = i2 + 4;
                        int i10 = i8 ^ (bArr[i7] << 21);
                        if (i10 < 0) {
                            i = (-2080896) ^ i10;
                        } else {
                            i7 = i2 + 5;
                            byte b2 = bArr[i9];
                            int i11 = (i10 ^ (b2 << 28)) ^ 266354560;
                            if (b2 < 0) {
                                i9 = i2 + 6;
                                if (bArr[i7] < 0) {
                                    i7 = i2 + 7;
                                    if (bArr[i9] < 0) {
                                        i9 = i2 + 8;
                                        if (bArr[i7] < 0) {
                                            i7 = i2 + 9;
                                            if (bArr[i9] < 0) {
                                                int i12 = i2 + 10;
                                                if (bArr[i7] >= 0) {
                                                    i5 = i12;
                                                    i = i11;
                                                }
                                            }
                                        }
                                    }
                                }
                                i = i11;
                            }
                            i = i11;
                        }
                        i5 = i9;
                    }
                    i5 = i7;
                }
                this.h = i5;
                return i;
            }
        }
        return (int) I();
    }

    public final long H() {
        long j;
        long j2;
        long j3;
        long j4;
        int i = this.h;
        int i2 = this.f;
        if (i2 != i) {
            int i3 = i + 1;
            byte[] bArr = this.e;
            byte b = bArr[i];
            if (b >= 0) {
                this.h = i3;
                return b;
            }
            if (i2 - i3 >= 9) {
                int i4 = i + 2;
                int i5 = (bArr[i3] << 7) ^ b;
                if (i5 < 0) {
                    j = i5 ^ (-128);
                } else {
                    int i6 = i + 3;
                    int i7 = (bArr[i4] << 14) ^ i5;
                    if (i7 >= 0) {
                        j = i7 ^ 16256;
                        i4 = i6;
                    } else {
                        int i8 = i + 4;
                        int i9 = i7 ^ (bArr[i6] << 21);
                        if (i9 < 0) {
                            j4 = (-2080896) ^ i9;
                        } else {
                            long j5 = i9;
                            i4 = i + 5;
                            long j6 = j5 ^ (((long) bArr[i8]) << 28);
                            if (j6 >= 0) {
                                j3 = 266354560;
                            } else {
                                i8 = i + 6;
                                long j7 = j6 ^ (((long) bArr[i4]) << 35);
                                if (j7 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i4 = i + 7;
                                    j6 = j7 ^ (((long) bArr[i8]) << 42);
                                    if (j6 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i8 = i + 8;
                                        j7 = j6 ^ (((long) bArr[i4]) << 49);
                                        if (j7 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            i4 = i + 9;
                                            long j8 = (j7 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                            if (j8 < 0) {
                                                int i10 = i + 10;
                                                if (bArr[i4] >= 0) {
                                                    i4 = i10;
                                                }
                                            }
                                            j = j8;
                                        }
                                    }
                                }
                                j4 = j2 ^ j7;
                            }
                            j = j3 ^ j6;
                        }
                        i4 = i8;
                        j = j4;
                    }
                }
                this.h = i4;
                return j;
            }
        }
        return I();
    }

    public final long I() throws InvalidProtocolBufferException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            int i2 = this.h;
            if (i2 == this.f) {
                throw InvalidProtocolBufferException.j();
            }
            this.h = i2 + 1;
            byte b = this.e[i2];
            j |= ((long) (b & 127)) << i;
            if ((b & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.e();
    }

    public final void J() {
        int i = this.f + this.g;
        this.f = i;
        int i2 = i - this.i;
        int i3 = this.k;
        if (i2 <= i3) {
            this.g = 0;
            return;
        }
        int i4 = i2 - i3;
        this.g = i4;
        this.f = i - i4;
    }

    public final void K(int i) throws InvalidProtocolBufferException {
        if (i >= 0) {
            int i2 = this.f;
            int i3 = this.h;
            if (i <= i2 - i3) {
                this.h = i3 + i;
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
        if (this.j != i) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
    }

    @Override // p.au
    public final int d() {
        return this.h - this.i;
    }

    @Override // p.au
    public final boolean e() {
        return this.h == this.f;
    }

    @Override // p.au
    public final void i(int i) {
        this.k = i;
        J();
    }

    @Override // p.au
    public final int j(int i) throws InvalidProtocolBufferException {
        if (i < 0) {
            throw InvalidProtocolBufferException.f();
        }
        int iD = d() + i;
        if (iD < 0) {
            throw InvalidProtocolBufferException.g();
        }
        int i2 = this.k;
        if (iD > i2) {
            throw InvalidProtocolBufferException.j();
        }
        this.k = iD;
        J();
        return i2;
    }

    @Override // p.au
    public final boolean k() {
        return H() != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    @Override // p.au
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.protobuf.ByteString l() throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r4 = this;
            int r0 = r4.G()
            byte[] r1 = r4.e
            if (r0 <= 0) goto L19
            int r2 = r4.f
            int r3 = r4.h
            int r2 = r2 - r3
            if (r0 > r2) goto L19
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.c(r1, r3, r0)
            int r2 = r4.h
            int r2 = r2 + r0
            r4.h = r2
            return r1
        L19:
            if (r0 != 0) goto L1e
            com.google.protobuf.ByteString r0 = com.google.protobuf.ByteString.c
            return r0
        L1e:
            if (r0 <= 0) goto L2f
            int r2 = r4.f
            int r3 = r4.h
            int r2 = r2 - r3
            if (r0 > r2) goto L2f
            int r0 = r0 + r3
            r4.h = r0
            byte[] r0 = java.util.Arrays.copyOfRange(r1, r3, r0)
            goto L35
        L2f:
            if (r0 > 0) goto L42
            if (r0 != 0) goto L3d
            byte[] r0 = p.w51.b
        L35:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.c
            com.google.protobuf.ByteString$LiteralByteString r1 = new com.google.protobuf.ByteString$LiteralByteString
            r1.<init>(r0)
            return r1
        L3d:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.f()
            throw r0
        L42:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.j()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.b.l():com.google.protobuf.ByteString");
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
            int i = this.f;
            int i2 = this.h;
            if (iG <= i - i2) {
                String str = new String(this.e, i2, iG, w51.a);
                this.h += iG;
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
            int i = this.f;
            int i2 = this.h;
            if (iG <= i - i2) {
                String strU = r.a.u(this.e, i2, iG);
                this.h += iG;
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

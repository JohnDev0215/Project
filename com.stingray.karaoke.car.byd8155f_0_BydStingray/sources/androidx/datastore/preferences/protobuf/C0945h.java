package androidx.datastore.preferences.protobuf;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0945h extends AbstractC0947j {
    public final byte[] c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f11847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f11848f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f11849g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f11850h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f11851i = Integer.MAX_VALUE;

    public C0945h(byte[] bArr, int i7, int i10, boolean z6) {
        this.c = bArr;
        this.d = i10 + i7;
        this.f11848f = i7;
        this.f11849g = i7;
    }

    public final int A() {
        int i7;
        int i10 = this.f11848f;
        int i11 = this.d;
        if (i11 != i10) {
            int i12 = i10 + 1;
            byte[] bArr = this.c;
            byte b10 = bArr[i10];
            if (b10 >= 0) {
                this.f11848f = i12;
                return b10;
            }
            if (i11 - i12 >= 9) {
                int i13 = i10 + 2;
                int i14 = (bArr[i12] << 7) ^ b10;
                if (i14 < 0) {
                    i7 = i14 ^ (-128);
                } else {
                    int i15 = i10 + 3;
                    int i16 = (bArr[i13] << 14) ^ i14;
                    if (i16 >= 0) {
                        i7 = i16 ^ 16256;
                    } else {
                        int i17 = i10 + 4;
                        int i18 = i16 ^ (bArr[i15] << 21);
                        if (i18 < 0) {
                            i7 = (-2080896) ^ i18;
                        } else {
                            i15 = i10 + 5;
                            byte b11 = bArr[i17];
                            int i19 = (i18 ^ (b11 << 28)) ^ 266354560;
                            if (b11 < 0) {
                                i17 = i10 + 6;
                                if (bArr[i15] < 0) {
                                    i15 = i10 + 7;
                                    if (bArr[i17] < 0) {
                                        i17 = i10 + 8;
                                        if (bArr[i15] < 0) {
                                            i15 = i10 + 9;
                                            if (bArr[i17] < 0) {
                                                int i20 = i10 + 10;
                                                if (bArr[i15] >= 0) {
                                                    i13 = i20;
                                                    i7 = i19;
                                                }
                                            }
                                        }
                                    }
                                }
                                i7 = i19;
                            }
                            i7 = i19;
                        }
                        i13 = i17;
                    }
                    i13 = i15;
                }
                this.f11848f = i13;
                return i7;
            }
        }
        return (int) C();
    }

    public final long B() {
        long j3;
        long j10;
        long j11;
        long j12;
        int i7 = this.f11848f;
        int i10 = this.d;
        if (i10 != i7) {
            int i11 = i7 + 1;
            byte[] bArr = this.c;
            byte b10 = bArr[i7];
            if (b10 >= 0) {
                this.f11848f = i11;
                return b10;
            }
            if (i10 - i11 >= 9) {
                int i12 = i7 + 2;
                int i13 = (bArr[i11] << 7) ^ b10;
                if (i13 < 0) {
                    j3 = i13 ^ (-128);
                } else {
                    int i14 = i7 + 3;
                    int i15 = (bArr[i12] << 14) ^ i13;
                    if (i15 >= 0) {
                        j3 = i15 ^ 16256;
                        i12 = i14;
                    } else {
                        int i16 = i7 + 4;
                        int i17 = i15 ^ (bArr[i14] << 21);
                        if (i17 < 0) {
                            j12 = (-2080896) ^ i17;
                        } else {
                            long j13 = i17;
                            i12 = i7 + 5;
                            long j14 = j13 ^ (((long) bArr[i16]) << 28);
                            if (j14 >= 0) {
                                j11 = 266354560;
                            } else {
                                i16 = i7 + 6;
                                long j15 = j14 ^ (((long) bArr[i12]) << 35);
                                if (j15 < 0) {
                                    j10 = -34093383808L;
                                } else {
                                    i12 = i7 + 7;
                                    j14 = j15 ^ (((long) bArr[i16]) << 42);
                                    if (j14 >= 0) {
                                        j11 = 4363953127296L;
                                    } else {
                                        i16 = i7 + 8;
                                        j15 = j14 ^ (((long) bArr[i12]) << 49);
                                        if (j15 < 0) {
                                            j10 = -558586000294016L;
                                        } else {
                                            i12 = i7 + 9;
                                            long j16 = (j15 ^ (((long) bArr[i16]) << 56)) ^ 71499008037633920L;
                                            if (j16 < 0) {
                                                int i18 = i7 + 10;
                                                if (bArr[i12] >= 0) {
                                                    i12 = i18;
                                                }
                                            }
                                            j3 = j16;
                                        }
                                    }
                                }
                                j12 = j10 ^ j15;
                            }
                            j3 = j11 ^ j14;
                        }
                        i12 = i16;
                        j3 = j12;
                    }
                }
                this.f11848f = i12;
                return j3;
            }
        }
        return C();
    }

    public final long C() throws C0961y {
        long j3 = 0;
        for (int i7 = 0; i7 < 64; i7 += 7) {
            int i10 = this.f11848f;
            if (i10 == this.d) {
                throw C0961y.e();
            }
            this.f11848f = i10 + 1;
            byte b10 = this.c[i10];
            j3 |= ((long) (b10 & 127)) << i7;
            if ((b10 & 128) == 0) {
                return j3;
            }
        }
        throw C0961y.c();
    }

    public final void D() {
        int i7 = this.d + this.f11847e;
        this.d = i7;
        int i10 = i7 - this.f11849g;
        int i11 = this.f11851i;
        if (i10 <= i11) {
            this.f11847e = 0;
            return;
        }
        int i12 = i10 - i11;
        this.f11847e = i12;
        this.d = i7 - i12;
    }

    public final void E(int i7) throws C0961y {
        if (i7 >= 0) {
            int i10 = this.d;
            int i11 = this.f11848f;
            if (i7 <= i10 - i11) {
                this.f11848f = i11 + i7;
                return;
            }
        }
        if (i7 >= 0) {
            throw C0961y.e();
        }
        throw C0961y.d();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final void a(int i7) throws C0961y {
        if (this.f11850h != i7) {
            throw new C0961y("Protocol message end-group tag did not match expected tag.");
        }
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int b() {
        return this.f11848f - this.f11849g;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final boolean c() {
        return this.f11848f == this.d;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final void d(int i7) {
        this.f11851i = i7;
        D();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int e(int i7) throws C0961y {
        if (i7 < 0) {
            throw C0961y.d();
        }
        int iB = b() + i7;
        if (iB < 0) {
            throw new C0961y("Failed to parse the message.");
        }
        int i10 = this.f11851i;
        if (iB > i10) {
            throw C0961y.e();
        }
        this.f11851i = iB;
        D();
        return i10;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final boolean f() {
        return B() != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.datastore.preferences.protobuf.C0944g g() throws androidx.datastore.preferences.protobuf.C0961y {
        /*
            r4 = this;
            int r0 = r4.A()
            byte[] r1 = r4.c
            if (r0 <= 0) goto L19
            int r2 = r4.d
            int r3 = r4.f11848f
            int r2 = r2 - r3
            if (r0 > r2) goto L19
            androidx.datastore.preferences.protobuf.g r1 = androidx.datastore.preferences.protobuf.C0944g.l(r3, r0, r1)
            int r2 = r4.f11848f
            int r2 = r2 + r0
            r4.f11848f = r2
            return r1
        L19:
            if (r0 != 0) goto L1e
            androidx.datastore.preferences.protobuf.g r4 = androidx.datastore.preferences.protobuf.C0944g.f11844n
            return r4
        L1e:
            if (r0 <= 0) goto L2f
            int r2 = r4.d
            int r3 = r4.f11848f
            int r2 = r2 - r3
            if (r0 > r2) goto L2f
            int r0 = r0 + r3
            r4.f11848f = r0
            byte[] r4 = java.util.Arrays.copyOfRange(r1, r3, r0)
            goto L35
        L2f:
            if (r0 > 0) goto L42
            if (r0 != 0) goto L3d
            byte[] r4 = androidx.datastore.preferences.protobuf.AbstractC0959w.f11891b
        L35:
            androidx.datastore.preferences.protobuf.g r0 = androidx.datastore.preferences.protobuf.C0944g.f11844n
            androidx.datastore.preferences.protobuf.g r0 = new androidx.datastore.preferences.protobuf.g
            r0.<init>(r4)
            return r0
        L3d:
            androidx.datastore.preferences.protobuf.y r4 = androidx.datastore.preferences.protobuf.C0961y.d()
            throw r4
        L42:
            androidx.datastore.preferences.protobuf.y r4 = androidx.datastore.preferences.protobuf.C0961y.e()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.C0945h.g():androidx.datastore.preferences.protobuf.g");
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final double h() {
        return Double.longBitsToDouble(z());
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int i() {
        return A();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int j() {
        return y();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long k() {
        return z();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final float l() {
        return Float.intBitsToFloat(y());
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int m() {
        return A();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long n() {
        return B();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int o() {
        return y();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long p() {
        return z();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int q() {
        int iA = A();
        return (-(iA & 1)) ^ (iA >>> 1);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long r() {
        long jB = B();
        return (-(jB & 1)) ^ (jB >>> 1);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final String s() throws C0961y {
        int iA = A();
        if (iA > 0) {
            int i7 = this.d;
            int i10 = this.f11848f;
            if (iA <= i7 - i10) {
                String str = new String(this.c, i10, iA, AbstractC0959w.f11890a);
                this.f11848f += iA;
                return str;
            }
        }
        if (iA == 0) {
            return "";
        }
        if (iA < 0) {
            throw C0961y.d();
        }
        throw C0961y.e();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final String t() throws C0961y {
        int iA = A();
        if (iA > 0) {
            int i7 = this.d;
            int i10 = this.f11848f;
            if (iA <= i7 - i10) {
                String strA = u0.f11889a.a(i10, iA, this.c);
                this.f11848f += iA;
                return strA;
            }
        }
        if (iA == 0) {
            return "";
        }
        if (iA <= 0) {
            throw C0961y.d();
        }
        throw C0961y.e();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int u() throws C0961y {
        if (c()) {
            this.f11850h = 0;
            return 0;
        }
        int iA = A();
        this.f11850h = iA;
        if ((iA >>> 3) != 0) {
            return iA;
        }
        throw new C0961y("Protocol message contained an invalid tag (zero).");
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final int v() {
        return A();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final long w() {
        return B();
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0947j
    public final boolean x(int i7) throws C0961y {
        int iU;
        int i10 = i7 & 7;
        int i11 = 0;
        if (i10 == 0) {
            int i12 = this.d - this.f11848f;
            byte[] bArr = this.c;
            if (i12 >= 10) {
                while (i11 < 10) {
                    int i13 = this.f11848f;
                    this.f11848f = i13 + 1;
                    if (bArr[i13] < 0) {
                        i11++;
                    }
                }
                throw C0961y.c();
            }
            while (i11 < 10) {
                int i14 = this.f11848f;
                if (i14 == this.d) {
                    throw C0961y.e();
                }
                this.f11848f = i14 + 1;
                if (bArr[i14] < 0) {
                    i11++;
                }
            }
            throw C0961y.c();
            return true;
        }
        if (i10 == 1) {
            E(8);
            return true;
        }
        if (i10 == 2) {
            E(A());
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw C0961y.b();
            }
            E(4);
            return true;
        }
        do {
            iU = u();
            if (iU == 0) {
                break;
            }
        } while (x(iU));
        a(((i7 >>> 3) << 3) | 4);
        return true;
    }

    public final int y() throws C0961y {
        int i7 = this.f11848f;
        if (this.d - i7 < 4) {
            throw C0961y.e();
        }
        this.f11848f = i7 + 4;
        byte[] bArr = this.c;
        return ((bArr[i7 + 3] & 255) << 24) | (bArr[i7] & 255) | ((bArr[i7 + 1] & 255) << 8) | ((bArr[i7 + 2] & 255) << 16);
    }

    public final long z() throws C0961y {
        int i7 = this.f11848f;
        if (this.d - i7 < 8) {
            throw C0961y.e();
        }
        this.f11848f = i7 + 8;
        byte[] bArr = this.c;
        return ((((long) bArr[i7 + 1]) & 255) << 8) | (((long) bArr[i7]) & 255) | ((((long) bArr[i7 + 2]) & 255) << 16) | ((((long) bArr[i7 + 3]) & 255) << 24) | ((((long) bArr[i7 + 4]) & 255) << 32) | ((((long) bArr[i7 + 5]) & 255) << 40) | ((((long) bArr[i7 + 6]) & 255) << 48) | ((((long) bArr[i7 + 7]) & 255) << 56);
    }
}

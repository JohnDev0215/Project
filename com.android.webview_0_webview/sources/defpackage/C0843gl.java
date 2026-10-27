package defpackage;

/* JADX INFO: renamed from: gl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0843gl extends AbstractC1010jl {
    public final byte[] e;
    public final boolean f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;

    public C0843gl(byte[] bArr, int i, int i2, boolean z, AbstractC0787fl abstractC0787fl) {
        super(null);
        this.l = Integer.MAX_VALUE;
        this.e = bArr;
        this.g = i2 + i;
        this.i = i;
        this.j = i;
        this.f = z;
    }

    public byte A() throws C0155Il {
        int i = this.i;
        if (i == this.g) {
            throw C0155Il.g();
        }
        byte[] bArr = this.e;
        this.i = i + 1;
        return bArr[i];
    }

    public int B() throws C0155Il {
        int i = this.i;
        if (this.g - i < 4) {
            throw C0155Il.g();
        }
        byte[] bArr = this.e;
        this.i = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public long C() throws C0155Il {
        int i = this.i;
        if (this.g - i < 8) {
            throw C0155Il.g();
        }
        byte[] bArr = this.e;
        this.i = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
    
        if (r2[r3] < 0) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int D() {
        /*
            r5 = this;
            int r0 = r5.i
            int r1 = r5.g
            if (r1 != r0) goto L7
            goto L6a
        L7:
            byte[] r2 = r5.e
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r5.i = r3
            return r0
        L12:
            int r1 = r1 - r3
            r4 = 9
            if (r1 >= r4) goto L18
            goto L6a
        L18:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L24
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L70
        L24:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L31
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2f:
            r1 = r3
            goto L70
        L31:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3f
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L70
        L3f:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2f
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L70
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2f
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L70
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2f
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 >= 0) goto L70
        L6a:
            long r0 = r5.F()
            int r1 = (int) r0
            return r1
        L70:
            r5.i = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0843gl.D():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
    
        if (r2[r0] < 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long E() {
        /*
            r11 = this;
            int r0 = r11.i
            int r1 = r11.g
            if (r1 != r0) goto L8
            goto Lb6
        L8:
            byte[] r2 = r11.e
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L14
            r11.i = r3
            long r0 = (long) r0
            return r0
        L14:
            int r1 = r1 - r3
            r4 = 9
            if (r1 >= r4) goto L1b
            goto Lb6
        L1b:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L29
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
        L26:
            long r2 = (long) r0
            goto Lbd
        L29:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L3a
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto Lbd
        L3a:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L48
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L26
        L48:
            long r3 = (long) r0
            int r0 = r1 + 1
            r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L5f
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L5b:
            long r2 = r3 ^ r1
            r1 = r0
            goto Lbd
        L5f:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L74
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L71:
            long r2 = r3 ^ r5
            goto Lbd
        L74:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L87
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L5b
        L87:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L9a
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L71
        L9a:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto Lbb
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 >= 0) goto Lbc
        Lb6:
            long r0 = r11.F()
            return r0
        Lbb:
            r1 = r0
        Lbc:
            r2 = r3
        Lbd:
            r11.i = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0843gl.E():long");
    }

    public long F() throws C0155Il {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bA = A();
            j |= ((long) (bA & 127)) << i;
            if ((bA & 128) == 0) {
                return j;
            }
        }
        throw C0155Il.d();
    }

    public final void G() {
        int i = this.g + this.h;
        this.g = i;
        int i2 = i - this.j;
        int i3 = this.l;
        if (i2 <= i3) {
            this.h = 0;
            return;
        }
        int i4 = i2 - i3;
        this.h = i4;
        this.g = i - i4;
    }

    @Override // defpackage.AbstractC1010jl
    public void a(int i) throws C0155Il {
        if (this.k != i) {
            throw C0155Il.a();
        }
    }

    @Override // defpackage.AbstractC1010jl
    public int d() {
        return this.i - this.j;
    }

    @Override // defpackage.AbstractC1010jl
    public boolean e() {
        return this.i == this.g;
    }

    @Override // defpackage.AbstractC1010jl
    public void g(int i) {
        this.l = i;
        G();
    }

    @Override // defpackage.AbstractC1010jl
    public int h(int i) throws C0155Il {
        if (i < 0) {
            throw C0155Il.e();
        }
        int iD = d() + i;
        int i2 = this.l;
        if (iD > i2) {
            throw C0155Il.g();
        }
        this.l = iD;
        G();
        return i2;
    }

    @Override // defpackage.AbstractC1010jl
    public boolean i() {
        return E() != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    @Override // defpackage.AbstractC1010jl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public defpackage.AbstractC0731el j() throws defpackage.C0155Il {
        /*
            r3 = this;
            int r0 = r3.D()
            if (r0 <= 0) goto L19
            int r1 = r3.g
            int r2 = r3.i
            int r1 = r1 - r2
            if (r0 > r1) goto L19
            byte[] r1 = r3.e
            el r1 = defpackage.AbstractC0731el.h(r1, r2, r0)
            int r2 = r3.i
            int r2 = r2 + r0
            r3.i = r2
            return r1
        L19:
            if (r0 != 0) goto L1e
            el r0 = defpackage.AbstractC0731el.t
            return r0
        L1e:
            if (r0 <= 0) goto L31
            int r1 = r3.g
            int r2 = r3.i
            int r1 = r1 - r2
            if (r0 > r1) goto L31
            int r0 = r0 + r2
            r3.i = r0
            byte[] r1 = r3.e
            byte[] r0 = java.util.Arrays.copyOfRange(r1, r2, r0)
            goto L37
        L31:
            if (r0 > 0) goto L41
            if (r0 != 0) goto L3c
            byte[] r0 = defpackage.AbstractC0119Gl.b
        L37:
            el r0 = defpackage.AbstractC0731el.n(r0)
            return r0
        L3c:
            Il r0 = defpackage.C0155Il.e()
            throw r0
        L41:
            Il r0 = defpackage.C0155Il.g()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0843gl.j():el");
    }

    @Override // defpackage.AbstractC1010jl
    public double k() {
        return Double.longBitsToDouble(C());
    }

    @Override // defpackage.AbstractC1010jl
    public int l() {
        return D();
    }

    @Override // defpackage.AbstractC1010jl
    public int m() {
        return B();
    }

    @Override // defpackage.AbstractC1010jl
    public long n() {
        return C();
    }

    @Override // defpackage.AbstractC1010jl
    public float o() {
        return Float.intBitsToFloat(B());
    }

    @Override // defpackage.AbstractC1010jl
    public int p() {
        return D();
    }

    @Override // defpackage.AbstractC1010jl
    public long q() {
        return E();
    }

    @Override // defpackage.AbstractC1010jl
    public int r() {
        return B();
    }

    @Override // defpackage.AbstractC1010jl
    public long s() {
        return C();
    }

    @Override // defpackage.AbstractC1010jl
    public int t() {
        return AbstractC1010jl.b(D());
    }

    @Override // defpackage.AbstractC1010jl
    public long u() {
        return AbstractC1010jl.c(E());
    }

    @Override // defpackage.AbstractC1010jl
    public String v() throws C0155Il {
        int iD = D();
        if (iD > 0 && iD <= this.g - this.i) {
            String str = new String(this.e, this.i, iD, AbstractC0119Gl.a);
            this.i += iD;
            return str;
        }
        if (iD == 0) {
            return "";
        }
        if (iD < 0) {
            throw C0155Il.e();
        }
        throw C0155Il.g();
    }

    @Override // defpackage.AbstractC1010jl
    public String w() throws C0155Il {
        int iD = D();
        if (iD > 0) {
            int i = this.g;
            int i2 = this.i;
            if (iD <= i - i2) {
                String strA = AbstractC0174Jm.a.a(this.e, i2, iD);
                this.i += iD;
                return strA;
            }
        }
        if (iD == 0) {
            return "";
        }
        if (iD <= 0) {
            throw C0155Il.e();
        }
        throw C0155Il.g();
    }

    @Override // defpackage.AbstractC1010jl
    public int x() throws C0155Il {
        if (e()) {
            this.k = 0;
            return 0;
        }
        int iD = D();
        this.k = iD;
        if ((iD >>> 3) != 0) {
            return iD;
        }
        throw new C0155Il("Protocol message contained an invalid tag (zero).");
    }

    @Override // defpackage.AbstractC1010jl
    public int y() {
        return D();
    }

    @Override // defpackage.AbstractC1010jl
    public long z() {
        return E();
    }
}

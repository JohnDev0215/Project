package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class q extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4342d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4343e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4344f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4345g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4346h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4347i;
    public int j;
    public int k;
    public int[] l;
    public int m;

    public q() {
        this.l = new int[16];
        reset();
    }

    public q(q qVar) {
        super(qVar);
        this.l = new int[16];
        r(qVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        r((q) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new q(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        w(this.f4342d, bArr, i2);
        w(this.f4343e, bArr, i2 + 4);
        w(this.f4344f, bArr, i2 + 8);
        w(this.f4345g, bArr, i2 + 12);
        w(this.f4346h, bArr, i2 + 16);
        w(this.f4347i, bArr, i2 + 20);
        w(this.j, bArr, i2 + 24);
        w(this.k, bArr, i2 + 28);
        reset();
        return 32;
    }

    @Override // g.a.b.a0.h
    public void f() {
        int i2 = this.f4342d;
        int i3 = this.f4343e;
        int i4 = this.f4344f;
        int i5 = this.f4345g;
        int i6 = this.f4346h;
        int i7 = this.f4347i;
        int i8 = this.j;
        int i9 = this.k;
        int i10 = i(i2, i3, i4, i5, this.l[0], 11);
        int i11 = i(i5, i10, i3, i4, this.l[1], 14);
        int i12 = i(i4, i11, i10, i3, this.l[2], 15);
        int i13 = i(i3, i12, i11, i10, this.l[3], 12);
        int i14 = i(i10, i13, i12, i11, this.l[4], 5);
        int i15 = i(i11, i14, i13, i12, this.l[5], 8);
        int i16 = i(i12, i15, i14, i13, this.l[6], 7);
        int i17 = i(i13, i16, i15, i14, this.l[7], 9);
        int i18 = i(i14, i17, i16, i15, this.l[8], 11);
        int i19 = i(i15, i18, i17, i16, this.l[9], 13);
        int i20 = i(i16, i19, i18, i17, this.l[10], 14);
        int i21 = i(i17, i20, i19, i18, this.l[11], 15);
        int i22 = i(i18, i21, i20, i19, this.l[12], 6);
        int i23 = i(i19, i22, i21, i20, this.l[13], 7);
        int i24 = i(i20, i23, i22, i21, this.l[14], 9);
        int i25 = i(i21, i24, i23, i22, this.l[15], 8);
        int iP = p(i6, i7, i8, i9, this.l[5], 8);
        int iP2 = p(i9, iP, i7, i8, this.l[14], 9);
        int iP3 = p(i8, iP2, iP, i7, this.l[7], 9);
        int iP4 = p(i7, iP3, iP2, iP, this.l[0], 11);
        int iP5 = p(iP, iP4, iP3, iP2, this.l[9], 13);
        int iP6 = p(iP2, iP5, iP4, iP3, this.l[2], 15);
        int iP7 = p(iP3, iP6, iP5, iP4, this.l[11], 15);
        int iP8 = p(iP4, iP7, iP6, iP5, this.l[4], 5);
        int iP9 = p(iP5, iP8, iP7, iP6, this.l[13], 7);
        int iP10 = p(iP6, iP9, iP8, iP7, this.l[6], 7);
        int iP11 = p(iP7, iP10, iP9, iP8, this.l[15], 8);
        int iP12 = p(iP8, iP11, iP10, iP9, this.l[8], 11);
        int iP13 = p(iP9, iP12, iP11, iP10, this.l[1], 14);
        int iP14 = p(iP10, iP13, iP12, iP11, this.l[10], 14);
        int iP15 = p(iP11, iP14, iP13, iP12, this.l[3], 12);
        int iP16 = p(iP12, iP15, iP14, iP13, this.l[12], 6);
        int iJ = j(iP13, i25, i24, i23, this.l[7], 7);
        int iJ2 = j(i23, iJ, i25, i24, this.l[4], 6);
        int iJ3 = j(i24, iJ2, iJ, i25, this.l[13], 8);
        int iJ4 = j(i25, iJ3, iJ2, iJ, this.l[1], 13);
        int iJ5 = j(iJ, iJ4, iJ3, iJ2, this.l[10], 11);
        int iJ6 = j(iJ2, iJ5, iJ4, iJ3, this.l[6], 9);
        int iJ7 = j(iJ3, iJ6, iJ5, iJ4, this.l[15], 7);
        int iJ8 = j(iJ4, iJ7, iJ6, iJ5, this.l[3], 15);
        int iJ9 = j(iJ5, iJ8, iJ7, iJ6, this.l[12], 7);
        int iJ10 = j(iJ6, iJ9, iJ8, iJ7, this.l[0], 12);
        int iJ11 = j(iJ7, iJ10, iJ9, iJ8, this.l[9], 15);
        int iJ12 = j(iJ8, iJ11, iJ10, iJ9, this.l[5], 9);
        int iJ13 = j(iJ9, iJ12, iJ11, iJ10, this.l[2], 11);
        int iJ14 = j(iJ10, iJ13, iJ12, iJ11, this.l[14], 7);
        int iJ15 = j(iJ11, iJ14, iJ13, iJ12, this.l[11], 13);
        int iJ16 = j(iJ12, iJ15, iJ14, iJ13, this.l[8], 12);
        int iO = o(i22, iP16, iP15, iP14, this.l[6], 9);
        int iO2 = o(iP14, iO, iP16, iP15, this.l[11], 13);
        int iO3 = o(iP15, iO2, iO, iP16, this.l[3], 15);
        int iO4 = o(iP16, iO3, iO2, iO, this.l[7], 7);
        int iO5 = o(iO, iO4, iO3, iO2, this.l[0], 12);
        int iO6 = o(iO2, iO5, iO4, iO3, this.l[13], 8);
        int iO7 = o(iO3, iO6, iO5, iO4, this.l[5], 9);
        int iO8 = o(iO4, iO7, iO6, iO5, this.l[10], 11);
        int iO9 = o(iO5, iO8, iO7, iO6, this.l[14], 7);
        int iO10 = o(iO6, iO9, iO8, iO7, this.l[15], 7);
        int iO11 = o(iO7, iO10, iO9, iO8, this.l[8], 12);
        int iO12 = o(iO8, iO11, iO10, iO9, this.l[12], 7);
        int iO13 = o(iO9, iO12, iO11, iO10, this.l[4], 6);
        int iO14 = o(iO10, iO13, iO12, iO11, this.l[9], 15);
        int iO15 = o(iO11, iO14, iO13, iO12, this.l[1], 13);
        int iO16 = o(iO12, iO15, iO14, iO13, this.l[2], 11);
        int iK = k(iJ13, iO16, iJ15, iJ14, this.l[3], 11);
        int iK2 = k(iJ14, iK, iO16, iJ15, this.l[10], 13);
        int iK3 = k(iJ15, iK2, iK, iO16, this.l[14], 6);
        int iK4 = k(iO16, iK3, iK2, iK, this.l[4], 7);
        int iK5 = k(iK, iK4, iK3, iK2, this.l[9], 14);
        int iK6 = k(iK2, iK5, iK4, iK3, this.l[15], 9);
        int iK7 = k(iK3, iK6, iK5, iK4, this.l[8], 13);
        int iK8 = k(iK4, iK7, iK6, iK5, this.l[1], 15);
        int iK9 = k(iK5, iK8, iK7, iK6, this.l[2], 14);
        int iK10 = k(iK6, iK9, iK8, iK7, this.l[7], 8);
        int iK11 = k(iK7, iK10, iK9, iK8, this.l[0], 13);
        int iK12 = k(iK8, iK11, iK10, iK9, this.l[6], 6);
        int iK13 = k(iK9, iK12, iK11, iK10, this.l[13], 5);
        int iK14 = k(iK10, iK13, iK12, iK11, this.l[11], 12);
        int iK15 = k(iK11, iK14, iK13, iK12, this.l[5], 7);
        int iK16 = k(iK12, iK15, iK14, iK13, this.l[12], 5);
        int iN = n(iO13, iJ16, iO15, iO14, this.l[15], 9);
        int iN2 = n(iO14, iN, iJ16, iO15, this.l[5], 7);
        int iN3 = n(iO15, iN2, iN, iJ16, this.l[1], 15);
        int iN4 = n(iJ16, iN3, iN2, iN, this.l[3], 11);
        int iN5 = n(iN, iN4, iN3, iN2, this.l[7], 8);
        int iN6 = n(iN2, iN5, iN4, iN3, this.l[14], 6);
        int iN7 = n(iN3, iN6, iN5, iN4, this.l[6], 6);
        int iN8 = n(iN4, iN7, iN6, iN5, this.l[9], 14);
        int iN9 = n(iN5, iN8, iN7, iN6, this.l[11], 12);
        int iN10 = n(iN6, iN9, iN8, iN7, this.l[8], 13);
        int iN11 = n(iN7, iN10, iN9, iN8, this.l[12], 5);
        int iN12 = n(iN8, iN11, iN10, iN9, this.l[2], 14);
        int iN13 = n(iN9, iN12, iN11, iN10, this.l[10], 13);
        int iN14 = n(iN10, iN13, iN12, iN11, this.l[0], 13);
        int iN15 = n(iN11, iN14, iN13, iN12, this.l[4], 7);
        int iN16 = n(iN12, iN15, iN14, iN13, this.l[13], 5);
        int iL = l(iK13, iK16, iN15, iK14, this.l[1], 11);
        int iL2 = l(iK14, iL, iK16, iN15, this.l[9], 12);
        int iL3 = l(iN15, iL2, iL, iK16, this.l[11], 14);
        int iL4 = l(iK16, iL3, iL2, iL, this.l[10], 15);
        int iL5 = l(iL, iL4, iL3, iL2, this.l[0], 14);
        int iL6 = l(iL2, iL5, iL4, iL3, this.l[8], 15);
        int iL7 = l(iL3, iL6, iL5, iL4, this.l[12], 9);
        int iL8 = l(iL4, iL7, iL6, iL5, this.l[4], 8);
        int iL9 = l(iL5, iL8, iL7, iL6, this.l[13], 9);
        int iL10 = l(iL6, iL9, iL8, iL7, this.l[3], 14);
        int iL11 = l(iL7, iL10, iL9, iL8, this.l[7], 5);
        int iL12 = l(iL8, iL11, iL10, iL9, this.l[15], 6);
        int iL13 = l(iL9, iL12, iL11, iL10, this.l[14], 8);
        int iL14 = l(iL10, iL13, iL12, iL11, this.l[5], 6);
        int iL15 = l(iL11, iL14, iL13, iL12, this.l[6], 5);
        int iL16 = l(iL12, iL15, iL14, iL13, this.l[2], 12);
        int iM = m(iN13, iN16, iK15, iN14, this.l[8], 15);
        int iM2 = m(iN14, iM, iN16, iK15, this.l[6], 5);
        int iM3 = m(iK15, iM2, iM, iN16, this.l[4], 8);
        int iM4 = m(iN16, iM3, iM2, iM, this.l[1], 11);
        int iM5 = m(iM, iM4, iM3, iM2, this.l[3], 14);
        int iM6 = m(iM2, iM5, iM4, iM3, this.l[11], 14);
        int iM7 = m(iM3, iM6, iM5, iM4, this.l[15], 6);
        int iM8 = m(iM4, iM7, iM6, iM5, this.l[0], 14);
        int iM9 = m(iM5, iM8, iM7, iM6, this.l[5], 6);
        int iM10 = m(iM6, iM9, iM8, iM7, this.l[12], 9);
        int iM11 = m(iM7, iM10, iM9, iM8, this.l[2], 12);
        int iM12 = m(iM8, iM11, iM10, iM9, this.l[13], 9);
        int iM13 = m(iM9, iM12, iM11, iM10, this.l[9], 12);
        int iM14 = m(iM10, iM13, iM12, iM11, this.l[7], 5);
        int iM15 = m(iM11, iM14, iM13, iM12, this.l[10], 15);
        int iM16 = m(iM12, iM15, iM14, iM13, this.l[14], 8);
        this.f4342d += iL13;
        this.f4343e += iL16;
        this.f4344f += iL15;
        this.f4345g += iM14;
        this.f4346h += iM13;
        this.f4347i += iM16;
        this.j += iM15;
        this.k += iL14;
        this.m = 0;
        int i26 = 0;
        while (true) {
            int[] iArr = this.l;
            if (i26 == iArr.length) {
                return;
            }
            iArr[i26] = 0;
            i26++;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.m > 14) {
            f();
        }
        int[] iArr = this.l;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 32;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int[] iArr = this.l;
        int i3 = this.m;
        int i4 = i3 + 1;
        this.m = i4;
        iArr[i3] = ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        if (i4 == 16) {
            f();
        }
    }

    public final int i(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + s(i3, i4, i5) + i6, i7);
    }

    public final int j(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + t(i3, i4, i5) + i6 + 1518500249, i7);
    }

    public final int k(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + u(i3, i4, i5) + i6 + 1859775393, i7);
    }

    public final int l(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(((i2 + v(i3, i4, i5)) + i6) - 1894007588, i7);
    }

    public final int m(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + s(i3, i4, i5) + i6, i7);
    }

    public final int n(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + t(i3, i4, i5) + i6 + 1836072691, i7);
    }

    public final int o(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + u(i3, i4, i5) + i6 + 1548603684, i7);
    }

    public final int p(int i2, int i3, int i4, int i5, int i6, int i7) {
        return q(i2 + v(i3, i4, i5) + i6 + 1352829926, i7);
    }

    public final int q(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    public final void r(q qVar) {
        super.d(qVar);
        this.f4342d = qVar.f4342d;
        this.f4343e = qVar.f4343e;
        this.f4344f = qVar.f4344f;
        this.f4345g = qVar.f4345g;
        this.f4346h = qVar.f4346h;
        this.f4347i = qVar.f4347i;
        this.j = qVar.j;
        this.k = qVar.k;
        int[] iArr = qVar.l;
        System.arraycopy(iArr, 0, this.l, 0, iArr.length);
        this.m = qVar.m;
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4342d = 1732584193;
        this.f4343e = -271733879;
        this.f4344f = -1732584194;
        this.f4345g = 271733878;
        this.f4346h = 1985229328;
        this.f4347i = -19088744;
        this.j = -1985229329;
        this.k = 19088743;
        this.m = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.l;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public final int s(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    public final int t(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    public final int u(int i2, int i3, int i4) {
        return (i2 | (~i3)) ^ i4;
    }

    public final int v(int i2, int i3, int i4) {
        return (i2 & i4) | (i3 & (~i4));
    }

    public final void w(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }
}

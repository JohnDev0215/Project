package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class o extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4331e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4332f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4333g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f4334h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4335i;

    public o() {
        this.f4334h = new int[16];
        reset();
    }

    public o(o oVar) {
        super(oVar);
        this.f4334h = new int[16];
        r(oVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        r((o) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new o(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        w(this.f4330d, bArr, i2);
        w(this.f4331e, bArr, i2 + 4);
        w(this.f4332f, bArr, i2 + 8);
        w(this.f4333g, bArr, i2 + 12);
        reset();
        return 16;
    }

    @Override // g.a.b.a0.h
    public void f() {
        int i2 = this.f4330d;
        int i3 = this.f4331e;
        int i4 = this.f4332f;
        int i5 = this.f4333g;
        int i6 = i(i2, i3, i4, i5, this.f4334h[0], 11);
        int i7 = i(i5, i6, i3, i4, this.f4334h[1], 14);
        int i8 = i(i4, i7, i6, i3, this.f4334h[2], 15);
        int i9 = i(i3, i8, i7, i6, this.f4334h[3], 12);
        int i10 = i(i6, i9, i8, i7, this.f4334h[4], 5);
        int i11 = i(i7, i10, i9, i8, this.f4334h[5], 8);
        int i12 = i(i8, i11, i10, i9, this.f4334h[6], 7);
        int i13 = i(i9, i12, i11, i10, this.f4334h[7], 9);
        int i14 = i(i10, i13, i12, i11, this.f4334h[8], 11);
        int i15 = i(i11, i14, i13, i12, this.f4334h[9], 13);
        int i16 = i(i12, i15, i14, i13, this.f4334h[10], 14);
        int i17 = i(i13, i16, i15, i14, this.f4334h[11], 15);
        int i18 = i(i14, i17, i16, i15, this.f4334h[12], 6);
        int i19 = i(i15, i18, i17, i16, this.f4334h[13], 7);
        int i20 = i(i16, i19, i18, i17, this.f4334h[14], 9);
        int i21 = i(i17, i20, i19, i18, this.f4334h[15], 8);
        int iJ = j(i18, i21, i20, i19, this.f4334h[7], 7);
        int iJ2 = j(i19, iJ, i21, i20, this.f4334h[4], 6);
        int iJ3 = j(i20, iJ2, iJ, i21, this.f4334h[13], 8);
        int iJ4 = j(i21, iJ3, iJ2, iJ, this.f4334h[1], 13);
        int iJ5 = j(iJ, iJ4, iJ3, iJ2, this.f4334h[10], 11);
        int iJ6 = j(iJ2, iJ5, iJ4, iJ3, this.f4334h[6], 9);
        int iJ7 = j(iJ3, iJ6, iJ5, iJ4, this.f4334h[15], 7);
        int iJ8 = j(iJ4, iJ7, iJ6, iJ5, this.f4334h[3], 15);
        int iJ9 = j(iJ5, iJ8, iJ7, iJ6, this.f4334h[12], 7);
        int iJ10 = j(iJ6, iJ9, iJ8, iJ7, this.f4334h[0], 12);
        int iJ11 = j(iJ7, iJ10, iJ9, iJ8, this.f4334h[9], 15);
        int iJ12 = j(iJ8, iJ11, iJ10, iJ9, this.f4334h[5], 9);
        int iJ13 = j(iJ9, iJ12, iJ11, iJ10, this.f4334h[2], 11);
        int iJ14 = j(iJ10, iJ13, iJ12, iJ11, this.f4334h[14], 7);
        int iJ15 = j(iJ11, iJ14, iJ13, iJ12, this.f4334h[11], 13);
        int iJ16 = j(iJ12, iJ15, iJ14, iJ13, this.f4334h[8], 12);
        int iK = k(iJ13, iJ16, iJ15, iJ14, this.f4334h[3], 11);
        int iK2 = k(iJ14, iK, iJ16, iJ15, this.f4334h[10], 13);
        int iK3 = k(iJ15, iK2, iK, iJ16, this.f4334h[14], 6);
        int iK4 = k(iJ16, iK3, iK2, iK, this.f4334h[4], 7);
        int iK5 = k(iK, iK4, iK3, iK2, this.f4334h[9], 14);
        int iK6 = k(iK2, iK5, iK4, iK3, this.f4334h[15], 9);
        int iK7 = k(iK3, iK6, iK5, iK4, this.f4334h[8], 13);
        int iK8 = k(iK4, iK7, iK6, iK5, this.f4334h[1], 15);
        int iK9 = k(iK5, iK8, iK7, iK6, this.f4334h[2], 14);
        int iK10 = k(iK6, iK9, iK8, iK7, this.f4334h[7], 8);
        int iK11 = k(iK7, iK10, iK9, iK8, this.f4334h[0], 13);
        int iK12 = k(iK8, iK11, iK10, iK9, this.f4334h[6], 6);
        int iK13 = k(iK9, iK12, iK11, iK10, this.f4334h[13], 5);
        int iK14 = k(iK10, iK13, iK12, iK11, this.f4334h[11], 12);
        int iK15 = k(iK11, iK14, iK13, iK12, this.f4334h[5], 7);
        int iK16 = k(iK12, iK15, iK14, iK13, this.f4334h[12], 5);
        int iL = l(iK13, iK16, iK15, iK14, this.f4334h[1], 11);
        int iL2 = l(iK14, iL, iK16, iK15, this.f4334h[9], 12);
        int iL3 = l(iK15, iL2, iL, iK16, this.f4334h[11], 14);
        int iL4 = l(iK16, iL3, iL2, iL, this.f4334h[10], 15);
        int iL5 = l(iL, iL4, iL3, iL2, this.f4334h[0], 14);
        int iL6 = l(iL2, iL5, iL4, iL3, this.f4334h[8], 15);
        int iL7 = l(iL3, iL6, iL5, iL4, this.f4334h[12], 9);
        int iL8 = l(iL4, iL7, iL6, iL5, this.f4334h[4], 8);
        int iL9 = l(iL5, iL8, iL7, iL6, this.f4334h[13], 9);
        int iL10 = l(iL6, iL9, iL8, iL7, this.f4334h[3], 14);
        int iL11 = l(iL7, iL10, iL9, iL8, this.f4334h[7], 5);
        int iL12 = l(iL8, iL11, iL10, iL9, this.f4334h[15], 6);
        int iL13 = l(iL9, iL12, iL11, iL10, this.f4334h[14], 8);
        int iL14 = l(iL10, iL13, iL12, iL11, this.f4334h[5], 6);
        int iL15 = l(iL11, iL14, iL13, iL12, this.f4334h[6], 5);
        int iL16 = l(iL12, iL15, iL14, iL13, this.f4334h[2], 12);
        int iP = p(i2, i3, i4, i5, this.f4334h[5], 8);
        int iP2 = p(i5, iP, i3, i4, this.f4334h[14], 9);
        int iP3 = p(i4, iP2, iP, i3, this.f4334h[7], 9);
        int iP4 = p(i3, iP3, iP2, iP, this.f4334h[0], 11);
        int iP5 = p(iP, iP4, iP3, iP2, this.f4334h[9], 13);
        int iP6 = p(iP2, iP5, iP4, iP3, this.f4334h[2], 15);
        int iP7 = p(iP3, iP6, iP5, iP4, this.f4334h[11], 15);
        int iP8 = p(iP4, iP7, iP6, iP5, this.f4334h[4], 5);
        int iP9 = p(iP5, iP8, iP7, iP6, this.f4334h[13], 7);
        int iP10 = p(iP6, iP9, iP8, iP7, this.f4334h[6], 7);
        int iP11 = p(iP7, iP10, iP9, iP8, this.f4334h[15], 8);
        int iP12 = p(iP8, iP11, iP10, iP9, this.f4334h[8], 11);
        int iP13 = p(iP9, iP12, iP11, iP10, this.f4334h[1], 14);
        int iP14 = p(iP10, iP13, iP12, iP11, this.f4334h[10], 14);
        int iP15 = p(iP11, iP14, iP13, iP12, this.f4334h[3], 12);
        int iP16 = p(iP12, iP15, iP14, iP13, this.f4334h[12], 6);
        int iO = o(iP13, iP16, iP15, iP14, this.f4334h[6], 9);
        int iO2 = o(iP14, iO, iP16, iP15, this.f4334h[11], 13);
        int iO3 = o(iP15, iO2, iO, iP16, this.f4334h[3], 15);
        int iO4 = o(iP16, iO3, iO2, iO, this.f4334h[7], 7);
        int iO5 = o(iO, iO4, iO3, iO2, this.f4334h[0], 12);
        int iO6 = o(iO2, iO5, iO4, iO3, this.f4334h[13], 8);
        int iO7 = o(iO3, iO6, iO5, iO4, this.f4334h[5], 9);
        int iO8 = o(iO4, iO7, iO6, iO5, this.f4334h[10], 11);
        int iO9 = o(iO5, iO8, iO7, iO6, this.f4334h[14], 7);
        int iO10 = o(iO6, iO9, iO8, iO7, this.f4334h[15], 7);
        int iO11 = o(iO7, iO10, iO9, iO8, this.f4334h[8], 12);
        int iO12 = o(iO8, iO11, iO10, iO9, this.f4334h[12], 7);
        int iO13 = o(iO9, iO12, iO11, iO10, this.f4334h[4], 6);
        int iO14 = o(iO10, iO13, iO12, iO11, this.f4334h[9], 15);
        int iO15 = o(iO11, iO14, iO13, iO12, this.f4334h[1], 13);
        int iO16 = o(iO12, iO15, iO14, iO13, this.f4334h[2], 11);
        int iN = n(iO13, iO16, iO15, iO14, this.f4334h[15], 9);
        int iN2 = n(iO14, iN, iO16, iO15, this.f4334h[5], 7);
        int iN3 = n(iO15, iN2, iN, iO16, this.f4334h[1], 15);
        int iN4 = n(iO16, iN3, iN2, iN, this.f4334h[3], 11);
        int iN5 = n(iN, iN4, iN3, iN2, this.f4334h[7], 8);
        int iN6 = n(iN2, iN5, iN4, iN3, this.f4334h[14], 6);
        int iN7 = n(iN3, iN6, iN5, iN4, this.f4334h[6], 6);
        int iN8 = n(iN4, iN7, iN6, iN5, this.f4334h[9], 14);
        int iN9 = n(iN5, iN8, iN7, iN6, this.f4334h[11], 12);
        int iN10 = n(iN6, iN9, iN8, iN7, this.f4334h[8], 13);
        int iN11 = n(iN7, iN10, iN9, iN8, this.f4334h[12], 5);
        int iN12 = n(iN8, iN11, iN10, iN9, this.f4334h[2], 14);
        int iN13 = n(iN9, iN12, iN11, iN10, this.f4334h[10], 13);
        int iN14 = n(iN10, iN13, iN12, iN11, this.f4334h[0], 13);
        int iN15 = n(iN11, iN14, iN13, iN12, this.f4334h[4], 7);
        int iN16 = n(iN12, iN15, iN14, iN13, this.f4334h[13], 5);
        int iM = m(iN13, iN16, iN15, iN14, this.f4334h[8], 15);
        int iM2 = m(iN14, iM, iN16, iN15, this.f4334h[6], 5);
        int iM3 = m(iN15, iM2, iM, iN16, this.f4334h[4], 8);
        int iM4 = m(iN16, iM3, iM2, iM, this.f4334h[1], 11);
        int iM5 = m(iM, iM4, iM3, iM2, this.f4334h[3], 14);
        int iM6 = m(iM2, iM5, iM4, iM3, this.f4334h[11], 14);
        int iM7 = m(iM3, iM6, iM5, iM4, this.f4334h[15], 6);
        int iM8 = m(iM4, iM7, iM6, iM5, this.f4334h[0], 14);
        int iM9 = m(iM5, iM8, iM7, iM6, this.f4334h[5], 6);
        int iM10 = m(iM6, iM9, iM8, iM7, this.f4334h[12], 9);
        int iM11 = m(iM7, iM10, iM9, iM8, this.f4334h[2], 12);
        int iM12 = m(iM8, iM11, iM10, iM9, this.f4334h[13], 9);
        int iM13 = m(iM9, iM12, iM11, iM10, this.f4334h[9], 12);
        int iM14 = m(iM10, iM13, iM12, iM11, this.f4334h[7], 5);
        int iM15 = m(iM11, iM14, iM13, iM12, this.f4334h[10], 15);
        int iM16 = m(iM12, iM15, iM14, iM13, this.f4334h[14], 8);
        int i22 = iM14 + iL15 + this.f4331e;
        this.f4331e = this.f4332f + iL14 + iM13;
        this.f4332f = this.f4333g + iL13 + iM16;
        this.f4333g = this.f4330d + iL16 + iM15;
        this.f4330d = i22;
        this.f4335i = 0;
        int i23 = 0;
        while (true) {
            int[] iArr = this.f4334h;
            if (i23 == iArr.length) {
                return;
            }
            iArr[i23] = 0;
            i23++;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.f4335i > 14) {
            f();
        }
        int[] iArr = this.f4334h;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 16;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int[] iArr = this.f4334h;
        int i3 = this.f4335i;
        int i4 = i3 + 1;
        this.f4335i = i4;
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

    public final void r(o oVar) {
        super.d(oVar);
        this.f4330d = oVar.f4330d;
        this.f4331e = oVar.f4331e;
        this.f4332f = oVar.f4332f;
        this.f4333g = oVar.f4333g;
        int[] iArr = oVar.f4334h;
        System.arraycopy(iArr, 0, this.f4334h, 0, iArr.length);
        this.f4335i = oVar.f4335i;
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4330d = 1732584193;
        this.f4331e = -271733879;
        this.f4332f = -1732584194;
        this.f4333g = 271733878;
        this.f4335i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f4334h;
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

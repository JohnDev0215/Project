package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class s extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4354d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4355e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4356f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4357g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4358h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f4359i;
    public int j;

    public s() {
        this.f4359i = new int[80];
        reset();
    }

    public s(s sVar) {
        super(sVar);
        this.f4359i = new int[80];
        i(sVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        s sVar = (s) eVar;
        super.d(sVar);
        i(sVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new s(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        g.a.f.f.d(this.f4354d, bArr, i2);
        g.a.f.f.d(this.f4355e, bArr, i2 + 4);
        g.a.f.f.d(this.f4356f, bArr, i2 + 8);
        g.a.f.f.d(this.f4357g, bArr, i2 + 12);
        g.a.f.f.d(this.f4358h, bArr, i2 + 16);
        reset();
        return 20;
    }

    @Override // g.a.b.a0.h
    public void f() {
        for (int i2 = 16; i2 < 80; i2++) {
            int[] iArr = this.f4359i;
            int i3 = ((iArr[i2 - 3] ^ iArr[i2 - 8]) ^ iArr[i2 - 14]) ^ iArr[i2 - 16];
            iArr[i2] = (i3 >>> 31) | (i3 << 1);
        }
        int iL = this.f4354d;
        int iL2 = this.f4355e;
        int i4 = this.f4356f;
        int i5 = this.f4357g;
        int i6 = this.f4358h;
        int i7 = 0;
        int i8 = 0;
        while (i7 < 4) {
            int i9 = i8 + 1;
            int iJ = i6 + ((iL << 5) | (iL >>> 27)) + j(iL2, i4, i5) + this.f4359i[i8] + 1518500249;
            int i10 = (iL2 >>> 2) | (iL2 << 30);
            int i11 = i9 + 1;
            int iJ2 = i5 + ((iJ << 5) | (iJ >>> 27)) + j(iL, i10, i4) + this.f4359i[i9] + 1518500249;
            int i12 = (iL >>> 2) | (iL << 30);
            int i13 = i11 + 1;
            int iJ3 = i4 + ((iJ2 << 5) | (iJ2 >>> 27)) + j(iJ, i12, i10) + this.f4359i[i11] + 1518500249;
            i6 = (iJ >>> 2) | (iJ << 30);
            int i14 = i13 + 1;
            iL2 = i10 + ((iJ3 << 5) | (iJ3 >>> 27)) + j(iJ2, i6, i12) + this.f4359i[i13] + 1518500249;
            i5 = (iJ2 >>> 2) | (iJ2 << 30);
            iL = i12 + ((iL2 << 5) | (iL2 >>> 27)) + j(iJ3, i5, i6) + this.f4359i[i14] + 1518500249;
            i4 = (iJ3 >>> 2) | (iJ3 << 30);
            i7++;
            i8 = i14 + 1;
        }
        int i15 = 0;
        while (i15 < 4) {
            int i16 = i8 + 1;
            int iL3 = i6 + ((iL << 5) | (iL >>> 27)) + l(iL2, i4, i5) + this.f4359i[i8] + 1859775393;
            int i17 = (iL2 >>> 2) | (iL2 << 30);
            int i18 = i16 + 1;
            int iL4 = i5 + ((iL3 << 5) | (iL3 >>> 27)) + l(iL, i17, i4) + this.f4359i[i16] + 1859775393;
            int i19 = (iL >>> 2) | (iL << 30);
            int i20 = i18 + 1;
            int iL5 = i4 + ((iL4 << 5) | (iL4 >>> 27)) + l(iL3, i19, i17) + this.f4359i[i18] + 1859775393;
            i6 = (iL3 >>> 2) | (iL3 << 30);
            int i21 = i20 + 1;
            iL2 = i17 + ((iL5 << 5) | (iL5 >>> 27)) + l(iL4, i6, i19) + this.f4359i[i20] + 1859775393;
            i5 = (iL4 >>> 2) | (iL4 << 30);
            iL = i19 + ((iL2 << 5) | (iL2 >>> 27)) + l(iL5, i5, i6) + this.f4359i[i21] + 1859775393;
            i4 = (iL5 >>> 2) | (iL5 << 30);
            i15++;
            i8 = i21 + 1;
        }
        int i22 = 0;
        while (i22 < 4) {
            int iK = i6 + (((((iL << 5) | (iL >>> 27)) + k(iL2, i4, i5)) + this.f4359i[i8]) - 1894007588);
            int iK2 = i5 + (((((iK << 5) | (iK >>> 27)) + k(iL, r2, i4)) + this.f4359i[r12]) - 1894007588);
            int iK3 = i4 + (((((iK2 << 5) | (iK2 >>> 27)) + k(iK, r1, r2)) + this.f4359i[r13]) - 1894007588);
            i6 = (iK >>> 2) | (iK << 30);
            iL2 = ((iL2 >>> 2) | (iL2 << 30)) + (((((iK3 << 5) | (iK3 >>> 27)) + k(iK2, i6, r1)) + this.f4359i[r12]) - 1894007588);
            i5 = (iK2 >>> 2) | (iK2 << 30);
            iL = ((iL >>> 2) | (iL << 30)) + (((((iL2 << 5) | (iL2 >>> 27)) + k(iK3, i5, i6)) + this.f4359i[r13]) - 1894007588);
            i4 = (iK3 >>> 2) | (iK3 << 30);
            i22++;
            i8 = i8 + 1 + 1 + 1 + 1 + 1;
        }
        int i23 = 0;
        while (i23 <= 3) {
            int iL6 = i6 + (((((iL << 5) | (iL >>> 27)) + l(iL2, i4, i5)) + this.f4359i[i8]) - 899497514);
            int iL7 = i5 + (((((iL6 << 5) | (iL6 >>> 27)) + l(iL, r2, i4)) + this.f4359i[r11]) - 899497514);
            int iL8 = i4 + (((((iL7 << 5) | (iL7 >>> 27)) + l(iL6, r1, r2)) + this.f4359i[r12]) - 899497514);
            i6 = (iL6 >>> 2) | (iL6 << 30);
            iL2 = ((iL2 >>> 2) | (iL2 << 30)) + (((((iL8 << 5) | (iL8 >>> 27)) + l(iL7, i6, r1)) + this.f4359i[r11]) - 899497514);
            i5 = (iL7 >>> 2) | (iL7 << 30);
            iL = ((iL >>> 2) | (iL << 30)) + (((((iL2 << 5) | (iL2 >>> 27)) + l(iL8, i5, i6)) + this.f4359i[r12]) - 899497514);
            i4 = (iL8 >>> 2) | (iL8 << 30);
            i23++;
            i8 = i8 + 1 + 1 + 1 + 1 + 1;
        }
        this.f4354d += iL;
        this.f4355e += iL2;
        this.f4356f += i4;
        this.f4357g += i5;
        this.f4358h += i6;
        this.j = 0;
        for (int i24 = 0; i24 < 16; i24++) {
            this.f4359i[i24] = 0;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.j > 14) {
            f();
        }
        int[] iArr = this.f4359i;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) j;
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "SHA-1";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 20;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int i3 = bArr[i2] << 24;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = (bArr[i6 + 1] & 255) | i5 | ((bArr[i6] & 255) << 8);
        int[] iArr = this.f4359i;
        int i8 = this.j;
        iArr[i8] = i7;
        int i9 = i8 + 1;
        this.j = i9;
        if (i9 == 16) {
            f();
        }
    }

    public final void i(s sVar) {
        this.f4354d = sVar.f4354d;
        this.f4355e = sVar.f4355e;
        this.f4356f = sVar.f4356f;
        this.f4357g = sVar.f4357g;
        this.f4358h = sVar.f4358h;
        int[] iArr = sVar.f4359i;
        System.arraycopy(iArr, 0, this.f4359i, 0, iArr.length);
        this.j = sVar.j;
    }

    public final int j(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    public final int k(int i2, int i3, int i4) {
        return (i2 & i4) | (i2 & i3) | (i3 & i4);
    }

    public final int l(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4354d = 1732584193;
        this.f4355e = -271733879;
        this.f4356f = -1732584194;
        this.f4357g = 271733878;
        this.f4358h = -1009589776;
        this.j = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f4359i;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }
}

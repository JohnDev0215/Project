package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class t extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f4360d = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4361e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4362f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4363g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4364h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4365i;
    public int j;
    public int k;
    public int l;
    public int[] m;
    public int n;

    public t() {
        this.m = new int[64];
        reset();
    }

    public t(t tVar) {
        super(tVar);
        this.m = new int[64];
        o(tVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        o((t) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new t(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        g.a.f.f.d(this.f4361e, bArr, i2);
        g.a.f.f.d(this.f4362f, bArr, i2 + 4);
        g.a.f.f.d(this.f4363g, bArr, i2 + 8);
        g.a.f.f.d(this.f4364h, bArr, i2 + 12);
        g.a.f.f.d(this.f4365i, bArr, i2 + 16);
        g.a.f.f.d(this.j, bArr, i2 + 20);
        g.a.f.f.d(this.k, bArr, i2 + 24);
        reset();
        return 28;
    }

    @Override // g.a.b.a0.h
    public void f() {
        for (int i2 = 16; i2 <= 63; i2++) {
            int[] iArr = this.m;
            int iN = n(iArr[i2 - 2]);
            int[] iArr2 = this.m;
            iArr[i2] = iN + iArr2[i2 - 7] + m(iArr2[i2 - 15]) + this.m[i2 - 16];
        }
        int iK = this.f4361e;
        int iK2 = this.f4362f;
        int iK3 = this.f4363g;
        int iK4 = this.f4364h;
        int i3 = this.f4365i;
        int i4 = this.j;
        int i5 = this.k;
        int i6 = this.l;
        int i7 = 0;
        for (int i8 = 0; i8 < 8; i8++) {
            int iL = l(i3) + i(i3, i4, i5);
            int[] iArr3 = f4360d;
            int i9 = i6 + iL + iArr3[i7] + this.m[i7];
            int i10 = iK4 + i9;
            int iK5 = i9 + k(iK) + j(iK, iK2, iK3);
            int i11 = i7 + 1;
            int iL2 = i5 + l(i10) + i(i10, i3, i4) + iArr3[i11] + this.m[i11];
            int i12 = iK3 + iL2;
            int iK6 = iL2 + k(iK5) + j(iK5, iK, iK2);
            int i13 = i11 + 1;
            int iL3 = i4 + l(i12) + i(i12, i10, i3) + iArr3[i13] + this.m[i13];
            int i14 = iK2 + iL3;
            int iK7 = iL3 + k(iK6) + j(iK6, iK5, iK);
            int i15 = i13 + 1;
            int iL4 = i3 + l(i14) + i(i14, i12, i10) + iArr3[i15] + this.m[i15];
            int i16 = iK + iL4;
            int iK8 = iL4 + k(iK7) + j(iK7, iK6, iK5);
            int i17 = i15 + 1;
            int iL5 = i10 + l(i16) + i(i16, i14, i12) + iArr3[i17] + this.m[i17];
            i6 = iK5 + iL5;
            iK4 = iL5 + k(iK8) + j(iK8, iK7, iK6);
            int i18 = i17 + 1;
            int iL6 = i12 + l(i6) + i(i6, i16, i14) + iArr3[i18] + this.m[i18];
            i5 = iK6 + iL6;
            iK3 = iL6 + k(iK4) + j(iK4, iK8, iK7);
            int i19 = i18 + 1;
            int iL7 = i14 + l(i5) + i(i5, i6, i16) + iArr3[i19] + this.m[i19];
            i4 = iK7 + iL7;
            iK2 = iL7 + k(iK3) + j(iK3, iK4, iK8);
            int i20 = i19 + 1;
            int iL8 = i16 + l(i4) + i(i4, i5, i6) + iArr3[i20] + this.m[i20];
            i3 = iK8 + iL8;
            iK = iL8 + k(iK2) + j(iK2, iK3, iK4);
            i7 = i20 + 1;
        }
        this.f4361e += iK;
        this.f4362f += iK2;
        this.f4363g += iK3;
        this.f4364h += iK4;
        this.f4365i += i3;
        this.j += i4;
        this.k += i5;
        this.l += i6;
        this.n = 0;
        for (int i21 = 0; i21 < 16; i21++) {
            this.m[i21] = 0;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.n > 14) {
            f();
        }
        int[] iArr = this.m;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "SHA-224";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 28;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int i3 = bArr[i2] << 24;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = (bArr[i6 + 1] & 255) | i5 | ((bArr[i6] & 255) << 8);
        int[] iArr = this.m;
        int i8 = this.n;
        iArr[i8] = i7;
        int i9 = i8 + 1;
        this.n = i9;
        if (i9 == 16) {
            f();
        }
    }

    public final int i(int i2, int i3, int i4) {
        return ((~i2) & i4) ^ (i3 & i2);
    }

    public final int j(int i2, int i3, int i4) {
        return ((i2 & i4) ^ (i2 & i3)) ^ (i3 & i4);
    }

    public final int k(int i2) {
        return ((i2 << 10) | (i2 >>> 22)) ^ (((i2 >>> 2) | (i2 << 30)) ^ ((i2 >>> 13) | (i2 << 19)));
    }

    public final int l(int i2) {
        return ((i2 << 7) | (i2 >>> 25)) ^ (((i2 >>> 6) | (i2 << 26)) ^ ((i2 >>> 11) | (i2 << 21)));
    }

    public final int m(int i2) {
        return (i2 >>> 3) ^ (((i2 >>> 7) | (i2 << 25)) ^ ((i2 >>> 18) | (i2 << 14)));
    }

    public final int n(int i2) {
        return (i2 >>> 10) ^ (((i2 >>> 17) | (i2 << 15)) ^ ((i2 >>> 19) | (i2 << 13)));
    }

    public final void o(t tVar) {
        super.d(tVar);
        this.f4361e = tVar.f4361e;
        this.f4362f = tVar.f4362f;
        this.f4363g = tVar.f4363g;
        this.f4364h = tVar.f4364h;
        this.f4365i = tVar.f4365i;
        this.j = tVar.j;
        this.k = tVar.k;
        this.l = tVar.l;
        int[] iArr = tVar.m;
        System.arraycopy(iArr, 0, this.m, 0, iArr.length);
        this.n = tVar.n;
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4361e = -1056596264;
        this.f4362f = 914150663;
        this.f4363g = 812702999;
        this.f4364h = -150054599;
        this.f4365i = -4191439;
        this.j = 1750603025;
        this.k = 1694076839;
        this.l = -1090891868;
        this.n = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.m;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }
}

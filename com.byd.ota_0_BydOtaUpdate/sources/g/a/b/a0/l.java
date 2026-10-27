package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class l extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4317d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4319f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4320g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f4321h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4322i;

    public l() {
        this.f4321h = new int[16];
        reset();
    }

    public l(l lVar) {
        super(lVar);
        this.f4321h = new int[16];
        l(lVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        l((l) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new l(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        n(this.f4317d, bArr, i2);
        n(this.f4318e, bArr, i2 + 4);
        n(this.f4319f, bArr, i2 + 8);
        n(this.f4320g, bArr, i2 + 12);
        reset();
        return 16;
    }

    @Override // g.a.b.a0.h
    public void f() {
        int i2 = this.f4317d;
        int i3 = this.f4318e;
        int i4 = this.f4319f;
        int i5 = this.f4320g;
        int iM = m(i2 + i(i3, i4, i5) + this.f4321h[0], 3);
        int iM2 = m(i5 + i(iM, i3, i4) + this.f4321h[1], 7);
        int iM3 = m(i4 + i(iM2, iM, i3) + this.f4321h[2], 11);
        int iM4 = m(i3 + i(iM3, iM2, iM) + this.f4321h[3], 19);
        int iM5 = m(iM + i(iM4, iM3, iM2) + this.f4321h[4], 3);
        int iM6 = m(iM2 + i(iM5, iM4, iM3) + this.f4321h[5], 7);
        int iM7 = m(iM3 + i(iM6, iM5, iM4) + this.f4321h[6], 11);
        int iM8 = m(iM4 + i(iM7, iM6, iM5) + this.f4321h[7], 19);
        int iM9 = m(iM5 + i(iM8, iM7, iM6) + this.f4321h[8], 3);
        int iM10 = m(iM6 + i(iM9, iM8, iM7) + this.f4321h[9], 7);
        int iM11 = m(iM7 + i(iM10, iM9, iM8) + this.f4321h[10], 11);
        int iM12 = m(iM8 + i(iM11, iM10, iM9) + this.f4321h[11], 19);
        int iM13 = m(iM9 + i(iM12, iM11, iM10) + this.f4321h[12], 3);
        int iM14 = m(iM10 + i(iM13, iM12, iM11) + this.f4321h[13], 7);
        int iM15 = m(iM11 + i(iM14, iM13, iM12) + this.f4321h[14], 11);
        int iM16 = m(iM12 + i(iM15, iM14, iM13) + this.f4321h[15], 19);
        int iM17 = m(iM13 + j(iM16, iM15, iM14) + this.f4321h[0] + 1518500249, 3);
        int iM18 = m(iM14 + j(iM17, iM16, iM15) + this.f4321h[4] + 1518500249, 5);
        int iM19 = m(iM15 + j(iM18, iM17, iM16) + this.f4321h[8] + 1518500249, 9);
        int iM20 = m(iM16 + j(iM19, iM18, iM17) + this.f4321h[12] + 1518500249, 13);
        int iM21 = m(iM17 + j(iM20, iM19, iM18) + this.f4321h[1] + 1518500249, 3);
        int iM22 = m(iM18 + j(iM21, iM20, iM19) + this.f4321h[5] + 1518500249, 5);
        int iM23 = m(iM19 + j(iM22, iM21, iM20) + this.f4321h[9] + 1518500249, 9);
        int iM24 = m(iM20 + j(iM23, iM22, iM21) + this.f4321h[13] + 1518500249, 13);
        int iM25 = m(iM21 + j(iM24, iM23, iM22) + this.f4321h[2] + 1518500249, 3);
        int iM26 = m(iM22 + j(iM25, iM24, iM23) + this.f4321h[6] + 1518500249, 5);
        int iM27 = m(iM23 + j(iM26, iM25, iM24) + this.f4321h[10] + 1518500249, 9);
        int iM28 = m(iM24 + j(iM27, iM26, iM25) + this.f4321h[14] + 1518500249, 13);
        int iM29 = m(iM25 + j(iM28, iM27, iM26) + this.f4321h[3] + 1518500249, 3);
        int iM30 = m(iM26 + j(iM29, iM28, iM27) + this.f4321h[7] + 1518500249, 5);
        int iM31 = m(iM27 + j(iM30, iM29, iM28) + this.f4321h[11] + 1518500249, 9);
        int iM32 = m(iM28 + j(iM31, iM30, iM29) + this.f4321h[15] + 1518500249, 13);
        int iM33 = m(iM29 + k(iM32, iM31, iM30) + this.f4321h[0] + 1859775393, 3);
        int iM34 = m(iM30 + k(iM33, iM32, iM31) + this.f4321h[8] + 1859775393, 9);
        int iM35 = m(iM31 + k(iM34, iM33, iM32) + this.f4321h[4] + 1859775393, 11);
        int iM36 = m(iM32 + k(iM35, iM34, iM33) + this.f4321h[12] + 1859775393, 15);
        int iM37 = m(iM33 + k(iM36, iM35, iM34) + this.f4321h[2] + 1859775393, 3);
        int iM38 = m(iM34 + k(iM37, iM36, iM35) + this.f4321h[10] + 1859775393, 9);
        int iM39 = m(iM35 + k(iM38, iM37, iM36) + this.f4321h[6] + 1859775393, 11);
        int iM40 = m(iM36 + k(iM39, iM38, iM37) + this.f4321h[14] + 1859775393, 15);
        int iM41 = m(iM37 + k(iM40, iM39, iM38) + this.f4321h[1] + 1859775393, 3);
        int iM42 = m(iM38 + k(iM41, iM40, iM39) + this.f4321h[9] + 1859775393, 9);
        int iM43 = m(iM39 + k(iM42, iM41, iM40) + this.f4321h[5] + 1859775393, 11);
        int iM44 = m(iM40 + k(iM43, iM42, iM41) + this.f4321h[13] + 1859775393, 15);
        int iM45 = m(iM41 + k(iM44, iM43, iM42) + this.f4321h[3] + 1859775393, 3);
        int iM46 = m(iM42 + k(iM45, iM44, iM43) + this.f4321h[11] + 1859775393, 9);
        int iM47 = m(iM43 + k(iM46, iM45, iM44) + this.f4321h[7] + 1859775393, 11);
        int iM48 = m(iM44 + k(iM47, iM46, iM45) + this.f4321h[15] + 1859775393, 15);
        this.f4317d += iM45;
        this.f4318e += iM48;
        this.f4319f += iM47;
        this.f4320g += iM46;
        this.f4322i = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f4321h;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.f4322i > 14) {
            f();
        }
        int[] iArr = this.f4321h;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "MD4";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 16;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int[] iArr = this.f4321h;
        int i3 = this.f4322i;
        int i4 = i3 + 1;
        this.f4322i = i4;
        iArr[i3] = ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        if (i4 == 16) {
            f();
        }
    }

    public final int i(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    public final int j(int i2, int i3, int i4) {
        return (i2 & i4) | (i2 & i3) | (i3 & i4);
    }

    public final int k(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    public final void l(l lVar) {
        super.d(lVar);
        this.f4317d = lVar.f4317d;
        this.f4318e = lVar.f4318e;
        this.f4319f = lVar.f4319f;
        this.f4320g = lVar.f4320g;
        int[] iArr = lVar.f4321h;
        System.arraycopy(iArr, 0, this.f4321h, 0, iArr.length);
        this.f4322i = lVar.f4322i;
    }

    public final int m(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    public final void n(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4317d = 1732584193;
        this.f4318e = -271733879;
        this.f4319f = -1732584194;
        this.f4320g = 271733878;
        this.f4322i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f4321h;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }
}

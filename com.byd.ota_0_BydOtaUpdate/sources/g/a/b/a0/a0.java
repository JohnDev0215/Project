package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f4212d = new int[64];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4213e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f4214f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4215g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f4216h;

    static {
        int i2;
        int i3 = 0;
        while (true) {
            if (i3 >= 16) {
                break;
            }
            f4212d[i3] = (2043430169 >>> (32 - i3)) | (2043430169 << i3);
            i3++;
        }
        for (i2 = 16; i2 < 64; i2++) {
            int i4 = i2 % 32;
            f4212d[i2] = (2055708042 >>> (32 - i4)) | (2055708042 << i4);
        }
    }

    public a0() {
        this.f4213e = new int[8];
        this.f4214f = new int[16];
        this.f4216h = new int[68];
        reset();
    }

    public a0(a0 a0Var) {
        super(a0Var);
        this.f4213e = new int[8];
        this.f4214f = new int[16];
        this.f4216h = new int[68];
        o(a0Var);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        a0 a0Var = (a0) eVar;
        super.d(a0Var);
        o(a0Var);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new a0(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        g.a.f.f.e(this.f4213e, bArr, i2);
        reset();
        return 32;
    }

    @Override // g.a.b.a0.h
    public void f() {
        int i2;
        int i3 = 0;
        while (true) {
            if (i3 >= 16) {
                break;
            }
            this.f4216h[i3] = this.f4214f[i3];
            i3++;
        }
        for (int i4 = 16; i4 < 68; i4++) {
            int[] iArr = this.f4216h;
            int i5 = iArr[i4 - 3];
            int i6 = iArr[i4 - 13];
            iArr[i4] = (n(((i5 >>> 17) | (i5 << 15)) ^ (iArr[i4 - 16] ^ iArr[i4 - 9])) ^ ((i6 >>> 25) | (i6 << 7))) ^ this.f4216h[i4 - 6];
        }
        int[] iArr2 = this.f4213e;
        int i7 = iArr2[0];
        int i8 = iArr2[1];
        int i9 = iArr2[2];
        int i10 = iArr2[3];
        int iM = iArr2[4];
        int i11 = iArr2[5];
        int i12 = iArr2[6];
        int i13 = iArr2[7];
        int i14 = 0;
        int i15 = i12;
        for (i2 = 16; i14 < i2; i2 = 16) {
            int i16 = (i7 << 12) | (i7 >>> 20);
            int i17 = i16 + iM + f4212d[i14];
            int i18 = (i17 << 7) | (i17 >>> 25);
            int[] iArr3 = this.f4216h;
            int i19 = iArr3[i14];
            int i20 = i19 ^ iArr3[i14 + 4];
            int i21 = i(i7, i8, i9) + i10;
            int iK = k(iM, i11, i15) + i13 + i18 + i19;
            int i22 = (i8 << 9) | (i8 >>> 23);
            int i23 = (i11 << 19) | (i11 >>> 13);
            i14++;
            i11 = iM;
            iM = m(iK);
            i10 = i9;
            i9 = i22;
            i13 = i15;
            i15 = i23;
            i8 = i7;
            i7 = i21 + (i18 ^ i16) + i20;
        }
        int i24 = i13;
        int iM2 = iM;
        int i25 = i15;
        int i26 = i10;
        int i27 = i9;
        int i28 = i8;
        int i29 = i7;
        int i30 = 16;
        while (i30 < 64) {
            int i31 = (i29 << 12) | (i29 >>> 20);
            int i32 = i31 + iM2 + f4212d[i30];
            int i33 = (i32 << 7) | (i32 >>> 25);
            int[] iArr4 = this.f4216h;
            int i34 = iArr4[i30];
            int i35 = i34 ^ iArr4[i30 + 4];
            int iJ = j(i29, i28, i27) + i26;
            int iL = l(iM2, i11, i25) + i24 + i33 + i34;
            int i36 = (i11 << 19) | (i11 >>> 13);
            i30++;
            i11 = iM2;
            iM2 = m(iL);
            i26 = i27;
            i27 = (i28 >>> 23) | (i28 << 9);
            i28 = i29;
            i29 = iJ + (i33 ^ i31) + i35;
            i24 = i25;
            i25 = i36;
        }
        int[] iArr5 = this.f4213e;
        iArr5[0] = i29 ^ iArr5[0];
        iArr5[1] = iArr5[1] ^ i28;
        iArr5[2] = iArr5[2] ^ i27;
        iArr5[3] = iArr5[3] ^ i26;
        iArr5[4] = iArr5[4] ^ iM2;
        iArr5[5] = iArr5[5] ^ i11;
        iArr5[6] = i25 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i24;
        this.f4215g = 0;
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        int i2 = this.f4215g;
        if (i2 > 14) {
            this.f4214f[i2] = 0;
            this.f4215g = i2 + 1;
            f();
        }
        while (true) {
            int i3 = this.f4215g;
            if (i3 >= 14) {
                int[] iArr = this.f4214f;
                int i4 = i3 + 1;
                this.f4215g = i4;
                iArr[i3] = (int) (j >>> 32);
                this.f4215g = i4 + 1;
                iArr[i4] = (int) j;
                return;
            }
            this.f4214f[i3] = 0;
            this.f4215g = i3 + 1;
        }
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 32;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int i3 = (bArr[i2] & 255) << 24;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = (bArr[i6 + 1] & 255) | i5 | ((bArr[i6] & 255) << 8);
        int[] iArr = this.f4214f;
        int i8 = this.f4215g;
        iArr[i8] = i7;
        int i9 = i8 + 1;
        this.f4215g = i9;
        if (i9 >= 16) {
            f();
        }
    }

    public final int i(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    public final int j(int i2, int i3, int i4) {
        return (i2 & i4) | (i2 & i3) | (i3 & i4);
    }

    public final int k(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    public final int l(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    public final int m(int i2) {
        return (i2 ^ ((i2 << 9) | (i2 >>> 23))) ^ ((i2 << 17) | (i2 >>> 15));
    }

    public final int n(int i2) {
        return (i2 ^ ((i2 << 15) | (i2 >>> 17))) ^ ((i2 << 23) | (i2 >>> 9));
    }

    public final void o(a0 a0Var) {
        int[] iArr = a0Var.f4213e;
        int[] iArr2 = this.f4213e;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = a0Var.f4214f;
        int[] iArr4 = this.f4214f;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.f4215g = a0Var.f4215g;
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        int[] iArr = this.f4213e;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.f4215g = 0;
    }
}

package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements g.a.b.p, g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4282a = {24, 35, 198, 232, 135, 184, 1, 79, 54, 166, 210, 245, 121, 111, 145, 82, 96, 188, 155, 142, 163, 12, 123, 53, 29, 224, 215, 194, 46, 75, 254, 87, 21, 119, 55, 229, 159, 240, 74, 218, 88, 201, 41, 10, 177, 160, 107, 133, 189, 93, 16, 244, 203, 62, 5, 103, 228, 39, 65, 139, 167, 125, 149, 216, 251, 238, 124, 102, 221, 23, 71, 158, 202, 45, 191, 7, 173, 90, 131, 51, 99, 2, 170, 113, 200, 25, 73, 217, 242, 227, 91, 136, 154, 38, 50, 176, 233, 15, 213, 128, 190, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, 174, 180, 84, 147, 34, 100, 241, 115, 18, 64, 8, 195, 236, 219, 161, 141, 61, 151, 0, 207, 43, 118, 130, 214, 27, 181, 175, 106, 80, 69, 243, 48, 239, 63, 85, 162, 234, 101, 186, 47, 192, 222, 28, 253, 77, 146, 117, 6, 138, 178, 230, 14, 31, 98, 212, 168, 150, 249, 197, 37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 252, 4, 81, 153, 109, 13, 250, 223, 126, 36, 59, 171, 206, 17, 143, 78, 183, 235, 60, 129, 148, 247, 185, 19, 44, 211, 231, 110, 196, 3, 86, 68, 127, 169, 42, 187, 193, 83, 220, 11, 157, 108, 49, 116, 246, 70, 172, 137, 20, 225, 22, 58, 105, 9, 112, 182, 208, 237, 204, 66, 152, 164, 40, 92, 248, 134};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long[] f4283b = new long[256];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long[] f4284c = new long[256];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long[] f4285d = new long[256];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long[] f4286e = new long[256];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long[] f4287f = new long[256];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long[] f4288g = new long[256];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long[] f4289h = new long[256];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final long[] f4290i = new long[256];
    public static final short[] j;
    public final long[] k;
    public byte[] l;
    public int m;
    public short[] n;
    public long[] o;
    public long[] p;
    public long[] q;
    public long[] r;
    public long[] s;

    static {
        short[] sArr = new short[32];
        j = sArr;
        sArr[31] = 8;
    }

    public e0() {
        this.k = new long[11];
        this.l = new byte[64];
        this.m = 0;
        this.n = new short[32];
        this.o = new long[8];
        this.p = new long[8];
        this.q = new long[8];
        this.r = new long[8];
        this.s = new long[8];
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = f4282a[i2];
            int i4 = i(i3 << 1);
            int i5 = i(i4 << 1);
            int i6 = i5 ^ i3;
            int i7 = i(i5 << 1);
            int i8 = i7 ^ i3;
            f4283b[i2] = j(i3, i3, i5, i3, i7, i6, i4, i8);
            f4284c[i2] = j(i8, i3, i3, i5, i3, i7, i6, i4);
            f4285d[i2] = j(i4, i8, i3, i3, i5, i3, i7, i6);
            f4286e[i2] = j(i6, i4, i8, i3, i3, i5, i3, i7);
            f4287f[i2] = j(i7, i6, i4, i8, i3, i3, i5, i3);
            f4288g[i2] = j(i3, i7, i6, i4, i8, i3, i3, i5);
            f4289h[i2] = j(i5, i3, i7, i6, i4, i8, i3, i3);
            f4290i[i2] = j(i3, i5, i3, i7, i6, i4, i8, i3);
        }
        this.k[0] = 0;
        for (int i9 = 1; i9 <= 10; i9++) {
            int i10 = (i9 - 1) * 8;
            this.k[i9] = (((((((f4283b[i10] & (-72057594037927936L)) ^ (f4284c[i10 + 1] & 71776119061217280L)) ^ (f4285d[i10 + 2] & 280375465082880L)) ^ (f4286e[i10 + 3] & 1095216660480L)) ^ (f4287f[i10 + 4] & 4278190080L)) ^ (f4288g[i10 + 5] & 16711680)) ^ (f4289h[i10 + 6] & 65280)) ^ (f4290i[i10 + 7] & 255);
        }
    }

    public e0(e0 e0Var) {
        this.k = new long[11];
        this.l = new byte[64];
        this.m = 0;
        this.n = new short[32];
        this.o = new long[8];
        this.p = new long[8];
        this.q = new long[8];
        this.r = new long[8];
        this.s = new long[8];
        a(e0Var);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        e0 e0Var = (e0) eVar;
        long[] jArr = e0Var.k;
        long[] jArr2 = this.k;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = e0Var.l;
        byte[] bArr2 = this.l;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.m = e0Var.m;
        short[] sArr = e0Var.n;
        short[] sArr2 = this.n;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = e0Var.o;
        long[] jArr4 = this.o;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = e0Var.p;
        long[] jArr6 = this.p;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = e0Var.q;
        long[] jArr8 = this.q;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = e0Var.r;
        long[] jArr10 = this.r;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = e0Var.s;
        long[] jArr12 = this.s;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new e0(this);
    }

    public final long d(byte[] bArr, int i2) {
        return (((long) bArr[i2 + 7]) & 255) | ((((long) bArr[i2 + 0]) & 255) << 56) | ((((long) bArr[i2 + 1]) & 255) << 48) | ((((long) bArr[i2 + 2]) & 255) << 40) | ((((long) bArr[i2 + 3]) & 255) << 32) | ((((long) bArr[i2 + 4]) & 255) << 24) | ((((long) bArr[i2 + 5]) & 255) << 16) | ((((long) bArr[i2 + 6]) & 255) << 8);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        g();
        for (int i3 = 0; i3 < 8; i3++) {
            e(this.o[i3], bArr, (i3 * 8) + i2);
        }
        reset();
        return getDigestSize();
    }

    public final void e(long j2, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i2 + i3] = (byte) ((j2 >> (56 - (i3 * 8))) & 255);
        }
    }

    public final byte[] f() {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr[i2] = (byte) (this.n[i2] & 255);
        }
        return bArr;
    }

    public final void g() {
        byte[] bArrF = f();
        byte[] bArr = this.l;
        int i2 = this.m;
        int i3 = i2 + 1;
        this.m = i3;
        bArr[i2] = (byte) (bArr[i2] | 128);
        if (i3 == bArr.length) {
            l(bArr, 0);
        }
        if (this.m > 32) {
            while (this.m != 0) {
                update((byte) 0);
            }
        }
        while (this.m <= 32) {
            update((byte) 0);
        }
        System.arraycopy(bArrF, 0, this.l, 32, bArrF.length);
        l(this.l, 0);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "Whirlpool";
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return 64;
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 64;
    }

    public final void h() {
        int i2 = 0;
        for (int length = this.n.length - 1; length >= 0; length--) {
            short[] sArr = this.n;
            int i3 = (sArr[length] & 255) + j[length] + i2;
            i2 = i3 >>> 8;
            sArr[length] = (short) (i3 & 255);
        }
    }

    public final int i(int i2) {
        return ((long) i2) >= 256 ? i2 ^ 285 : i2;
    }

    public final long j(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        return (((((((((long) i3) << 48) ^ (((long) i2) << 56)) ^ (((long) i4) << 40)) ^ (((long) i5) << 32)) ^ (((long) i6) << 24)) ^ (((long) i7) << 16)) ^ (((long) i8) << 8)) ^ ((long) i9);
    }

    public void k() {
        long[] jArr;
        for (int i2 = 0; i2 < 8; i2++) {
            long[] jArr2 = this.s;
            long j2 = this.r[i2];
            long[] jArr3 = this.p;
            long j3 = this.o[i2];
            jArr3[i2] = j3;
            jArr2[i2] = j2 ^ j3;
        }
        int i3 = 1;
        while (i3 <= 10) {
            int i4 = 0;
            while (i4 < 8) {
                long[] jArr4 = this.q;
                jArr4[i4] = 0;
                long j4 = jArr4[i4];
                long[] jArr5 = f4283b;
                long[] jArr6 = this.p;
                jArr4[i4] = jArr5[((int) (jArr6[(i4 + 0) & 7] >>> 56)) & 255] ^ j4;
                jArr4[i4] = jArr4[i4] ^ f4284c[((int) (jArr6[(i4 - 1) & 7] >>> 48)) & 255];
                jArr4[i4] = jArr4[i4] ^ f4285d[((int) (jArr6[(i4 - 2) & 7] >>> 40)) & 255];
                jArr4[i4] = jArr4[i4] ^ f4286e[((int) (jArr6[(i4 - 3) & 7] >>> 32)) & 255];
                jArr4[i4] = jArr4[i4] ^ f4287f[((int) (jArr6[(i4 - 4) & 7] >>> 24)) & 255];
                jArr4[i4] = jArr4[i4] ^ f4288g[((int) (jArr6[(i4 - 5) & 7] >>> 16)) & 255];
                jArr4[i4] = jArr4[i4] ^ f4289h[((int) (jArr6[(i4 - 6) & 7] >>> 8)) & 255];
                jArr4[i4] = jArr4[i4] ^ f4290i[((int) jArr6[(i4 - 7) & 7]) & 255];
                i4++;
                i3 = i3;
            }
            int i5 = i3;
            long[] jArr7 = this.q;
            long[] jArr8 = this.p;
            System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
            long[] jArr9 = this.p;
            jArr9[0] = jArr9[0] ^ this.k[i5];
            int i6 = 0;
            while (true) {
                jArr = this.q;
                if (i6 < 8) {
                    jArr[i6] = this.p[i6];
                    long j5 = jArr[i6];
                    long[] jArr10 = f4283b;
                    long[] jArr11 = this.s;
                    jArr[i6] = j5 ^ jArr10[((int) (jArr11[(i6 + 0) & 7] >>> 56)) & 255];
                    jArr[i6] = jArr[i6] ^ f4284c[((int) (jArr11[(i6 - 1) & 7] >>> 48)) & 255];
                    jArr[i6] = jArr[i6] ^ f4285d[((int) (jArr11[(i6 - 2) & 7] >>> 40)) & 255];
                    jArr[i6] = jArr[i6] ^ f4286e[((int) (jArr11[(i6 - 3) & 7] >>> 32)) & 255];
                    jArr[i6] = jArr[i6] ^ f4287f[((int) (jArr11[(i6 - 4) & 7] >>> 24)) & 255];
                    jArr[i6] = jArr[i6] ^ f4288g[((int) (jArr11[(i6 - 5) & 7] >>> 16)) & 255];
                    jArr[i6] = jArr[i6] ^ f4289h[((int) (jArr11[(i6 - 6) & 7] >>> 8)) & 255];
                    jArr[i6] = jArr[i6] ^ f4290i[((int) jArr11[(i6 - 7) & 7]) & 255];
                    i6++;
                }
            }
            long[] jArr12 = this.s;
            System.arraycopy(jArr, 0, jArr12, 0, jArr12.length);
            i3 = i5 + 1;
        }
        for (int i7 = 0; i7 < 8; i7++) {
            long[] jArr13 = this.o;
            jArr13[i7] = jArr13[i7] ^ (this.s[i7] ^ this.r[i7]);
        }
    }

    public final void l(byte[] bArr, int i2) {
        for (int i3 = 0; i3 < this.s.length; i3++) {
            this.r[i3] = d(this.l, i3 * 8);
        }
        k();
        this.m = 0;
        g.a.f.a.u(this.l, (byte) 0);
    }

    @Override // g.a.b.n
    public void reset() {
        this.m = 0;
        g.a.f.a.x(this.n, (short) 0);
        g.a.f.a.u(this.l, (byte) 0);
        g.a.f.a.w(this.o, 0L);
        g.a.f.a.w(this.p, 0L);
        g.a.f.a.w(this.q, 0L);
        g.a.f.a.w(this.r, 0L);
        g.a.f.a.w(this.s, 0L);
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        byte[] bArr = this.l;
        int i2 = this.m;
        bArr[i2] = b2;
        int i3 = i2 + 1;
        this.m = i3;
        if (i3 == bArr.length) {
            l(bArr, 0);
        }
        h();
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        while (i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
    }
}

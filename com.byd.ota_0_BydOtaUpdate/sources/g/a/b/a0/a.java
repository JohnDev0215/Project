package g.a.b.a0;

import android.R;

/* JADX INFO: loaded from: classes.dex */
public class a implements g.a.b.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f4203a = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[][] f4204b = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, new byte[]{11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, new byte[]{2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, new byte[]{13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f4205c = 12;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4206d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4207e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4208f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4209g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4210h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4211i;
    public int j;
    public long[] k;
    public long[] l;
    public long m;
    public long n;
    public long o;

    public a() {
        this(512);
    }

    public a(int i2) {
        this.f4206d = 64;
        this.f4207e = 0;
        this.f4208f = null;
        this.f4209g = null;
        this.f4210h = null;
        this.f4211i = null;
        this.j = 0;
        this.k = new long[16];
        this.l = null;
        this.m = 0L;
        this.n = 0L;
        this.o = 0L;
        if (i2 < 8 || i2 > 512 || i2 % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2b digest bit length must be a multiple of 8 and not greater than 512");
        }
        this.f4211i = new byte[128];
        this.f4207e = 0;
        this.f4206d = i2 / 8;
        d();
    }

    public a(a aVar) {
        this.f4206d = 64;
        this.f4207e = 0;
        this.f4208f = null;
        this.f4209g = null;
        this.f4210h = null;
        this.f4211i = null;
        this.j = 0;
        this.k = new long[16];
        this.l = null;
        this.m = 0L;
        this.n = 0L;
        this.o = 0L;
        this.j = aVar.j;
        this.f4211i = g.a.f.a.f(aVar.f4211i);
        this.f4207e = aVar.f4207e;
        this.f4210h = g.a.f.a.f(aVar.f4210h);
        this.f4206d = aVar.f4206d;
        this.l = g.a.f.a.j(aVar.l);
        this.f4209g = g.a.f.a.f(aVar.f4209g);
        this.f4208f = g.a.f.a.f(aVar.f4208f);
        this.m = aVar.m;
        this.n = aVar.n;
        this.o = aVar.o;
    }

    public static long f(long j, int i2) {
        return (j << (64 - i2)) | (j >>> i2);
    }

    public final void a(long j, long j2, int i2, int i3, int i4, int i5) {
        long[] jArr = this.k;
        jArr[i2] = jArr[i2] + jArr[i3] + j;
        jArr[i5] = f(jArr[i5] ^ jArr[i2], 32);
        long[] jArr2 = this.k;
        jArr2[i4] = jArr2[i4] + jArr2[i5];
        jArr2[i3] = f(jArr2[i3] ^ jArr2[i4], 24);
        long[] jArr3 = this.k;
        jArr3[i2] = jArr3[i2] + jArr3[i3] + j2;
        jArr3[i5] = f(jArr3[i5] ^ jArr3[i2], 16);
        long[] jArr4 = this.k;
        jArr4[i4] = jArr4[i4] + jArr4[i5];
        jArr4[i3] = f(jArr4[i3] ^ jArr4[i4], 63);
    }

    public final void b(byte[] bArr, int i2) {
        e();
        long[] jArr = new long[16];
        int i3 = 0;
        for (int i4 = 0; i4 < 16; i4++) {
            jArr[i4] = g.a.f.f.n(bArr, (i4 * 8) + i2);
        }
        for (int i5 = 0; i5 < f4205c; i5++) {
            byte[][] bArr2 = f4204b;
            a(jArr[bArr2[i5][0]], jArr[bArr2[i5][1]], 0, 4, 8, 12);
            a(jArr[bArr2[i5][2]], jArr[bArr2[i5][3]], 1, 5, 9, 13);
            a(jArr[bArr2[i5][4]], jArr[bArr2[i5][5]], 2, 6, 10, 14);
            a(jArr[bArr2[i5][6]], jArr[bArr2[i5][7]], 3, 7, 11, 15);
            a(jArr[bArr2[i5][8]], jArr[bArr2[i5][9]], 0, 5, 10, 15);
            a(jArr[bArr2[i5][10]], jArr[bArr2[i5][11]], 1, 6, 11, 12);
            a(jArr[bArr2[i5][12]], jArr[bArr2[i5][13]], 2, 7, 8, 13);
            a(jArr[bArr2[i5][14]], jArr[bArr2[i5][15]], 3, 4, 9, 14);
        }
        while (true) {
            long[] jArr2 = this.l;
            if (i3 >= jArr2.length) {
                return;
            }
            long j = jArr2[i3];
            long[] jArr3 = this.k;
            jArr2[i3] = (j ^ jArr3[i3]) ^ jArr3[i3 + 8];
            i3++;
        }
    }

    public final void d() {
        if (this.l == null) {
            long[] jArr = {jArr[0] ^ ((long) ((this.f4206d | (this.f4207e << 8)) | R.attr.theme)), jArr[1], jArr[2], jArr[3], jArr[4], jArr[5], 0, 0};
            this.l = jArr;
            long[] jArr2 = f4203a;
            byte[] bArr = this.f4208f;
            if (bArr != null) {
                jArr[4] = jArr[4] ^ g.a.f.f.n(bArr, 0);
                long[] jArr3 = this.l;
                jArr3[5] = jArr3[5] ^ g.a.f.f.n(this.f4208f, 8);
            }
            long[] jArr4 = this.l;
            jArr4[6] = jArr2[6];
            jArr4[7] = jArr2[7];
            byte[] bArr2 = this.f4209g;
            if (bArr2 != null) {
                jArr4[6] = g.a.f.f.n(bArr2, 0) ^ jArr4[6];
                long[] jArr5 = this.l;
                jArr5[7] = jArr5[7] ^ g.a.f.f.n(this.f4209g, 8);
            }
        }
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        long[] jArr;
        int i3;
        this.o = -1L;
        long j = this.m;
        int i4 = this.j;
        long j2 = j + ((long) i4);
        this.m = j2;
        if (i4 > 0 && j2 == 0) {
            this.n++;
        }
        b(this.f4211i, 0);
        g.a.f.a.u(this.f4211i, (byte) 0);
        g.a.f.a.w(this.k, 0L);
        int i5 = 0;
        while (true) {
            jArr = this.l;
            if (i5 >= jArr.length || (i3 = i5 * 8) >= this.f4206d) {
                break;
            }
            byte[] bArrW = g.a.f.f.w(jArr[i5]);
            int i6 = this.f4206d;
            if (i3 < i6 - 8) {
                System.arraycopy(bArrW, 0, bArr, i3 + i2, 8);
            } else {
                System.arraycopy(bArrW, 0, bArr, i2 + i3, i6 - i3);
            }
            i5++;
        }
        g.a.f.a.w(jArr, 0L);
        reset();
        return this.f4206d;
    }

    public final void e() {
        long[] jArr = this.l;
        System.arraycopy(jArr, 0, this.k, 0, jArr.length);
        long[] jArr2 = f4203a;
        System.arraycopy(jArr2, 0, this.k, this.l.length, 4);
        long[] jArr3 = this.k;
        jArr3[12] = this.m ^ jArr2[4];
        jArr3[13] = this.n ^ jArr2[5];
        jArr3[14] = this.o ^ jArr2[6];
        jArr3[15] = jArr2[7];
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "BLAKE2b";
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return 128;
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return this.f4206d;
    }

    @Override // g.a.b.n
    public void reset() {
        this.j = 0;
        this.o = 0L;
        this.m = 0L;
        this.n = 0L;
        this.l = null;
        g.a.f.a.u(this.f4211i, (byte) 0);
        byte[] bArr = this.f4210h;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.f4211i, 0, bArr.length);
            this.j = 128;
        }
        d();
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        int i2 = this.j;
        if (128 - i2 != 0) {
            this.f4211i[i2] = b2;
            this.j = i2 + 1;
            return;
        }
        long j = this.m + 128;
        this.m = j;
        if (j == 0) {
            this.n++;
        }
        b(this.f4211i, 0);
        g.a.f.a.u(this.f4211i, (byte) 0);
        this.f4211i[0] = b2;
        this.j = 1;
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null || i3 == 0) {
            return;
        }
        int i5 = this.j;
        if (i5 != 0) {
            i4 = 128 - i5;
            if (i4 >= i3) {
                System.arraycopy(bArr, i2, this.f4211i, i5, i3);
                this.j += i3;
            }
            System.arraycopy(bArr, i2, this.f4211i, i5, i4);
            long j = this.m + 128;
            this.m = j;
            if (j == 0) {
                this.n++;
            }
            b(this.f4211i, 0);
            this.j = 0;
            g.a.f.a.u(this.f4211i, (byte) 0);
        } else {
            i4 = 0;
        }
        int i6 = i3 + i2;
        int i7 = i6 - 128;
        int i8 = i2 + i4;
        while (i8 < i7) {
            long j2 = this.m + 128;
            this.m = j2;
            if (j2 == 0) {
                this.n++;
            }
            b(bArr, i8);
            i8 += 128;
        }
        i3 = i6 - i8;
        System.arraycopy(bArr, i8, this.f4211i, 0, i3);
        this.j += i3;
    }
}

package g.a.b.a0;

import android.R;

/* JADX INFO: loaded from: classes.dex */
public class b implements g.a.b.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4217a = {1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[][] f4218b = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, new byte[]{11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, new byte[]{2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, new byte[]{13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4222f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4223g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4224h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4225i;
    public int[] j;
    public int[] k;
    public int l;
    public int m;
    public int n;

    public b() {
        this(256);
    }

    public b(int i2) {
        this.f4219c = 32;
        this.f4220d = 0;
        this.f4221e = null;
        this.f4222f = null;
        this.f4223g = null;
        this.f4224h = null;
        this.f4225i = 0;
        this.j = new int[16];
        this.k = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        if (i2 < 8 || i2 > 256 || i2 % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2s digest bit length must be a multiple of 8 and not greater than 256");
        }
        this.f4224h = new byte[64];
        this.f4220d = 0;
        this.f4219c = i2 / 8;
        d();
    }

    public b(b bVar) {
        this.f4219c = 32;
        this.f4220d = 0;
        this.f4221e = null;
        this.f4222f = null;
        this.f4223g = null;
        this.f4224h = null;
        this.f4225i = 0;
        this.j = new int[16];
        this.k = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.f4225i = bVar.f4225i;
        this.f4224h = g.a.f.a.f(bVar.f4224h);
        this.f4220d = bVar.f4220d;
        this.f4223g = g.a.f.a.f(bVar.f4223g);
        this.f4219c = bVar.f4219c;
        this.k = g.a.f.a.i(bVar.k);
        this.f4222f = g.a.f.a.f(bVar.f4222f);
    }

    public final void a(int i2, int i3, int i4, int i5, int i6, int i7) {
        int[] iArr = this.j;
        iArr[i4] = iArr[i4] + iArr[i5] + i2;
        iArr[i7] = f(iArr[i7] ^ iArr[i4], 16);
        int[] iArr2 = this.j;
        iArr2[i6] = iArr2[i6] + iArr2[i7];
        iArr2[i5] = f(iArr2[i5] ^ iArr2[i6], 12);
        int[] iArr3 = this.j;
        iArr3[i4] = iArr3[i4] + iArr3[i5] + i3;
        iArr3[i7] = f(iArr3[i7] ^ iArr3[i4], 8);
        int[] iArr4 = this.j;
        iArr4[i6] = iArr4[i6] + iArr4[i7];
        iArr4[i5] = f(iArr4[i5] ^ iArr4[i6], 7);
    }

    public final void b(byte[] bArr, int i2) {
        e();
        int[] iArr = new int[16];
        int i3 = 0;
        for (int i4 = 0; i4 < 16; i4++) {
            iArr[i4] = g.a.f.f.j(bArr, (i4 * 4) + i2);
        }
        for (int i5 = 0; i5 < 10; i5++) {
            byte[][] bArr2 = f4218b;
            a(iArr[bArr2[i5][0]], iArr[bArr2[i5][1]], 0, 4, 8, 12);
            a(iArr[bArr2[i5][2]], iArr[bArr2[i5][3]], 1, 5, 9, 13);
            a(iArr[bArr2[i5][4]], iArr[bArr2[i5][5]], 2, 6, 10, 14);
            a(iArr[bArr2[i5][6]], iArr[bArr2[i5][7]], 3, 7, 11, 15);
            a(iArr[bArr2[i5][8]], iArr[bArr2[i5][9]], 0, 5, 10, 15);
            a(iArr[bArr2[i5][10]], iArr[bArr2[i5][11]], 1, 6, 11, 12);
            a(iArr[bArr2[i5][12]], iArr[bArr2[i5][13]], 2, 7, 8, 13);
            a(iArr[bArr2[i5][14]], iArr[bArr2[i5][15]], 3, 4, 9, 14);
        }
        while (true) {
            int[] iArr2 = this.k;
            if (i3 >= iArr2.length) {
                return;
            }
            int i6 = iArr2[i3];
            int[] iArr3 = this.j;
            iArr2[i3] = (i6 ^ iArr3[i3]) ^ iArr3[i3 + 8];
            i3++;
        }
    }

    public final void d() {
        if (this.k == null) {
            int[] iArr = {(((this.f4220d << 8) | i) | R.attr.theme) ^ i, iArr[1], iArr[2], iArr[3], iArr[4], iArr[5], 0, 0};
            this.k = iArr;
            int[] iArr2 = f4217a;
            int i2 = iArr2[0];
            int i3 = this.f4219c;
            byte[] bArr = this.f4221e;
            if (bArr != null) {
                iArr[4] = g.a.f.f.j(bArr, 0) ^ iArr[4];
                int[] iArr3 = this.k;
                iArr3[5] = iArr3[5] ^ g.a.f.f.j(this.f4221e, 4);
            }
            int[] iArr4 = this.k;
            iArr4[6] = iArr2[6];
            iArr4[7] = iArr2[7];
            byte[] bArr2 = this.f4222f;
            if (bArr2 != null) {
                iArr4[6] = g.a.f.f.j(bArr2, 0) ^ iArr4[6];
                int[] iArr5 = this.k;
                iArr5[7] = g.a.f.f.j(this.f4222f, 4) ^ iArr5[7];
            }
        }
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        int[] iArr;
        int i3;
        this.n = -1;
        int i4 = this.l;
        int i5 = this.f4225i;
        int i6 = i4 + i5;
        this.l = i6;
        if (i6 < 0 && i5 > (-i6)) {
            this.m++;
        }
        b(this.f4224h, 0);
        g.a.f.a.u(this.f4224h, (byte) 0);
        g.a.f.a.v(this.j, 0);
        int i7 = 0;
        while (true) {
            iArr = this.k;
            if (i7 >= iArr.length || (i3 = i7 * 4) >= this.f4219c) {
                break;
            }
            byte[] bArrI = g.a.f.f.i(iArr[i7]);
            int i8 = this.f4219c;
            if (i3 < i8 - 4) {
                System.arraycopy(bArrI, 0, bArr, i3 + i2, 4);
            } else {
                System.arraycopy(bArrI, 0, bArr, i2 + i3, i8 - i3);
            }
            i7++;
        }
        g.a.f.a.v(iArr, 0);
        reset();
        return this.f4219c;
    }

    public final void e() {
        int[] iArr = this.k;
        System.arraycopy(iArr, 0, this.j, 0, iArr.length);
        int[] iArr2 = f4217a;
        System.arraycopy(iArr2, 0, this.j, this.k.length, 4);
        int[] iArr3 = this.j;
        iArr3[12] = this.l ^ iArr2[4];
        iArr3[13] = this.m ^ iArr2[5];
        iArr3[14] = this.n ^ iArr2[6];
        iArr3[15] = iArr2[7];
    }

    public final int f(int i2, int i3) {
        return (i2 << (32 - i3)) | (i2 >>> i3);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "BLAKE2s";
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return 64;
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return this.f4219c;
    }

    @Override // g.a.b.n
    public void reset() {
        this.f4225i = 0;
        this.n = 0;
        this.l = 0;
        this.m = 0;
        this.k = null;
        g.a.f.a.u(this.f4224h, (byte) 0);
        byte[] bArr = this.f4223g;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.f4224h, 0, bArr.length);
            this.f4225i = 64;
        }
        d();
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        int i2 = this.f4225i;
        if (64 - i2 != 0) {
            this.f4224h[i2] = b2;
            this.f4225i = i2 + 1;
            return;
        }
        int i3 = this.l + 64;
        this.l = i3;
        if (i3 == 0) {
            this.m++;
        }
        b(this.f4224h, 0);
        g.a.f.a.u(this.f4224h, (byte) 0);
        this.f4224h[0] = b2;
        this.f4225i = 1;
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null || i3 == 0) {
            return;
        }
        int i5 = this.f4225i;
        if (i5 != 0) {
            i4 = 64 - i5;
            if (i4 >= i3) {
                System.arraycopy(bArr, i2, this.f4224h, i5, i3);
                this.f4225i += i3;
            }
            System.arraycopy(bArr, i2, this.f4224h, i5, i4);
            int i6 = this.l + 64;
            this.l = i6;
            if (i6 == 0) {
                this.m++;
            }
            b(this.f4224h, 0);
            this.f4225i = 0;
            g.a.f.a.u(this.f4224h, (byte) 0);
        } else {
            i4 = 0;
        }
        int i7 = i3 + i2;
        int i8 = i7 - 64;
        int i9 = i2 + i4;
        while (i9 < i8) {
            int i10 = this.l + 64;
            this.l = i10;
            if (i10 == 0) {
                this.m++;
            }
            b(bArr, i9);
            i9 += 64;
        }
        i3 = i7 - i9;
        System.arraycopy(bArr, i9, this.f4224h, 0, i3);
        this.f4225i += i3;
    }
}

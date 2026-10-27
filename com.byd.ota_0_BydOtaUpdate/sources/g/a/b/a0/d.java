package g.a.b.a0;

import g.a.b.j0.s0;
import g.a.b.j0.y0;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class d implements g.a.b.p, g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4255a = {0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4257c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4259e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[][] f4260f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4261g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4262h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f4263i;
    public g.a.b.e j;
    public byte[] k;
    public byte[] l;
    public byte[] m;
    public short[] n;
    public short[] o;
    public byte[] p;
    public byte[] q;
    public byte[] r;
    public byte[] s;

    public d() {
        this.f4256b = new byte[32];
        this.f4257c = new byte[32];
        this.f4258d = new byte[32];
        this.f4259e = new byte[32];
        this.f4260f = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 32);
        this.f4261g = new byte[32];
        this.j = new g.a.b.d0.u();
        this.l = new byte[32];
        this.m = new byte[8];
        this.n = new short[16];
        this.o = new short[16];
        this.p = new byte[32];
        this.q = new byte[32];
        this.r = new byte[32];
        this.s = new byte[32];
        byte[] bArrH = g.a.b.d0.u.h("D-A");
        this.k = bArrH;
        this.j.init(true, new y0(null, bArrH));
        reset();
    }

    public d(d dVar) {
        this.f4256b = new byte[32];
        this.f4257c = new byte[32];
        this.f4258d = new byte[32];
        this.f4259e = new byte[32];
        this.f4260f = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 32);
        this.f4261g = new byte[32];
        this.j = new g.a.b.d0.u();
        this.l = new byte[32];
        this.m = new byte[8];
        this.n = new short[16];
        this.o = new short[16];
        this.p = new byte[32];
        this.q = new byte[32];
        this.r = new byte[32];
        this.s = new byte[32];
        a(dVar);
    }

    public d(byte[] bArr) {
        this.f4256b = new byte[32];
        this.f4257c = new byte[32];
        this.f4258d = new byte[32];
        this.f4259e = new byte[32];
        this.f4260f = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 32);
        this.f4261g = new byte[32];
        this.j = new g.a.b.d0.u();
        this.l = new byte[32];
        this.m = new byte[8];
        this.n = new short[16];
        this.o = new short[16];
        this.p = new byte[32];
        this.q = new byte[32];
        this.r = new byte[32];
        this.s = new byte[32];
        byte[] bArrF = g.a.f.a.f(bArr);
        this.k = bArrF;
        this.j.init(true, new y0(null, bArrF));
        reset();
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        d dVar = (d) eVar;
        byte[] bArr = dVar.k;
        this.k = bArr;
        this.j.init(true, new y0(null, bArr));
        reset();
        byte[] bArr2 = dVar.f4256b;
        System.arraycopy(bArr2, 0, this.f4256b, 0, bArr2.length);
        byte[] bArr3 = dVar.f4257c;
        System.arraycopy(bArr3, 0, this.f4257c, 0, bArr3.length);
        byte[] bArr4 = dVar.f4258d;
        System.arraycopy(bArr4, 0, this.f4258d, 0, bArr4.length);
        byte[] bArr5 = dVar.f4259e;
        System.arraycopy(bArr5, 0, this.f4259e, 0, bArr5.length);
        byte[][] bArr6 = dVar.f4260f;
        System.arraycopy(bArr6[1], 0, this.f4260f[1], 0, bArr6[1].length);
        byte[][] bArr7 = dVar.f4260f;
        System.arraycopy(bArr7[2], 0, this.f4260f[2], 0, bArr7[2].length);
        byte[][] bArr8 = dVar.f4260f;
        System.arraycopy(bArr8[3], 0, this.f4260f[3], 0, bArr8[3].length);
        byte[] bArr9 = dVar.f4261g;
        System.arraycopy(bArr9, 0, this.f4261g, 0, bArr9.length);
        this.f4262h = dVar.f4262h;
        this.f4263i = dVar.f4263i;
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new d(this);
    }

    public final byte[] d(byte[] bArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            this.m[i2] = (byte) (bArr[i2] ^ bArr[i2 + 8]);
        }
        System.arraycopy(bArr, 8, bArr, 0, 24);
        System.arraycopy(this.m, 0, bArr, 24, 8);
        return bArr;
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        i();
        byte[] bArr2 = this.f4256b;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        reset();
        return 32;
    }

    public final void e(byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        this.j.init(true, new s0(bArr));
        this.j.a(bArr3, i3, bArr2, i2);
    }

    public final byte[] f(byte[] bArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            byte[] bArr2 = this.l;
            int i3 = i2 * 4;
            bArr2[i3] = bArr[i2];
            bArr2[i3 + 1] = bArr[i2 + 8];
            bArr2[i3 + 2] = bArr[i2 + 16];
            bArr2[i3 + 3] = bArr[i2 + 24];
        }
        return this.l;
    }

    public final void g(byte[] bArr, short[] sArr) {
        for (int i2 = 0; i2 < bArr.length / 2; i2++) {
            int i3 = i2 * 2;
            sArr[i2] = (short) ((bArr[i3] & 255) | ((bArr[i3 + 1] << 8) & 65280));
        }
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "GOST3411";
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return 32;
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 32;
    }

    public final void h(short[] sArr, byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length / 2; i2++) {
            int i3 = i2 * 2;
            bArr[i3 + 1] = (byte) (sArr[i2] >> 8);
            bArr[i3] = (byte) sArr[i2];
        }
    }

    public final void i() {
        g.a.f.f.t(this.f4263i * 8, this.f4257c, 0);
        while (this.f4262h != 0) {
            update((byte) 0);
        }
        k(this.f4257c, 0);
        k(this.f4259e, 0);
    }

    public final void j(byte[] bArr) {
        g(bArr, this.n);
        short[] sArr = this.o;
        short[] sArr2 = this.n;
        sArr[15] = (short) (((((sArr2[0] ^ sArr2[1]) ^ sArr2[2]) ^ sArr2[3]) ^ sArr2[12]) ^ sArr2[15]);
        System.arraycopy(sArr2, 1, sArr, 0, 15);
        h(this.o, bArr);
    }

    public void k(byte[] bArr, int i2) {
        System.arraycopy(bArr, i2, this.f4258d, 0, 32);
        System.arraycopy(this.f4256b, 0, this.q, 0, 32);
        System.arraycopy(this.f4258d, 0, this.r, 0, 32);
        for (int i3 = 0; i3 < 32; i3++) {
            this.s[i3] = (byte) (this.q[i3] ^ this.r[i3]);
        }
        e(f(this.s), this.p, 0, this.f4256b, 0);
        for (int i4 = 1; i4 < 4; i4++) {
            byte[] bArrD = d(this.q);
            for (int i5 = 0; i5 < 32; i5++) {
                this.q[i5] = (byte) (bArrD[i5] ^ this.f4260f[i4][i5]);
            }
            this.r = d(d(this.r));
            for (int i6 = 0; i6 < 32; i6++) {
                this.s[i6] = (byte) (this.q[i6] ^ this.r[i6]);
            }
            int i7 = i4 * 8;
            e(f(this.s), this.p, i7, this.f4256b, i7);
        }
        for (int i8 = 0; i8 < 12; i8++) {
            j(this.p);
        }
        for (int i9 = 0; i9 < 32; i9++) {
            byte[] bArr2 = this.p;
            bArr2[i9] = (byte) (bArr2[i9] ^ this.f4258d[i9]);
        }
        j(this.p);
        for (int i10 = 0; i10 < 32; i10++) {
            byte[] bArr3 = this.p;
            bArr3[i10] = (byte) (this.f4256b[i10] ^ bArr3[i10]);
        }
        for (int i11 = 0; i11 < 61; i11++) {
            j(this.p);
        }
        byte[] bArr4 = this.p;
        byte[] bArr5 = this.f4256b;
        System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
    }

    public final void l(byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr2 = this.f4259e;
            if (i2 == bArr2.length) {
                return;
            }
            int i4 = (bArr2[i2] & 255) + (bArr[i2] & 255) + i3;
            bArr2[i2] = (byte) i4;
            i3 = i4 >>> 8;
            i2++;
        }
    }

    @Override // g.a.b.n
    public void reset() {
        this.f4263i = 0L;
        this.f4262h = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4256b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr2 = this.f4257c;
            if (i3 >= bArr2.length) {
                break;
            }
            bArr2[i3] = 0;
            i3++;
        }
        int i4 = 0;
        while (true) {
            byte[] bArr3 = this.f4258d;
            if (i4 >= bArr3.length) {
                break;
            }
            bArr3[i4] = 0;
            i4++;
        }
        int i5 = 0;
        while (true) {
            byte[][] bArr4 = this.f4260f;
            if (i5 >= bArr4[1].length) {
                break;
            }
            bArr4[1][i5] = 0;
            i5++;
        }
        int i6 = 0;
        while (true) {
            byte[][] bArr5 = this.f4260f;
            if (i6 >= bArr5[3].length) {
                break;
            }
            bArr5[3][i6] = 0;
            i6++;
        }
        int i7 = 0;
        while (true) {
            byte[] bArr6 = this.f4259e;
            if (i7 >= bArr6.length) {
                break;
            }
            bArr6[i7] = 0;
            i7++;
        }
        int i8 = 0;
        while (true) {
            byte[] bArr7 = this.f4261g;
            if (i8 >= bArr7.length) {
                byte[] bArr8 = f4255a;
                System.arraycopy(bArr8, 0, this.f4260f[2], 0, bArr8.length);
                return;
            } else {
                bArr7[i8] = 0;
                i8++;
            }
        }
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        byte[] bArr = this.f4261g;
        int i2 = this.f4262h;
        int i3 = i2 + 1;
        this.f4262h = i3;
        bArr[i2] = b2;
        if (i3 == bArr.length) {
            l(bArr);
            k(this.f4261g, 0);
            this.f4262h = 0;
        }
        this.f4263i++;
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        while (this.f4262h != 0 && i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
        while (true) {
            byte[] bArr2 = this.f4261g;
            if (i3 <= bArr2.length) {
                break;
            }
            System.arraycopy(bArr, i2, bArr2, 0, bArr2.length);
            l(this.f4261g);
            k(this.f4261g, 0);
            byte[] bArr3 = this.f4261g;
            i2 += bArr3.length;
            i3 -= bArr3.length;
            this.f4263i += (long) bArr3.length;
        }
        while (i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
    }
}

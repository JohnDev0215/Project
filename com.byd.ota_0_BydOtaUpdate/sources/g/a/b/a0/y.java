package g.a.b.a0;

import org.bouncycastle.util.MemoableResetException;

/* JADX INFO: loaded from: classes.dex */
public class y extends j {
    public int p;
    public long q;
    public long r;
    public long s;
    public long t;
    public long u;
    public long v;
    public long w;
    public long x;

    public y(int i2) {
        if (i2 >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        }
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        }
        if (i2 == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        }
        int i3 = i2 / 8;
        this.p = i3;
        r(i3 * 8);
        reset();
    }

    public y(y yVar) {
        super(yVar);
        this.p = yVar.p;
        a(yVar);
    }

    public static void p(int i2, byte[] bArr, int i3, int i4) {
        int iMin = Math.min(4, i4);
        while (true) {
            iMin--;
            if (iMin < 0) {
                return;
            } else {
                bArr[i3 + iMin] = (byte) (i2 >>> ((3 - iMin) * 8));
            }
        }
    }

    public static void q(long j, byte[] bArr, int i2, int i3) {
        if (i3 > 0) {
            p((int) (j >>> 32), bArr, i2, i3);
            if (i3 > 4) {
                p((int) (j & 4294967295L), bArr, i2 + 4, i3 - 4);
            }
        }
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        y yVar = (y) eVar;
        if (this.p != yVar.p) {
            throw new MemoableResetException("digestLength inappropriate in other");
        }
        super.k(yVar);
        this.q = yVar.q;
        this.r = yVar.r;
        this.s = yVar.s;
        this.t = yVar.t;
        this.u = yVar.u;
        this.v = yVar.v;
        this.w = yVar.w;
        this.x = yVar.x;
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new y(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        l();
        q(this.f4306f, bArr, i2, this.p);
        q(this.f4307g, bArr, i2 + 8, this.p - 8);
        q(this.f4308h, bArr, i2 + 16, this.p - 16);
        q(this.f4309i, bArr, i2 + 24, this.p - 24);
        q(this.j, bArr, i2 + 32, this.p - 32);
        q(this.k, bArr, i2 + 40, this.p - 40);
        q(this.l, bArr, i2 + 48, this.p - 48);
        q(this.m, bArr, i2 + 56, this.p - 56);
        reset();
        return this.p;
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "SHA-512/" + Integer.toString(this.p * 8);
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return this.p;
    }

    public final void r(int i2) {
        this.f4306f = -3482333909917012819L;
        this.f4307g = 2216346199247487646L;
        this.f4308h = -7364697282686394994L;
        this.f4309i = 65953792586715988L;
        this.j = -816286391624063116L;
        this.k = 4512832404995164602L;
        this.l = -5033199132376557362L;
        this.m = -124578254951840548L;
        update((byte) 83);
        update((byte) 72);
        update((byte) 65);
        update((byte) 45);
        update((byte) 53);
        update((byte) 49);
        update((byte) 50);
        update((byte) 47);
        if (i2 <= 100) {
            if (i2 > 10) {
            }
            update((byte) (i2 + 48));
            l();
            this.q = this.f4306f;
            this.r = this.f4307g;
            this.s = this.f4308h;
            this.t = this.f4309i;
            this.u = this.j;
            this.v = this.k;
            this.w = this.l;
            this.x = this.m;
        }
        update((byte) ((i2 / 100) + 48));
        i2 %= 100;
        update((byte) ((i2 / 10) + 48));
        i2 %= 10;
        update((byte) (i2 + 48));
        l();
        this.q = this.f4306f;
        this.r = this.f4307g;
        this.s = this.f4308h;
        this.t = this.f4309i;
        this.u = this.j;
        this.v = this.k;
        this.w = this.l;
        this.x = this.m;
    }

    @Override // g.a.b.a0.j, g.a.b.n
    public void reset() {
        super.reset();
        this.f4306f = this.q;
        this.f4307g = this.r;
        this.f4308h = this.s;
        this.f4309i = this.t;
        this.j = this.u;
        this.k = this.v;
        this.l = this.w;
        this.m = this.x;
    }
}

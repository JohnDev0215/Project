package g.a.e.a;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.x;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class l extends g.a.a.l {
    public final byte[] E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5590c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f5591d;
    public final byte[] s;
    public final byte[] x;
    public final byte[] y;

    public l(int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this.f5590c = i2;
        this.f5591d = g.a.f.a.f(bArr);
        this.s = g.a.f.a.f(bArr2);
        this.x = g.a.f.a.f(bArr3);
        this.y = g.a.f.a.f(bArr4);
        this.E3 = g.a.f.a.f(bArr5);
    }

    public l(r rVar) {
        if (!g.a.a.j.n(rVar.q(0)).q().equals(BigInteger.valueOf(0L))) {
            throw new IllegalArgumentException("unknown version of sequence");
        }
        if (rVar.size() != 2 && rVar.size() != 3) {
            throw new IllegalArgumentException("key sequence wrong size");
        }
        r rVarN = r.n(rVar.q(1));
        this.f5590c = g.a.a.j.n(rVarN.q(0)).q().intValue();
        this.f5591d = g.a.f.a.f(n.n(rVarN.q(1)).p());
        this.s = g.a.f.a.f(n.n(rVarN.q(2)).p());
        this.x = g.a.f.a.f(n.n(rVarN.q(3)).p());
        this.y = g.a.f.a.f(n.n(rVarN.q(4)).p());
        if (rVar.size() == 3) {
            this.E3 = g.a.f.a.f(n.o(x.n(rVar.q(2)), true).p());
        } else {
            this.E3 = null;
        }
    }

    public static l i(Object obj) {
        if (obj instanceof l) {
            return (l) obj;
        }
        if (obj != null) {
            return new l(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(0L));
        g.a.a.f fVar2 = new g.a.a.f();
        fVar2.a(new g.a.a.j(this.f5590c));
        fVar2.a(new y0(this.f5591d));
        fVar2.a(new y0(this.s));
        fVar2.a(new y0(this.x));
        fVar2.a(new y0(this.y));
        fVar.a(new c1(fVar2));
        fVar.a(new h1(true, 0, new y0(this.E3)));
        return new c1(fVar);
    }

    public byte[] g() {
        return g.a.f.a.f(this.E3);
    }

    public int h() {
        return this.f5590c;
    }

    public byte[] j() {
        return g.a.f.a.f(this.x);
    }

    public byte[] k() {
        return g.a.f.a.f(this.y);
    }

    public byte[] l() {
        return g.a.f.a.f(this.s);
    }

    public byte[] m() {
        return g.a.f.a.f(this.f5591d);
    }
}

package g.a.a.t2;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.j;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.x;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b extends l {
    public n E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f4056c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f4057d;
    public j s;
    public n x;
    public j y;

    public b(r rVar) {
        this.f4056c = BigInteger.valueOf(0L);
        int i2 = 0;
        if (rVar.q(0) instanceof x) {
            x xVar = (x) rVar.q(0);
            if (!xVar.r() || xVar.q() != 0) {
                throw new IllegalArgumentException("object parse error");
            }
            this.f4056c = j.n(xVar.a()).q();
            i2 = 1;
        }
        this.f4057d = a.g(rVar.q(i2));
        int i3 = i2 + 1;
        this.s = j.n(rVar.q(i3));
        int i4 = i3 + 1;
        this.x = n.n(rVar.q(i4));
        int i5 = i4 + 1;
        this.y = j.n(rVar.q(i5));
        this.E3 = n.n(rVar.q(i5 + 1));
    }

    public static b k(Object obj) {
        if (obj instanceof b) {
            return (b) obj;
        }
        if (obj != null) {
            return new b(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        if (this.f4056c.compareTo(BigInteger.valueOf(0L)) != 0) {
            fVar.a(new h1(true, 0, new j(this.f4056c)));
        }
        fVar.a(this.f4057d);
        fVar.a(this.s);
        fVar.a(this.x);
        fVar.a(this.y);
        fVar.a(this.E3);
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.s.q();
    }

    public byte[] h() {
        return g.a.f.a.f(this.x.p());
    }

    public a i() {
        return this.f4057d;
    }

    public byte[] j() {
        return g.a.f.a.f(this.E3.p());
    }

    public BigInteger l() {
        return this.y.q();
    }
}

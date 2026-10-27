package g.a.a.p2;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.w0;
import g.a.a.x;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class u extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g.a.a.w2.a f3995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g.a.a.w2.a f3996d;
    public static final g.a.a.j s;
    public static final g.a.a.j x;
    public g.a.a.w2.a E3;
    public g.a.a.j F3;
    public g.a.a.j G3;
    public g.a.a.w2.a y;

    static {
        g.a.a.w2.a aVar = new g.a.a.w2.a(g.a.a.o2.b.f3948i, w0.f4105c);
        f3995c = aVar;
        f3996d = new g.a.a.w2.a(n.l, aVar);
        s = new g.a.a.j(20L);
        x = new g.a.a.j(1L);
    }

    public u() {
        this.y = f3995c;
        this.E3 = f3996d;
        this.F3 = s;
        this.G3 = x;
    }

    public u(g.a.a.r rVar) {
        this.y = f3995c;
        this.E3 = f3996d;
        this.F3 = s;
        this.G3 = x;
        for (int i2 = 0; i2 != rVar.size(); i2++) {
            x xVar = (x) rVar.q(i2);
            int iQ = xVar.q();
            if (iQ == 0) {
                this.y = g.a.a.w2.a.i(xVar, true);
            } else if (iQ == 1) {
                this.E3 = g.a.a.w2.a.i(xVar, true);
            } else if (iQ == 2) {
                this.F3 = g.a.a.j.o(xVar, true);
            } else {
                if (iQ != 3) {
                    throw new IllegalArgumentException("unknown tag");
                }
                this.G3 = g.a.a.j.o(xVar, true);
            }
        }
    }

    public u(g.a.a.w2.a aVar, g.a.a.w2.a aVar2, g.a.a.j jVar, g.a.a.j jVar2) {
        this.y = aVar;
        this.E3 = aVar2;
        this.F3 = jVar;
        this.G3 = jVar2;
    }

    public static u h(Object obj) {
        if (obj instanceof u) {
            return (u) obj;
        }
        if (obj != null) {
            return new u(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        if (!this.y.equals(f3995c)) {
            fVar.a(new h1(true, 0, this.y));
        }
        if (!this.E3.equals(f3996d)) {
            fVar.a(new h1(true, 1, this.E3));
        }
        if (!this.F3.equals(s)) {
            fVar.a(new h1(true, 2, this.F3));
        }
        if (!this.G3.equals(x)) {
            fVar.a(new h1(true, 3, this.G3));
        }
        return new c1(fVar);
    }

    public g.a.a.w2.a g() {
        return this.y;
    }

    public g.a.a.w2.a i() {
        return this.E3;
    }

    public BigInteger j() {
        return this.F3.q();
    }

    public BigInteger k() {
        return this.G3.q();
    }
}

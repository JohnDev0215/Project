package g.a.a.r2;

import g.a.a.c1;
import g.a.a.e;
import g.a.a.f;
import g.a.a.h1;
import g.a.a.j;
import g.a.a.l;
import g.a.a.n;
import g.a.a.o0;
import g.a.a.q;
import g.a.a.r;
import g.a.a.x;
import g.a.a.y0;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class a extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public r f4014c;

    public a(int i2, BigInteger bigInteger, e eVar) {
        this(i2, bigInteger, null, eVar);
    }

    public a(int i2, BigInteger bigInteger, o0 o0Var, e eVar) {
        byte[] bArrA = g.a.f.b.a((i2 + 7) / 8, bigInteger);
        f fVar = new f();
        fVar.a(new j(1L));
        fVar.a(new y0(bArrA));
        if (eVar != null) {
            fVar.a(new h1(true, 0, eVar));
        }
        if (o0Var != null) {
            fVar.a(new h1(true, 1, o0Var));
        }
        this.f4014c = new c1(fVar);
    }

    public a(r rVar) {
        this.f4014c = rVar;
    }

    public static a g(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return this.f4014c;
    }

    public BigInteger h() {
        return new BigInteger(1, ((n) this.f4014c.q(1)).p());
    }

    public final q i(int i2) {
        Enumeration enumerationR = this.f4014c.r();
        while (enumerationR.hasMoreElements()) {
            e eVar = (e) enumerationR.nextElement();
            if (eVar instanceof x) {
                x xVar = (x) eVar;
                if (xVar.q() == i2) {
                    return xVar.p().c();
                }
            }
        }
        return null;
    }

    public o0 j() {
        return (o0) i(1);
    }
}

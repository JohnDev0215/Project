package g.a.a.p2;

import g.a.a.c1;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class t extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f3993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f3994d;

    public t(g.a.a.r rVar) {
        if (rVar.size() == 2) {
            Enumeration enumerationR = rVar.r();
            this.f3993c = g.a.a.j.n(enumerationR.nextElement()).p();
            this.f3994d = g.a.a.j.n(enumerationR.nextElement()).p();
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
    }

    public t(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f3993c = bigInteger;
        this.f3994d = bigInteger2;
    }

    public static t g(Object obj) {
        if (obj instanceof t) {
            return (t) obj;
        }
        if (obj != null) {
            return new t(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(h()));
        fVar.a(new g.a.a.j(i()));
        return new c1(fVar);
    }

    public BigInteger h() {
        return this.f3993c;
    }

    public BigInteger i() {
        return this.f3994d;
    }
}

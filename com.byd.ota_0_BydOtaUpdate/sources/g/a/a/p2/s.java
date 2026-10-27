package g.a.a.p2;

import g.a.a.c1;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class s extends g.a.a.l {
    public BigInteger E3;
    public BigInteger F3;
    public BigInteger G3;
    public BigInteger H3;
    public g.a.a.r I3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f3991c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f3992d;
    public BigInteger s;
    public BigInteger x;
    public BigInteger y;

    public s(g.a.a.r rVar) {
        this.I3 = null;
        Enumeration enumerationR = rVar.r();
        BigInteger bigIntegerQ = ((g.a.a.j) enumerationR.nextElement()).q();
        if (bigIntegerQ.intValue() != 0 && bigIntegerQ.intValue() != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        this.f3991c = bigIntegerQ;
        this.f3992d = ((g.a.a.j) enumerationR.nextElement()).q();
        this.s = ((g.a.a.j) enumerationR.nextElement()).q();
        this.x = ((g.a.a.j) enumerationR.nextElement()).q();
        this.y = ((g.a.a.j) enumerationR.nextElement()).q();
        this.E3 = ((g.a.a.j) enumerationR.nextElement()).q();
        this.F3 = ((g.a.a.j) enumerationR.nextElement()).q();
        this.G3 = ((g.a.a.j) enumerationR.nextElement()).q();
        this.H3 = ((g.a.a.j) enumerationR.nextElement()).q();
        if (enumerationR.hasMoreElements()) {
            this.I3 = (g.a.a.r) enumerationR.nextElement();
        }
    }

    public s(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this.I3 = null;
        this.f3991c = BigInteger.valueOf(0L);
        this.f3992d = bigInteger;
        this.s = bigInteger2;
        this.x = bigInteger3;
        this.y = bigInteger4;
        this.E3 = bigInteger5;
        this.F3 = bigInteger6;
        this.G3 = bigInteger7;
        this.H3 = bigInteger8;
    }

    public static s j(Object obj) {
        if (obj instanceof s) {
            return (s) obj;
        }
        if (obj != null) {
            return new s(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(this.f3991c));
        fVar.a(new g.a.a.j(k()));
        fVar.a(new g.a.a.j(o()));
        fVar.a(new g.a.a.j(n()));
        fVar.a(new g.a.a.j(l()));
        fVar.a(new g.a.a.j(m()));
        fVar.a(new g.a.a.j(h()));
        fVar.a(new g.a.a.j(i()));
        fVar.a(new g.a.a.j(g()));
        g.a.a.r rVar = this.I3;
        if (rVar != null) {
            fVar.a(rVar);
        }
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.H3;
    }

    public BigInteger h() {
        return this.F3;
    }

    public BigInteger i() {
        return this.G3;
    }

    public BigInteger k() {
        return this.f3992d;
    }

    public BigInteger l() {
        return this.y;
    }

    public BigInteger m() {
        return this.E3;
    }

    public BigInteger n() {
        return this.x;
    }

    public BigInteger o() {
        return this.s;
    }
}

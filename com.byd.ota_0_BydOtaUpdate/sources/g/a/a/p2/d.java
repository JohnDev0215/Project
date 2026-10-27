package g.a.a.p2;

import g.a.a.c1;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class d extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f3960c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.j f3961d;
    public g.a.a.j s;

    public d(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f3960c = g.a.a.j.n(enumerationR.nextElement());
        this.f3961d = g.a.a.j.n(enumerationR.nextElement());
        this.s = enumerationR.hasMoreElements() ? (g.a.a.j) enumerationR.nextElement() : null;
    }

    public d(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        this.f3960c = new g.a.a.j(bigInteger);
        this.f3961d = new g.a.a.j(bigInteger2);
        this.s = i2 != 0 ? new g.a.a.j(i2) : null;
    }

    public static d h(Object obj) {
        if (obj instanceof d) {
            return (d) obj;
        }
        if (obj != null) {
            return new d(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3960c);
        fVar.a(this.f3961d);
        if (i() != null) {
            fVar.a(this.s);
        }
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.f3961d.p();
    }

    public BigInteger i() {
        g.a.a.j jVar = this.s;
        if (jVar == null) {
            return null;
        }
        return jVar.p();
    }

    public BigInteger j() {
        return this.f3960c.p();
    }
}

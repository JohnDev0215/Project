package g.a.a.w2;

import g.a.a.c1;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class i extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f4123c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.j f4124d;
    public g.a.a.j s;

    public i(g.a.a.r rVar) {
        if (rVar.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
        Enumeration enumerationR = rVar.r();
        this.f4123c = g.a.a.j.n(enumerationR.nextElement());
        this.f4124d = g.a.a.j.n(enumerationR.nextElement());
        this.s = g.a.a.j.n(enumerationR.nextElement());
    }

    public i(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f4123c = new g.a.a.j(bigInteger);
        this.f4124d = new g.a.a.j(bigInteger2);
        this.s = new g.a.a.j(bigInteger3);
    }

    public static i h(Object obj) {
        if (obj instanceof i) {
            return (i) obj;
        }
        if (obj != null) {
            return new i(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4123c);
        fVar.a(this.f4124d);
        fVar.a(this.s);
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.s.p();
    }

    public BigInteger i() {
        return this.f4123c.p();
    }

    public BigInteger j() {
        return this.f4124d.p();
    }
}

package g.a.a.x2;

import g.a.a.c1;
import g.a.a.q;
import g.a.a.r;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class a extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.j f4170c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.a.a.j f4171d;
    public final g.a.a.j s;
    public final g.a.a.j x;
    public final c y;

    public a(r rVar) {
        if (rVar.size() < 3 || rVar.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
        Enumeration enumerationR = rVar.r();
        this.f4170c = g.a.a.j.n(enumerationR.nextElement());
        this.f4171d = g.a.a.j.n(enumerationR.nextElement());
        this.s = g.a.a.j.n(enumerationR.nextElement());
        g.a.a.e eVarJ = j(enumerationR);
        if (eVarJ == null || !(eVarJ instanceof g.a.a.j)) {
            this.x = null;
        } else {
            this.x = g.a.a.j.n(eVarJ);
            eVarJ = j(enumerationR);
        }
        if (eVarJ != null) {
            this.y = c.g(eVarJ.c());
        } else {
            this.y = null;
        }
    }

    public a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, c cVar) {
        if (bigInteger == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (bigInteger2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (bigInteger3 == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        }
        this.f4170c = new g.a.a.j(bigInteger);
        this.f4171d = new g.a.a.j(bigInteger2);
        this.s = new g.a.a.j(bigInteger3);
        this.x = bigInteger4 != null ? new g.a.a.j(bigInteger4) : null;
        this.y = cVar;
    }

    public static a h(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        return null;
    }

    public static g.a.a.e j(Enumeration enumeration) {
        if (enumeration.hasMoreElements()) {
            return (g.a.a.e) enumeration.nextElement();
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4170c);
        fVar.a(this.f4171d);
        fVar.a(this.s);
        g.a.a.j jVar = this.x;
        if (jVar != null) {
            fVar.a(jVar);
        }
        c cVar = this.y;
        if (cVar != null) {
            fVar.a(cVar);
        }
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.f4171d.p();
    }

    public BigInteger i() {
        g.a.a.j jVar = this.x;
        if (jVar == null) {
            return null;
        }
        return jVar.p();
    }

    public BigInteger k() {
        return this.f4170c.p();
    }

    public BigInteger l() {
        return this.s.p();
    }

    public c m() {
        return this.y;
    }
}

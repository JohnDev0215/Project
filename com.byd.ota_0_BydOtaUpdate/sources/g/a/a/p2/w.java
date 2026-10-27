package g.a.a.p2;

import g.a.a.f0;
import g.a.a.h1;
import g.a.a.x;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class w extends g.a.a.l implements n {
    public g.a.a.t E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f3999c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.t f4000d;
    public c s;
    public g.a.a.t x;
    public g.a.a.t y;

    public w(g.a.a.j jVar, g.a.a.t tVar, c cVar, g.a.a.t tVar2, g.a.a.t tVar3, g.a.a.t tVar4) {
        this.f3999c = jVar;
        this.f4000d = tVar;
        this.s = cVar;
        this.x = tVar2;
        this.y = tVar3;
        this.E3 = tVar4;
    }

    public w(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f3999c = (g.a.a.j) enumerationR.nextElement();
        this.f4000d = (g.a.a.t) enumerationR.nextElement();
        this.s = c.i(enumerationR.nextElement());
        while (enumerationR.hasMoreElements()) {
            g.a.a.q qVar = (g.a.a.q) enumerationR.nextElement();
            if (qVar instanceof x) {
                x xVar = (x) qVar;
                int iQ = xVar.q();
                if (iQ == 0) {
                    this.x = g.a.a.t.p(xVar, false);
                } else {
                    if (iQ != 1) {
                        throw new IllegalArgumentException("unknown tag value " + xVar.q());
                    }
                    this.y = g.a.a.t.p(xVar, false);
                }
            } else {
                this.E3 = (g.a.a.t) qVar;
            }
        }
    }

    public static w i(Object obj) {
        if (obj instanceof w) {
            return (w) obj;
        }
        if (obj != null) {
            return new w(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3999c);
        fVar.a(this.f4000d);
        fVar.a(this.s);
        if (this.x != null) {
            fVar.a(new h1(false, 0, this.x));
        }
        if (this.y != null) {
            fVar.a(new h1(false, 1, this.y));
        }
        fVar.a(this.E3);
        return new f0(fVar);
    }

    public g.a.a.t g() {
        return this.y;
    }

    public g.a.a.t h() {
        return this.x;
    }
}

package g.a.a.p2;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.x;
import g.a.a.y0;
import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class p extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.n f3985c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.w2.a f3986d;
    public g.a.a.t s;

    public p(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        if (((g.a.a.j) enumerationR.nextElement()).q().intValue() != 0) {
            throw new IllegalArgumentException("wrong version for private key info");
        }
        this.f3986d = g.a.a.w2.a.h(enumerationR.nextElement());
        this.f3985c = g.a.a.n.n(enumerationR.nextElement());
        if (enumerationR.hasMoreElements()) {
            this.s = g.a.a.t.p((x) enumerationR.nextElement(), false);
        }
    }

    public p(g.a.a.w2.a aVar, g.a.a.e eVar) throws IOException {
        this(aVar, eVar, null);
    }

    public p(g.a.a.w2.a aVar, g.a.a.e eVar, g.a.a.t tVar) throws IOException {
        this.f3985c = new y0(eVar.c().f("DER"));
        this.f3986d = aVar;
        this.s = tVar;
    }

    public static p g(Object obj) {
        if (obj instanceof p) {
            return (p) obj;
        }
        if (obj != null) {
            return new p(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(0L));
        fVar.a(this.f3986d);
        fVar.a(this.f3985c);
        if (this.s != null) {
            fVar.a(new h1(false, 0, this.s));
        }
        return new c1(fVar);
    }

    public g.a.a.w2.a h() {
        return this.f3986d;
    }

    public g.a.a.e i() throws IOException {
        return g.a.a.q.j(this.f3985c.p());
    }
}

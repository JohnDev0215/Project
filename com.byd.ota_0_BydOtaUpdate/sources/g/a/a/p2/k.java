package g.a.a.p2;

import g.a.a.c1;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class k extends g.a.a.l implements n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h f3971c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f3972d;

    public k(h hVar, g gVar) {
        this.f3971c = hVar;
        this.f3972d = gVar;
    }

    public k(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        g.a.a.r rVarN = g.a.a.r.n(((g.a.a.e) enumerationR.nextElement()).c());
        g.a.a.e eVarQ = rVarN.q(0);
        g.a.a.m mVar = n.H;
        if (eVarQ.equals(mVar)) {
            this.f3971c = new h(mVar, l.g(rVarN.q(1)));
        } else {
            this.f3971c = h.h(rVarN);
        }
        this.f3972d = g.h(enumerationR.nextElement());
    }

    public static k h(Object obj) {
        if (obj instanceof k) {
            return (k) obj;
        }
        if (obj != null) {
            return new k(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3971c);
        fVar.a(this.f3972d);
        return new c1(fVar);
    }

    public g g() {
        return this.f3972d;
    }

    public h i() {
        return this.f3971c;
    }
}

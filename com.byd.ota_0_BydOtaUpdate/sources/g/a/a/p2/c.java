package g.a.a.p2;

import g.a.a.f0;
import g.a.a.j0;
import g.a.a.o1;
import g.a.a.x;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class c extends g.a.a.l implements n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.m f3958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.e f3959d;
    public boolean s;

    public c(g.a.a.m mVar, g.a.a.e eVar) {
        this.s = true;
        this.f3958c = mVar;
        this.f3959d = eVar;
    }

    public c(g.a.a.r rVar) {
        this.s = true;
        Enumeration enumerationR = rVar.r();
        this.f3958c = (g.a.a.m) enumerationR.nextElement();
        if (enumerationR.hasMoreElements()) {
            this.f3959d = ((x) enumerationR.nextElement()).p();
        }
        this.s = rVar instanceof f0;
    }

    public static c i(Object obj) {
        if (obj instanceof c) {
            return (c) obj;
        }
        if (obj != null) {
            return new c(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3958c);
        g.a.a.e eVar = this.f3959d;
        if (eVar != null) {
            fVar.a(new j0(true, 0, eVar));
        }
        return this.s ? new f0(fVar) : new o1(fVar);
    }

    public g.a.a.e g() {
        return this.f3959d;
    }

    public g.a.a.m h() {
        return this.f3958c;
    }
}

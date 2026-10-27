package g.a.a.p2;

import g.a.a.f0;

/* JADX INFO: loaded from: classes.dex */
public class o extends g.a.a.l implements n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f3983c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i f3984d;

    public o(c cVar, i iVar) {
        this.f3984d = null;
        this.f3983c = cVar;
        this.f3984d = iVar;
    }

    public o(g.a.a.r rVar) {
        this.f3984d = null;
        if (g.a.a.j.n(rVar.q(0)).q().intValue() != 3) {
            throw new IllegalArgumentException("wrong version for PFX PDU");
        }
        this.f3983c = c.i(rVar.q(1));
        if (rVar.size() == 3) {
            this.f3984d = i.g(rVar.q(2));
        }
    }

    public static o h(Object obj) {
        if (obj instanceof o) {
            return (o) obj;
        }
        if (obj != null) {
            return new o(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(3L));
        fVar.a(this.f3983c);
        i iVar = this.f3984d;
        if (iVar != null) {
            fVar.a(iVar);
        }
        return new f0(fVar);
    }

    public c g() {
        return this.f3983c;
    }

    public i i() {
        return this.f3984d;
    }
}
